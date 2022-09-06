package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: typeSignatureMapping.kt */
/* loaded from: classes3.dex */
public interface TypeMappingConfiguration<T> {

    /* compiled from: typeSignatureMapping.kt */
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @Nullable
        public static <T> String getPredefinedFullInternalNameForClass(TypeMappingConfiguration<? extends T> typeMappingConfiguration, @NotNull ClassDescriptor classDescriptor) {
            Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
            return null;
        }

        @Nullable
        public static <T> KotlinType preprocessType(TypeMappingConfiguration<? extends T> typeMappingConfiguration, @NotNull KotlinType kotlinType) {
            Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
            return null;
        }

        public static <T> boolean releaseCoroutines(TypeMappingConfiguration<? extends T> typeMappingConfiguration) {
            return true;
        }
    }

    @NotNull
    KotlinType commonSupertype(@NotNull Collection<KotlinType> collection);

    @Nullable
    String getPredefinedFullInternalNameForClass(@NotNull ClassDescriptor classDescriptor);

    @Nullable
    String getPredefinedInternalNameForClass(@NotNull ClassDescriptor classDescriptor);

    @Nullable
    T getPredefinedTypeForClass(@NotNull ClassDescriptor classDescriptor);

    @Nullable
    KotlinType preprocessType(@NotNull KotlinType kotlinType);

    void processErrorType(@NotNull KotlinType kotlinType, @NotNull ClassDescriptor classDescriptor);

    boolean releaseCoroutines();
}
