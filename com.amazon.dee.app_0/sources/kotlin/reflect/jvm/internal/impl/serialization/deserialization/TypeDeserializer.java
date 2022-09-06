package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionForAbsentTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TypeDeserializer.kt */
/* loaded from: classes4.dex */
public final class TypeDeserializer {
    private final DeserializationContext c;
    private final Function1<Integer, ClassDescriptor> classDescriptors;
    private final String containerPresentableName;
    private final String debugName;
    private boolean experimentalSuspendFunctionTypeEncountered;
    private final TypeDeserializer parent;
    private final Function1<Integer, ClassifierDescriptor> typeAliasDescriptors;
    private final Map<Integer, TypeParameterDescriptor> typeParameterDescriptors;

    public TypeDeserializer(@NotNull DeserializationContext c, @Nullable TypeDeserializer typeDeserializer, @NotNull List<ProtoBuf.TypeParameter> typeParameterProtos, @NotNull String debugName, @NotNull String containerPresentableName, boolean z) {
        Map<Integer, TypeParameterDescriptor> linkedHashMap;
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(typeParameterProtos, "typeParameterProtos");
        Intrinsics.checkParameterIsNotNull(debugName, "debugName");
        Intrinsics.checkParameterIsNotNull(containerPresentableName, "containerPresentableName");
        this.c = c;
        this.parent = typeDeserializer;
        this.debugName = debugName;
        this.containerPresentableName = containerPresentableName;
        this.experimentalSuspendFunctionTypeEncountered = z;
        this.classDescriptors = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new TypeDeserializer$classDescriptors$1(this));
        this.typeAliasDescriptors = this.c.getStorageManager().createMemoizedFunctionWithNullableValues(new TypeDeserializer$typeAliasDescriptors$1(this));
        if (typeParameterProtos.isEmpty()) {
            linkedHashMap = MapsKt__MapsKt.emptyMap();
        } else {
            linkedHashMap = new LinkedHashMap<>();
            int i = 0;
            for (ProtoBuf.TypeParameter typeParameter : typeParameterProtos) {
                linkedHashMap.put(Integer.valueOf(typeParameter.getId()), new DeserializedTypeParameterDescriptor(this.c, typeParameter, i));
                i++;
            }
        }
        this.typeParameterDescriptors = linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor computeClassDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return this.c.getComponents().deserializeClass(classId);
        }
        return FindClassInModuleKt.findClassAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final SimpleType computeLocalClassifierReplacementType(int i) {
        if (NameResolverUtilKt.getClassId(this.c.getNameResolver(), i).isLocal()) {
            return this.c.getComponents().getLocalClassifierTypeSettings().getReplacementTypeForLocalClassifiers();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassifierDescriptor computeTypeAliasDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return null;
        }
        return FindClassInModuleKt.findTypeAliasAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final SimpleType createSimpleSuspendFunctionType(KotlinType kotlinType, KotlinType kotlinType2) {
        List<TypeProjection> dropLast;
        int collectionSizeOrDefault;
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(kotlinType);
        Annotations mo12070getAnnotations = kotlinType.mo12070getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
        dropLast = CollectionsKt___CollectionsKt.dropLast(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType), 1);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(dropLast, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (TypeProjection typeProjection : dropLast) {
            arrayList.add(typeProjection.getType());
        }
        return FunctionTypesKt.createFunctionType(builtIns, mo12070getAnnotations, receiverTypeFromFunctionType, arrayList, null, kotlinType2, true).mo12132makeNullableAsSpecified(kotlinType.isMarkedNullable());
    }

    private final SimpleType createSuspendFunctionType(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        int size;
        int size2 = typeConstructor.getParameters().size() - list.size();
        SimpleType simpleType = null;
        if (size2 == 0) {
            simpleType = createSuspendFunctionTypeForBasicCase(annotations, typeConstructor, list, z);
        } else if (size2 == 1 && (size = list.size() - 1) >= 0) {
            ClassDescriptor suspendFunction = typeConstructor.getBuiltIns().getSuspendFunction(size);
            Intrinsics.checkExpressionValueIsNotNull(suspendFunction, "functionTypeConstructor.…getSuspendFunction(arity)");
            TypeConstructor mo11528getTypeConstructor = suspendFunction.mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor, "functionTypeConstructor.…on(arity).typeConstructor");
            simpleType = KotlinTypeFactory.simpleType$default(annotations, mo11528getTypeConstructor, list, z, null, 16, null);
        }
        if (simpleType != null) {
            return simpleType;
        }
        SimpleType createErrorTypeWithArguments = ErrorUtils.createErrorTypeWithArguments("Bad suspend function in metadata with constructor: " + typeConstructor, list);
        Intrinsics.checkExpressionValueIsNotNull(createErrorTypeWithArguments, "ErrorUtils.createErrorTy…      arguments\n        )");
        return createErrorTypeWithArguments;
    }

    private final SimpleType createSuspendFunctionTypeForBasicCase(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        SimpleType simpleType$default = KotlinTypeFactory.simpleType$default(annotations, typeConstructor, list, z, null, 16, null);
        if (!FunctionTypesKt.isFunctionType(simpleType$default)) {
            return null;
        }
        return transformRuntimeFunctionTypeToSuspendFunction(simpleType$default);
    }

    private final SimpleType transformRuntimeFunctionTypeToSuspendFunction(KotlinType kotlinType) {
        KotlinType type;
        boolean releaseCoroutines = this.c.getComponents().getConfiguration().getReleaseCoroutines();
        TypeProjection typeProjection = (TypeProjection) CollectionsKt.lastOrNull((List<? extends Object>) FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType));
        FqName fqName = null;
        if (typeProjection == null || (type = typeProjection.getType()) == null) {
            return null;
        }
        Intrinsics.checkExpressionValueIsNotNull(type, "funType.getValueParamete…ll()?.type ?: return null");
        ClassifierDescriptor mo12085getDeclarationDescriptor = type.mo12131getConstructor().mo12085getDeclarationDescriptor();
        FqName fqNameSafe = mo12085getDeclarationDescriptor != null ? DescriptorUtilsKt.getFqNameSafe(mo12085getDeclarationDescriptor) : null;
        boolean z = true;
        if (type.getArguments().size() == 1 && (SuspendFunctionTypesKt.isContinuation(fqNameSafe, true) || SuspendFunctionTypesKt.isContinuation(fqNameSafe, false))) {
            KotlinType type2 = ((TypeProjection) CollectionsKt.single((List<? extends Object>) type.getArguments())).getType();
            Intrinsics.checkExpressionValueIsNotNull(type2, "continuationArgumentType.arguments.single().type");
            DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
            if (!(containingDeclaration instanceof CallableDescriptor)) {
                containingDeclaration = null;
            }
            CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
            if (callableDescriptor != null) {
                fqName = DescriptorUtilsKt.fqNameOrNull(callableDescriptor);
            }
            if (Intrinsics.areEqual(fqName, SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
                return createSimpleSuspendFunctionType(kotlinType, type2);
            }
            if (!this.experimentalSuspendFunctionTypeEncountered && (!releaseCoroutines || !SuspendFunctionTypesKt.isContinuation(fqNameSafe, !releaseCoroutines))) {
                z = false;
            }
            this.experimentalSuspendFunctionTypeEncountered = z;
            return createSimpleSuspendFunctionType(kotlinType, type2);
        }
        return (SimpleType) kotlinType;
    }

    private final TypeProjection typeArgument(TypeParameterDescriptor typeParameterDescriptor, ProtoBuf.Type.Argument argument) {
        if (argument.getProjection() == ProtoBuf.Type.Argument.Projection.STAR) {
            if (typeParameterDescriptor == null) {
                return new StarProjectionForAbsentTypeParameter(this.c.getComponents().getModuleDescriptor().getBuiltIns());
            }
            return new StarProjectionImpl(typeParameterDescriptor);
        }
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        ProtoBuf.Type.Argument.Projection projection = argument.getProjection();
        Intrinsics.checkExpressionValueIsNotNull(projection, "typeArgumentProto.projection");
        Variance variance = protoEnumFlags.variance(projection);
        ProtoBuf.Type type = ProtoTypeTableUtilKt.type(argument, this.c.getTypeTable());
        if (type != null) {
            return new TypeProjectionImpl(variance, type(type));
        }
        return new TypeProjectionImpl(ErrorUtils.createErrorType("No type recorded"));
    }

    private final TypeConstructor typeConstructor(ProtoBuf.Type type) {
        Object obj;
        TypeConstructor mo11528getTypeConstructor;
        TypeDeserializer$typeConstructor$1 typeDeserializer$typeConstructor$1 = new TypeDeserializer$typeConstructor$1(this, type);
        if (type.hasClassName()) {
            ClassDescriptor mo12165invoke = this.classDescriptors.mo12165invoke(Integer.valueOf(type.getClassName()));
            if (mo12165invoke == null) {
                mo12165invoke = typeDeserializer$typeConstructor$1.invoke(type.getClassName());
            }
            TypeConstructor mo11528getTypeConstructor2 = mo12165invoke.mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor2, "(classDescriptors(proto.…assName)).typeConstructor");
            return mo11528getTypeConstructor2;
        } else if (type.hasTypeParameter()) {
            TypeConstructor typeParameterTypeConstructor = typeParameterTypeConstructor(type.getTypeParameter());
            if (typeParameterTypeConstructor != null) {
                return typeParameterTypeConstructor;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown type parameter ");
            outline107.append(type.getTypeParameter());
            outline107.append(". Please try recompiling module containing \"");
            outline107.append(this.containerPresentableName);
            outline107.append('\"');
            TypeConstructor createErrorTypeConstructor = ErrorUtils.createErrorTypeConstructor(outline107.toString());
            Intrinsics.checkExpressionValueIsNotNull(createErrorTypeConstructor, "ErrorUtils.createErrorTy…\\\"\"\n                    )");
            return createErrorTypeConstructor;
        } else if (type.hasTypeParameterName()) {
            DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
            String string = this.c.getNameResolver().getString(type.getTypeParameterName());
            Iterator<T> it2 = getOwnTypeParameters().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (Intrinsics.areEqual(((TypeParameterDescriptor) obj).getName().asString(), string)) {
                    break;
                }
            }
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) obj;
            if (typeParameterDescriptor != null && (mo11528getTypeConstructor = typeParameterDescriptor.mo11528getTypeConstructor()) != null) {
                return mo11528getTypeConstructor;
            }
            TypeConstructor createErrorTypeConstructor2 = ErrorUtils.createErrorTypeConstructor("Deserialized type parameter " + string + " in " + containingDeclaration);
            Intrinsics.checkExpressionValueIsNotNull(createErrorTypeConstructor2, "ErrorUtils.createErrorTy…ter $name in $container\")");
            return createErrorTypeConstructor2;
        } else if (!type.hasTypeAliasName()) {
            TypeConstructor createErrorTypeConstructor3 = ErrorUtils.createErrorTypeConstructor("Unknown type");
            Intrinsics.checkExpressionValueIsNotNull(createErrorTypeConstructor3, "ErrorUtils.createErrorTy…nstructor(\"Unknown type\")");
            return createErrorTypeConstructor3;
        } else {
            ClassifierDescriptor mo12165invoke2 = this.typeAliasDescriptors.mo12165invoke(Integer.valueOf(type.getTypeAliasName()));
            if (mo12165invoke2 == null) {
                mo12165invoke2 = typeDeserializer$typeConstructor$1.invoke(type.getTypeAliasName());
            }
            TypeConstructor mo11528getTypeConstructor3 = mo12165invoke2.mo11528getTypeConstructor();
            Intrinsics.checkExpressionValueIsNotNull(mo11528getTypeConstructor3, "(typeAliasDescriptors(pr…iasName)).typeConstructor");
            return mo11528getTypeConstructor3;
        }
    }

    private final TypeConstructor typeParameterTypeConstructor(int i) {
        TypeConstructor mo11528getTypeConstructor;
        TypeParameterDescriptor typeParameterDescriptor = this.typeParameterDescriptors.get(Integer.valueOf(i));
        if (typeParameterDescriptor == null || (mo11528getTypeConstructor = typeParameterDescriptor.mo11528getTypeConstructor()) == null) {
            TypeDeserializer typeDeserializer = this.parent;
            if (typeDeserializer == null) {
                return null;
            }
            return typeDeserializer.typeParameterTypeConstructor(i);
        }
        return mo11528getTypeConstructor;
    }

    public final boolean getExperimentalSuspendFunctionTypeEncountered() {
        return this.experimentalSuspendFunctionTypeEncountered;
    }

    @NotNull
    public final List<TypeParameterDescriptor> getOwnTypeParameters() {
        List<TypeParameterDescriptor> list;
        list = CollectionsKt___CollectionsKt.toList(this.typeParameterDescriptors.values());
        return list;
    }

    @NotNull
    public final SimpleType simpleType(@NotNull ProtoBuf.Type proto) {
        SimpleType computeLocalClassifierReplacementType;
        int collectionSizeOrDefault;
        List<? extends TypeProjection> list;
        SimpleType simpleType$default;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        if (proto.hasClassName()) {
            computeLocalClassifierReplacementType = computeLocalClassifierReplacementType(proto.getClassName());
        } else {
            computeLocalClassifierReplacementType = proto.hasTypeAliasName() ? computeLocalClassifierReplacementType(proto.getTypeAliasName()) : null;
        }
        if (computeLocalClassifierReplacementType != null) {
            return computeLocalClassifierReplacementType;
        }
        TypeConstructor typeConstructor = typeConstructor(proto);
        if (ErrorUtils.isError(typeConstructor.mo12085getDeclarationDescriptor())) {
            SimpleType createErrorTypeWithCustomConstructor = ErrorUtils.createErrorTypeWithCustomConstructor(typeConstructor.toString(), typeConstructor);
            Intrinsics.checkExpressionValueIsNotNull(createErrorTypeWithCustomConstructor, "ErrorUtils.createErrorTy….toString(), constructor)");
            return createErrorTypeWithCustomConstructor;
        }
        DeserializedAnnotations deserializedAnnotations = new DeserializedAnnotations(this.c.getStorageManager(), new TypeDeserializer$simpleType$annotations$1(this, proto));
        List<ProtoBuf.Type.Argument> invoke2 = new TypeDeserializer$simpleType$1(this).invoke2(proto);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(invoke2, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        int i = 0;
        for (Object obj : invoke2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "constructor.parameters");
            arrayList.add(typeArgument((TypeParameterDescriptor) CollectionsKt.getOrNull(parameters, i), (ProtoBuf.Type.Argument) obj));
            i = i2;
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        Boolean mo11937get = Flags.SUSPEND_TYPE.mo11937get(proto.getFlags());
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.SUSPEND_TYPE.get(proto.flags)");
        if (mo11937get.booleanValue()) {
            simpleType$default = createSuspendFunctionType(deserializedAnnotations, typeConstructor, list, proto.getNullable());
        } else {
            simpleType$default = KotlinTypeFactory.simpleType$default(deserializedAnnotations, typeConstructor, list, proto.getNullable(), null, 16, null);
        }
        ProtoBuf.Type abbreviatedType = ProtoTypeTableUtilKt.abbreviatedType(proto, this.c.getTypeTable());
        return abbreviatedType != null ? SpecialTypesKt.withAbbreviation(simpleType$default, simpleType(abbreviatedType)) : simpleType$default;
    }

    @NotNull
    public String toString() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.debugName);
        if (this.parent == null) {
            sb = "";
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(". Child of ");
            outline107.append(this.parent.debugName);
            sb = outline107.toString();
        }
        sb2.append(sb);
        return sb2.toString();
    }

    @NotNull
    public final KotlinType type(@NotNull ProtoBuf.Type proto) {
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        if (proto.hasFlexibleTypeCapabilitiesId()) {
            String string = this.c.getNameResolver().getString(proto.getFlexibleTypeCapabilitiesId());
            SimpleType simpleType = simpleType(proto);
            ProtoBuf.Type flexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(proto, this.c.getTypeTable());
            if (flexibleUpperBound == null) {
                Intrinsics.throwNpe();
            }
            return this.c.getComponents().getFlexibleTypeDeserializer().create(proto, string, simpleType, simpleType(flexibleUpperBound));
        }
        return simpleType(proto);
    }

    public /* synthetic */ TypeDeserializer(DeserializationContext deserializationContext, TypeDeserializer typeDeserializer, List list, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationContext, typeDeserializer, list, str, str2, (i & 32) != 0 ? false : z);
    }
}
