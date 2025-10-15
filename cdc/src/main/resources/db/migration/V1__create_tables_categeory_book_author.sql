create table "authors"
(
    "id"          uuid                        not null,
    "created_at"  timestamp(6) with time zone not null,
    "updated_at"  timestamp(6) with time zone not null,
    "version"     bigint,
    "description" varchar(400)                not null,
    "email"       varchar(255)                not null,
    "name"        varchar(255)                not null,
    primary key ("id")
);
create table "books"
(
    "id"               uuid                        not null,
    "created_at"       timestamp(6) with time zone not null,
    "updated_at"       timestamp(6) with time zone not null,
    "version"          bigint,
    "title"            varchar(255)                not null,
    "abstract_text"    varchar(500)                not null,
    "price"            numeric(38, 2)              not null,
    "number_of_pages"  integer                     not null,
    "publication_date" date,
    "isbn"             varchar(255)                not null,
    "author_id"        uuid                        not null,
    "category_id"      uuid                        not null,
    primary key ("id")
);
create table "categories"
(
    "id"         uuid                        not null,
    "created_at" timestamp(6) with time zone not null,
    "updated_at" timestamp(6) with time zone not null,
    "version"    bigint,
    "name"       varchar(255)                not null,
    primary key ("id")
);
alter table if exists "authors"
    drop constraint if exists "UKmcs1p08va8d0nn29n5fo0a55l";
alter table if exists "authors"
    add constraint "UKmcs1p08va8d0nn29n5fo0a55l" unique ("email");
alter table if exists "books"
    drop constraint if exists "UKqtsjivajbskak1k58sd57v81h";
alter table if exists "books"
    add constraint "UKqtsjivajbskak1k58sd57v81h" unique ("title");
alter table if exists "books"
    drop constraint if exists "UKfk0d0dgf8wq1lm1lqkaic7jw9";
alter table if exists "books"
    add constraint "UKfk0d0dgf8wq1lm1lqkaic7jw9" unique ("isbn");
alter table if exists "categories"
    drop constraint if exists "UK63be57w0lp7mjmyg4bhlp08s7";
alter table if exists "categories"
    add constraint "UK63be57w0lp7mjmyg4bhlp08s7" unique ("name");
alter table if exists "books"
    add constraint "FK9rceatmdmh8w7ofovg6qwwu8s" foreign key ("author_id") references "authors";
alter table if exists "books"
    add constraint "FK1fclg689sbwwm3awxy2t6ij6v" foreign key ("category_id") references "categories";