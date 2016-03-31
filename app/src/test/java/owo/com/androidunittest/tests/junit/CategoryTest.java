package owo.com.androidunittest.tests.junit;

import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by wangli on 3/26/16.
 */
public class CategoryTest {
  public interface FastTests { /* category marker */
  }

  public interface SlowTests { /* category marker */
  }

  public class A {
    @Test
    public void a() {
    }

    @Category(SlowTests.class)
    @Test
    public void b() {
    }
  }

  @Category({SlowTests.class, FastTests.class})
  public class B {
    @Test
    public void c() {
    }
  }

  @RunWith(Categories.class)
  @Categories.IncludeCategory(SlowTests.class)
  @Suite.SuiteClasses({A.class, B.class})
  // Note that Categories is a kind of Suite
  public class SlowTestSuite {
    // Will run A.b and B.c, but not A.a
  }

  @RunWith(Categories.class)
  @Categories.IncludeCategory(SlowTests.class)
  @Categories.ExcludeCategory(FastTests.class)
  @Suite.SuiteClasses({A.class, B.class})
  // Note that Categories is a kind of Suite
  public class SlowTestButNoFastTestSuite {
    // Will run A.b, but not A.a or B.c
  }
}
