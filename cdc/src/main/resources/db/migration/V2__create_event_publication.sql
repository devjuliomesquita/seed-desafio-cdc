create table "EVENT_PUBLICATION"
(
    "id"                   uuid    not null,
    "completionAttempts"   integer not null,
    "completionDate"       timestamp(6) with time zone,
    "eventType"            varchar(255),
    "lastResubmissionDate" timestamp(6) with time zone,
    "listenerId"           varchar(255),
    "publicationDate"      timestamp(6) with time zone,
    "serializedEvent"      varchar(255),
    "status"               smallint check (("status" between 0 and 4)),
    primary key ("id")
);