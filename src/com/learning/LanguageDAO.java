package com.learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LanguageDAO {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Connection con = null;
    String query = "";

    public void openConnection(){
        String url = "jdbc:mysql://localhost:3306/phrazer";
        String user = "root";
        String password = "";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void checkRecord(){
        query = "SELECT COUNT(1) FROM Language WHERE unique_key = value";
    }

    public Language createLanguage(Language languageObject){
        languageObject.setDateCreated(dateFormat.format(Calendar.getInstance().getTime()));
        query = MessageFormat.format("INSERT INTO Language (Name, DateCreated) VALUES (''{0}'', ''{1}'');",
                languageObject.getName(), languageObject.getDateCreated());

        try{
            Statement st = con.createStatement();

            // need to check if exists

            int rowsCreated = st.executeUpdate(query);
            System.out.println(rowsCreated + " row has been inserted.");

            st.close();
            con.close();

        } catch (Exception ex){
            System.out.println(ex);
        }

        return null;
    }

    public Language deleteLanguageByID(int ID){
        query = MessageFormat.format("DELETE FROM Language WHERE LanguageID = ''{0}''", ID);

        try{
            // need to check if it exists first
            Statement st = con.createStatement();
            int rowsDeleted = st.executeUpdate(query);
            System.out.println(rowsDeleted + " row has been deleted.");

            st.close();
            con.close();

        } catch(Exception ex){
            System.out.println(ex);
        }
        // Should I return object which has been deleted?
        return null;
    }

    public Language updateLanguageNameByID(Language languageObject){
        languageObject.setDateModified(dateFormat.format(Calendar.getInstance().getTime()));
        query = MessageFormat.format("UPDATE Language SET Name = ''{0}'', DateModified = ''{1}'' WHERE LanguageID = ''{2}''",
                languageObject.getName(), languageObject.getDateModified(), languageObject.getID());
        try{
            Statement st = con.createStatement();
            int rowsUpdated = st.executeUpdate(query);
            System.out.println(rowsUpdated + " row has been updated.");

            st.close();
            con.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public Language getLanguageNameByID(int ID){

        Language myLanguage = new Language();
        myLanguage.setID(ID);
        query = MessageFormat.format("SELECT Name FROM Language WHERE LanguageID = ''{0}''", myLanguage.getID());

        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String resultName = rs.getString("Name");
            myLanguage.setName(resultName);
            st.close();
            con.close();
            return myLanguage;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public Language getLanguages(){

        query = "SELECT * FROM Language";

        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            String data = "";
            while (rs.next()){
                data = rs.getInt("LanguageID") + " : " + rs.getString("Name");
                System.out.println(data);
            }

            st.close();
            con.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
