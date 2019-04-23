package com.manipovore.app;

import com.manipovore.calculator.Operation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationTests {

	@Test
	public void contextLoads() {
	}

	Operation goodAdditionTwo = new Operation("1+1");

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

	@Test
	public void should_BadOperationWith_Letters() {
		assertEquals(new BigDecimal(0), new Operation("1+t").getResult());
	}

	@Test
	public void should_BadOperationWith_Escape() {
		assertEquals(new BigDecimal(5), new Operation("1+ 4").getResult());
	}

	@Test
	public void should_GoodOperationWith_Addition() {
		assertEquals(new BigDecimal(2), new Operation("1+1").getResult());
	}

	@Test
	public void should_GoodOperationWith_Soustraction() {
		assertEquals(new BigDecimal(0), new Operation("1-1").getResult());
	}

	@Test
	public void should_GoodOperationWith_Multiplication() {
		assertEquals(new BigDecimal(4), new Operation("2*2").getResult());
	}

	@Test
	public void should_GoodOperationWith_Divide() {
		assertEquals(new BigDecimal(2), new Operation("8/4").getResult());
	}

	@Test
	public void should_GoodOperationWith_Modulo() {
		assertEquals(new BigDecimal(2), new Operation("8%3").getResult());
	}

	@Test
	public void should_GoodOperationWith_NegativeNumber() {
		assertEquals(new BigDecimal(4), new Operation("8+-4").getResult());
	}

	@Test
	public void should_GoodOperationWith_Priority() {
		Double arroundRes = (double)Math.round(-28.632000002 * 10000) / 10000;
		BigDecimal bd = new BigDecimal(Double.toString(arroundRes)).stripTrailingZeros();
		assertEquals(bd, new Operation("8+5-6*8+6/6+3%2*5.368").getResult());
	}

}
