create database future_iot_beta character set utf8 collate utf8_unicode_ci;
use future_iot_beta;

create table employee(
	id int auto_increment primary key,
	username varchar(15) unique,
    hash_pass varchar(300),
    fullname nvarchar(50),
    email varchar(50),
    create_date date,
    address nvarchar(100)
	);    
    
create table role(
	id int auto_increment primary key,
    employee_id int,
    role varchar(20)
    );

create table device(
	id int auto_increment primary key,
	mac_address varchar(100),
    employee_id int ,
    description nvarchar(30),
    status nvarchar(20),
    type_code varchar(10),
    gpio varchar(10)
    );

create table reley(
	id int auto_increment primary key,
    device_id int unique,
    value smallint,
    update_time datetime,
    time_lable int
    );

create table sensor_temperature(
	id bigint auto_increment primary key,
	device_id int,
    temperature_value float,
    humidity_value float,
    update_time datetime,
	time_lable int
);
create table type_device(
	type_code varchar(10)  primary key,
    type_name nvarchar(30)
); 
create table key_manage_device(
	mac_address varchar(100) primary key,
    algo varchar(20),
    secret_key text,
    public_key text,
    public_key_client text,
    private_key text,
    iv text,
    hash_mac text
    );
    
create table posts(
 id int primary key,
 title nvarchar(200),
 draftersId int,
 content text,
 theme nvarchar(30)
);
alter table role add  foreign key (employee_id) references employee (id)  ON DELETE CASCADE ;    
alter table device add  foreign key (employee_id) references employee (id)  ON DELETE CASCADE ;    
alter table device add  foreign key (type_code) references type_device (type_code); 
alter table device add  foreign key (mac_address) references key_manage_device (mac_address) ON DELETE CASCADE;    
alter table sensor_temperature add  foreign key (device_id) references device (id)  ON DELETE CASCADE ;
alter table reley add  foreign key (device_id) references device (id)  ON DELETE CASCADE ;

alter table posts  add foreign key (draftersId) references employee(id);
    
insert into employee(username,fullname,hash_pass,email) values ('0398749499','Trần Việt Anh','$2a$10$bIZ5tAILMh/VpTs3tJVEIeBHxYh/Ut..4qNsI1n4hWw5myhe85Z1q','tranvietanh190196@gmail.com');    
insert into role(employee_id, role) values (1, 'ROLE_ADMIN');
insert into role(employee_id, role) values (1, 'ROLE_USER');
insert into role(employee_id, role) values (1, 'ROLE_POSTER');
insert into type_device(type_code, type_name) values ('cbnd', 'Cảm biến nhiệt độ');
insert into type_device(type_code, type_name) values ('reley', 'Thiết bị bật tắt');


