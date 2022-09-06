package com.amazon.tarazed.core.webrtc.signals;

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
/* compiled from: Offer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/tarazed/core/webrtc/signals/Offer.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/tarazed/core/webrtc/signals/Offer;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes13.dex */
public final class Offer$$serializer implements GeneratedSerializer<Offer> {
    private static final /* synthetic */ SerialDescriptor $$serialDesc;
    public static final Offer$$serializer INSTANCE = new Offer$$serializer();

    static {
        SerialClassDescImpl serialClassDescImpl = new SerialClassDescImpl("com.amazon.tarazed.core.webrtc.signals.Offer", INSTANCE, 1);
        serialClassDescImpl.addElement(WebRTCSignalFormats.SDP, false);
        $$serialDesc = serialClassDescImpl;
    }

    private Offer$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public Offer mo12413deserialize(@NotNull Decoder decoder) {
        String str;
        int i;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor, new KSerializer[0]);
        if (!beginStructure.decodeSequentially()) {
            int i2 = 0;
            String str2 = null;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    str = str2;
                    i = i2;
                    break;
                } else if (decodeElementIndex != 0) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    str2 = beginStructure.decodeStringElement(serialDescriptor, 0);
                    i2 |= 1;
                }
            }
        } else {
            str = beginStructure.decodeStringElement(serialDescriptor, 0);
            i = Integer.MAX_VALUE;
        }
        beginStructure.endStructure(serialDescriptor);
        return new Offer(i, str, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return $$serialDesc;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public Offer patch(@NotNull Decoder decoder, @NotNull Offer old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (Offer) GeneratedSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull Offer value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor, new KSerializer[0]);
        Offer.write$Self(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }
}
