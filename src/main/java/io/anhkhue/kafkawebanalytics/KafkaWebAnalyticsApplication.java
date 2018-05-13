package io.anhkhue.kafkawebanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(AnalyticsBinding.class)
public class KafkaWebAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaWebAnalyticsApplication.class, args);
	}
}
