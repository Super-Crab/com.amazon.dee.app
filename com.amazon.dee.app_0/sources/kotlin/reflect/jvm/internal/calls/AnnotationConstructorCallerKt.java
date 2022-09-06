package kotlin.reflect.jvm.internal.calls;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.tarazed.core.types.annotations.AnnotationEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AnnotationConstructorCaller.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001aI\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\u0010\u000b\u001a$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u0014"}, d2 = {"createAnnotationInstance", ExifInterface.GPS_DIRECTION_TRUE, "", "annotationClass", "Ljava/lang/Class;", "values", "", "", "methods", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/Map;Ljava/util/List;)Ljava/lang/Object;", "throwIllegalArgumentType", "", "index", "", "name", "expectedJvmType", "transformKotlinToJvm", "expectedType", "kotlin-reflection"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AnnotationConstructorCallerKt {
    @NotNull
    public static final <T> T createAnnotationInstance(@NotNull final Class<T> annotationClass, @NotNull final Map<String, ? extends Object> values, @NotNull List<Method> methods) {
        final Lazy lazy;
        final Lazy lazy2;
        Intrinsics.checkParameterIsNotNull(annotationClass, "annotationClass");
        Intrinsics.checkParameterIsNotNull(values, "values");
        Intrinsics.checkParameterIsNotNull(methods, "methods");
        final AnnotationConstructorCallerKt$createAnnotationInstance$2 annotationConstructorCallerKt$createAnnotationInstance$2 = new AnnotationConstructorCallerKt$createAnnotationInstance$2(annotationClass, methods, values);
        lazy = LazyKt__LazyJVMKt.lazy(new AnnotationConstructorCallerKt$createAnnotationInstance$hashCode$2(values));
        lazy2 = LazyKt__LazyJVMKt.lazy(new AnnotationConstructorCallerKt$createAnnotationInstance$toString$2(annotationClass, values));
        T t = (T) Proxy.newProxyInstance(annotationClass.getClassLoader(), new Class[]{annotationClass}, new InvocationHandler() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$createAnnotationInstance$result$1
            @Override // java.lang.reflect.InvocationHandler
            @Nullable
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                List list;
                Intrinsics.checkExpressionValueIsNotNull(method, "method");
                String name = method.getName();
                if (name != null) {
                    int hashCode = name.hashCode();
                    if (hashCode != -1776922004) {
                        if (hashCode != 147696667) {
                            if (hashCode == 1444986633 && name.equals(AnnotationEvent.KEY_ANNOTATION_TYPE)) {
                                return annotationClass;
                            }
                        } else if (name.equals("hashCode")) {
                            return lazy.getValue();
                        }
                    } else if (name.equals("toString")) {
                        return lazy2.getValue();
                    }
                }
                if (!Intrinsics.areEqual(name, "equals") || objArr == null || objArr.length != 1) {
                    if (values.containsKey(name)) {
                        return values.get(name);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Method is not supported: ");
                    sb.append(method);
                    sb.append(" (args: ");
                    if (objArr == null) {
                        objArr = new Object[0];
                    }
                    list = ArraysKt___ArraysKt.toList(objArr);
                    sb.append(list);
                    sb.append(')');
                    throw new KotlinReflectionInternalError(sb.toString());
                }
                return Boolean.valueOf(annotationConstructorCallerKt$createAnnotationInstance$2.mo12165invoke(ArraysKt.single(objArr)));
            }
        });
        if (t != null) {
            return t;
        }
        throw new TypeCastException("null cannot be cast to non-null type T");
    }

    public static /* synthetic */ Object createAnnotationInstance$default(Class cls, Map map, List list, int i, Object obj) {
        int collectionSizeOrDefault;
        if ((i & 4) != 0) {
            Set<String> keySet = map.keySet();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(keySet, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (String str : keySet) {
                arrayList.add(cls.getDeclaredMethod(str, new Class[0]));
            }
            list = arrayList;
        }
        return createAnnotationInstance(cls, map, list);
    }

    public static final Void throwIllegalArgumentType(int i, String str, Class<?> cls) {
        KClass kotlinClass;
        String qualifiedName;
        if (Intrinsics.areEqual(cls, Class.class)) {
            kotlinClass = Reflection.getOrCreateKotlinClass(KClass.class);
        } else if (cls.isArray() && Intrinsics.areEqual(cls.getComponentType(), Class.class)) {
            kotlinClass = Reflection.getOrCreateKotlinClass(KClass[].class);
        } else {
            kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
        }
        if (Intrinsics.areEqual(kotlinClass.getQualifiedName(), Reflection.getOrCreateKotlinClass(Object[].class).getQualifiedName())) {
            StringBuilder sb = new StringBuilder();
            sb.append(kotlinClass.getQualifiedName());
            sb.append(Typography.less);
            Class<?> componentType = JvmClassMappingKt.getJavaClass(kotlinClass).getComponentType();
            Intrinsics.checkExpressionValueIsNotNull(componentType, "kotlinClass.java.componentType");
            sb.append(JvmClassMappingKt.getKotlinClass(componentType).getQualifiedName());
            sb.append(Typography.greater);
            qualifiedName = sb.toString();
        } else {
            qualifiedName = kotlinClass.getQualifiedName();
        }
        throw new IllegalArgumentException("Argument #" + i + Chars.SPACE + str + " is not of the required type " + qualifiedName);
    }

    public static final Object transformKotlinToJvm(@Nullable Object obj, Class<?> cls) {
        if (obj instanceof Class) {
            return null;
        }
        if (obj instanceof KClass) {
            obj = JvmClassMappingKt.getJavaClass((KClass) obj);
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr instanceof Class[]) {
                return null;
            }
            if (!(objArr instanceof KClass[])) {
                obj = objArr;
            } else if (obj != null) {
                KClass[] kClassArr = (KClass[]) obj;
                ArrayList arrayList = new ArrayList(kClassArr.length);
                for (KClass kClass : kClassArr) {
                    arrayList.add(JvmClassMappingKt.getJavaClass(kClass));
                }
                obj = arrayList.toArray(new Class[0]);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
            }
        }
        if (!cls.isInstance(obj)) {
            return null;
        }
        return obj;
    }
}
