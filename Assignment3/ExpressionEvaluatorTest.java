package edu.stevens.cs570.assignments;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionEvaluatorTest {

    @Test
    public void test() {
        ExpressionEvaluator exp = new ExpressionEvaluator("4.0*2.0");
        assertEquals(8.0, exp.evaluate(), 0.1);
        ExpressionEvaluator exp1 = new ExpressionEvaluator("3*8");
        assertEquals(24.0, exp1.evaluate(), 0.1);
        ExpressionEvaluator exp2 = new ExpressionEvaluator("2+5");
        assertEquals(7.0, exp2.evaluate(), 0.1);
        ExpressionEvaluator exp3 = new ExpressionEvaluator("3-7");
        assertEquals(-4.0, exp3.evaluate(), 0.1);
        ExpressionEvaluator exp4 = new ExpressionEvaluator("2+3+8*5");
        assertEquals(45.0, exp4.evaluate(), 0.1);
        ExpressionEvaluator exp5 = new ExpressionEvaluator("(2**3)+8*5");
        assertEquals(48.0, exp5.evaluate(), 0.1);
        ExpressionEvaluator exp6 = new ExpressionEvaluator("(10)+6*11");
        assertEquals(76.0, exp6.evaluate(), 0.1);
        ExpressionEvaluator exp7 = new ExpressionEvaluator("5**2+10**3");
        assertEquals(1025.0, exp7.evaluate(), 0.1);


    }

}
