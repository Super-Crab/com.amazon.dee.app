package com.amazon.alexa.fitness.algorithms;
/* loaded from: classes.dex */
public class ActivityEvent {
    public int accumulatedSteps;
    public ActivityType activityType;
    public int cadenceStepsPerMinute;
    public EventType eventType;
    public long timestamp;

    /* loaded from: classes.dex */
    public enum EventType {
        EVENT_TYPE_ACTIVITY,
        EVENT_TYPE_START,
        EVENT_TYPE_PAUSE,
        EVENT_TYPE_RESUME,
        EVENT_TYPE_STOP
    }
}
