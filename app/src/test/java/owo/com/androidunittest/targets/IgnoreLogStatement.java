package owo.com.androidunittest.targets;

import org.junit.runners.model.Statement;

import owo.com.androidunittest.targets.Log;
import owo.com.androidunittest.tests.utils.TestReflectHelper;


public class IgnoreLogStatement extends Statement {
  private final Statement base;

  public IgnoreLogStatement(Statement base) {
    this.base = base;
  }

  @Override
  public void evaluate() throws Throwable {
    Object logDelegate = TestReflectHelper.getFieldValue(Log.class, "mDelegate");
    Log.setDelegate(new Log.Delegate() {
      @Override
      public void info(String tag, String msg) {
        //Do nothing here!
      }

      @Override
      public void fatal() throws Exception {
        //Do nothing here!
      }
    });
    try {
      base.evaluate();
    } finally {
      TestReflectHelper.setFieldValue(Log.class, "mDelegate", logDelegate);
    }
  }
}
