package owo.com.androidunittest.targets;

/**
 * Created by wangli on 3/26/16.
 */
public class Fibonacci {
  public static int compute(int input) {
    if (input == 0) {
      return 0;
    } else if (input == 1) {
      return 1;
    } else {
      return compute(input - 1) + compute(input - 2);
    }
  }
}
