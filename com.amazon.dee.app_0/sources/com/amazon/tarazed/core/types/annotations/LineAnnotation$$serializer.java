package com.amazon.tarazed.core.types.annotations;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.SerialClassDescImpl;
import org.jetbrains.annotations.NotNull;
/* compiled from: LineAnnotation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/tarazed/core/types/annotations/LineAnnotation.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/tarazed/core/types/annotations/LineAnnotation;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes13.dex */
public final class LineAnnotation$$serializer implements GeneratedSerializer<LineAnnotation> {
    private static final /* synthetic */ SerialDescriptor $$serialDesc;
    public static final LineAnnotation$$serializer INSTANCE = new LineAnnotation$$serializer();

    static {
        SerialClassDescImpl serialClassDescImpl = new SerialClassDescImpl("com.amazon.tarazed.core.types.annotations.LineAnnotation", INSTANCE, 2);
        serialClassDescImpl.addElement("p0", false);
        serialClassDescImpl.addElement("p1", false);
        $$serialDesc = serialClassDescImpl;
    }

    private LineAnnotation$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        AnnotationPoint$$serializer annotationPoint$$serializer = AnnotationPoint$$serializer.INSTANCE;
        return new KSerializer[]{annotationPoint$$serializer, annotationPoint$$serializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public LineAnnotation mo12413deserialize(@NotNull Decoder decoder) {
        AnnotationPoint annotationPoint;
        AnnotationPoint annotationPoint2;
        int i;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor, new KSerializer[0]);
        if (!beginStructure.decodeSequentially()) {
            int i2 = 0;
            AnnotationPoint annotationPoint3 = null;
            AnnotationPoint annotationPoint4 = null;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    annotationPoint = annotationPoint3;
                    annotationPoint2 = annotationPoint4;
                    i = i2;
                    break;
                } else if (decodeElementIndex == 0) {
                    AnnotationPoint$$serializer annotationPoint$$serializer = AnnotationPoint$$serializer.INSTANCE;
                    annotationPoint3 = (AnnotationPoint) ((i2 & 1) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 0, annotationPoint$$serializer, annotationPoint3) : beginStructure.decodeSerializableElement(serialDescriptor, 0, annotationPoint$$serializer));
                    i2 |= 1;
                } else if (decodeElementIndex != 1) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    AnnotationPoint$$serializer annotationPoint$$serializer2 = AnnotationPoint$$serializer.INSTANCE;
                    annotationPoint4 = (AnnotationPoint) ((i2 & 2) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 1, annotationPoint$$serializer2, annotationPoint4) : beginStructure.decodeSerializableElement(serialDescriptor, 1, annotationPoint$$serializer2));
                    i2 |= 2;
                }
            }
        } else {
            annotationPoint = (AnnotationPoint) beginStructure.decodeSerializableElement(serialDescriptor, 0, AnnotationPoint$$serializer.INSTANCE);
            annotationPoint2 = (AnnotationPoint) beginStructure.decodeSerializableElement(serialDescriptor, 1, AnnotationPoint$$serializer.INSTANCE);
            i = Integer.MAX_VALUE;
        }
        beginStructure.endStructure(serialDescriptor);
        return new LineAnnotation(i, annotationPoint, annotationPoint2, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return $$serialDesc;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public LineAnnotation patch(@NotNull Decoder decoder, @NotNull LineAnnotation old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (LineAnnotation) GeneratedSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull LineAnnotation value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor, new KSerializer[0]);
        LineAnnotation.write$Self(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }
}
