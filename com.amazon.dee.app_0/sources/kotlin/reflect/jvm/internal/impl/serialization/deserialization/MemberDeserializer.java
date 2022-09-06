package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirement;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: MemberDeserializer.kt */
/* loaded from: classes4.dex */
public final class MemberDeserializer {
    private final AnnotationDeserializer annotationDeserializer;
    private final DeserializationContext c;

    public MemberDeserializer(@NotNull DeserializationContext c) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        this.c = c;
        this.annotationDeserializer = new AnnotationDeserializer(this.c.getComponents().getModuleDescriptor(), this.c.getComponents().getNotFoundClasses());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ProtoContainer asProtoContainer(@NotNull DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor instanceof PackageFragmentDescriptor) {
            return new ProtoContainer.Package(((PackageFragmentDescriptor) declarationDescriptor).getFqName(), this.c.getNameResolver(), this.c.getTypeTable(), this.c.getContainerSource());
        }
        if (!(declarationDescriptor instanceof DeserializedClassDescriptor)) {
            return null;
        }
        return ((DeserializedClassDescriptor) declarationDescriptor).getThisAsProtoContainer$deserialization();
    }

    private final DeserializedMemberDescriptor.CoroutinesCompatibilityMode checkExperimentalCoroutine(@NotNull DeserializedMemberDescriptor deserializedMemberDescriptor, TypeDeserializer typeDeserializer) {
        if (!versionAndReleaseCoroutinesMismatch(deserializedMemberDescriptor)) {
            return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
        }
        forceUpperBoundsComputation(typeDeserializer);
        if (typeDeserializer.getExperimentalSuspendFunctionTypeEncountered()) {
            return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
        }
        return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
    }

    private final DeserializedMemberDescriptor.CoroutinesCompatibilityMode computeExperimentalityModeForFunctions(@NotNull DeserializedCallableMemberDescriptor deserializedCallableMemberDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor, Collection<? extends ValueParameterDescriptor> collection, Collection<? extends TypeParameterDescriptor> collection2, KotlinType kotlinType, boolean z) {
        int collectionSizeOrDefault;
        List listOfNotNull;
        List<KotlinType> plus;
        boolean z2;
        boolean z3;
        int collectionSizeOrDefault2;
        DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityMode;
        DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityMode2;
        boolean z4;
        if (versionAndReleaseCoroutinesMismatch(deserializedCallableMemberDescriptor) && !Intrinsics.areEqual(DescriptorUtilsKt.fqNameOrNull(deserializedCallableMemberDescriptor), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(collection, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (ValueParameterDescriptor valueParameterDescriptor : collection) {
                arrayList.add(valueParameterDescriptor.getType());
            }
            listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(receiverParameterDescriptor != null ? receiverParameterDescriptor.getType() : null);
            plus = CollectionsKt___CollectionsKt.plus((Collection) arrayList, (Iterable) listOfNotNull);
            if (kotlinType != null && containsSuspendFunctionType(kotlinType)) {
                return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
            }
            if (!(collection2 instanceof Collection) || !collection2.isEmpty()) {
                for (TypeParameterDescriptor typeParameterDescriptor : collection2) {
                    List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
                    Intrinsics.checkExpressionValueIsNotNull(upperBounds, "typeParameter.upperBounds");
                    if (!upperBounds.isEmpty()) {
                        for (KotlinType it2 : upperBounds) {
                            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                            if (containsSuspendFunctionType(it2)) {
                                z2 = true;
                                continue;
                                break;
                            }
                        }
                    }
                    z2 = false;
                    continue;
                    if (z2) {
                        z3 = true;
                        break;
                    }
                }
            }
            z3 = false;
            if (z3) {
                return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
            }
            collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(plus, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
            for (KotlinType type : plus) {
                Intrinsics.checkExpressionValueIsNotNull(type, "type");
                if (FunctionTypesKt.isSuspendFunctionType(type) && type.getArguments().size() <= 3) {
                    List<TypeProjection> arguments = type.getArguments();
                    if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
                        for (TypeProjection typeProjection : arguments) {
                            KotlinType type2 = typeProjection.getType();
                            Intrinsics.checkExpressionValueIsNotNull(type2, "it.type");
                            if (containsSuspendFunctionType(type2)) {
                                z4 = true;
                                break;
                            }
                        }
                    }
                    z4 = false;
                    if (z4) {
                        coroutinesCompatibilityMode2 = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
                    } else {
                        coroutinesCompatibilityMode2 = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.NEEDS_WRAPPER;
                    }
                } else {
                    coroutinesCompatibilityMode2 = containsSuspendFunctionType(type) ? DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE : DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
                }
                arrayList2.add(coroutinesCompatibilityMode2);
            }
            DeserializedMemberDescriptor.CoroutinesCompatibilityMode coroutinesCompatibilityMode3 = (DeserializedMemberDescriptor.CoroutinesCompatibilityMode) CollectionsKt.max((Iterable<? extends Comparable>) arrayList2);
            if (coroutinesCompatibilityMode3 == null) {
                coroutinesCompatibilityMode3 = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
            }
            if (z) {
                coroutinesCompatibilityMode = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.NEEDS_WRAPPER;
            } else {
                coroutinesCompatibilityMode = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
            }
            return (DeserializedMemberDescriptor.CoroutinesCompatibilityMode) ComparisonsKt.maxOf(coroutinesCompatibilityMode, coroutinesCompatibilityMode3);
        }
        return DeserializedMemberDescriptor.CoroutinesCompatibilityMode.COMPATIBLE;
    }

    private final boolean containsSuspendFunctionType(@NotNull KotlinType kotlinType) {
        return TypeUtilsKt.contains(kotlinType, MemberDeserializer$containsSuspendFunctionType$1.INSTANCE);
    }

    private final void forceUpperBoundsComputation(TypeDeserializer typeDeserializer) {
        for (TypeParameterDescriptor typeParameterDescriptor : typeDeserializer.getOwnTypeParameters()) {
            typeParameterDescriptor.getUpperBounds();
        }
    }

    private final Annotations getAnnotations(MessageLite messageLite, int i, AnnotatedCallableKind annotatedCallableKind) {
        if (!Flags.HAS_ANNOTATIONS.mo11937get(i).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getAnnotations$1(this, messageLite, annotatedCallableKind));
    }

    private final ReceiverParameterDescriptor getDispatchReceiverParameter() {
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        if (classDescriptor != null) {
            return classDescriptor.getThisAsReceiverParameter();
        }
        return null;
    }

    private final Annotations getPropertyFieldAnnotations(ProtoBuf.Property property, boolean z) {
        if (!Flags.HAS_ANNOTATIONS.mo11937get(property.getFlags()).booleanValue()) {
            return Annotations.Companion.getEMPTY();
        }
        return new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getPropertyFieldAnnotations$1(this, z, property));
    }

    private final Annotations getReceiverParameterAnnotations(MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        return new DeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$getReceiverParameterAnnotations$1(this, messageLite, annotatedCallableKind));
    }

    private final void initializeWithCoroutinesExperimentalityStatus(@NotNull DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor2, List<? extends TypeParameterDescriptor> list, List<? extends ValueParameterDescriptor> list2, KotlinType kotlinType, Modality modality, Visibility visibility, Map<? extends CallableDescriptor.UserDataKey<?>, ?> map, boolean z) {
        deserializedSimpleFunctionDescriptor.initialize(receiverParameterDescriptor, receiverParameterDescriptor2, list, list2, kotlinType, modality, visibility, map, computeExperimentalityModeForFunctions(deserializedSimpleFunctionDescriptor, receiverParameterDescriptor, list2, list, kotlinType, z));
    }

    private final int loadOldFlags(int i) {
        return (i & 63) + ((i >> 8) << 6);
    }

    private final List<ValueParameterDescriptor> valueParameters(List<ProtoBuf.ValueParameter> list, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        int collectionSizeOrDefault;
        List<ValueParameterDescriptor> list2;
        NonEmptyDeserializedAnnotations empty;
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (containingDeclaration != null) {
            CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
            DeclarationDescriptor mo11607getContainingDeclaration = callableDescriptor.mo11607getContainingDeclaration();
            Intrinsics.checkExpressionValueIsNotNull(mo11607getContainingDeclaration, "callableDescriptor.containingDeclaration");
            ProtoContainer asProtoContainer = asProtoContainer(mo11607getContainingDeclaration);
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                ProtoBuf.ValueParameter valueParameter = (ProtoBuf.ValueParameter) obj;
                int flags = valueParameter.hasFlags() ? valueParameter.getFlags() : 0;
                if (asProtoContainer != null && GeneratedOutlineSupport1.outline192(Flags.HAS_ANNOTATIONS, flags, "Flags.HAS_ANNOTATIONS.get(flags)")) {
                    empty = new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new MemberDeserializer$valueParameters$$inlined$mapIndexed$lambda$1(i, valueParameter, this, asProtoContainer, messageLite, annotatedCallableKind, callableDescriptor));
                } else {
                    empty = Annotations.Companion.getEMPTY();
                }
                Name name = NameResolverUtilKt.getName(this.c.getNameResolver(), valueParameter.getName());
                KotlinType type = this.c.getTypeDeserializer().type(ProtoTypeTableUtilKt.type(valueParameter, this.c.getTypeTable()));
                boolean outline192 = GeneratedOutlineSupport1.outline192(Flags.DECLARES_DEFAULT_VALUE, flags, "Flags.DECLARES_DEFAULT_VALUE.get(flags)");
                boolean outline1922 = GeneratedOutlineSupport1.outline192(Flags.IS_CROSSINLINE, flags, "Flags.IS_CROSSINLINE.get(flags)");
                boolean outline1923 = GeneratedOutlineSupport1.outline192(Flags.IS_NOINLINE, flags, "Flags.IS_NOINLINE.get(flags)");
                ProtoBuf.Type varargElementType = ProtoTypeTableUtilKt.varargElementType(valueParameter, this.c.getTypeTable());
                KotlinType type2 = varargElementType != null ? this.c.getTypeDeserializer().type(varargElementType) : null;
                SourceElement sourceElement = SourceElement.NO_SOURCE;
                Intrinsics.checkExpressionValueIsNotNull(sourceElement, "SourceElement.NO_SOURCE");
                ArrayList arrayList2 = arrayList;
                arrayList2.add(new ValueParameterDescriptorImpl(callableDescriptor, null, i, empty, name, type, outline192, outline1922, outline1923, type2, sourceElement));
                arrayList = arrayList2;
                i = i2;
            }
            list2 = CollectionsKt___CollectionsKt.toList(arrayList);
            return list2;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
    }

    private final boolean versionAndReleaseCoroutinesMismatch(@NotNull DeserializedMemberDescriptor deserializedMemberDescriptor) {
        boolean z;
        boolean z2;
        if (this.c.getComponents().getConfiguration().getReleaseCoroutines()) {
            List<VersionRequirement> versionRequirements = deserializedMemberDescriptor.getVersionRequirements();
            if (!(versionRequirements instanceof Collection) || !versionRequirements.isEmpty()) {
                for (VersionRequirement versionRequirement : versionRequirements) {
                    if (!Intrinsics.areEqual(versionRequirement.getVersion(), new VersionRequirement.Version(1, 3, 0, 4, null)) || versionRequirement.getKind() != ProtoBuf.VersionRequirement.VersionKind.LANGUAGE_VERSION) {
                        z = false;
                        continue;
                    } else {
                        z = true;
                        continue;
                    }
                    if (z) {
                        z2 = false;
                        break;
                    }
                }
            }
            z2 = true;
            return z2;
        }
        return false;
    }

    @NotNull
    public final ClassConstructorDescriptor loadConstructor(@NotNull ProtoBuf.Constructor proto, boolean z) {
        List emptyList;
        DeserializedClassConstructorDescriptor deserializedClassConstructorDescriptor;
        DeserializedMemberDescriptor.CoroutinesCompatibilityMode computeExperimentalityModeForFunctions;
        DeserializationContext c;
        TypeDeserializer typeDeserializer;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (containingDeclaration != null) {
            ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
            DeserializedClassConstructorDescriptor deserializedClassConstructorDescriptor2 = new DeserializedClassConstructorDescriptor(classDescriptor, null, getAnnotations(proto, proto.getFlags(), AnnotatedCallableKind.FUNCTION), z, CallableMemberDescriptor.Kind.DECLARATION, proto, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource(), null, 1024, null);
            DeserializationContext deserializationContext = this.c;
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            MemberDeserializer memberDeserializer = DeserializationContext.childContext$default(deserializationContext, deserializedClassConstructorDescriptor2, emptyList, null, null, null, null, 60, null).getMemberDeserializer();
            List<ProtoBuf.ValueParameter> valueParameterList = proto.getValueParameterList();
            Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
            deserializedClassConstructorDescriptor2.initialize(memberDeserializer.valueParameters(valueParameterList, proto, AnnotatedCallableKind.FUNCTION), ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.mo11937get(proto.getFlags())));
            deserializedClassConstructorDescriptor2.setReturnType(classDescriptor.getDefaultType());
            DeclarationDescriptor containingDeclaration2 = this.c.getContainingDeclaration();
            if (!(containingDeclaration2 instanceof DeserializedClassDescriptor)) {
                containingDeclaration2 = null;
            }
            DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) containingDeclaration2;
            boolean z2 = true;
            if (deserializedClassDescriptor == null || (c = deserializedClassDescriptor.getC()) == null || (typeDeserializer = c.getTypeDeserializer()) == null || !typeDeserializer.getExperimentalSuspendFunctionTypeEncountered() || !versionAndReleaseCoroutinesMismatch(deserializedClassConstructorDescriptor2)) {
                z2 = false;
            }
            if (z2) {
                computeExperimentalityModeForFunctions = DeserializedMemberDescriptor.CoroutinesCompatibilityMode.INCOMPATIBLE;
                deserializedClassConstructorDescriptor = deserializedClassConstructorDescriptor2;
            } else {
                Collection<? extends ValueParameterDescriptor> valueParameters = deserializedClassConstructorDescriptor2.getValueParameters();
                Intrinsics.checkExpressionValueIsNotNull(valueParameters, "descriptor.valueParameters");
                Collection<? extends TypeParameterDescriptor> typeParameters = deserializedClassConstructorDescriptor2.getTypeParameters();
                Intrinsics.checkExpressionValueIsNotNull(typeParameters, "descriptor.typeParameters");
                deserializedClassConstructorDescriptor = deserializedClassConstructorDescriptor2;
                computeExperimentalityModeForFunctions = computeExperimentalityModeForFunctions(deserializedClassConstructorDescriptor2, null, valueParameters, typeParameters, deserializedClassConstructorDescriptor2.getReturnType(), false);
            }
            deserializedClassConstructorDescriptor.setCoroutinesExperimentalCompatibilityMode$deserialization(computeExperimentalityModeForFunctions);
            return deserializedClassConstructorDescriptor;
        }
        throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
    }

    @NotNull
    public final SimpleFunctionDescriptor loadFunction(@NotNull ProtoBuf.Function proto) {
        Annotations empty;
        VersionRequirementTable versionRequirementTable;
        Map<? extends CallableDescriptor.UserDataKey<?>, ?> emptyMap;
        KotlinType type;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        int flags = proto.hasFlags() ? proto.getFlags() : loadOldFlags(proto.getOldFlags());
        Annotations annotations = getAnnotations(proto, flags, AnnotatedCallableKind.FUNCTION);
        if (ProtoTypeTableUtilKt.hasReceiver(proto)) {
            empty = getReceiverParameterAnnotations(proto, AnnotatedCallableKind.FUNCTION);
        } else {
            empty = Annotations.Companion.getEMPTY();
        }
        if (Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(this.c.getContainingDeclaration()).child(NameResolverUtilKt.getName(this.c.getNameResolver(), proto.getName())), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            versionRequirementTable = VersionRequirementTable.Companion.getEMPTY();
        } else {
            versionRequirementTable = this.c.getVersionRequirementTable();
        }
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor = new DeserializedSimpleFunctionDescriptor(this.c.getContainingDeclaration(), null, annotations, NameResolverUtilKt.getName(this.c.getNameResolver(), proto.getName()), ProtoEnumFlags.INSTANCE.memberKind(Flags.MEMBER_KIND.mo11937get(flags)), proto, this.c.getNameResolver(), this.c.getTypeTable(), versionRequirementTable, this.c.getContainerSource(), null, 1024, null);
        DeserializationContext deserializationContext = this.c;
        List<ProtoBuf.TypeParameter> typeParameterList = proto.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "proto.typeParameterList");
        DeserializationContext childContext$default = DeserializationContext.childContext$default(deserializationContext, deserializedSimpleFunctionDescriptor, typeParameterList, null, null, null, null, 60, null);
        ProtoBuf.Type receiverType = ProtoTypeTableUtilKt.receiverType(proto, this.c.getTypeTable());
        ReceiverParameterDescriptor createExtensionReceiverParameterForCallable = (receiverType == null || (type = childContext$default.getTypeDeserializer().type(receiverType)) == null) ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(deserializedSimpleFunctionDescriptor, type, empty);
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        List<TypeParameterDescriptor> ownTypeParameters = childContext$default.getTypeDeserializer().getOwnTypeParameters();
        MemberDeserializer memberDeserializer = childContext$default.getMemberDeserializer();
        List<ProtoBuf.ValueParameter> valueParameterList = proto.getValueParameterList();
        Intrinsics.checkExpressionValueIsNotNull(valueParameterList, "proto.valueParameterList");
        List<ValueParameterDescriptor> valueParameters = memberDeserializer.valueParameters(valueParameterList, proto, AnnotatedCallableKind.FUNCTION);
        KotlinType type2 = childContext$default.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(proto, this.c.getTypeTable()));
        Modality modality = ProtoEnumFlags.INSTANCE.modality(Flags.MODALITY.mo11937get(flags));
        Visibility visibility = ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.mo11937get(flags));
        emptyMap = MapsKt__MapsKt.emptyMap();
        initializeWithCoroutinesExperimentalityStatus(deserializedSimpleFunctionDescriptor, createExtensionReceiverParameterForCallable, dispatchReceiverParameter, ownTypeParameters, valueParameters, type2, modality, visibility, emptyMap, GeneratedOutlineSupport1.outline192(Flags.IS_SUSPEND, flags, "Flags.IS_SUSPEND.get(flags)"));
        Boolean mo11937get = Flags.IS_OPERATOR.mo11937get(flags);
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, "Flags.IS_OPERATOR.get(flags)");
        deserializedSimpleFunctionDescriptor.setOperator(mo11937get.booleanValue());
        Boolean mo11937get2 = Flags.IS_INFIX.mo11937get(flags);
        Intrinsics.checkExpressionValueIsNotNull(mo11937get2, "Flags.IS_INFIX.get(flags)");
        deserializedSimpleFunctionDescriptor.setInfix(mo11937get2.booleanValue());
        Boolean mo11937get3 = Flags.IS_EXTERNAL_FUNCTION.mo11937get(flags);
        Intrinsics.checkExpressionValueIsNotNull(mo11937get3, "Flags.IS_EXTERNAL_FUNCTION.get(flags)");
        deserializedSimpleFunctionDescriptor.setExternal(mo11937get3.booleanValue());
        Boolean mo11937get4 = Flags.IS_INLINE.mo11937get(flags);
        Intrinsics.checkExpressionValueIsNotNull(mo11937get4, "Flags.IS_INLINE.get(flags)");
        deserializedSimpleFunctionDescriptor.setInline(mo11937get4.booleanValue());
        Boolean mo11937get5 = Flags.IS_TAILREC.mo11937get(flags);
        Intrinsics.checkExpressionValueIsNotNull(mo11937get5, "Flags.IS_TAILREC.get(flags)");
        deserializedSimpleFunctionDescriptor.setTailrec(mo11937get5.booleanValue());
        Boolean mo11937get6 = Flags.IS_SUSPEND.mo11937get(flags);
        Intrinsics.checkExpressionValueIsNotNull(mo11937get6, "Flags.IS_SUSPEND.get(flags)");
        deserializedSimpleFunctionDescriptor.setSuspend(mo11937get6.booleanValue());
        Boolean mo11937get7 = Flags.IS_EXPECT_FUNCTION.mo11937get(flags);
        Intrinsics.checkExpressionValueIsNotNull(mo11937get7, "Flags.IS_EXPECT_FUNCTION.get(flags)");
        deserializedSimpleFunctionDescriptor.setExpect(mo11937get7.booleanValue());
        Pair<CallableDescriptor.UserDataKey<?>, Object> deserializeContractFromFunction = this.c.getComponents().getContractDeserializer().deserializeContractFromFunction(proto, deserializedSimpleFunctionDescriptor, this.c.getTypeTable(), this.c.getTypeDeserializer());
        if (deserializeContractFromFunction != null) {
            deserializedSimpleFunctionDescriptor.putInUserDataMap(deserializeContractFromFunction.getFirst(), deserializeContractFromFunction.getSecond());
        }
        return deserializedSimpleFunctionDescriptor;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0297  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor loadProperty(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property r32) {
        /*
            Method dump skipped, instructions count: 716
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer.loadProperty(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property):kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor");
    }

    @NotNull
    public final TypeAliasDescriptor loadTypeAlias(@NotNull ProtoBuf.TypeAlias proto) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(proto, "proto");
        Annotations.Companion companion = Annotations.Companion;
        List<ProtoBuf.Annotation> annotationList = proto.getAnnotationList();
        Intrinsics.checkExpressionValueIsNotNull(annotationList, "proto.annotationList");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(annotationList, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (ProtoBuf.Annotation it2 : annotationList) {
            AnnotationDeserializer annotationDeserializer = this.annotationDeserializer;
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            arrayList.add(annotationDeserializer.deserializeAnnotation(it2, this.c.getNameResolver()));
        }
        DeserializedTypeAliasDescriptor deserializedTypeAliasDescriptor = new DeserializedTypeAliasDescriptor(this.c.getStorageManager(), this.c.getContainingDeclaration(), companion.create(arrayList), NameResolverUtilKt.getName(this.c.getNameResolver(), proto.getName()), ProtoEnumFlags.INSTANCE.visibility(Flags.VISIBILITY.mo11937get(proto.getFlags())), proto, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource());
        DeserializationContext deserializationContext = this.c;
        List<ProtoBuf.TypeParameter> typeParameterList = proto.getTypeParameterList();
        Intrinsics.checkExpressionValueIsNotNull(typeParameterList, "proto.typeParameterList");
        DeserializationContext childContext$default = DeserializationContext.childContext$default(deserializationContext, deserializedTypeAliasDescriptor, typeParameterList, null, null, null, null, 60, null);
        deserializedTypeAliasDescriptor.initialize(childContext$default.getTypeDeserializer().getOwnTypeParameters(), childContext$default.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.underlyingType(proto, this.c.getTypeTable())), childContext$default.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.expandedType(proto, this.c.getTypeTable())), checkExperimentalCoroutine(deserializedTypeAliasDescriptor, childContext$default.getTypeDeserializer()));
        return deserializedTypeAliasDescriptor;
    }
}
