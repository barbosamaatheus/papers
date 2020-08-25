/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 29 07:38:37 GMT 2020
 */

package org.apache.storm.kafka.spout;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class KafkaSpoutConfig_ESTest extends KafkaSpoutConfig_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      KafkaSpoutConfig<Object, String> kafkaSpoutConfig0 = new KafkaSpoutConfig<Object, String>();
      String string0 = kafkaSpoutConfig0.toString();
      assertEquals("KafkaSpoutConfig{kafkaProps=null, keyDeserializer=null, valueDeserializer=null, pollTimeoutMs=0, offsetCommitPeriodMs=0, maxUncommittedOffsets=0, firstPollOffsetStrategy=null, kafkaSpoutStreams=null, tuplesBuilder=null, retryService=null, topics=null, topicWildcardPattern=null}", string0); // (Primitive) Original Value: KafkaSpoutConfig{kafkaProps=null, keyDeserializer=null, valueDeserializer=null, pollTimeoutMs=0, offsetCommitPeriodMs=0, maxUncommittedOffsets=0, firstPollOffsetStrategy=null, kafkaSpoutStreams=null, tuplesBuilder=null, retryService=null, topics=null, topicWildcardPattern=null} | Regression Value: KafkaSpoutConfig{kafkaProps=null, keyDeserializer=null, valueDeserializer=null, pollTimeoutMs=0, offsetCommitPeriodMs=0, maxRetries=0, maxUncommittedOffsets=0, firstPollOffsetStrategy=null, kafkaSpoutStreams=null, tuplesBuilder=null, retryService=null, topics=null, topicWildcardPattern=null}
  }
}