package com.amazon.tarazed.core.notification.type;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedLaunchRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB5\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\fJ\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;", "", "seen1", "", "notificationTime", "", "sessionEndpoint", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(JLjava/lang/String;Ljava/lang/String;)V", "getNotificationTime", "()J", "getSessionEndpoint", "()Ljava/lang/String;", "getSessionId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class TarazedLaunchRequest {
    public static final Companion Companion = new Companion(null);
    private final long notificationTime;
    @NotNull
    private final String sessionEndpoint;
    @NotNull
    private final String sessionId;

    /* compiled from: TarazedLaunchRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<TarazedLaunchRequest> serializer() {
            return TarazedLaunchRequest$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TarazedLaunchRequest(int i, long j, @Nullable String str, @Nullable String str2, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.notificationTime = j;
            if ((i & 2) == 0) {
                throw new MissingFieldException("sessionEndpoint");
            }
            this.sessionEndpoint = str;
            if ((i & 4) == 0) {
                throw new MissingFieldException(AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY);
            }
            this.sessionId = str2;
            return;
        }
        throw new MissingFieldException("notificationTime");
    }

    public TarazedLaunchRequest(long j, @NotNull String sessionEndpoint, @NotNull String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionEndpoint, "sessionEndpoint");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        this.notificationTime = j;
        this.sessionEndpoint = sessionEndpoint;
        this.sessionId = sessionId;
    }

    public static /* synthetic */ TarazedLaunchRequest copy$default(TarazedLaunchRequest tarazedLaunchRequest, long j, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = tarazedLaunchRequest.notificationTime;
        }
        if ((i & 2) != 0) {
            str = tarazedLaunchRequest.sessionEndpoint;
        }
        if ((i & 4) != 0) {
            str2 = tarazedLaunchRequest.sessionId;
        }
        return tarazedLaunchRequest.copy(j, str, str2);
    }

    @JvmStatic
    public static final void write$Self(@NotNull TarazedLaunchRequest self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeLongElement(serialDesc, 0, self.notificationTime);
        output.encodeStringElement(serialDesc, 1, self.sessionEndpoint);
        output.encodeStringElement(serialDesc, 2, self.sessionId);
    }

    public final long component1() {
        return this.notificationTime;
    }

    @NotNull
    public final String component2() {
        return this.sessionEndpoint;
    }

    @NotNull
    public final String component3() {
        return this.sessionId;
    }

    @NotNull
    public final TarazedLaunchRequest copy(long j, @NotNull String sessionEndpoint, @NotNull String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionEndpoint, "sessionEndpoint");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        return new TarazedLaunchRequest(j, sessionEndpoint, sessionId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof TarazedLaunchRequest)) {
                return false;
            }
            TarazedLaunchRequest tarazedLaunchRequest = (TarazedLaunchRequest) obj;
            return this.notificationTime == tarazedLaunchRequest.notificationTime && Intrinsics.areEqual(this.sessionEndpoint, tarazedLaunchRequest.sessionEndpoint) && Intrinsics.areEqual(this.sessionId, tarazedLaunchRequest.sessionId);
        }
        return true;
    }

    public final long getNotificationTime() {
        return this.notificationTime;
    }

    @NotNull
    public final String getSessionEndpoint() {
        return this.sessionEndpoint;
    }

    @NotNull
    public final String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        int hashCode = Long.hashCode(this.notificationTime) * 31;
        String str = this.sessionEndpoint;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.sessionId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TarazedLaunchRequest(notificationTime=");
        outline107.append(this.notificationTime);
        outline107.append(", sessionEndpoint=");
        outline107.append(this.sessionEndpoint);
        outline107.append(", sessionId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.sessionId, ")");
    }
}
