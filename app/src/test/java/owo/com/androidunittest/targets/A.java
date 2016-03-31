package owo.com.androidunittest.targets;

/**
 * Created by wangli on 3/22/16.
 */
public class A {
  private int mPrivateAttribute = 1;
  public int mPublicAttribute = 1;

  public A() {

  }

  private String privateMethod() {
    return "privateMethod";
  }

  public String publicMethod() {
    return "publicMethod";
  }

  public String publicMethod2() {
    return "publicMethod2";
  }

  public void foo() {
  }

  public void foo(String string) {

  }

  private static String privateStaticMethod() {
    return "privateMethod";
  }

  public static String publicStaticMethod() {
    return "publicMethod";
  }

  public static String callPrivateStaticMethod() {
    return "call" + privateStaticMethod();
  }

  public static A create() {
    return new A();
  }
}
