package owo.com.androidunittest.tests.robolectric;

import android.view.View;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import owo.com.androidunittest.BuildConfig;
import owo.com.androidunittest.MainActivity;

/*
  static {
    SUPPORTED_APIS = new HashMap<>();
    addSdk(Build.VERSION_CODES.JELLY_BEAN, "4.1.2_r1", "0");
    addSdk(Build.VERSION_CODES.JELLY_BEAN_MR1, "4.2.2_r1.2", "0");
    addSdk(Build.VERSION_CODES.JELLY_BEAN_MR2, "4.3_r2", "0");
    addSdk(Build.VERSION_CODES.KITKAT, "4.4_r1", "1");
    addSdk(Build.VERSION_CODES.LOLLIPOP, "5.0.0_r2", "1");
    ROBOLECTRIC_VERSION = getRobolectricVersion();
  }
 */
/*
#{resourceDir} and #{assetDir} should be relative to the directory containing AndroidManifest.xml.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/test/AndroidManifest.xml", sdk = 21,//
  constants = BuildConfig.class, resourceDir = "../test/res", assetDir = "assets")
public class RobolectricTest {
  @Test
  public void testIsTextEmpty() {
    Assert.assertTrue(AndroidTarget.isTextEmpty(""));
    Assert.assertFalse(AndroidTarget.isTextEmpty("abc"));
  }

  @Test
  public void testCreateView() {
    MainActivity activity = Robolectric.setupActivity(MainActivity.class);
    View v = AndroidTarget.makeView(activity);
    Assert.assertNotNull(v);
  }
}
