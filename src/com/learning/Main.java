package com.learning;

import java.sql.*;

/*
 * 1. import package --> java.sql
 * 2. load and register driver --> com.mysql.jdbc.Driver
 * 3. create a connection
 * 4. create a statement
 * 5. execute the query
 * 6. process the results
 * 7. close
 */

public class Main {

    public static void main(String[] args) throws Exception {
        Language myLanguage = new Language("Russian");

        //create new language
        //myLanguage.createLanguage();

        //update language name by id
        //myLanguage.updateLanguageName("Finnish", 4);

        //delete language by ID
        //myLanguage.deleteLanguageByID(4);

        //get language name by ID
        //myLanguage.getLanguageNameByID(2);

        //get languages
        myLanguage.getLanguages();

    }
}