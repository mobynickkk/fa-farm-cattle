create table herd(
    id uuid primary key default gen_random_uuid(),
    animal_id uuid not null references animal (id),
    corral_id uuid not null references corral (id),
    amount integer not null
);