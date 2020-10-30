/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restaurat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author victor
 */
public final class Conexion_BD {
     
    Connection conn= null;
    String hostname=null;
    String port =null;
    String database =null;
    String username = null;
    String password = null;
    String jndi=null;
    String driver= null;
    static Properties props = new Properties();

    
    public Conexion_BD(){
    InputStream in = null;
    try{
    in = Files.newInputStream(FileSystems.getDefault().getPath("c:\\restaurant\\db_props.properties"));
     props.load(in);
     in.close();
    }
    catch (IOException ex){
       System.out.println( ex.getMessage());
    }
    
    // Invocar un metodo para cargar info en memoria. 
    cargarPropiedades();
    
    
    }
        public void cargarPropiedades(){
        this.hostname= props.getProperty("hostname");
        this.port= props.getProperty("port");
        this.database=props.getProperty("database");
        this.username=props.getProperty("username");
        this.password=props.getProperty("password");
        this.driver=props.getProperty("driver");
        jndi= props.getProperty("jndi");
        
        System.out.println(hostname);
    }
    
        public Connection getConection() throws SQLException{
                try{
                    Class.forName(driver).newInstance();
                    String jdbcUrl = "jdbc:mysql://"+hostname+":"+port+"/"+database+"?useTime=true&serverTimezone=UTC"; 
                    conn = DriverManager.getConnection(jdbcUrl,username,password);
                    System.out.println("Conexion Establecida ");
                }catch(Exception e){
                   System.out.println(e.getMessage());
                }
        return conn;
    }
    public void CerrarConection(){
    try{
        conn.close();
        System.out.println("La sesion se cerro exitosamente");
    }catch(SQLException e){
        System.out.println("Error al cerrar la conexion");
    
    }
   
}


}
     
    
    
