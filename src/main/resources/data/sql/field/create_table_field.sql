create table field(
    id uuid primary key default gen_random_uuid(),
    name varchar(50) not null
);