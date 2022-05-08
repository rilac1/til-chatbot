package com.dku.tilchatbot.record.model;

import com.dku.tilchatbot.webhook.controller.dto.WebhookRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Record {
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ActivityCategory activityCategory;

    public Record(LocalDateTime startTime, LocalDateTime endTime, ActivityCategory category) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityCategory = category;
    }

    public static Record from(WebhookRequest request) {
        final var startTime = request.getQueryResult().getParameters().getStartTime();
        final var endTime = request.getQueryResult().getParameters().getEndTime();
        final var category = request.getQueryResult().getParameters().getActivityCategory();

        return new Record(startTime, endTime, category);
    }
}