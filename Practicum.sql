drop database if exists practicum;
create database practicum;
use practicum;

-- Variable usada como llave para encriptar pass
-- Se debe inicializar siempre
select @pss:='gatin';

-- Para ingresar la pass encriptada, debes insertar en la columna pass lo siguiente: AES_ENCRYPT(pass,@pss)
-- Para obtener el valor de pass, se debe hacer lo siguiente: AES_DECRYPT(columna_encriptada,@pss)
-- Si no cachas todavia... metete a http://donnierock.com/2012/09/10/encriptacion-aes-en-mysql-y-mariadb/

create table usuarios(
	id int,
	tipo int,
	primary key(id)
	);
	
create table alumnos(
	run int,
	email varchar(50),
	pass blob,
	primer_nombre varchar(30),
	segundo_nombre varchar(30),
	paterno varchar(30),
	materno varchar(30),
	estado_civil varchar(20),
	direccion text,
	nacionalidad varchar(30),
	nacimiento date,
	referencia text, 
	datos_adicionales text, 
	primary key(run),
	foreign key(run) references usuarios(id) on delete cascade on update cascade
	);	
		
-- Tabla que contiene los curriculums	
create table curriculums(
	id int,
	run int,
	is_activo boolean,
	ultima_actualizacion date,
	confidencialidad boolean, -- visibilidad del curriculum
	foreign key(run) references alumnos(run) on delete cascade on update cascade,
	primary key(run,id)
	);
	
-- Areas Principales de interes y a que pertenecen
create table areas_interes(
	id int auto_increment,
	area varchar(50),
	desarrollo int,
	base_de_datos int,
	redes int,
	primary key(id)
	);

-- Compuesta de areas de interes y curriculum	
create table curriculums_areas_interes(
	id int,
	run int,
	id_curriculum int,
	foreign key(run,id_curriculum) references curriculums(run,id) on delete cascade on update cascade,
	foreign key(id) references areas_interes(id) on delete cascade on update cascade,
	primary key(id,run,id_curriculum)
	);
	
create table datos_academicos(
	id int auto_increment,
	run int,
	id_curriculum int,
	establecimiento text,
	inicio date,
	fin date,
	descripcion text,
	foreign key(run,id_curriculum) references curriculums(run,id) on delete cascade on update cascade,
	primary key(id)
	);
	
create table historial_laboral(
	id int auto_increment,
	run int,
	id_curriculum int,
	establecimiento text,
	cargo text,
	inicio date,
	fin date,
	descripcion text,
	foreign key(run,id_curriculum) references curriculums(run,id) on delete cascade on update cascade,
	primary key(id)
	);
	
create table idiomas(
	id int auto_increment,
	idioma varchar(30),
	primary key(id)
	);
	
create table manejo_idiomas(
	id int,
	run int,
	id_curriculum int,
	nivel enum('Basico','Medio','Avanzado'),
	foreign key(id) references idiomas(id) on delete cascade on update cascade,
	foreign key(run,id_curriculum) references curriculums(run,id) on delete cascade on update cascade,
	primary key(id,run,id_curriculum)
	);

create table empresas(
	rut int,
	email varchar(50),
	pass blob,
	nombre varchar(30),
	numero_trabajadores int,
	nacionalidad varchar(30),
	area_laboral text,
	direccion text,
	descripcion text,
	foreign key(rut) references usuarios(id) on delete cascade on update cascade,
	primary key(rut)
	);
		
create table profesores(
	run int,
	email varchar(50),
	pass blob,
	nombre varchar(30),
	paterno varchar(30),
	ramo text,
	titulo text,
	foreign key(run) references usuarios(id) on delete cascade on update cascade,
	primary key(run)
	);

create table recomendaciones(
	run_profesor int,
	run_alumno int,
	recomendacion text,
	foreign key(run_profesor) references profesores(run) on delete cascade on update cascade,
	foreign key(run_alumno) references alumnos(run) on delete cascade on update cascade,
	primary key(run_profesor, run_alumno)
);

create table asignaturas(
	id int,
	asignatura varchar(50),
	desarrollo int,
	base_de_datos int,
	redes int,
	primary key(id)
);

create table notas_alumnos(
	run int,
	id_asignatura int,
	nota float,
	foreign key(run) references alumnos(run) on delete cascade on update cascade,
	foreign key(id_asignatura) references asignaturas(id) on delete cascade on update cascade,
	primary key(run,id_asignatura)
);

-- Trigger para insertar usuarios
	
delimiter ??

create trigger inserta_alumno before insert on alumnos for each row
	begin
		insert into usuarios values(new.run,1);
	end
??

create trigger inserta_empresa before insert on empresas for each row
	begin
		insert into usuarios values(new.rut,2);
	end
??

create trigger inserta_profesor before insert on profesores for each row
	begin
		insert into usuarios values(new.run,3);
	end
??

-- Triggers para borrar usuario

create trigger borra_alumno before delete on alumnos for each row
	begin
		delete from usuarios where id = old.run;
	end
??

create trigger borra_empresa before delete on empresas for each row
	begin
		delete from usuarios where id = old.rut;
	end
??

create trigger borra_profesor before delete on profesores for each row
	begin
		delete from usuarios where id = old.run;
	end
??

delimiter ;

-- Inserciones para el test del programa

insert into alumnos values(123456789,'gatin@test.com',AES_ENCRYPT('gatin','gatin'),'Gatin','Gatuso','Cat','Kitty','Soltero','Villa Gatito #001','Gatunesca','2000-03-22','Soy un gatito bien sensual','Hola, soy el dato adicional');

insert into curriculums values (1,123456789,'true','2013-03-01','true');

insert into areas_interes values(null,'Lenguaje Java',1,0,0);
insert into areas_interes values(null,'Lenguaje Ruby',1,0,0);
insert into areas_interes values(null,'Lenguaje Python',1,0,0);
insert into areas_interes values(null,'Lenguaje C',1,0,0);
insert into areas_interes values(null,'Lenguaje C++',1,0,0);
insert into areas_interes values(null,'UML',1,0,0);
insert into areas_interes values(null,'IDE Eclipse',1,0,0);
insert into areas_interes values(null,'IDE NetBeans',1,0,0);
insert into areas_interes values(null,'IDE TopBraid',1,0,0);
insert into areas_interes values(null,'Lenguaje HTML',1,0,0);
insert into areas_interes values(null,'Lenguaje CSS',1,0,0);
insert into areas_interes values(null,'Lenguaje XML',1,0,0);
insert into areas_interes values(null,'Lenguaje RDF',1,0,0);
insert into areas_interes values(null,'Lenguaje WSDL',1,0,0);
insert into areas_interes values(null,'IDE Knime',0,1,0);
insert into areas_interes values(null,'MySql',0,1,0);
insert into areas_interes values(null,'PostgresSql',0,1,0);
insert into areas_interes values(null,'Lenguaje Prolog',0,1,0);
insert into areas_interes values(null,'Workbench',0,1,0);
insert into areas_interes values(null,'Lenguaje PHP',1,1,0);
insert into areas_interes values(null,'IDE Core',0,0,1);

	
insert into curriculums_areas_interes values(1,123456789,1);
insert into curriculums_areas_interes values(2,123456789,1);
insert into curriculums_areas_interes values(4,123456789,1);
insert into curriculums_areas_interes values(6,123456789,1);
insert into curriculums_areas_interes values(10,123456789,1);
insert into curriculums_areas_interes values(15,123456789,1);
insert into curriculums_areas_interes values(20,123456789,1);

	
insert into datos_academicos values(null,123456789,1,'Escuela Gatitos de Kitty','1995-03-05','2003-12-20','Primaria de Gatitos');
insert into datos_academicos values(null,123456789,1,'Liceo de Gatos','2004-03-05','2007-12-20','Secundaria Hermosa de Gatitos');
insert into datos_academicos values(null,123456789,1,'Universidad de Gatos','2008-03-05',null,'Universidad de Gatos');

insert into historial_laboral values(null,123456789,1,'Veterinaria Gatitos Felices','Jefe de Cajas de Arenas','2010-01-10','2012-12-01','Limpieza y Orden de Cajas de Gatos');
insert into historial_laboral values(null,123456789,1,'Sicario de Perros','Ejecutor','2012-02-11',null,'Asesinatas con armas de fuego');

insert into idiomas values (null,'Ingles'); 
insert into idiomas values (null,'Portugues'); 
insert into idiomas values (null,'Frances'); 
insert into idiomas values (null,'Aleman');
insert into idiomas values (null,'Chino Mandarin');

insert into manejo_idiomas values(1,123456789,1,'Basico');
insert into manejo_idiomas values(3,123456789,1,'Avanzado');
insert into manejo_idiomas values(5,123456789,1,'Medio');

insert into empresas values (987654321,'gatos.inc@gatin.com',AES_ENCRYPT('gatin','gatin'),'Gatos INC',69,'Gatunesca','Exportacion de Gatos Menores de Edad','Kitty #32, Catland','Empresa dedicada a la exportacion de gatitos menores de edad');

insert into profesores values (111111111,'don.gato@gatouv.cat',AES_ENCRYPT('gatin','gatin'),'Gaston','Gatuso','Física','Magister En Ciencias Cuanticas de los Gatos');

insert into recomendaciones values(111111111,123456789,'Un gato muy esforzado a la hora de jugar con lana');


insert into asignaturas values (100,'Algebra Elemental',0,0,0);
insert into asignaturas values (101,'Cálculo Diferencial',0,0,0);
insert into asignaturas values (102,'Fundamentos de Programación',0,0,0);
insert into asignaturas values (103,'Historia General de las Ciencias y las Tecnologías',0,0,0);
insert into asignaturas values (104,'Formación Valórica Personal',0,0,0);
insert into asignaturas values (110,'Física',0,0,0);
insert into asignaturas values (111,'Cálculo Integral',0,0,0);
insert into asignaturas values (112,'Programación I',1,0,0);
insert into asignaturas values (113,'Introducción al Hardware',0,0,1);
insert into asignaturas values (114,'Filosofía de las Ciencias',0,0,0);
insert into asignaturas values (200,'Física Experimental',0,0,0);
insert into asignaturas values (201,'Cálculo Multivariable',0,0,0);
insert into asignaturas values (202,'Programación II',1,0,0);
insert into asignaturas values (203,'Sistemas Digitales',0,0,1);
insert into asignaturas values (204,'Estructuras Discretas',0,0,0);
insert into asignaturas values (205,'Asignatura de Formación General I',0,0,0);
insert into asignaturas values (210,'Álgebra Lineal',0,0,0);
insert into asignaturas values (211,'Estructura de Datos',1,1,0);
insert into asignaturas values (212,'Arquitectura de Computadores',0,0,1);
insert into asignaturas values (213,'Teoría de Sistemas',0,0,0);
insert into asignaturas values (214,'Contabilidad',0,0,0);
insert into asignaturas values (215,'Asignatura de Formación General II',0,0,0);
insert into asignaturas values (300,'Ecuaciones Diferenciales',0,0,0);
insert into asignaturas values (301,'Electromagnetismo',0,0,0);
insert into asignaturas values (302,'Análisis y Diseño de Algoritmos',1,0,0);
insert into asignaturas values (303,'Teoría de Sistemas Operativos',0,0,1);
insert into asignaturas values (304,'Fundamentos de Ingeniería de Software',1,0,0);
insert into asignaturas values (305,'Inglés I',0,0,0);
insert into asignaturas values (310,'Probabilidad y Estadística',0,0,0);
insert into asignaturas values (311,'Lenguaje y Autómatas',1,1,0);
insert into asignaturas values (312,'Redes de Computadores',0,0,1);
insert into asignaturas values (313,'Metodoligías de Análisis',1,0,0);
insert into asignaturas values (314,'Administración en Informática',0,0,0);
insert into asignaturas values (315,'Inglés II',0,0,0);
insert into asignaturas values (400,'Física Contemporánea',0,0,0);
insert into asignaturas values (401,'Interfaz Hombre-Máquina',1,0,0);
insert into asignaturas values (402,'Modelo de Datos',0,1,0);
insert into asignaturas values (403,'Taller de Sistemas Operativos',0,0,1);
insert into asignaturas values (404,'Metodologías de Diseño',1,0,0);
insert into asignaturas values (405,'Inglés III',0,0,0);
insert into asignaturas values (410,'Biología',0,0,0);
insert into asignaturas values (411,'Desarrollo Web',1,0,0);
insert into asignaturas values (412,'Sistema de Base de Datos',0,1,0);
insert into asignaturas values (413,'Arquitectura de Software',1,0,0);
insert into asignaturas values (414,'Evaluación de Proyectos',1,0,0);
insert into asignaturas values (415,'Lenguajes de Programación',1,1,0);
insert into asignaturas values (500,'Pruebas de Software',1,0,0);
insert into asignaturas values (503,'Sistema de Telecomuniaciones',0,0,1);
insert into asignaturas values (504,'Gestión de Proyectos Informáticos',1,0,0);
insert into asignaturas values (505,'Investigación de Operaciones',0,0,0);
insert into asignaturas values (510,'Economía',0,0,0);
insert into asignaturas values (513,'Fundamentos de Inteligencia Artificial',0,0,0);
insert into asignaturas values (514,'Taller de Aplicaciones',1,1,1);
insert into asignaturas values (515,'Simulación',0,0,0);
insert into asignaturas values (602,'Ética y Legislación',0,0,0);
insert into asignaturas values (610,'Seminario de Tecnologías de Información y Comunicación',1,1,1);
insert into asignaturas values (700,'Taller de Base de Datos',0,1,0);
insert into asignaturas values (701,'Bases de Datos Avanzadas',0,1,0);
insert into asignaturas values (702,'Minería de Datos',0,1,0);
insert into asignaturas values (703,'Recuperación de Información',0,1,0);
insert into asignaturas values (710,'Bibliotecas Digitales',1,0,0);
insert into asignaturas values (711,'Productividad y Calidad de Software',1,0,0);
insert into asignaturas values (712,'Computación Gráfica',1,0,0);
insert into asignaturas values (720,'Taller de Redes',0,0,1);
insert into asignaturas values (721,'TCP/IP',0,0,1);
insert into asignaturas values (722,'Administración de Sistemas',0,0,1);
insert into asignaturas values (723,'Calidad de Servicio en Redes',0,0,1);

insert into notas_alumnos values(123456789,100,5.2);
insert into notas_alumnos values(123456789,101,6.0);
insert into notas_alumnos values(123456789,102,4.2);
insert into notas_alumnos values(123456789,103,5.1);
insert into notas_alumnos values(123456789,104,5.8);
insert into notas_alumnos values(123456789,110,5.9);
insert into notas_alumnos values(123456789,111,6.2);
insert into notas_alumnos values(123456789,112,7.0);
insert into notas_alumnos values(123456789,113,4.2);
insert into notas_alumnos values(123456789,114,4.0);
insert into notas_alumnos values(123456789,200,5.2);
insert into notas_alumnos values(123456789,201,5.5);
insert into notas_alumnos values(123456789,202,5.8);
insert into notas_alumnos values(123456789,203,5.9);