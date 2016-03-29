package owo.com.androidunittest.tests.mockito;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import owo.com.androidunittest.targets.A;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

/**
 * Created by wangli on 3/26/16.
 */
public class MockitoTest {

  @Test
  public void testJudgeMock() {
    A a = mock(A.class);
    A b = spy(new A());
    Assert.assertTrue(Mockito.mockingDetails(a).isMock());
    Assert.assertFalse(Mockito.mockingDetails(a).isSpy());
    Assert.assertTrue(Mockito.mockingDetails(b).isMock());
    Assert.assertTrue(Mockito.mockingDetails(b).isSpy());
  }

  @Test
  public void testMock() {
    A a = mock(A.class);
    Mockito.when(a.publicMethod()).thenReturn("mockA");
    Assert.assertEquals("mockA", a.publicMethod());
    Mockito.verify(a, times(1)).publicMethod();
    Mockito.verify(a, never()).foo();
  }

  @Test
  public void testSpy() {
    A a = new A();
    a = Mockito.spy(a);
    Mockito.when(a.publicMethod()).thenReturn("mockA");
    Assert.assertEquals("mockA", a.publicMethod()); // mocked
    Assert.assertEquals("publicMethod2", a.publicMethod2()); // not mocked
    Mockito.verify(a, times(1)).publicMethod();
  }

  @Test(expected = Exception.class)
  public void testThrowException() {
    A a = mock(A.class);
    Mockito.doThrow(new Exception("exception")).when(a).foo();
    Mockito.verify(a).foo();
  }

  @Test
  public void testInOrder() {
    {
      List singleMock = mock(List.class);
      singleMock.add("was added first");
      singleMock.add("was added second");
      InOrder inOrder = Mockito.inOrder(singleMock);
      inOrder.verify(singleMock).add("was added first");
      inOrder.verify(singleMock).add("was added second");
    }
    {
      List firstMock = mock(List.class);
      List secondMock = mock(List.class);
      firstMock.add("was called first");
      secondMock.add("was called second");
      InOrder inOrder = Mockito.inOrder(firstMock, secondMock);
      inOrder.verify(firstMock).add("was called first");
      inOrder.verify(secondMock).add("was called second");
    }
  }

  @Test
  public void testAnswer() {
    A a = mock(A.class);
    Mockito.doAnswer(new Answer<Void>() {
      public Void answer(InvocationOnMock invocation) {
        Object[] args = invocation.getArguments();
        Object mock = invocation.getMock();
        return null;
      }

    }).when(a).foo();
    a.foo();
    Mockito.verify(a).foo();
  }

  @Test
  public void testArgumentCaptor() {
    A a = mock(A.class);
    ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
    a.foo("abc");
    Mockito.verify(a).foo(argument.capture());
    Assert.assertEquals("abc", argument.getValue());
  }
}
