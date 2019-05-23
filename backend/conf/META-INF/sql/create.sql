create table orders ( id int auto_increment, name varchar(255) not null,  age int not null, date_added datetime not null default now(), primary key(id) );
create table entries (  id int auto_increment, color varchar(255) not null, `size` int not null, order_id int not null, primary key(id), constraint FK_EntryOrder foreign key (order_id) references orders (id));
create table colors ( value varchar(50), text varchar(50) not null, primary key(value) );
create table sizes ( value int, text varchar(50) not null, primary key (value) );
create table colorsizelimit ( color varchar(50), `size` int, `limit` int not null, primary key(color, `size`) );