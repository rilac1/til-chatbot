package com.dku.tilchatbot.record.service;

import com.dku.tilchatbot.record.model.ActivityCategory;
import com.dku.tilchatbot.record.model.Record;

import java.time.LocalDate;
import java.util.List;

public interface RecordService {
    void saveRecord(Record record);

    List<Record> lookUpRecord(ActivityCategory activityCategory);
    List<Record> lookupRecord(ActivityCategory activityCategory, LocalDate lookupDate);

    long totalTime(List<Record> recordList);
    long averageTime(List<Record> recordList);
}
