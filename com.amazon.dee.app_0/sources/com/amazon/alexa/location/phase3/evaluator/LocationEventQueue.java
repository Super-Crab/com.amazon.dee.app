package com.amazon.alexa.location.phase3.evaluator;

import com.amazon.alexa.location.phase3.LocationDataStore;
import com.amazon.alexa.location.phase3.LocationDataStoreService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes9.dex */
public class LocationEventQueue {
    private final LocationDataStore<LocationEvent> eventStore;
    private final LocationDataStoreService locationDataStoreService;
    private final LinkedList<LocationEvent> queue = new LinkedList<>();

    public LocationEventQueue(LocationDataStoreService locationDataStoreService) {
        this.locationDataStoreService = locationDataStoreService;
        this.eventStore = locationDataStoreService.getDataStore(LocationDataStoreService.EVENT_QUEUE_TABLE, LocationEvent.class);
        loadQueueData();
    }

    private int getIndexNewerThan(long j) {
        Iterator<LocationEvent> descendingIterator = this.queue.descendingIterator();
        int size = this.queue.size();
        while (descendingIterator.hasNext() && descendingIterator.next().timestamp > j) {
            size--;
        }
        return size;
    }

    private void loadQueueData() {
        this.queue.addAll(this.eventStore.retrieveAll().values());
        Collections.sort(this.queue, $$Lambda$LocationEventQueue$ivwVt85tMLg2Bss9UHkLHmU.INSTANCE);
    }

    public void add(LocationEvent locationEvent) {
        this.queue.add(getIndexNewerThan(locationEvent.timestamp), locationEvent);
        this.eventStore.lambda$storeValueAsRx$1$LocationDataStore(locationEvent.id, locationEvent);
    }

    public void clear() {
        this.queue.clear();
        this.eventStore.removeAll();
    }

    public Iterable<LocationEvent> getAllEvents() {
        return this.queue;
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void removeExpiredEvents(long j) {
        ArrayList arrayList = new ArrayList();
        while (!this.queue.isEmpty() && this.queue.getFirst().timestamp < j) {
            arrayList.add(this.queue.getFirst().id);
            this.queue.removeFirst();
        }
        this.eventStore.lambda$removeValuesAsRx$2$LocationDataStore(arrayList);
    }
}
