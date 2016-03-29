package owo.com.androidunittest.tests.robolectric;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by wangli on 3/29/16.
 */
public class AndroidTarget {

  public static boolean isTextEmpty(String text) {
    return TextUtils.isEmpty(text);
  }

  public static View makeView(Context context) {
    return new View(context);
  }
}
