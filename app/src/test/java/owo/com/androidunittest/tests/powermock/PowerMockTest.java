package owo.com.androidunittest.tests.powermock;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

import owo.com.androidunittest.targets.A;


@PowerMockIgnore({
  "org.mockito.*", "org.robolectric.*", "org.json.*", "owo.com.androidunittest.targets.A"
})
@PrepareForTest({A.class})
public class PowerMockTest {
  @Rule
  public PowerMockRule rule = new PowerMockRule();

  @Test
  public void testStaticMethod() {
    PowerMockito.mockStatic(A.class);
    PowerMockito.when(A.publicStaticMethod()).thenReturn("abc");
    Assert.assertEquals("abc", A.publicStaticMethod());

    PowerMockito.verifyStatic();
    A.publicStaticMethod();
  }

  @Test
  public void testPrivateMethod() throws Exception {
    PowerMockito.spy(A.class);
    PowerMockito.when(A.class, "privateStaticMethod").thenReturn("abc");

    String real = A.callPrivateStaticMethod();
    Assert.assertEquals("callabc", real);

    PowerMockito.verifyPrivate(A.class).invoke("privateStaticMethod");
  }

  @Test
  public void testConstructor() throws Exception {
    A a = new A();
    PowerMockito.whenNew(A.class).withNoArguments().thenReturn(a);

    A a2 = A.create();
    Assert.assertEquals(a, a2);

//    PowerMockito.verifyNew(A.class).withNoArguments();
  }
}
