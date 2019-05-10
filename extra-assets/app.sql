create table ciclo (
idciclo              integer              not null,
ciclo                integer,
anio                  date,
primary key (idciclo)
);

create table rolrevision (
idrol                integer              not null,
nombre               varchar(10)          not null,
descripcion          char(254),
primary key (idrol)
);

create table tipodocente (
idtipodocente        integer              not null,
nombre               varchar(10),
primary key (idtipodocente)
);

create table docente (
iddocente            integer              not null,
idtipodocente        integer              not null,
nombre               varchar(10)          not null,
apellidos            varchar(50)          not null,
cod_docente          varchar(10),
sexo                 varchar(1)           not null,
primary key (iddocente),
foreign key (idtipodocente)
      references tipodocente (idtipodocente) on delete restrict
);

create table local (
idlocal              integer              not null,
codigoedificio       varchar(5),
nombrelocal          varchar(30)          not null,
codigolocal          varchar(5),
capacidad            integer,
primary key (idlocal)
);

create table tipotramite (
idtipotramite        integer              not null,
nombrelocal          varchar(30)          not null,
descripcion          char(254),
primary key (idtipotramite)
);

create table tramite (
fechasolicitud       date                 not null,
idtramite            integer              not null,
idlocal              integer,
idtipotramite        integer              not null,
primary key (idtramite),
foreign key (idlocal)
      references local (idlocal) on delete restrict,
foreign key (idtipotramite)
      references tipotramite (idtipotramite) on delete restrict
);

create table detalledocente (
idrol                integer              not null,
iddocente            integer              not null,
idtramite            integer              not null,
asistencia           smallint             not null,
motivoinasistencia   char(254),
primary key (idrol, iddocente, idtramite),
foreign key (idrol)
      references rolrevision (idrol) on delete restrict,
foreign key (iddocente)
      references docente (iddocente) on delete restrict,
foreign key (idtramite)
      references tramite (idtramite) on delete restrict
);

create table tipogrupo (
idtipogrupo          integer              not null,
nombre               varchar(10)          not null,
primary key (idtipogrupo)
);

create table materia (
idmateria            integer              not null,
nombre               varchar(10)          not null,
codigo_materia       varchar(7)           not null,
uvs                  integer              not null,
primary key (idmateria)
);

create table materiaciclo (
idmatciclo           integer              not null,
idmateria            integer,
idciclo              integer,
primary key (idmatciclo),
foreign key (idciclo)
      references ciclo (idciclo) on delete restrict,
foreign key (idmateria)
      references materia (idmateria) on delete restrict
);

create table grupomateriaciclo (
idgrupo              integer              not null,
iddocente            integer              not null,
idmatciclo           integer,
idtipogrupo          integer,
codgrupo             varchar(5)           not null,
cantidadalumnos      integer,
capacidadalumnos     integer,
primary key (idgrupo),
foreign key (iddocente)
      references docente (iddocente) on delete restrict,
foreign key (idtipogrupo)
      references tipogrupo (idtipogrupo) on delete restrict,
foreign key (idmatciclo)
      references materiaciclo (idmatciclo) on delete restrict 
);

create table tipoevaluacion (
idtipoevaluacion     integer              not null,
nombre               varchar(10)          not null,
descripcion          char(254),
primary key (idtipoevaluacion)
);

create table evaluacion (
idevaluacion         integer              not null,
idgrupo              integer,
idtipoevaluacion     integer,
nombreevaluacion     varchar(20)          not null,
fechasolicitud       date                 not null,
primary key (idevaluacion),
foreign key (idgrupo)
      references grupomateriaciclo (idgrupo) on delete restrict,
foreign key (idtipoevaluacion)
      references tipoevaluacion (idtipoevaluacion) on delete restrict
);

create table detallelocal (
idlocal              integer              not null,
idevaluacion         integer              not null,
cantidadalumnos      integer,
primary key (idlocal, idevaluacion),
foreign key (idlocal)
      references local (idlocal) on delete restrict,
foreign key (idevaluacion)
      references evaluacion (idevaluacion) on delete restrict
);

create table estudiante (
idestudiante         integer              not null,
nombres              varchar(60)          not null,
apelllidos           varchar(60)          not null,
carnet               varchar(7)           not null,
sexo                 varchar(1)           not null,
primary key (idestudiante)
);

create table detallerevision (
idtramite            integer              not null,
idestudiante         integer              not null,
resultado            integer,
motivo               char(254),
asistencia           smallint,
primary key (idtramite, idestudiante),
foreign key (idtramite)
      references tramite (idtramite) on delete restrict,
foreign key (idestudiante)
      references estudiante (idestudiante) on delete restrict
);

create table detallesolicitud (
idtramite            integer              not null,
idestudiante         integer              not null,
motivo               char(254),
esrechazado          smallint,
primary key (idtramite, idestudiante),
foreign key (idtramite)
      references tramite (idtramite) on delete restrict,
foreign key (idestudiante)
      references estudiante (idestudiante) on delete restrict
);

create table diasnohabiles (
id_dias              integer              not null,
idciclo              integer,
nombre               varchar(10)          not null,
descripcion          char(254),
fechasolicitud       date,
primary key (id_dias),
foreign key (idciclo)
      references ciclo (idciclo) on delete restrict
);

create table estadoevaluacion (
idestado             integer              not null,
idevaluacion         integer,
idestudiante         integer,
nota                 float                not null,
primary key (idestado),
foreign key (idestudiante)
      references estudiante (idestudiante) on delete restrict,
foreign key (idevaluacion)
      references evaluacion (idevaluacion) on delete restrict
);

create table parametro (
parametro            varchar(10)          not null,
diashabiles          integer
);

create table solicitudimpresa (
idsolicitudimp       integer              not null,
iddocente            integer              not null,
cantidadimpresiones  integer              not null,
asunto               varchar(20)          not null,
justificacion        char(254),
aprobado             smallint,
fechasolicitud       date,
paginasanexas        integer,
codigoimpresion      varchar(5),
primary key (idsolicitudimp),
foreign key (iddocente)
      references docente (iddocente) on delete restrict
);

create table testigo (
idtestigo            integer              not null,
idtramite            integer,
idestudiante         integer,
justificacion        char(254)            not null,
primary key (idtestigo),
foreign key (idestudiante)
      references estudiante (idestudiante) on delete restrict, 
foreign key (idtramite)
      references tramite (idtramite) on delete restrict
);

