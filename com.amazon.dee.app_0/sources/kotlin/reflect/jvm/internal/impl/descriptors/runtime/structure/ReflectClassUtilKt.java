package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: reflectClassUtil.kt */
/* loaded from: classes2.dex */
public final class ReflectClassUtilKt {
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;
    private static final List<KClass<? extends Object>> PRIMITIVE_CLASSES;
    private static final Map<Class<? extends Object>, Class<? extends Object>> PRIMITIVE_TO_WRAPPER;
    private static final Map<Class<? extends Object>, Class<? extends Object>> WRAPPER_TO_PRIMITIVE;

    static {
        List<KClass<? extends Object>> listOf;
        int collectionSizeOrDefault;
        Map<Class<? extends Object>, Class<? extends Object>> map;
        int collectionSizeOrDefault2;
        Map<Class<? extends Object>, Class<? extends Object>> map2;
        List listOf2;
        int collectionSizeOrDefault3;
        Map<Class<? extends Function<?>>, Integer> map3;
        int i = 0;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new KClass[]{Reflection.getOrCreateKotlinClass(Boolean.TYPE), Reflection.getOrCreateKotlinClass(Byte.TYPE), Reflection.getOrCreateKotlinClass(Character.TYPE), Reflection.getOrCreateKotlinClass(Double.TYPE), Reflection.getOrCreateKotlinClass(Float.TYPE), Reflection.getOrCreateKotlinClass(Integer.TYPE), Reflection.getOrCreateKotlinClass(Long.TYPE), Reflection.getOrCreateKotlinClass(Short.TYPE)});
        PRIMITIVE_CLASSES = listOf;
        List<KClass<? extends Object>> list = PRIMITIVE_CLASSES;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            KClass kClass = (KClass) it2.next();
            arrayList.add(TuplesKt.to(JvmClassMappingKt.getJavaObjectType(kClass), JvmClassMappingKt.getJavaPrimitiveType(kClass)));
        }
        map = MapsKt__MapsKt.toMap(arrayList);
        WRAPPER_TO_PRIMITIVE = map;
        List<KClass<? extends Object>> list2 = PRIMITIVE_CLASSES;
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        Iterator<T> it3 = list2.iterator();
        while (it3.hasNext()) {
            KClass kClass2 = (KClass) it3.next();
            arrayList2.add(TuplesKt.to(JvmClassMappingKt.getJavaPrimitiveType(kClass2), JvmClassMappingKt.getJavaObjectType(kClass2)));
        }
        map2 = MapsKt__MapsKt.toMap(arrayList2);
        PRIMITIVE_TO_WRAPPER = map2;
        listOf2 = CollectionsKt__CollectionsKt.listOf((Object[]) new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        collectionSizeOrDefault3 = CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf2, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault3);
        for (Object obj : listOf2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            arrayList3.add(TuplesKt.to((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        map3 = MapsKt__MapsKt.toMap(arrayList3);
        FUNCTION_CLASSES = map3;
    }

    @NotNull
    public static final Class<?> createArrayType(@NotNull Class<?> createArrayType) {
        Intrinsics.checkParameterIsNotNull(createArrayType, "$this$createArrayType");
        return Array.newInstance(createArrayType, 0).getClass();
    }

    @NotNull
    public static final ClassId getClassId(@NotNull Class<?> classId) {
        ClassId classId2;
        ClassId createNestedClassId;
        Intrinsics.checkParameterIsNotNull(classId, "$this$classId");
        if (!classId.isPrimitive()) {
            if (!classId.isArray()) {
                if (classId.getEnclosingMethod() == null && classId.getEnclosingConstructor() == null) {
                    String simpleName = classId.getSimpleName();
                    Intrinsics.checkExpressionValueIsNotNull(simpleName, "simpleName");
                    if (!(simpleName.length() == 0)) {
                        Class<?> declaringClass = classId.getDeclaringClass();
                        if (declaringClass != null && (classId2 = getClassId(declaringClass)) != null && (createNestedClassId = classId2.createNestedClassId(Name.identifier(classId.getSimpleName()))) != null) {
                            return createNestedClassId;
                        }
                        ClassId classId3 = ClassId.topLevel(new FqName(classId.getName()));
                        Intrinsics.checkExpressionValueIsNotNull(classId3, "ClassId.topLevel(FqName(name))");
                        return classId3;
                    }
                }
                FqName fqName = new FqName(classId.getName());
                return new ClassId(fqName.parent(), FqName.topLevel(fqName.shortName()), true);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline66("Can't compute ClassId for array type: ", classId));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline66("Can't compute ClassId for primitive type: ", classId));
    }

    @NotNull
    public static final String getDesc(@NotNull Class<?> desc) {
        String replace$default;
        Intrinsics.checkParameterIsNotNull(desc, "$this$desc");
        if (Intrinsics.areEqual(desc, Void.TYPE)) {
            return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
        }
        String name = createArrayType(desc).getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "createArrayType().name");
        String substring = name.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        replace$default = StringsKt__StringsJVMKt.replace$default(substring, '.', '/', false, 4, (Object) null);
        return replace$default;
    }

    @Nullable
    public static final Integer getFunctionClassArity(@NotNull Class<?> functionClassArity) {
        Intrinsics.checkParameterIsNotNull(functionClassArity, "$this$functionClassArity");
        return FUNCTION_CLASSES.get(functionClassArity);
    }

    @NotNull
    public static final List<Type> getParameterizedTypeArguments(@NotNull Type parameterizedTypeArguments) {
        Sequence generateSequence;
        Sequence flatMap;
        List<Type> list;
        List<Type> list2;
        List<Type> emptyList;
        Intrinsics.checkParameterIsNotNull(parameterizedTypeArguments, "$this$parameterizedTypeArguments");
        if (!(parameterizedTypeArguments instanceof ParameterizedType)) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        ParameterizedType parameterizedType = (ParameterizedType) parameterizedTypeArguments;
        if (parameterizedType.getOwnerType() == null) {
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Intrinsics.checkExpressionValueIsNotNull(actualTypeArguments, "actualTypeArguments");
            list2 = ArraysKt___ArraysKt.toList(actualTypeArguments);
            return list2;
        }
        generateSequence = SequencesKt__SequencesKt.generateSequence(parameterizedTypeArguments, ReflectClassUtilKt$parameterizedTypeArguments$1.INSTANCE);
        flatMap = SequencesKt___SequencesKt.flatMap(generateSequence, ReflectClassUtilKt$parameterizedTypeArguments$2.INSTANCE);
        list = SequencesKt___SequencesKt.toList(flatMap);
        return list;
    }

    @Nullable
    public static final Class<?> getPrimitiveByWrapper(@NotNull Class<?> primitiveByWrapper) {
        Intrinsics.checkParameterIsNotNull(primitiveByWrapper, "$this$primitiveByWrapper");
        return WRAPPER_TO_PRIMITIVE.get(primitiveByWrapper);
    }

    @NotNull
    public static final ClassLoader getSafeClassLoader(@NotNull Class<?> safeClassLoader) {
        Intrinsics.checkParameterIsNotNull(safeClassLoader, "$this$safeClassLoader");
        ClassLoader classLoader = safeClassLoader.getClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Intrinsics.checkExpressionValueIsNotNull(systemClassLoader, "ClassLoader.getSystemClassLoader()");
        return systemClassLoader;
    }

    @Nullable
    public static final Class<?> getWrapperByPrimitive(@NotNull Class<?> wrapperByPrimitive) {
        Intrinsics.checkParameterIsNotNull(wrapperByPrimitive, "$this$wrapperByPrimitive");
        return PRIMITIVE_TO_WRAPPER.get(wrapperByPrimitive);
    }

    public static final boolean isEnumClassOrSpecializedEnumEntryClass(@NotNull Class<?> isEnumClassOrSpecializedEnumEntryClass) {
        Intrinsics.checkParameterIsNotNull(isEnumClassOrSpecializedEnumEntryClass, "$this$isEnumClassOrSpecializedEnumEntryClass");
        return Enum.class.isAssignableFrom(isEnumClassOrSpecializedEnumEntryClass);
    }
}
