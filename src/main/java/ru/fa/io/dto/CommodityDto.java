package ru.fa.io.dto;

import java.math.BigDecimal;

public record CommodityDto(String username, String type, String category, BigDecimal amount, String unit) {
    public static CommodityDto of(String username, String type, String category, BigDecimal amount, String unit) {
        return new CommodityDto(username, type, category, amount, unit);
    }
}
