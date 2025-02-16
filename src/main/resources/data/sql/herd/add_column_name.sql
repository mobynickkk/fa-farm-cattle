alter table herd drop column amount;
alter table herd add column name varchar(64);
alter table herd add column birth_date timestamptz;