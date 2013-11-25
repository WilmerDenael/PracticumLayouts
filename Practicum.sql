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

-- Areas Principales de interes
create table areas_interes(
	id int auto_increment,
	area varchar(50),
	primary key(id)
	);
	
insert into areas_interes (area) values ('Desarrollo') , ('Base de Datos') , ('Redes');

-- Sub Areas de interes, cada una de ellas tiene relación con 3 áreas principales
create table sub_areas_interes(
	id int auto_increment,
	id_area int,
	sub_area varchar(50),
	primary key(id),
	foreign key(id_area) references areas_interes(id) on delete cascade on update cascade
	);
	
	
create table curriculums_areas_interes(
	id int,
	run int,
	id_curriculum int,
	foreign key(run,id_curriculum) references curriculums(run,id) on delete cascade on update cascade,
	foreign key(id) references sub_areas_interes(id) on delete cascade on update cascade,
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
	foreign key(run) references usuarios(id) on delete cascade on update cascade,
	primary key(run)
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