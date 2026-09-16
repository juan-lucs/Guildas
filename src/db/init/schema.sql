create table aventureiro
(
    id        int auto_increment
        primary key,
    name      varchar(100) not null,
    nivel     int          not null,
    classe    varchar(50)  not null,
    guilda_id int          null
);

create table guilda
(
    id        int auto_increment
        primary key,
    name      varchar(100)  not null,
    level     int           not null,
    reputacao int default 0 not null,
    mestre_id int           null,
    constraint fk_guilda_mestre
        foreign key (mestre_id) references aventureiro (id)
            on delete set null
);

alter table aventureiro
    add constraint fk_aventureiro_guilda
        foreign key (guilda_id) references guilda (id)
            on delete set null;

create table missao
(
    id          int auto_increment
        primary key,
    name        varchar(100) not null,
    dificuldade int          not null,
    guilda_id   int          null,
    status      varchar(50)  not null,
    constraint fk_missao_guilda
        foreign key (guilda_id) references guilda (id)
            on delete cascade
);

create table participantesMissao
(
    aventureiro_id int null,
    missao_id      int null,
    constraint participantesMissao_ibfk_1
        foreign key (missao_id) references missao (id),
    constraint participantesMissao_ibfk_2
        foreign key (aventureiro_id) references aventureiro (id)
);

create index aventureiro_id
    on participantesMissao (aventureiro_id);

create index missao_id
    on participantesMissao (missao_id);
