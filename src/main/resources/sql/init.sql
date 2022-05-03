create table if not exists SAVE_POINT(
    title_name varchar(127) primary key,
    series_num integer not null default 1,
    series_duration varchar(5) not null default '00:00',
    dub_name varchar (15),
    source_uri varchar(255) not null,
    update_time timestamp default current_timestamp);