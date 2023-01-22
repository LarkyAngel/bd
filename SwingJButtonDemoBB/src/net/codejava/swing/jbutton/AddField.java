package net.codejava.swing.jbutton;

public class AddField {
    private String fieldName;
    private char type; //domyslnie s - string, i - int, d - double, e - date, wiem, ze brzydko, ale nie wiem jak inaczej
    AddField(String fieldName, char type){
        this.fieldName = fieldName;
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }
    public char getType(){
        return type;
    }
}
