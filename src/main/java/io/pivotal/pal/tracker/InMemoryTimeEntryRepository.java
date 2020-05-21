package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTimeEntryRepository implements  TimeEntryRepository{
    private Map<Long,TimeEntry> timeMap= new HashMap<>();
    private Long timeEntryId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry timeEntry1 = new TimeEntry(timeEntryId,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        timeMap.put(timeEntry1.getTimeEntryId(), timeEntry1);
        timeEntryId++;
        return timeMap.get(timeEntry1.getTimeEntryId());
    }

    public TimeEntry find(long timeEntryId) {
        return timeMap.get(timeEntryId);
    }

    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        TimeEntry timeEntry1 = timeMap.get(timeEntryId);
        if(timeEntry1!=null){
            timeMap.put(timeEntryId, new TimeEntry(timeEntry1.getTimeEntryId(),timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours()));
        }
        return timeMap.get(timeEntryId);
    }

    public void delete(long timeEntryId) {
        timeMap.remove(timeEntryId);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeMap.values());
    }
}
