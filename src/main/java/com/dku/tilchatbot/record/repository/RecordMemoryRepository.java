package com.dku.tilchatbot.record.repository;

import com.dku.tilchatbot.record.model.Record;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

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
}
