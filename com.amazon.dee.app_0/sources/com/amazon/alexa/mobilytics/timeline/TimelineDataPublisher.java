package com.amazon.alexa.mobilytics.timeline;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.LogLevel;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsErrorEvent;
import com.amazon.alexa.mobilytics.executor.Executor;
import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class TimelineDataPublisher {
    private static final String COMPONENT = "mobilytics";
    private static final String ERROR_EVENT_NAME = "TimelinePublish";
    private static final String ERROR_TITLE = "publishExistingEvents";
    private static final String EVENT_VALUE = "EventValue";
    private static final String SUB_COMPONENT = "timeline";
    private static final String TAG = Log.tag(TimelineDataPublisher.class);
    private final PersistentStorage.Factory persistentStorageFactory;
    private final TimelineStorage timelineStorage;

    @Inject
    public TimelineDataPublisher(@NonNull TimelineStorage timelineStorage, @NonNull PersistentStorage.Factory factory) {
        this.timelineStorage = timelineStorage;
        this.persistentStorageFactory = factory;
    }

    private boolean publishEventsInSession(Executor executor, String str) {
        PersistentStorage create = this.persistentStorageFactory.create(str);
        Set<String> stringSet = create.getStringSet(TimelineStorage.PERSISTANCE_KEY_TL_KEY_SET, null);
        if (stringSet == null) {
            create.edit().clear().commit();
            return true;
        }
        HashSet hashSet = new HashSet(stringSet);
        for (String str2 : stringSet) {
            Map<String, String> stringMap = create.getStringMap(str2, null);
            TimelineEvent timelineEvent = new TimelineEvent(stringMap.containsKey("name") ? stringMap.get("name") : "Unknown");
            timelineEvent.deSerialize(stringMap);
            timelineEvent.state(4);
            timelineEvent.abortReason("Reconciled");
            timelineEvent.endTimestamp(timelineEvent.getEventTimestamp());
            executor.executeRecordEvent(timelineEvent);
            hashSet.remove(str2);
        }
        if (hashSet.isEmpty()) {
            create.edit().clear().commit();
            return true;
        }
        create.edit().set(TimelineStorage.PERSISTANCE_KEY_TL_KEY_SET, Collections.unmodifiableSet(hashSet)).commit();
        return false;
    }

    public void publishExistingEvents(Executor executor) {
        try {
            Set<String> readTimelineSessionSet = this.timelineStorage.readTimelineSessionSet();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            String currentTimelineSessionId = this.timelineStorage.getCurrentTimelineSessionId();
            for (String str : readTimelineSessionSet) {
                if (!currentTimelineSessionId.equals(str) && publishEventsInSession(executor, str)) {
                    linkedHashSet.add(str);
                }
            }
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            for (String str2 : readTimelineSessionSet) {
                if (!linkedHashSet.contains(str2)) {
                    linkedHashSet2.add(str2);
                }
            }
            this.timelineStorage.saveSessionSet(linkedHashSet2);
        } catch (Exception e) {
            DefaultMobilyticsErrorEvent defaultMobilyticsErrorEvent = new DefaultMobilyticsErrorEvent(ERROR_EVENT_NAME, LogLevel.CRITICAL, ERROR_TITLE, "mobilytics", "timeline", OwnerIdentifier.MOBILE_ORG_ANALYTICS_BACKEND);
            defaultMobilyticsErrorEvent.withShortMessage(e.toString());
            HashMap hashMap = new HashMap();
            hashMap.put("EventValue", Utils.getStackTrace(e.getStackTrace()));
            defaultMobilyticsErrorEvent.withDebugInfo(hashMap);
            executor.executeRecordEvent(defaultMobilyticsErrorEvent);
            Log.e(TAG, e, "Error while publishing existing timeline events", new Object[0]);
        }
    }
}
