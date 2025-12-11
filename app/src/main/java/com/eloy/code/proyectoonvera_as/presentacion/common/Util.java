package com.eloy.code.proyectoonvera_as.presentacion.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public  static Date stringToDate(String str){
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            return format.parse(str);
        }catch (Exception e){
            return null;
        }

    }
}
