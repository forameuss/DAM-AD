USE Ejemplos  

GO

CREATE TABLE Dons (
	
	ID INT NOT NULL Identity,
	Nombre VARCHAR (20) NOT NULL,
	Apellidos VARCHAR (20) NOT NULL,
	Apodo VARCHAR (20) NOT NULL,
	Procedencia VARCHAR (30),

	CONSTRAINT PK_DON PRIMARY KEY (ID)

);

CREATE TABLE Famiglias (

	ID INT NOT NULL Identity,
	Nombre VARCHAR (20) NOT NULL,
	CiudadPrincipal VARCHAR (30) NULL,
	Miembros SmallInt NULL,
	ID_Don INT NOT NULL CONSTRAINT UQ_DON UNIQUE,	

	CONSTRAINT PK_FAGMILIAS PRIMARY KEY (ID),
	CONSTRAINT FK_FAGMILIA_DONS FOREIGN KEY (ID_Don) REFERENCES Dons(ID) ON DELETE NO ACTION ON UPDATE CASCADE,
);
GO

INSERT INTO Dons (Nombre,Apellidos,Apodo,Procedencia)
     VALUES ('Vito','Corleone','Padrino','Sicilia')
	 ,('Al','Capone','Signore','New York')
	 ,('Charlie','Luciano','Lucky','Sicilia')
	 ,('Dutch','Schultz','Holandes','Alemania')
	 ,('Enrique','Temeto','Mostachino','Utrera')

GO
USE [Ejemplos]
GO

INSERT INTO Famiglias (Nombre,CiudadPrincipal,Miembros,ID_Don)
     VALUES  ('Corleone','New York',154,1)
	 ,('Spaguetti','Chicago',310,2)
	 ,('Mancini','New York',228,3)
	 ,('Gamber','Detroit',173,4)
	 ,('Risitas','Sevilla',309,5)
GO



