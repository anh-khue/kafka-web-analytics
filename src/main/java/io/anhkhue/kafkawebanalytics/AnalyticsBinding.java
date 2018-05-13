package io.anhkhue.kafkawebanalytics;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface AnalyticsBinding {

    String PAGE_VIEWS_OUT = "pageViewsOut";

    @Output(PAGE_VIEWS_OUT)
    MessageChannel pageViewsOut();

}
