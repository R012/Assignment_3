
package odbc_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario Cobos Maestre
 */
public class ODBC_Test {

    private static Connection getConnection(String userName, String password) 
            throws SQLException, ClassNotFoundException
    {
        String driver = "org.postgresql.Driver";
        Class.forName(driver);
        System.out.println(DriverManager.getDrivers().nextElement().toString());
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", userName, password);
        return conn;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String usrName, passwrd;
        System.out.print("User name: ");
        usrName = new Scanner(System.in).next();
        System.out.print("Password: ");
        passwrd = new Scanner(System.in).next();
        Connection conn = null;
        try {
            conn = getConnection(usrName, passwrd);
        } catch (SQLException ex) {
            Logger.getLogger(ODBC_Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ODBC_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(conn == null)
        {
            throw new NullPointerException();
        }
        else
        {
            try{
                System.out.println("Connection was succesful");
                conn.setAutoCommit(false);
                while(!conn.isClosed())
                {
                    System.out.println("Insert statement: ");
                    String statement = new Scanner(System.in).nextLine();
                    System.out.println("Query: "+statement);
                    if(statement.toLowerCase().equals("exit"))
                        conn.close();
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(statement);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int count = rsmd.getColumnCount();
                    while(rs.next())
                    {
                        for(int i=1; i<=count; i++)
                            System.out.print(rs.getObject(i).toString()+" | ");
                        System.out.print("\n");
                    }
                }
            }catch(java.sql.SQLException se)
            {
                System.out.println(se.getCause()+" -> "+
                        se.getErrorCode()+": "+se.getMessage()+" / "+se.getSQLState());
            }
        }
    }
    
}
