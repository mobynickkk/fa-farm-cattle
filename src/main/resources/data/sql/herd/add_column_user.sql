alter table herd add column username varchar(50);
create index idx_herd_username on herd using hash(username);