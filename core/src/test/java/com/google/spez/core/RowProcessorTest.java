/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.spez.core;

import com.google.cloud.Timestamp;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.protobuf.Value;
import com.google.spanner.v1.StructType;
import com.google.spanner.v1.Type;
import com.google.spanner.v1.TypeCode;
import com.google.spez.spanner.Row;
import com.google.spez.spanner.internal.BothanRow;
import io.opencensus.trace.Span;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RowProcessorTest implements WithAssertions {

  @Test
  void logStats() {}

  @Test
  void publishRecord() {}

  @Test
  void process(
      @Mock StatsCollector statsCollector, @Mock EventPublisher publisher, @Mock Span parent)
      throws Exception {
    var fields =
        ImmutableList.of(
            StructType.Field.newBuilder()
                .setName("uuid")
                .setType(Type.newBuilder().setCode(TypeCode.STRING).build())
                .build(),
            StructType.Field.newBuilder()
                .setName("timestamp")
                .setType(Type.newBuilder().setCode(TypeCode.TIMESTAMP).build())
                .build());
    var timestamp = Timestamp.ofTimeSecondsAndNanos(0, 0);
    var values =
        ImmutableList.of(
            Value.newBuilder().setStringValue("1234").build(),
            Value.newBuilder().setStringValue(timestamp.toString()).build());
    Row row = new BothanRow(new com.google.spannerclient.Row(fields, values));

    var pubsub = new SpezConfig.PubSubConfig(null, "ledger-topic", 30);
    var sink =
        new SpezConfig.SinkConfig(
            null,
            "sink-instance",
            "sink-database",
            "sink_table",
            "uuid",
            "timestamp",
            30,
            false,
            null);
    var config = new SpezConfig(null, pubsub, sink, null, null);
    var extractor = new MetadataExtractor(config);
    // ByteString data, Map<String, String> attrMap, Span parent
    Mockito.when(publisher.publish(Mockito.any(), Mockito.any(), Mockito.any()))
        .thenReturn(Futures.immediateFuture(""));
    var handler = new RowProcessor(sink, publisher, extractor);
    var eventState = new EventState(null, statsCollector);
    eventState.rowRead(row);
    var result = handler.convertAndPublishTask(eventState);
    assertThat(result.get()).isEqualTo("");
  }
}
