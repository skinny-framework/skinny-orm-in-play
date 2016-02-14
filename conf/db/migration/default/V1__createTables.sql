create table user (
  id bigserial not null,
  name varchar(20) not null,
  description text,
  created_at timestamp not null default current_timestamp
);

create table tweet (
  id bigserial not null,
  user_id bigint not null references user(id),
  text text not null default '',
  created_at timestamp not null default current_timestamp
);
