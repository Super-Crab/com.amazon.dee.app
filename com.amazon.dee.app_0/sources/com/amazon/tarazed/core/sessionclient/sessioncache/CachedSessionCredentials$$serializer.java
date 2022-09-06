package com.amazon.tarazed.core.sessionclient.sessioncache;

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
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.SerialClassDescImpl;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: CachedSessionCredentials.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/tarazed/core/sessionclient/sessioncache/CachedSessionCredentials.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/CachedSessionCredentials;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes13.dex */
public final class CachedSessionCredentials$$serializer implements GeneratedSerializer<CachedSessionCredentials> {
    private static final /* synthetic */ SerialDescriptor $$serialDesc;
    public static final CachedSessionCredentials$$serializer INSTANCE = new CachedSessionCredentials$$serializer();

    static {
        SerialClassDescImpl serialClassDescImpl = new SerialClassDescImpl("com.amazon.tarazed.core.sessionclient.sessioncache.CachedSessionCredentials", INSTANCE, 4);
        serialClassDescImpl.addElement("timestamp", false);
        serialClassDescImpl.addElement("encryptedKey", false);
        serialClassDescImpl.addElement("iv", false);
        serialClassDescImpl.addElement("encryptedCredentials", false);
        $$serialDesc = serialClassDescImpl;
    }

    private CachedSessionCredentials$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        return new KSerializer[]{LongSerializer.INSTANCE, stringSerializer, stringSerializer, stringSerializer};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public CachedSessionCredentials mo12413deserialize(@NotNull Decoder decoder) {
        String str;
        String str2;
        long j;
        String str3;
        int i;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor, new KSerializer[0]);
        if (!beginStructure.decodeSequentially()) {
            String str4 = null;
            int i2 = 0;
            String str5 = null;
            long j2 = 0;
            String str6 = null;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    str = str4;
                    str2 = str6;
                    j = j2;
                    str3 = str5;
                    i = i2;
                    break;
                } else if (decodeElementIndex == 0) {
                    j2 = beginStructure.decodeLongElement(serialDescriptor, 0);
                    i2 |= 1;
                } else if (decodeElementIndex == 1) {
                    str4 = beginStructure.decodeStringElement(serialDescriptor, 1);
                    i2 |= 2;
                } else if (decodeElementIndex == 2) {
                    str6 = beginStructure.decodeStringElement(serialDescriptor, 2);
                    i2 |= 4;
                } else if (decodeElementIndex != 3) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    str5 = beginStructure.decodeStringElement(serialDescriptor, 3);
                    i2 |= 8;
                }
            }
        } else {
            long decodeLongElement = beginStructure.decodeLongElement(serialDescriptor, 0);
            String decodeStringElement = beginStructure.decodeStringElement(serialDescriptor, 1);
            String decodeStringElement2 = beginStructure.decodeStringElement(serialDescriptor, 2);
            j = decodeLongElement;
            str3 = beginStructure.decodeStringElement(serialDescriptor, 3);
            str2 = decodeStringElement2;
            str = decodeStringElement;
            i = Integer.MAX_VALUE;
        }
        beginStructure.endStructure(serialDescriptor);
        return new CachedSessionCredentials(i, j, str, str2, str3, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return $$serialDesc;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public CachedSessionCredentials patch(@NotNull Decoder decoder, @NotNull CachedSessionCredentials old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (CachedSessionCredentials) GeneratedSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull CachedSessionCredentials value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor, new KSerializer[0]);
        CachedSessionCredentials.write$Self(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }
}
