package com.learning;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;

public class Language {
    private int ID;
    private String Name;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private String dateCreated;
    private String dateModified;
    private String databaseUrl = "jdbc:mysql://localhost:3306/phrazer";
    private String userName = "root";
    private String password = "";
    private String query = "";

    // Constructor
    public Language(String Name) throws ClassNotFoundException {
        this.Name = Name;
        this.dateCreated = formatter.format(Calendar.getInstance().getTime());
        this.dateModified = dateCreated;

        Class.forName("com.mysql.jdbc.Driver");
    }

    // Setter
    public void setName (String Name) { this.Name = Name;}

    // Getter
    public String getName(){return this.Name; }

    // Create new language
    public void createLanguage() throws SQLException {
        Connection con = DriverManager.getConnection(databaseUrl, userName, password);
        Statement st = con.createStatement();

        query = MessageFormat.format("INSERT INTO Language (Name, DateCreated, DateModified) VALUES (''{0}'', ''{1}'', ''{2}'');",
                this.Name, this.dateCreated, this.dateModified);
        int rowsUpdated = st.executeUpdate(query);
        System.out.println(rowsUpdated + " row has been inserted.");

        st.close();
        con.close();
    }

    // Update language name by ID
    public void updateLanguageName(String Name, int ID) throws SQLException {
        Connection con = DriverManager.getConnection(databaseUrl, userName, password);
        Statement st = con.createStatement();

        this.dateModified = formatter.format(Calendar.getInstance().getTime());
        query = MessageFormat.format("UPDATE Language SET Name = ''{0}'', DateModified = ''{1}'' WHERE LanguageID = ''{2}''",
                Name, this.dateModified, ID);

        int rowsUpdated = st.executeUpdate(query);
        System.out.println(rowsUpdated + " row has been updated.");

        st.close();
        con.close();
    }

    // Delete row
    public void deleteLanguageByID (int ID) throws SQLException{
        Connection con = DriverManager.getConnection(databaseUrl, userName, password);
        Statement st = con.createStatement();

        // need to check if ID exists
        query = MessageFormat.format("DELETE FROM Language WHERE LanguageID = ''{0}''", ID);

        int rowsUpdated = st.executeUpdate(query);
        System.out.println(rowsUpdated + " row has been deleted.");

        st.close();
        con.close();
    }

    // Get language by ID
    public void getLanguageNameByID (int ID) throws SQLException{
        Connection con = DriverManager.getConnection(databaseUrl, userName, password);
        Statement st = con.createStatement();

        query = MessageFormat.format("SELECT Name FROM Language WHERE LanguageID = ''{0}''", ID);
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String resultName = rs.getString("Name");

        System.out.println(resultName);

        st.close();
        con.close();
    }

    // Get languages
    public void getLanguages () throws SQLException {
        Connection con = DriverManager.getConnection(databaseUrl, userName, password);
        Statement st = con.createStatement();

        query = "SELECT * FROM Language";
        ResultSet rs = st.executeQuery(query);

        String data = "";
        while (rs.next()){
            data = rs.getInt("LanguageID") + " : " + rs.getString("Name");
            System.out.println(data);
        }

        st.close();
        con.close();
    }
}
