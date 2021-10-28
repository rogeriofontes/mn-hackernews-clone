CREATE TABLE tb_user (
	id SERIAL CONSTRAINT pk_id_user PRIMARY KEY,
	username VARCHAR(60) NOT NULL,
	password VARCHAR(60) NOT NULL,
	role VARCHAR(60) NOT NULL,
	token VARCHAR(100)
);

CREATE TABLE tb_person (
	id SERIAL CONSTRAINT pk_id_person PRIMARY KEY,
	name VARCHAR(60),
	age integer NOT NULL
);

--CREATE TABLE tbl_livros (
 --ID_Livro SERIAL CONSTRAINT pk_id_livro PRIMARY KEY,
 --Nome_Livro varchar(50) NOT NULL,
 --Autor integer NOT NULL,
 --Editora integer NOT NULL,
 --Data_Pub date,
 --Genero integer NOT NULL,
 --Preco_Livro money,
 --FOREIGN KEY (Autor) REFERENCES tbl_autores (ID_Autor) ON DELETE CASCADE,
 --FOREIGN KEY (Editora) REFERENCES tbl_editoras (ID_Editora) ON DELETE CASCADE,
 --FOREIGN KEY (Genero) REFERENCES tbl_generos (ID_Genero) ON DELETE CASCADE
--);

