alter table corral add column username varchar(50);
create index idx_username on corral using hash(username);