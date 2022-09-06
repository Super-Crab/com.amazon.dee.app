package com.amazon.alexa.mobilytics.timeline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes9.dex */
public class TimelineEvent extends DefaultMobilyticsOperationalEvent {
    private static final boolean[][] stateTransitionValidityMapping = {new boolean[]{false, true, false, true, true}, new boolean[]{false, false, true, true, true}, new boolean[]{false, true, false, true, true}, new boolean[]{false, false, false, false, false}, new boolean[]{false, false, false, false, false}};
    private String abortReason;
    private AtomicLong activeDuration;
    private AtomicLong endTimestamp;
    private AtomicInteger eventCount;
    private transient AtomicLong mLastStateChangeTime;
    private transient AtomicInteger mState;
    private AtomicLong startTimestamp;
    private String timelineState;
    private AtomicLong totalDuration;

    public TimelineEvent(@NonNull String str, @Nullable String str2) {
        super("mobilytics.events", Constants.TIMELINE);
        this.eventCount = null;
        this.startTimestamp = null;
        this.endTimestamp = null;
        this.activeDuration = null;
        this.totalDuration = null;
        this.abortReason = null;
        this.mLastStateChangeTime = null;
        this.mState = new AtomicInteger(0);
        this.timelineId = UUID.randomUUID().toString();
        this.timelineName = str;
        this.timelineNamespace = str2 == null ? Constants.TIMELINE : str2;
        this.operationalEventType = Constants.TIMELINE;
    }

    private boolean setStateIntenal(long j, int i, long j2) {
        setEventTimestamp(j2);
        if (this.activeDuration == null) {
            this.activeDuration = new AtomicLong(0L);
        }
        if (this.totalDuration == null) {
            this.totalDuration = new AtomicLong(0L);
        }
        int state = state();
        if (state(i)) {
            long andSet = this.mLastStateChangeTime.get() - this.mLastStateChangeTime.getAndSet(j);
            if (state == 0 || state == 2) {
                this.activeDuration.addAndGet(andSet);
            }
            this.totalDuration.addAndGet(andSet);
            return true;
        }
        return false;
    }

    public boolean abort(String str, long j, long j2) {
        this.abortReason = str;
        boolean stateIntenal = setStateIntenal(j, 4, j2);
        this.endTimestamp = new AtomicLong(j2);
        return stateIntenal;
    }

    public String abortReason() {
        String str = this.abortReason;
        return str != null ? str : "Unknown";
    }

    public long activeDuration() {
        AtomicLong atomicLong = this.activeDuration;
        if (atomicLong != null) {
            return atomicLong.get();
        }
        return 0L;
    }

    public long calculateTimelineElapsedDuration(long j) {
        AtomicLong atomicLong = this.mLastStateChangeTime;
        if (atomicLong == null || atomicLong.get() == 0) {
            return 0L;
        }
        if (this.activeDuration == null) {
            this.activeDuration = new AtomicLong(0L);
        }
        return this.activeDuration.get() + (j - this.mLastStateChangeTime.get());
    }

    public void deSerialize(Map<String, String> map) {
        this.timelineId = map.get(Constants.TIMELINE_ID_KEY);
        if (map.containsKey("ad")) {
            this.activeDuration = new AtomicLong(Long.parseLong(map.get("ad")));
        }
        if (map.containsKey(Constants.TIMELINE_TOTAL_DURATION_KEY)) {
            this.totalDuration = new AtomicLong(Long.parseLong(map.get(Constants.TIMELINE_TOTAL_DURATION_KEY)));
        }
        if (map.containsKey(Constants.TIMELINE_START_TIME_KEY)) {
            this.startTimestamp = new AtomicLong(Long.parseLong(map.get(Constants.TIMELINE_START_TIME_KEY)));
        }
        if (map.containsKey(Constants.TIMELINE_EVENT_COUNT_KEY)) {
            this.eventCount = new AtomicInteger(Integer.parseInt(map.get(Constants.TIMELINE_EVENT_COUNT_KEY)));
        }
        if (map.containsKey("name")) {
            this.timelineName = map.get("name");
        }
        if (map.containsKey(Constants.TIMELINE_NAMESPACE_KEY)) {
            this.timelineNamespace = map.get(Constants.TIMELINE_NAMESPACE_KEY);
        }
    }

    public long endTimestamp() {
        AtomicLong atomicLong = this.endTimestamp;
        if (atomicLong != null) {
            return atomicLong.get();
        }
        return 0L;
    }

    public int eventCount() {
        AtomicInteger atomicInteger = this.eventCount;
        if (atomicInteger != null) {
            return atomicInteger.get();
        }
        return 0;
    }

    public void incrementEventCount() {
        if (this.eventCount == null) {
            this.eventCount = new AtomicInteger(0);
        }
        this.eventCount.incrementAndGet();
    }

    public boolean isMarkerRecordable() {
        int i = this.mState.get();
        return i == 0 || i == 2;
    }

    public boolean isValidStateToRecord() {
        int i = this.mState.get();
        return i == 0 || i == 2;
    }

    public boolean isValidStateTransition(int i) {
        return stateTransitionValidityMapping[this.mState.get()][i];
    }

    public boolean pause(long j, long j2) {
        return setStateIntenal(j, 1, j2);
    }

    public boolean resume(long j, long j2) {
        return setStateIntenal(j, 2, j2);
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent, com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    public EventDetailsProto.Builder serialize() {
        EventDetailsProto.Builder newBuilder = EventDetailsProto.newBuilder();
        if (this.activeDuration != null) {
            newBuilder.setActiveDuration(activeDuration());
        }
        if (this.totalDuration != null) {
            newBuilder.setTotalDuration(totalDuration());
        }
        if (this.eventCount != null) {
            newBuilder.setEventCount(eventCount());
        }
        if (this.startTimestamp != null) {
            newBuilder.setStartTimestamp(startTimestamp());
        }
        if (this.endTimestamp != null) {
            newBuilder.setEndTimestamp(endTimestamp());
        }
        if (this.abortReason != null) {
            newBuilder.setAbortReason(abortReason());
        }
        String str = this.timelineState;
        if (str != null) {
            newBuilder.setTimelineState(str);
        }
        return newBuilder;
    }

    public Map<String, String> serializeToMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.TIMELINE_ID_KEY, timelineId());
        if (this.activeDuration != null) {
            hashMap.put("ad", String.valueOf(activeDuration()));
        }
        if (this.totalDuration != null) {
            hashMap.put(Constants.TIMELINE_TOTAL_DURATION_KEY, String.valueOf(totalDuration()));
        }
        String str = this.timelineName;
        if (str != null) {
            hashMap.put("name", str);
        }
        String str2 = this.timelineNamespace;
        if (str2 != null) {
            hashMap.put(Constants.TIMELINE_NAMESPACE_KEY, str2);
        }
        if (this.eventCount != null) {
            hashMap.put(Constants.TIMELINE_EVENT_COUNT_KEY, String.valueOf(eventCount()));
        }
        if (this.startTimestamp != null) {
            hashMap.put(Constants.TIMELINE_START_TIME_KEY, String.valueOf(startTimestamp()));
        }
        return hashMap;
    }

    public void start(long j, long j2) {
        setEventTimestamp(j2);
        this.timelineState = stateString(0);
        this.startTimestamp = new AtomicLong(j2);
        this.mLastStateChangeTime = new AtomicLong(j);
    }

    public long startTimestamp() {
        AtomicLong atomicLong = this.startTimestamp;
        if (atomicLong != null) {
            return atomicLong.get();
        }
        return 0L;
    }

    public int state() {
        return this.mState.get();
    }

    public String stateString() {
        return stateString(state());
    }

    public String stateString(int i) {
        if (i != 0) {
            if (i == 1) {
                return "paused";
            }
            if (i == 2) {
                return Constants.TIMELINE_RESUMED_STR;
            }
            if (i == 3) {
                return "stopped";
            }
            if (i == 4) {
                return Constants.TIMELINE_ABORTED_STR;
            }
            return null;
        }
        return "started";
    }

    public boolean stop(long j, long j2) {
        boolean stateIntenal = setStateIntenal(j, 3, j2);
        this.endTimestamp = new AtomicLong(j2);
        return stateIntenal;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent, com.amazon.alexa.mobilytics.event.MobilyticsEvent
    public String timelineId() {
        return this.timelineId;
    }

    public long totalDuration() {
        AtomicLong atomicLong = this.totalDuration;
        if (atomicLong != null) {
            return atomicLong.get();
        }
        return 0L;
    }

    public void abortReason(String str) {
        this.abortReason = str;
    }

    public void endTimestamp(long j) {
        this.endTimestamp = new AtomicLong(j);
    }

    public boolean state(int i) {
        if (isValidStateTransition(i)) {
            AtomicInteger atomicInteger = this.mState;
            if (!atomicInteger.compareAndSet(atomicInteger.get(), i)) {
                return false;
            }
            this.timelineState = stateString(this.mState.get());
            return true;
        }
        TimelineExceptionLogger.logInvalidStateTransition(stateString(i), this);
        return false;
    }

    public TimelineEvent(String str) {
        super(str, "mobilytics.events", Constants.TIMELINE);
        this.eventCount = null;
        this.startTimestamp = null;
        this.endTimestamp = null;
        this.activeDuration = null;
        this.totalDuration = null;
        this.abortReason = null;
        this.mLastStateChangeTime = null;
        this.mState = new AtomicInteger(0);
        this.operationalEventType = Constants.TIMELINE;
    }
}
