package owo.com.androidunittest.tests.utils;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.res.FsFile;

import java.io.File;

public class SimpleRobolectricTestRunner extends RobolectricGradleTestRunner {

	public SimpleRobolectricTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	protected AndroidManifest getAppManifest(Config config) {
		AndroidManifest appManifest = super.getAppManifest(config);
		FsFile androidManifestFile = appManifest.getAndroidManifestFile();
		if (androidManifestFile.exists()) {
			return appManifest;
		} else {
			String moduleRoot = getModuleRootPath(config);
			final String build_test_out = "build" + File.separator + "out";
			androidManifestFile = FileFsFile.from(moduleRoot, 
					appManifest.getAndroidManifestFile().getPath()
					.replace("bundles", 
							"manifests" + File.separator + "full")
					.replace("build", build_test_out));
			FsFile resDirectory = FileFsFile.from(moduleRoot,
					appManifest.getResDirectory().getPath()
					.replace("" + File.separator + "res", "")
					.replace("bundles", "res")
					.replace("build", build_test_out));
			FsFile assetsDirectory = FileFsFile.from(moduleRoot,
					appManifest.getAssetsDirectory().getPath()
				.replace("" + File.separator + "assets", "")
				.replace("bundles", "assets")
				.replace("build", build_test_out));
			return new AndroidManifest(androidManifestFile, resDirectory, assetsDirectory);
		}
	}

	private String getModuleRootPath(Config config) {
		String moduleRoot = config.constants().getResource("").toString().replace("file:", "");
		// Assert.assertEquals("abc", moduleRoot);
		return moduleRoot.substring(0, moduleRoot.indexOf("/build"));
	}

}
