package ru.fa.tech.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.fa.tech.auth.ExternalSymmetricJwtAuthenticationToken;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN = "token";

    private final JwtDecodeService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var authHeader = Optional.ofNullable(request.getHeader(AUTH_HEADER)).filter(StringUtils::isNotEmpty)
                .or(() -> getAuthFromCookie(request));
        if (SecurityContextHolder.getContext().getAuthentication() != null || authHeader.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = authHeader
                .filter(auth -> auth.startsWith(BEARER_PREFIX))
                .map(auth -> auth.substring(BEARER_PREFIX.length()));
        try {
            // Пользователь авторизован на уровне прокси.
            // Если токен успешно проходит проверку подписи, авторизация считается корректной.
            var userDto = token.map(jwtService::getUserInfo);
            var internalToken = userDto
                    .map(dto -> new UsernamePasswordAuthenticationToken(
                            dto.username(),
                            null,
                            dto.roles().stream().map(role -> (GrantedAuthority) () -> role).toList()))
                    .orElseThrow(() -> new BadCredentialsException("Не удалось авторизовать пользователя"));
            SecurityContextHolder.getContext().setAuthentication(internalToken);
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("Истек срок действия токена", e);
        } catch (SignatureException e) {
            throw new BadCredentialsException("Неверная подпись", e);
        }

        filterChain.doFilter(request, response);
    }


    private Optional<? extends String> getAuthFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> TOKEN.equals(cookie.getName())).map(Cookie::getValue).findFirst();
    }
}
