package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.fitness.api.util.DateTime;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.imap.IMAPStore;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEvent;", "Ljava/io/Serializable;", "eventType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType;", IMAPStore.ID_DATE, "Lcom/amazon/alexa/fitness/api/util/DateTime;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType;Lcom/amazon/alexa/fitness/api/util/DateTime;)V", "getDate", "()Lcom/amazon/alexa/fitness/api/util/DateTime;", "getEventType", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEventType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionEvent implements Serializable {
    @NotNull
    private final DateTime date;
    @NotNull
    private final SessionEventType eventType;

    public SessionEvent(@NotNull SessionEventType eventType, @NotNull DateTime date) {
        Intrinsics.checkParameterIsNotNull(eventType, "eventType");
        Intrinsics.checkParameterIsNotNull(date, "date");
        this.eventType = eventType;
        this.date = date;
    }

    public static /* synthetic */ SessionEvent copy$default(SessionEvent sessionEvent, SessionEventType sessionEventType, DateTime dateTime, int i, Object obj) {
        if ((i & 1) != 0) {
            sessionEventType = sessionEvent.eventType;
        }
        if ((i & 2) != 0) {
            dateTime = sessionEvent.date;
        }
        return sessionEvent.copy(sessionEventType, dateTime);
    }

    @NotNull
    public final SessionEventType component1() {
        return this.eventType;
    }

    @NotNull
    public final DateTime component2() {
        return this.date;
    }

    @NotNull
    public final SessionEvent copy(@NotNull SessionEventType eventType, @NotNull DateTime date) {
        Intrinsics.checkParameterIsNotNull(eventType, "eventType");
        Intrinsics.checkParameterIsNotNull(date, "date");
        return new SessionEvent(eventType, date);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SessionEvent)) {
                return false;
            }
            SessionEvent sessionEvent = (SessionEvent) obj;
            return Intrinsics.areEqual(this.eventType, sessionEvent.eventType) && Intrinsics.areEqual(this.date, sessionEvent.date);
        }
        return true;
    }

    @NotNull
    public final DateTime getDate() {
        return this.date;
    }

    @NotNull
    public final SessionEventType getEventType() {
        return this.eventType;
    }

    public int hashCode() {
        SessionEventType sessionEventType = this.eventType;
        int i = 0;
        int hashCode = (sessionEventType != null ? sessionEventType.hashCode() : 0) * 31;
        DateTime dateTime = this.date;
        if (dateTime != null) {
            i = dateTime.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SessionEvent(eventType=");
        outline107.append(this.eventType);
        outline107.append(", date=");
        outline107.append(this.date);
        outline107.append(")");
        return outline107.toString();
    }
}
