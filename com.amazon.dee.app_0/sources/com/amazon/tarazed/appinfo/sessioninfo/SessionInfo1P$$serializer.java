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
/* compiled from: SessionInfo1P.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/amazon/tarazed/appinfo/sessioninfo/SessionInfo1P.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/amazon/tarazed/appinfo/sessioninfo/SessionInfo1P;", "()V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes13.dex */
public final class SessionInfo1P$$serializer implements GeneratedSerializer<SessionInfo1P> {
    private static final /* synthetic */ SerialDescriptor $$serialDesc;
    public static final SessionInfo1P$$serializer INSTANCE = new SessionInfo1P$$serializer();

    static {
        SerialClassDescImpl serialClassDescImpl = new SerialClassDescImpl("com.amazon.tarazed.appinfo.sessioninfo.SessionInfo1P", INSTANCE, 9);
        serialClassDescImpl.addElement("operatingSystem", false);
        serialClassDescImpl.addElement("operatingSystemVersion", false);
        serialClassDescImpl.addElement("applicationName", false);
        serialClassDescImpl.addElement("tarazedVersion", false);
        serialClassDescImpl.addElement("handledEventTypes", false);
        serialClassDescImpl.addElement(MetricsConfiguration.DEVICE_LANGUAGE, false);
        serialClassDescImpl.addElement("voiceView", false);
        serialClassDescImpl.addElement("isFireTV", false);
        serialClassDescImpl.addElement("activeProfileType", false);
        $$serialDesc = serialClassDescImpl;
    }

    private SessionInfo1P$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    @NotNull
    public KSerializer<?>[] childSerializers() {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
        return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, stringSerializer, new ArrayListSerializer(stringSerializer), StringSerializer.INSTANCE, booleanSerializer, booleanSerializer, new EnumSerializer("com.amazon.tarazed.core.type.ProfileType", ProfileType.values())};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public SessionInfo1P mo12413deserialize(@NotNull Decoder decoder) {
        List list;
        int i;
        ProfileType profileType;
        String str;
        String str2;
        String str3;
        String str4;
        boolean z;
        String str5;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor, new KSerializer[0]);
        int i2 = 3;
        if (beginStructure.decodeSequentially()) {
            String decodeStringElement = beginStructure.decodeStringElement(serialDescriptor, 0);
            String decodeStringElement2 = beginStructure.decodeStringElement(serialDescriptor, 1);
            String decodeStringElement3 = beginStructure.decodeStringElement(serialDescriptor, 2);
            String decodeStringElement4 = beginStructure.decodeStringElement(serialDescriptor, 3);
            String decodeStringElement5 = beginStructure.decodeStringElement(serialDescriptor, 5);
            boolean decodeBooleanElement = beginStructure.decodeBooleanElement(serialDescriptor, 6);
            boolean decodeBooleanElement2 = beginStructure.decodeBooleanElement(serialDescriptor, 7);
            str = decodeStringElement;
            str2 = decodeStringElement2;
            profileType = (ProfileType) beginStructure.decodeSerializableElement(serialDescriptor, 8, new EnumSerializer("com.amazon.tarazed.core.type.ProfileType", ProfileType.values()));
            z2 = decodeBooleanElement2;
            z = decodeBooleanElement;
            str5 = decodeStringElement5;
            str4 = decodeStringElement4;
            i = Integer.MAX_VALUE;
            list = (List) beginStructure.decodeSerializableElement(serialDescriptor, 4, new ArrayListSerializer(StringSerializer.INSTANCE));
            str3 = decodeStringElement3;
        } else {
            boolean z3 = false;
            boolean z4 = false;
            ProfileType profileType2 = null;
            String str6 = null;
            String str7 = null;
            String str8 = null;
            String str9 = null;
            String str10 = null;
            List list2 = null;
            int i3 = 0;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        list = list2;
                        i = i3;
                        profileType = profileType2;
                        str = str6;
                        str2 = str7;
                        str3 = str8;
                        str4 = str9;
                        z = z3;
                        str5 = str10;
                        z2 = z4;
                        break;
                    case 0:
                        i3 |= 1;
                        str6 = beginStructure.decodeStringElement(serialDescriptor, 0);
                        continue;
                    case 1:
                        i3 |= 2;
                        str7 = beginStructure.decodeStringElement(serialDescriptor, 1);
                        continue;
                    case 2:
                        str8 = beginStructure.decodeStringElement(serialDescriptor, 2);
                        i3 |= 4;
                        continue;
                    case 3:
                        str9 = beginStructure.decodeStringElement(serialDescriptor, i2);
                        i3 |= 8;
                        continue;
                    case 4:
                        ArrayListSerializer arrayListSerializer = new ArrayListSerializer(StringSerializer.INSTANCE);
                        list2 = (List) ((i3 & 16) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 4, arrayListSerializer, list2) : beginStructure.decodeSerializableElement(serialDescriptor, 4, arrayListSerializer));
                        i3 |= 16;
                        break;
                    case 5:
                        str10 = beginStructure.decodeStringElement(serialDescriptor, 5);
                        i3 |= 32;
                        break;
                    case 6:
                        z3 = beginStructure.decodeBooleanElement(serialDescriptor, 6);
                        i3 |= 64;
                        break;
                    case 7:
                        z4 = beginStructure.decodeBooleanElement(serialDescriptor, 7);
                        i3 |= 128;
                        break;
                    case 8:
                        EnumSerializer enumSerializer = new EnumSerializer("com.amazon.tarazed.core.type.ProfileType", ProfileType.values());
                        profileType2 = (ProfileType) ((i3 & 256) != 0 ? beginStructure.updateSerializableElement(serialDescriptor, 8, enumSerializer, profileType2) : beginStructure.decodeSerializableElement(serialDescriptor, 8, enumSerializer));
                        i3 |= 256;
                        break;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
                i2 = 3;
            }
        }
        beginStructure.endStructure(serialDescriptor);
        return new SessionInfo1P(i, str, str2, str3, str4, list, str5, z, z2, profileType, null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return $$serialDesc;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SessionInfo1P patch(@NotNull Decoder decoder, @NotNull SessionInfo1P old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (SessionInfo1P) GeneratedSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(@NotNull Encoder encoder, @NotNull SessionInfo1P value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        SerialDescriptor serialDescriptor = $$serialDesc;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor, new KSerializer[0]);
        SessionInfo1P.write$Self(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }
}
