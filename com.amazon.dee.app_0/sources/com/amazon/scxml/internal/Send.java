package com.amazon.scxml.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Send.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u000fJN\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0005HÖ\u0003J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006("}, d2 = {"Lcom/amazon/scxml/internal/Send;", "Lcom/amazon/scxml/internal/Executable;", "event", "", "data", "", "target", "type", "sendId", "delay", "", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;)V", "getData", "()Ljava/lang/Object;", "getDelay", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getEvent", "()Ljava/lang/String;", "getSendId", "getTarget", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;)Lcom/amazon/scxml/internal/Send;", "equals", "", "other", "execute", "", "machine", "Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Send implements Executable {
    @Nullable
    private final Object data;
    @Nullable
    private final Double delay;
    @NotNull
    private final String event;
    @NotNull
    private final String sendId;
    @NotNull
    private final String target;
    @NotNull
    private final String type;

    public Send(@NotNull String event, @Nullable Object obj, @NotNull String target, @NotNull String type, @NotNull String sendId, @Nullable Double d) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(sendId, "sendId");
        this.event = event;
        this.data = obj;
        this.target = target;
        this.type = type;
        this.sendId = sendId;
        this.delay = d;
    }

    public static /* synthetic */ Send copy$default(Send send, String str, Object obj, String str2, String str3, String str4, Double d, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = send.event;
        }
        if ((i & 2) != 0) {
            obj = send.data;
        }
        Object obj3 = obj;
        if ((i & 4) != 0) {
            str2 = send.target;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = send.type;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            str4 = send.sendId;
        }
        String str7 = str4;
        if ((i & 32) != 0) {
            d = send.delay;
        }
        return send.copy(str, obj3, str5, str6, str7, d);
    }

    @NotNull
    public final String component1() {
        return this.event;
    }

    @Nullable
    public final Object component2() {
        return this.data;
    }

    @NotNull
    public final String component3() {
        return this.target;
    }

    @NotNull
    public final String component4() {
        return this.type;
    }

    @NotNull
    public final String component5() {
        return this.sendId;
    }

    @Nullable
    public final Double component6() {
        return this.delay;
    }

    @NotNull
    public final Send copy(@NotNull String event, @Nullable Object obj, @NotNull String target, @NotNull String type, @NotNull String sendId, @Nullable Double d) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(sendId, "sendId");
        return new Send(event, obj, target, type, sendId, d);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Send)) {
                return false;
            }
            Send send = (Send) obj;
            return Intrinsics.areEqual(this.event, send.event) && Intrinsics.areEqual(this.data, send.data) && Intrinsics.areEqual(this.target, send.target) && Intrinsics.areEqual(this.type, send.type) && Intrinsics.areEqual(this.sendId, send.sendId) && Intrinsics.areEqual((Object) this.delay, (Object) send.delay);
        }
        return true;
    }

    @Override // com.amazon.scxml.internal.Executable
    public void execute(@NotNull SCXMLStateMachineExecutor machine) {
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        machine.execute(this);
    }

    @Nullable
    public final Object getData() {
        return this.data;
    }

    @Nullable
    public final Double getDelay() {
        return this.delay;
    }

    @NotNull
    public final String getEvent() {
        return this.event;
    }

    @NotNull
    public final String getSendId() {
        return this.sendId;
    }

    @NotNull
    public final String getTarget() {
        return this.target;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.event;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Object obj = this.data;
        int hashCode2 = (hashCode + (obj != null ? obj.hashCode() : 0)) * 31;
        String str2 = this.target;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.type;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.sendId;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        Double d = this.delay;
        if (d != null) {
            i = d.hashCode();
        }
        return hashCode5 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Send(event=");
        outline107.append(this.event);
        outline107.append(", data=");
        outline107.append(this.data);
        outline107.append(", target=");
        outline107.append(this.target);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(", sendId=");
        outline107.append(this.sendId);
        outline107.append(", delay=");
        outline107.append(this.delay);
        outline107.append(")");
        return outline107.toString();
    }
}
