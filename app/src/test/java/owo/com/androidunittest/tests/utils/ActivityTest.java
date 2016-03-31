package owo.com.androidunittest.tests.utils;

import android.app.Activity;

import org.robolectric.Robolectric;

import owo.com.androidunittest.MainActivity;

public class ActivityTest{
    protected final MainActivity mActivity;
    public ActivityTest() {
      mActivity = Robolectric.setupActivity(MainActivity.class);
    }

    public Activity getActivity()
    {
        return mActivity;
    }

    public Activity getContext()
    {
        return mActivity;
    }
}
