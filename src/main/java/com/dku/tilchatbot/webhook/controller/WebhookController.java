package com.dku.tilchatbot.webhook.controller;

import com.dku.tilchatbot.record.model.Record;
import com.dku.tilchatbot.record.service.RecordService;
import com.dku.tilchatbot.webhook.controller.dto.IntentType;
import com.dku.tilchatbot.webhook.controller.dto.WebhookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final RecordService recordService;

    @PostMapping("/api/v1/webhook")
    public void parseWebhook(@RequestBody WebhookRequest request) {

        IntentType intentType = request.getIntentType();

        switch (intentType) {
            case SAVE_RECORD:
                final var record = Record.from(request);
                recordService.saveRecord(record);
                break;
            case LOOKUP_RECORD:
                //TODO : 조회 구현
                break;
        }
    }
}
