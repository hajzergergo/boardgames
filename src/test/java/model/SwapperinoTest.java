package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapperinoTest {

    Swapperino swapperino;

    @Test
    void shoot() {
        swapperino = new Swapperino(10,10);

        swapperino.shoot(1,1);
        assertEquals('+',swapperino.table[0][1].text);
        assertEquals('+',swapperino.table[1][0].text);
        assertEquals('+',swapperino.table[1][1].text);
        assertEquals('+',swapperino.table[2][1].text);
        assertEquals('+',swapperino.table[1][2].text);

        swapperino.shoot(9,9);
        assertEquals('+',swapperino.table[9][9].text);
        assertEquals('+',swapperino.table[8][9].text);
        assertEquals('+',swapperino.table[9][8].text);

        swapperino.shoot(8,9);
        assertEquals('-',swapperino.table[8][9].text);
        assertEquals('+',swapperino.table[7][9].text);
        assertEquals('-',swapperino.table[9][9].text);
        assertEquals('+',swapperino.table[8][8].text);

        swapperino.shoot(1,1);
        assertEquals('-',swapperino.table[0][1].text);
        assertEquals('-',swapperino.table[1][0].text);
        assertEquals('-',swapperino.table[1][1].text);
        assertEquals('-',swapperino.table[2][1].text);
        assertEquals('-',swapperino.table[1][2].text);
    }

    @Test
    void filledWith() {
        swapperino = new Swapperino(10,10);
        assertEquals(0,swapperino.filledWith('q'));
        assertEquals(0,swapperino.filledWith('4'));
        assertEquals(0,swapperino.filledWith('.'));


        assertEquals(100,swapperino.filledWith('-'));
        assertEquals(0,swapperino.filledWith('+'));

        swapperino.shoot(1,1);
        assertEquals(95,swapperino.filledWith('-'));
        assertEquals(5,swapperino.filledWith('+'));

        swapperino.shoot(1,1);
        assertEquals(100,swapperino.filledWith('-'));
        assertEquals(0,swapperino.filledWith('+'));

    }

    @Test
    void isOver() {
        swapperino = new Swapperino(10,10);
        assertEquals(false,swapperino.isOver());
        swapperino.shoot(1,1);
        swapperino.shoot(3,3);
        swapperino.shoot(5,5);
        swapperino.shoot(7,7);
        swapperino.shoot(1,4);
        swapperino.shoot(2,6);
        swapperino.shoot(1,8);
        swapperino.shoot(4,1);
        swapperino.shoot(6,2);
        assertEquals(false,swapperino.isOver());
        swapperino.shoot(4,7);
        assertEquals(true,swapperino.isOver());
        swapperino.shoot(4,7);
        assertEquals(false,swapperino.isOver());
    }
}