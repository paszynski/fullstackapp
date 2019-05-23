
insert into colors (value, text) values ("blue","niebieski");
insert into colors (value, text) values ("red","czerwony");
insert into colors (value, text) values ("green","zielony");
insert into colors (value, text) values ("yellow","żółty");
insert into colors (value, text) values ("black","czarny");

insert into sizes (value,text) values (25,"S");
insert into sizes (value,text) values (50,"M");
insert into sizes (value,text) values (75,"L");
insert into sizes (value,text) values (100,"XL");


insert into colorsizelimit (color,`size`,`limit`) values ("blue",25,1);
insert into colorsizelimit (color,`size`,`limit`) values ("red",25,2);
insert into colorsizelimit (color,`size`,`limit`) values ("green",25,3);
insert into colorsizelimit (color,`size`,`limit`) values ("yellow",25,4);
insert into colorsizelimit (color,`size`,`limit`) values ("black",25,5);

insert into colorsizelimit (color,`size`,`limit`) values ("blue",50,1);
insert into colorsizelimit (color,`size`,`limit`) values ("red",50,0);
insert into colorsizelimit (color,`size`,`limit`) values ("green",50,0);
insert into colorsizelimit (color,`size`,`limit`) values ("yellow",50,0);
insert into colorsizelimit (color,`size`,`limit`) values ("black",50,5);

insert into colorsizelimit (color,`size`,`limit`) values ("yellow",100,1);
