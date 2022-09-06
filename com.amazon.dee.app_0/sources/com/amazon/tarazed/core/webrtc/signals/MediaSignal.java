package com.amazon.tarazed.core.webrtc.signals;

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
/* compiled from: MediaSignal.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0081\b\u0018\u0000 \u001b*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u001a\u001bB-\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u000e\u0010\u0012\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ(\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0007\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/signals/MediaSignal;", "SignalPayload", "", "seen1", "", "signalType", "", "signalPayload", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Object;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/Object;)V", "getSignalPayload", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSignalType", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Object;)Lcom/amazon/tarazed/core/webrtc/signals/MediaSignal;", "equals", "", "other", "hashCode", "toString", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class MediaSignal<SignalPayload> {
    public static final Companion Companion = new Companion(null);
    private final SignalPayload signalPayload;
    @NotNull
    private final String signalType;

    /* compiled from: MediaSignal.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u0004\"\u0004\b\u0001\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004HÆ\u0001¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/signals/MediaSignal$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/webrtc/signals/MediaSignal;", "T0", "typeSerial0", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final <T0> KSerializer<MediaSignal<T0>> serializer(@NotNull KSerializer<T0> typeSerial0) {
            Intrinsics.checkParameterIsNotNull(typeSerial0, "typeSerial0");
            return new MediaSignal$$serializer(typeSerial0);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ MediaSignal(int i, @Nullable String str, @Nullable SignalPayload signalpayload, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.signalType = str;
            if ((i & 2) == 0) {
                throw new MissingFieldException("signalPayload");
            }
            this.signalPayload = signalpayload;
            return;
        }
        throw new MissingFieldException("signalType");
    }

    public MediaSignal(@NotNull String signalType, SignalPayload signalpayload) {
        Intrinsics.checkParameterIsNotNull(signalType, "signalType");
        this.signalType = signalType;
        this.signalPayload = signalpayload;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MediaSignal copy$default(MediaSignal mediaSignal, String str, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = mediaSignal.signalType;
        }
        if ((i & 2) != 0) {
            obj = mediaSignal.signalPayload;
        }
        return mediaSignal.copy(str, obj);
    }

    @JvmStatic
    public static final <T0> void write$Self(@NotNull MediaSignal<T0> self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc, @NotNull KSerializer<T0> typeSerial0) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        Intrinsics.checkParameterIsNotNull(typeSerial0, "typeSerial0");
        output.encodeStringElement(serialDesc, 0, ((MediaSignal) self).signalType);
        output.encodeSerializableElement(serialDesc, 1, typeSerial0, ((MediaSignal) self).signalPayload);
    }

    @NotNull
    public final String component1() {
        return this.signalType;
    }

    public final SignalPayload component2() {
        return this.signalPayload;
    }

    @NotNull
    public final MediaSignal<SignalPayload> copy(@NotNull String signalType, SignalPayload signalpayload) {
        Intrinsics.checkParameterIsNotNull(signalType, "signalType");
        return new MediaSignal<>(signalType, signalpayload);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof MediaSignal)) {
                return false;
            }
            MediaSignal mediaSignal = (MediaSignal) obj;
            return Intrinsics.areEqual(this.signalType, mediaSignal.signalType) && Intrinsics.areEqual(this.signalPayload, mediaSignal.signalPayload);
        }
        return true;
    }

    public final SignalPayload getSignalPayload() {
        return this.signalPayload;
    }

    @NotNull
    public final String getSignalType() {
        return this.signalType;
    }

    public int hashCode() {
        String str = this.signalType;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        SignalPayload signalpayload = this.signalPayload;
        if (signalpayload != null) {
            i = signalpayload.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaSignal(signalType=");
        outline107.append(this.signalType);
        outline107.append(", signalPayload=");
        return GeneratedOutlineSupport1.outline88(outline107, this.signalPayload, ")");
    }
}
