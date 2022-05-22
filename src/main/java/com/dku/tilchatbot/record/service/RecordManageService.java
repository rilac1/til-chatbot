package com.dku.tilchatbot.record.service;

import com.dku.tilchatbot.record.model.ActivityCategory;
import com.dku.tilchatbot.record.model.Record;
import com.dku.tilchatbot.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

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

    @Override
    public List<Record> lookUpRecord(ActivityCategory activityCategory) {
        return recordRepository.find(activityCategory);
    }

    @Override
    public List<Record> lookupRecord(ActivityCategory activityCategory, LocalDate lookupDate) {
        return recordRepository.find(activityCategory, lookupDate);
    }

    @Override
    public long totalTime(List<Record> recordList) {
        return recordList.stream()
                .map(record -> Duration.between(record.getStartTime(), record.getEndTime()).toMinutes())
                .mapToLong(i->i)
                .sum();
    }

    @Override
    public long averageTime(List<Record> recordList) {
        OptionalDouble average =  recordList.stream()
                .map(record -> Duration.between(record.getStartTime(), record.getEndTime()).toMinutes())
                .mapToLong(i->i)
                .average();

        return Double.valueOf(String.valueOf(average)).longValue();
    }
}
