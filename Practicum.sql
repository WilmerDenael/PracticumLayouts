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
	asignatura varchar(50),
	desarrollo float,
	base_de_datos float,
	redes float,
	primary key(asignatura)
);

create table notas_alumnos(
	run int,
	asignatura varchar(50),
	nota float,
	foreign key(run) references alumnos(run) on delete cascade on update cascade,
	foreign key(asignatura) references asignaturas(asignatura) on delete cascade on update cascade,
	primary key(run,asignatura)
);

create table administradores(
	run int,
	email varchar(50),
	pass blob,
	nombre varchar(30),
	primary key(run),
	foreign key(run) references usuarios(id) on delete cascade on update cascade
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

create trigger inserta_administrador before insert on administradores for each row
	begin
		insert into usuarios values(new.run,4);
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

create trigger borra_administrador before delete on administradores for each row
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

insert into profesores values (111111111,'don.gato@gatouv.cat',AES_ENCRYPT('gatin','gatin'),'Gaston','Gatuso','Fisica','Magister En Ciencias Cuanticas de los Gatos');

insert into recomendaciones values(111111111,123456789,'Un gato muy esforzado a la hora de jugar con lana');

-- (desarrollo, base de datos, redes)
insert into asignaturas values ('Algebra Elemental',0,0,0);
insert into asignaturas values ('Calculo Diferencial',0,0,0);
insert into asignaturas values ('Fundamentos de Programacion',0,0,0);
insert into asignaturas values ('Historia General de las Ciencias y las Tecnologias',0,0,0);
insert into asignaturas values ('Formacion Valorica Personal',0,0,0);
insert into asignaturas values ('Fisica',0,0,0);
insert into asignaturas values ('Calculo Integral',0,0,0);
insert into asignaturas values ('Programacion I',1,1,1);
insert into asignaturas values ('Introduccion al Hardware',0,0,2);
insert into asignaturas values ('Filosofia de las Ciencias',0,0,0);
insert into asignaturas values ('Fisica Experimental',0,0,0);
insert into asignaturas values ('Calculo Multivariable',0,0,0);
insert into asignaturas values ('Programacion II',1,1,1);
insert into asignaturas values ('Sistemas Digitales',0,0,2);
insert into asignaturas values ('Estructuras Discretas',0,0,0);
insert into asignaturas values ('Asignatura de Formacion General I',0,0,0);
insert into asignaturas values ('Algebra Lineal',0,0,0);
insert into asignaturas values ('Estructura de Datos',1,1,1);
insert into asignaturas values ('Arquitectura de Computadores',0,0,2);
insert into asignaturas values ('Teoria de Sistemas',0,0,0);
insert into asignaturas values ('Contabilidad',0,0,0);
insert into asignaturas values ('Asignatura de Formacion General II',0,0,0);
insert into asignaturas values ('Ecuaciones Diferenciales',0,0,0);
insert into asignaturas values ('Electromagnetismo',0,0,0);
insert into asignaturas values ('Analisis y Diseño de Algoritmos',1,1,1);
insert into asignaturas values ('Teoria de Sistemas Operativos',0,0,2);
insert into asignaturas values ('Fundamentos de Ingenieria de Software',2,0,0);
insert into asignaturas values ('Ingles I',0,0,0);
insert into asignaturas values ('Probabilidad y Estadistica',0,0,0);
insert into asignaturas values ('Lenguaje y Automatas',1,1,1);
insert into asignaturas values ('Redes de Computadores',0,0,2);
insert into asignaturas values ('Metodoligias de Analisis',2,0,0);
insert into asignaturas values ('Administracion en Informatica',0,0,0);
insert into asignaturas values ('Ingles II',0,0,0);
insert into asignaturas values ('Fisica Contemporanea',0,0,0);
insert into asignaturas values ('Interfaz Hombre-Maquina',2,0,0);
insert into asignaturas values ('Modelo de Datos',0,4.7,0);
insert into asignaturas values ('Taller de Sistemas Operativos',0,0,2);
insert into asignaturas values ('Metodologias de Diseño',2,0,0);
insert into asignaturas values ('Ingles III',0,0,0);
insert into asignaturas values ('Biologia',0,0,0);
insert into asignaturas values ('Desarrollo Web',2,4.6,0);
insert into asignaturas values ('Sistema de Base de Datos',0,4.7,0);
insert into asignaturas values ('Arquitectura de Software',1,1,1);
insert into asignaturas values ('Evaluacion de Proyectos',0,0,0);
insert into asignaturas values ('Lenguajes de Programacion',1,1,1);
insert into asignaturas values ('Pruebas de Software',2,0,0);
insert into asignaturas values ('Sistema de Telecomuniaciones',0,0,2);
insert into asignaturas values ('Gestion de Proyectos Informaticos',2,0,0);
insert into asignaturas values ('Investigacion de Operaciones',0,0,0);
insert into asignaturas values ('Economia',0,0,0);
insert into asignaturas values ('Fundamentos de Inteligencia Artificial',2,2,2);
insert into asignaturas values ('Taller de Aplicaciones',2,2,2);
insert into asignaturas values ('Simulacion',1,1,1);
insert into asignaturas values ('Etica y Legislacion',0,0,0);
insert into asignaturas values ('Seminario de Tecnologias de Informacion y Comunicacion',1,1,1);
insert into asignaturas values ('Taller de Base de Datos',0,3,0);
insert into asignaturas values ('Redes Complejas',0,3,0);
insert into asignaturas values ('Mineria de Datos',0,3,0);
insert into asignaturas values ('Recuperacion de Informacion',0,3,0);
insert into asignaturas values ('Bibliotecas Digitales',3,0,0);
insert into asignaturas values ('Productividad y Calidad de Software',3,0,0);
insert into asignaturas values ('Computacion Grafica',3,0,0);
insert into asignaturas values ('Accesibilidad',3,0,0);
insert into asignaturas values ('Taller de Redes',0,0,3);
insert into asignaturas values ('TCP/IP',0,0,3);
insert into asignaturas values ('Administracion de Sistemas',0,0,3);
insert into asignaturas values ('Calidad de Servicio en Redes',0,0,3);

insert into notas_alumnos values(123456789,'Algebra Elemental',5.2);
insert into notas_alumnos values(123456789,'Calculo Diferencial',6.0);
insert into notas_alumnos values(123456789,'Fundamentos de Programacion',4.2);
insert into notas_alumnos values(123456789,'Historia General de las Ciencias y las Tecnologias',5.1);
insert into notas_alumnos values(123456789,'Formacion Valorica Personal',5.8);
insert into notas_alumnos values(123456789,'Fisica',5.9);
insert into notas_alumnos values(123456789,'Calculo Integral',6.2);
insert into notas_alumnos values(123456789,'Programacion I',7.0);
insert into notas_alumnos values(123456789,'Introduccion al Hardware',4.2);
insert into notas_alumnos values(123456789,'Filosofia de las Ciencias',4.0);
insert into notas_alumnos values(123456789,'Fisica Experimental',5.2);
insert into notas_alumnos values(123456789,'Calculo Multivariable',5.5);
insert into notas_alumnos values(123456789,'Programacion II',5.8);
insert into notas_alumnos values(123456789,'Sistemas Digitales',5.9);

insert into administradores values(123,'admin@choro.com',AES_ENCRYPT('admin','gatin'),'Master Cat');