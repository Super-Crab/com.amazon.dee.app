package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CapturedTypeConstructor.kt */
/* loaded from: classes4.dex */
public final class CapturedTypeConstructorKt {
    public static final TypeProjection createCapturedIfNeeded(@NotNull TypeProjection typeProjection, TypeParameterDescriptor typeParameterDescriptor) {
        if (typeParameterDescriptor == null || typeProjection.getProjectionKind() == Variance.INVARIANT) {
            return typeProjection;
        }
        if (typeParameterDescriptor.getVariance() == typeProjection.getProjectionKind()) {
            if (typeProjection.isStarProjection()) {
                StorageManager storageManager = LockBasedStorageManager.NO_LOCKS;
                Intrinsics.checkExpressionValueIsNotNull(storageManager, "LockBasedStorageManager.NO_LOCKS");
                return new TypeProjectionImpl(new LazyWrappedType(storageManager, new CapturedTypeConstructorKt$createCapturedIfNeeded$1(typeProjection)));
            }
            return new TypeProjectionImpl(typeProjection.getType());
        }
        return new TypeProjectionImpl(createCapturedType(typeProjection));
    }

    @NotNull
    public static final KotlinType createCapturedType(@NotNull TypeProjection typeProjection) {
        Intrinsics.checkParameterIsNotNull(typeProjection, "typeProjection");
        return new CapturedType(typeProjection, null, false, null, 14, null);
    }

    public static final boolean isCaptured(@NotNull KotlinType isCaptured) {
        Intrinsics.checkParameterIsNotNull(isCaptured, "$this$isCaptured");
        return isCaptured.mo12131getConstructor() instanceof CapturedTypeConstructor;
    }

    @NotNull
    public static final TypeSubstitution wrapWithCapturingSubstitution(@NotNull final TypeSubstitution wrapWithCapturingSubstitution, final boolean z) {
        List<Pair> zip;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(wrapWithCapturingSubstitution, "$this$wrapWithCapturingSubstitution");
        if (wrapWithCapturingSubstitution instanceof IndexedParametersSubstitution) {
            IndexedParametersSubstitution indexedParametersSubstitution = (IndexedParametersSubstitution) wrapWithCapturingSubstitution;
            TypeParameterDescriptor[] parameters = indexedParametersSubstitution.getParameters();
            zip = ArraysKt___ArraysKt.zip(indexedParametersSubstitution.getArguments(), indexedParametersSubstitution.getParameters());
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(zip, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (Pair pair : zip) {
                arrayList.add(createCapturedIfNeeded((TypeProjection) pair.getFirst(), (TypeParameterDescriptor) pair.getSecond()));
            }
            Object[] array = arrayList.toArray(new TypeProjection[0]);
            if (array != null) {
                return new IndexedParametersSubstitution(parameters, (TypeProjection[]) array, z);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return new DelegatedTypeSubstitution(wrapWithCapturingSubstitution) { // from class: kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt$wrapWithCapturingSubstitution$2
            @Override // kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution, kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            public boolean approximateContravariantCapturedTypes() {
                return z;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution, kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
            @Nullable
            /* renamed from: get */
            public TypeProjection mo12121get(@NotNull KotlinType key) {
                TypeProjection createCapturedIfNeeded;
                Intrinsics.checkParameterIsNotNull(key, "key");
                TypeProjection mo12121get = super.mo12121get(key);
                if (mo12121get != null) {
                    ClassifierDescriptor mo12085getDeclarationDescriptor = key.mo12131getConstructor().mo12085getDeclarationDescriptor();
                    if (!(mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor)) {
                        mo12085getDeclarationDescriptor = null;
                    }
                    createCapturedIfNeeded = CapturedTypeConstructorKt.createCapturedIfNeeded(mo12121get, (TypeParameterDescriptor) mo12085getDeclarationDescriptor);
                    return createCapturedIfNeeded;
                }
                return null;
            }
        };
    }

    public static /* synthetic */ TypeSubstitution wrapWithCapturingSubstitution$default(TypeSubstitution typeSubstitution, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return wrapWithCapturingSubstitution(typeSubstitution, z);
    }
}
