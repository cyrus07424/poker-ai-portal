# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table master_setting (
  id                            bigint not null,
  delete_flag                   boolean not null,
  master_setting_key            varchar(11) not null,
  master_setting_value          text not null,
  create_date                   timestamp not null,
  update_date                   timestamp not null,
  version                       bigint not null,
  constraint ck_master_setting_master_setting_key check ( master_setting_key in ('information')),
  constraint pk_master_setting primary key (id)
);
create sequence master_setting_seq;

create table user_table (
  id                            bigint not null,
  delete_flag                   boolean not null,
  name                          varchar(255),
  user_role                     varchar(7) not null,
  email                         varchar(255) not null,
  password                      varchar(255),
  user_status                   varchar(13) not null,
  biography                     text,
  verify_date                   timestamp,
  email_update_flag             boolean not null,
  new_email                     varchar(255),
  email_update_date             timestamp,
  password_reset_flag           boolean not null,
  password_reset_date           timestamp,
  verify_uuid                   varchar(255),
  email_update_uuid             varchar(255),
  password_reset_uuid           varchar(255),
  create_date                   timestamp not null,
  update_date                   timestamp not null,
  version                       bigint not null,
  constraint ck_user_table_user_role check ( user_role in ('admin','general')),
  constraint ck_user_table_user_status check ( user_status in ('temporary','notRegistered','本登録')),
  constraint uq_user_table_email unique (email),
  constraint pk_user_table primary key (id)
);
create sequence user_table_seq;


# --- !Downs

drop table if exists master_setting;
drop sequence if exists master_setting_seq;

drop table if exists user_table;
drop sequence if exists user_table_seq;

