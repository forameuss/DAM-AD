Create DataBase Bichos
GO
USE Bichos
GO
/****** Object:  Table BI_Clientes    Script Date: 21/11/2016 ******/
 
CREATE TABLE BI_Clientes(
    Codigo Int NOT NULL,
    Telefono char(9) NOT NULL,
    Direccion varchar(30) NOT NULL,
    NumeroCuenta char(24) NULL,
    Nombre VarChar(30)
 ,CONSTRAINT PK_Clientes PRIMARY KEY (Codigo)
 ,CONSTRAINT [CK_Telefono] CHECK  (Telefono like '[679][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
) 
 
GO
 
/****** Object:  Table BI_Enfermedades    Script Date: 21/11/2016 ******/
 
CREATE TABLE BI_Enfermedades(
    ID SmallInt Not NULL Identity (1,1)
    ,Nombre VarChar(30) NOT NULL
    ,CONSTRAINT PK_Enfermedades PRIMARY KEY (ID)
)
 
GO
/****** Object:  Table [dbo].[BI_Mascotas]    Script Date: 21/11/2016 19:08:18 ******/
 
CREATE TABLE BI_Mascotas(
    Codigo char(5) NOT NULL,
    Raza varchar(30) NOT NULL,
    Especie varchar(30) NOT NULL,
    FechaNacimiento date NOT NULL,
    FechaFallecimiento date NULL,
    Alias varchar(20) NOT NULL,
    CodigoPropietario Int Not NULL,
 CONSTRAINT PK_Mascotas PRIMARY KEY (Codigo)
 ,CONSTRAINT FK_MascotasPropietarios Foreign Key (CodigoPropietario) REFERENCES BI_Clientes (Codigo)
)
 
GO
/****** Object:  Table BI_Mascotas_Enfermedades    Script Date: 21/11/2016 ******/
 
CREATE TABLE BI_Mascotas_Enfermedades(
    IDEnfermedad SmallInt NOT NULL,
    Mascota char(5) NOT NULL,
    FechaInicio date NOT NULL,
    FechaCura date NULL,
 CONSTRAINT PK_Mascotas_Enfermedades PRIMARY KEY (IDEnfermedad, Mascota)
 ,CONSTRAINT FK_MascotasEnfermedades_Enfermedades FOREIGN KEY(IDEnfermedad) REFERENCES BI_Enfermedades (ID) ON UPDATE CASCADE
 ,CONSTRAINT FK_MascotasEnfermedades_Mascotas FOREIGN KEY(Mascota) REFERENCES BI_Mascotas (Codigo) ON UPDATE CASCADE
)
 
GO
/****** Object:  Table BI_Visitas    Script Date: 21/11/2016 ******/
 
CREATE TABLE BI_Visitas(
    IDVisita Int Not NULL Identity(1,1),
    Fecha smalldatetime NOT NULL,
    Temperatura tinyint NOT NULL,
    Peso int NOT NULL,
    Mascota char(5) NOT NULL,
 CONSTRAINT PK_Visitas PRIMARY KEY (IDVisita)
 ,CONSTRAINT FK_Visitas_Mascotas FOREIGN KEY(Mascota) REFERENCES BI_Mascotas (Codigo)
)
 
GO
/* Datos de prueba */
 
INSERT INTO BI_Clientes (Codigo,Telefono,Direccion,NumeroCuenta,Nombre)
     VALUES (101,'600000000','Desconocida',Null,'Propietario Desconocido')
     ,(102,'601782667','Avenida de América, 104','ES4908230723061224560890','Josema Ravilla')
     ,(103,'622687514','Calle Indalecio Prieto, 41','ES3248260728069924930821','Matilde Mente')
     ,(104,'621479305','Plaza de la Libertad, 14','ES8046875230612245608905','Paco Barde')
     ,(105,'654713356','Calle Sahara, 37','ES8848230523661824360834','Felipe Cador')
     ,(106,'658004983','Calle Larga, 43','ES4901230123001234567890','Penelope Tarda')
GO
INSERT INTO BI_Enfermedades (Nombre)
     VALUES ('Borreliosis '),('Campilobacteriosis'),('Rabia'),('Sarna')
     ,('Hidatidiosis'),('Toxoplasmosis'),('Micobacteriosis'),('Psitacosis')
     ,('Otitis canina'),('Artritis'),('Moquillo'),('Parvoriosis')
     ,('Leishmaniosis'),('Filariosis'),('Leucemia'),('Inmunodeficiencia')
GO
INSERT INTO BI_Mascotas (Codigo,Raza,Especie,FechaNacimiento,FechaFallecimiento,Alias,CodigoPropietario)
     VALUES ('PM001','Galgo','Perro','20100504',Null,'Cuqui',101)
     ,('PH002','Mastín','Perro','20120823',Null,'Sombra',102)
     ,('GH003','Siamés','Gato','20141015',Null,'Misi',103)
     ,('PM004','Caniche','Perro','20110106',Null,'Renato',104)
     ,('CH005','Arabe','Caballo','20090210',Null,'Comtess',105)
     ,('PH004','Setter','Perro','20130722',Null,'Luna',104)
     ,('PM005','Podenco','Perro','20121127',Null,'Loco',105)
     ,('GM006','Cartujo','Gato','20120823',Null,'Charlie',106)
 
GO
INSERT INTO BI_Mascotas_Enfermedades (IDEnfermedad,Mascota,FechaInicio,FechaCura)
     VALUES
           (1,'PH002','20140601','20140821')
           ,(3,'PH004','20141011','20150417')
           ,(11,'PM004','20120216','20120511')
           ,(13,'PM005','20161030',Null)
 
GO
 
INSERT INTO BI_Visitas (Fecha,Mascota,Temperatura,Peso)
     VALUES
           ('20140104 10:05','PM001',38,18),('20140104 11:00','PH002',38,32),('20150104 11:35','GH003',39,3),
           ('20140204 12:04','PM004',38,5),('20140314 12:11','PH004',38,21),('20140310 13:00','CH005',39,400)
           ,('20140521 10:40','PH004',38,22),('20140507 17:00','PM005',38,12),('20150724 17:20','GM006',39,2)
           ,('20150104 10:55','PM001',38,18),('20150104 18:25','PH002',38,32),('20160104 11:30','GH003',39,3)
           ,('20150204 10:00','PM004',38,5),('20150314 11:25','PH004',38,21),('20150310 12:40','CH005',39,400)
           ,('20150521 12:30','PH004',38,22),('20150507 10:30','PM005',38,12),('20160724 16:50','GM006',39,2)
GO
 
/* Tabla BI_Actualizaciones para incorporar a las otras    */
CREATE TABLE BI_Actualizaciones(
    Id int identity(1,1) ,
    Fecha smalldatetime NOT NULL,
    Temperatura tinyint NOT NULL,
    Peso int NOT NULL,
    CodigoMascota char(5) NOT NULL,
    Raza varchar(30) NULL,
    Especie varchar(30) NULL,
    FechaNacimiento date NULL,
    FechaFallecimiento date NULL,
    Alias varchar(20) NULL,
    CodigoPropietario Int NULL
    ,Enfermedad VarChar(30) NULL,
    constraint  PK_Actualziaciones Primary key (Id)
)
 
GO
 
INSERT INTO BI_Actualizaciones (Fecha,CodigoMascota,Temperatura,Peso,Raza,Especie,FechaNacimiento,FechaFallecimiento,Alias,CodigoPropietario,Enfermedad)
     VALUES ('20161121 10:05','PH004',38,22,Null,Null,Null,Null,Null,Null,'Toxoplasmosis')
     ,('20161121 10:30','PM005',38,12,Null,Null,Null,Null,Null,Null,Null)
     ,('20161121 10:45','PH002',38,33,Null,Null,Null,Null,Null,Null,Null)
     ,('20161121 11:10','PM004',38,5,Null,Null,Null,Null,Null,Null,'Otitis canina')
     ,('20161121 13:00','CH005',39,402,Null,Null,Null,Null,Null,Null,Null)
     ,('20161121 12:20','PH001',38,12,'Chucho','Perro','20151001',Null,'Rebeca',101,Null)
     ,('20161121 11:30','PM002',38,18,'Boxer','Perro','20160112',Null,'Batman',102,'Leishmaniosis')
  