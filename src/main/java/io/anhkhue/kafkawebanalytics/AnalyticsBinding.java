package io.anhkhue.kafkawebanalytics;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface AnalyticsBinding {

    String PAGE_VIEWS_OUT = "pageViewsOut";
    String PAGE_VIEWS_IN = "pageViewsIn";
    String PAGE_COUNT_MATERIALIZED_VIEW = "pageCountMaterializedView";

    @Input(PAGE_VIEWS_IN)
    KStream<String, PageViewEvent> pageViewsIn();

    @Output(PAGE_VIEWS_OUT)
    MessageChannel pageViewsOut();

}
