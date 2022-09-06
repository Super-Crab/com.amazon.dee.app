package kotlinx.serialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.builtins.PrimitiveSerializersKt;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.HashMapSerializer;
import kotlinx.serialization.internal.HashSetSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SerializerResolving.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"serializerByKTypeImpl", "Lkotlinx/serialization/KSerializer;", "", "type", "Lkotlin/reflect/KType;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerializerResolvingKt$serializer$1 extends Lambda implements Function1<KType, KSerializer<Object>> {
    public static final SerializerResolvingKt$serializer$1 INSTANCE = new SerializerResolvingKt$serializer$1();

    SerializerResolvingKt$serializer$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final KSerializer<Object> mo12165invoke(@NotNull KType type) {
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        KSerializer<Object> arrayListSerializer;
        Intrinsics.checkParameterIsNotNull(type, "type");
        KClassifier classifier = type.getClassifier();
        if (classifier instanceof KClass) {
            KClass kClass = (KClass) classifier;
            List<KTypeProjection> arguments = type.getArguments();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10);
            ArrayList<KType> arrayList = new ArrayList(collectionSizeOrDefault);
            for (KTypeProjection kTypeProjection : arguments) {
                KType type2 = kTypeProjection.getType();
                if (type2 == null) {
                    throw new IllegalArgumentException(("Star projections are not allowed, had " + kTypeProjection + " instead").toString());
                }
                arrayList.add(type2);
            }
            if (arrayList.isEmpty()) {
                arrayListSerializer = PlatformUtilsKt.serializer(kClass);
            } else {
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
                for (KType kType : arrayList) {
                    arrayList2.add(SerializerResolvingKt.serializer(kType));
                }
                if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(List.class)) || Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(List.class)) || Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(ArrayList.class))) {
                    arrayListSerializer = new ArrayListSerializer<>((KSerializer) arrayList2.get(0));
                } else if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(HashSet.class))) {
                    arrayListSerializer = new HashSetSerializer<>((KSerializer) arrayList2.get(0));
                } else if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Set.class)) || Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Set.class)) || Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(LinkedHashSet.class))) {
                    arrayListSerializer = new LinkedHashSetSerializer<>((KSerializer) arrayList2.get(0));
                } else if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(HashMap.class))) {
                    arrayListSerializer = new HashMapSerializer<>((KSerializer) arrayList2.get(0), (KSerializer) arrayList2.get(1));
                } else if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Map.class)) || Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Map.class)) || Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(LinkedHashMap.class))) {
                    arrayListSerializer = new LinkedHashMapSerializer<>((KSerializer) arrayList2.get(0), (KSerializer) arrayList2.get(1));
                } else if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Map.Entry.class))) {
                    arrayListSerializer = BuiltinSerializersKt.MapEntrySerializer((KSerializer) arrayList2.get(0), (KSerializer) arrayList2.get(1));
                } else if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Pair.class))) {
                    arrayListSerializer = BuiltinSerializersKt.PairSerializer((KSerializer) arrayList2.get(0), (KSerializer) arrayList2.get(1));
                } else if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Triple.class))) {
                    arrayListSerializer = BuiltinSerializersKt.TripleSerializer((KSerializer) arrayList2.get(0), (KSerializer) arrayList2.get(1), (KSerializer) arrayList2.get(2));
                } else if (SerializationKt.isReferenceArray(type, kClass)) {
                    KClassifier classifier2 = ((KType) arrayList.get(0)).getClassifier();
                    if (classifier2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
                    }
                    KSerializer<Object> ArraySerializer = PrimitiveSerializersKt.ArraySerializer((KClass) classifier2, (KSerializer) arrayList2.get(0));
                    if (ArraySerializer == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
                    }
                    return ArraySerializer;
                } else {
                    Object[] array = arrayList2.toArray(new KSerializer[0]);
                    if (array != null) {
                        KSerializer[] kSerializerArr = (KSerializer[]) array;
                        arrayListSerializer = SerializationKt.constructSerializerForGivenTypeArgs(kClass, (KSerializer[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
                        if (arrayListSerializer == null) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can't find a method to construct serializer for type ");
                            outline107.append(SerializationKt.simpleName(kClass));
                            outline107.append(". ");
                            outline107.append("Make sure this class is marked as @Serializable or provide serializer explicitly.");
                            throw new IllegalArgumentException(outline107.toString().toString());
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            }
            if (arrayListSerializer == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.KSerializer<T>");
            }
            return arrayListSerializer;
        }
        throw new IllegalStateException(("Only KClass supported as classifier, got " + classifier).toString());
    }
}
