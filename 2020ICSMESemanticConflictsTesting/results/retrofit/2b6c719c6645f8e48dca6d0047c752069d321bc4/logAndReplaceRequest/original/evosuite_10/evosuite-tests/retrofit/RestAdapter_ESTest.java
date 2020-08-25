/*
 * This file was automatically generated by EvoSuite
 * Tue May 26 07:05:57 GMT 2020
 */

package retrofit;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import android.os.WorkSource;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.DecimalNode;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.routing.RouteInfo;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.System;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;
import retrofit.ErrorHandler;
import retrofit.Profiler;
import retrofit.RequestInterceptor;
import retrofit.RequestInterceptorTape;
import retrofit.RestAdapter;
import retrofit.Server;
import retrofit.android.AndroidLog;
import retrofit.client.ApacheClient;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.converter.JacksonConverter;
import retrofit.converter.ProtoConverter;
import retrofit.converter.WireConverter;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedByteArray;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class RestAdapter_ESTest extends RestAdapter_ESTest_scaffolding {

  @Test(timeout = 11000)
  public void test00()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      WireConverter wireConverter0 = new WireConverter();
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setConverter(wireConverter0);
      String string0 = "Z";
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setServer(string0);
      int int0 = 257;
      ForkJoinPool forkJoinPool0 = new ForkJoinPool(int0);
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setExecutors(forkJoinPool0, forkJoinPool0);
      RestAdapter.Builder restAdapter_Builder4 = restAdapter_Builder2.setServer(string0);
      ErrorHandler errorHandler0 = mock(ErrorHandler.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder5 = restAdapter_Builder2.setErrorHandler(errorHandler0);
      int int1 = 512;
      ForkJoinPool forkJoinPool1 = new ForkJoinPool(int1);
      RestAdapter.Builder restAdapter_Builder6 = restAdapter_Builder5.setExecutors(forkJoinPool1, forkJoinPool1);
      RestAdapter restAdapter0 = restAdapter_Builder2.build();
      String string1 = "";
      Server server0 = new Server(string0, string1);
      RestAdapter.Builder restAdapter_Builder7 = restAdapter_Builder3.setServer(server0);
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.HEADERS;
      restAdapter0.setLogLevel(restAdapter_LogLevel0);
      Class<WorkSource> class0 = WorkSource.class;
      // Undeclared exception!
      try { 
        restAdapter0.create(class0);
      } catch(IllegalArgumentException e) {
         //
         // Only interface endpoint definitions are supported.
         //
         verifyException("retrofit.RestAdapter", e);
      }
  }

  @Test(timeout = 11000)
  public void test01()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      Profiler<Integer> profiler0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder0.setProfiler(profiler0);
      } catch(NullPointerException e) {
         //
         // Profiler may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test02()  throws Throwable  {
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.HEADERS;
      boolean boolean0 = restAdapter_LogLevel0.log();
  }

  @Test(timeout = 11000)
  public void test03()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      RestAdapter.Log restAdapter_Log0 = RestAdapter.Log.NONE;
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setLog(restAdapter_Log0);
      Profiler<RouteInfo.TunnelType> profiler0 = (Profiler<RouteInfo.TunnelType>) mock(Profiler.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setProfiler(profiler0);
      Client.Provider client_Provider0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder2.setClient(client_Provider0);
      } catch(NullPointerException e) {
         //
         // Client provider may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test04()  throws Throwable  {
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.NONE;
      String string0 = "FULL";
      RestAdapter.LogLevel restAdapter_LogLevel1 = RestAdapter.LogLevel.valueOf(string0);
      boolean boolean0 = restAdapter_LogLevel0.log();
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      String string1 = null;
      Server server0 = new Server(string1);
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setServer(server0);
      ApacheClient apacheClient0 = new ApacheClient();
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder0.setClient((Client) apacheClient0);
      RestAdapter.Log restAdapter_Log0 = RestAdapter.Log.NONE;
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setLog(restAdapter_Log0);
      Client.Provider client_Provider0 = mock(Client.Provider.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder4 = restAdapter_Builder3.setClient(client_Provider0);
      RestAdapter.LogLevel restAdapter_LogLevel2 = RestAdapter.LogLevel.HEADERS;
      RestAdapter.Builder restAdapter_Builder5 = restAdapter_Builder4.setLogLevel(restAdapter_LogLevel2);
      RestAdapter.Builder restAdapter_Builder6 = restAdapter_Builder3.setLogLevel(restAdapter_LogLevel2);
      RestAdapter restAdapter0 = restAdapter_Builder5.build();
      RestAdapter.LogLevel restAdapter_LogLevel3 = RestAdapter.LogLevel.HEADERS;
      restAdapter0.setLogLevel(restAdapter_LogLevel3);
      int int0 = 0;
      ThreadFactory threadFactory0 = mock(ThreadFactory.class, new ViolatedAssumptionAnswer());
      ScheduledThreadPoolExecutor scheduledThreadPoolExecutor0 = new ScheduledThreadPoolExecutor(int0, threadFactory0);
      RestAdapter.Builder restAdapter_Builder7 = restAdapter_Builder3.setExecutors(scheduledThreadPoolExecutor0, scheduledThreadPoolExecutor0);
  }

  @Test(timeout = 11000)
  public void test05()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      RequestInterceptor requestInterceptor0 = RequestInterceptor.NONE;
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setRequestInterceptor(requestInterceptor0);
      String string0 = "k7\\<s)E{BN}Y#oXU@q";
      Server server0 = new Server(string0, string0);
      // Undeclared exception!
      try { 
        restAdapter_Builder0.build();
      } catch(IllegalArgumentException e) {
         //
         // Server may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test06()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      Converter converter0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder0.setConverter(converter0);
      } catch(NullPointerException e) {
         //
         // Converter may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test07()  throws Throwable  {
      RestAdapter.Log restAdapter_Log0 = RestAdapter.Log.NONE;
      String string0 = "eD-6V\"";
      restAdapter_Log0.log(string0);
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      UrlConnectionClient urlConnectionClient0 = new UrlConnectionClient();
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setClient((Client) urlConnectionClient0);
      Profiler<TypedByteArray> profiler0 = (Profiler<TypedByteArray>) mock(Profiler.class, new ViolatedAssumptionAnswer());
      ErrorHandler errorHandler0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder0.setErrorHandler(errorHandler0);
      } catch(NullPointerException e) {
         //
         // Error handler may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test08()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      RequestInterceptor requestInterceptor0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder0.setRequestInterceptor(requestInterceptor0);
      } catch(NullPointerException e) {
         //
         // Request interceptor may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test09()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      HttpClient httpClient0 = null;
      ApacheClient apacheClient0 = new ApacheClient(httpClient0);
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setClient((Client) apacheClient0);
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.BASIC;
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setLogLevel(restAdapter_LogLevel0);
      ErrorHandler errorHandler0 = mock(ErrorHandler.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setErrorHandler(errorHandler0);
      JsonFactory jsonFactory0 = new JsonFactory();
      ObjectMapper objectMapper0 = new ObjectMapper(jsonFactory0);
      JacksonConverter jacksonConverter0 = new JacksonConverter(objectMapper0);
      RestAdapter.Builder restAdapter_Builder4 = restAdapter_Builder3.setConverter(jacksonConverter0);
      RestAdapter.Builder restAdapter_Builder5 = restAdapter_Builder4.setServer(jsonFactory0.FORMAT_NAME_JSON);
      Executor executor0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder5.setExecutors(executor0, executor0);
      } catch(NullPointerException e) {
         //
         // HTTP executor may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test10()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      ForkJoinPool forkJoinPool0 = new ForkJoinPool();
      Thread.UncaughtExceptionHandler thread_UncaughtExceptionHandler0 = forkJoinPool0.getUncaughtExceptionHandler();
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setExecutors(forkJoinPool0, forkJoinPool0);
      Client.Provider client_Provider0 = mock(Client.Provider.class, new ViolatedAssumptionAnswer());
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.BASIC;
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setLogLevel(restAdapter_LogLevel0);
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder1.setClient(client_Provider0);
      RequestInterceptor requestInterceptor0 = RequestInterceptor.NONE;
      RestAdapter.Builder restAdapter_Builder4 = restAdapter_Builder1.setRequestInterceptor(requestInterceptor0);
      RestAdapter.Log restAdapter_Log0 = RestAdapter.Log.NONE;
      String string0 = ", second 0x";
      Server server0 = new Server(string0, string0);
      RestAdapter.Builder restAdapter_Builder5 = restAdapter_Builder0.setServer(server0);
      RestAdapter.Builder restAdapter_Builder6 = restAdapter_Builder0.setLog(restAdapter_Log0);
      Client.Provider client_Provider1 = mock(Client.Provider.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder7 = restAdapter_Builder6.setClient(client_Provider1);
      Profiler<Object> profiler0 = (Profiler<Object>) mock(Profiler.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder8 = restAdapter_Builder0.setProfiler(profiler0);
      RestAdapter.LogLevel[] restAdapter_LogLevelArray0 = RestAdapter.LogLevel.values();
      String string1 = "";
      restAdapter_Log0.log(string1);
      RestAdapter.Builder restAdapter_Builder9 = new RestAdapter.Builder();
      RestAdapter restAdapter0 = restAdapter_Builder2.build();
      Class<Delayed> class0 = Delayed.class;
      Class<HashSet> class1 = restAdapter0.create((Class<Class<HashSet>>) class0);
      RestAdapter.LogLevel[] restAdapter_LogLevelArray1 = RestAdapter.LogLevel.values();
  }

  @Test(timeout = 11000)
  public void test11()  throws Throwable  {
      RestAdapter.LogLevel[] restAdapter_LogLevelArray0 = RestAdapter.LogLevel.values();
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.FULL;
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setLogLevel(restAdapter_LogLevel0);
      RestAdapter.LogLevel restAdapter_LogLevel1 = RestAdapter.LogLevel.BASIC;
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setLogLevel(restAdapter_LogLevel1);
      WireConverter wireConverter0 = new WireConverter();
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder1.setConverter(wireConverter0);
      RequestInterceptor requestInterceptor0 = RequestInterceptor.NONE;
      RestAdapter.Builder restAdapter_Builder4 = restAdapter_Builder3.setRequestInterceptor(requestInterceptor0);
      String string0 = "";
      Server server0 = new Server(string0);
      RestAdapter.Builder restAdapter_Builder5 = restAdapter_Builder4.setServer(server0);
      RestAdapter.LogLevel restAdapter_LogLevel2 = RestAdapter.LogLevel.NONE;
      RestAdapter.Builder restAdapter_Builder6 = restAdapter_Builder5.setLogLevel(restAdapter_LogLevel2);
      int int0 = 972;
      ScheduledThreadPoolExecutor scheduledThreadPoolExecutor0 = new ScheduledThreadPoolExecutor(int0);
      RestAdapter.Builder restAdapter_Builder7 = restAdapter_Builder5.setExecutors(scheduledThreadPoolExecutor0, scheduledThreadPoolExecutor0);
      // Undeclared exception!
      try { 
        restAdapter_Builder7.setServer(string0);
      } catch(NullPointerException e) {
         //
         // Server may not be blank.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test12()  throws Throwable  {
      RestAdapter.Log restAdapter_Log0 = RestAdapter.Log.NONE;
      String string0 = "";
      restAdapter_Log0.log(string0);
  }

  @Test(timeout = 11000)
  public void test13()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      WireConverter wireConverter0 = new WireConverter();
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setConverter(wireConverter0);
      RequestInterceptorTape requestInterceptorTape0 = new RequestInterceptorTape();
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder0.setRequestInterceptor(requestInterceptorTape0);
  }

  @Test(timeout = 11000)
  public void test14()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      RestAdapter.Builder restAdapter_Builder1 = new RestAdapter.Builder();
      // Undeclared exception!
      try { 
        restAdapter_Builder0.build();
      } catch(IllegalArgumentException e) {
         //
         // Server may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test15()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      String string0 = "Rs8!Z`b@]";
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setServer(string0);
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.FULL;
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setLogLevel(restAdapter_LogLevel0);
      RequestInterceptor requestInterceptor0 = RequestInterceptor.NONE;
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setRequestInterceptor(requestInterceptor0);
      RestAdapter restAdapter0 = restAdapter_Builder3.build();
      RestAdapter.LogLevel restAdapter_LogLevel1 = restAdapter0.getLogLevel();
  }

  @Test(timeout = 11000)
  public void test16()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      Gson gson0 = new Gson();
      String string0 = "sy";
      GsonConverter gsonConverter0 = new GsonConverter(gson0, string0);
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setConverter(gsonConverter0);
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setServer(string0);
      Profiler<String> profiler0 = (Profiler<String>) mock(Profiler.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setProfiler(profiler0);
      RestAdapter restAdapter0 = restAdapter_Builder3.build();
      Class<DecimalNode> class0 = DecimalNode.class;
      // Undeclared exception!
      try { 
        restAdapter0.create(class0);
      } catch(IllegalArgumentException e) {
         //
         // Only interface endpoint definitions are supported.
         //
         verifyException("retrofit.RestAdapter", e);
      }
  }

  @Test(timeout = 11000)
  public void test17()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      String string0 = "";
      AndroidLog androidLog0 = new AndroidLog(string0);
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setLog(androidLog0);
      // Undeclared exception!
      try { 
        restAdapter_Builder1.setServer(string0);
      } catch(NullPointerException e) {
         //
         // Server may not be blank.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test18()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      ErrorHandler errorHandler0 = mock(ErrorHandler.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setErrorHandler(errorHandler0);
      Client.Provider client_Provider0 = mock(Client.Provider.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setClient(client_Provider0);
      String string0 = null;
      Server server0 = new Server(string0);
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setServer(server0);
      Profiler<String> profiler0 = (Profiler<String>) mock(Profiler.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder4 = restAdapter_Builder3.setProfiler(profiler0);
      RestAdapter restAdapter0 = restAdapter_Builder4.build();
      Class<Header> class0 = Header.class;
      // Undeclared exception!
      try { 
        restAdapter0.create(class0);
      } catch(IllegalArgumentException e) {
         //
         // Only interface endpoint definitions are supported.
         //
         verifyException("retrofit.RestAdapter", e);
      }
  }

  @Test(timeout = 11000)
  public void test19()  throws Throwable  {
      RestAdapter.LogLevel[] restAdapter_LogLevelArray0 = RestAdapter.LogLevel.values();
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      String string0 = "f3RCZ?";
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setServer(string0);
      RequestInterceptor requestInterceptor0 = RequestInterceptor.NONE;
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setRequestInterceptor(requestInterceptor0);
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.FULL;
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setLogLevel(restAdapter_LogLevel0);
      RestAdapter.Log restAdapter_Log0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder3.setLog(restAdapter_Log0);
      } catch(NullPointerException e) {
         //
         // Log may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test20()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      RestAdapter.Log restAdapter_Log0 = RestAdapter.Log.NONE;
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setLog(restAdapter_Log0);
      Executor executor0 = null;
      int int0 = 3;
      ForkJoinPool forkJoinPool0 = new ForkJoinPool(int0);
      // Undeclared exception!
      try { 
        restAdapter_Builder1.setExecutors(executor0, forkJoinPool0);
      } catch(NullPointerException e) {
         //
         // HTTP executor may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test21()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      HttpClient httpClient0 = null;
      ApacheClient apacheClient0 = new ApacheClient(httpClient0);
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setClient((Client) apacheClient0);
      int int0 = 159;
      long long0 = 153L;
      TimeUnit timeUnit0 = TimeUnit.MICROSECONDS;
      ScheduledThreadPoolExecutor scheduledThreadPoolExecutor0 = new ScheduledThreadPoolExecutor(int0);
      BlockingQueue<Runnable> blockingQueue0 = scheduledThreadPoolExecutor0.getQueue();
      ThreadFactory threadFactory0 = mock(ThreadFactory.class, new ViolatedAssumptionAnswer());
      ThreadPoolExecutor.AbortPolicy threadPoolExecutor_AbortPolicy0 = new ThreadPoolExecutor.AbortPolicy();
      ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(int0, int0, long0, timeUnit0, blockingQueue0, threadFactory0, threadPoolExecutor_AbortPolicy0);
      ForkJoinPool forkJoinPool0 = ForkJoinTask.getPool();
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setExecutors(threadPoolExecutor0, forkJoinPool0);
      String string0 = null;
      Server server0 = new Server(string0);
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setServer(server0);
      RequestInterceptor requestInterceptor0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder3.setRequestInterceptor(requestInterceptor0);
      } catch(NullPointerException e) {
         //
         // Request interceptor may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test22()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      Server server0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder0.setServer(server0);
      } catch(NullPointerException e) {
         //
         // Server may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test23()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      Client client0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder0.setClient(client0);
      } catch(NullPointerException e) {
         //
         // Client may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test24()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      ProtoConverter protoConverter0 = new ProtoConverter();
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setConverter(protoConverter0);
      String string0 = "{7";
      String string1 = null;
      Server server0 = new Server(string0, string1);
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setServer(server0);
      RequestInterceptor requestInterceptor0 = RequestInterceptor.NONE;
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setRequestInterceptor(requestInterceptor0);
      ErrorHandler errorHandler0 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder3.setErrorHandler(errorHandler0);
      } catch(NullPointerException e) {
         //
         // Error handler may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }

  @Test(timeout = 11000)
  public void test25()  throws Throwable  {
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      Client.Provider client_Provider0 = mock(Client.Provider.class, new ViolatedAssumptionAnswer());
      EvoSuiteFile evoSuiteFile0 = null;
      byte[] byteArray0 = new byte[5];
      byte byte0 = (byte)65;
      byteArray0[0] = byte0;
      byte byte1 = (byte) (-94);
      byteArray0[1] = byte1;
      byte byte2 = (byte)16;
      byteArray0[2] = byte2;
      byte byte3 = (byte)39;
      byteArray0[3] = byte3;
      byte byte4 = (byte)10;
      byteArray0[4] = byte4;
      boolean boolean0 = FileSystemHandling.appendDataToFile(evoSuiteFile0, byteArray0);
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setClient(client_Provider0);
      RequestInterceptor requestInterceptor0 = RequestInterceptor.NONE;
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder1.setRequestInterceptor(requestInterceptor0);
      String string0 = "android.content.pm.PermissionInfo";
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setServer(string0);
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.NONE;
      Client.Provider client_Provider1 = mock(Client.Provider.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder4 = restAdapter_Builder1.setClient(client_Provider1);
      RestAdapter.Builder restAdapter_Builder5 = restAdapter_Builder0.setLogLevel(restAdapter_LogLevel0);
      RestAdapter restAdapter0 = restAdapter_Builder3.build();
      String string1 = "";
      String string2 = "";
      Server server0 = new Server(string1, string2);
      RestAdapter.Builder restAdapter_Builder6 = restAdapter_Builder1.setServer(server0);
      Class<Delayed> class0 = Delayed.class;
      Delayed delayed0 = restAdapter0.create(class0);
      RestAdapter.LogLevel restAdapter_LogLevel1 = RestAdapter.LogLevel.FULL;
      restAdapter0.setLogLevel(restAdapter_LogLevel1);
      boolean boolean1 = restAdapter_LogLevel1.log();
      String string3 = "55}gb}n|-1Wq/h>&";
      RestAdapter.Builder restAdapter_Builder7 = restAdapter_Builder1.setServer(string3);
      restAdapter0.setLogLevel(restAdapter_LogLevel1);
      RestAdapter.Log restAdapter_Log0 = RestAdapter.Log.NONE;
      String string4 = "android.intent.action.TIME_TICK";
      restAdapter_Log0.log(string4);
      Profiler<Integer> profiler0 = (Profiler<Integer>) mock(Profiler.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder8 = restAdapter_Builder7.setProfiler(profiler0);
      long long0 = (-2854L);
      System.setCurrentTimeMillis(long0);
  }

  @Test(timeout = 11000)
  public void test26()  throws Throwable  {
      RestAdapter.LogLevel restAdapter_LogLevel0 = RestAdapter.LogLevel.NONE;
      String string0 = "FULL";
      RestAdapter.LogLevel restAdapter_LogLevel1 = RestAdapter.LogLevel.valueOf(string0);
      boolean boolean0 = restAdapter_LogLevel0.log();
      RestAdapter.Builder restAdapter_Builder0 = new RestAdapter.Builder();
      String string1 = null;
      Server server0 = new Server(string1);
      RestAdapter.Builder restAdapter_Builder1 = restAdapter_Builder0.setServer(server0);
      ApacheClient apacheClient0 = new ApacheClient();
      RestAdapter.Builder restAdapter_Builder2 = restAdapter_Builder0.setClient((Client) apacheClient0);
      RestAdapter.Log restAdapter_Log0 = RestAdapter.Log.NONE;
      RestAdapter.Builder restAdapter_Builder3 = restAdapter_Builder2.setLog(restAdapter_Log0);
      Client.Provider client_Provider0 = mock(Client.Provider.class, new ViolatedAssumptionAnswer());
      RestAdapter.Builder restAdapter_Builder4 = restAdapter_Builder3.setClient(client_Provider0);
      RestAdapter.LogLevel restAdapter_LogLevel2 = RestAdapter.LogLevel.HEADERS;
      RestAdapter.Builder restAdapter_Builder5 = restAdapter_Builder4.setLogLevel(restAdapter_LogLevel2);
      RestAdapter.LogLevel restAdapter_LogLevel3 = null;
      // Undeclared exception!
      try { 
        restAdapter_Builder3.setLogLevel(restAdapter_LogLevel3);
      } catch(NullPointerException e) {
         //
         // Log level may not be null.
         //
         verifyException("retrofit.RestAdapter$Builder", e);
      }
  }
}