create database shopfone;
use shopfone;
insert into custumer values
(2,'lala@gmail.com','123','Nguyễn Văn A','Tôn đản Q4','0807112115','2021-05-19 02:13:23','2021-05-19 02:13:23',1,'BASIC'
);
delete from user_1 where id1=2;
select * from user_1;
create table role_1
(
idrole int not null,
role_name varchar(30) not null
);
select * from role_1;
create table user_role
(
id1 int auto_increment not null ,
idrole int null default 1,
primary key (id1,idrole),
foreign key (id1) references custumer(id),
foreign key (idrole) references role_1(idrole)
);
select * from user_role;
create table custumer
(
id int auto_increment not null primary key,
email1 varchar(128) not null,
password1 varchar(128)not null,
hoten varchar(128) null,
diachi1 varchar(128)  null,
sdt1 varchar(15)  null,
createtime timestamp not null default current_timestamp,
lasttime timestamp not null default current_timestamp,
enabled tinyint(4) default 0,
auth_provider varchar(15)
);
alter table custumer add column verification_code varchar(64) null;
drop table custumer;
select * from custumer;
