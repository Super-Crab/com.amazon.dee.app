package com.amazon.tarazed.core.types.rotation;

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
/* compiled from: RotationEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017B)\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/amazon/tarazed/core/types/rotation/RotationEvent;", "", "seen1", "", "sequence", "degrees", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(IIILkotlinx/serialization/SerializationConstructorMarker;)V", "(II)V", "getDegrees", "()I", "getSequence", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class RotationEvent {
    public static final Companion Companion = new Companion(null);
    private final int degrees;
    private final int sequence;

    /* compiled from: RotationEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/types/rotation/RotationEvent$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/types/rotation/RotationEvent;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<RotationEvent> serializer() {
            return RotationEvent$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RotationEvent(int i, int i2) {
        this.sequence = i;
        this.degrees = i2;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ RotationEvent(int i, int i2, int i3, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.sequence = i2;
            if ((i & 2) == 0) {
                throw new MissingFieldException("degrees");
            }
            this.degrees = i3;
            return;
        }
        throw new MissingFieldException("sequence");
    }

    public static /* synthetic */ RotationEvent copy$default(RotationEvent rotationEvent, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = rotationEvent.sequence;
        }
        if ((i3 & 2) != 0) {
            i2 = rotationEvent.degrees;
        }
        return rotationEvent.copy(i, i2);
    }

    @JvmStatic
    public static final void write$Self(@NotNull RotationEvent self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeIntElement(serialDesc, 0, self.sequence);
        output.encodeIntElement(serialDesc, 1, self.degrees);
    }

    public final int component1() {
        return this.sequence;
    }

    public final int component2() {
        return this.degrees;
    }

    @NotNull
    public final RotationEvent copy(int i, int i2) {
        return new RotationEvent(i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof RotationEvent)) {
                return false;
            }
            RotationEvent rotationEvent = (RotationEvent) obj;
            return this.sequence == rotationEvent.sequence && this.degrees == rotationEvent.degrees;
        }
        return true;
    }

    public final int getDegrees() {
        return this.degrees;
    }

    public final int getSequence() {
        return this.sequence;
    }

    public int hashCode() {
        return Integer.hashCode(this.degrees) + (Integer.hashCode(this.sequence) * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RotationEvent(sequence=");
        outline107.append(this.sequence);
        outline107.append(", degrees=");
        return GeneratedOutlineSupport1.outline86(outline107, this.degrees, ")");
    }
}
