package com.amazon.alexa.mobilytics.timeline;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.google.common.base.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class TimelineManager {
    private final TimelineStorage timelineStorage;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface MessageType {
        public static final int TIMELINE_ABORT = 5;
        public static final int TIMELINE_PAUSE = 2;
        public static final int TIMELINE_RECORD_EVENT = 6;
        public static final int TIMELINE_RESUME = 3;
        public static final int TIMELINE_START = 1;
        public static final int TIMELINE_STOP = 4;
    }

    @Inject
    public TimelineManager(@NonNull TimelineStorage timelineStorage) {
        this.timelineStorage = (TimelineStorage) Preconditions.checkNotNull(timelineStorage);
    }

    private TimelineEvent abort(TimelineMessage timelineMessage) {
        if (!TextUtils.isEmpty(timelineMessage.name) && !TextUtils.isEmpty(timelineMessage.namespace) && !TextUtils.isEmpty(timelineMessage.abortReason)) {
            String key = this.timelineStorage.getKey(timelineMessage.name, timelineMessage.namespace);
            if (this.timelineStorage.doesExist(key)) {
                TimelineEvent timelineEvent = this.timelineStorage.get(timelineMessage.name, timelineMessage.namespace);
                if (timelineEvent.abort(timelineMessage.abortReason, timelineMessage.elapsedRealtime, timelineMessage.currentTimeMillis)) {
                    this.timelineStorage.remove(key);
                    return timelineEvent;
                }
            } else {
                TimelineExceptionLogger.logException(TimelineExceptionLogger.NO_SUCH_TIMELINE_EXCEPTION, timelineMessage.name, timelineMessage.namespace);
            }
        }
        return null;
    }

    private TimelineEvent pause(TimelineMessage timelineMessage) {
        if (!TextUtils.isEmpty(timelineMessage.name) && !TextUtils.isEmpty(timelineMessage.namespace)) {
            if (this.timelineStorage.doesExist(timelineMessage.name, timelineMessage.namespace)) {
                TimelineEvent timelineEvent = this.timelineStorage.get(timelineMessage.name, timelineMessage.namespace);
                if (timelineEvent.pause(timelineMessage.elapsedRealtime, timelineMessage.currentTimeMillis)) {
                    updatePersistance(timelineEvent);
                    return timelineEvent;
                }
            } else {
                TimelineExceptionLogger.logException(TimelineExceptionLogger.NO_SUCH_TIMELINE_EXCEPTION, timelineMessage.name, timelineMessage.namespace);
            }
        }
        return null;
    }

    private MobilyticsEvent recordEvent(TimelineMessage timelineMessage) {
        if (!TextUtils.isEmpty(timelineMessage.name) && !TextUtils.isEmpty(timelineMessage.namespace) && timelineMessage.associatedEvent != null) {
            if (this.timelineStorage.doesExist(timelineMessage.name, timelineMessage.namespace)) {
                TimelineEvent timelineEvent = this.timelineStorage.get(timelineMessage.name, timelineMessage.namespace);
                if (timelineEvent.isValidStateToRecord()) {
                    ((DefaultMobilyticsEvent) timelineMessage.associatedEvent).timelineId(timelineEvent.timelineId());
                    ((DefaultMobilyticsEvent) timelineMessage.associatedEvent).timelineName(timelineEvent.timelineName());
                    ((DefaultMobilyticsEvent) timelineMessage.associatedEvent).timelineNamespace(timelineEvent.timelineNamespace());
                    ((DefaultMobilyticsEvent) timelineMessage.associatedEvent).timelineElapsedDuration(timelineEvent.calculateTimelineElapsedDuration(timelineMessage.elapsedRealtime));
                    timelineEvent.incrementEventCount();
                    return timelineMessage.associatedEvent;
                }
                TimelineExceptionLogger.logIllegalStateToRecord(timelineMessage.associatedEvent, timelineEvent);
            } else {
                TimelineExceptionLogger.logException(TimelineExceptionLogger.NO_SUCH_TIMELINE_EXCEPTION, timelineMessage.name, timelineMessage.namespace);
            }
        }
        return null;
    }

    private TimelineEvent resume(TimelineMessage timelineMessage) {
        if (!TextUtils.isEmpty(timelineMessage.name) && !TextUtils.isEmpty(timelineMessage.namespace)) {
            if (this.timelineStorage.doesExist(timelineMessage.name, timelineMessage.namespace)) {
                TimelineEvent timelineEvent = this.timelineStorage.get(timelineMessage.name, timelineMessage.namespace);
                if (timelineEvent.resume(timelineMessage.elapsedRealtime, timelineMessage.currentTimeMillis)) {
                    updatePersistance(timelineEvent);
                    return timelineEvent;
                }
            } else {
                TimelineExceptionLogger.logException(TimelineExceptionLogger.NO_SUCH_TIMELINE_EXCEPTION, timelineMessage.name, timelineMessage.namespace);
            }
        }
        return null;
    }

    private MobilyticsEvent start(TimelineMessage timelineMessage) {
        if (!TextUtils.isEmpty(timelineMessage.name) && !TextUtils.isEmpty(timelineMessage.namespace)) {
            if (!this.timelineStorage.doesExist(timelineMessage.name, timelineMessage.namespace)) {
                TimelineEvent timelineEvent = new TimelineEvent(timelineMessage.name, timelineMessage.namespace);
                timelineEvent.start(timelineMessage.elapsedRealtime, timelineMessage.currentTimeMillis);
                this.timelineStorage.store(timelineMessage.name, timelineMessage.namespace, timelineEvent);
                return timelineEvent;
            }
            TimelineExceptionLogger.logException(TimelineExceptionLogger.TIMELINE_ALREADY_EXISTS_EXCEPTION, timelineMessage.name, timelineMessage.namespace);
        }
        return null;
    }

    private TimelineEvent stop(TimelineMessage timelineMessage) {
        if (!TextUtils.isEmpty(timelineMessage.name) && !TextUtils.isEmpty(timelineMessage.namespace)) {
            String key = this.timelineStorage.getKey(timelineMessage.name, timelineMessage.namespace);
            if (this.timelineStorage.doesExist(key)) {
                TimelineEvent timelineEvent = this.timelineStorage.get(timelineMessage.name, timelineMessage.namespace);
                if (timelineEvent.stop(timelineMessage.elapsedRealtime, timelineMessage.currentTimeMillis)) {
                    this.timelineStorage.remove(key);
                    return timelineEvent;
                }
            } else {
                TimelineExceptionLogger.logException(TimelineExceptionLogger.NO_SUCH_TIMELINE_EXCEPTION, timelineMessage.name, timelineMessage.namespace);
            }
        }
        return null;
    }

    private void updatePersistance(TimelineEvent timelineEvent) {
        this.timelineStorage.persistanceWrite(timelineEvent);
    }

    public MobilyticsEvent process(TimelineMessage timelineMessage) {
        switch (timelineMessage.action) {
            case 1:
                return start(timelineMessage);
            case 2:
                return pause(timelineMessage);
            case 3:
                return resume(timelineMessage);
            case 4:
                return stop(timelineMessage);
            case 5:
                return abort(timelineMessage);
            case 6:
                return recordEvent(timelineMessage);
            default:
                return null;
        }
    }
}
