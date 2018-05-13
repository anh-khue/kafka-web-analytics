package io.anhkhue.kafkawebanalytics;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.stereotype.Component;

@Component
public interface PageCountStreamsBinding {

    String PAGE_COUNT_MATERIALIZED_VIEW = "pageCountMaterializedView";

    String PAGE_COUNT_OUT = "pageCountOut";
    String PAGE_COUNT_IN = "pageCountIn";

    @Output(PAGE_COUNT_OUT)
    KStream<String, Long> pageCountOut();

    @Input(PAGE_COUNT_IN)
    KTable<String, Long> pageCountIn();
}
