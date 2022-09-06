package com.amazon.tarazed.core.types.annotations;

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
/* compiled from: AnnotationColor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u001a2\u00020\u0001:\u0002\u0019\u001aB1\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;", "", "seen1", "", "r", "g", "b", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(IIIILkotlinx/serialization/SerializationConstructorMarker;)V", "(III)V", "getB", "()I", "getG", "getR", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class AnnotationColor {
    public static final Companion Companion = new Companion(null);
    private final int b;
    private final int g;
    private final int r;

    /* compiled from: AnnotationColor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/AnnotationColor$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<AnnotationColor> serializer() {
            return AnnotationColor$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AnnotationColor(int i, int i2, int i3) {
        this.r = i;
        this.g = i2;
        this.b = i3;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ AnnotationColor(int i, int i2, int i3, int i4, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.r = i2;
            if ((i & 2) == 0) {
                throw new MissingFieldException("g");
            }
            this.g = i3;
            if ((i & 4) == 0) {
                throw new MissingFieldException("b");
            }
            this.b = i4;
            return;
        }
        throw new MissingFieldException("r");
    }

    public static /* synthetic */ AnnotationColor copy$default(AnnotationColor annotationColor, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = annotationColor.r;
        }
        if ((i4 & 2) != 0) {
            i2 = annotationColor.g;
        }
        if ((i4 & 4) != 0) {
            i3 = annotationColor.b;
        }
        return annotationColor.copy(i, i2, i3);
    }

    @JvmStatic
    public static final void write$Self(@NotNull AnnotationColor self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeIntElement(serialDesc, 0, self.r);
        output.encodeIntElement(serialDesc, 1, self.g);
        output.encodeIntElement(serialDesc, 2, self.b);
    }

    public final int component1() {
        return this.r;
    }

    public final int component2() {
        return this.g;
    }

    public final int component3() {
        return this.b;
    }

    @NotNull
    public final AnnotationColor copy(int i, int i2, int i3) {
        return new AnnotationColor(i, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AnnotationColor)) {
                return false;
            }
            AnnotationColor annotationColor = (AnnotationColor) obj;
            return this.r == annotationColor.r && this.g == annotationColor.g && this.b == annotationColor.b;
        }
        return true;
    }

    public final int getB() {
        return this.b;
    }

    public final int getG() {
        return this.g;
    }

    public final int getR() {
        return this.r;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.g);
        return Integer.hashCode(this.b) + ((hashCode + (Integer.hashCode(this.r) * 31)) * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AnnotationColor(r=");
        outline107.append(this.r);
        outline107.append(", g=");
        outline107.append(this.g);
        outline107.append(", b=");
        return GeneratedOutlineSupport1.outline86(outline107, this.b, ")");
    }
}
