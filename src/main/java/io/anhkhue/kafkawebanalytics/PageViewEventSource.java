package io.anhkhue.kafkawebanalytics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class PageViewEventSource implements ApplicationRunner {

    private final PageViewsStreamsBinding pageViewsStreamsBinding;

    public PageViewEventSource(PageViewsStreamsBinding pageViewsStreamsBinding) {
        this.pageViewsStreamsBinding = pageViewsStreamsBinding;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> names = List.of("Anh Khue", "Bill Gates", "Steve Jobs", "Elon Musk", "Tony Stark", "Kobe");
        List<String> pages = List.of("spring.io", "github.com", "kafka.apache.org", "redhat.com", "jetbrains.com");

        Runnable runnable = () -> {
            String randomName = names.get(new Random().nextInt(names.size()));
            String randomPage = pages.get(new Random().nextInt(pages.size()));

            PageViewEvent pageViewEvent = new PageViewEvent(randomName,
                                                            randomPage,
                                                            Math.random() > .5 ? 10 : 1000);

            Message<PageViewEvent> message = MessageBuilder
                    .withPayload(pageViewEvent)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, pageViewEvent.getUserId().getBytes())
                    .build();

            try {
                this.pageViewsStreamsBinding.pageViewsOut().send(message);
                log.info("Sending message: {}", message);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        };

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
}
