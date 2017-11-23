package com.lfranco.logger;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("*** Desarrollado por Leandro Franco en new branch ***");
            Action a = new Action();
            a.setLog();
        } catch (Exception e) {
            e.getMessage();
        }finally{
            System.out.println("Belatrix Software Logger");
        }
    }
}
