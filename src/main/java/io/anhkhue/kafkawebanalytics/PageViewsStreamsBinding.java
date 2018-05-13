package io.anhkhue.kafkawebanalytics;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface PageViewsStreamsBinding {

    String PAGE_VIEWS_OUT = "pageViewsOut";
    String PAGE_VIEWS_IN = "pageViewsIn";

    @Input(PAGE_VIEWS_IN)
    KStream<String, PageViewEvent> pageViewsIn();

    @Output(PAGE_VIEWS_OUT)
    MessageChannel pageViewsOut();
}
