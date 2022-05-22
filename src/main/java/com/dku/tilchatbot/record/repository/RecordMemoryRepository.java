package com.dku.tilchatbot.record.repository;

import com.dku.tilchatbot.record.model.ActivityCategory;
import com.dku.tilchatbot.record.model.Record;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RecordMemoryRepository implements RecordRepository {
    private final Map<Long, Record> recordMap = new HashMap<>();

    private Long incrementIndex;

    public RecordMemoryRepository() {
        this.incrementIndex = 0L;
    }

    @Override
    public Record save(Record record) {
        record.setId(incrementIndex++);
        recordMap.put(record.getId(), record);
        return record;
    }

    @Override
    public List<Record> find(ActivityCategory activityCategory) {
        return recordMap.values().stream()
                .filter(record -> record.getActivityCategory()
                        .equals(activityCategory))
                .collect(Collectors.toList());
    }

    @Override
    public List<Record> find(ActivityCategory activityCategory, LocalDate lookupDate) {
        return find(activityCategory).stream()
                .filter(record -> record.getStartTime().toLocalDate()
                        .equals(lookupDate))
                .collect(Collectors.toList());
    }
}
