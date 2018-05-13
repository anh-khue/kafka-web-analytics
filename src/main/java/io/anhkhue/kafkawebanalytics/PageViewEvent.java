package io.anhkhue.kafkawebanalytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageViewEvent {

    private String userId;
    private String page;
    private long duration;

}
