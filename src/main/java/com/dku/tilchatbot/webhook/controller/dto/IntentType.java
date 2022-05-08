package com.dku.tilchatbot.webhook.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum IntentType {
    SAVE_RECORD,
    LOOKUP_RECORD;

    @JsonCreator
    public static IntentType from(String intentType) {
        return IntentType.valueOf(intentType.toUpperCase());
    }
}
