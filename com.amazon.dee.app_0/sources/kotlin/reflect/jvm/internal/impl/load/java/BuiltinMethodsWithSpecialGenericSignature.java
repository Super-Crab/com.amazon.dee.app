package kotlin.reflect.jvm.internal.impl.load.java;

import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.dee.app.metrics.MetricsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: specialBuiltinMembers.kt */
/* loaded from: classes3.dex */
public final class BuiltinMethodsWithSpecialGenericSignature {
    @NotNull
    private static final List<String> ERASED_COLLECTION_PARAMETER_NAMES;
    private static final List<NameAndSignature> ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
    private static final List<String> ERASED_COLLECTION_PARAMETER_SIGNATURES;
    private static final Set<Name> ERASED_VALUE_PARAMETERS_SHORT_NAMES;
    @NotNull
    private static final Set<String> ERASED_VALUE_PARAMETERS_SIGNATURES;
    private static final Map<NameAndSignature, TypeSafeBarrierDescription> GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP;
    public static final BuiltinMethodsWithSpecialGenericSignature INSTANCE = new BuiltinMethodsWithSpecialGenericSignature();
    private static final Map<String, TypeSafeBarrierDescription> SIGNATURE_TO_DEFAULT_VALUES_MAP;

    /* compiled from: specialBuiltinMembers.kt */
    /* loaded from: classes3.dex */
    public enum SpecialSignatureInfo {
        ONE_COLLECTION_PARAMETER("Ljava/util/Collection<+Ljava/lang/Object;>;", false),
        OBJECT_PARAMETER_NON_GENERIC(null, true),
        OBJECT_PARAMETER_GENERIC("Ljava/lang/Object;", true);
        
        private final boolean isObjectReplacedWithTypeParameter;
        @Nullable
        private final String valueParametersSignature;

        SpecialSignatureInfo(String str, boolean z) {
            this.valueParametersSignature = str;
            this.isObjectReplacedWithTypeParameter = z;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: specialBuiltinMembers.kt */
    /* loaded from: classes3.dex */
    public static final class TypeSafeBarrierDescription extends Enum<TypeSafeBarrierDescription> {
        private static final /* synthetic */ TypeSafeBarrierDescription[] $VALUES;
        public static final TypeSafeBarrierDescription FALSE;
        public static final TypeSafeBarrierDescription INDEX;
        public static final TypeSafeBarrierDescription MAP_GET_OR_DEFAULT;
        public static final TypeSafeBarrierDescription NULL;
        @Nullable
        private final Object defaultValue;

        /* compiled from: specialBuiltinMembers.kt */
        /* loaded from: classes3.dex */
        static final class MAP_GET_OR_DEFAULT extends TypeSafeBarrierDescription {
            MAP_GET_OR_DEFAULT(String str, int i) {
                super(str, i, null, null);
            }
        }

        static {
            TypeSafeBarrierDescription typeSafeBarrierDescription = new TypeSafeBarrierDescription(AppInformation.NULL, 0, null);
            NULL = typeSafeBarrierDescription;
            TypeSafeBarrierDescription typeSafeBarrierDescription2 = new TypeSafeBarrierDescription("INDEX", 1, -1);
            INDEX = typeSafeBarrierDescription2;
            TypeSafeBarrierDescription typeSafeBarrierDescription3 = new TypeSafeBarrierDescription("FALSE", 2, false);
            FALSE = typeSafeBarrierDescription3;
            MAP_GET_OR_DEFAULT map_get_or_default = new MAP_GET_OR_DEFAULT("MAP_GET_OR_DEFAULT", 3);
            MAP_GET_OR_DEFAULT = map_get_or_default;
            $VALUES = new TypeSafeBarrierDescription[]{typeSafeBarrierDescription, typeSafeBarrierDescription2, typeSafeBarrierDescription3, map_get_or_default};
        }

        private TypeSafeBarrierDescription(String str, int i, Object obj) {
            this.defaultValue = obj;
        }

        public static TypeSafeBarrierDescription valueOf(String str) {
            return (TypeSafeBarrierDescription) Enum.valueOf(TypeSafeBarrierDescription.class, str);
        }

        public static TypeSafeBarrierDescription[] values() {
            return (TypeSafeBarrierDescription[]) $VALUES.clone();
        }

        public /* synthetic */ TypeSafeBarrierDescription(String str, int i, Object obj, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, obj);
        }
    }

    static {
        Set<String> of;
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        int collectionSizeOrDefault3;
        NameAndSignature method;
        NameAndSignature method2;
        NameAndSignature method3;
        NameAndSignature method4;
        NameAndSignature method5;
        NameAndSignature method6;
        NameAndSignature method7;
        NameAndSignature method8;
        NameAndSignature method9;
        NameAndSignature method10;
        Map<NameAndSignature, TypeSafeBarrierDescription> mapOf;
        int mapCapacity;
        Set<NameAndSignature> plus;
        int collectionSizeOrDefault4;
        Set<Name> set;
        int collectionSizeOrDefault5;
        Set<String> set2;
        NameAndSignature method11;
        of = SetsKt__SetsKt.setOf((Object[]) new String[]{"containsAll", "removeAll", "retainAll"});
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(of, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str : of) {
            String desc = JvmPrimitiveType.BOOLEAN.getDesc();
            Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.BOOLEAN.desc");
            method11 = SpecialBuiltinMembers.method("java/util/Collection", str, "Ljava/util/Collection;", desc);
            arrayList.add(method11);
        }
        ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES = arrayList;
        List<NameAndSignature> list = ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        for (NameAndSignature nameAndSignature : list) {
            arrayList2.add(nameAndSignature.getSignature());
        }
        ERASED_COLLECTION_PARAMETER_SIGNATURES = arrayList2;
        List<NameAndSignature> list2 = ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
        collectionSizeOrDefault3 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault3);
        for (NameAndSignature nameAndSignature2 : list2) {
            arrayList3.add(nameAndSignature2.getName().asString());
        }
        ERASED_COLLECTION_PARAMETER_NAMES = arrayList3;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String javaUtil = signatureBuildingComponents.javaUtil("Collection");
        String desc2 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc2, "JvmPrimitiveType.BOOLEAN.desc");
        method = SpecialBuiltinMembers.method(javaUtil, "contains", "Ljava/lang/Object;", desc2);
        String javaUtil2 = signatureBuildingComponents.javaUtil("Collection");
        String desc3 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc3, "JvmPrimitiveType.BOOLEAN.desc");
        method2 = SpecialBuiltinMembers.method(javaUtil2, BulkOperationType.remove, "Ljava/lang/Object;", desc3);
        String javaUtil3 = signatureBuildingComponents.javaUtil("Map");
        String desc4 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc4, "JvmPrimitiveType.BOOLEAN.desc");
        method3 = SpecialBuiltinMembers.method(javaUtil3, "containsKey", "Ljava/lang/Object;", desc4);
        String javaUtil4 = signatureBuildingComponents.javaUtil("Map");
        String desc5 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc5, "JvmPrimitiveType.BOOLEAN.desc");
        method4 = SpecialBuiltinMembers.method(javaUtil4, "containsValue", "Ljava/lang/Object;", desc5);
        String javaUtil5 = signatureBuildingComponents.javaUtil("Map");
        String desc6 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc6, "JvmPrimitiveType.BOOLEAN.desc");
        method5 = SpecialBuiltinMembers.method(javaUtil5, BulkOperationType.remove, "Ljava/lang/Object;Ljava/lang/Object;", desc6);
        method6 = SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), "getOrDefault", "Ljava/lang/Object;Ljava/lang/Object;", "Ljava/lang/Object;");
        method7 = SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), MetricsConstants.Method.CACHE_GET, "Ljava/lang/Object;", "Ljava/lang/Object;");
        method8 = SpecialBuiltinMembers.method(signatureBuildingComponents.javaUtil("Map"), BulkOperationType.remove, "Ljava/lang/Object;", "Ljava/lang/Object;");
        String javaUtil6 = signatureBuildingComponents.javaUtil("List");
        String desc7 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc7, "JvmPrimitiveType.INT.desc");
        method9 = SpecialBuiltinMembers.method(javaUtil6, "indexOf", "Ljava/lang/Object;", desc7);
        String javaUtil7 = signatureBuildingComponents.javaUtil("List");
        String desc8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc8, "JvmPrimitiveType.INT.desc");
        method10 = SpecialBuiltinMembers.method(javaUtil7, "lastIndexOf", "Ljava/lang/Object;", desc8);
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(method, TypeSafeBarrierDescription.FALSE), TuplesKt.to(method2, TypeSafeBarrierDescription.FALSE), TuplesKt.to(method3, TypeSafeBarrierDescription.FALSE), TuplesKt.to(method4, TypeSafeBarrierDescription.FALSE), TuplesKt.to(method5, TypeSafeBarrierDescription.FALSE), TuplesKt.to(method6, TypeSafeBarrierDescription.MAP_GET_OR_DEFAULT), TuplesKt.to(method7, TypeSafeBarrierDescription.NULL), TuplesKt.to(method8, TypeSafeBarrierDescription.NULL), TuplesKt.to(method9, TypeSafeBarrierDescription.INDEX), TuplesKt.to(method10, TypeSafeBarrierDescription.INDEX));
        GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP = mapOf;
        Map<NameAndSignature, TypeSafeBarrierDescription> map = GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(map.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        Iterator<T> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_DEFAULT_VALUES_MAP = linkedHashMap;
        plus = SetsKt___SetsKt.plus((Set) GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP.keySet(), (Iterable) ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES);
        collectionSizeOrDefault4 = CollectionsKt__IterablesKt.collectionSizeOrDefault(plus, 10);
        ArrayList arrayList4 = new ArrayList(collectionSizeOrDefault4);
        for (NameAndSignature nameAndSignature3 : plus) {
            arrayList4.add(nameAndSignature3.getName());
        }
        set = CollectionsKt___CollectionsKt.toSet(arrayList4);
        ERASED_VALUE_PARAMETERS_SHORT_NAMES = set;
        collectionSizeOrDefault5 = CollectionsKt__IterablesKt.collectionSizeOrDefault(plus, 10);
        ArrayList arrayList5 = new ArrayList(collectionSizeOrDefault5);
        for (NameAndSignature nameAndSignature4 : plus) {
            arrayList5.add(nameAndSignature4.getSignature());
        }
        set2 = CollectionsKt___CollectionsKt.toSet(arrayList5);
        ERASED_VALUE_PARAMETERS_SIGNATURES = set2;
    }

    private BuiltinMethodsWithSpecialGenericSignature() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasErasedValueParametersInJava(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        boolean contains;
        contains = CollectionsKt___CollectionsKt.contains(ERASED_VALUE_PARAMETERS_SIGNATURES, MethodSignatureMappingKt.computeJvmSignature(callableMemberDescriptor));
        return contains;
    }

    @JvmStatic
    @Nullable
    public static final FunctionDescriptor getOverriddenBuiltinFunctionWithErasedValueParametersInJava(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = INSTANCE;
        Name name = functionDescriptor.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "functionDescriptor.name");
        if (!builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return null;
        }
        return (FunctionDescriptor) DescriptorUtilsKt.firstOverridden$default(functionDescriptor, false, BuiltinMethodsWithSpecialGenericSignature$getOverriddenBuiltinFunctionWithErasedValueParametersInJava$1.INSTANCE, 1, null);
    }

    @JvmStatic
    @Nullable
    public static final SpecialSignatureInfo getSpecialSignatureInfo(@NotNull CallableMemberDescriptor getSpecialSignatureInfo) {
        CallableMemberDescriptor firstOverridden$default;
        String computeJvmSignature;
        Intrinsics.checkParameterIsNotNull(getSpecialSignatureInfo, "$this$getSpecialSignatureInfo");
        if (!ERASED_VALUE_PARAMETERS_SHORT_NAMES.contains(getSpecialSignatureInfo.getName()) || (firstOverridden$default = DescriptorUtilsKt.firstOverridden$default(getSpecialSignatureInfo, false, BuiltinMethodsWithSpecialGenericSignature$getSpecialSignatureInfo$builtinSignature$1.INSTANCE, 1, null)) == null || (computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(firstOverridden$default)) == null) {
            return null;
        }
        if (ERASED_COLLECTION_PARAMETER_SIGNATURES.contains(computeJvmSignature)) {
            return SpecialSignatureInfo.ONE_COLLECTION_PARAMETER;
        }
        if (((TypeSafeBarrierDescription) MapsKt.getValue(SIGNATURE_TO_DEFAULT_VALUES_MAP, computeJvmSignature)) == TypeSafeBarrierDescription.NULL) {
            return SpecialSignatureInfo.OBJECT_PARAMETER_GENERIC;
        }
        return SpecialSignatureInfo.OBJECT_PARAMETER_NON_GENERIC;
    }

    public final boolean getSameAsBuiltinMethodWithErasedValueParameters(@NotNull Name sameAsBuiltinMethodWithErasedValueParameters) {
        Intrinsics.checkParameterIsNotNull(sameAsBuiltinMethodWithErasedValueParameters, "$this$sameAsBuiltinMethodWithErasedValueParameters");
        return ERASED_VALUE_PARAMETERS_SHORT_NAMES.contains(sameAsBuiltinMethodWithErasedValueParameters);
    }
}
