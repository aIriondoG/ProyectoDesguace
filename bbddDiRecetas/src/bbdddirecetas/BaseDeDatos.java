/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdddirecetas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Raul y Adrian
 * @version 1.0.2
 */
public class BaseDeDatos {

    /**
     *
     * @param args argumento pasado por linea de comandos o desde propiedades
     * @throws SQLException excepcion en SQL
     * @throws IOException excepcionIO
     */
    public static void main(String[] args) throws SQLException, IOException {
        try {
            //CREACION DE LA CONEXION , PARA PODER CONECTARSE A LA BASE DE DATOS
            Connection conexion = Conexion.conexionMySQL();
            Statement st = conexion.createStatement();
            //Creacion de la base de datos
            st.executeUpdate("DROP DATABASE IF EXISTS Desguaces");
            st.executeUpdate("CREATE DATABASE Desguaces");
            st.executeUpdate("USE Desguaces");
            //Creacion de las tablas
            String marca = "CREATE TABLE MARCA(\n"
                    + "P_Marca INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "Nombre VARCHAR (50),\n"
                    + "Pais VARCHAR(50)\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(marca);
            System.out.println("Marca Creada");
            String modelo = "CREATE TABLE MODELO(\n"
                    + "P_Modelo INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "Nombre VARCHAR(50),\n"
                    + "A_Marca INT(8),\n"
                    + "AnoSalida VARCHAR(4),\n"
                    + "AnoExtincion VARCHAR(12),\n"
                    + "FOREIGN KEY (A_Marca) \n"
                    + "REFERENCES MARCA (P_Marca) \n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(modelo);
            System.out.println("Modelo creado");
            String tipo = "CREATE TABLE TIPO(\n"
                    + "P_Tipo INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "Nombre VARCHAR (50)"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(tipo);
            System.out.println("Tipo creado");
            String pieza = "CREATE TABLE PIEZA(\n"
                    + "P_Pieza INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "Nombre VARCHAR (50),\n"
                    + "A_Tipo INT(8),"
                    + "FOREIGN KEY(A_Tipo)\n"
                    + "REFERENCES TIPO (P_Tipo)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(pieza);
            System.out.println("Pieza creada");
            String desguace = "CREATE TABLE DESGUACE(\n"
                    + "P_Desguace INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "Nombre VARCHAR (50),\n"
                    + "Direccion VARCHAR(50),\n"
                    + "Contrasenia VARCHAR(50),\n"
                    + "Telefono VARCHAR(50),\n"
                    + "CIF VARCHAR(15)\n"
                    + ")ENGINE=InnoDB";
        
            st.executeUpdate(desguace);
            System.out.println("Desguace creado");
            String usuarios = "CREATE TABLE USUARIO(\n"
                    + "P_Usuario INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "A_Desguace INT(8),\n"
                    + "Nombre VARCHAR(50),\n"
                    + "Usuario VARCHAR(50) UNIQUE,\n"
                    + "Contrasena VARCHAR(50),\n"
                    + "Direccion VARCHAR(50),\n"
                    + "Telefono VARCHAR(9),\n"
                    + "CorreoElectronico VARCHAR(50),\n"
                    + "DNI VARCHAR(9),\n"
                    + "Apellidos VARCHAR(50)\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(usuarios);
            System.out.println("Usuarios creados");
            String cuenta = "CREATE TABLE CuentaBancaria(\n"
                    + "P_CB INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "A_Usuario INT(8),\n"
                    + "IBAN VARCHAR(34),\n"
                    + "FOREIGN KEY(A_Usuario)\n"
                    + "REFERENCES USUARIO (P_Usuario)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE\n"
                    + ")Engine=InnoDB";
            st.executeUpdate(cuenta);
            System.out.println("Ya tenemos cuenta bancaria");
            String motor = "CREATE TABLE MOTOR("
                    + "P_Motor INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "Codigo VARCHAR(20),\n"
                    + "Combustible VARCHAR(10)\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(motor);
            System.out.println("motor creado");
            String ModeloMotor = "CREATE TABLE ModeloMotor("
                    + "P_MM INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "A_Modelo INT(8),\n"
                    + "A_Motor INT(8),\n"
                    + "FOREIGN KEY(A_Modelo)\n"
                    + "REFERENCES MODELO (P_Modelo)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE,\n"
                    + "FOREIGN KEY(A_Motor)\n"
                    + "REFERENCES MOTOR (P_Motor)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(ModeloMotor);
            System.out.println("MM creado");
            String recambio = "CREATE TABLE RECAMBIO(\n"
                    + "P_Recambio INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "A_Motor INT(8),\n"
                    + "A_Pieza INT(8),\n"
                    + "A_Desguace INT(8),\n"
                    + "CantidadDisponible INT(2),\n"
                    + "Precio double(5,2),\n"
                    + "FOREIGN KEY(A_Motor)\n"
                    + "REFERENCES MOTOR (P_Motor)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE,\n"
                    + "FOREIGN KEY(A_Pieza)\n"
                    + "REFERENCES PIEZA (P_Pieza)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE,\n"
                    + "FOREIGN KEY(A_Desguace)\n"
                    + "REFERENCES DESGUACE (P_Desguace)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(recambio);
            System.out.println("Recambio creado");
            String carrito = "CREATE TABLE CARRITO("
                    + "P_Carrito INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "A_Usuario INT(8),\n"
                    + "FOREIGN KEY(A_Usuario)\n"
                    + "REFERENCES Usuario (P_Usuario)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(carrito);
            System.out.println("Carrito creado");
            String carre = "CREATE TABLE CAR_RE("
                    + "P_Carre INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "A_Carrito INT(8),\n"
                    + "A_Recambio INT(8),\n"
                    + "Cantidad INT(2),\n"
                    + "FOREIGN KEY(A_Recambio)\n"
                    + "REFERENCES RECAMBIO (P_Recambio)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE,\n"
                    + "FOREIGN KEY(A_Carrito)\n"
                    + "REFERENCES CARRITO (P_CARRITO)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(carre);
            System.out.println("Carre creado");
            String factura = "CREATE TABLE FACTURA("
                    + "P_Factura INT(8) PRIMARY KEY AUTO_INCREMENT,\n"
                    + "Fecha VARCHAR(10),\n"
                    + "A_Carrito INT(8),"
                    + "A_Desguace INT(8),\n"
                    + "FOREIGN KEY(A_Carrito)\n"
                    + "REFERENCES CARRITO (P_Carrito)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE,\n"
                    + "FOREIGN KEY(A_Desguace)\n"
                    + "REFERENCES DESGUACE (P_DESGUACE)\n"
                    + "ON DELETE CASCADE \n"
                    + "ON UPDATE CASCADE\n"
                    + ")ENGINE=InnoDB";
            st.executeUpdate(factura);
            System.out.println("Factura creado");
            /*SELECT
     car_re.`Cantidad` AS car_re_Cantidad,
     factura.`Fecha` AS factura_Fecha,
     usuario.`Nombre` AS usuario_Nombre,
     usuario.`Direccion` AS usuario_Direccion,
     usuario.`CorreoElectronico` AS usuario_CorreoElectronico,
     usuario.`Apellidos` AS usuario_Apellidos,
     recambio.`Precio` AS recambio_Precio,
     pieza.`Nombre` AS pieza_Nombre,
     modelo.`Nombre` AS modelo_Nombre,
     marca.`Nombre` AS marca_Nombre
FROM
     `carrito` carrito INNER JOIN `car_re` car_re ON carrito.`P_Carrito` = car_re.`A_Carrito`
     INNER JOIN `factura` factura ON car_re.`P_Carre` = factura.`A_Carre`
     INNER JOIN `recambio` recambio ON car_re.`A_Recambio` = recambio.`P_Recambio`
     INNER JOIN `pieza` pieza ON recambio.`A_Pieza` = pieza.`P_Pieza`
     INNER JOIN `motor` motor ON recambio.`A_Motor` = motor.`P_Motor`
     INNER JOIN `modelomotor` modelomotor ON motor.`P_Motor` = modelomotor.`A_Motor`
     INNER JOIN `modelo` modelo ON modelomotor.`A_Modelo` = modelo.`P_Modelo`
     INNER JOIN `marca` marca ON modelo.`A_Marca` = marca.`P_Marca`
     INNER JOIN `usuario` usuario ON carrito.`A_Usuario` = usuario.`P_Usuario`*/

 /*Introduccion de datos*/
            //Marcas
            Introducir("MARCA", "1,'Audi','Alemania'");
            Introducir("MARCA", "2,'Mercedes','Alemania'");
            Introducir("MARCA", "3,'BMW','Alemania'");
            Introducir("MARCA", "4,'Citroen','Francia'");
            Introducir("MARCA", "5,'Fiat','Italia'");
            Introducir("MARCA", "6,'Ford','Estados Unidos'");
            Introducir("MARCA", "7,'Nissan','Japon'");
            Introducir("MARCA", "8,'Opel','Alemania'");
            Introducir("MARCA", "9,'Peugeot','Francia'");
            Introducir("MARCA", "10,'Renault','Francia'");
            Introducir("MARCA", "11,'Seat','España'");
            Introducir("MARCA", "12,'Volkswagen','Alemania'");
            Introducir("MARCA", "13,'Honda','Japon'");
            /*Modelos*/
            //Audi
            Introducir("MODELO", "null,'A1',1,'2010','Actualmente'");
            Introducir("MODELO", "null,'A2',1,'2000','2005'");
            Introducir("MODELO", "null,'A3',1,'1996','2003'");
            Introducir("MODELO", "null,'A3',1,'2003','2012'");
            Introducir("MODELO", "null,'A3',1,'2012','Actualmente'");
            Introducir("MODELO", "null,'A4',1,'1994','2000'");
            Introducir("MODELO", "null,'A4',1,'2000','2004'");
            Introducir("MODELO", "null,'A4',1,'2000','2004'");
            Introducir("MODELO", "null,'A4',1,'2004','2008'");
            Introducir("MODELO", "null,'A4',1,'2008','2015'");
            Introducir("MODELO", "null,'A4',1,'2015','Actualmente'");
            Introducir("MODELO", "null,'A5',1,'2007','Actualmente'");
            Introducir("MODELO", "null,'A6',1,'1994','1997'");
            Introducir("MODELO", "null,'A6',1,'1997','2005'");
            Introducir("MODELO", "null,'A6',1,'2005','2011'");
            Introducir("MODELO", "null,'A6',1,'2011','Actualmente'");
            Introducir("MODELO", "null,'A7',1,'2010','Actualmente'");
            //Mercedes
            Introducir("MODELO", "null,'190E',2,1982,1993");
            Introducir("MODELO", "null,'Clase A',2,'1997','2004'");
            Introducir("MODELO", "null,'Clase A',2,'2004','2012'");
            Introducir("MODELO", "null,'Clase A',2,'2012','Actualmente'");
            Introducir("MODELO", "null,'Clase B',2,'2005','2011'");
            Introducir("MODELO", "null,'Clase B',2,'2011','Actualmente'");
            Introducir("MODELO", "null,'Clase C',2,'1993','2000'");
            Introducir("MODELO", "null,'Clase C',2,'2000','2007'");
            Introducir("MODELO", "null,'Clase C',2,'2007','2014'");
            Introducir("MODELO", "null,'Clase C',2,'2014','Actualmente'");
            Introducir("MODELO", "null,'Clase E',2,'1993','1995'");
            Introducir("MODELO", "null,'Clase E',2,'1995','2003'");
            Introducir("MODELO", "null,'Clase E',2,'2002','2009'");
            Introducir("MODELO", "null,'Clase E',2,'2009','Actualmete'");
            Introducir("MODELO", "null,'Clase G',2,'1979','1993'");
            Introducir("MODELO", "null,'Clase G',2,'1993','Actualmente'");
            Introducir("MODELO", "null,'Clase M',2,'1998','2005'");
            Introducir("MODELO", "null,'Clase M',2,'2005','2011'");
            Introducir("MODELO", "null,'Clase M',2,'2011','Actualmente'");
            Introducir("MODELO", "null,'Clase E',2,'1996','2003'");
            Introducir("MODELO", "null,'Clase E',2,'2003','Actualmente'");
            //BMW
            Introducir("MODELO", "null,'Serie 1',3,'2004','2012'");
            Introducir("MODELO", "null,'Serie 1',3,'2010','Actualmente'");
            Introducir("MODELO", "null,'Serie 2(2002)',3,'1966','1977'");
            Introducir("MODELO", "null,'Serie 2 Coupe',3,'2012','Actualmente'");
            Introducir("MODELO", "null,'Serie 3(E21)',3,'1975','1984'");
            Introducir("MODELO", "null,'Serie 3(E30)',3,'1982','1992'");
            Introducir("MODELO", "null,'Serie 3(E36)',3,'1990','1998'");
            Introducir("MODELO", "null,'Serie 3(E46)',3,'1998','2005'");
            Introducir("MODELO", "null,'Serie 3(E90/E92)',3,'2005','2011'");
            Introducir("MODELO", "null,'Serie 3(F30/F80)',3,'2011','Actualmente'");
            Introducir("MODELO", "null,'Serie 4',3,'2012','Actualmente'");
            Introducir("MODELO", "null,'Serie 5(E12)',3,'1972','1981'");
            Introducir("MODELO", "null,'Serie 5(E28)',3,'1980','1987'");
            Introducir("MODELO", "null,'Serie 5(E34)',3,'1987','1995'");
            Introducir("MODELO", "null,'Serie 5(E39)',3,'1995','2003'");
            Introducir("MODELO", "null,'Serie 5(E60)',3,'2003','2010'");
            Introducir("MODELO", "null,'Serie 5(F10)',3,'2009','Actualmente'");
            Introducir("MODELO", "null,'Serie 6(E24)',3,'1976','1990'");
            Introducir("MODELO", "null,'Serie 6(E63)',3,'2004','2010'");
            Introducir("MODELO", "null,'Serie 6(F13)',3,'2010','Actualmente'");
            //Citroen
            Introducir("MODELO", "null,'C1',4,'2004','2014'");
            Introducir("MODELO", "null,'C1 II',4,'2014','Actualmente'");
            Introducir("MODELO", "null,'C2',4,'2003','2009'");
            Introducir("MODELO", "null,'C3',4,'2002','Actualmente'");
            Introducir("MODELO", "null,'C3 II',4,'2009','Actualmente'");
            Introducir("MODELO", "null,'C3 III',4,'2017','Actualmente'");
            Introducir("MODELO", "null,'C4',4,'2004','2011'");
            Introducir("MODELO", "null,'C4 II',4,'2009','Hoy'");
            Introducir("MODELO", "null,'C5',4,'2001','2004'");
            Introducir("MODELO", "null,'C5 II',4,'2004','Actualmente'");
            Introducir("MODELO", "null,'C5 III',4,'2008','Actualmente'");
            Introducir("MODELO", "null,'C6',4,'2005','Actualmente'");
            Introducir("MODELO", "null,'C8',4,'2002','Actualmente'");
            Introducir("MODELO", "null,'DS3',4,'2009','2015'");
            Introducir("MODELO", "null,'DS4',4,'2011','2015'");
            Introducir("MODELO", "null,'DS5',4,'2011','2015'");
            //Fiat
            Introducir("MODELO", "null,'Punto',5,'1993','1999'");
            Introducir("MODELO", "null,'Punto',5,'1999','2012'");
            Introducir("MODELO", "null,'Punto',5,'2012','Actualmente'");
            Introducir("MODELO", "null,'500',5,'2007','Actualmente'");
            Introducir("MODELO", "null,'Panda',5,'1980','2004'");
            Introducir("MODELO", "null,'Panda',5,'2003','Actualmente'");
            Introducir("MODELO", "null,'Panda',5,'2012','Actualmente'");
            //Ford
            Introducir("MODELO", "null,'Focus',6,'1998','2007'");
            Introducir("MODELO", "null,'Focus',6,'2007','2012'");
            Introducir("MODELO", "null,'Focus',6,'2010','Actualmente'");

            Introducir("MODELO", "null,'Fiesta',6,'1976','1983'");
            Introducir("MODELO", "null,'Fiesta',6,'1983','1989'");
            Introducir("MODELO", "null,'Fiesta',6,'1989','1997'");
            Introducir("MODELO", "null,'Fiesta',6,'1995','2002'");
            Introducir("MODELO", "null,'Fiesta',6,'2002','Actualmente'");
            Introducir("MODELO", "null,'Fiesta',6,'2008','Actualmente'");

            Introducir("MODELO", "null,'Escort',6,'1968','1976'");
            Introducir("MODELO", "null,'Escort',6,'1973','1981'");
            Introducir("MODELO", "null,'Escort',6,'1980','1986'");
            Introducir("MODELO", "null,'Escort',6,'1985','1990'");
            Introducir("MODELO", "null,'Escort',6,'1990','1992'");
            Introducir("MODELO", "null,'Escort',6,'1992','1996'");
            Introducir("MODELO", "null,'Escort',6,'1995','2002'");

            Introducir("MODELO", "null,'Sierra',6,'1987','1993'");

            Introducir("MODELO", "null,'Mondeo',6,'1993','1996'");
            Introducir("MODELO", "null,'Mondeo',6,'1996','2000'");
            Introducir("MODELO", "null,'Mondeo',6,'2000','2007'");
            Introducir("MODELO", "null,'Mondeo',6,'2007','Actualmente'");
            //Nissan
            Introducir("MODELO", "null,'Datsun 240 ',7,'1978','1981'");
            Introducir("MODELO", "null,'280Z',7,'1978','1984'");
            Introducir("MODELO", "null,'350Z',7,'2002','Actualmente'");
            Introducir("MODELO", "null,'370Z',7,'2009','Actualmente'");
            Introducir("MODELO", "null,'GT-R',7,'2007','Actualmente'");
            Introducir("MODELO", "null,'Almera',7,'1995','2000'");
            Introducir("MODELO", "null,'Mondeo',7,'2000','Actualmente'");

            Introducir("MODELO", "null,'Patrol',7,'1979','1989'");
            Introducir("MODELO", "null,'Patrol',7,'1994','1998'");
            Introducir("MODELO", "null,'Patrol',7,'1998','Actualmente'");

            Introducir("MODELO", "null,'Primera',7,'1990','1996'");
            Introducir("MODELO", "null,'Primera',7,'1996','2002'");
            Introducir("MODELO", "null,'Primera',7,'2002','Actualmente'");

            Introducir("MODELO", "null,'Skyline R32',7,'1989','1994'");
            Introducir("MODELO", "null,'Skyline R33',7,'1993','1998'");
            Introducir("MODELO", "null,'Skyline R34',7,'1998','2002'");

            Introducir("MODELO", "null,'Silvia S12',7,'1984','1988'");
            Introducir("MODELO", "null,'Silvia S13/240sx/200sx',7,'1988','1993'");
            Introducir("MODELO", "null,'Silvia S14',7,'1993','1999'");
            Introducir("MODELO", "null,'Silvia S15',7,'1999','1992'");
            //Opel
            Introducir("MODELO", "null,'Astra F',8,'1991','1998'");
            Introducir("MODELO", "null,'Astra G',8,'1998','2009'");
            Introducir("MODELO", "null,'Astra H',8,'2004','Actualmente'");

            Introducir("MODELO", "null,'Corsa',8,'1982','1993'");
            Introducir("MODELO", "null,'Corsa',8,'1993','2002'");
            Introducir("MODELO", "null,'Corsa',8,'2000','2009'");
            Introducir("MODELO", "null,'Corsa',8,'2006','Actualmente'");
            Introducir("MODELO", "null,'Corsa',8,'2014','Actualmente'");
            //Peugeot
            Introducir("MODELO", "null,'106',9,'1991','1996'");
            Introducir("MODELO", "null,'106',9,'1988','2003'");

            Introducir("MODELO", "null,'205',9,'1983','1987'");

            Introducir("MODELO", "null,'206',9,'1998','2013'");
            Introducir("MODELO", "null,'206+',9,'2009','2013'");

            Introducir("MODELO", "null,'208',9,'2012','Actualmente'");
            Introducir("MODELO", "null,'305',9,'1982','1990'");
            Introducir("MODELO", "null,'405',9,'1987','1993'");
            Introducir("MODELO", "null,'405',9,'1992','1999'");

            Introducir("MODELO", "null,'306',9,'1993','2001'");
            Introducir("MODELO", "null,'307',9,'2002','Actualmente'");
            Introducir("MODELO", "null,'308',9,'2007','Actualmente'");
            Introducir("MODELO", "null,'308',9,'2013','Actualmente'");
            //Renault
            Introducir("MODELO", "null,'Clio I',10,'1990','1998'");
            Introducir("MODELO", "null,'Clio II',10,'1998','2005'");
            Introducir("MODELO", "null,'Clio II',10,'2005','2012'");
            Introducir("MODELO", "null,'Clio IV',10,'2012','Actualmente'");

            Introducir("MODELO", "null,'Megane I',10,'1995','2004'");
            Introducir("MODELO", "null,'Megane II',10,'2002','2011'");
            Introducir("MODELO", "null,'Megane III',10,'2009','2013'");
            Introducir("MODELO", "null,'Megane IV',10,'2015','Actualmente'");

            Introducir("MODELO", "null,'Laguna',10,'1993','2002'");
            Introducir("MODELO", "null,'Laguna',10,'2001','Actualmente'");
            Introducir("MODELO", "null,'Laguna',10,'2007','Actualmente'");

            Introducir("MODELO", "null,'Modus',10,'2004','Actualmente'");

            Introducir("MODELO", "null,'Megane Scenic',10,'1996','2001'");
            Introducir("MODELO", "null,'Scenic I',10,'1999','2003'");
            Introducir("MODELO", "null,'Scenic II',10,'2003','Actualmente'");
            Introducir("MODELO", "null,'Scenic III',10,'2009','Actualmente'");
            //Seat
            Introducir("MODELO", "null,'Cordoba',11,'1993','1999'");
            Introducir("MODELO", "null,'Cordoba',11,'1999','2002'");
            Introducir("MODELO", "null,'Cordoba',11,'2002','2009'");

            Introducir("MODELO", "null,'Ibiza',11,'1984','1993'");
            Introducir("MODELO", "null,'Ibiza',11,'1993','1999'");
            Introducir("MODELO", "null,'Ibiza',11,'1999','2002'");
            Introducir("MODELO", "null,'Ibiza',11,'2002','2009'");
            Introducir("MODELO", "null,'Ibiza',11,'2008','Actualmente'");

            Introducir("MODELO", "null,'Leon',11,'1999','2006'");
            Introducir("MODELO", "null,'Leon',11,'2005','2012'");
            Introducir("MODELO", "null,'Leon',11,'2012','Actualmente'");

            Introducir("MODELO", "null,'Marbella',11,'1986','1998'");
            //Volkswagen
            Introducir("MODELO", "null,'Golf MKI',12,'1974','1984'");
            Introducir("MODELO", "null,'Golf MKII',12,'1983','1992'");
            Introducir("MODELO", "null,'Golf MKIII',12,'1991','1997'");
            Introducir("MODELO", "null,'Golf MKIV',12,'1997','2003'");
            Introducir("MODELO", "null,'Golf MKV',12,'2003','2008'");
            Introducir("MODELO", "null,'Golf MKVI',12,'2008','2012'");
            Introducir("MODELO", "null,'Golf MKVII',12,'2012','Actualmente'");

            Introducir("MODELO", "null,'Corrado',12,'1987','1995'");

            Introducir("MODELO", "null,'Scirocco',12,'1974','1980'");
            Introducir("MODELO", "null,'Scirocco',12,'1980','1992'");
            Introducir("MODELO", "null,'Scirocco',12,'2008','Actualmente'");

            Introducir("MODELO", "null,'Passat B3',12,'1988','1993'");
            Introducir("MODELO", "null,'Passat B4',12,'1993','1997'");
            Introducir("MODELO", "null,'Passat B5',12,'1997','2005'");
            Introducir("MODELO", "null,'Passat B6',12,'2005','2010'");
            Introducir("MODELO", "null,'Passat B7',12,'2010','2014'");
            Introducir("MODELO", "null,'Passat B8',12,'2014','Actualmente'");

            Introducir("MODELO", "null,'Polo I',12,'1975','1981'");
            Introducir("MODELO", "null,'Polo II',12,'1981','1994'");
            Introducir("MODELO", "null,'Polo III',12,'1994','2002'");
            Introducir("MODELO", "null,'Polo IV',12,'2002','2010'");
            Introducir("MODELO", "null,'Polo V',12,'2009','Actualmente'");
            Introducir("MODELO", "null,'Polo VI',12,'2017','Actualmente'");
            //Honda
            Introducir("MODELO", "null,'Accord',13,'1976','1981'");
            Introducir("MODELO", "null,'Accord',13,'1982','1985'");
            Introducir("MODELO", "null,'Accord',13,'1986','1989'");
            Introducir("MODELO", "null,'Accord',13,'1990','1993'");
            Introducir("MODELO", "null,'Accord',13,'1994','1997'");
            Introducir("MODELO", "null,'Accord',13,'1998','2002'");
            Introducir("MODELO", "null,'Accord',13,'2003','2007'");
            Introducir("MODELO", "null,'Accord',13,'2013','Actualmente'");

            Introducir("MODELO", "null,'Civic',13,'1973','1979'");
            Introducir("MODELO", "null,'Civic',13,'1980','1983'");
            Introducir("MODELO", "null,'Civic',13,'1984','1987'");
            Introducir("MODELO", "null,'Civic',13,'1988','1991'");
            Introducir("MODELO", "null,'Civic',13,'1992','1995'");
            Introducir("MODELO", "null,'Civic',13,'1996','2000'");
            Introducir("MODELO", "null,'Civic',13,'2000','2005'");
            Introducir("MODELO", "null,'Civic',13,'2006','2011'");
            Introducir("MODELO", "null,'Civic',13,'2012','2015'");
            Introducir("MODELO", "null,'Civic',13,'2016','Actualmente'");

            Introducir("MODELO", "null,'S2000',13,'1999','Actualmente'");
            /*Tipo*/
            Introducir("TIPO", "null,'Frenado'");
            Introducir("TIPO", "null,'Filtracion'");
            Introducir("TIPO", "null,'Suspension-Direccion'");
            Introducir("TIPO", "null,'Embrague-Caja-Trasmision'");
            Introducir("TIPO", "null,'Compartimento Motor'");
            Introducir("TIPO", "null,'Equipamentos Exteriores'");
            Introducir("TIPO", "null,'Escape'");
            Introducir("TIPO", "null,'Electricidad'");
            Introducir("TIPO", "null,'Refrigeracion Motor'");
            Introducir("TIPO", "null,'Calefaccion-Climatizacion-Aire Acondicionado'");
            Introducir("TIPO", "null,'Cerraduras-Cierres'");
            /*Pieza*/
            Introducir("PIEZA", "null,'Pinza de Freno',1");
            Introducir("PIEZA", "null,'Frenos de Disco',1");
            Introducir("PIEZA", "null,'Frenos de Tambor(Kit)',1");
            Introducir("PIEZA", "null,'Tubos/Latiguillos',1");
            Introducir("PIEZA", "null,'Anillo sensor ABS',1");
            Introducir("PIEZA", "null,'Liquido de Frenos',1");
            Introducir("PIEZA", "null,'Cable de accionamiento(Freno de estacionamiento)',1");
            Introducir("PIEZA", "null,'Regulador de fuerza de Frenado',1");
            Introducir("PIEZA", "null,'Servofreno',1");
            Introducir("PIEZA", "null,'Sensor ABS',1");
            Introducir("PIEZA", "null,'Interruptor Luces de ferno',1");
            Introducir("PIEZA", "null,'Zapatas de Freno',1");

            Introducir("PIEZA", "null,'Filtro de aceite',2");
            Introducir("PIEZA", "null,'Filtro de aire',2");
            Introducir("PIEZA", "null,'Filtro de combustible',2");
            Introducir("PIEZA", "null,'Filtro de aire del habitaculo',2");
            Introducir("PIEZA", "null,'Filtro de aceite',2");
            Introducir("PIEZA", "null,'Tapon de cambio de aceite',2");
            Introducir("PIEZA", "null,'Tapón roscado de vaciado de aceite',2");

            Introducir("PIEZA", "null,'Amortiguadores Delanteros',3");
            Introducir("PIEZA", "null,'Amortiguadores Traseros',3");
            Introducir("PIEZA", "null,'Muelle de chasis',3");
            Introducir("PIEZA", "null,'Cojinete columna suspension',3");
            Introducir("PIEZA", "null,'Kit de proteccion guardapolvos',3");
            Introducir("PIEZA", "null,'Rodamiento soporte amortiguador',3");
            Introducir("PIEZA", "null,'Cojinete de Rueda',3");
            Introducir("PIEZA", "null,'Cubo de Rueda',3");
            Introducir("PIEZA", "null,'Anillo Reten, Ciegüeñal',3");
            Introducir("PIEZA", "null,'Perno de Rueda',3");
            Introducir("PIEZA", "null,'Llanta de acero',3");
            Introducir("PIEZA", "null,'Rotula barra de acoplamiento',3");
            Introducir("PIEZA", "null,'Barra de acoplamiento',3");
            Introducir("PIEZA", "null,'Articulacion axial(Barra de acoplamiento)',3");
            Introducir("PIEZA", "null,'Bomba Hidarulica(Direccion)',3");
            Introducir("PIEZA", "null,'Engranaje de direccion',3");
            Introducir("PIEZA", "null,'Fuelle de direccion',3");
            Introducir("PIEZA", "null,'Juego de fuelles de direccion',3");
            Introducir("PIEZA", "null,'Fuelle de direccion(con accesorios)',3");
            Introducir("PIEZA", "null,'Barra Oscilante',3");
            Introducir("PIEZA", "null,'Rotula de suspension',3");
            Introducir("PIEZA", "null,'Travesaños/barras-estabilizador',3");
            Introducir("PIEZA", "null,'Suspension, Brazo Oscilante',3");
            Introducir("PIEZA", "null,'Casquillo del cojinete',3");
            Introducir("PIEZA", "null,'Suspension, cuerpo del eje',3");

            Introducir("PIEZA", "null,'Kit de embrague',4");
            Introducir("PIEZA", "null,'Cojinete de desembrague',4");
            Introducir("PIEZA", "null,'Cable de accionamiento del embrague',4");
            Introducir("PIEZA", "null,'Casquillo guia, embrague',4");
            Introducir("PIEZA", "null,'Arbol de trasmision',4");
            Introducir("PIEZA", "null,'Fuelle del arbol de transmision',4");
            Introducir("PIEZA", "null,'Suspension, arbol de transmision',4");
            Introducir("PIEZA", "null,'Fuelle de cardan',4");
            Introducir("PIEZA", "null,'Juego de articulacion(arbol)',4");
            Introducir("PIEZA", "null,'Anillo reten, brida caja',4");
            Introducir("PIEZA", "null,'Juego reapacion palanca de cambios',4");
            Introducir("PIEZA", "null,'Suspension caja de cambios',4");
            Introducir("PIEZA", "null,'Sensor revolcuion de caja automatica',4");

            Introducir("PIEZA", "null,'Bujia de encendido',5");
            Introducir("PIEZA", "null,'Bobina de encendido(Pipa)',5");
            Introducir("PIEZA", "null,'Correa dentada(distribucion)',5");
            Introducir("PIEZA", "null,'Correa del alternador',5");
            Introducir("PIEZA", "null,'Kit distribucion',5");
            Introducir("PIEZA", "null,'Kit distribucion + Bomba',5");
            Introducir("PIEZA", "null,'Polea del cigüeñal',5");
            Introducir("PIEZA", "null,'Bomba de combustible',5");
            Introducir("PIEZA", "null,'Suministro de aire',5");
            Introducir("PIEZA", "null,'Regulador de la presion del combustible',5");
            Introducir("PIEZA", "null,'Inyector',5");

            Introducir("PIEZA", "null,'Escobillla del Limpiaparabrisas',6");
            Introducir("PIEZA", "null,'Bomba de agua de lavado',6");
            Introducir("PIEZA", "null,'Motor del limpiaparabrisas',6");
            Introducir("PIEZA", "null,'Faro Principal',6");
            Introducir("PIEZA", "null,'Faro antiniebla',6");
            Introducir("PIEZA", "null,'Piloto posterior',6");
            Introducir("PIEZA", "null,'Piloto intermitente',6");
            Introducir("PIEZA", "null,'Elemento regulacion de faros',6");
            Introducir("PIEZA", "null,'Faro freno',6");
            Introducir("PIEZA", "null,'Enganche remolque',6");
            Introducir("PIEZA", "null,'Retrovisor interior',6");
            Introducir("PIEZA", "null,'Cristla retrovisor exterior',6");
            Introducir("PIEZA", "null,'Cubierta retrovisor exterior',6");

            Introducir("PIEZA", "null,'Soporte del escape',7");
            Introducir("PIEZA", "null,'Silencioso posterior',7");
            Introducir("PIEZA", "null,'Silencioso del medio',7");
            Introducir("PIEZA", "null,'Cinta de foma de escape',7");
            Introducir("PIEZA", "null,'Catalizador',7");
            Introducir("PIEZA", "null,'Sonda Lmabda',7");
            Introducir("PIEZA", "null,'Colector de escape',7");

            Introducir("PIEZA", "null,'Alternador',8");
            Introducir("PIEZA", "null,'Motor de arranque(Puesta en marcha)',8");
            Introducir("PIEZA", "null,'Polea del alternador',8");
            Introducir("PIEZA", "null,'Reagulador del alternador',8");
            Introducir("PIEZA", "null,'Bocina',8");
            Introducir("PIEZA", "null,'Tornillo corrector de inclinacion',8");
            Introducir("PIEZA", "null,'Bateria de arranque',8");
            Introducir("PIEZA", "null,'Cables de arranque',8");
            Introducir("PIEZA", "null,'Interruptor de marcha atras',8");
            Introducir("PIEZA", "null,'Interruptor de luz principal',8");
            Introducir("PIEZA", "null,'Conmutador en la columna de direccion',8");

            Introducir("PIEZA", "null,'Bomba de agua',9");
            Introducir("PIEZA", "null,'Tapon de refirgerante',9");
            Introducir("PIEZA", "null,'Deposito de compensacion refrigerante',9");
            Introducir("PIEZA", "null,'Radiador de refrigeracion',9");
            Introducir("PIEZA", "null,'Tubería de radiador',9");
            Introducir("PIEZA", "null,'Ventilador de refrigeracion',9");
            Introducir("PIEZA", "null,'Motor electrico, ventilador del radiador',9");
            Introducir("PIEZA", "null,'Interruptor de temperatura',9");
            Introducir("PIEZA", "null,'Termostato',9");
            Introducir("PIEZA", "null,'Sensor temperatura refrigerante',9");
            Introducir("PIEZA", "null,'Junta termostato',9");

            Introducir("PIEZA", "null,'Radiador de calefaccion',10");
            Introducir("PIEZA", "null,'Ventialdor habitaculo',10");
            Introducir("PIEZA", "null,'Compresor Aire acondicionado',10");
            Introducir("PIEZA", "null,'Condensador aire acondicionado',10");
            Introducir("PIEZA", "null,'Valvula de expasion aire acondicionado',10");
            Introducir("PIEZA", "null,'Filtro deshidratante de aire acondicionado',10");
            Introducir("PIEZA", "null,'Evaporador aire acondicionado',10");
            Introducir("PIEZA", "null,'Presostato aire acondicionado',10");
            Introducir("PIEZA", "null,'Valvula mezcladora',10");
            Introducir("PIEZA", "null,'Conducto refrigerante',10");
            Introducir("PIEZA", "null,'Elemento control aire acondicionado',10");

            Introducir("PIEZA", "null,'Tapa deposito de combustible',11");
            Introducir("PIEZA", "null,'Carcasa de llave',11");
            Introducir("PIEZA", "null,'Pila lithium',11");
            Introducir("PIEZA", "null,'Juegpo de cilindros de cierre',11");
            Introducir("PIEZA", "null,'Cerradura de puerta',11");
            Introducir("PIEZA", "null,'Cerradura de la puerta de maletero',11");
            Introducir("PIEZA", "null,'Cilindro de cierre, cerradura de encendido',11");
            Introducir("PIEZA", "null,'Elevalunas delantero',11");
            Introducir("PIEZA", "null,'Manecilla de apertura',11");
            Introducir("PIEZA", "null,'Muelle nuematico del maletero',11");
            //Esto de a continuacion ser ira creando con el uso , solo son ejemplos actualmente
            /*Desguaces*/
            Introducir("DESGUACE", "null,'','','','',''");
            Introducir("DESGUACE", "null,'Desguace Becerril','Calle Inventada 7','1','942558789','123456789C'");
            /*Usuarios*/
            Introducir("USUARIO", "null,null, 'Adrian' , 'adr1997' , '1234','San camilo 8','666123456','adrian@gmail.com','74859613A','Iriondo Gonzalez' ");
            Introducir("USUARIO", "null,2, 'Rigoberto' , 'r' , '1','SanTiburcio 16','942568794','rigoberto@gmail.com','45698712K' ,'Di Sousa'");
            /*Cuenta bancaria*/
            Introducir("CuentaBancaria", "null,1,'ES12 1234 1324 1234 2015'");
            Introducir("CuentaBancaria", "null,2,'ES15 1234 1324 1234 2016'");
            Introducir("Motor", "null , '2JC','Gasolina'");
            Introducir("ModeloMotor", "null , 1, 1");
            Introducir("Carrito", "null, 1");
            Introducir("Recambio", "null, 1,1,2,19,50");
            Introducir("Recambio", "null, 1,14,2,25,15");
            Introducir("Car_re", "null , 1 ,1 ,5");
            Introducir("Car_re", "null , 1 ,2 ,1");
            Introducir("Factura", "null ,'20/05/2018',1,2");
            /*Recambio*/
            //("RECAMBIO", "null, 148,108,1,17");
            /*Carrito*/
            //Introducir("CARRITO", "null, 1,1,1");

            st.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Introducir(String tabla, String sentencias) throws SQLException {
        //METODO POR EL CUAL SE INTRODUCEN LOS DATOS DENTRO DE UNA BASE DE DATOS DETERMINADA
        Connection conexion = Conexion.conexionMySQL();
        Statement sentencia = conexion.createStatement();
        sentencia.executeUpdate("USE Desguaces");
        String insertinto = "INSERT INTO " + tabla + " VALUES(" + sentencias + ");";
        System.out.println(insertinto);
        sentencia.executeUpdate(insertinto);
        sentencia.close();
        conexion.close();
    }
}
