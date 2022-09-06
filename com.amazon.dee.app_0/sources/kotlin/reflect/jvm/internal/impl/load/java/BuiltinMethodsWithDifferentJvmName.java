package kotlin.reflect.jvm.internal.impl.load.java;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.dee.app.metrics.MetricsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: specialBuiltinMembers.kt */
/* loaded from: classes3.dex */
public final class BuiltinMethodsWithDifferentJvmName {
    public static final BuiltinMethodsWithDifferentJvmName INSTANCE = new BuiltinMethodsWithDifferentJvmName();
    private static final Map<Name, List<Name>> JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
    private static final Map<NameAndSignature, Name> NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
    @NotNull
    private static final List<Name> ORIGINAL_SHORT_NAMES;
    private static final NameAndSignature REMOVE_AT_NAME_AND_SIGNATURE;
    private static final Map<String, Name> SIGNATURE_TO_JVM_REPRESENTATION_NAME;

    static {
        NameAndSignature method;
        NameAndSignature method2;
        NameAndSignature method3;
        NameAndSignature method4;
        NameAndSignature method5;
        NameAndSignature method6;
        NameAndSignature method7;
        NameAndSignature method8;
        Map<NameAndSignature, Name> mapOf;
        int mapCapacity;
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        String desc = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.INT.desc");
        method = SpecialBuiltinMembers.method("java/util/List", "removeAt", desc, "Ljava/lang/Object;");
        REMOVE_AT_NAME_AND_SIGNATURE = method;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        String javaLang = signatureBuildingComponents.javaLang("Number");
        String desc2 = JvmPrimitiveType.BYTE.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc2, "JvmPrimitiveType.BYTE.desc");
        method2 = SpecialBuiltinMembers.method(javaLang, "toByte", "", desc2);
        String javaLang2 = signatureBuildingComponents.javaLang("Number");
        String desc3 = JvmPrimitiveType.SHORT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc3, "JvmPrimitiveType.SHORT.desc");
        method3 = SpecialBuiltinMembers.method(javaLang2, "toShort", "", desc3);
        String javaLang3 = signatureBuildingComponents.javaLang("Number");
        String desc4 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc4, "JvmPrimitiveType.INT.desc");
        method4 = SpecialBuiltinMembers.method(javaLang3, "toInt", "", desc4);
        String javaLang4 = signatureBuildingComponents.javaLang("Number");
        String desc5 = JvmPrimitiveType.LONG.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc5, "JvmPrimitiveType.LONG.desc");
        method5 = SpecialBuiltinMembers.method(javaLang4, "toLong", "", desc5);
        String javaLang5 = signatureBuildingComponents.javaLang("Number");
        String desc6 = JvmPrimitiveType.FLOAT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc6, "JvmPrimitiveType.FLOAT.desc");
        method6 = SpecialBuiltinMembers.method(javaLang5, "toFloat", "", desc6);
        String javaLang6 = signatureBuildingComponents.javaLang("Number");
        String desc7 = JvmPrimitiveType.DOUBLE.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc7, "JvmPrimitiveType.DOUBLE.desc");
        method7 = SpecialBuiltinMembers.method(javaLang6, "toDouble", "", desc7);
        String javaLang7 = signatureBuildingComponents.javaLang("CharSequence");
        String desc8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc8, "JvmPrimitiveType.INT.desc");
        String desc9 = JvmPrimitiveType.CHAR.getDesc();
        Intrinsics.checkExpressionValueIsNotNull(desc9, "JvmPrimitiveType.CHAR.desc");
        method8 = SpecialBuiltinMembers.method(javaLang7, MetricsConstants.Method.CACHE_GET, desc8, desc9);
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(method2, Name.identifier("byteValue")), TuplesKt.to(method3, Name.identifier("shortValue")), TuplesKt.to(method4, Name.identifier("intValue")), TuplesKt.to(method5, Name.identifier("longValue")), TuplesKt.to(method6, Name.identifier("floatValue")), TuplesKt.to(method7, Name.identifier("doubleValue")), TuplesKt.to(REMOVE_AT_NAME_AND_SIGNATURE, Name.identifier(BulkOperationType.remove)), TuplesKt.to(method8, Name.identifier("charAt")));
        NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP = mapOf;
        Map<NameAndSignature, Name> map = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(map.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        Iterator<T> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            linkedHashMap.put(((NameAndSignature) entry.getKey()).getSignature(), entry.getValue());
        }
        SIGNATURE_TO_JVM_REPRESENTATION_NAME = linkedHashMap;
        Set<NameAndSignature> keySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.keySet();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(keySet, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (NameAndSignature nameAndSignature : keySet) {
            arrayList.add(nameAndSignature.getName());
        }
        ORIGINAL_SHORT_NAMES = arrayList;
        Set<Map.Entry<NameAndSignature, Name>> entrySet = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.entrySet();
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(entrySet, 10);
        ArrayList<Pair> arrayList2 = new ArrayList(collectionSizeOrDefault2);
        Iterator<T> it3 = entrySet.iterator();
        while (it3.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it3.next();
            arrayList2.add(new Pair(((NameAndSignature) entry2.getKey()).getName(), entry2.getValue()));
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Pair pair : arrayList2) {
            Name name = (Name) pair.getSecond();
            Object obj = linkedHashMap2.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap2.put(name, obj);
            }
            ((List) obj).add((Name) pair.getFirst());
        }
        JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP = linkedHashMap2;
    }

    private BuiltinMethodsWithDifferentJvmName() {
    }

    @NotNull
    public final List<Name> getBuiltinFunctionNamesByJvmName(@NotNull Name name) {
        List<Name> emptyList;
        Intrinsics.checkParameterIsNotNull(name, "name");
        List<Name> list = JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP.get(name);
        if (list != null) {
            return list;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Nullable
    public final Name getJvmName(@NotNull SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        Map<String, Name> map = SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        String computeJvmSignature = MethodSignatureMappingKt.computeJvmSignature(functionDescriptor);
        if (computeJvmSignature != null) {
            return map.get(computeJvmSignature);
        }
        return null;
    }

    @NotNull
    public final List<Name> getORIGINAL_SHORT_NAMES() {
        return ORIGINAL_SHORT_NAMES;
    }

    public final boolean getSameAsRenamedInJvmBuiltin(@NotNull Name sameAsRenamedInJvmBuiltin) {
        Intrinsics.checkParameterIsNotNull(sameAsRenamedInJvmBuiltin, "$this$sameAsRenamedInJvmBuiltin");
        return ORIGINAL_SHORT_NAMES.contains(sameAsRenamedInJvmBuiltin);
    }

    public final boolean isBuiltinFunctionWithDifferentNameInJvm(@NotNull SimpleFunctionDescriptor functionDescriptor) {
        Intrinsics.checkParameterIsNotNull(functionDescriptor, "functionDescriptor");
        return KotlinBuiltIns.isBuiltIn(functionDescriptor) && DescriptorUtilsKt.firstOverridden$default(functionDescriptor, false, new BuiltinMethodsWithDifferentJvmName$isBuiltinFunctionWithDifferentNameInJvm$1(functionDescriptor), 1, null) != null;
    }

    public final boolean isRemoveAtByIndex(@NotNull SimpleFunctionDescriptor isRemoveAtByIndex) {
        Intrinsics.checkParameterIsNotNull(isRemoveAtByIndex, "$this$isRemoveAtByIndex");
        return Intrinsics.areEqual(isRemoveAtByIndex.getName().asString(), "removeAt") && Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmSignature(isRemoveAtByIndex), REMOVE_AT_NAME_AND_SIGNATURE.getSignature());
    }
}
