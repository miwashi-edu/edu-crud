package net.miwashi.crud;

import net.miwashi.crud.model.Book;
import net.miwashi.crud.model.BookRepository;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.MySQLContainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class AppTest {
    private static final Logger LOGGER = LoggerFactory.getLogger( AppTest.class );
    private static final String INIT_DB_FILE_NAME = "initdb.sql";
    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @BeforeAll
    public static void setUp() throws Exception{
        URL resource = AppTest.class.getClassLoader().getResource(INIT_DB_FILE_NAME);
        File sqlFile = new File(resource.toURI());

        Connection con = DriverManager.getConnection(mySQLContainer.getJdbcUrl(), mySQLContainer.getUsername(), mySQLContainer.getPassword());
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader(sqlFile.getAbsolutePath()));
        sr.runScript(reader);

        Statement stmt = con.createStatement();
        ResultSet rs = con.createStatement().executeQuery("SHOW TABLES");
        while(rs.next()) {
            var tableName = rs.getString(1);
            LOGGER.info("Created table " + tableName + " for testing.");
        }
        con.close();

        System.setProperty("net.miwashi.crud.connectionstring", mySQLContainer.getJdbcUrl());
        System.setProperty("net.miwashi.crud.user", mySQLContainer.getUsername());
        System.setProperty("net.miwashi.crud.password", mySQLContainer.getPassword());
    }

    @Test
    void shouldRun() {
        var books = new BookRepository();
        var book = books.create(new Book("Java for Beginners"));
        assertNotEquals( 0, book.getId(), "Indx must not be zero!");
    }
}
