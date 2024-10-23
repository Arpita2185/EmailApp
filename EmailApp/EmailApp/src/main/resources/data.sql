create table person (
id integer not null, 
name varchar (255) not null, 
location varchar(50), 
birth_date timestamp,
primary key(id));


insert into person (id, name, location, Birth_date) values (10001, 'Sandhya J1','Mumbai',sysdate);  
insert into person (id, name, location, Birth_date) values (10002, 'Sandhya S','GT',sysdate);  
