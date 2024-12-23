create table genero(
	id int not null auto_increment primary key,
	nome varchar(30)
);



create table autor(
	id int not null auto_increment primary key,
	nome varchar(100)
);

create table livro(
id int not null auto_increment primary key,
titulo varchar(100),
isbn varchar(100),
ano_publicacao varchar(50),
id_genero int not null,
id_autor int null null,
foto longtext,
status varchar(60),

foreign key(id_genero) references genero(id),
foreign key(id_autor) references autor(id)
);




create table pessoa(
	id int not null auto_increment primary key, 
	nome varchar(100),
	email varchar(200),
	telefone varchar(100)
);


create table emprestimo(
	id int not null auto_increment primary key,
	data_emprestimo varchar(100),
	data_devolucao varchar(100),
	id_livro int not null,
	id_pessoa int not null,
	foreign key(id_livro) references livro(id),
	foreign key(id_pessoa) references pessoa(id)
);


create table reserva(
	id int not null auto_increment primary key,
	data_reserva varchar(100),
	data_validade varchar(100),
	id_livro int not null,
	id_pessoa int not null,
	foreign key(id_livro) references livro(id),
	foreign key(id_pessoa) references pessoa(id)
);
