package com.amazon.alexa.mobilytics.event;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.metadata.EventMetadata;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import java.util.Collection;
/* loaded from: classes9.dex */
public interface MobilyticsEvent {
    String getChannelName();

    @NonNull
    String getComponent();

    @Nullable
    String getContentId();

    @Nullable
    String getContentProvider();

    @Nullable
    String getContentType();

    @Nullable
    String getContentVersion();

    @NonNull
    String getEventId();

    @Nullable
    Collection<EventMetadata> getEventMetadata();

    @NonNull
    String getEventName();

    long getEventTimestamp();

    String getEventType();

    @Nullable
    String getInputType();

    String getOwnerIdentifier();

    @Nullable
    String getSourceContext();

    @Nullable
    String getSubComponent();

    void invalidateEvent();

    boolean isInvalidated();

    @Nullable
    default MobilyticsSession session() {
        return null;
    }

    void setChannelName(String str);

    @NonNull
    void setComponent(String str);

    @Nullable
    void setContentId(String str);

    @Nullable
    void setContentProvider(String str);

    @Nullable
    void setContentType(String str);

    @Nullable
    void setContentVersion(String str);

    @NonNull
    void setEventId(String str);

    @Nullable
    void setEventMetadata(Collection<EventMetadata> collection);

    @NonNull
    void setEventName(String str);

    void setEventTimestamp(long j);

    @Nullable
    void setInputType(String str);

    default void setSession(MobilyticsSession mobilyticsSession) {
    }

    @Nullable
    void setSourceContext(String str);

    @NonNull
    void setSubComponent(String str);

    long timelineElapsedDuration();

    String timelineId();

    String timelineName();

    String timelineNamespace();
}
