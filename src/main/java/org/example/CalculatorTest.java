package org.example;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @org.junit.jupiter.api.Test
    void testStart() {
        Calculator test = new Calculator();
        assertEquals(7.0, test.start("3.0+(2-1)*4"));
        assertEquals(-4.0, test.start("-(2-1)*4"));
    }

    @org.junit.jupiter.api.Test
    void testPreparingExp() {
        Calculator test = new Calculator();
        assertEquals("3.0+(2-1)*4", test.preparingExp("3.0+(2-1)*4"));
        assertEquals("0-(2-1)*4", test.preparingExp("-(2-1)*4"));
    }

    @org.junit.jupiter.api.Test
    void testExpressionToRPN() {
        Calculator test = new Calculator();
        assertEquals("3.0 2 1 - 4*+", test.expressionToRPN("3.0+(2-1)*4"));
        assertEquals("0 2 1 - 4*-", test.expressionToRPN("0-(2-1)*4"));
    }

    @org.junit.jupiter.api.Test
    void testRpnToAnswer() {
        Calculator test = new Calculator();
        assertEquals(7.0, test.rpnToAnswer("3.0 2 1 - 4*+"));
        assertEquals(-4.0, test.rpnToAnswer("0 2 1 - 4*-"));
    }
}