package owo.com.androidunittest.tests.junit;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import owo.com.androidunittest.targets.AWithLog;
import owo.com.androidunittest.targets.IgnoreLogRule;

public class IgnoreLogRuleTest {

  @Rule
  public IgnoreLogRule rule = new IgnoreLogRule();

  @Test
  public void testRule() throws Exception {
    AWithLog log = new AWithLog();
    Assert.assertTrue(log.foo());
  }
}
