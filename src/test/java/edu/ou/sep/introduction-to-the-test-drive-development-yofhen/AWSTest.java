package edu.ou.sep.introduction_to_the_test_drive_development_yofhen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AWSTest {

	private static final int FILLER_VALUE = Integer.MIN_VALUE;
	private int[] original={1, 2, 3};
	AWS originalAWS;
	
	@BeforeEach
	void setUp() throws Exception {
		 originalAWS = new AWS(this.original);
	}

	@Test
	void testGetValues() {
		int[] result = originalAWS.getValues();
		assertEquals(result[0], original[0]);
		assertEquals(result[1], original[1]);
		assertEquals(result[2], original[2]);
	}

	@Test
	void testSetValues() {
		int[] test = {5,6,7};
		originalAWS.setValues(test);
		int[] result = originalAWS.getValues();
		assertEquals(result[0], test[0]);
		assertEquals(result[1], test[1]);
		assertEquals(result[2], test[2]);
	}

	@Test
	void testToString() {
		String result = originalAWS.toString();
		assertEquals(result, "AWS [values=" + Arrays.toString(original) + "]");
	}

	@Test
	void testAWS() {
		int[] expected = {1, 2, 3};
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		x[1] = 100;
		
		int[] actual = aws.getValues();
		assertEquals(actual[0], expected[0]);
		assertEquals(actual[1], expected[1]);
	}
	
	@Test
	void testRemove() {
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		
		int value = aws.remove(-1);
		int expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		 value = aws.remove(x.length + 10);
		expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		value = aws.remove(0);
		assertEquals(x[0], value);
		
		int[] r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
		value = aws.remove(2);
		assertEquals(r[2], value);
		
		r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
	}
	
	@Test
	void testFillAndExpand() {
		int position = 1;
		int numberOfTimes = 2;
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
		int first = org[0];
 		
		int expected = originalAWS.getValues().length + numberOfTimes;
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		assertEquals(first, result[0]);	
	}
	
	@Test
	void testFillAndExpandWithNegative() {
		int position = 1;
		int numberOfTimes = -2;
		
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
 		int first = org[0];
		int expected = originalAWS.getValues().length + Math.abs(numberOfTimes);
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		 
		assertEquals(first, result[0]);

	
	}
	
	@Test
	void testClear() {
		int[] org = originalAWS.getValues();
		assertEquals(org.length, original.length);
		originalAWS.clear();
		int[] org1 = originalAWS.getValues();
		assertEquals(org1.length, 0);		
	}
	
	@Test
	void testRemoveBiggerThan() {
		originalAWS.removeBiggerThan(2);
		int[] result = originalAWS.getValues();
		assertEquals(3, result.length);
		assertEquals(1, result[0]);
		assertEquals(2, result[1]);
		assertEquals(FILLER_VALUE, result[2]);
		
		originalAWS.removeBiggerThan(1);
		result = originalAWS.getValues();
		assertEquals(3, result.length);
		assertEquals(1, result[0]);
		assertEquals(FILLER_VALUE, result[1]);
		assertEquals(FILLER_VALUE, result[2]);
	}

	@Test
	void testStepMultiplier() {
		originalAWS.stepMultiplier();
		int[] result = originalAWS.getValues();
		assertEquals(3, result.length);
		assertEquals(2, result[0]);
		assertEquals(4, result[1]);
		assertEquals(6, result[2]);
		
		int[] test = {5, 15, 50, 150};
		originalAWS.setValues(test);
		originalAWS.stepMultiplier();
		result = originalAWS.getValues();
		assertEquals(test.length, result.length);
		assertEquals(10, result[0]);
		assertEquals(60, result[1]);
		assertEquals(5000, result[2]);
		assertEquals(150, result[3]);
	}
}
