package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.auth.BuildConfig;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.ImplicitReflectionSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializerResolvingKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialModuleBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u001a#\u0010\u0007\u001a\u00020\u0001\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0086\b\u001a\"\u0010\f\u001a\u00020\u00012\u001a\u0010\r\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u000e\u001a,\u0010\f\u001a\u00020\u0001\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\b0\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000b\u001a\u0019\u0010\u0011\u001a\u00020\u0005\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t*\u00020\u0004H\u0087\b¨\u0006\u0012"}, d2 = {"SerializersModule", "Lkotlinx/serialization/modules/SerialModule;", "buildAction", "Lkotlin/Function1;", "Lkotlinx/serialization/modules/SerializersModuleBuilder;", "", "Lkotlin/ExtensionFunctionType;", "serializersModule", ExifInterface.GPS_DIRECTION_TRUE, "", "serializer", "Lkotlinx/serialization/KSerializer;", "serializersModuleOf", BuildConfig.FLAVOR_authtype, "", "Lkotlin/reflect/KClass;", "kClass", "contextual", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialModuleBuildersKt {
    @NotNull
    public static final SerialModule SerializersModule(@NotNull Function1<? super SerializersModuleBuilder, Unit> buildAction) {
        Intrinsics.checkParameterIsNotNull(buildAction, "buildAction");
        SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
        buildAction.mo12165invoke(serializersModuleBuilder);
        return serializersModuleBuilder.build$kotlinx_serialization_runtime();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ImplicitReflectionSerializer
    public static final /* synthetic */ <T> void contextual(@NotNull SerializersModuleBuilder contextual) {
        Intrinsics.checkParameterIsNotNull(contextual, "$this$contextual");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            contextual.contextual(orCreateKotlinClass, serializer);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }

    @NotNull
    public static final /* synthetic */ <T> SerialModule serializersModule(@NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return serializersModuleOf(Reflection.getOrCreateKotlinClass(Object.class), serializer);
    }

    @NotNull
    public static final <T> SerialModule serializersModuleOf(@NotNull KClass<T> kClass, @NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(kClass, "kClass");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        return SerializersModule(new SerialModuleBuildersKt$serializersModuleOf$1(kClass, serializer));
    }

    @NotNull
    public static final SerialModule serializersModuleOf(@NotNull Map<KClass<?>, ? extends KSerializer<?>> map) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        return SerializersModule(new SerialModuleBuildersKt$serializersModuleOf$2(map));
    }
}
