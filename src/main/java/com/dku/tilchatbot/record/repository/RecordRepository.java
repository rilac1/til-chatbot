package com.dku.tilchatbot.record.repository;

import com.dku.tilchatbot.record.model.ActivityCategory;
import com.dku.tilchatbot.record.model.Record;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository {
    Record save(Record record);
    List<Record> find(ActivityCategory activityCategory);
    List<Record> find(ActivityCategory activityCategory, LocalDate lookupDate);
}
