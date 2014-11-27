package logica.basesDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private Connection con;
	
	public void crearConexion(){
        try {      
        // Load the JDBC driver
        String driverName = "com.mysql.jdbc.Driver"; 
        Class.forName(driverName);

        // Create a connection to the database
        String db = "drnfrani_ips3";
        String url = "jdbc:mysql://31.22.4.75/"+db;
        String username = "drnfrani_almacen";
        String password = "carapan1234";
        setCon(DriverManager.getConnection(url, username, password));
        
        } catch (Exception e) {
             System.out.println(e);
        }
        //System.out.println("CONEXION CREADA");
	}

	public void cerraConexion() {
		setCon(null);	
        //System.out.println("CONEXION CERRADA");
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
}

