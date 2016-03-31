package owo.com.androidunittest.tests.junit;

import org.hamcrest.core.CombinableMatcher;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;


public class JunitMainTest {
  @Test
  public void testAssertArrayEquals() {
    byte[] expected = "trial".getBytes();
    byte[] actual = "trial".getBytes();
    Assert.assertArrayEquals("failure - byte arrays not same", expected, actual);
  }

  @Test
  public void testAssertEquals() {
    Assert.assertEquals("failure - strings are not equal", "text", "text");
  }

  @Test
  public void testAssertFalse() {
    Assert.assertFalse("failure - should be false", false);
  }

  @Test
  public void testAssertNotNull() {
    Assert.assertNotNull("should not be null", new Object());
  }

  @Test
  public void testAssertNotSame() {
    Assert.assertNotSame("should not be same Object", new Object(), new Object());
  }

  @Test
  public void testAssertNull() {
    Assert.assertNull("should be null", null);
  }

  @Test
  public void testAssertTrue() {
    Assert.assertTrue("failure - should be true", true);
  }

  @Test
  public void testAssertSame() {
    Integer aNumber = Integer.valueOf(768);
    Assert.assertSame("should be same", aNumber, aNumber);
  }

  // JUnit Matchers assertThat
  @Test
  public void testAssertThatBothContainsString() {
    assertThat("albumen", allOf(containsString("a"), containsString("b")));
  }

  @Test
  public void testAssertThatHasItems() {
    assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
  }

  @Test
  public void testAssertThatEveryItemContainsString() {
    assertThat(Arrays.asList(new String[] {"fun", "ban", "net"}), everyItem(containsString("n")));
  }

  // Core Hamcrest Matchers with assertThat
  @Test
  public void testAssertThatHamcrestCoreMatchers() {
    assertThat("good", allOf(equalTo("good"), startsWith("good")));
    assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
    assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
    assertThat(7, not(CombinableMatcher.<Integer>either(equalTo(3)).or(equalTo(4))));
    assertThat(new Object(), not(sameInstance(new Object())));
  }

  @Test(expected= IndexOutOfBoundsException.class)
  public void empty() { new ArrayList<Object>().get(0); }

  @Ignore("Test is ignored as a demonstration")
  @Test
  public void testSame() {assertThat(1, is(1));}

  @Test(timeout=1000)
  public void testWithTimeout() {
//    while(true){
//
//    }
  }

  @Test
  public void filenameIncludesUsername() throws Exception {
    boolean flag = false;
    assumeTrue(flag);
    throw new Exception("flag is true!");
  }

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();
  @Test
  public void countsAssets() throws IOException {
    File icon = tempFolder.newFile("icon.png");
    Assert.assertTrue(icon.exists());
  }
}