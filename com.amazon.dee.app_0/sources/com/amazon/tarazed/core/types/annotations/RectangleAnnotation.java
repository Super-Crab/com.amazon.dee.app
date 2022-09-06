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
/* compiled from: RectangleAnnotation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B-\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/RectangleAnnotation;", "", "seen1", "", "p0", "Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;", "p1", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILcom/amazon/tarazed/core/types/annotations/AnnotationPoint;Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;)V", "getP0", "()Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;", "getP1", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class RectangleAnnotation {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final AnnotationPoint p0;
    @NotNull
    private final AnnotationPoint p1;

    /* compiled from: RectangleAnnotation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/RectangleAnnotation$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/types/annotations/RectangleAnnotation;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<RectangleAnnotation> serializer() {
            return RectangleAnnotation$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ RectangleAnnotation(int i, @Nullable AnnotationPoint annotationPoint, @Nullable AnnotationPoint annotationPoint2, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.p0 = annotationPoint;
            if ((i & 2) == 0) {
                throw new MissingFieldException("p1");
            }
            this.p1 = annotationPoint2;
            return;
        }
        throw new MissingFieldException("p0");
    }

    public RectangleAnnotation(@NotNull AnnotationPoint p0, @NotNull AnnotationPoint p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        this.p0 = p0;
        this.p1 = p1;
    }

    public static /* synthetic */ RectangleAnnotation copy$default(RectangleAnnotation rectangleAnnotation, AnnotationPoint annotationPoint, AnnotationPoint annotationPoint2, int i, Object obj) {
        if ((i & 1) != 0) {
            annotationPoint = rectangleAnnotation.p0;
        }
        if ((i & 2) != 0) {
            annotationPoint2 = rectangleAnnotation.p1;
        }
        return rectangleAnnotation.copy(annotationPoint, annotationPoint2);
    }

    @JvmStatic
    public static final void write$Self(@NotNull RectangleAnnotation self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, AnnotationPoint$$serializer.INSTANCE, self.p0);
        output.encodeSerializableElement(serialDesc, 1, AnnotationPoint$$serializer.INSTANCE, self.p1);
    }

    @NotNull
    public final AnnotationPoint component1() {
        return this.p0;
    }

    @NotNull
    public final AnnotationPoint component2() {
        return this.p1;
    }

    @NotNull
    public final RectangleAnnotation copy(@NotNull AnnotationPoint p0, @NotNull AnnotationPoint p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        return new RectangleAnnotation(p0, p1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof RectangleAnnotation)) {
                return false;
            }
            RectangleAnnotation rectangleAnnotation = (RectangleAnnotation) obj;
            return Intrinsics.areEqual(this.p0, rectangleAnnotation.p0) && Intrinsics.areEqual(this.p1, rectangleAnnotation.p1);
        }
        return true;
    }

    @NotNull
    public final AnnotationPoint getP0() {
        return this.p0;
    }

    @NotNull
    public final AnnotationPoint getP1() {
        return this.p1;
    }

    public int hashCode() {
        AnnotationPoint annotationPoint = this.p0;
        int i = 0;
        int hashCode = (annotationPoint != null ? annotationPoint.hashCode() : 0) * 31;
        AnnotationPoint annotationPoint2 = this.p1;
        if (annotationPoint2 != null) {
            i = annotationPoint2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RectangleAnnotation(p0=");
        outline107.append(this.p0);
        outline107.append(", p1=");
        outline107.append(this.p1);
        outline107.append(")");
        return outline107.toString();
    }
}
