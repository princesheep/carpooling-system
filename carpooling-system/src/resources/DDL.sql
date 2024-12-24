create table user
(
    id          bigint auto_increment
        primary key,
    username    varchar(50)                        not null,
    password    varchar(255)                       not null,
    email       varchar(100)                       null,
    phone       varchar(20)                        null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint idx_users_email
        unique (email),
    constraint idx_users_username
        unique (username),
    constraint username
        unique (username)
);

create table carpool
(
    id              bigint auto_increment
        primary key,
    owner_id        bigint                             not null,
    start_point     varchar(255)                       not null,
    end_point       varchar(255)                       not null,
    departure_time  datetime                           not null,
    available_seats int                                not null,
    route           varchar(255)                       null,
    plate_number    varchar(50)                        not null,
    total_seats     int                                not null,
    create_time     datetime default CURRENT_TIMESTAMP null,
    update_time     datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint carpool_ibfk_1
        foreign key (owner_id) references user (id)
            on delete cascade
);

create index idx_carpool_departure_time
    on carpool (departure_time);

create index idx_carpool_owner_id
    on carpool (owner_id);

create table carpool_order
(
    id         bigint auto_increment
        primary key,
    user_id    bigint                                not null,
    carpool_id bigint                                not null,
    status     varchar(20) default 'PENDING'         null,
    created_at datetime    default CURRENT_TIMESTAMP null,
    updated_at datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    remark     varchar(500)                          null,
    constraint carpool_order_ibfk_1
        foreign key (user_id) references user (id)
            on delete cascade,
    constraint carpool_order_ibfk_2
        foreign key (carpool_id) references carpool (id)
            on delete cascade
);

create index idx_order_carpool_id
    on carpool_order (carpool_id);

create index idx_order_user_id
    on carpool_order (user_id);

create table user_role
(
    id   bigint      not null,
    role varchar(20) not null,
    primary key (id, role),
    constraint user_role_user_id_fk
        foreign key (id) references user (id)
);

