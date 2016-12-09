--Alberto Navarro Gordillo

USE Bichos

GO

/**Ejercicio 1**/
/*Crear un procedimiento almacenado que inserte una nueva fila en
BI_MascotasEnfermedades, tomando como datos de entrada el nombre de la enfermedad,
la fecha en que se diagnosticó y el código de la mascota.*/

CREATE PROCEDURE insertaMascotasEnfermedades (@enfermedad varchar(30), @fecha date, @codigoMascota char(5))
AS
	BEGIN
		DECLARE @idEnfermedad smallint

		--Comprobamos si existe dicha enfermedad en la base de datos.
		SET @idEnfermedad = (SELECT ID FROM BI_Enfermedades WHERE Nombre LIKE @enfermedad)
		
		--Si no existe, lanzamos un error.
		IF(@idEnfermedad IS NULL)
			BEGIN
			RAISERROR ('No se encuentran registros de dicha enfermedad en la base de datos.',7,15)
			END
		--Si existe, insertamos los valores.
		ELSE
			BEGIN
			INSERT INTO BI_Mascotas_Enfermedades VALUES (@idEnfermedad, @codigoMascota, @fecha, NULL)
			END

	END


GO

---CREAR USUARIO 
---		usuario: bichoAdmin
---		contraseña:pass
CREATE LOGIN bichoAdmin WITH PASSWORD='pass', DEFAULT_DATABASE=Bichos
USE Bichos
CREATE USER bichoAdmin FOR LOGIN bichoAdmin
GRANT EXECUTE, INSERT, UPDATE, DELETE, SELECT TO bichoAdmin

GO

/*
select * from BI_Mascotas_Enfermedades
SELECT * FROM BI_Actualizaciones
select * from BI_Visitas
select * from BI_Mascotas
*/