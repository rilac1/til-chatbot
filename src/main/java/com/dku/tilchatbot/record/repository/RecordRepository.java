package com.dku.tilchatbot.record.repository;

import com.dku.tilchatbot.record.model.Record;

public interface RecordRepository {
    Record save(Record record);
}
