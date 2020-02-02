package com.learning;

public class Main {

    public static void main(String[] args) throws Exception {

        LanguageDAO dao = new LanguageDAO();

        //create new language
        dao.openConnection();
        Language myLanguage = new Language("English");
        dao.createLanguage(myLanguage);

        //get language name by ID
        dao.openConnection();
        System.out.println(dao.getLanguageNameByID(4));

        //delete language by ID
        dao.openConnection();
        dao.deleteLanguageByID(1);

        myLanguage.setName("French");
        myLanguage.setID(3);
        dao.openConnection();
        dao.updateLanguageNameByID(myLanguage);

        // get languages
        dao.openConnection();
        dao.getLanguages();
    }
}

