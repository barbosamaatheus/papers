/*
 * This file was automatically generated by EvoSuite
 * Sun May 03 07:23:09 GMT 2020
 */

package org.jboss.netty.handler.codec.frame;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.LinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.buffer.ByteBufferBackedChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.CompositeChannelBuffer;
import org.jboss.netty.buffer.DirectChannelBufferFactory;
import org.jboss.netty.buffer.DuplicatedChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.buffer.LittleEndianHeapChannelBuffer;
import org.jboss.netty.buffer.ReadOnlyChannelBuffer;
import org.jboss.netty.buffer.SlicedChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.UpstreamChannelStateEvent;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class LengthFieldBasedFrameDecoder_ESTest extends LengthFieldBasedFrameDecoder_ESTest_scaffolding {

  @Test(timeout = 11000)
  public void test00()  throws Throwable  {
      int int0 = 0;
      int int1 = (-15);
      int int2 = 764;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int0, int0, int1, int2);
      } catch(IllegalArgumentException e) {
         //
         // maxFrameLength must be a positive integer: 0
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test01()  throws Throwable  {
      int int0 = 582;
      int int1 = (-3429);
      int int2 = 11;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int1, int1, int2);
      } catch(IllegalArgumentException e) {
         //
         // lengthFieldOffset must be a non-negative integer: -3429
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test02()  throws Throwable  {
      int int0 = 4;
      int int1 = 0;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int0);
      int int2 = 984;
      BigEndianHeapChannelBuffer bigEndianHeapChannelBuffer0 = new BigEndianHeapChannelBuffer(int2);
      ChannelBufferFactory channelBufferFactory0 = bigEndianHeapChannelBuffer0.factory();
      Charset charset0 = Charset.defaultCharset();
      String string0 = bigEndianHeapChannelBuffer0.toString(charset0);
      int int3 = 0;
      ChannelBuffer channelBuffer0 = lengthFieldBasedFrameDecoder0.extractFrame(bigEndianHeapChannelBuffer0, int2, int1);
      int int4 = (-644);
      ChannelHandlerContext channelHandlerContext0 = null;
      Channel channel0 = mock(Channel.class, new ViolatedAssumptionAnswer());
      Channel channel1 = mock(Channel.class, new ViolatedAssumptionAnswer());
      Object object0 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext0, channel1, bigEndianHeapChannelBuffer0);
      Object object1 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext0, channel0, bigEndianHeapChannelBuffer0);
  }

  @Test(timeout = 11000)
  public void test03()  throws Throwable  {
      int int0 = 74;
      int int1 = 3;
      int int2 = (-3011);
      boolean boolean0 = false;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int0, int1, int0, int2, boolean0);
      } catch(IllegalArgumentException e) {
         //
         // initialBytesToStrip must be a non-negative integer: -3011
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test04()  throws Throwable  {
      int int0 = 65535;
      int int1 = 39;
      int int2 = 3;
      boolean boolean0 = false;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int2, int1, int1, boolean0);
      ChannelHandlerContext channelHandlerContext0 = mock(ChannelHandlerContext.class, new ViolatedAssumptionAnswer());
      Channel channel0 = mock(Channel.class, new ViolatedAssumptionAnswer());
      HeapChannelBufferFactory heapChannelBufferFactory0 = new HeapChannelBufferFactory();
      int int3 = 313;
      BigEndianHeapChannelBuffer bigEndianHeapChannelBuffer0 = new BigEndianHeapChannelBuffer(int3);
      ByteOrder byteOrder0 = bigEndianHeapChannelBuffer0.order();
      ChannelBuffer channelBuffer0 = heapChannelBufferFactory0.getBuffer(byteOrder0, int1);
      Object object0 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext0, channel0, channelBuffer0);
  }

  @Test(timeout = 11000)
  public void test05()  throws Throwable  {
      int int0 = 7;
      int int1 = (-1);
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int0, int1);
      } catch(IllegalArgumentException e) {
         //
         // lengthFieldLength must be either 1, 2, 3, 4, or 8: -1
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test06()  throws Throwable  {
      int int0 = 2074;
      int int1 = 1946;
      int int2 = 2;
      int int3 = 15;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int2, int0, int3);
      ChannelHandlerContext channelHandlerContext0 = mock(ChannelHandlerContext.class, new ViolatedAssumptionAnswer());
      Channel channel0 = null;
      ByteOrder byteOrder0 = ByteOrder.LITTLE_ENDIAN;
      DirectChannelBufferFactory directChannelBufferFactory0 = new DirectChannelBufferFactory(byteOrder0, int1);
      ChannelBuffer channelBuffer0 = directChannelBufferFactory0.getBuffer(byteOrder0, int1);
      Object object0 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext0, channel0, channelBuffer0);
      ChannelHandlerContext channelHandlerContext1 = null;
      Channel channel1 = null;
      ByteOrder byteOrder1 = ByteOrder.nativeOrder();
      LinkedList<ChannelBuffer> linkedList0 = new LinkedList<ChannelBuffer>();
      CompositeChannelBuffer compositeChannelBuffer0 = new CompositeChannelBuffer(byteOrder1, linkedList0);
      ReadOnlyChannelBuffer readOnlyChannelBuffer0 = new ReadOnlyChannelBuffer(compositeChannelBuffer0);
      int int4 = 451;
      compositeChannelBuffer0.clear();
      int int5 = 8;
      // Undeclared exception!
      try { 
        readOnlyChannelBuffer0.toByteBuffer(int4, int5);
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 1
         //
         verifyException("org.jboss.netty.buffer.CompositeChannelBuffer", e);
      }
  }

  @Test(timeout = 11000)
  public void test07()  throws Throwable  {
      int int0 = 128;
      int int1 = 8;
      boolean boolean0 = true;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int0, int1, int1, int0, boolean0);
      } catch(IllegalArgumentException e) {
         //
         // maxFrameLength (128) must be equal to or greater than lengthFieldOffset (128) + lengthFieldLength (8).
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test08()  throws Throwable  {
      int int0 = 1;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int0, int0);
      } catch(IllegalArgumentException e) {
         //
         // maxFrameLength (1) must be equal to or greater than lengthFieldOffset (1) + lengthFieldLength (1).
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test09()  throws Throwable  {
      int int0 = 8;
      int int1 = 4;
      int int2 = 8;
      boolean boolean0 = true;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int1, int0, int2, boolean0);
      ChannelHandlerContext channelHandlerContext0 = mock(ChannelHandlerContext.class, new ViolatedAssumptionAnswer());
      Channel channel0 = mock(Channel.class, new ViolatedAssumptionAnswer());
      byte[] byteArray0 = new byte[8];
      byte byte0 = (byte) (-32);
      byteArray0[0] = byte0;
      byte byte1 = (byte)72;
      byteArray0[1] = byte1;
      byte byte2 = (byte) (-80);
      byte byte3 = (byte)93;
      byteArray0[3] = byte3;
      byte byte4 = (byte)74;
      byteArray0[4] = byte4;
      byte byte5 = (byte)28;
      byteArray0[5] = byte5;
      byteArray0[6] = byte0;
      byte byte6 = (byte) (-128);
      LittleEndianHeapChannelBuffer littleEndianHeapChannelBuffer0 = new LittleEndianHeapChannelBuffer(byteArray0);
      ChannelBuffer channelBuffer0 = littleEndianHeapChannelBuffer0.duplicate();
      Object object0 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext0, channel0, channelBuffer0);
      ChannelHandlerContext channelHandlerContext1 = mock(ChannelHandlerContext.class, new ViolatedAssumptionAnswer());
      Channel channel1 = mock(Channel.class, new ViolatedAssumptionAnswer());
      Object object1 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext1, channel1, littleEndianHeapChannelBuffer0);
      ChannelHandlerContext channelHandlerContext2 = mock(ChannelHandlerContext.class, new ViolatedAssumptionAnswer());
      Channel channel2 = mock(Channel.class, new ViolatedAssumptionAnswer());
      Object object2 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext2, channel2, channelBuffer0);
  }

  @Test(timeout = 11000)
  public void test10()  throws Throwable  {
      int int0 = 852;
      int int1 = 4;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int0, int1, int1, int0);
      } catch(IllegalArgumentException e) {
         //
         // maxFrameLength (852) must be equal to or greater than lengthFieldOffset (852) + lengthFieldLength (4).
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test11()  throws Throwable  {
      int int0 = (-15);
      int int1 = 32;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int1);
      } catch(IllegalArgumentException e) {
         //
         // maxFrameLength must be a positive integer: -15
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test12()  throws Throwable  {
      int int0 = 4639;
      int int1 = (-3291);
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int0);
      } catch(IllegalArgumentException e) {
         //
         // lengthFieldOffset must be a non-negative integer: -3291
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test13()  throws Throwable  {
      int int0 = 3174;
      int int1 = (-1);
      int int2 = 2355;
      boolean boolean0 = true;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int0, int1, int0, int2, boolean0);
      } catch(IllegalArgumentException e) {
         //
         // lengthFieldLength must be either 1, 2, 3, 4, or 8: -1
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test14()  throws Throwable  {
      int int0 = 1;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int0, int0);
      } catch(IllegalArgumentException e) {
         //
         // maxFrameLength (1) must be equal to or greater than lengthFieldOffset (1) + lengthFieldLength (1).
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test15()  throws Throwable  {
      int int0 = 0;
      int int1 = 1345;
      int int2 = (-1780);
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = null;
      try {
        lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int2, int2, int1);
      } catch(IllegalArgumentException e) {
         //
         // maxFrameLength must be a positive integer: 0
         //
         verifyException("org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder", e);
      }
  }

  @Test(timeout = 11000)
  public void test16()  throws Throwable  {
      int int0 = 4;
      int int1 = 0;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int0);
      int int2 = 984;
      BigEndianHeapChannelBuffer bigEndianHeapChannelBuffer0 = new BigEndianHeapChannelBuffer(int2);
      ChannelBufferFactory channelBufferFactory0 = bigEndianHeapChannelBuffer0.factory();
      Charset charset0 = Charset.defaultCharset();
      String string0 = bigEndianHeapChannelBuffer0.toString(charset0);
      int int3 = 0;
      ChannelBuffer channelBuffer0 = lengthFieldBasedFrameDecoder0.extractFrame(bigEndianHeapChannelBuffer0, int2, int1);
      ChannelBuffer channelBuffer1 = lengthFieldBasedFrameDecoder0.extractFrame(bigEndianHeapChannelBuffer0, int1, int3);
      int int4 = (-644);
      // Undeclared exception!
      try { 
        lengthFieldBasedFrameDecoder0.extractFrame(bigEndianHeapChannelBuffer0, int1, int4);
      } catch(NegativeArraySizeException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jboss.netty.buffer.HeapChannelBuffer", e);
      }
  }

  @Test(timeout = 11000)
  public void test17()  throws Throwable  {
      int int0 = 4;
      int int1 = 0;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int0);
      BigEndianHeapChannelBuffer bigEndianHeapChannelBuffer0 = new BigEndianHeapChannelBuffer(int1);
      ChannelBufferFactory channelBufferFactory0 = bigEndianHeapChannelBuffer0.factory();
      Charset charset0 = Charset.defaultCharset();
      String string0 = bigEndianHeapChannelBuffer0.toString(charset0);
      int int2 = 0;
      // Undeclared exception!
      try { 
        lengthFieldBasedFrameDecoder0.extractFrame(bigEndianHeapChannelBuffer0, int0, int1);
      } catch(ArrayIndexOutOfBoundsException e) {
      }
  }

  @Test(timeout = 11000)
  public void test18()  throws Throwable  {
      int int0 = 8;
      int int1 = 4;
      int int2 = 8;
      boolean boolean0 = true;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int1, int0, int2, boolean0);
      ChannelHandlerContext channelHandlerContext0 = mock(ChannelHandlerContext.class, new ViolatedAssumptionAnswer());
      Channel channel0 = mock(Channel.class, new ViolatedAssumptionAnswer());
      byte[] byteArray0 = new byte[8];
      byte byte0 = (byte) (-32);
      byteArray0[0] = byte0;
      byte byte1 = (byte)72;
      byteArray0[1] = byte1;
      byte byte2 = (byte) (-80);
      byteArray0[2] = byte2;
      byte byte3 = (byte)93;
      byteArray0[3] = byte3;
      byte byte4 = (byte)74;
      byteArray0[4] = byte4;
      byte byte5 = (byte)28;
      byteArray0[5] = byte5;
      byte byte6 = (byte)42;
      byteArray0[6] = byte6;
      byte byte7 = (byte) (-128);
      byteArray0[7] = byte7;
      LittleEndianHeapChannelBuffer littleEndianHeapChannelBuffer0 = new LittleEndianHeapChannelBuffer(byteArray0);
      ChannelBuffer channelBuffer0 = littleEndianHeapChannelBuffer0.duplicate();
      Object object0 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext0, channel0, channelBuffer0);
  }

  @Test(timeout = 11000)
  public void test19()  throws Throwable  {
      int int0 = 8;
      int int1 = 4;
      int int2 = 8;
      boolean boolean0 = true;
      LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder0 = new LengthFieldBasedFrameDecoder(int0, int1, int1, int0, int2, boolean0);
      ChannelHandlerContext channelHandlerContext0 = mock(ChannelHandlerContext.class, new ViolatedAssumptionAnswer());
      Channel channel0 = mock(Channel.class, new ViolatedAssumptionAnswer());
      byte[] byteArray0 = new byte[13];
      byte byte0 = (byte) (-32);
      byteArray0[0] = byte0;
      byte byte1 = (byte)72;
      byteArray0[1] = byte1;
      byte byte2 = (byte) (-80);
      byteArray0[2] = byte2;
      byte byte3 = (byte)93;
      byteArray0[3] = byte3;
      byte byte4 = (byte)74;
      byteArray0[4] = byte4;
      byte byte5 = (byte)28;
      byteArray0[5] = byte5;
      byte byte6 = (byte)42;
      byteArray0[6] = byte6;
      byte byte7 = (byte) (-128);
      byteArray0[7] = byte7;
      LittleEndianHeapChannelBuffer littleEndianHeapChannelBuffer0 = new LittleEndianHeapChannelBuffer(byteArray0);
      ChannelBuffer channelBuffer0 = littleEndianHeapChannelBuffer0.duplicate();
      Object object0 = lengthFieldBasedFrameDecoder0.decode(channelHandlerContext0, channel0, channelBuffer0);
      ChannelHandlerContext channelHandlerContext1 = mock(ChannelHandlerContext.class, new ViolatedAssumptionAnswer());
      Channel channel1 = mock(Channel.class, new ViolatedAssumptionAnswer());
      HeapChannelBufferFactory heapChannelBufferFactory0 = new HeapChannelBufferFactory();
      ByteOrder byteOrder0 = ByteOrder.nativeOrder();
      int int3 = 4;
      int int4 = (-249);
      // Undeclared exception!
      try { 
        heapChannelBufferFactory0.getBuffer(byteOrder0, byteArray0, int3, int4);
      } catch(IndexOutOfBoundsException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jboss.netty.buffer.AbstractChannelBuffer", e);
      }
  }
}