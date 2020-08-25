/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 29 23:49:21 GMT 2020
 */

package org.webbitserver.netty;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.FileDescriptor;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFileInputStream;
import org.junit.runner.RunWith;
import org.webbitserver.netty.WebSocketClient;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class WebSocketClient_ESTest extends WebSocketClient_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      WebSocketClient webSocketClient0 = new WebSocketClient();
      FileDescriptor fileDescriptor0 = new FileDescriptor();
      MockFileInputStream mockFileInputStream0 = new MockFileInputStream(fileDescriptor0);
      // EXCEPTION DIFF:
      // The modified version did not exhibit this exception:
      //     org.webbitserver.WebbitException : org.evosuite.runtime.mock.java.lang.MockThrowable
      // Undeclared exception!
      try { 
        webSocketClient0.setupSsl(mockFileInputStream0, "~<H6Pzp?F^,.hI/");
        fail("Expecting exception: RuntimeException");
      
      } catch(RuntimeException e) {
         //
         // org.evosuite.runtime.mock.java.lang.MockThrowable
         //
         verifyException("org.webbitserver.helpers.SslFactory", e);
         assertTrue(e.getMessage().equals("org.evosuite.runtime.mock.java.lang.MockThrowable"));   
      }
  }
}