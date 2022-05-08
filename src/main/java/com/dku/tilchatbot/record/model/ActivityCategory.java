package com.dku.tilchatbot.record.model;

import com.dku.tilchatbot.webhook.controller.dto.IntentType;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum ActivityCategory {
    STUDY,
    WORKOUT;

    @JsonCreator
    public static ActivityCategory from(String intentType) {
        return ActivityCategory.valueOf(intentType.toUpperCase());
    }
}
