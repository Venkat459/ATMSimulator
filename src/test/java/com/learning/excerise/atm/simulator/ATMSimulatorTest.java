package com.learning.excerise.atm.simulator;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.ExpectedException;

public class ATMSimulatorTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void deposit_ministatment_quit_test1() throws Exception {
		ATMSimulator atmSimulator = new ATMSimulator();
		systemInMock.provideLines("1", "10 20 50 .", "4", "5");
		atmSimulator.runAtmSimulator();
	}

	@Test
	public void deposit_displayBalance_ministatement_quit_test2() throws Exception {
		ATMSimulator atmSimulator = new ATMSimulator();
		systemInMock.provideLines("1", "10 20 50 .", "3", "4", "5");
		atmSimulator.runAtmSimulator();
	}

	
	public void deposit_displayBalance_ministatement_quit_test3() throws Exception {
	
		exceptionRule.expect(Exception.class);
		exceptionRule.expectMessage("Input is not in the correct format, it should be ended with Space and .  by e.g. 10 20 50 .");
		ATMSimulator atmSimulator = new ATMSimulator();
		systemInMock.provideLines("1", "10 20 50 ");
		atmSimulator.runAtmSimulator();
	
	}

	
	@Test
	public void whenExceptionThrown_thenRuleIsApplied() {
		exceptionRule.expect(NumberFormatException.class);
		exceptionRule.expectMessage("For input string");
		Integer.parseInt("1a");
	}
}
