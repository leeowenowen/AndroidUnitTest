package owo.com.androidunittest.tests.configuration;

import org.mockito.configuration.DefaultMockitoConfiguration;

public class MockitoConfiguration extends DefaultMockitoConfiguration {
  @Override
  public boolean enableClassCache() {
    return false;
  }
}
