package com.amazon.tarazed.core.signaling.events;

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
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0004B\u0015\b\u0017\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\rHÖ\u0001¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u001f\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003HÖ\u0001R\u0014\u0010\b\u001a\u00020\t8VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"com/amazon/tarazed/core/signaling/events/TarazedEvent.$serializer", "PayloadType", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "()V", "typeSerial0", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "childSerializers", "", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes13.dex */
public final class TarazedEvent$$serializer<PayloadType> implements GeneratedSerializer<TarazedEvent<? extends PayloadType>> {
    private final /* synthetic */ SerialDescriptor $$serialDesc;
    private /* synthetic */ KSerializer typeSerial0;

    private TarazedEvent$$serializer() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TarazedEvent$$serializer(@NotNull KSerializer<PayloadType> typeSerial0) {
        Intrinsics.checkParameterIsNotNull(typeSerial0, "typeSerial0");
        this.typeSerial0 = typeSerial0;
        SerialClassDescImpl serialClassDescImpl = new SerialClassDescImpl("com.amazon.tarazed.core.signaling.events.TarazedEvent", this, 2);
        serialClassDescImpl.addElement("type", false);
        serialClassDescImpl.addElement("payload", false);
        this.$$serialDesc = serialClassDescImpl;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, this.typeSerial0};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public TarazedEvent<PayloadType> mo12413deserialize(@NotNull Decoder decoder) {
        String str;
        Object obj;
        int i;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor serialDescriptor = this.$$serialDesc;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor, this.typeSerial0);
        if (!beginStructure.decodeSequentially()) {
            str = null;
            Object obj2 = null;
            int i2 = 0;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    obj = obj2;
                    i = i2;
                    break;
                } else if (decodeElementIndex == 0) {
                    str = beginStructure.decodeStringElement(serialDescriptor, 0);
                    i2 |= 1;
                } else if (decodeElementIndex != 1) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    KSerializer kSerializer = this.typeSerial0;
                    obj2 = (i2 & 2) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 1, kSerializer, obj2) : beginStructure.decodeSerializableElement(serialDescriptor, 1, kSerializer);
                    i2 |= 2;
                }
            }
        } else {
            str = beginStructure.decodeStringElement(serialDescriptor, 0);
            obj = beginStructure.decodeSerializableElement(serialDescriptor, 1, this.typeSerial0);
            i = Integer.MAX_VALUE;
        }
        beginStructure.endStructure(serialDescriptor);
        return new TarazedEvent<>(i, str, obj, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.$$serialDesc;
    }

    @NotNull
    public TarazedEvent<PayloadType> patch(@NotNull Decoder decoder, @NotNull TarazedEvent<? extends PayloadType> old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (TarazedEvent) GeneratedSerializer.DefaultImpls.patch(this, decoder, old);
    }

    public void serialize(@NotNull Encoder encoder, @NotNull TarazedEvent<? extends PayloadType> value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialDescriptor serialDescriptor = this.$$serialDesc;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor, this.typeSerial0);
        TarazedEvent.write$Self(value, beginStructure, serialDescriptor, this.typeSerial0);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public /* bridge */ /* synthetic */ void serialize(Encoder encoder, Object obj) {
        serialize(encoder, (TarazedEvent) ((TarazedEvent) obj));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    public /* bridge */ /* synthetic */ Object patch(Decoder decoder, Object obj) {
        return patch(decoder, (TarazedEvent) ((TarazedEvent) obj));
    }
}
