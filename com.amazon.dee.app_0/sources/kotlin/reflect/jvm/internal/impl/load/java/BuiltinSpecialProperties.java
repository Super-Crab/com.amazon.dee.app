package kotlin.reflect.jvm.internal.impl.load.java;

import com.drew.metadata.mov.QuickTimeAtomTypes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: specialBuiltinMembers.kt */
/* loaded from: classes3.dex */
public final class BuiltinSpecialProperties {
    private static final Map<Name, List<Name>> GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP;
    public static final BuiltinSpecialProperties INSTANCE = new BuiltinSpecialProperties();
    @NotNull
    private static final Map<FqName, Name> PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP;
    private static final Set<FqName> SPECIAL_FQ_NAMES;
    @NotNull
    private static final Set<Name> SPECIAL_SHORT_NAMES;

    static {
        FqName childSafe;
        FqName childSafe2;
        FqName child;
        FqName child2;
        FqName childSafe3;
        FqName child3;
        FqName child4;
        FqName child5;
        Map<FqName, Name> mapOf;
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        Set<Name> set;
        FqNameUnsafe fqNameUnsafe = KotlinBuiltIns.FQ_NAMES._enum;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe, "BUILTIN_NAMES._enum");
        childSafe = SpecialBuiltinMembers.childSafe(fqNameUnsafe, "name");
        FqNameUnsafe fqNameUnsafe2 = KotlinBuiltIns.FQ_NAMES._enum;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe2, "BUILTIN_NAMES._enum");
        childSafe2 = SpecialBuiltinMembers.childSafe(fqNameUnsafe2, "ordinal");
        FqName fqName = KotlinBuiltIns.FQ_NAMES.collection;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "BUILTIN_NAMES.collection");
        child = SpecialBuiltinMembers.child(fqName, "size");
        FqName fqName2 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "BUILTIN_NAMES.map");
        child2 = SpecialBuiltinMembers.child(fqName2, "size");
        FqNameUnsafe fqNameUnsafe3 = KotlinBuiltIns.FQ_NAMES.charSequence;
        Intrinsics.checkExpressionValueIsNotNull(fqNameUnsafe3, "BUILTIN_NAMES.charSequence");
        childSafe3 = SpecialBuiltinMembers.childSafe(fqNameUnsafe3, "length");
        FqName fqName3 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName3, "BUILTIN_NAMES.map");
        child3 = SpecialBuiltinMembers.child(fqName3, QuickTimeAtomTypes.ATOM_KEYS);
        FqName fqName4 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName4, "BUILTIN_NAMES.map");
        child4 = SpecialBuiltinMembers.child(fqName4, "values");
        FqName fqName5 = KotlinBuiltIns.FQ_NAMES.map;
        Intrinsics.checkExpressionValueIsNotNull(fqName5, "BUILTIN_NAMES.map");
        child5 = SpecialBuiltinMembers.child(fqName5, "entries");
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(childSafe, Name.identifier("name")), TuplesKt.to(childSafe2, Name.identifier("ordinal")), TuplesKt.to(child, Name.identifier("size")), TuplesKt.to(child2, Name.identifier("size")), TuplesKt.to(childSafe3, Name.identifier("length")), TuplesKt.to(child3, Name.identifier("keySet")), TuplesKt.to(child4, Name.identifier("values")), TuplesKt.to(child5, Name.identifier("entrySet")));
        PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP = mapOf;
        Set<Map.Entry<FqName, Name>> entrySet = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.entrySet();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(entrySet, 10);
        ArrayList<Pair> arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<T> it2 = entrySet.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            arrayList.add(new Pair(((FqName) entry.getKey()).shortName(), entry.getValue()));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair pair : arrayList) {
            Name name = (Name) pair.getSecond();
            Object obj = linkedHashMap.get(name);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(name, obj);
            }
            ((List) obj).add((Name) pair.getFirst());
        }
        GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP = linkedHashMap;
        SPECIAL_FQ_NAMES = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.keySet();
        Set<FqName> set2 = SPECIAL_FQ_NAMES;
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        for (FqName fqName6 : set2) {
            arrayList2.add(fqName6.shortName());
        }
        set = CollectionsKt___CollectionsKt.toSet(arrayList2);
        SPECIAL_SHORT_NAMES = set;
    }

    private BuiltinSpecialProperties() {
    }

    private final boolean hasBuiltinSpecialPropertyFqNameImpl(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        boolean contains;
        contains = CollectionsKt___CollectionsKt.contains(SPECIAL_FQ_NAMES, DescriptorUtilsKt.fqNameOrNull(callableMemberDescriptor));
        if (!contains || !callableMemberDescriptor.getValueParameters().isEmpty()) {
            if (!KotlinBuiltIns.isBuiltIn(callableMemberDescriptor)) {
                return false;
            }
            Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
            Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "overriddenDescriptors");
            if (overriddenDescriptors.isEmpty()) {
                return false;
            }
            for (CallableMemberDescriptor it2 : overriddenDescriptors) {
                BuiltinSpecialProperties builtinSpecialProperties = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                if (builtinSpecialProperties.hasBuiltinSpecialPropertyFqName(it2)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getBuiltinSpecialPropertyGetterName(@NotNull CallableMemberDescriptor getBuiltinSpecialPropertyGetterName) {
        Name name;
        Intrinsics.checkParameterIsNotNull(getBuiltinSpecialPropertyGetterName, "$this$getBuiltinSpecialPropertyGetterName");
        boolean isBuiltIn = KotlinBuiltIns.isBuiltIn(getBuiltinSpecialPropertyGetterName);
        if (!_Assertions.ENABLED || isBuiltIn) {
            CallableMemberDescriptor firstOverridden$default = DescriptorUtilsKt.firstOverridden$default(DescriptorUtilsKt.getPropertyIfAccessor(getBuiltinSpecialPropertyGetterName), false, BuiltinSpecialProperties$getBuiltinSpecialPropertyGetterName$descriptor$1.INSTANCE, 1, null);
            if (firstOverridden$default != null && (name = PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.get(DescriptorUtilsKt.getFqNameSafe(firstOverridden$default))) != null) {
                return name.asString();
            }
            return null;
        }
        throw new AssertionError("This method is defined only for builtin members, but " + getBuiltinSpecialPropertyGetterName + " found");
    }

    @NotNull
    public final List<Name> getPropertyNameCandidatesBySpecialGetterName(@NotNull Name name1) {
        List<Name> emptyList;
        Intrinsics.checkParameterIsNotNull(name1, "name1");
        List<Name> list = GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP.get(name1);
        if (list != null) {
            return list;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public final Set<Name> getSPECIAL_SHORT_NAMES$descriptors_jvm() {
        return SPECIAL_SHORT_NAMES;
    }

    public final boolean hasBuiltinSpecialPropertyFqName(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableMemberDescriptor, "callableMemberDescriptor");
        if (!SPECIAL_SHORT_NAMES.contains(callableMemberDescriptor.getName())) {
            return false;
        }
        return hasBuiltinSpecialPropertyFqNameImpl(callableMemberDescriptor);
    }
}
