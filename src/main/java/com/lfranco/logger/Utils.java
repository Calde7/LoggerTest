/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfranco.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lfranco
 */
public class Utils {
    
    public static String convertDateCompleteToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }
    
}
