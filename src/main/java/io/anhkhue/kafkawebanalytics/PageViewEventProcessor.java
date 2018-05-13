package io.anhkhue.kafkawebanalytics;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PageViewEventProcessor {

    @StreamListener
    @SendTo(PageCountStreamsBinding.PAGE_COUNT_OUT)
    public KStream<String, Long> process(
            @Input(PageViewsStreamsBinding.PAGE_VIEWS_IN) KStream<String, PageViewEvent> kStream) {
        return kStream.filter((key, value) -> value.getDuration() > 10)
                .map((key, value) -> new KeyValue<>(value.getPage(), "0"))
                .groupByKey()
                .count(Materialized.as(PageCountStreamsBinding.PAGE_COUNT_MATERIALIZED_VIEW))
                .toStream();
    }
}
