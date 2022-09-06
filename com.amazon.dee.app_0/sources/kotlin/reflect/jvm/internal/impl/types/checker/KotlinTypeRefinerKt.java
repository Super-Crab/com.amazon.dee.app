package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
/* compiled from: KotlinTypeRefiner.kt */
/* loaded from: classes4.dex */
public final class KotlinTypeRefinerKt {
    @NotNull
    private static final ModuleDescriptor.Capability<Ref<KotlinTypeRefiner>> REFINER_CAPABILITY = new ModuleDescriptor.Capability<>("KotlinTypeRefiner");

    @NotNull
    public static final ModuleDescriptor.Capability<Ref<KotlinTypeRefiner>> getREFINER_CAPABILITY() {
        return REFINER_CAPABILITY;
    }

    @NotNull
    public static final List<KotlinType> refineTypes(@NotNull KotlinTypeRefiner refineTypes, @NotNull Iterable<? extends KotlinType> types) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(refineTypes, "$this$refineTypes");
        Intrinsics.checkParameterIsNotNull(types, "types");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(types, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (KotlinType kotlinType : types) {
            arrayList.add(refineTypes.refineType(kotlinType));
        }
        return arrayList;
    }
}
