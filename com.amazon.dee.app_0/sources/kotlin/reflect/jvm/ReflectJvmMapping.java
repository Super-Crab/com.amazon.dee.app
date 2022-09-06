package kotlin.reflect.jvm;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KPackageImpl;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJvmMapping.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010%\u001a\u0004\u0018\u00010&*\u00020'H\u0002\"/\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u001b\u0010\b\u001a\u0004\u0018\u00010\t*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0015\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"-\u0010\u001d\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001e*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u001b\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010!\"\u001b\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n*\u00020\t8F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006("}, d2 = {"javaConstructor", "Ljava/lang/reflect/Constructor;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/reflect/KFunction;", "javaConstructor$annotations", "(Lkotlin/reflect/KFunction;)V", "getJavaConstructor", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Constructor;", "javaField", "Ljava/lang/reflect/Field;", "Lkotlin/reflect/KProperty;", "getJavaField", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Field;", "javaGetter", "Ljava/lang/reflect/Method;", "getJavaGetter", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Method;", "javaMethod", "getJavaMethod", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Method;", "javaSetter", "Lkotlin/reflect/KMutableProperty;", "getJavaSetter", "(Lkotlin/reflect/KMutableProperty;)Ljava/lang/reflect/Method;", "javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "kotlinFunction", "", "getKotlinFunction", "(Ljava/lang/reflect/Constructor;)Lkotlin/reflect/KFunction;", "(Ljava/lang/reflect/Method;)Lkotlin/reflect/KFunction;", "kotlinProperty", "getKotlinProperty", "(Ljava/lang/reflect/Field;)Lkotlin/reflect/KProperty;", "getKPackage", "Lkotlin/reflect/KDeclarationContainer;", "Ljava/lang/reflect/Member;", "kotlin-reflection"}, k = 2, mv = {1, 1, 16})
@JvmName(name = "ReflectJvmMapping")
/* loaded from: classes2.dex */
public final class ReflectJvmMapping {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[KotlinClassHeader.Kind.values().length];

        static {
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 1;
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.MULTIFILE_CLASS.ordinal()] = 2;
            $EnumSwitchMapping$0[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 3;
        }
    }

    @Nullable
    public static final <T> Constructor<T> getJavaConstructor(@NotNull KFunction<? extends T> javaConstructor) {
        Caller<?> caller;
        Intrinsics.checkParameterIsNotNull(javaConstructor, "$this$javaConstructor");
        KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(javaConstructor);
        Object mo11499getMember = (asKCallableImpl == null || (caller = asKCallableImpl.getCaller()) == null) ? null : caller.mo11499getMember();
        if (!(mo11499getMember instanceof Constructor)) {
            mo11499getMember = null;
        }
        return (Constructor) mo11499getMember;
    }

    @Nullable
    public static final Field getJavaField(@NotNull KProperty<?> javaField) {
        Intrinsics.checkParameterIsNotNull(javaField, "$this$javaField");
        KPropertyImpl<?> asKPropertyImpl = UtilKt.asKPropertyImpl(javaField);
        if (asKPropertyImpl != null) {
            return asKPropertyImpl.getJavaField();
        }
        return null;
    }

    @Nullable
    public static final Method getJavaGetter(@NotNull KProperty<?> javaGetter) {
        Intrinsics.checkParameterIsNotNull(javaGetter, "$this$javaGetter");
        return getJavaMethod(javaGetter.mo11472getGetter());
    }

    @Nullable
    public static final Method getJavaMethod(@NotNull KFunction<?> javaMethod) {
        Caller<?> caller;
        Intrinsics.checkParameterIsNotNull(javaMethod, "$this$javaMethod");
        KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(javaMethod);
        Object mo11499getMember = (asKCallableImpl == null || (caller = asKCallableImpl.getCaller()) == null) ? null : caller.mo11499getMember();
        if (!(mo11499getMember instanceof Method)) {
            mo11499getMember = null;
        }
        return (Method) mo11499getMember;
    }

    @Nullable
    public static final Method getJavaSetter(@NotNull KMutableProperty<?> javaSetter) {
        Intrinsics.checkParameterIsNotNull(javaSetter, "$this$javaSetter");
        return getJavaMethod(javaSetter.mo11451getSetter());
    }

    @NotNull
    public static final Type getJavaType(@NotNull KType javaType) {
        Intrinsics.checkParameterIsNotNull(javaType, "$this$javaType");
        return ((KTypeImpl) javaType).getJavaType$kotlin_reflection();
    }

    private static final KDeclarationContainer getKPackage(@NotNull Member member) {
        int i;
        KotlinClassHeader classHeader;
        ReflectKotlinClass.Factory factory = ReflectKotlinClass.Factory;
        Class<?> declaringClass = member.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
        ReflectKotlinClass create = factory.create(declaringClass);
        KotlinClassHeader.Kind kind = (create == null || (classHeader = create.getClassHeader()) == null) ? null : classHeader.getKind();
        if (kind != null && ((i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) == 1 || i == 2 || i == 3)) {
            Class<?> declaringClass2 = member.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(declaringClass2, "declaringClass");
            return new KPackageImpl(declaringClass2, null, 2, null);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00bf A[EDGE_INSN: B:62:0x00bf->B:42:0x00bf ?: BREAK  , SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.reflect.KFunction<?> getKotlinFunction(@org.jetbrains.annotations.NotNull java.lang.reflect.Method r8) {
        /*
            java.lang.String r0 = "$this$kotlinFunction"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            int r0 = r8.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            java.lang.String r1 = "declaringClass"
            r2 = 0
            if (r0 == 0) goto Lc4
            kotlin.reflect.KDeclarationContainer r0 = getKPackage(r8)
            if (r0 == 0) goto L57
            java.util.Collection r0 = r0.getMembers()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L25:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L37
            java.lang.Object r3 = r0.next()
            boolean r4 = r3 instanceof kotlin.reflect.KFunction
            if (r4 == 0) goto L25
            r1.add(r3)
            goto L25
        L37:
            java.util.Iterator r0 = r1.iterator()
        L3b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L53
            java.lang.Object r1 = r0.next()
            r3 = r1
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            java.lang.reflect.Method r3 = getJavaMethod(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r8)
            if (r3 == 0) goto L3b
            goto L54
        L53:
            r1 = r2
        L54:
            kotlin.reflect.KFunction r1 = (kotlin.reflect.KFunction) r1
            return r1
        L57:
            java.lang.Class r0 = r8.getDeclaringClass()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.KClass r0 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r0)
            kotlin.reflect.KClass r0 = kotlin.reflect.full.KClasses.getCompanionObject(r0)
            if (r0 == 0) goto Lc4
            java.util.Collection r0 = kotlin.reflect.full.KClasses.getFunctions(r0)
            java.util.Iterator r0 = r0.iterator()
        L70:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto Lbe
            java.lang.Object r3 = r0.next()
            r4 = r3
            kotlin.reflect.KFunction r4 = (kotlin.reflect.KFunction) r4
            java.lang.reflect.Method r4 = getJavaMethod(r4)
            if (r4 == 0) goto Lba
            java.lang.String r5 = r4.getName()
            java.lang.String r6 = r8.getName()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            if (r5 == 0) goto Lba
            java.lang.Class[] r5 = r4.getParameterTypes()
            if (r5 != 0) goto L9a
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L9a:
            java.lang.Class[] r6 = r8.getParameterTypes()
            java.lang.String r7 = "this.parameterTypes"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            boolean r5 = java.util.Arrays.equals(r5, r6)
            if (r5 == 0) goto Lba
            java.lang.Class r4 = r4.getReturnType()
            java.lang.Class r5 = r8.getReturnType()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 == 0) goto Lba
            r4 = 1
            goto Lbb
        Lba:
            r4 = 0
        Lbb:
            if (r4 == 0) goto L70
            goto Lbf
        Lbe:
            r3 = r2
        Lbf:
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            if (r3 == 0) goto Lc4
            return r3
        Lc4:
            java.lang.Class r0 = r8.getDeclaringClass()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            kotlin.reflect.KClass r0 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r0)
            java.util.Collection r0 = kotlin.reflect.full.KClasses.getFunctions(r0)
            java.util.Iterator r0 = r0.iterator()
        Ld7:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lef
            java.lang.Object r1 = r0.next()
            r3 = r1
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            java.lang.reflect.Method r3 = getJavaMethod(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r8)
            if (r3 == 0) goto Ld7
            goto Lf0
        Lef:
            r1 = r2
        Lf0:
            kotlin.reflect.KFunction r1 = (kotlin.reflect.KFunction) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.ReflectJvmMapping.getKotlinFunction(java.lang.reflect.Method):kotlin.reflect.KFunction");
    }

    @Nullable
    public static final KProperty<?> getKotlinProperty(@NotNull Field kotlinProperty) {
        Intrinsics.checkParameterIsNotNull(kotlinProperty, "$this$kotlinProperty");
        Object obj = null;
        if (kotlinProperty.isSynthetic()) {
            return null;
        }
        KDeclarationContainer kPackage = getKPackage(kotlinProperty);
        if (kPackage != null) {
            Collection<KCallable<?>> members = kPackage.getMembers();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : members) {
                if (obj2 instanceof KProperty) {
                    arrayList.add(obj2);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                if (Intrinsics.areEqual(getJavaField((KProperty) next), kotlinProperty)) {
                    obj = next;
                    break;
                }
            }
            return (KProperty) obj;
        }
        Class<?> declaringClass = kotlinProperty.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
        Iterator it3 = KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(declaringClass)).iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Object next2 = it3.next();
            if (Intrinsics.areEqual(getJavaField((KProperty1) next2), kotlinProperty)) {
                obj = next2;
                break;
            }
        }
        return (KProperty) obj;
    }

    public static /* synthetic */ void javaConstructor$annotations(KFunction kFunction) {
    }

    @Nullable
    public static final <T> KFunction<T> getKotlinFunction(@NotNull Constructor<T> kotlinFunction) {
        T t;
        Intrinsics.checkParameterIsNotNull(kotlinFunction, "$this$kotlinFunction");
        Class<T> declaringClass = kotlinFunction.getDeclaringClass();
        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "declaringClass");
        Iterator<T> it2 = JvmClassMappingKt.getKotlinClass(declaringClass).getConstructors().iterator();
        while (true) {
            if (!it2.hasNext()) {
                t = null;
                break;
            }
            t = it2.next();
            if (Intrinsics.areEqual(getJavaConstructor((KFunction) t), kotlinFunction)) {
                break;
            }
        }
        return (KFunction) t;
    }
}
