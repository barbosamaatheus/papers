/*
 * This file was automatically generated by EvoSuite
 * Mon May 25 11:58:00 GMT 2020
 */

package org.jsoup.helper;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class HttpConnection_ESTest extends HttpConnection_ESTest_scaffolding {

  @Test(timeout = 11000)
  public void test0()  throws Throwable  {
      HttpConnection httpConnection0 = new HttpConnection();
      // Undeclared exception!
      try { 
        httpConnection0.execute();
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.evosuite.runtime.mock.java.net.MockURL", e);
      }
  }

  @Test(timeout = 11000)
  public void test1()  throws Throwable  {
      HttpConnection.Request httpConnection_Request0 = new HttpConnection.Request();
      Parser parser0 = Parser.xmlParser();
      String string0 = "7n^=0q^U%8AzVfriKq";
      Document document0 = parser0.parseInput(string0, string0);
      String string1 = "";
      String string2 = null;
      // Undeclared exception!
      try { 
        Parser.parse(string1, string2);
      } catch(IllegalArgumentException e) {
         //
         // BaseURI must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }
}