package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionSummaryCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003JK\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0007HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/amazon/alexa/fitness/repository/PendingSessionSummary;", "Ljava/io/Serializable;", "sessionSummary", "Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;", "profileDirectedId", "", "failedUploadAttempts", "", "firstFailedTime", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "lastFailedTime", "lastException", "(Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;Ljava/lang/String;ILcom/amazon/alexa/fitness/api/util/DateTime;Lcom/amazon/alexa/fitness/api/util/DateTime;Ljava/lang/String;)V", "getFailedUploadAttempts", "()I", "getFirstFailedTime", "()Lcom/amazon/alexa/fitness/api/util/DateTime;", "getLastException", "()Ljava/lang/String;", "getLastFailedTime", "getProfileDirectedId", "getSessionSummary", "()Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class PendingSessionSummary implements Serializable {
    private final int failedUploadAttempts;
    @Nullable
    private final DateTime firstFailedTime;
    @Nullable
    private final String lastException;
    @Nullable
    private final DateTime lastFailedTime;
    @NotNull
    private final String profileDirectedId;
    @NotNull
    private final SessionSummary sessionSummary;

    public PendingSessionSummary(@NotNull SessionSummary sessionSummary, @NotNull String profileDirectedId, int i, @Nullable DateTime dateTime, @Nullable DateTime dateTime2, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(sessionSummary, "sessionSummary");
        Intrinsics.checkParameterIsNotNull(profileDirectedId, "profileDirectedId");
        this.sessionSummary = sessionSummary;
        this.profileDirectedId = profileDirectedId;
        this.failedUploadAttempts = i;
        this.firstFailedTime = dateTime;
        this.lastFailedTime = dateTime2;
        this.lastException = str;
    }

    public static /* synthetic */ PendingSessionSummary copy$default(PendingSessionSummary pendingSessionSummary, SessionSummary sessionSummary, String str, int i, DateTime dateTime, DateTime dateTime2, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            sessionSummary = pendingSessionSummary.sessionSummary;
        }
        if ((i2 & 2) != 0) {
            str = pendingSessionSummary.profileDirectedId;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            i = pendingSessionSummary.failedUploadAttempts;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            dateTime = pendingSessionSummary.firstFailedTime;
        }
        DateTime dateTime3 = dateTime;
        if ((i2 & 16) != 0) {
            dateTime2 = pendingSessionSummary.lastFailedTime;
        }
        DateTime dateTime4 = dateTime2;
        if ((i2 & 32) != 0) {
            str2 = pendingSessionSummary.lastException;
        }
        return pendingSessionSummary.copy(sessionSummary, str3, i3, dateTime3, dateTime4, str2);
    }

    @NotNull
    public final SessionSummary component1() {
        return this.sessionSummary;
    }

    @NotNull
    public final String component2() {
        return this.profileDirectedId;
    }

    public final int component3() {
        return this.failedUploadAttempts;
    }

    @Nullable
    public final DateTime component4() {
        return this.firstFailedTime;
    }

    @Nullable
    public final DateTime component5() {
        return this.lastFailedTime;
    }

    @Nullable
    public final String component6() {
        return this.lastException;
    }

    @NotNull
    public final PendingSessionSummary copy(@NotNull SessionSummary sessionSummary, @NotNull String profileDirectedId, int i, @Nullable DateTime dateTime, @Nullable DateTime dateTime2, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(sessionSummary, "sessionSummary");
        Intrinsics.checkParameterIsNotNull(profileDirectedId, "profileDirectedId");
        return new PendingSessionSummary(sessionSummary, profileDirectedId, i, dateTime, dateTime2, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof PendingSessionSummary)) {
                return false;
            }
            PendingSessionSummary pendingSessionSummary = (PendingSessionSummary) obj;
            return Intrinsics.areEqual(this.sessionSummary, pendingSessionSummary.sessionSummary) && Intrinsics.areEqual(this.profileDirectedId, pendingSessionSummary.profileDirectedId) && this.failedUploadAttempts == pendingSessionSummary.failedUploadAttempts && Intrinsics.areEqual(this.firstFailedTime, pendingSessionSummary.firstFailedTime) && Intrinsics.areEqual(this.lastFailedTime, pendingSessionSummary.lastFailedTime) && Intrinsics.areEqual(this.lastException, pendingSessionSummary.lastException);
        }
        return true;
    }

    public final int getFailedUploadAttempts() {
        return this.failedUploadAttempts;
    }

    @Nullable
    public final DateTime getFirstFailedTime() {
        return this.firstFailedTime;
    }

    @Nullable
    public final String getLastException() {
        return this.lastException;
    }

    @Nullable
    public final DateTime getLastFailedTime() {
        return this.lastFailedTime;
    }

    @NotNull
    public final String getProfileDirectedId() {
        return this.profileDirectedId;
    }

    @NotNull
    public final SessionSummary getSessionSummary() {
        return this.sessionSummary;
    }

    public int hashCode() {
        SessionSummary sessionSummary = this.sessionSummary;
        int i = 0;
        int hashCode = (sessionSummary != null ? sessionSummary.hashCode() : 0) * 31;
        String str = this.profileDirectedId;
        int hashCode2 = (((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.failedUploadAttempts) * 31;
        DateTime dateTime = this.firstFailedTime;
        int hashCode3 = (hashCode2 + (dateTime != null ? dateTime.hashCode() : 0)) * 31;
        DateTime dateTime2 = this.lastFailedTime;
        int hashCode4 = (hashCode3 + (dateTime2 != null ? dateTime2.hashCode() : 0)) * 31;
        String str2 = this.lastException;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PendingSessionSummary(sessionSummary=");
        outline107.append(this.sessionSummary);
        outline107.append(", profileDirectedId=");
        outline107.append(this.profileDirectedId);
        outline107.append(", failedUploadAttempts=");
        outline107.append(this.failedUploadAttempts);
        outline107.append(", firstFailedTime=");
        outline107.append(this.firstFailedTime);
        outline107.append(", lastFailedTime=");
        outline107.append(this.lastFailedTime);
        outline107.append(", lastException=");
        return GeneratedOutlineSupport1.outline91(outline107, this.lastException, ")");
    }
}
