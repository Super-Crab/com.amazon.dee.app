package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.ImplicitReflectionSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PlatformUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SerialModuleExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a!\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u0086\b\u001a)\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0006\u001a)\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0006\u001a*\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0007\u001a\u0015\u0010\n\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0086\u0004\u001a\u0015\u0010\f\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0086\u0002¨\u0006\r"}, d2 = {"getContextual", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/modules/SerialModule;", "value", "(Lkotlinx/serialization/modules/SerialModule;Ljava/lang/Object;)Lkotlinx/serialization/KSerializer;", "getContextualOrDefault", "klass", "Lkotlin/reflect/KClass;", "overwriteWith", "other", "plus", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialModuleExtensionsKt {
    @Nullable
    public static final /* synthetic */ <T> KSerializer<T> getContextual(@NotNull SerialModule getContextual) {
        Intrinsics.checkParameterIsNotNull(getContextual, "$this$getContextual");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return getContextual.getContextual(Reflection.getOrCreateKotlinClass(Object.class));
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final <T> KSerializer<T> getContextualOrDefault(@NotNull SerialModule getContextualOrDefault, @NotNull KClass<T> klass) {
        Intrinsics.checkParameterIsNotNull(getContextualOrDefault, "$this$getContextualOrDefault");
        Intrinsics.checkParameterIsNotNull(klass, "klass");
        KSerializer<T> contextual = getContextualOrDefault.getContextual(klass);
        return contextual != null ? contextual : PlatformUtilsKt.serializer(klass);
    }

    @NotNull
    public static final SerialModule overwriteWith(@NotNull SerialModule overwriteWith, @NotNull SerialModule other) {
        Intrinsics.checkParameterIsNotNull(overwriteWith, "$this$overwriteWith");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return SerialModuleBuildersKt.SerializersModule(new SerialModuleExtensionsKt$overwriteWith$1(overwriteWith, other));
    }

    @NotNull
    public static final SerialModule plus(@NotNull SerialModule plus, @NotNull SerialModule other) {
        Intrinsics.checkParameterIsNotNull(plus, "$this$plus");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return SerialModuleBuildersKt.SerializersModule(new SerialModuleExtensionsKt$plus$1(plus, other));
    }

    @Nullable
    public static final <T> KSerializer<T> getContextual(@NotNull SerialModule getContextual, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(getContextual, "$this$getContextual");
        Intrinsics.checkParameterIsNotNull(value, "value");
        KSerializer<T> contextual = getContextual.getContextual(Reflection.getOrCreateKotlinClass(value.getClass()));
        if (contextual != null) {
            return contextual;
        }
        return null;
    }

    @ImplicitReflectionSerializer
    @NotNull
    public static final <T> KSerializer<T> getContextualOrDefault(@NotNull SerialModule getContextualOrDefault, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(getContextualOrDefault, "$this$getContextualOrDefault");
        Intrinsics.checkParameterIsNotNull(value, "value");
        KSerializer<T> contextual = getContextual(getContextualOrDefault, value);
        if (contextual == null && (contextual = PlatformUtilsKt.serializer(Reflection.getOrCreateKotlinClass(value.getClass()))) == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
        }
        return contextual;
    }
}
