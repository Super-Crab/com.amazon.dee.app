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
import kotlinx.serialization.internal.EnumSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AnnotationEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u0000 !*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002 !B7\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J\u000e\u0010\u0017\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000fJ2\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0013\u0010\t\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/AnnotationEvent;", "Annotation", "", "seen1", "", AnnotationEvent.KEY_ANNOTATION_TYPE, "Lcom/amazon/tarazed/core/types/annotations/AnnotationType;", "color", "Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;", "annotation", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILcom/amazon/tarazed/core/types/annotations/AnnotationType;Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;Ljava/lang/Object;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Lcom/amazon/tarazed/core/types/annotations/AnnotationType;Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;Ljava/lang/Object;)V", "getAnnotation", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getAnnotationType", "()Lcom/amazon/tarazed/core/types/annotations/AnnotationType;", "getColor", "()Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;", "component1", "component2", "component3", "copy", "(Lcom/amazon/tarazed/core/types/annotations/AnnotationType;Lcom/amazon/tarazed/core/types/annotations/AnnotationColor;Ljava/lang/Object;)Lcom/amazon/tarazed/core/types/annotations/AnnotationEvent;", "equals", "", "other", "hashCode", "toString", "", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class AnnotationEvent<Annotation> {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String KEY_ANNOTATION = "annotation";
    @NotNull
    public static final String KEY_ANNOTATION_TYPE = "annotationType";
    @NotNull
    public static final String KEY_COLOR = "color";
    private final Annotation annotation;
    @NotNull
    private final AnnotationType annotationType;
    @NotNull
    private final AnnotationColor color;

    /* compiled from: AnnotationEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\t0\b\"\u0004\b\u0001\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\bHÆ\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/AnnotationEvent$Companion;", "", "()V", "KEY_ANNOTATION", "", "KEY_ANNOTATION_TYPE", "KEY_COLOR", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/types/annotations/AnnotationEvent;", "T0", "typeSerial0", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final <T0> KSerializer<AnnotationEvent<T0>> serializer(@NotNull KSerializer<T0> typeSerial0) {
            Intrinsics.checkParameterIsNotNull(typeSerial0, "typeSerial0");
            return new AnnotationEvent$$serializer(typeSerial0);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ AnnotationEvent(int i, @Nullable AnnotationType annotationType, @Nullable AnnotationColor annotationColor, @Nullable Annotation annotation, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.annotationType = annotationType;
            if ((i & 2) == 0) {
                throw new MissingFieldException("color");
            }
            this.color = annotationColor;
            if ((i & 4) == 0) {
                throw new MissingFieldException("annotation");
            }
            this.annotation = annotation;
            return;
        }
        throw new MissingFieldException(KEY_ANNOTATION_TYPE);
    }

    public AnnotationEvent(@NotNull AnnotationType annotationType, @NotNull AnnotationColor color, Annotation annotation) {
        Intrinsics.checkParameterIsNotNull(annotationType, "annotationType");
        Intrinsics.checkParameterIsNotNull(color, "color");
        this.annotationType = annotationType;
        this.color = color;
        this.annotation = annotation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnnotationEvent copy$default(AnnotationEvent annotationEvent, AnnotationType annotationType, AnnotationColor annotationColor, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            annotationType = annotationEvent.annotationType;
        }
        if ((i & 2) != 0) {
            annotationColor = annotationEvent.color;
        }
        if ((i & 4) != 0) {
            obj = annotationEvent.annotation;
        }
        return annotationEvent.copy(annotationType, annotationColor, obj);
    }

    @JvmStatic
    public static final <T0> void write$Self(@NotNull AnnotationEvent<T0> self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc, @NotNull KSerializer<T0> typeSerial0) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        Intrinsics.checkParameterIsNotNull(typeSerial0, "typeSerial0");
        output.encodeSerializableElement(serialDesc, 0, new EnumSerializer("com.amazon.tarazed.core.types.annotations.AnnotationType", AnnotationType.values()), ((AnnotationEvent) self).annotationType);
        output.encodeSerializableElement(serialDesc, 1, AnnotationColor$$serializer.INSTANCE, ((AnnotationEvent) self).color);
        output.encodeSerializableElement(serialDesc, 2, typeSerial0, ((AnnotationEvent) self).annotation);
    }

    @NotNull
    public final AnnotationType component1() {
        return this.annotationType;
    }

    @NotNull
    public final AnnotationColor component2() {
        return this.color;
    }

    public final Annotation component3() {
        return this.annotation;
    }

    @NotNull
    public final AnnotationEvent<Annotation> copy(@NotNull AnnotationType annotationType, @NotNull AnnotationColor color, Annotation annotation) {
        Intrinsics.checkParameterIsNotNull(annotationType, "annotationType");
        Intrinsics.checkParameterIsNotNull(color, "color");
        return new AnnotationEvent<>(annotationType, color, annotation);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AnnotationEvent)) {
                return false;
            }
            AnnotationEvent annotationEvent = (AnnotationEvent) obj;
            return Intrinsics.areEqual(this.annotationType, annotationEvent.annotationType) && Intrinsics.areEqual(this.color, annotationEvent.color) && Intrinsics.areEqual(this.annotation, annotationEvent.annotation);
        }
        return true;
    }

    public final Annotation getAnnotation() {
        return this.annotation;
    }

    @NotNull
    public final AnnotationType getAnnotationType() {
        return this.annotationType;
    }

    @NotNull
    public final AnnotationColor getColor() {
        return this.color;
    }

    public int hashCode() {
        AnnotationType annotationType = this.annotationType;
        int i = 0;
        int hashCode = (annotationType != null ? annotationType.hashCode() : 0) * 31;
        AnnotationColor annotationColor = this.color;
        int hashCode2 = (hashCode + (annotationColor != null ? annotationColor.hashCode() : 0)) * 31;
        Annotation annotation = this.annotation;
        if (annotation != null) {
            i = annotation.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AnnotationEvent(annotationType=");
        outline107.append(this.annotationType);
        outline107.append(", color=");
        outline107.append(this.color);
        outline107.append(", annotation=");
        return GeneratedOutlineSupport1.outline88(outline107, this.annotation, ")");
    }
}
