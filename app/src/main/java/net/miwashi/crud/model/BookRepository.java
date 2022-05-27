package net.miwashi.crud.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BookRepository {

    private final String CONNECTION_STRING;
    private final Properties connectionProps;

    public BookRepository(){
        CONNECTION_STRING = System.getProperty("net.miwashi.crud.connectionstring");
        connectionProps = new Properties();
        connectionProps.put("user", System.getProperty("net.miwashi.crud.user"));
        connectionProps.put("password", System.getProperty("net.miwashi.crud.password"));
    }

    public Book create(Book Book) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONNECTION_STRING, connectionProps);

            var stmt = con.prepareStatement("INSERT INTO BOOKS(NAME) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Book.getName());
            stmt.execute();
            var rs = stmt.getGeneratedKeys();
            if(rs.next()){
                int bookId = rs.getInt(1);
                Book.setId(bookId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            }catch(Exception ignore){}
        }
        return Book;
    }

    public Book findById(int id) {
        Connection connection = null;
        Book Book = null;
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING, connectionProps);
            var stmt = connection.prepareStatement("SELECT ID, NAME FROM BOOKS WHERE ID = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()){
                String name = rs.getString(2);
                int balance = rs.getInt(3);
                Book = new Book(name);
                Book.setId(id);
            }
        }catch(Exception e){

        }finally{
            try {
                connection.close();
            }catch(Exception ignore){}
        }
        return Book;
    }
}
