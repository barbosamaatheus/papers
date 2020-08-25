/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 29 21:10:42 GMT 2020
 */

package com.metamx.druid.loading;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.metamx.druid.client.DataSegment;
import com.metamx.druid.shard.SingleDimensionShardSpec;
import java.util.HashMap;
import java.util.Vector;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.joda.time.Interval;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class S3SegmentPusher_ESTest extends S3SegmentPusher_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Interval interval0 = Interval.parse((String) null);
      HashMap<String, Object> hashMap0 = new HashMap<String, Object>();
      Vector<String> vector0 = new Vector<String>();
      SingleDimensionShardSpec singleDimensionShardSpec0 = new SingleDimensionShardSpec("bucket", "", "y{rrBLQ<6`Gf", (-3544));
      Integer integer0 = Integer.getInteger((String) null, (-3544));
      DataSegment dataSegment0 = null;
      try {
        dataSegment0 = new DataSegment((String) null, interval0, "", hashMap0, vector0, vector0, singleDimensionShardSpec0, integer0, 0L);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.metamx.druid.client.DataSegment", e);
      }
  }
}