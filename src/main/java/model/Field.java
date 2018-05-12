package model;


public class Field {

    protected static final int FIELD_SIZE = 40;

    protected char text;

    public Field() {
        text = '-';
    }

    public char getText(){
        return text;
    }
}
