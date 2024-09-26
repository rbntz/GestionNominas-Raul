CREATE DATABASE GestionNominas;

USE GestionNominas;

CREATE TABLE Empleado (
    nombre VARCHAR(20) NOT NULL,
    DNI VARCHAR(9) PRIMARY KEY,
    sexo CHAR(1) NOT NULL,
    categoria INT DEFAULT 1,
    anyos INT DEFAULT 0, 
    CONSTRAINT categoria_chk CHECK ((categoria >= 1) && (categoria <= 10)),
    CONSTRAINT anyos_chk CHECK ((anyos >= 0))
);

CREATE TABLE Nomina (
    empleado_dni VARCHAR(9),
    sueldoCalculado DECIMAL(10,2),
    FOREIGN KEY (empleado_dni) REFERENCES Empleado(DNI)
);

INSERT INTO Empleado VALUES ('James Cosling', '32000032G', 'M', 4, 7);
INSERT INTO Empleado(nombre, dni, sexo) VALUES ('Ada Lovelace', '32000031R', 'F');

UPDATE Empleado SET anyos = anyos + 1 WHERE DNI = '32000031R';
UPDATE Empleado SET categoria = 9 WHERE DNI = '32000032G';


