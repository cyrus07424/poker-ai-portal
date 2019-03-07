# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user_table (
  id                            bigint not null,
  email                         varchar(255) not null,
  password                      varchar(255) not null,
  api_key                       varchar(255) not null,
  client_user_status            varchar(9) not null,
  verify_date                   timestamptz,
  email_update_flag             boolean not null,
  new_email                     varchar(255),
  verify_uuid                   varchar(255),
  email_update_uuid             varchar(255),
  create_date                   timestamptz not null,
  update_date                   timestamptz not null,
  version                       bigint not null,
  constraint ck_user_table_client_user_status check ( client_user_status in ('leaved','temporary','verified')),
  constraint uq_user_table_email unique (email),
  constraint pk_user_table primary key (id)
);
create sequence user_table_seq;


# --- !Downs

drop table if exists user_table cascade;
drop sequence if exists user_table_seq;

