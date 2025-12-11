package com.eloy.code.proyectoonvera_as.presentacion.common;

import android.widget.EditText;

import java.text.SimpleDateFormat;

public class Validator {
    private EditText editText;
    private String errorMessage;

    private Validator(EditText editText){
        this.editText=editText;
    }

    public static Validator with(EditText editText){
        return new Validator(editText);
    }

    public Validator required(){
        String value= editText.getText().toString().trim();
        if(errorMessage == null && value.isEmpty()){
            errorMessage="Este campo es obligatorio";
        }
        return this;
    }
    public Validator length(int len){
        String value = editText.getText().toString().trim();
        if(errorMessage == null && value.length() !=len){
            errorMessage="Debe tener"+ len+"caracteres";
        }
        return this;
    }

    public Validator isDate(){
        String value= editText.getText().toString().trim();
        if(errorMessage == null && !isValidDate(value)){
            errorMessage = "El campo debe ser fecha válida";
        }
        return this;
    }

    public boolean validate(){
        if(errorMessage != null){
            editText.setError(errorMessage);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isValidDate(String date){
        if(date == null || date.isEmpty()) return false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
