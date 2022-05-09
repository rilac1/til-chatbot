package com.dku.tilchatbot.webhook.controller.dto;

import com.dku.tilchatbot.record.model.ActivityCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class WebhookRequest {
    @Valid
    private QueryResult queryResult;

    @Getter
    public static class QueryResult {
        @Valid
        @NotNull
        private Parameters parameters;

        @Valid
        @NotNull
        private Intent intent;

        @Getter
        public static class Parameters {
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
            private LocalDateTime startTime;

            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
            private LocalDateTime endTime;

            @NotNull
            @JsonProperty("categoryName")
            private ActivityCategory activityCategory;

            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
            private LocalDate date;
        }

        @Getter
        public static class Intent {
            @NotNull
            @JsonProperty("displayName")
            private IntentType intentType;
        }
    }

    public IntentType getIntentType() {
        return queryResult.getIntent().getIntentType();
    }
}
