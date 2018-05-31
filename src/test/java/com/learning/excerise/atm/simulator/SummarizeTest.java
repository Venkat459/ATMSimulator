package com.learning.excerise.atm.simulator;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class SummarizeTest {
  @Rule
  public final TextFromStandardInputStream systemInMock
    = emptyStandardInputStream();

  @Test
  public void summarizesTwoNumbers() {
	Summarize s = new Summarize();
    systemInMock.provideLines("1", "2");
    
    int sum = s.sumOfNumbersFromSystemIn();
    System.out.println("sum:" + sum);
    assertEquals(3, sum);
  }
}

