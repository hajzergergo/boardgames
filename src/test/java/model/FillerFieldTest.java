package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FillerFieldTest {

    @Test
    void swap() {
        FillerField ff = new FillerField();
        assertEquals('-',ff.getText());
        ff.swap();
        assertEquals('+',ff.getText());
        ff.swap();
        assertEquals('-',ff.getText());
    }
}