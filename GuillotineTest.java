/**
 * This is the Guillotine testing class.
 * @author Boris Brondz
 */
package com.example.prelabs;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GuillotineTest {

    /**
     * Test for the Guillotine class getter/setter methods.
     * These do not follow the standard "zero, one, many" and "first, middle, last" edge-case testing
     * as they do not involve any loops or complicated logic.
     */

    @Test
    public void testGetterSetters() {
        Guillotine guillotine = new Guillotine();
        assertTrue(Guillotine.getTurn());
        Guillotine.switchTurn();
        assertFalse(Guillotine.getTurn());

        assertEquals(0, Guillotine.getPlayer1Score());
        guillotine.setPlayer1Score(4);
        assertEquals(4, Guillotine.getPlayer1Score());
        guillotine.setPlayer2Score(5);
        assertEquals(5, Guillotine.getPlayer2Score());

    }

    /**
     * These are the tests for the LinkedList class methods that involve complicated loops/logic for the Guillotine class
     * card game.
     */

    @Test
    public void testReverseList() {

        /**
         * Test zero, one, many
         */

        //Zero test
        LinkedList<Integer> listZero = new LinkedList<>();
        listZero.reverseList();
        assertNull(listZero.getFirstNode());

        //One test
        LinkedList<Integer> listOne = new LinkedList<>();
        listOne.addToEnd(1);
        listOne.reverseList();
        assertEquals(1, listOne.getFirstNode().getElement());

        //Many test
        LinkedList<Integer> listMany = new LinkedList<>();
        listMany.addToEnd(1);
        listMany.addToEnd(2);
        listMany.addToEnd(3);
        listMany.addToEnd(4);
        listMany.addToEnd(5);
        listMany.reverseList();

        assertEquals(5, listMany.getFirstNode().getElement());
        assertEquals(4, listMany.getFirstNode().getNext().getElement());
        assertEquals(3, listMany.getFirstNode().getNext().getNext().getElement());
        assertEquals(2, listMany.getFirstNode().getNext().getNext().getNext().getElement());
        assertEquals(1, listMany.getFirstNode().getNext().getNext().getNext().getNext().getElement());



        /**
         * Test first, middle, last
         */

        LinkedList<Integer> listFirstMiddleLast = new LinkedList<>();
        listFirstMiddleLast.addToEnd(1);
        listFirstMiddleLast.addToEnd(2);
        listFirstMiddleLast.addToEnd(3);
        listFirstMiddleLast.addToEnd(4);
        listFirstMiddleLast.addToEnd(5);

        listFirstMiddleLast.reverseList();
        //Make sure first element is last (5)
        assertEquals(5, listFirstMiddleLast.getFirstNode().getElement());
        //Make sure middle element stays the same (3)
        assertEquals(3, listFirstMiddleLast.getFirstNode().getNext().getNext().getElement());
        //Make sure last element is first (1)
        assertEquals(1, listFirstMiddleLast.getFirstNode().getNext().getNext().getNext().getNext().getElement());

    }

    @Test
    public void testMoveFirstToLast() {

        /**
         * Test zero, one, many
         */

        //Zero test
        LinkedList<Integer> listZero = new LinkedList<>();
        listZero.moveFirstToLast();
        assertNull(listZero.getFirstNode());

        //One test
        LinkedList<Integer> listOne = new LinkedList<>();
        listOne.addToEnd(1);
        listOne.moveFirstToLast();
        //Can use getFirstNode() because order is not changing (only one element).
        assertEquals(1, listOne.getFirstNode().getElement());

        //Many test
        LinkedList<Integer> listMany = new LinkedList<>();
        listMany.addToEnd(1);
        LLNode<Integer> nodeptr = listMany.getFirstNode();
        listMany.addToEnd(2);
        listMany.addToEnd(3);
        listMany.addToEnd(4);
        listMany.addToEnd(5);
        listMany.moveFirstToLast();
        while(listMany.getFirstNode().getNext() != null ) {
            nodeptr = nodeptr.getNext();
        }
        assertEquals(1, nodeptr.getElement());

    }

    @Test
    public void testMoveLastToFirst() {

        /**
         * Test zero, one, many
         */

        //Zero test
        LinkedList<Integer> listZero = new LinkedList<>();
        listZero.moveLastToFirst();
        assertNull(listZero.getFirstNode());

        //One test
        LinkedList<Integer> listOne = new LinkedList<>();
        listOne.addToEnd(1);
        listOne.moveLastToFirst();
        //Can use getFirstNode() because order is not changing (only one element).
        assertEquals(1, listOne.getFirstNode().getElement());

        //Many test
        LinkedList<Integer> listMany = new LinkedList<>();
        listMany.addToEnd(1);
        listMany.addToEnd(2);
        listMany.addToEnd(3);
        listMany.addToEnd(4);
        listMany.addToEnd(5);
        listMany.moveLastToFirst();
        assertEquals(5, listMany.getFirstNode().getElement());

    }

    @Test
    public void testMoveBack() {

        /**
         * Test zero, one, many
         */

        //Zero test
        LinkedList<Integer> listZero = new LinkedList<>();
        listZero.moveBack(1);
        assertNull(listZero.getFirstNode());

        //One test
        LinkedList<Integer> listOne = new LinkedList<>();
        listOne.addToEnd(1);
        listOne.moveBack(1);
        //Can use getFirstNode() because order is not changing (only one element).
        assertEquals(1, listOne.getFirstNode().getElement());

        ///Many test
        LinkedList<Integer> listMany = new LinkedList<>();
        listMany.addToEnd(1);
        listMany.addToEnd(2);
        listMany.addToEnd(3);
        listMany.addToEnd(4);

        listMany.moveBack(2);
        //call getNext() twice as we moved it twice.
        assertEquals(1, listMany.getFirstNode().getNext().getNext().getElement());

        /**
         * Test first, middle, last
         */

        //First test
        LinkedList<Integer> listFirst = new LinkedList<>();
        listFirst.addToEnd(1);
        listFirst.addToEnd(2);
        listFirst.addToEnd(3);
        listFirst.addToEnd(4);
        listFirst.moveBack(1);
        assertEquals(1, listFirst.getFirstNode().getElement());
        //Middle test
        LinkedList<Integer> listMiddle = new LinkedList<>();
        listMiddle.addToEnd(1);
        listMiddle.addToEnd(2);
        listMiddle.addToEnd(3);
        listMiddle.addToEnd(4);
        listMiddle.moveBack(2);
        //Last test
        LinkedList<Integer> listLast = new LinkedList<>();
        listLast.addToEnd(1);
        listLast.addToEnd(2);
        listLast.addToEnd(3);
        listLast.addToEnd(4);
        listLast.moveBack(3);
        assertEquals(1, listLast.getFirstNode().getNext().getNext().getNext().getElement());

    }

    @Test
    public void testReverseFirstK() {

        /**
         * Test zero, one, many
         */

        //Zero test
        LinkedList<Integer> listZero = new LinkedList<>();
        listZero.reverseFirstK(1);
        assertNull(listZero.getFirstNode());

        //One test
        LinkedList<Integer> listOne = new LinkedList<>();
        listOne.addToEnd(1);
        listOne.reverseFirstK(1);
        //Can use getFirstNode() because order is not changing (only one element).
        assertEquals(1, listOne.getFirstNode().getElement());

        //Many test
        LinkedList<Integer> listMany = new LinkedList<>();
        listMany.addToEnd(1);
        listMany.addToEnd(2);
        listMany.addToEnd(3);
        listMany.addToEnd(4);
        listMany.addToEnd(5);
        listMany.reverseFirstK(3);
        assertEquals(3, listMany.getFirstNode().getElement());
        assertEquals(2, listMany.getFirstNode().getNext().getElement());
        assertEquals(1, listMany.getFirstNode().getNext().getNext().getElement());
        assertEquals(4, listMany.getFirstNode().getNext().getNext().getNext().getElement());
        assertEquals(5, listMany.getFirstNode().getNext().getNext().getNext().getNext().getElement());


        /**
         * Test first, middle, last
         */

        //First test
        LinkedList<Integer> listFirst = new LinkedList<>();
        listFirst.addToEnd(1);
        listFirst.addToEnd(2);
        listFirst.addToEnd(3);
        listFirst.addToEnd(4);
        listFirst.reverseFirstK(2);
        assertEquals(1, listFirst.getFirstNode().getElement());
        assertEquals(2, listFirst.getFirstNode().getNext().getElement());

        //Middle test
        LinkedList<Integer> listMiddle = new LinkedList<>();
        listMiddle.addToEnd(1);
        listMiddle.addToEnd(2);
        listMiddle.addToEnd(3);
        listMiddle.addToEnd(4);
        listMiddle.addToEnd(5);
        listMiddle.reverseFirstK(3);
        assertEquals(3, listMiddle.getFirstNode().getElement());
        assertEquals(2, listMiddle.getFirstNode().getNext().getElement());
        assertEquals(1, listMiddle.getFirstNode().getNext().getNext().getElement());
       // assertEquals(4, listMiddle.getFirstNode().getNext().getNext().getNext().getElement());
        //assertEquals(5, listMiddle.getFirstNode().getNext().getNext().getNext().getNext().getElement());

        //Last test
        LinkedList<Integer> listLast = new LinkedList<>();
        listLast.addToEnd(1);
        listLast.addToEnd(2);
        listLast.addToEnd(3);
        listLast.addToEnd(4);
        listLast.addToEnd(5);
        listLast.reverseFirstK(4);
        assertEquals(4, listLast.getFirstNode().getElement());
        assertEquals(3, listLast.getFirstNode().getNext().getElement());
        assertEquals(2, listLast.getFirstNode().getNext().getNext().getElement());
        assertEquals(1, listLast.getFirstNode().getNext().getNext().getNext().getElement());

    }

/**
 * These tests are for the Guillotine class methods that incorporate gameplay functions (outside of JavaFX)
 */

    @Test
    public void testMoveBackFour() {

        /**
         * Test zero, one, many
         */
        Guillotine g = new Guillotine();
        LinkedList<Integer> listZero = new LinkedList<>();
        Guillotine.moveBackFour(listZero);
        assertNull(listZero.getFirstNode());

        LinkedList<Integer> listOne = new LinkedList<>();
        listOne.addToEnd(1);
        Guillotine.moveBackFour(listOne);
        assertEquals(1, listOne.getFirstNode().getElement());

        LinkedList<Integer> listMany = new LinkedList<>();
        listMany.addToEnd(1);
        listMany.addToEnd(2);
        listMany.addToEnd(3);
        listMany.addToEnd(4);
        listMany.addToEnd(5);
        Guillotine.moveBackFour(listMany);
        assertEquals(1, listMany.getFirstNode().getNext().getNext().getNext().getElement());

    }

    @Test
    public void testMoveBackThree() {

        /**
         * Test zero, one, many
         */

        LinkedList<Integer> testZero = new LinkedList<>();
        Guillotine g = new Guillotine();
        Guillotine.moveBackThree(testZero);
        assertNull(testZero.getFirstNode());

        LinkedList<Integer> testOne = new LinkedList<>();
        testOne.addToEnd(1);
        Guillotine.moveBackThree(testOne);
        assertEquals(1, testOne.getFirstNode().getElement());

        LinkedList<Integer> testMany = new LinkedList<>();
        testMany.addToEnd(1);
        testMany.addToEnd(2);
        testMany.addToEnd(3);
        testMany.addToEnd(4);
        testMany.addToEnd(5);
        Guillotine.moveBackThree(testMany);
        assertEquals(1, testMany.getFirstNode().getNext().getNext().getElement());

    }

    @Test
    public void testMoveBackTwo() {

        /**
         * Test zero, one, many
         */

        LinkedList<Integer> testZero = new LinkedList<>();
        Guillotine g = new Guillotine();
        Guillotine.moveBackTwo(testZero);
        assertNull(testZero.getFirstNode());

        LinkedList<Integer> testOne = new LinkedList<>();
        testOne.addToEnd(1);
        Guillotine.moveBackTwo(testOne);
        assertEquals(1, testOne.getFirstNode().getElement());

        LinkedList<Integer> testMany = new LinkedList<>();
        testMany.addToEnd(1);
        testMany.addToEnd(2);
        testMany.addToEnd(3);
        testMany.addToEnd(4);
        testMany.addToEnd(5);
        Guillotine.moveBackTwo(testMany);
        assertEquals(1, testMany.getFirstNode().getNext().getNext().getElement());

    }

    @Test
    public void testMoveLeadToEnd() {

        /**
         * Test zero, one, many
         */

        LinkedList<Integer> testZero = new LinkedList<>();
        Guillotine g = new Guillotine();
        Guillotine.moveLeadToEnd(testZero);
        assertNull(testZero.getFirstNode());

        LinkedList<Integer> testOne = new LinkedList<>();
        testOne.addToEnd(1);
        Guillotine.moveLeadToEnd(testOne);
        assertEquals(1, testOne.getFirstNode().getElement());

        LinkedList<Integer> testMany = new LinkedList<>();
        testMany.addToEnd(1);
        testMany.addToEnd(2);
        testMany.addToEnd(3);
        testMany.addToEnd(4);
        testMany.addToEnd(5);
        Guillotine.moveLeadToEnd(testMany);
        assertEquals(1, testMany.getFirstNode().getNext().getNext().getNext().getNext().getElement());


    }

    @Test
    public void testReverseCards() {

        /**
         * Test zero, one, many
         */

        LinkedList<Integer> testZero = new LinkedList<>();
        Guillotine g = new Guillotine();
        Guillotine.reverseCards(testZero);
        assertNull(testZero.getFirstNode());

        LinkedList<Integer> testOne = new LinkedList<>();
        testOne.addToEnd(1);
        Guillotine.reverseCards(testOne);
        assertEquals(1, testOne.getFirstNode().getElement());

        LinkedList<Integer> testMany = new LinkedList<>();
        testMany.addToEnd(1);
        testMany.addToEnd(2);
        testMany.addToEnd(3);
        testMany.addToEnd(4);
        testMany.addToEnd(5);
        Guillotine.reverseCards(testMany);
        assertEquals(1, testMany.getFirstNode().getNext().getNext().getNext().getNext().getElement());
        assertEquals(2, testMany.getFirstNode().getNext().getNext().getNext().getElement());
        assertEquals(3, testMany.getFirstNode().getNext().getNext().getElement());
        assertEquals(4, testMany.getFirstNode().getNext().getElement());
        assertEquals(5, testMany.getFirstNode().getElement());


        /**
         * Test first, middle, last
         */

        LinkedList<Integer> testFirst = new LinkedList<>();
        testFirst.addToEnd(1);
        testFirst.addToEnd(2);
        testFirst.addToEnd(3);
        testFirst.addToEnd(4);
        testFirst.addToEnd(5);
        Guillotine.reverseCards(testFirst);
        assertEquals(1, testFirst.getFirstNode().getNext().getNext().getNext().getNext().getElement());
        assertEquals(3, testFirst.getFirstNode().getNext().getNext().getElement());
        assertEquals(5, testFirst.getFirstNode().getElement());

    }

    @Test
    public void testReverseFirstFive() {

        /**
         * Test zero, one, many
         */

        LinkedList<Integer> testZero = new LinkedList<>();
        Guillotine g = new Guillotine();
        Guillotine.reverseFirstFive(testZero);
        assertNull(testZero.getFirstNode());

        LinkedList<Integer> testOne = new LinkedList<>();
        testOne.addToEnd(1);
        Guillotine.reverseFirstFive(testOne);
        assertEquals(1, testOne.getFirstNode().getElement());

        LinkedList<Integer> testMany = new LinkedList<>();
        testMany.addToEnd(1);
        testMany.addToEnd(2);
        testMany.addToEnd(3);
        testMany.addToEnd(4);
        testMany.addToEnd(5);
        testMany.addToEnd(6);
        Guillotine.reverseFirstFive(testMany);
        assertEquals(1, testMany.getFirstNode().getNext().getNext().getNext().getNext().getElement());
        assertEquals(2, testMany.getFirstNode().getNext().getNext().getNext().getElement());
        assertEquals(3, testMany.getFirstNode().getNext().getNext().getElement());
        assertEquals(4, testMany.getFirstNode().getNext().getElement());
        assertEquals(5, testMany.getFirstNode().getElement());
        assertEquals(6, testMany.getFirstNode().getNext().getNext().getNext().getNext().getNext().getElement());




        /**
         * Test first, middle, last
         */

    }

    @Test
    public void testSkipTurn() {

        /**
         * Test player1 and player2 conditions (true/false)
         */

        Guillotine g = new Guillotine();
        Guillotine.skipTurn();
        assertFalse(Guillotine.getTurn());
        Guillotine.skipTurn();
        assertTrue(Guillotine.getTurn());


    }

    @Test
    public void testAddCardsToLinkedList() {

        /**
         * Test zero, one, many
         */

        Guillotine g = new Guillotine();
        LinkedList<Guillotine.GuillotineCards> testZero = new LinkedList<>();
        Guillotine.addCardsToLinkedList(testZero, 0);
        assertNull(testZero.getFirstNode());

        LinkedList<Guillotine.GuillotineCards> testOne = new LinkedList<>();
        Guillotine.addCardsToLinkedList(testOne, 1);
        assertEquals(1, testOne.getFirstNode().getElement().toString());

        LinkedList<Guillotine.GuillotineCards> testMany = new LinkedList<>();
        Guillotine.addCardsToLinkedList(testMany, 5);
        assertEquals(1, testMany.getFirstNode().getElement().toString());
        assertEquals(2, testMany.getFirstNode().getNext().getElement().toString());
        assertEquals(3, testMany.getFirstNode().getNext().getNext().getElement().toString());
        assertEquals(4, testMany.getFirstNode().getNext().getNext().getNext().getElement().toString());
        assertEquals(5, testMany.getFirstNode().getNext().getNext().getNext().getNext().getElement().toString());

    }

    @Test
    public void testEndGame() {

        /**
         * Test tie, player1 winning, player2 winning.
         */

        LinkedList<Guillotine.GuillotineCards> emptyHand = new LinkedList<>();

          ByteArrayOutputStream output = new ByteArrayOutputStream();
         PrintStream originalOut = System.out;
        //private final PrintStream originalErr = System.err;


        Guillotine g = new Guillotine();
        g.setPlayer1Score(10);
        g.setPlayer2Score(20);
        Guillotine.endGame(emptyHand);
        assertEquals("Player 2 wins!", output.toString());

        ByteArrayOutputStream output2 = new ByteArrayOutputStream();
        PrintStream originalOut2 = System.out;

        Guillotine g2 = new Guillotine();
        g2.setPlayer1Score(20);
        g2.setPlayer2Score(10);
        Guillotine.endGame(emptyHand);
        assertEquals("Player 1 wins!", output2.toString());

        ByteArrayOutputStream output3 = new ByteArrayOutputStream();
        PrintStream originalOut3 = System.out;

        Guillotine g3 = new Guillotine();
        g3.setPlayer1Score(10);
        g3.setPlayer2Score(10);
        Guillotine.endGame(emptyHand);
        assertEquals("Tie!", output3.toString());

    }

}