package com.dku.tilchatbot.record.service;

import com.dku.tilchatbot.record.model.Record;
import com.dku.tilchatbot.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordManageService implements RecordService {

    private final RecordRepository recordRepository;

    @Override
    public void saveRecord(Record record) {
        if(record == null) {
            throw new IllegalArgumentException();
        }
        final var savedRecord = recordRepository.save(record);
        System.out.println(savedRecord);
    }
}
