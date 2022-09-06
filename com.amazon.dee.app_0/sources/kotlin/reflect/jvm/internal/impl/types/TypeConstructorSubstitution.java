package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeSubstitution.kt */
/* loaded from: classes4.dex */
public abstract class TypeConstructorSubstitution extends TypeSubstitution {
    public static final Companion Companion = new Companion(null);

    /* compiled from: TypeSubstitution.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ TypeConstructorSubstitution createByConstructorsMap$default(Companion companion, Map map, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.createByConstructorsMap(map, z);
        }

        @JvmStatic
        @NotNull
        public final TypeSubstitution create(@NotNull KotlinType kotlinType) {
            Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
            return create(kotlinType.mo12131getConstructor(), kotlinType.getArguments());
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final TypeConstructorSubstitution createByConstructorsMap(@NotNull final Map<TypeConstructor, ? extends TypeProjection> map, final boolean z) {
            Intrinsics.checkParameterIsNotNull(map, "map");
            return new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution$Companion$createByConstructorsMap$1
                @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
                public boolean approximateCapturedTypes() {
                    return z;
                }

                @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
                @Nullable
                public TypeProjection get(@NotNull TypeConstructor key) {
                    Intrinsics.checkParameterIsNotNull(key, "key");
                    return (TypeProjection) map.get(key);
                }

                @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
                public boolean isEmpty() {
                    return map.isEmpty();
                }
            };
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final TypeSubstitution create(@NotNull TypeConstructor typeConstructor, @NotNull List<? extends TypeProjection> arguments) {
            int collectionSizeOrDefault;
            List zip;
            Map map;
            Intrinsics.checkParameterIsNotNull(typeConstructor, "typeConstructor");
            Intrinsics.checkParameterIsNotNull(arguments, "arguments");
            List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "typeConstructor.parameters");
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) CollectionsKt.lastOrNull((List<? extends Object>) parameters);
            if (typeParameterDescriptor != null ? typeParameterDescriptor.isCapturedFromOuterDeclaration() : false) {
                List<TypeParameterDescriptor> parameters2 = typeConstructor.getParameters();
                Intrinsics.checkExpressionValueIsNotNull(parameters2, "typeConstructor.parameters");
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters2, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                for (TypeParameterDescriptor it2 : parameters2) {
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    arrayList.add(it2.mo11528getTypeConstructor());
                }
                zip = CollectionsKt___CollectionsKt.zip(arrayList, arguments);
                map = MapsKt__MapsKt.toMap(zip);
                return createByConstructorsMap$default(this, map, false, 2, null);
            }
            return new IndexedParametersSubstitution(parameters, arguments);
        }
    }

    @JvmStatic
    @NotNull
    public static final TypeSubstitution create(@NotNull TypeConstructor typeConstructor, @NotNull List<? extends TypeProjection> list) {
        return Companion.create(typeConstructor, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final TypeConstructorSubstitution createByConstructorsMap(@NotNull Map<TypeConstructor, ? extends TypeProjection> map) {
        return Companion.createByConstructorsMap$default(Companion, map, false, 2, null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    @Nullable
    /* renamed from: get */
    public TypeProjection mo12121get(@NotNull KotlinType key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return get(key.mo12131getConstructor());
    }

    @Nullable
    public abstract TypeProjection get(@NotNull TypeConstructor typeConstructor);
}
