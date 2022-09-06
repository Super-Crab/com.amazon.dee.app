package com.amazon.tarazed.core.signaling.events;

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
/* compiled from: TarazedEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u0000 \u001b*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u001a\u001bB-\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u000e\u0010\u0012\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ(\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0007\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "PayloadType", "", "seen1", "", "type", "", "payload", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Object;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/Object;)V", "getPayload", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getType", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Object;)Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "equals", "", "other", "hashCode", "toString", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class TarazedEvent<PayloadType> {
    public static final Companion Companion = new Companion(null);
    private final PayloadType payload;
    @NotNull
    private final String type;

    /* compiled from: TarazedEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u0004\"\u0004\b\u0001\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004HÆ\u0001¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/core/signaling/events/TarazedEvent$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "T0", "typeSerial0", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final <T0> KSerializer<TarazedEvent<T0>> serializer(@NotNull KSerializer<T0> typeSerial0) {
            Intrinsics.checkParameterIsNotNull(typeSerial0, "typeSerial0");
            return new TarazedEvent$$serializer(typeSerial0);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TarazedEvent(int i, @Nullable String str, @Nullable PayloadType payloadtype, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.type = str;
            if ((i & 2) == 0) {
                throw new MissingFieldException("payload");
            }
            this.payload = payloadtype;
            return;
        }
        throw new MissingFieldException("type");
    }

    public TarazedEvent(@NotNull String type, PayloadType payloadtype) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
        this.payload = payloadtype;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TarazedEvent copy$default(TarazedEvent tarazedEvent, String str, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = tarazedEvent.type;
        }
        if ((i & 2) != 0) {
            obj = tarazedEvent.payload;
        }
        return tarazedEvent.copy(str, obj);
    }

    @JvmStatic
    public static final <T0> void write$Self(@NotNull TarazedEvent<? extends T0> self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc, @NotNull KSerializer<T0> typeSerial0) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        Intrinsics.checkParameterIsNotNull(typeSerial0, "typeSerial0");
        output.encodeStringElement(serialDesc, 0, ((TarazedEvent) self).type);
        output.encodeSerializableElement(serialDesc, 1, typeSerial0, ((TarazedEvent) self).payload);
    }

    @NotNull
    public final String component1() {
        return this.type;
    }

    public final PayloadType component2() {
        return this.payload;
    }

    @NotNull
    public final TarazedEvent<PayloadType> copy(@NotNull String type, PayloadType payloadtype) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new TarazedEvent<>(type, payloadtype);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof TarazedEvent)) {
                return false;
            }
            TarazedEvent tarazedEvent = (TarazedEvent) obj;
            return Intrinsics.areEqual(this.type, tarazedEvent.type) && Intrinsics.areEqual(this.payload, tarazedEvent.payload);
        }
        return true;
    }

    public final PayloadType getPayload() {
        return this.payload;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.type;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        PayloadType payloadtype = this.payload;
        if (payloadtype != null) {
            i = payloadtype.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TarazedEvent(type=");
        outline107.append(this.type);
        outline107.append(", payload=");
        return GeneratedOutlineSupport1.outline88(outline107, this.payload, ")");
    }
}
