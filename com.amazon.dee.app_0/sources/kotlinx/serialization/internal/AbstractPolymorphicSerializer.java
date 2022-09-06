package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.CompositeDecoder;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerializationException;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractPolymorphicSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007\b\u0000¢\u0006\u0002\u0010\u0004J\u0015\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J \u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00032\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J%\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J\u001b\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u0000¢\u0006\u0002\u0010\u001aR\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lkotlinx/serialization/internal/AbstractPolymorphicSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/KSerializer;", "()V", "baseClass", "Lkotlin/reflect/KClass;", "getBaseClass", "()Lkotlin/reflect/KClass;", "decodeSequentially", "compositeDecoder", "Lkotlinx/serialization/CompositeDecoder;", "(Lkotlinx/serialization/CompositeDecoder;)Ljava/lang/Object;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "(Lkotlinx/serialization/Decoder;)Ljava/lang/Object;", "findPolymorphicSerializer", "klassName", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)Lkotlinx/serialization/KSerializer;", "serialize", "", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public abstract class AbstractPolymorphicSerializer<T> implements KSerializer<T> {
    /* JADX INFO: Access modifiers changed from: private */
    public final T decodeSequentially(CompositeDecoder compositeDecoder) {
        T t = (T) compositeDecoder.decodeSerializableElement(getDescriptor(), 1, findPolymorphicSerializer(compositeDecoder, compositeDecoder.decodeStringElement(getDescriptor(), 0)));
        compositeDecoder.endStructure(getDescriptor());
        return t;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public final T mo12413deserialize(@NotNull Decoder decoder) {
        T t;
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        SerialDescriptor descriptor = getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor, new KSerializer[0]);
        if (beginStructure.decodeSequentially()) {
            t = (T) decodeSequentially(beginStructure);
        } else {
            Object obj = null;
            String str = null;
            while (true) {
                int decodeElementIndex = beginStructure.decodeElementIndex(getDescriptor());
                if (decodeElementIndex == -1) {
                    if (obj == null) {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Polymorphic value has not been read for class ", str).toString());
                    }
                    t = (T) obj;
                } else if (decodeElementIndex == 0) {
                    str = beginStructure.decodeStringElement(getDescriptor(), decodeElementIndex);
                } else if (decodeElementIndex != 1) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid index in polymorphic deserialization of ");
                    if (str == null) {
                        str = "unknown class";
                    }
                    outline107.append((Object) str);
                    outline107.append("\n Expected 0, 1 or READ_DONE(-1), but found ");
                    outline107.append(decodeElementIndex);
                    throw new SerializationException(outline107.toString(), null, 2, null);
                } else if (str != null) {
                    obj = beginStructure.decodeSerializableElement(getDescriptor(), decodeElementIndex, findPolymorphicSerializer(beginStructure, str));
                } else {
                    throw new IllegalArgumentException("Cannot read polymorphic value before its type token".toString());
                }
            }
        }
        beginStructure.endStructure(descriptor);
        return t;
    }

    @NotNull
    public KSerializer<? extends T> findPolymorphicSerializer(@NotNull CompositeDecoder decoder, @NotNull String klassName) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(klassName, "klassName");
        KSerializer<? extends T> polymorphic = decoder.getContext().getPolymorphic((KClass) getBaseClass(), klassName);
        if (polymorphic != null) {
            return polymorphic;
        }
        AbstractPolymorphicSerializerKt.throwSubtypeNotRegistered(klassName, (KClass<?>) getBaseClass());
        throw null;
    }

    @NotNull
    public abstract KClass<T> getBaseClass();

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public T patch(@NotNull Decoder decoder, @NotNull T old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (T) KSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        KSerializer<? extends T> findPolymorphicSerializer = findPolymorphicSerializer(encoder, (Encoder) value);
        SerialDescriptor descriptor = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor, new KSerializer[0]);
        beginStructure.encodeStringElement(getDescriptor(), 0, findPolymorphicSerializer.getDescriptor().getSerialName());
        beginStructure.encodeSerializableElement(getDescriptor(), 1, findPolymorphicSerializer, value);
        beginStructure.endStructure(descriptor);
    }

    @NotNull
    public KSerializer<? extends T> findPolymorphicSerializer(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        KSerializer<? extends T> polymorphic = encoder.getContext().getPolymorphic((KClass<KClass<T>>) getBaseClass(), (KClass<T>) value);
        if (polymorphic != null) {
            return polymorphic;
        }
        AbstractPolymorphicSerializerKt.throwSubtypeNotRegistered((KClass<?>) Reflection.getOrCreateKotlinClass(value.getClass()), (KClass<?>) getBaseClass());
        throw null;
    }
}
