# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table client_user (
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
  constraint ck_client_user_client_user_status check ( client_user_status in ('leaved','temporary','verified')),
  constraint uq_client_user_email unique (email),
  constraint pk_client_user primary key (id)
);
create sequence client_user_seq;


# --- !Downs

drop table if exists client_user cascade;
drop sequence if exists client_user_seq;

