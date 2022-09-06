package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/ConcreteQuantitySessionRecord;", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "sessionVersion", "(Ljava/lang/String;Ljava/lang/String;)V", "getSessionId", "()Ljava/lang/String;", "getSessionVersion", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ConcreteQuantitySessionRecord {
    @NotNull
    private final String sessionId;
    @NotNull
    private final String sessionVersion;

    public ConcreteQuantitySessionRecord(@NotNull String sessionId, @NotNull String sessionVersion) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(sessionVersion, "sessionVersion");
        this.sessionId = sessionId;
        this.sessionVersion = sessionVersion;
    }

    public static /* synthetic */ ConcreteQuantitySessionRecord copy$default(ConcreteQuantitySessionRecord concreteQuantitySessionRecord, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = concreteQuantitySessionRecord.sessionId;
        }
        if ((i & 2) != 0) {
            str2 = concreteQuantitySessionRecord.sessionVersion;
        }
        return concreteQuantitySessionRecord.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.sessionId;
    }

    @NotNull
    public final String component2() {
        return this.sessionVersion;
    }

    @NotNull
    public final ConcreteQuantitySessionRecord copy(@NotNull String sessionId, @NotNull String sessionVersion) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(sessionVersion, "sessionVersion");
        return new ConcreteQuantitySessionRecord(sessionId, sessionVersion);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ConcreteQuantitySessionRecord)) {
                return false;
            }
            ConcreteQuantitySessionRecord concreteQuantitySessionRecord = (ConcreteQuantitySessionRecord) obj;
            return Intrinsics.areEqual(this.sessionId, concreteQuantitySessionRecord.sessionId) && Intrinsics.areEqual(this.sessionVersion, concreteQuantitySessionRecord.sessionVersion);
        }
        return true;
    }

    @NotNull
    public final String getSessionId() {
        return this.sessionId;
    }

    @NotNull
    public final String getSessionVersion() {
        return this.sessionVersion;
    }

    public int hashCode() {
        String str = this.sessionId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.sessionVersion;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ConcreteQuantitySessionRecord(sessionId=");
        outline107.append(this.sessionId);
        outline107.append(", sessionVersion=");
        return GeneratedOutlineSupport1.outline91(outline107, this.sessionVersion, ")");
    }
}
