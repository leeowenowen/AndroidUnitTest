package owo.com.androidunittest.tests.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import owo.com.androidunittest.targets.Fibonacci;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangli on 3/26/16.
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {
  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {{0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}});
  }

  private int fInput;
  private int fExpected;

  public ParameterizedTest(int input, int expected) {
    fInput = input;
    fExpected = expected;
  }

  @Test
  public void test() {
    assertEquals(fExpected, Fibonacci.compute(fInput));
  }
}
