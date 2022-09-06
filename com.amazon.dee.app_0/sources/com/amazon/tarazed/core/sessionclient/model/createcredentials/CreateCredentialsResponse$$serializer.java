package com.amazon.tarazed.core.sessionclient.model.createcredentials;

import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerialClassDescImpl;
import org.jetbrains.annotations.NotNull;
/* compiled from: CreateCredentialsResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes13.dex */
public final class CreateCredentialsResponse$$serializer implements GeneratedSerializer<CreateCredentialsResponse> {
    private static final /* synthetic */ SerialDescriptor $$serialDesc;
    public static final CreateCredentialsResponse$$serializer INSTANCE = new CreateCredentialsResponse$$serializer();

    static {
        SerialClassDescImpl serialClassDescImpl = new SerialClassDescImpl("com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse", INSTANCE, 4);
        serialClassDescImpl.addElement("durationSeconds", false);
        serialClassDescImpl.addElement("loggingCredentials", false);
        serialClassDescImpl.addElement("signalingCredentials", false);
        serialClassDescImpl.addElement("mediaCredentials", false);
        $$serialDesc = serialClassDescImpl;
    }

    private CreateCredentialsResponse$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, LoggingCredentials$$serializer.INSTANCE, SignalingCredentials$$serializer.INSTANCE, new ArrayListSerializer(MediaCredential$$serializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public CreateCredentialsResponse mo12413deserialize(@NotNull Decoder decoder) {
        LoggingCredentials loggingCredentials;
        SignalingCredentials signalingCredentials;
        List list;
        int i;
        int i2;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor, new KSerializer[0]);
        if (!beginStructure.decodeSequentially()) {
            LoggingCredentials loggingCredentials2 = null;
            int i3 = 0;
            int i4 = 0;
            SignalingCredentials signalingCredentials2 = null;
            List list2 = null;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                if (decodeElementIndex == -1) {
                    loggingCredentials = loggingCredentials2;
                    signalingCredentials = signalingCredentials2;
                    list = list2;
                    i = i3;
                    i2 = i4;
                    break;
                } else if (decodeElementIndex == 0) {
                    i4 = beginStructure.decodeIntElement(serialDescriptor, 0);
                    i3 |= 1;
                } else if (decodeElementIndex == 1) {
                    LoggingCredentials$$serializer loggingCredentials$$serializer = LoggingCredentials$$serializer.INSTANCE;
                    loggingCredentials2 = (LoggingCredentials) ((i3 & 2) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 1, loggingCredentials$$serializer, loggingCredentials2) : beginStructure.decodeSerializableElement(serialDescriptor, 1, loggingCredentials$$serializer));
                    i3 |= 2;
                } else if (decodeElementIndex == 2) {
                    SignalingCredentials$$serializer signalingCredentials$$serializer = SignalingCredentials$$serializer.INSTANCE;
                    signalingCredentials2 = (SignalingCredentials) ((i3 & 4) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 2, signalingCredentials$$serializer, signalingCredentials2) : beginStructure.decodeSerializableElement(serialDescriptor, 2, signalingCredentials$$serializer));
                    i3 |= 4;
                } else if (decodeElementIndex != 3) {
                    throw new UnknownFieldException(decodeElementIndex);
                } else {
                    ArrayListSerializer arrayListSerializer = new ArrayListSerializer(MediaCredential$$serializer.INSTANCE);
                    list2 = (List) ((i3 & 8) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 3, arrayListSerializer, list2) : beginStructure.decodeSerializableElement(serialDescriptor, 3, arrayListSerializer));
                    i3 |= 8;
                }
            }
        } else {
            i2 = beginStructure.decodeIntElement(serialDescriptor, 0);
            loggingCredentials = (LoggingCredentials) beginStructure.decodeSerializableElement(serialDescriptor, 1, LoggingCredentials$$serializer.INSTANCE);
            list = (List) beginStructure.decodeSerializableElement(serialDescriptor, 3, new ArrayListSerializer(MediaCredential$$serializer.INSTANCE));
            signalingCredentials = (SignalingCredentials) beginStructure.decodeSerializableElement(serialDescriptor, 2, SignalingCredentials$$serializer.INSTANCE);
            i = Integer.MAX_VALUE;
        }
        beginStructure.endStructure(serialDescriptor);
        return new CreateCredentialsResponse(i, i2, loggingCredentials, signalingCredentials, list, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return $$serialDesc;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public CreateCredentialsResponse patch(@NotNull Decoder decoder, @NotNull CreateCredentialsResponse old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (CreateCredentialsResponse) GeneratedSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull CreateCredentialsResponse value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor, new KSerializer[0]);
        CreateCredentialsResponse.write$Self(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }
}
