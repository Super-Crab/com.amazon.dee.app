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
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.SerialClassDescImpl;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0004B\u0015\b\u0017\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\rHÖ\u0001¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u001f\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003HÖ\u0001R\u0014\u0010\b\u001a\u00020\t8VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"com/amazon/tarazed/core/types/annotations/AnnotationEvent.$serializer", "Annotation", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/tarazed/core/types/annotations/AnnotationEvent;", "()V", "typeSerial0", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "childSerializers", "", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes13.dex */
public final class AnnotationEvent$$serializer<Annotation> implements GeneratedSerializer<AnnotationEvent<Annotation>> {
    private final /* synthetic */ SerialDescriptor $$serialDesc;
    private /* synthetic */ KSerializer typeSerial0;

    private AnnotationEvent$$serializer() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ AnnotationEvent$$serializer(@NotNull KSerializer<Annotation> typeSerial0) {
        Intrinsics.checkParameterIsNotNull(typeSerial0, "typeSerial0");
        this.typeSerial0 = typeSerial0;
        SerialClassDescImpl serialClassDescImpl = new SerialClassDescImpl("com.amazon.tarazed.core.types.annotations.AnnotationEvent", this, 3);
        serialClassDescImpl.addElement(AnnotationEvent.KEY_ANNOTATION_TYPE, false);
        serialClassDescImpl.addElement("color", false);
        serialClassDescImpl.addElement("annotation", false);
        this.$$serialDesc = serialClassDescImpl;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{new EnumSerializer("com.amazon.tarazed.core.types.annotations.AnnotationType", AnnotationType.values()), AnnotationColor$$serializer.INSTANCE, this.typeSerial0};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public AnnotationEvent<Annotation> mo12413deserialize(@NotNull Decoder decoder) {
        AnnotationType annotationType;
        AnnotationColor annotationColor;
        Object obj;
        int i;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor serialDescriptor = this.$$serialDesc;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor, this.typeSerial0);
        if (!beginStructure.decodeSequentially()) {
            AnnotationType annotationType2 = null;
            AnnotationColor annotationColor2 = null;
            Object obj2 = null;
            int i2 = 0;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    annotationType = annotationType2;
                    annotationColor = annotationColor2;
                    obj = obj2;
                    i = i2;
                    break;
                } else if (decodeElementIndex == 0) {
                    EnumSerializer enumSerializer = new EnumSerializer("com.amazon.tarazed.core.types.annotations.AnnotationType", AnnotationType.values());
                    annotationType2 = (AnnotationType) ((i2 & 1) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 0, enumSerializer, annotationType2) : beginStructure.decodeSerializableElement(serialDescriptor, 0, enumSerializer));
                    i2 |= 1;
                } else if (decodeElementIndex == 1) {
                    AnnotationColor$$serializer annotationColor$$serializer = AnnotationColor$$serializer.INSTANCE;
                    annotationColor2 = (AnnotationColor) ((i2 & 2) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 1, annotationColor$$serializer, annotationColor2) : beginStructure.decodeSerializableElement(serialDescriptor, 1, annotationColor$$serializer));
                    i2 |= 2;
                } else if (decodeElementIndex != 2) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    KSerializer kSerializer = this.typeSerial0;
                    obj2 = (i2 & 4) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 2, kSerializer, obj2) : beginStructure.decodeSerializableElement(serialDescriptor, 2, kSerializer);
                    i2 |= 4;
                }
            }
        } else {
            annotationColor = (AnnotationColor) beginStructure.decodeSerializableElement(serialDescriptor, 1, AnnotationColor$$serializer.INSTANCE);
            annotationType = (AnnotationType) beginStructure.decodeSerializableElement(serialDescriptor, 0, new EnumSerializer("com.amazon.tarazed.core.types.annotations.AnnotationType", AnnotationType.values()));
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 2, this.typeSerial0);
            i = Integer.MAX_VALUE;
        }
        beginStructure.endStructure(serialDescriptor);
        return new AnnotationEvent<>(i, annotationType, annotationColor, obj, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.$$serialDesc;
    }

    @NotNull
    public AnnotationEvent<Annotation> patch(@NotNull Decoder decoder, @NotNull AnnotationEvent<Annotation> old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (AnnotationEvent) GeneratedSerializer.DefaultImpls.patch(this, decoder, old);
    }

    public void serialize(@NotNull Encoder encoder, @NotNull AnnotationEvent<Annotation> value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialDescriptor serialDescriptor = this.$$serialDesc;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor, this.typeSerial0);
        AnnotationEvent.write$Self(value, beginStructure, serialDescriptor, this.typeSerial0);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        serialize(encoder, (AnnotationEvent) ((AnnotationEvent) obj));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object patch(Decoder decoder, Object obj) {
        return patch(decoder, (AnnotationEvent) ((AnnotationEvent) obj));
    }
}
