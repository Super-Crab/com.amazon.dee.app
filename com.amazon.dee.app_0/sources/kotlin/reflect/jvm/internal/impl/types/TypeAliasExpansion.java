package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeAliasExpansion.kt */
/* loaded from: classes4.dex */
public final class TypeAliasExpansion {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<TypeProjection> arguments;
    @NotNull
    private final TypeAliasDescriptor descriptor;
    @NotNull
    private final Map<TypeParameterDescriptor, TypeProjection> mapping;
    @Nullable
    private final TypeAliasExpansion parent;

    /* compiled from: TypeAliasExpansion.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final TypeAliasExpansion create(@Nullable TypeAliasExpansion typeAliasExpansion, @NotNull TypeAliasDescriptor typeAliasDescriptor, @NotNull List<? extends TypeProjection> arguments) {
            int collectionSizeOrDefault;
            List zip;
            Map map;
            Intrinsics.checkParameterIsNotNull(typeAliasDescriptor, "typeAliasDescriptor");
            Intrinsics.checkParameterIsNotNull(arguments, "arguments");
            TypeConstructor mo11528getTypeConstructor = typeAliasDescriptor.mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "typeAliasDescriptor.typeConstructor");
            List<TypeParameterDescriptor> parameters = mo11528getTypeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "typeAliasDescriptor.typeConstructor.parameters");
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (TypeParameterDescriptor it2 : parameters) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                arrayList.add(it2.mo11613getOriginal());
            }
            zip = CollectionsKt___CollectionsKt.zip(arrayList, arguments);
            map = MapsKt__MapsKt.toMap(zip);
            return new TypeAliasExpansion(typeAliasExpansion, typeAliasDescriptor, arguments, map, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List<? extends TypeProjection> list, Map<TypeParameterDescriptor, ? extends TypeProjection> map) {
        this.parent = typeAliasExpansion;
        this.descriptor = typeAliasDescriptor;
        this.arguments = list;
        this.mapping = map;
    }

    @NotNull
    public final List<TypeProjection> getArguments() {
        return this.arguments;
    }

    @NotNull
    public final TypeAliasDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Nullable
    public final TypeProjection getReplacement(@NotNull TypeConstructor constructor) {
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        ClassifierDescriptor mo12085getDeclarationDescriptor = constructor.mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return this.mapping.get(mo12085getDeclarationDescriptor);
        }
        return null;
    }

    public final boolean isRecursion(@NotNull TypeAliasDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (!Intrinsics.areEqual(this.descriptor, descriptor)) {
            TypeAliasExpansion typeAliasExpansion = this.parent;
            if (!(typeAliasExpansion != null ? typeAliasExpansion.isRecursion(descriptor) : false)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List list, Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeAliasExpansion, typeAliasDescriptor, list, map);
    }
}
