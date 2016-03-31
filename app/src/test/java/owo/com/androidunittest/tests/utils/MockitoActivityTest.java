package owo.com.androidunittest.tests.utils;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class MockitoActivityTest extends ActivityTest {
    @Before
    public void setUp() throws Exception {
        // temporary workaround for an incompatibility in current dexmaker (1.1)
        // implementation and Android >= 4.3
        // cf. https://code.google.com/p/dexmaker/issues/detail?id=2 for details
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().toString());
        MockitoAnnotations.initMocks(this);
    }
        
}
