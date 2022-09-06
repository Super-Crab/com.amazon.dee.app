package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.model.biometric.FitnessSessionSummary;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionSummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010#\u001a\u00020\rHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003Ja\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010&\u001a\u00020\r2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006,"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;", "Ljava/io/Serializable;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "userIdentityDirectedId", "", "startTime", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "endTime", "version", "fitnessSessionSummary", "Lcom/amazon/alexa/fitness/model/biometric/FitnessSessionSummary;", "shouldCaloriesBeReported", "", "deviceType", "(Ljava/util/UUID;Ljava/lang/String;Lcom/amazon/alexa/fitness/api/util/DateTime;Lcom/amazon/alexa/fitness/api/util/DateTime;Ljava/lang/String;Lcom/amazon/alexa/fitness/model/biometric/FitnessSessionSummary;ZLjava/lang/String;)V", "getDeviceType", "()Ljava/lang/String;", "getEndTime", "()Lcom/amazon/alexa/fitness/api/util/DateTime;", "getFitnessSessionSummary", "()Lcom/amazon/alexa/fitness/model/biometric/FitnessSessionSummary;", "getSessionId", "()Ljava/util/UUID;", "getShouldCaloriesBeReported", "()Z", "getStartTime", "getUserIdentityDirectedId", "getVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionSummary implements Serializable {
    @Nullable
    private final String deviceType;
    @Nullable
    private final DateTime endTime;
    @Nullable
    private final FitnessSessionSummary fitnessSessionSummary;
    @NotNull
    private final UUID sessionId;
    private final boolean shouldCaloriesBeReported;
    @NotNull
    private final DateTime startTime;
    @NotNull
    private final String userIdentityDirectedId;
    @Nullable
    private final String version;

    public SessionSummary(@NotNull UUID sessionId, @NotNull String userIdentityDirectedId, @NotNull DateTime startTime, @Nullable DateTime dateTime, @Nullable String str, @Nullable FitnessSessionSummary fitnessSessionSummary, boolean z, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(userIdentityDirectedId, "userIdentityDirectedId");
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        this.sessionId = sessionId;
        this.userIdentityDirectedId = userIdentityDirectedId;
        this.startTime = startTime;
        this.endTime = dateTime;
        this.version = str;
        this.fitnessSessionSummary = fitnessSessionSummary;
        this.shouldCaloriesBeReported = z;
        this.deviceType = str2;
    }

    @NotNull
    public final UUID component1() {
        return this.sessionId;
    }

    @NotNull
    public final String component2() {
        return this.userIdentityDirectedId;
    }

    @NotNull
    public final DateTime component3() {
        return this.startTime;
    }

    @Nullable
    public final DateTime component4() {
        return this.endTime;
    }

    @Nullable
    public final String component5() {
        return this.version;
    }

    @Nullable
    public final FitnessSessionSummary component6() {
        return this.fitnessSessionSummary;
    }

    public final boolean component7() {
        return this.shouldCaloriesBeReported;
    }

    @Nullable
    public final String component8() {
        return this.deviceType;
    }

    @NotNull
    public final SessionSummary copy(@NotNull UUID sessionId, @NotNull String userIdentityDirectedId, @NotNull DateTime startTime, @Nullable DateTime dateTime, @Nullable String str, @Nullable FitnessSessionSummary fitnessSessionSummary, boolean z, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(userIdentityDirectedId, "userIdentityDirectedId");
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        return new SessionSummary(sessionId, userIdentityDirectedId, startTime, dateTime, str, fitnessSessionSummary, z, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SessionSummary)) {
                return false;
            }
            SessionSummary sessionSummary = (SessionSummary) obj;
            return Intrinsics.areEqual(this.sessionId, sessionSummary.sessionId) && Intrinsics.areEqual(this.userIdentityDirectedId, sessionSummary.userIdentityDirectedId) && Intrinsics.areEqual(this.startTime, sessionSummary.startTime) && Intrinsics.areEqual(this.endTime, sessionSummary.endTime) && Intrinsics.areEqual(this.version, sessionSummary.version) && Intrinsics.areEqual(this.fitnessSessionSummary, sessionSummary.fitnessSessionSummary) && this.shouldCaloriesBeReported == sessionSummary.shouldCaloriesBeReported && Intrinsics.areEqual(this.deviceType, sessionSummary.deviceType);
        }
        return true;
    }

    @Nullable
    public final String getDeviceType() {
        return this.deviceType;
    }

    @Nullable
    public final DateTime getEndTime() {
        return this.endTime;
    }

    @Nullable
    public final FitnessSessionSummary getFitnessSessionSummary() {
        return this.fitnessSessionSummary;
    }

    @NotNull
    public final UUID getSessionId() {
        return this.sessionId;
    }

    public final boolean getShouldCaloriesBeReported() {
        return this.shouldCaloriesBeReported;
    }

    @NotNull
    public final DateTime getStartTime() {
        return this.startTime;
    }

    @NotNull
    public final String getUserIdentityDirectedId() {
        return this.userIdentityDirectedId;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        UUID uuid = this.sessionId;
        int i = 0;
        int hashCode = (uuid != null ? uuid.hashCode() : 0) * 31;
        String str = this.userIdentityDirectedId;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        DateTime dateTime = this.startTime;
        int hashCode3 = (hashCode2 + (dateTime != null ? dateTime.hashCode() : 0)) * 31;
        DateTime dateTime2 = this.endTime;
        int hashCode4 = (hashCode3 + (dateTime2 != null ? dateTime2.hashCode() : 0)) * 31;
        String str2 = this.version;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        FitnessSessionSummary fitnessSessionSummary = this.fitnessSessionSummary;
        int hashCode6 = (hashCode5 + (fitnessSessionSummary != null ? fitnessSessionSummary.hashCode() : 0)) * 31;
        boolean z = this.shouldCaloriesBeReported;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = (hashCode6 + i2) * 31;
        String str3 = this.deviceType;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return i4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SessionSummary(sessionId=");
        outline107.append(this.sessionId);
        outline107.append(", userIdentityDirectedId=");
        outline107.append(this.userIdentityDirectedId);
        outline107.append(", startTime=");
        outline107.append(this.startTime);
        outline107.append(", endTime=");
        outline107.append(this.endTime);
        outline107.append(", version=");
        outline107.append(this.version);
        outline107.append(", fitnessSessionSummary=");
        outline107.append(this.fitnessSessionSummary);
        outline107.append(", shouldCaloriesBeReported=");
        outline107.append(this.shouldCaloriesBeReported);
        outline107.append(", deviceType=");
        return GeneratedOutlineSupport1.outline91(outline107, this.deviceType, ")");
    }
}
