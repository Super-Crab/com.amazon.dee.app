package com.amazon.alexa.fitness.api.afits;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/DeleteFitnessSessionRequest;", "", "commsId", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "version", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCommsId", "()Ljava/lang/String;", "getSessionId", "getVersion", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DeleteFitnessSessionRequest {
    @NotNull
    private final String commsId;
    @NotNull
    private final String sessionId;
    @NotNull
    private final String version;

    public DeleteFitnessSessionRequest(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        GeneratedOutlineSupport1.outline169(str, "commsId", str2, AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, str3, "version");
        this.commsId = str;
        this.sessionId = str2;
        this.version = str3;
    }

    public static /* synthetic */ DeleteFitnessSessionRequest copy$default(DeleteFitnessSessionRequest deleteFitnessSessionRequest, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deleteFitnessSessionRequest.commsId;
        }
        if ((i & 2) != 0) {
            str2 = deleteFitnessSessionRequest.sessionId;
        }
        if ((i & 4) != 0) {
            str3 = deleteFitnessSessionRequest.version;
        }
        return deleteFitnessSessionRequest.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.commsId;
    }

    @NotNull
    public final String component2() {
        return this.sessionId;
    }

    @NotNull
    public final String component3() {
        return this.version;
    }

    @NotNull
    public final DeleteFitnessSessionRequest copy(@NotNull String commsId, @NotNull String sessionId, @NotNull String version) {
        Intrinsics.checkParameterIsNotNull(commsId, "commsId");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(version, "version");
        return new DeleteFitnessSessionRequest(commsId, sessionId, version);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DeleteFitnessSessionRequest)) {
                return false;
            }
            DeleteFitnessSessionRequest deleteFitnessSessionRequest = (DeleteFitnessSessionRequest) obj;
            return Intrinsics.areEqual(this.commsId, deleteFitnessSessionRequest.commsId) && Intrinsics.areEqual(this.sessionId, deleteFitnessSessionRequest.sessionId) && Intrinsics.areEqual(this.version, deleteFitnessSessionRequest.version);
        }
        return true;
    }

    @NotNull
    public final String getCommsId() {
        return this.commsId;
    }

    @NotNull
    public final String getSessionId() {
        return this.sessionId;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.commsId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.sessionId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.version;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeleteFitnessSessionRequest(commsId=");
        outline107.append(this.commsId);
        outline107.append(", sessionId=");
        outline107.append(this.sessionId);
        outline107.append(", version=");
        return GeneratedOutlineSupport1.outline91(outline107, this.version, ")");
    }
}
