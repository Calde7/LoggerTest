/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfranco.logger;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author lfranco
 */
public class Action {

    Map<String, String> dbParams;
    
    public Map<String, String> setdbParam() {
        dbParams = new HashMap<String, String>();
        dbParams.put("serverName", "localhost");
        dbParams.put("portNumber", "3306");
        dbParams.put("dbms", "mysql");
        dbParams.put("database", "db_logger");
        dbParams.put("userName", "root");
        dbParams.put("password", "root");
        dbParams.put("logFileFolder", "/home/lfranco/tmp/logger");
        return dbParams;        
    }

    public boolean setLog() throws Exception {
        dbParams = setdbParam();
        JobLogger jobLogger = null;
        int optionType = askForType();
        switch (optionType) {
            case 1:
                //FILE
                jobLogger = new JobLogger(true, false, false, dbParams);
                break;
            case 2:
                //CONSOLE
                jobLogger = new JobLogger(false, true, false, dbParams);
                break;
            case 3:
                //DATABASE
                jobLogger = new JobLogger(false, false, true, dbParams);
                break;
            case 4:
                //ALL
                jobLogger = new JobLogger(true, true, true, dbParams);
                break;
            default:
                System.exit(0);
        }
        //
        System.out.println("Ingrese el mensaje que desea guardar: ");
        Scanner scn = new Scanner(System.in);
        String msg = scn.nextLine();
        switch (askForLog()) {
            case 1:
                //WARNING
                jobLogger.LogMessage(msg, 1);
            case 2:
                //SEVERE
                jobLogger.LogMessage(msg, 2);
            case 3:
                //ALL
                jobLogger.LogMessage(msg, 3);
            default:
                System.exit(0);
        }
        return true;
    }

    public int askForType() {
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option;
        while (!exit) {
            System.out.println("Por favor, seleccione la opcion para registrar LOG:");
            System.out.println("1 - Guardar en archivo");
            System.out.println("2 - Imprimir en consola");
            System.out.println("3 - Registrar en la base de datos");
            System.out.println("4 - Todas");
            System.out.println("5 - Salir");
            try {
                option = sn.nextInt();
                switch (option) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    case 4:
                        return 4;
                    default:
                        System.out.println("Debe ingresar numeros entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un numero");
                sn.next();
            }
        }
        return 5;
    }

    public int askForLog() {
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option;
        while (!exit) {
            System.out.println("Por favor, seleccione la opcion para guardar el LOG: (Por defecto se guardan todos)");
            System.out.println("1 - Guardar log WARNING");
            System.out.println("2 - Guardar log SEVERE");
            System.out.println("3 - Todos");
            try {
                option = sn.nextInt();
                switch (option) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    default:
                        System.out.println("Debe ingresar numeros entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un numero");
                sn.next();
            }
        }
        return 4;
    }

}
