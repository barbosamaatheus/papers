/*
 * This file was automatically generated by EvoSuite
 * Tue May 26 03:36:28 GMT 2020
 */

package com.github.javafaker;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.github.javafaker.Address;
import com.github.javafaker.App;
import com.github.javafaker.Book;
import com.github.javafaker.Business;
import com.github.javafaker.Code;
import com.github.javafaker.Color;
import com.github.javafaker.Company;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import com.github.javafaker.Finance;
import com.github.javafaker.Hacker;
import com.github.javafaker.Internet;
import com.github.javafaker.Lorem;
import com.github.javafaker.Name;
import com.github.javafaker.Number;
import com.github.javafaker.Options;
import com.github.javafaker.PhoneNumber;
import java.util.Locale;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.util.MockRandom;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Faker_ESTest extends Faker_ESTest_scaffolding {

  @Test(timeout = 11000)
  public void test00()  throws Throwable  {
      MockRandom mockRandom0 = new MockRandom();
      Faker faker0 = new Faker(mockRandom0);
  }

  @Test(timeout = 11000)
  public void test01()  throws Throwable  {
      Locale locale0 = Locale.CANADA;
      Faker faker0 = new Faker(locale0);
  }

  @Test(timeout = 11000)
  public void test02()  throws Throwable  {
      Faker faker0 = new Faker();
  }

  @Test(timeout = 11000)
  public void test03()  throws Throwable  {
      MockRandom mockRandom0 = new MockRandom();
      long long0 = 0L;
      LongStream longStream0 = mockRandom0.longs(long0);
      Faker faker0 = new Faker(mockRandom0);
  }

  @Test(timeout = 11000)
  public void test04()  throws Throwable  {
      Locale locale0 = null;
      Faker faker0 = null;
      try {
        faker0 = new Faker(locale0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.github.javafaker.service.FakeValuesService", e);
      }
  }

  @Test(timeout = 11000)
  public void test05()  throws Throwable  {
      Locale locale0 = Locale.ITALIAN;
      char char0 = 'x';
      String string0 = locale0.getExtension(char0);
      long long0 = 91L;
      Locale locale1 = Locale.GERMANY;
      String string1 = locale1.getDisplayVariant(locale0);
      String string2 = locale0.getDisplayLanguage(locale1);
      MockRandom mockRandom0 = new MockRandom(long0);
      String string3 = locale0.getDisplayCountry();
      DoubleStream doubleStream0 = mockRandom0.doubles(long0);
      LongStream longStream0 = mockRandom0.longs(long0);
      int int0 = mockRandom0.nextInt();
      Faker faker0 = new Faker(locale0, mockRandom0);
  }

  @Test(timeout = 11000)
  public void test06()  throws Throwable  {
      Locale locale0 = Locale.PRC;
      MockRandom mockRandom0 = new MockRandom();
      Faker faker0 = new Faker(locale0, mockRandom0);
  }

  @Test(timeout = 11000)
  public void test07()  throws Throwable  {
      Locale locale0 = Locale.TRADITIONAL_CHINESE;
      Faker faker0 = new Faker(locale0);
  }

  @Test(timeout = 11000)
  public void test08()  throws Throwable  {
      String string0 = "address.street_address";
      Locale locale0 = new Locale(string0, string0);
      MockRandom mockRandom0 = new MockRandom();
      Faker faker0 = null;
      try {
        faker0 = new Faker(locale0, mockRandom0);
      } catch(RuntimeException e) {
         //
         // address.street_address_ADDRESS.STREET_ADDRESS could not be found, does not have a corresponding yaml file
         //
         verifyException("com.github.javafaker.service.FakeValuesService", e);
      }
  }

  @Test(timeout = 11000)
  public void test09()  throws Throwable  {
      String string0 = "lHWdf$3F'";
      Locale locale0 = new Locale(string0, string0);
      Faker faker0 = null;
      try {
        faker0 = new Faker(locale0);
      } catch(RuntimeException e) {
         //
         // lhwdf$3f'_LHWDF$3F' could not be found, does not have a corresponding yaml file
         //
         verifyException("com.github.javafaker.service.FakeValuesService", e);
      }
  }

  @Test(timeout = 11000)
  public void test10()  throws Throwable  {
      Locale locale0 = null;
      MockRandom mockRandom0 = new MockRandom();
      Faker faker0 = null;
      try {
        faker0 = new Faker(locale0, mockRandom0);
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.github.javafaker.service.FakeValuesService", e);
      }
  }
}