package owo.com.androidunittest.tests.utils;

import java.util.concurrent.Executor;
/**
 * 用于在当前线程上下文中执行闭包代码
 * @author wangli
 *
 */
public class SyncExecutor implements Executor {
	@Override
	public void execute(Runnable command) {
		command.run();
	}

}
