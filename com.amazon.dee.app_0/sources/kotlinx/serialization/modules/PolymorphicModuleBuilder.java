package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.ImplicitReflectionSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PlatformUtilsKt;
import kotlinx.serialization.SerializerResolvingKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PolymorphicModuleBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B'\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\f\"\n\b\u0001\u0010\r\u0018\u0001*\u00028\u0000H\u0087\bJ,\u0010\u000b\u001a\u00020\f\"\b\b\u0001\u0010\r*\u00028\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006J#\u0010\u000b\u001a\u00020\f\"\n\b\u0001\u0010\r\u0018\u0001*\u00028\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006H\u0087\bJ\u0015\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J=\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0000\"\b\b\u0001\u0010\u0015*\u00020\u00022\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u0002H\u0015\u0018\u00010\u0006H\u0000¢\u0006\u0002\b\u0018J\u0015\u0010\u000e\u001a\u00020\f\"\n\b\u0001\u0010\r\u0018\u0001*\u00028\u0000H\u0087\bJ#\u0010\u000e\u001a\u00020\f\"\n\b\u0001\u0010\r\u0018\u0001*\u00028\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006H\u0086\bJ+\u0010\u0019\u001a\u00020\f\"\b\b\u0001\u0010\r*\u00028\u0000*\b\u0012\u0004\u0012\u0002H\r0\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0006H\u0086\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\b\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00060\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lkotlinx/serialization/modules/PolymorphicModuleBuilder;", "Base", "", "baseClass", "Lkotlin/reflect/KClass;", "baseSerializer", "Lkotlinx/serialization/KSerializer;", "(Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;)V", "subclasses", "", "Lkotlin/Pair;", "addSubclass", "", ExifInterface.GPS_DIRECTION_TRUE, "subclass", "serializer", "buildTo", "builder", "Lkotlinx/serialization/modules/SerializersModuleBuilder;", "buildTo$kotlinx_serialization_runtime", "changeBase", "NewBase", "newBaseClass", "newBaseClassSerializer", "changeBase$kotlinx_serialization_runtime", JsonPOJOBuilder.DEFAULT_WITH_PREFIX, "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PolymorphicModuleBuilder<Base> {
    private final KClass<Base> baseClass;
    private final KSerializer<Base> baseSerializer;
    private final List<Pair<KClass<? extends Base>, KSerializer<? extends Base>>> subclasses;

    public PolymorphicModuleBuilder(@NotNull KClass<Base> baseClass, @Nullable KSerializer<Base> kSerializer) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        this.baseClass = baseClass;
        this.baseSerializer = kSerializer;
        this.subclasses = new ArrayList();
    }

    public static /* synthetic */ PolymorphicModuleBuilder changeBase$kotlinx_serialization_runtime$default(PolymorphicModuleBuilder polymorphicModuleBuilder, KClass kClass, KSerializer kSerializer, int i, Object obj) {
        if ((i & 2) != 0) {
            kSerializer = null;
        }
        return polymorphicModuleBuilder.changeBase$kotlinx_serialization_runtime(kClass, kSerializer);
    }

    public final <T extends Base> void addSubclass(@NotNull KClass<T> subclass, @NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(subclass, "subclass");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        this.subclasses.add(TuplesKt.to(subclass, serializer));
    }

    public final void buildTo$kotlinx_serialization_runtime(@NotNull SerializersModuleBuilder builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        KSerializer<Base> kSerializer = this.baseSerializer;
        if (kSerializer != null) {
            KClass<Base> kClass = this.baseClass;
            SerializersModuleBuilder.registerPolymorphicSerializer$default(builder, kClass, kClass, kSerializer, false, 8, null);
        }
        Iterator<T> it2 = this.subclasses.iterator();
        while (it2.hasNext()) {
            Pair pair = (Pair) it2.next();
            KClass kClass2 = (KClass) pair.component1();
            KSerializer kSerializer2 = (KSerializer) pair.component2();
            KClass<Base> kClass3 = this.baseClass;
            if (kClass2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<Base>");
            }
            if (kSerializer2 != null) {
                SerializersModuleBuilder.registerPolymorphicSerializer$default(builder, kClass3, kClass2, kSerializer2, false, 8, null);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
            }
        }
    }

    @NotNull
    public final <NewBase> PolymorphicModuleBuilder<NewBase> changeBase$kotlinx_serialization_runtime(@NotNull KClass<NewBase> newBaseClass, @Nullable KSerializer<NewBase> kSerializer) {
        Intrinsics.checkParameterIsNotNull(newBaseClass, "newBaseClass");
        PolymorphicModuleBuilder<NewBase> polymorphicModuleBuilder = new PolymorphicModuleBuilder<>(newBaseClass, kSerializer);
        KSerializer kSerializer2 = (KSerializer<Base>) this.baseSerializer;
        if (kSerializer2 != null) {
            KClass kClass = (KClass<Base>) this.baseClass;
            if (kClass == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<NewBase>");
            }
            if (kSerializer2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
            }
            polymorphicModuleBuilder.addSubclass(kClass, kSerializer2);
        }
        Iterator<T> it2 = this.subclasses.iterator();
        while (it2.hasNext()) {
            Pair pair = (Pair) it2.next();
            KClass<T> kClass2 = (KClass) pair.component1();
            KSerializer<T> kSerializer3 = (KSerializer) pair.component2();
            if (kClass2 == 0) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<NewBase>");
            }
            if (kSerializer3 == 0) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<NewBase>");
            }
            polymorphicModuleBuilder.addSubclass(kClass2, kSerializer3);
        }
        return polymorphicModuleBuilder;
    }

    public final /* synthetic */ <T extends Base> void subclass(@NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        addSubclass(Reflection.getOrCreateKotlinClass(Object.class), serializer);
    }

    public final <T extends Base> void with(@NotNull KClass<T> with, @NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(with, "$this$with");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        addSubclass(with, serializer);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use 'subclass(serializer)' instead", replaceWith = @ReplaceWith(expression = "subclass(serializer)", imports = {}))
    public final /* synthetic */ <T extends Base> void addSubclass(@NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        addSubclass(Reflection.getOrCreateKotlinClass(Object.class), serializer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ImplicitReflectionSerializer
    public final /* synthetic */ <T extends Base> void subclass() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        KSerializer<Object> serializer = SerializerResolvingKt.serializer(null);
        if (serializer != null) {
            addSubclass(orCreateKotlinClass, serializer);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
    }

    public /* synthetic */ PolymorphicModuleBuilder(KClass kClass, KSerializer kSerializer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kClass, (i & 2) != 0 ? null : kSerializer);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use 'subclass' instead", replaceWith = @ReplaceWith(expression = "subclass<T>()", imports = {}))
    @ImplicitReflectionSerializer
    public final /* synthetic */ <T extends Base> void addSubclass() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass<T> orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        addSubclass(orCreateKotlinClass, PlatformUtilsKt.serializer(Reflection.getOrCreateKotlinClass(Object.class)));
    }
}
