/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 29 17:24:47 GMT 2020
 */

package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeVisitor;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class TextNode_ESTest extends TextNode_ESTest_scaffolding {

  @Test(timeout = 11000)
  public void test00()  throws Throwable  {
      String string0 = "*[3T>[%:,j";
      int int0 = 22;
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      // Undeclared exception!
      try { 
        textNode0.splitText(int0);
      } catch(IllegalArgumentException e) {
         //
         // Split offset must not be greater than current text length
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test01()  throws Throwable  {
      String string0 = "LERKjkgM";
      TextNode textNode0 = new TextNode();
      TextNode textNode1 = new TextNode(textNode0.TEXT_KEY, textNode0.TEXT_KEY);
      String string1 = textNode1.text();
      int int0 = (-989);
      StringBuilder stringBuilder0 = new StringBuilder();
      Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
      textNode0.outerHtmlTail(stringBuilder0, int0, document_OutputSettings0);
      // Undeclared exception!
      try { 
        textNode0.outerHtmlHead(stringBuilder0, int0, document_OutputSettings0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.nodes.Entities", e);
      }
  }

  @Test(timeout = 11000)
  public void test02()  throws Throwable  {
      String string0 = "x*4^8cXUThX).?=>e]";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = textNode0.absUrl(string0);
  }

  @Test(timeout = 11000)
  public void test03()  throws Throwable  {
      String string0 = "qw(3tq";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      Node node0 = textNode0.attr(string0, string0);
      char char0 = '/';
      char char1 = 'r';
      int int0 = 7;
      String string1 = TextNode.stripLeadingWhitespace(string0);
  }

  @Test(timeout = 11000)
  public void test04()  throws Throwable  {
      String string0 = "^[:-[x1,iTf";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "|[#$+^, ~*5tp";
      int int0 = 0;
      TextNode textNode1 = textNode0.splitText(int0);
      Node node0 = textNode0.doClone(textNode1);
      String string2 = textNode0.toString();
      boolean boolean0 = textNode1.isBlank();
      float float0 = 4231.6973F;
  }

  @Test(timeout = 11000)
  public void test05()  throws Throwable  {
      String string0 = "~`(vw<+'8XT";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      int int0 = (-1203);
      char char0 = 'Z';
      char char1 = '/';
      int int1 = 40;
      // Undeclared exception!
      try { 
        textNode0.splitText(int0);
      } catch(IllegalArgumentException e) {
         //
         // Split offset must be not be negative
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test06()  throws Throwable  {
      String string0 = "^[:-[x1,iTf";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = textNode0.getWholeText();
      TextNode textNode1 = textNode0.text(textNode0.TEXT_KEY);
      textNode1.parentNode = (Node) textNode0;
      textNode0.text = string0;
      String string2 = "|[#$+^, ~*5tp";
      Node node0 = textNode0.removeAttr(string2);
      int int0 = 0;
      // Undeclared exception!
      try { 
        textNode0.splitText(int0);
      } catch(UnsupportedOperationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.AbstractList", e);
      }
  }

  @Test(timeout = 11000)
  public void test07()  throws Throwable  {
      String string0 = "";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      TextNode textNode1 = textNode0.text(string0);
      TextNode textNode2 = TextNode.createFromEncoded(textNode1.TEXT_KEY, textNode0.TEXT_KEY);
      Attributes attributes0 = textNode2.attributes();
  }

  @Test(timeout = 11000)
  public void test08()  throws Throwable  {
      String string0 = "input";
      String string1 = "rZAJ T";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string1);
      String string2 = "e}bk";
      TextNode textNode1 = textNode0.text(string2);
      int int0 = 22;
      StringBuilder stringBuilder0 = new StringBuilder(int0);
      boolean boolean0 = TextNode.lastCharIsWhitespace(stringBuilder0);
      boolean boolean1 = TextNode.lastCharIsWhitespace(stringBuilder0);
      TextNode textNode2 = textNode1.text(string0);
      Attributes attributes0 = new Attributes();
      Attributes attributes1 = attributes0.clone();
      textNode0.attributes = attributes1;
      Attribute attribute0 = new Attribute(textNode2.TEXT_KEY, textNode0.TEXT_KEY);
      attributes1.put(attribute0);
      String string3 = "|JO}]8\"fEnIt19";
      Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
      Document.OutputSettings document_OutputSettings1 = document_OutputSettings0.clone();
      Entities.EscapeMode entities_EscapeMode0 = Entities.EscapeMode.xhtml;
      Document.OutputSettings document_OutputSettings2 = document_OutputSettings1.escapeMode(entities_EscapeMode0);
      Document.OutputSettings document_OutputSettings3 = document_OutputSettings1.clone();
      Document.OutputSettings document_OutputSettings4 = document_OutputSettings2.prettyPrint(boolean1);
      Document.OutputSettings document_OutputSettings5 = document_OutputSettings4.indentAmount(int0);
      attributes1.html(stringBuilder0, document_OutputSettings5);
      TextNode textNode3 = textNode0.text(string3);
      String string4 = "Split offset must be not be negative";
      TextNode textNode4 = textNode1.text(string4);
      int int1 = (-2188);
      textNode3.parentNode = (Node) textNode0;
      textNode4.setSiblingIndex(int1);
      String string5 = "htTen7'#";
      TextNode textNode5 = textNode3.text(string2);
      boolean boolean2 = TextNode.lastCharIsWhitespace(stringBuilder0);
      String string6 = "`^VF!M=qQpK7feK-2";
      TextNode textNode6 = textNode0.text(string6);
      boolean boolean3 = TextNode.lastCharIsWhitespace(stringBuilder0);
      TextNode textNode7 = textNode5.text(string2);
  }

  @Test(timeout = 11000)
  public void test09()  throws Throwable  {
      String string0 = "K%n1jf^-ZT;P";
      TextNode textNode0 = new TextNode(string0, string0);
  }

  @Test(timeout = 11000)
  public void test10()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      String string0 = textNode0.nodeName();
  }

  @Test(timeout = 11000)
  public void test11()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      // Undeclared exception!
      try { 
        textNode0.text();
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.helper.StringUtil", e);
      }
  }

  @Test(timeout = 11000)
  public void test12()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      StringBuilder stringBuilder0 = new StringBuilder((CharSequence) textNode0.TEXT_KEY);
      int int0 = 0;
      Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
      // Undeclared exception!
      try { 
        textNode0.outerHtmlHead(stringBuilder0, int0, document_OutputSettings0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.nodes.Entities", e);
      }
  }

  @Test(timeout = 11000)
  public void test13()  throws Throwable  {
      String string0 = "";
      String string1 = "6oD-qis^i@:\\XOl-Y:g";
      TextNode textNode0 = new TextNode(string0, string1);
      String string2 = textNode0.outerHtml();
      textNode0.ensureAttributes();
  }

  @Test(timeout = 11000)
  public void test14()  throws Throwable  {
      String string0 = "Split offset must not be greater than current text length";
      TextNode textNode0 = new TextNode(string0, string0);
      int int0 = 0;
      TextNode textNode1 = textNode0.splitText(int0);
      String string1 = textNode1.toString();
      String string2 = "@(-Zk</a2`fAX}t^$p";
      String string3 = TextNode.normaliseWhitespace(string2);
  }

  @Test(timeout = 11000)
  public void test15()  throws Throwable  {
      String string0 = null;
      // Undeclared exception!
      try { 
        TextNode.createFromEncoded(string0, string0);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test16()  throws Throwable  {
      String string0 = "EaG8)4";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      Node node0 = textNode0.removeAttr(string0);
  }

  @Test(timeout = 11000)
  public void test17()  throws Throwable  {
      String string0 = "On";
      String string1 = "Split offset must not be greater than current text length";
      TextNode textNode0 = new TextNode(string0, string1);
      String string2 = textNode0.absUrl(string0);
  }

  @Test(timeout = 11000)
  public void test18()  throws Throwable  {
      String string0 = "wp6@oDTbB";
      String string1 = TextNode.stripLeadingWhitespace(string0);
      String string2 = TextNode.normaliseWhitespace(string0);
      String string3 = "S=?mjw+.P+)";
      TextNode textNode0 = new TextNode(string2, string3);
      String string4 = textNode0.getWholeText();
      textNode0.ensureAttributes();
      String string5 = textNode0.getWholeText();
  }

  @Test(timeout = 11000)
  public void test19()  throws Throwable  {
      String string0 = "v$!%t";
      String string1 = "Split offset must be not be negative";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string1);
      String string2 = "";
      TextNode textNode1 = textNode0.text(string2);
      String string3 = textNode1.attr(textNode0.TEXT_KEY);
  }

  @Test(timeout = 11000)
  public void test20()  throws Throwable  {
      String string0 = "";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = textNode0.toString();
      String string2 = textNode0.text();
      String string3 = textNode0.text();
      Attributes attributes0 = textNode0.attributes();
      String string4 = textNode0.getWholeText();
      String string5 = textNode0.toString();
  }

  @Test(timeout = 11000)
  public void test21()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      String string0 = textNode0.getWholeText();
      String string1 = "org.jsoup.nodes.TextNode";
      // Undeclared exception!
      try { 
        TextNode.createFromEncoded(string0, string1);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test22()  throws Throwable  {
      String string0 = "";
      String string1 = "K/j}S2{psr/djnsrF";
      TextNode textNode0 = new TextNode(string0, string1);
      // Undeclared exception!
      try { 
        textNode0.attr(string0, string1);
      } catch(IllegalArgumentException e) {
         //
         // String must not be empty
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test23()  throws Throwable  {
      String string0 = "igjbI5TNY _EHi";
      String string1 = TextNode.normaliseWhitespace(string0);
      TextNode textNode0 = new TextNode();
      String string2 = "";
      String string3 = null;
      // Undeclared exception!
      try { 
        textNode0.attr(string2, string3);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test24()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      String string0 = "";
      TextNode textNode1 = textNode0.text(string0);
      int int0 = 1;
      // Undeclared exception!
      try { 
        textNode1.splitText(int0);
      } catch(IllegalArgumentException e) {
         //
         // Split offset must not be greater than current text length
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test25()  throws Throwable  {
      String string0 = "";
      String string1 = TextNode.stripLeadingWhitespace(string0);
      TextNode textNode0 = TextNode.createFromEncoded(string1, string1);
      boolean boolean0 = textNode0.isBlank();
      textNode0.ensureAttributes();
      Attributes attributes0 = textNode0.attributes();
  }

  @Test(timeout = 11000)
  public void test26()  throws Throwable  {
      String string0 = "";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "";
      String string2 = null;
      TextNode textNode1 = TextNode.createFromEncoded(string1, string2);
      textNode0.parentNode = (Node) textNode1;
      String string3 = textNode0.toString();
      StringBuilder stringBuilder0 = new StringBuilder((CharSequence) textNode0.TEXT_KEY);
      boolean boolean0 = TextNode.lastCharIsWhitespace(stringBuilder0);
  }

  @Test(timeout = 11000)
  public void test27()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      int int0 = 0;
      // Undeclared exception!
      try { 
        textNode0.splitText(int0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.nodes.TextNode", e);
      }
  }

  @Test(timeout = 11000)
  public void test28()  throws Throwable  {
      StringBuilder stringBuilder0 = null;
      // Undeclared exception!
      try { 
        TextNode.lastCharIsWhitespace(stringBuilder0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.nodes.TextNode", e);
      }
  }

  @Test(timeout = 11000)
  public void test29()  throws Throwable  {
      String string0 = null;
      String string1 = ")O*:WH+Ez2yj";
      TextNode textNode0 = new TextNode(string0, string1);
      TextNode textNode1 = textNode0.text(string0);
      // Undeclared exception!
      try { 
        textNode1.ensureAttributes();
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test30()  throws Throwable  {
      String string0 = "";
      String string1 = "{f#j!z?";
      TextNode textNode0 = new TextNode(string0, string1);
      String string2 = textNode0.toString();
      String string3 = "s2$bc5}t";
      String string4 = textNode0.absUrl(string3);
      boolean boolean0 = textNode0.isBlank();
      boolean boolean1 = textNode0.isBlank();
  }

  @Test(timeout = 11000)
  public void test31()  throws Throwable  {
      String string0 = "";
      String string1 = TextNode.stripLeadingWhitespace(string0);
      String string2 = null;
      TextNode textNode0 = new TextNode(string1, string2);
      // Undeclared exception!
      try { 
        textNode0.removeAttr(string0);
      } catch(IllegalArgumentException e) {
         //
         // String must not be empty
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test32()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      String string0 = "|`r/GThdP]=";
      // Undeclared exception!
      try { 
        textNode0.hasAttr(string0);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test33()  throws Throwable  {
      String string0 = "";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "";
      boolean boolean0 = textNode0.hasAttr(string1);
      Attributes attributes0 = textNode0.attributes();
      boolean boolean1 = textNode0.isBlank();
      String string2 = TextNode.stripLeadingWhitespace(string0);
      Attributes attributes1 = textNode0.attributes();
      StringBuilder stringBuilder0 = new StringBuilder();
      int int0 = 41;
      StringBuilder stringBuilder1 = stringBuilder0.append(int0);
      StringBuilder stringBuilder2 = stringBuilder1.append((long) int0);
      StringBuilder stringBuilder3 = stringBuilder2.appendCodePoint(int0);
      int int1 = 0;
      Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
      String string3 = "b>~R&kq^20s1Wy3P8.";
      // Undeclared exception!
      try { 
        document_OutputSettings0.charset(string3);
      } catch(IllegalCharsetNameException e) {
         //
         // b>~R&kq^20s1Wy3P8.
         //
         verifyException("java.nio.charset.Charset", e);
      }
  }

  @Test(timeout = 11000)
  public void test34()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      // Undeclared exception!
      try { 
        textNode0.attributes();
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test35()  throws Throwable  {
      String string0 = "\\";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = null;
      TextNode textNode1 = textNode0.text(string1);
      // Undeclared exception!
      try { 
        textNode1.absUrl(string1);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test36()  throws Throwable  {
      String string0 = "";
      String string1 = TextNode.normaliseWhitespace(string0);
      String string2 = "";
      TextNode textNode0 = TextNode.createFromEncoded(string1, string2);
      String string3 = textNode0.nodeName();
      int int0 = 0;
      // Undeclared exception!
      try { 
        textNode0.splitText(int0);
      } catch(IllegalArgumentException e) {
         //
         // Split offset must not be greater than current text length
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test37()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      String string0 = "5%VXqs=!";
      TextNode textNode1 = textNode0.text(string0);
      int int0 = 0;
      TextNode textNode2 = textNode1.splitText(int0);
      TextNode textNode3 = textNode2.splitText(int0);
      String string1 = "Q*/V.#-V@LWW3$jJ";
      TextNode.TEXT_KEY = string1;
      String string2 = "";
      Node node0 = textNode0.attr(textNode3.TEXT_KEY, string2);
  }

  @Test(timeout = 11000)
  public void test38()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      int int0 = (-3411);
      // Undeclared exception!
      try { 
        textNode0.splitText(int0);
      } catch(IllegalArgumentException e) {
         //
         // Split offset must be not be negative
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test39()  throws Throwable  {
      String string0 = "RByh";
      String string1 = TextNode.normaliseWhitespace(string0);
      TextNode textNode0 = new TextNode();
      // Undeclared exception!
      try { 
        textNode0.hasAttr(string1);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test40()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      String string0 = "";
      // Undeclared exception!
      try { 
        textNode0.removeAttr(string0);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test41()  throws Throwable  {
      String string0 = "rV|Xb2OCl1 iIb?";
      String string1 = "T?";
      TextNode textNode0 = new TextNode(string0, string1);
      String string2 = null;
      TextNode.TEXT_KEY = textNode0.TEXT_KEY;
      // Undeclared exception!
      try { 
        TextNode.normaliseWhitespace(string2);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.helper.StringUtil", e);
      }
  }

  @Test(timeout = 11000)
  public void test42()  throws Throwable  {
      int int0 = 0;
      StringBuilder stringBuilder0 = new StringBuilder(int0);
      boolean boolean0 = TextNode.lastCharIsWhitespace(stringBuilder0);
      TextNode textNode0 = new TextNode();
      String string0 = "";
      boolean boolean1 = TextNode.lastCharIsWhitespace(stringBuilder0);
      TextNode textNode1 = textNode0.text(string0);
      Attributes attributes0 = null;
      textNode1.attributes = attributes0;
      StringBuilder stringBuilder1 = stringBuilder0.reverse();
      String string1 = TextNode.normaliseWhitespace(textNode0.TEXT_KEY);
      String string2 = "Hf+/'vc9K";
      String string3 = TextNode.normaliseWhitespace(string2);
      boolean boolean2 = TextNode.lastCharIsWhitespace(stringBuilder0);
      int int1 = 1;
      // Undeclared exception!
      try { 
        textNode1.remove();
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test43()  throws Throwable  {
      int int0 = 0;
      StringBuilder stringBuilder0 = new StringBuilder(int0);
      String string0 = "Ep^S2i##*'fEN.>PV";
      StringBuffer stringBuffer0 = new StringBuffer(string0);
      StringBuilder stringBuilder1 = stringBuilder0.append(stringBuffer0);
      StringBuilder stringBuilder2 = stringBuilder1.insert(int0, (double) int0);
      String string1 = "@";
      StringBuilder stringBuilder3 = stringBuilder2.replace(int0, int0, string1);
      char[] charArray0 = new char[2];
      char char0 = 'G';
      StringBuilder stringBuilder4 = stringBuilder1.reverse();
      charArray0[0] = char0;
      char char1 = 'k';
      charArray0[1] = char1;
      StringBuilder stringBuilder5 = stringBuilder3.insert(int0, charArray0);
      boolean boolean0 = TextNode.lastCharIsWhitespace(stringBuilder5);
      TextNode textNode0 = new TextNode();
      String string2 = "org.jsoup.nodes.TextNode";
      TextNode textNode1 = textNode0.text(string2);
      textNode1.ensureAttributes();
      boolean boolean1 = TextNode.lastCharIsWhitespace(stringBuilder4);
      String string3 = ".h.ZqI\\tZUg;]dekT";
      TextNode textNode2 = textNode0.text(string3);
  }

  @Test(timeout = 11000)
  public void test44()  throws Throwable  {
      String string0 = "";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      StringBuilder stringBuilder0 = new StringBuilder((CharSequence) textNode0.TEXT_KEY);
      int int0 = 0;
      StringBuilder stringBuilder1 = stringBuilder0.insert(int0, (long) int0);
      float float0 = 0.0F;
      StringBuilder stringBuilder2 = stringBuilder1.insert(int0, float0);
      StringBuilder stringBuilder3 = stringBuilder2.append((CharSequence) string0);
      Object object0 = new Object();
      StringBuilder stringBuilder4 = stringBuilder3.append(object0);
      int int1 = 0;
      Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
      Document.OutputSettings document_OutputSettings1 = document_OutputSettings0.indentAmount(int0);
      boolean boolean0 = true;
      Document.OutputSettings document_OutputSettings2 = document_OutputSettings1.prettyPrint(boolean0);
      textNode0.outerHtmlTail(stringBuilder4, int1, document_OutputSettings2);
  }

  @Test(timeout = 11000)
  public void test45()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      NodeVisitor nodeVisitor0 = mock(NodeVisitor.class, new ViolatedAssumptionAnswer());
      Node node0 = textNode0.traverse(nodeVisitor0);
      String string0 = "org.jsoup.examples.HtmlToPlainText$FormattingVisitor";
      // Undeclared exception!
      try { 
        textNode0.attr(string0);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test46()  throws Throwable  {
      TextNode textNode0 = new TextNode();
      Node node0 = textNode0.clone();
      List<Node> list0 = textNode0.childNodesCopy();
      String string0 = "Split offset must not be greater than current text length";
      textNode0.baseUri = string0;
      // Undeclared exception!
      try { 
        textNode0.toString();
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.nodes.Entities", e);
      }
  }

  @Test(timeout = 11000)
  public void test47()  throws Throwable  {
      String string0 = "";
      StringBuilder stringBuilder0 = new StringBuilder(string0);
      boolean boolean0 = TextNode.lastCharIsWhitespace(stringBuilder0);
      String string1 = "3S";
      String string2 = "";
      TextNode textNode0 = TextNode.createFromEncoded(string1, string2);
      String string3 = "";
      TextNode textNode1 = textNode0.text(string3);
      int int0 = 32;
      Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
      Document.OutputSettings document_OutputSettings1 = document_OutputSettings0.prettyPrint(boolean0);
      textNode1.outerHtmlHead(stringBuilder0, int0, document_OutputSettings1);
      int int1 = 38;
      // Undeclared exception!
      try { 
        textNode0.splitText(int1);
      } catch(IllegalArgumentException e) {
         //
         // Split offset must not be greater than current text length
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test48()  throws Throwable  {
      String string0 = "^[:-[x1,iTf";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "FrPmFnX)ms";
      String string2 = textNode0.attr(string0);
      String string3 = "";
      String string4 = textNode0.getWholeText();
      TextNode textNode1 = textNode0.text(string3);
      textNode1.parentNode = (Node) textNode0;
      textNode0.text = string1;
      String string5 = "|[#$+^, ~*5tp";
      Node node0 = textNode0.removeAttr(string5);
      int int0 = 0;
      // Undeclared exception!
      try { 
        textNode0.splitText(int0);
      } catch(UnsupportedOperationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.AbstractList", e);
      }
  }

  @Test(timeout = 11000)
  public void test49()  throws Throwable  {
      String string0 = ":YPRQ9YY|RdU~bZhth";
      TextNode textNode0 = new TextNode(string0, string0);
      TextNode textNode1 = textNode0.text(string0);
      String string1 = "org.jsoup.select.Evaluator$ContainsText";
      TextNode textNode2 = textNode1.text(string1);
      int int0 = 32;
      TextNode textNode3 = textNode1.splitText(int0);
      StringBuilder stringBuilder0 = new StringBuilder((CharSequence) textNode0.TEXT_KEY);
      StringBuilder stringBuilder1 = stringBuilder0.appendCodePoint(int0);
      int int1 = 2285;
      // Undeclared exception!
      try { 
        stringBuilder1.insert(int1, (CharSequence) textNode3.TEXT_KEY);
      } catch(StringIndexOutOfBoundsException e) {
         //
         // String index out of range: 2285
         //
         verifyException("java.lang.AbstractStringBuilder", e);
      }
  }

  @Test(timeout = 11000)
  public void test50()  throws Throwable  {
      String string0 = "~`(vw<+'8XT";
      String string1 = "2p}:hR@m3bH!{8)";
      TextNode textNode0 = new TextNode(string0, string1);
      String string2 = "";
      TextNode textNode1 = textNode0.text(string2);
      List<Node> list0 = textNode1.siblingNodes();
      int int0 = (-1203);
      StringBuilder stringBuilder0 = new StringBuilder();
      StringBuilder stringBuilder1 = stringBuilder0.append((CharSequence) textNode1.TEXT_KEY);
      char[] charArray0 = new char[6];
      char char0 = 'c';
      charArray0[0] = char0;
      char char1 = 'Z';
      charArray0[1] = char1;
      char char2 = '/';
      charArray0[2] = char2;
      char char3 = 'T';
      charArray0[3] = char3;
      char char4 = ' ';
      charArray0[4] = char4;
      char char5 = 'D';
      charArray0[5] = char5;
      StringBuilder stringBuilder2 = stringBuilder1.append(charArray0);
      StringBuilder stringBuilder3 = stringBuilder2.append((Object) stringBuilder1);
      int int1 = 23;
      StringBuilder stringBuilder4 = stringBuilder3.appendCodePoint(int1);
      boolean boolean0 = TextNode.lastCharIsWhitespace(stringBuilder4);
      // Undeclared exception!
      try { 
        textNode1.splitText(int0);
      } catch(IllegalArgumentException e) {
         //
         // Split offset must be not be negative
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test51()  throws Throwable  {
      String string0 = "Qt\">N(pG";
      String string1 = null;
      // Undeclared exception!
      try { 
        TextNode.stripLeadingWhitespace(string1);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.nodes.TextNode", e);
      }
  }

  @Test(timeout = 11000)
  public void test52()  throws Throwable  {
      String string0 = "q)H";
      String string1 = "`HlynO(E0\"I{#s";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string1);
      String string2 = ".&)P#jUSPFr4,/n>H@8";
      Tag tag0 = Tag.valueOf(string1);
      TextNode textNode1 = textNode0.text(string1);
      textNode1.ensureAttributes();
      String string3 = textNode1.text();
      int int0 = (-989);
      StringBuilder stringBuilder0 = new StringBuilder(string3);
      char char0 = 'G';
      StringBuilder stringBuilder1 = stringBuilder0.append(char0);
      StringBuilder stringBuilder2 = stringBuilder1.reverse();
      Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
      Document.OutputSettings document_OutputSettings1 = new Document.OutputSettings();
      Entities.EscapeMode entities_EscapeMode0 = Entities.EscapeMode.extended;
      Document.OutputSettings document_OutputSettings2 = document_OutputSettings1.escapeMode(entities_EscapeMode0);
      boolean boolean0 = true;
      Document.OutputSettings document_OutputSettings3 = document_OutputSettings2.prettyPrint(boolean0);
      textNode0.outerHtmlTail(stringBuilder1, int0, document_OutputSettings2);
      String string4 = null;
      // Undeclared exception!
      try { 
        textNode1.text(string4);
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 11000)
  public void test53()  throws Throwable  {
      String string0 = "^[:-[x1,iTf";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "FrPmFnX)ms";
      String string2 = textNode0.attr(string0);
      String string3 = "";
      String string4 = textNode0.getWholeText();
      TextNode textNode1 = textNode0.text(string3);
      textNode1.parentNode = (Node) textNode0;
      textNode0.text = string1;
      String string5 = "|[#$+^, ~*5tp";
      Node node0 = textNode0.removeAttr(string5);
      int int0 = 0;
      TextNode textNode2 = new TextNode();
      // Undeclared exception!
      try { 
        textNode0.toString();
      } catch(StackOverflowError e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 11000)
  public void test54()  throws Throwable  {
      String string0 = "^[:-[x1,iTf";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "FrPmFnX)ms";
      String string2 = textNode0.attr(string0);
      String string3 = "";
      String string4 = textNode0.getWholeText();
      TextNode textNode1 = textNode0.text(string3);
      textNode0.text = string1;
      String string5 = "|[#$+^, ~*5tp";
      Node node0 = textNode0.removeAttr(string5);
      int int0 = 556;
      textNode0.siblingIndex = int0;
      int int1 = 0;
      TextNode textNode2 = textNode0.splitText(int1);
      String string6 = textNode0.toString();
      boolean boolean0 = textNode2.isBlank();
      float float0 = 4231.6973F;
      StringBuilder stringBuilder0 = new StringBuilder(string6);
      StringBuilder stringBuilder1 = stringBuilder0.append((long) int1);
      int int2 = 0;
      char char0 = '|';
      StringBuilder stringBuilder2 = stringBuilder1.insert(int2, char0);
      boolean boolean1 = TextNode.lastCharIsWhitespace(stringBuilder2);
      int int3 = (-991);
      Document.OutputSettings document_OutputSettings0 = null;
      // Undeclared exception!
      try { 
        textNode2.outerHtmlHead(stringBuilder1, int3, document_OutputSettings0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.nodes.Entities", e);
      }
  }

  @Test(timeout = 11000)
  public void test55()  throws Throwable  {
      String string0 = "^[:-[x1,iTf";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "";
      String string2 = TextNode.stripLeadingWhitespace(string0);
      TextNode textNode1 = textNode0.text(string1);
      int int0 = (-2486);
      textNode1.setSiblingIndex(int0);
      textNode0.text = textNode0.TEXT_KEY;
      String string3 = "|[#$+^, ~*5tp";
      Node node0 = textNode0.removeAttr(string3);
      int int1 = 0;
      TextNode textNode2 = textNode0.splitText(int1);
      String string4 = textNode0.toString();
      boolean boolean0 = textNode2.isBlank();
      boolean boolean1 = true;
      float float0 = (-1376.86F);
      StringBuilder stringBuilder0 = new StringBuilder(string4);
      StringBuilder stringBuilder1 = stringBuilder0.append((long) int1);
      int int2 = 0;
      char char0 = '|';
      StringBuilder stringBuilder2 = stringBuilder1.append((CharSequence) textNode2.TEXT_KEY);
      StringBuilder stringBuilder3 = stringBuilder1.insert(int2, char0);
      boolean boolean2 = TextNode.lastCharIsWhitespace(stringBuilder3);
  }

  @Test(timeout = 11000)
  public void test56()  throws Throwable  {
      String string0 = "^[:-[x1,iTf";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "FrPmFnX)ms";
      String string2 = textNode0.attr(string0);
      String string3 = textNode0.getWholeText();
      TextNode textNode1 = textNode0.text(textNode0.TEXT_KEY);
      textNode1.parentNode = (Node) textNode0;
      textNode0.text = string1;
      String string4 = "|[#$+^, ~*5tp";
      TextNode textNode2 = (TextNode)textNode0.removeAttr(string4);
      int int0 = 0;
      TextNode textNode3 = textNode2.text(textNode0.TEXT_KEY);
      // Undeclared exception!
      textNode0.toString();
  }

  @Test(timeout = 11000)
  public void test57()  throws Throwable  {
      String string0 = "^[:-[x1,iTf";
      TextNode textNode0 = TextNode.createFromEncoded(string0, string0);
      String string1 = "FrPmFnX)ms";
      String string2 = textNode0.attr(string0);
      String string3 = textNode0.getWholeText();
      TextNode textNode1 = textNode0.text(textNode0.TEXT_KEY);
      textNode1.parentNode = (Node) textNode0;
      textNode0.text = string1;
      String string4 = "|[#$+^, ~*5tp";
      Node node0 = textNode0.removeAttr(string4);
      List<Node> list0 = node0.childNodesCopy();
      node0.childNodes = list0;
      int int0 = 0;
      // Undeclared exception!
      try { 
        textNode0.splitText(int0);
      } catch(IndexOutOfBoundsException e) {
         //
         // Index: 1, Size: 0
         //
         verifyException("java.util.ArrayList", e);
      }
  }
}