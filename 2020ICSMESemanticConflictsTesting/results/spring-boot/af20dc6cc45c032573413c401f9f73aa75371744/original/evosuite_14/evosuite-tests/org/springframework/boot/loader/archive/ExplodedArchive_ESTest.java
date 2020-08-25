/*
 * This file was automatically generated by EvoSuite
 * Fri May 01 16:47:34 GMT 2020
 */

package org.springframework.boot.loader.archive;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.jar.Manifest;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.ExplodedArchive;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ExplodedArchive_ESTest extends ExplodedArchive_ESTest_scaffolding {

  @Test(timeout = 11000)
  public void test00()  throws Throwable  {
      String string0 = "Unable to open fallback handler";
      MockFile mockFile0 = new MockFile(string0, string0);
      ExplodedArchive explodedArchive0 = null;
      try {
        explodedArchive0 = new ExplodedArchive(mockFile0);
      } catch(IllegalArgumentException e) {
         //
         // Invalid source folder /server/study-execution/new-nimrod/nimrod-hunoroutput-test-dest/spring-boot/af20dc6cc45c032573413c401f9f73aa75371744/evosuite_14/Unable to open fallback handler/Unable to open fallback handler
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test01()  throws Throwable  {
      String string0 = "ceBphyr";
      File file0 = MockFile.createTempFile(string0, string0);
      ExplodedArchive explodedArchive0 = null;
      try {
        explodedArchive0 = new ExplodedArchive(file0);
      } catch(IllegalArgumentException e) {
         //
         // Invalid source folder /tmp/ceBphyr0ceBphyr
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test02()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0, string0);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("/META-INF/MANIFEST.MF");
      boolean boolean0 = FileSystemHandling.appendStringToFile(evoSuiteFile0, string0);
      boolean boolean1 = mockFile0.mkdirs();
      boolean boolean2 = true;
      boolean boolean3 = true;
      boolean boolean4 = false;
      boolean boolean5 = true;
      boolean boolean6 = true;
      Archive.EntryFilter archive_EntryFilter0 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      LinkedList<Archive> linkedList0 = new LinkedList<Archive>();
      boolean boolean7 = mockFile0.mkdirs();
      Manifest manifest0 = explodedArchive0.getManifest();
      boolean boolean8 = false;
      boolean boolean9 = false;
      Archive.EntryFilter archive_EntryFilter1 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(boolean8, boolean4, boolean9, boolean2, boolean7).when(archive_EntryFilter1).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      List<Archive> list0 = explodedArchive0.getNestedArchives(archive_EntryFilter1);
      boolean boolean10 = false;
      Archive.EntryFilter archive_EntryFilter2 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(boolean8, boolean2).when(archive_EntryFilter2).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      String string1 = explodedArchive0.toString();
      try { 
        explodedArchive0.getNestedArchives(archive_EntryFilter2);
      } catch(FileNotFoundException e) {
         //
         // /META-INF/MANIFEST.MF (Arquivo ou diret\u00F3rio n\u00E3o encontrado)
         //
         verifyException("java.util.zip.ZipFile", e);
      }
  }

  @Test(timeout = 11000)
  public void test03()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0);
      String string1 = "4n#k]8Q";
      String string2 = "org.springframework.boot.loader.archive.ExplodedArchive$1";
      String string3 = "org.springframework.boot.loader.archive.ExplodedArchive";
      File file0 = MockFile.createTempFile(string2, string3, (File) mockFile0);
      boolean boolean0 = false;
      boolean boolean1 = FileSystemHandling.shouldAllThrowIOExceptions();
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      Manifest manifest0 = explodedArchive0.getManifest();
      Manifest manifest1 = explodedArchive0.getManifest();
      URL uRL0 = mockFile0.toURL();
      Iterator<Archive.Entry> iterator0 = explodedArchive0.iterator();
      boolean boolean2 = true;
      Spliterator<Archive.Entry> spliterator0 = explodedArchive0.spliterator();
      Archive.EntryFilter archive_EntryFilter0 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(boolean2).when(archive_EntryFilter0).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      try { 
        explodedArchive0.getNestedArchives(archive_EntryFilter0);
      } catch(FileNotFoundException e) {
         //
         // /server/study-execution/new-nimrod/nimrod-hunoroutput-test-dest/spring-boot/af20dc6cc45c032573413c401f9f73aa75371744/evosuite_14/org.springframework.boot.loader.archive.ExplodedArchive$10org.springframework.boot.loader.archive.ExplodedArchive (Arquivo ou diret\u00F3rio n\u00E3o encontrado)
         //
         verifyException("java.util.zip.ZipFile", e);
      }
  }

  @Test(timeout = 11000)
  public void test04()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0, string0);
      boolean boolean0 = false;
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0, boolean0);
      Iterator<Archive.Entry> iterator0 = explodedArchive0.iterator();
      boolean boolean1 = mockFile0.mkdirs();
      boolean boolean2 = false;
      long long0 = (-1158L);
      boolean boolean3 = true;
      Archive.EntryFilter archive_EntryFilter0 = null;
      // Undeclared exception!
      try { 
        explodedArchive0.getNestedArchives(archive_EntryFilter0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test05()  throws Throwable  {
      File file0 = null;
      ExplodedArchive explodedArchive0 = null;
      try {
        explodedArchive0 = new ExplodedArchive(file0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test06()  throws Throwable  {
      String string0 = ")$]De";
      File file0 = MockFile.createTempFile(string0, string0);
      boolean boolean0 = false;
      ExplodedArchive explodedArchive0 = null;
      try {
        explodedArchive0 = new ExplodedArchive(file0, boolean0);
      } catch(IllegalArgumentException e) {
         //
         // Invalid source folder /tmp/)$]De0)$]De
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test07()  throws Throwable  {
      String string0 = "";
      String string1 = "";
      MockFile mockFile0 = new MockFile(string0, string1);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      Archive.EntryFilter archive_EntryFilter0 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(false, false, false, false, false).when(archive_EntryFilter0).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      List<Archive> list0 = explodedArchive0.getNestedArchives(archive_EntryFilter0);
  }

  @Test(timeout = 11000)
  public void test08()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0, string0);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      String string1 = explodedArchive0.toString();
  }

  @Test(timeout = 11000)
  public void test09()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0, string0);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      Manifest manifest0 = explodedArchive0.getManifest();
  }

  @Test(timeout = 11000)
  public void test10()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      Archive.Entry archive_Entry0 = null;
      // Undeclared exception!
      try { 
        explodedArchive0.getNestedArchive(archive_Entry0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test11()  throws Throwable  {
      String string0 = "-@9w}L_P.pTT";
      String string1 = null;
      File file0 = MockFile.createTempFile(string0, string1);
      ExplodedArchive explodedArchive0 = null;
      try {
        explodedArchive0 = new ExplodedArchive(file0);
      } catch(IllegalArgumentException e) {
         //
         // Invalid source folder /tmp/-@9w}L_P.pTT0.tmp
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test12()  throws Throwable  {
      File file0 = null;
      String string0 = "";
      MockFile mockFile0 = new MockFile(file0, string0);
      boolean boolean0 = false;
      String string1 = "wRr>wNHo0k3";
      boolean boolean1 = true;
      boolean boolean2 = false;
      boolean boolean3 = mockFile0.setReadable(boolean1, boolean2);
      File file1 = MockFile.createTempFile(string1, string0);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0, boolean0);
      String string2 = "JB%mE)RYoD-";
      String string3 = null;
      File file2 = MockFile.createTempFile(string2, string3);
      File file3 = mockFile0.getAbsoluteFile();
      Archive.EntryFilter archive_EntryFilter0 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      List<Archive> list0 = explodedArchive0.getNestedArchives(archive_EntryFilter0);
      URL uRL0 = explodedArchive0.getUrl();
      Manifest manifest0 = explodedArchive0.getManifest();
      boolean boolean4 = mockFile0.mkdir();
      String string4 = explodedArchive0.toString();
      String string5 = explodedArchive0.toString();
      URL uRL1 = explodedArchive0.getUrl();
      boolean boolean5 = mockFile0.setWritable(boolean0, boolean4);
      Manifest manifest1 = explodedArchive0.getManifest();
      URL uRL2 = explodedArchive0.getUrl();
      URL uRL3 = explodedArchive0.getUrl();
      Manifest manifest2 = explodedArchive0.getManifest();
      Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
      explodedArchive0.forEach(consumer0);
      Manifest manifest3 = explodedArchive0.getManifest();
      String string6 = explodedArchive0.toString();
      Archive.EntryFilter archive_EntryFilter1 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      List<Archive> list1 = explodedArchive0.getNestedArchives(archive_EntryFilter1);
      Manifest manifest4 = explodedArchive0.getManifest();
      Manifest manifest5 = explodedArchive0.getManifest();
      Manifest manifest6 = explodedArchive0.getManifest();
      Archive.Entry archive_Entry0 = null;
      // Undeclared exception!
      try { 
        explodedArchive0.getNestedArchive(archive_Entry0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test13()  throws Throwable  {
      String string0 = "NESTED_DIRECTORY";
      String string1 = "file";
      File file0 = MockFile.createTempFile(string0, string1);
      MockFile mockFile0 = new MockFile(file0, string1);
      boolean boolean0 = mockFile0.createNewFile();
      boolean boolean1 = true;
      boolean boolean2 = mockFile0.mkdirs();
      ExplodedArchive explodedArchive0 = null;
      try {
        explodedArchive0 = new ExplodedArchive(mockFile0, boolean1);
      } catch(IllegalArgumentException e) {
         //
         // Invalid source folder /tmp/NESTED_DIRECTORY0file/file
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test14()  throws Throwable  {
      File file0 = null;
      boolean boolean0 = false;
      ExplodedArchive explodedArchive0 = null;
      try {
        explodedArchive0 = new ExplodedArchive(file0, boolean0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test15()  throws Throwable  {
      File file0 = null;
      String string0 = "";
      MockFile mockFile0 = new MockFile(file0, string0);
      boolean boolean0 = false;
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0, boolean0);
      Iterator<Archive.Entry> iterator0 = explodedArchive0.iterator();
      Spliterator<Archive.Entry> spliterator0 = explodedArchive0.spliterator();
      URL uRL0 = explodedArchive0.getUrl();
      String string1 = explodedArchive0.toString();
      Archive.Entry archive_Entry0 = mock(Archive.Entry.class, new ViolatedAssumptionAnswer());
      Iterator<Archive.Entry> iterator1 = explodedArchive0.iterator();
      // Undeclared exception!
      try { 
        explodedArchive0.getNestedArchive(archive_Entry0);
      } catch(ClassCastException e) {
         //
         // org.springframework.boot.loader.archive.Archive$Entry$MockitoMock$63913484 cannot be cast to org.springframework.boot.loader.archive.ExplodedArchive$FileEntry
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test16()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0);
      boolean boolean0 = mockFile0.mkdir();
      String string1 = "4n#k]8Q";
      String string2 = "org.springframework.boot.loader.archive.ExplodedArchive$1";
      String string3 = "org.springframework.boot.loader.archive.ExplodedArchive";
      File file0 = MockFile.createTempFile(string2, string3, (File) mockFile0);
      File file1 = MockFile.createTempFile(string1, string1, (File) mockFile0);
      boolean boolean1 = true;
      boolean boolean2 = mockFile0.setReadable(boolean1);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      Manifest manifest0 = explodedArchive0.getManifest();
      Manifest manifest1 = explodedArchive0.getManifest();
      URL uRL0 = mockFile0.toURL();
      Iterator<Archive.Entry> iterator0 = explodedArchive0.iterator();
      boolean boolean3 = true;
      boolean boolean4 = mockFile0.setWritable(boolean3);
      Archive.EntryFilter archive_EntryFilter0 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(false, false).when(archive_EntryFilter0).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      List<Archive> list0 = explodedArchive0.getNestedArchives(archive_EntryFilter0);
      Archive.EntryFilter archive_EntryFilter1 = null;
      URL uRL1 = mockFile0.toURL();
      Path path0 = mockFile0.toPath();
      // Undeclared exception!
      try { 
        explodedArchive0.getNestedArchives(archive_EntryFilter1);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test17()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0, string0);
      boolean boolean0 = false;
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0, boolean0);
      Iterator<Archive.Entry> iterator0 = explodedArchive0.iterator();
      boolean boolean1 = mockFile0.mkdirs();
      Archive.EntryFilter archive_EntryFilter0 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(false, false, false).when(archive_EntryFilter0).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      List<Archive> list0 = explodedArchive0.getNestedArchives(archive_EntryFilter0);
      String string1 = explodedArchive0.toString();
      Spliterator<Archive.Entry> spliterator0 = explodedArchive0.spliterator();
      long long0 = (-1158L);
      // Undeclared exception!
      try { 
        mockFile0.setLastModified(long0);
      } catch(IllegalArgumentException e) {
         //
         // Negative time
         //
         verifyException("org.evosuite.runtime.mock.java.io.MockFile", e);
      }
  }

  @Test(timeout = 11000)
  public void test18()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0, string0);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      boolean boolean0 = true;
      boolean boolean1 = true;
      boolean boolean2 = false;
      boolean boolean3 = false;
      Archive.EntryFilter archive_EntryFilter0 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(boolean0, boolean0, boolean1, boolean2, boolean3).when(archive_EntryFilter0).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      List<Archive> list0 = explodedArchive0.getNestedArchives(archive_EntryFilter0);
      boolean boolean4 = true;
      boolean boolean5 = true;
      boolean boolean6 = true;
      Archive.EntryFilter archive_EntryFilter1 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(boolean2, boolean4, boolean5, boolean6, boolean3).when(archive_EntryFilter1).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      List<Archive> list1 = explodedArchive0.getNestedArchives(archive_EntryFilter1);
      boolean boolean7 = mockFile0.mkdirs();
      Manifest manifest0 = explodedArchive0.getManifest();
      Iterator<Archive.Entry> iterator0 = explodedArchive0.iterator();
      boolean boolean8 = true;
      boolean boolean9 = false;
      boolean boolean10 = false;
      Archive.EntryFilter archive_EntryFilter2 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(boolean8, boolean9, boolean10, boolean0, boolean7).when(archive_EntryFilter2).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      List<Archive> list2 = explodedArchive0.getNestedArchives(archive_EntryFilter2);
      String string1 = explodedArchive0.toString();
      boolean boolean11 = true;
      boolean boolean12 = false;
      Archive.EntryFilter archive_EntryFilter3 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(boolean8, boolean9, boolean8, boolean11, boolean12).when(archive_EntryFilter3).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      String string2 = explodedArchive0.toString();
      List<Archive> list3 = explodedArchive0.getNestedArchives(archive_EntryFilter3);
      Manifest manifest1 = explodedArchive0.getManifest();
      Archive.Entry archive_Entry0 = mock(Archive.Entry.class, new ViolatedAssumptionAnswer());
      // Undeclared exception!
      try { 
        explodedArchive0.getNestedArchive(archive_Entry0);
      } catch(ClassCastException e) {
         //
         // org.springframework.boot.loader.archive.Archive$Entry$MockitoMock$63913484 cannot be cast to org.springframework.boot.loader.archive.ExplodedArchive$FileEntry
         //
         verifyException("org.springframework.boot.loader.archive.ExplodedArchive", e);
      }
  }

  @Test(timeout = 11000)
  public void test19()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0);
      boolean boolean0 = mockFile0.mkdir();
      String string1 = "4n#k]8Q";
      String string2 = "org.springframework.boot.loader.archive.ExplodedArchive$1";
      String string3 = "org.springframework.boot.loader.archive.ExplodedArchive";
      File file0 = MockFile.createTempFile(string2, string3, (File) mockFile0);
      File file1 = MockFile.createTempFile(string1, string1, (File) mockFile0);
      boolean boolean1 = true;
      boolean boolean2 = mockFile0.setReadable(boolean1);
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      Manifest manifest0 = explodedArchive0.getManifest();
      Manifest manifest1 = explodedArchive0.getManifest();
      URL uRL0 = mockFile0.toURL();
      Iterator<Archive.Entry> iterator0 = explodedArchive0.iterator();
      boolean boolean3 = true;
      boolean boolean4 = mockFile0.setWritable(boolean3);
      boolean boolean5 = true;
      boolean boolean6 = true;
      Archive.EntryFilter archive_EntryFilter0 = mock(Archive.EntryFilter.class, new ViolatedAssumptionAnswer());
      doReturn(boolean5).when(archive_EntryFilter0).matches(any(org.springframework.boot.loader.archive.Archive.Entry.class));
      try { 
        explodedArchive0.getNestedArchives(archive_EntryFilter0);
      } catch(FileNotFoundException e) {
         //
         // /server/study-execution/new-nimrod/nimrod-hunoroutput-test-dest/spring-boot/af20dc6cc45c032573413c401f9f73aa75371744/evosuite_14/4n#k]8Q14n#k]8Q (Arquivo ou diret\u00F3rio n\u00E3o encontrado)
         //
         verifyException("java.util.zip.ZipFile", e);
      }
  }

  @Test(timeout = 11000)
  public void test20()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0);
      EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("META-INF/MANIFEST.MF");
      boolean boolean0 = FileSystemHandling.createFolder(evoSuiteFile0);
      String string1 = "4n#k]8Q";
      String string2 = "org.springframework.boot.loader.archive.ExplodedArchive$1";
      String string3 = "org.springframework.boot.loader.archive.ExplodedArchive";
      EvoSuiteFile evoSuiteFile1 = new EvoSuiteFile("org.springframework.boot.loader.archive.ExplodedArchive$10org.springframework.boot.loader.archive.ExplodedArchive");
      boolean boolean1 = FileSystemHandling.createFolder(evoSuiteFile1);
      File file0 = MockFile.createTempFile(string2, string3, (File) mockFile0);
      boolean boolean2 = true;
      boolean boolean3 = FileSystemHandling.shouldAllThrowIOExceptions();
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      try { 
        explodedArchive0.getManifest();
      } catch(FileNotFoundException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.evosuite.runtime.mock.java.io.MockFileInputStream", e);
      }
  }

  @Test(timeout = 11000)
  public void test21()  throws Throwable  {
      String string0 = "";
      MockFile mockFile0 = new MockFile(string0);
      boolean boolean0 = mockFile0.mkdirs();
      EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("META-INF/MANIFEST.MF");
      String string1 = "af20dc6cc45c032573413c401f9f73aa75371744";
      boolean boolean1 = FileSystemHandling.appendStringToFile(evoSuiteFile0, string1);
      String string2 = "4n#k]8Q";
      String string3 = "org.springframework.boot.loader.archive.ExplodedArchive$1";
      String string4 = "org.springframework.boot.loader.archive.ExplodedArchive";
      File file0 = MockFile.createTempFile(string3, string4, (File) mockFile0);
      boolean boolean2 = true;
      boolean boolean3 = FileSystemHandling.shouldAllThrowIOExceptions();
      ExplodedArchive explodedArchive0 = new ExplodedArchive(mockFile0);
      try { 
        explodedArchive0.getManifest();
      } catch(IOException e) {
         //
         // Simulated IOException
         //
         verifyException("org.evosuite.runtime.vfs.VirtualFileSystem", e);
      }
  }
}