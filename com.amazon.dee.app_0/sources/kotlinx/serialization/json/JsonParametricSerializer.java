package kotlinx.serialization.json;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.Typography;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PlatformUtilsKt;
import kotlinx.serialization.PolymorphicKind;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.SerializationKt;
import kotlinx.serialization.internal.AbstractPolymorphicSerializerKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonParametricSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00032\u0006\u0010\u0010\u001a\u00020\u0011H$J\u001b\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u0000¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lkotlinx/serialization/json/JsonParametricSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/KSerializer;", "baseClass", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "(Lkotlinx/serialization/Decoder;)Ljava/lang/Object;", "selectSerializer", "element", "Lkotlinx/serialization/json/JsonElement;", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class JsonParametricSerializer<T> implements KSerializer<T> {
    private final KClass<T> baseClass;
    @NotNull
    private final SerialDescriptor descriptor;

    public JsonParametricSerializer(@NotNull KClass<T> baseClass) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        this.baseClass = baseClass;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JsonParametricSerializer<");
        outline107.append(SerializationKt.simpleName(this.baseClass));
        outline107.append(Typography.greater);
        this.descriptor = SerialDescriptorBuilderKt.SerialDescriptor$default(outline107.toString(), PolymorphicKind.OPEN.INSTANCE, null, 4, null);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public final T mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        JsonInput asJsonInput = JsonElementSerializerKt.asJsonInput(decoder);
        JsonElement decodeJson = asJsonInput.decodeJson();
        KSerializer<? extends T> selectSerializer = selectSerializer(decodeJson);
        if (selectSerializer != null) {
            return (T) asJsonInput.getJson().fromJson(selectSerializer, decodeJson);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public T patch(@NotNull Decoder decoder, @NotNull T old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (T) KSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @NotNull
    protected abstract KSerializer<? extends T> selectSerializer(@NotNull JsonElement jsonElement);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        KSerializer<? extends T> polymorphic = encoder.getContext().getPolymorphic((KClass<KClass<T>>) this.baseClass, (KClass<T>) value);
        if (polymorphic == null) {
            polymorphic = PlatformUtilsKt.serializerOrNull(Reflection.getOrCreateKotlinClass(value.getClass()));
        }
        if (polymorphic != null) {
            polymorphic.serialize(encoder, value);
        } else {
            AbstractPolymorphicSerializerKt.throwSubtypeNotRegistered((KClass<?>) Reflection.getOrCreateKotlinClass(value.getClass()), (KClass<?>) this.baseClass);
            throw null;
        }
    }
}
