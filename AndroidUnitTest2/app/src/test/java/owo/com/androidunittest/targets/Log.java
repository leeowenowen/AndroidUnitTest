package owo.com.androidunittest.targets;

/**
 * Created by wangli on 3/22/16.
 */
public class Log {
  public static interface Delegate {
    void info(String tag, String msg);

    // only for test
    void fatal() throws Exception;
  }

  private class DefaultDelegate implements Delegate {
    @Override
    public void info(String tag, String msg) {
      System.out.println("[tag:" + tag + "][msg:" + msg + "]");
    }

    @Override
    public void fatal() throws Exception {
      throw new Exception("fatal from log");
    }
  }

  private static Delegate mDelegate;

  public static void setDelegate(Delegate delegate) {
    mDelegate = delegate;
  }

  public static void log(String tag, String msg) {
    if (mDelegate != null) {
      mDelegate.info(tag, msg);
    }
  }

  public static void fatal() throws Exception {
    if (mDelegate != null) {
      mDelegate.fatal();
    }
  }
}
