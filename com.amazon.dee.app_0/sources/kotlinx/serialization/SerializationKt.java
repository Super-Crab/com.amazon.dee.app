package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.dee.app.elements.ElementsRouteKeys;
import java.lang.reflect.Array;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Serialization.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\b\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a1\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007\u001a1\u0010\b\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000b\u001a(\u0010\f\u001a\n\u0012\u0004\u0012\u0002H\u000e\u0018\u00010\r\"\b\b\u0000\u0010\u000e*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0011H\u0002\u001a\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004H\u0000\u001a\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019\u001a$\u0010\u001a\u001a\n\u0012\u0004\u0012\u0002H\u000e\u0018\u00010\r\"\b\b\u0000\u0010\u000e*\u00020\u000f*\b\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0001\u001aM\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u000e\u0018\u00010\r\"\b\b\u0000\u0010\u000e*\u00020\u000f*\b\u0012\u0004\u0012\u0002H\u000e0\u00042\"\u0010\u001c\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\r0\u001d\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\rH\u0001¢\u0006\u0002\u0010\u001e\u001a \u0010\u001f\u001a\u00020\u0006\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0004\u001a+\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00010\u001d\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0004¢\u0006\u0002\u0010!\u001a\u0018\u0010\"\u001a\u00020\u0013*\u00020\u000f2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000\u001a\u001e\u0010$\u001a\u0004\u0018\u00010\u0006\"\b\b\u0000\u0010\u000e*\u00020\u000f*\b\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0000\u001aK\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00010\u001d\"\b\b\u0000\u0010\u000e*\u00020\u000f\"\n\b\u0001\u0010\u0001*\u0004\u0018\u0001H\u000e*\u0012\u0012\u0004\u0012\u0002H\u00010&j\b\u0012\u0004\u0012\u0002H\u0001`'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0000¢\u0006\u0002\u0010)\u001a\n\u0010*\u001a\u00020\u0019*\u00020\u0006¨\u0006+"}, d2 = {"enumFromName", ExifInterface.LONGITUDE_EAST, "", "enumClass", "Lkotlin/reflect/KClass;", "value", "", "(Lkotlin/reflect/KClass;Ljava/lang/String;)Ljava/lang/Enum;", "enumFromOrdinal", "ordinal", "", "(Lkotlin/reflect/KClass;I)Ljava/lang/Enum;", "findObjectSerializer", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "jClass", "Ljava/lang/Class;", "isReferenceArray", "", "type", "Lkotlin/reflect/KType;", "rootClass", "stringFromUtf8Bytes", "bytes", "", "compiledSerializerImpl", "constructSerializerForGivenTypeArgs", ElementsRouteKeys.ARGS, "", "(Lkotlin/reflect/KClass;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "enumClassName", "enumMembers", "(Lkotlin/reflect/KClass;)[Ljava/lang/Enum;", "isInstanceOf", "kclass", "simpleName", "toNativeArrayImpl", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "eClass", "(Ljava/util/ArrayList;Lkotlin/reflect/KClass;)[Ljava/lang/Object;", "toUtf8Bytes", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerializationKt {
    @ImplicitReflectionSerializer
    @Nullable
    public static final <T> KSerializer<T> compiledSerializerImpl(@NotNull KClass<T> compiledSerializerImpl) {
        Intrinsics.checkParameterIsNotNull(compiledSerializerImpl, "$this$compiledSerializerImpl");
        return constructSerializerForGivenTypeArgs(compiledSerializerImpl, new KSerializer[0]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003b, code lost:
        if (r5 == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00e5, code lost:
        if (r0 == false) goto L62;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009c A[LOOP:1: B:18:0x005a->B:35:0x009c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00a0 A[EDGE_INSN: B:77:0x00a0->B:37:0x00a0 ?: BREAK  , SYNTHETIC] */
    @kotlinx.serialization.ImplicitReflectionSerializer
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> kotlinx.serialization.KSerializer<T> constructSerializerForGivenTypeArgs(@org.jetbrains.annotations.NotNull kotlin.reflect.KClass<T> r14, @org.jetbrains.annotations.NotNull kotlinx.serialization.KSerializer<java.lang.Object>... r15) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.SerializationKt.constructSerializerForGivenTypeArgs(kotlin.reflect.KClass, kotlinx.serialization.KSerializer[]):kotlinx.serialization.KSerializer");
    }

    @NotNull
    public static final <E extends Enum<E>> String enumClassName(@NotNull KClass<E> enumClassName) {
        Intrinsics.checkParameterIsNotNull(enumClassName, "$this$enumClassName");
        String canonicalName = JvmClassMappingKt.getJavaClass((KClass) enumClassName).getCanonicalName();
        return canonicalName != null ? canonicalName : "";
    }

    @NotNull
    public static final <E extends Enum<E>> E enumFromName(@NotNull KClass<E> enumClass, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(enumClass, "enumClass");
        Intrinsics.checkParameterIsNotNull(value, "value");
        E e = (E) Enum.valueOf(JvmClassMappingKt.getJavaClass((KClass) enumClass), value);
        Intrinsics.checkExpressionValueIsNotNull(e, "java.lang.Enum.valueOf(enumClass.java, value)");
        return e;
    }

    @NotNull
    public static final <E extends Enum<E>> E enumFromOrdinal(@NotNull KClass<E> enumClass, int i) {
        Intrinsics.checkParameterIsNotNull(enumClass, "enumClass");
        E e = (E) ((Enum[]) JvmClassMappingKt.getJavaClass((KClass) enumClass).getEnumConstants())[i];
        Intrinsics.checkExpressionValueIsNotNull(e, "enumClass.java.enumConstants[ordinal]");
        return e;
    }

    @NotNull
    public static final <E extends Enum<E>> E[] enumMembers(@NotNull KClass<E> enumMembers) {
        Intrinsics.checkParameterIsNotNull(enumMembers, "$this$enumMembers");
        Object[] enumConstants = JvmClassMappingKt.getJavaClass((KClass) enumMembers).getEnumConstants();
        Intrinsics.checkExpressionValueIsNotNull(enumConstants, "this.java.enumConstants");
        return (E[]) ((Enum[]) enumConstants);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
        if (r5 == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0099, code lost:
        if (r5 == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x009b, code lost:
        r6 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0096 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final <T> kotlinx.serialization.KSerializer<T> findObjectSerializer(java.lang.Class<T> r12) {
        /*
            java.lang.reflect.Field[] r0 = r12.getDeclaredFields()
            java.lang.String r1 = "jClass.declaredFields"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            int r1 = r0.length
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r4
            r6 = r3
        Lf:
            java.lang.String r7 = "it"
            r8 = 1
            if (r4 >= r1) goto L46
            r9 = r0[r4]
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r7)
            java.lang.String r10 = r9.getName()
            java.lang.String r11 = "INSTANCE"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r11)
            if (r10 == 0) goto L3b
            java.lang.Class r10 = r9.getType()
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r12)
            if (r10 == 0) goto L3b
            int r10 = r9.getModifiers()
            boolean r10 = java.lang.reflect.Modifier.isStatic(r10)
            if (r10 == 0) goto L3b
            r10 = r8
            goto L3c
        L3b:
            r10 = r2
        L3c:
            if (r10 == 0) goto L43
            if (r5 == 0) goto L41
            goto L48
        L41:
            r5 = r8
            r6 = r9
        L43:
            int r4 = r4 + 1
            goto Lf
        L46:
            if (r5 != 0) goto L49
        L48:
            r6 = r3
        L49:
            if (r6 == 0) goto Lac
            java.lang.Object r0 = r6.get(r3)
            java.lang.reflect.Method[] r12 = r12.getMethods()
            java.lang.String r1 = "jClass.methods"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r1)
            int r1 = r12.length
            r4 = r2
            r5 = r4
            r6 = r3
        L5c:
            if (r4 >= r1) goto L99
            r9 = r12[r4]
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r7)
            java.lang.String r10 = r9.getName()
            java.lang.String r11 = "serializer"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r11)
            if (r10 == 0) goto L8e
            java.lang.reflect.Parameter[] r10 = r9.getParameters()
            java.lang.String r11 = "it.parameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r11)
            int r10 = r10.length
            if (r10 != 0) goto L7d
            r10 = r8
            goto L7e
        L7d:
            r10 = r2
        L7e:
            if (r10 == 0) goto L8e
            java.lang.Class r10 = r9.getReturnType()
            java.lang.Class<kotlinx.serialization.KSerializer> r11 = kotlinx.serialization.KSerializer.class
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r11)
            if (r10 == 0) goto L8e
            r10 = r8
            goto L8f
        L8e:
            r10 = r2
        L8f:
            if (r10 == 0) goto L96
            if (r5 == 0) goto L94
            goto L9b
        L94:
            r5 = r8
            r6 = r9
        L96:
            int r4 = r4 + 1
            goto L5c
        L99:
            if (r5 != 0) goto L9c
        L9b:
            r6 = r3
        L9c:
            if (r6 == 0) goto Lac
            java.lang.Object[] r12 = new java.lang.Object[r2]
            java.lang.Object r12 = r6.invoke(r0, r12)
            boolean r0 = r12 instanceof kotlinx.serialization.KSerializer
            if (r0 != 0) goto La9
            r12 = r3
        La9:
            kotlinx.serialization.KSerializer r12 = (kotlinx.serialization.KSerializer) r12
            return r12
        Lac:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.SerializationKt.findObjectSerializer(java.lang.Class):kotlinx.serialization.KSerializer");
    }

    public static final boolean isInstanceOf(@NotNull Object isInstanceOf, @NotNull KClass<?> kclass) {
        Intrinsics.checkParameterIsNotNull(isInstanceOf, "$this$isInstanceOf");
        Intrinsics.checkParameterIsNotNull(kclass, "kclass");
        return JvmClassMappingKt.getJavaObjectType(kclass).isInstance(isInstanceOf);
    }

    public static final boolean isReferenceArray(@NotNull KType type, @NotNull KClass<Object> rootClass) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(rootClass, "rootClass");
        return JvmClassMappingKt.getJavaClass((KClass) rootClass).isArray();
    }

    @Nullable
    public static final <T> String simpleName(@NotNull KClass<T> simpleName) {
        Intrinsics.checkParameterIsNotNull(simpleName, "$this$simpleName");
        return JvmClassMappingKt.getJavaClass((KClass) simpleName).getSimpleName();
    }

    @NotNull
    public static final String stringFromUtf8Bytes(@NotNull byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        return new String(bytes, Charsets.UTF_8);
    }

    @NotNull
    public static final <T, E extends T> E[] toNativeArrayImpl(@NotNull ArrayList<E> toNativeArrayImpl, @NotNull KClass<T> eClass) {
        Intrinsics.checkParameterIsNotNull(toNativeArrayImpl, "$this$toNativeArrayImpl");
        Intrinsics.checkParameterIsNotNull(eClass, "eClass");
        Object newInstance = Array.newInstance(JvmClassMappingKt.getJavaClass((KClass) eClass), toNativeArrayImpl.size());
        if (newInstance != null) {
            E[] eArr = (E[]) toNativeArrayImpl.toArray((Object[]) newInstance);
            Intrinsics.checkExpressionValueIsNotNull(eArr, "toArray(java.lang.reflec….java, size) as Array<E>)");
            return eArr;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<E>");
    }

    @NotNull
    public static final byte[] toUtf8Bytes(@NotNull String toUtf8Bytes) {
        Intrinsics.checkParameterIsNotNull(toUtf8Bytes, "$this$toUtf8Bytes");
        byte[] bytes = toUtf8Bytes.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }
}
