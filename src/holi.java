import java.sql.*;

public class holi {
static String user="root";
static String pass="";
static String url="jdbc:odbc:mysql";
   
    public static void main(String[] args) {
   
    Connection cnn=null;
        try{
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
    cnn=DriverManager.getConnection(url, user, pass);
    if(cnn!=null){
    System.out.println("Se conecto a la base de datos");
    }else{System.out.println("No se conecto a la base de datos");}
    }catch(ClassNotFoundException cnfex){
    cnfex.printStackTrace();
    }catch(SQLException sqlex){
        sqlex.printStackTrace();
    }catch(Exception ex){
        ex.printStackTrace();
    }}}