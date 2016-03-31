package owo.com.androidunittest.targets;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class IgnoreLogRule implements MethodRule {
    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return new IgnoreLogStatement(base);
    }
}
