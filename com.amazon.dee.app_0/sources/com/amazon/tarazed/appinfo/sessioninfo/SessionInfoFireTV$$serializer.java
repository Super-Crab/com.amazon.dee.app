package com.amazon.tarazed.appinfo.sessioninfo;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.tarazed.core.type.ProfileType;
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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.SerialClassDescImpl;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionInfoFireTV.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/tarazed/appinfo/sessioninfo/SessionInfoFireTV.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/tarazed/appinfo/sessioninfo/SessionInfoFireTV;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes13.dex */
public final class SessionInfoFireTV$$serializer implements GeneratedSerializer<SessionInfoFireTV> {
    private static final /* synthetic */ SerialDescriptor $$serialDesc;
    public static final SessionInfoFireTV$$serializer INSTANCE = new SessionInfoFireTV$$serializer();

    static {
        SerialClassDescImpl serialClassDescImpl = new SerialClassDescImpl("com.amazon.tarazed.appinfo.sessioninfo.SessionInfoFireTV", INSTANCE, 10);
        serialClassDescImpl.addElement("operatingSystem", false);
        serialClassDescImpl.addElement("operatingSystemVersion", false);
        serialClassDescImpl.addElement("applicationName", false);
        serialClassDescImpl.addElement("tarazedVersion", false);
        serialClassDescImpl.addElement("handledEventTypes", false);
        serialClassDescImpl.addElement(MetricsConfiguration.DEVICE_LANGUAGE, false);
        serialClassDescImpl.addElement("voiceView", false);
        serialClassDescImpl.addElement("isFireTV", false);
        serialClassDescImpl.addElement("remoteType", false);
        serialClassDescImpl.addElement("profileType", false);
        $$serialDesc = serialClassDescImpl;
    }

    private SessionInfoFireTV$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, stringSerializer, new ArrayListSerializer(stringSerializer), StringSerializer.INSTANCE, booleanSerializer, booleanSerializer, StringSerializer.INSTANCE, new EnumSerializer("com.amazon.tarazed.core.type.ProfileType", ProfileType.values())};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public SessionInfoFireTV mo12413deserialize(@NotNull Decoder decoder) {
        List list;
        String str;
        int i;
        ProfileType profileType;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        boolean z;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor, new KSerializer[0]);
        if (beginStructure.decodeSequentially()) {
            String decodeStringElement = beginStructure.decodeStringElement(serialDescriptor, 0);
            String decodeStringElement2 = beginStructure.decodeStringElement(serialDescriptor, 1);
            String decodeStringElement3 = beginStructure.decodeStringElement(serialDescriptor, 2);
            String decodeStringElement4 = beginStructure.decodeStringElement(serialDescriptor, 3);
            String decodeStringElement5 = beginStructure.decodeStringElement(serialDescriptor, 5);
            boolean decodeBooleanElement = beginStructure.decodeBooleanElement(serialDescriptor, 6);
            boolean decodeBooleanElement2 = beginStructure.decodeBooleanElement(serialDescriptor, 7);
            String decodeStringElement6 = beginStructure.decodeStringElement(serialDescriptor, 8);
            str = decodeStringElement;
            str2 = decodeStringElement2;
            profileType = (ProfileType) beginStructure.decodeSerializableElement(serialDescriptor, 9, new EnumSerializer("com.amazon.tarazed.core.type.ProfileType", ProfileType.values()));
            i = Integer.MAX_VALUE;
            z2 = decodeBooleanElement2;
            z = decodeBooleanElement;
            str5 = decodeStringElement5;
            str4 = decodeStringElement4;
            str6 = decodeStringElement6;
            list = (List) beginStructure.decodeSerializableElement(serialDescriptor, 4, new ArrayListSerializer(StringSerializer.INSTANCE));
            str3 = decodeStringElement3;
        } else {
            String str7 = null;
            int i2 = 0;
            boolean z3 = false;
            boolean z4 = false;
            List list2 = null;
            ProfileType profileType2 = null;
            String str8 = null;
            String str9 = null;
            String str10 = null;
            String str11 = null;
            String str12 = null;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        list = list2;
                        str = str7;
                        i = i2;
                        profileType = profileType2;
                        str2 = str8;
                        str3 = str9;
                        str4 = str10;
                        str5 = str11;
                        str6 = str12;
                        z = z3;
                        z2 = z4;
                        break;
                    case 0:
                        i2 |= 1;
                        str7 = beginStructure.decodeStringElement(serialDescriptor, 0);
                        continue;
                    case 1:
                        i2 |= 2;
                        str8 = beginStructure.decodeStringElement(serialDescriptor, 1);
                        continue;
                    case 2:
                        i2 |= 4;
                        str9 = beginStructure.decodeStringElement(serialDescriptor, 2);
                        continue;
                    case 3:
                        str10 = beginStructure.decodeStringElement(serialDescriptor, 3);
                        i2 |= 8;
                        continue;
                    case 4:
                        ArrayListSerializer arrayListSerializer = new ArrayListSerializer(StringSerializer.INSTANCE);
                        list2 = (List) ((i2 & 16) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 4, arrayListSerializer, list2) : beginStructure.decodeSerializableElement(serialDescriptor, 4, arrayListSerializer));
                        i2 |= 16;
                        continue;
                    case 5:
                        str11 = beginStructure.decodeStringElement(serialDescriptor, 5);
                        i2 |= 32;
                        break;
                    case 6:
                        z3 = beginStructure.decodeBooleanElement(serialDescriptor, 6);
                        i2 |= 64;
                        break;
                    case 7:
                        z4 = beginStructure.decodeBooleanElement(serialDescriptor, 7);
                        i2 |= 128;
                        break;
                    case 8:
                        str12 = beginStructure.decodeStringElement(serialDescriptor, 8);
                        i2 |= 256;
                        break;
                    case 9:
                        EnumSerializer enumSerializer = new EnumSerializer("com.amazon.tarazed.core.type.ProfileType", ProfileType.values());
                        profileType2 = (ProfileType) ((i2 & 512) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 9, enumSerializer, profileType2) : beginStructure.decodeSerializableElement(serialDescriptor, 9, enumSerializer));
                        i2 |= 512;
                        break;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
        }
        beginStructure.endStructure(serialDescriptor);
        return new SessionInfoFireTV(i, str, str2, str3, str4, list, str5, z, z2, str6, profileType, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return $$serialDesc;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SessionInfoFireTV patch(@NotNull Decoder decoder, @NotNull SessionInfoFireTV old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (SessionInfoFireTV) GeneratedSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull SessionInfoFireTV value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor, new KSerializer[0]);
        SessionInfoFireTV.write$Self(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }
}
