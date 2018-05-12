package model;


public class FillerField extends Field {

    public FillerField() {
        super();
    }

    public void swap(){
        if (text == '-'){
            text = '+';
        }
        else {
            text = '-';
        }
    }
}
