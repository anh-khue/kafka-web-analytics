package io.anhkhue.kafkawebanalytics;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PageViewEventProcessor {

    @StreamListener
    public void process(@Input(AnalyticsBinding.PAGE_VIEWS_IN) KStream<String, PageViewEvent> kStream) {
        kStream.filter((key, value) -> value.getDuration() > 10)
                .map((key, value) -> new KeyValue<>(value.getPage(), "0"))
                .groupByKey()
                .count(Materialized.as(AnalyticsBinding.PAGE_COUNT_MATERIALIZED_VIEW));
    }

}
