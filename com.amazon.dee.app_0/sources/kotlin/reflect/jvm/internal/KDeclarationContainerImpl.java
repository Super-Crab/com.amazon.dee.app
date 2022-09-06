package kotlin.reflect.jvm.internal;

import com.amazon.alexa.enrollment.dialogs.DialogConstants;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KDeclarationContainerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\n\b \u0018\u0000 ?2\u00020\u0001:\u0003?@AB\u0005¢\u0006\u0002\u0010\u0002J*\u0010\f\u001a\u00020\r2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0014\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0014\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0013J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0011J\u0018\u0010\"\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010#\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0011J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020 0\u00042\u0006\u0010\u001d\u001a\u00020%H&J\u0012\u0010&\u001a\u0004\u0018\u00010\u00172\u0006\u0010'\u001a\u00020(H&J\"\u0010)\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*0\u00042\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0004J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00170\u00042\u0006\u0010\u001d\u001a\u00020%H&J\u001a\u00100\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t012\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0014\u00102\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J$\u00103\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00104\u001a\u00020(2\u0006\u00105\u001a\u00020(H\u0002JE\u00106\u001a\u0004\u0018\u00010\u001c*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u001d\u001a\u00020\u00112\u0010\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t082\n\u00109\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010:\u001a\u00020\u0013H\u0002¢\u0006\u0002\u0010;J(\u0010<\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0019*\u0006\u0012\u0002\b\u00030\t2\u0010\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t01H\u0002J=\u0010=\u001a\u0004\u0018\u00010\u001c*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u001d\u001a\u00020\u00112\u0010\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t082\n\u00109\u001a\u0006\u0012\u0002\b\u00030\tH\u0002¢\u0006\u0002\u0010>R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006B"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "()V", "constructorDescriptors", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "()Ljava/util/Collection;", "methodOwner", "Ljava/lang/Class;", "getMethodOwner", "()Ljava/lang/Class;", "addParametersAndMasks", "", "result", "", DialogConstants.DESC, "", "isConstructor", "", "createProperty", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "findConstructorBySignature", "Ljava/lang/reflect/Constructor;", "findDefaultConstructor", "findDefaultMethod", "Ljava/lang/reflect/Method;", "name", "isMember", "findFunctionDescriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "signature", "findMethodBySignature", "findPropertyDescriptor", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getLocalProperty", "index", "", "getMembers", "Lkotlin/reflect/jvm/internal/KCallableImpl;", AuthorizationResponseParser.SCOPE, "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "belonginess", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess;", "getProperties", "loadParameterTypes", "", "loadReturnType", "parseType", "begin", "end", "lookupMethod", "parameterTypes", "", "returnType", "isStaticDefault", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Ljava/lang/Class;Z)Ljava/lang/reflect/Method;", "tryGetConstructor", "tryGetMethod", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Ljava/lang/Class;)Ljava/lang/reflect/Method;", "Companion", "Data", "MemberBelonginess", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class KDeclarationContainerImpl implements ClassBasedDeclarationContainer {
    public static final Companion Companion = new Companion(null);
    private static final Class<?> DEFAULT_CONSTRUCTOR_MARKER = Class.forName("kotlin.jvm.internal.DefaultConstructorMarker");
    @NotNull
    private static final Regex LOCAL_PROPERTY_SIGNATURE = new Regex("<v#(\\d+)>");

    /* compiled from: KDeclarationContainerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0002\b\u0003 \u0005*\b\u0012\u0002\b\u0003\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Companion;", "", "()V", "DEFAULT_CONSTRUCTOR_MARKER", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "LOCAL_PROPERTY_SIGNATURE", "Lkotlin/text/Regex;", "getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection", "()Lkotlin/text/Regex;", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Regex getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection() {
            return KDeclarationContainerImpl.LOCAL_PROPERTY_SIGNATURE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: KDeclarationContainerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b¦\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data;", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;)V", "moduleData", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeModuleData;", "moduleData$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public abstract class Data {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "moduleData", "getModuleData()Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeModuleData;"))};
        @NotNull
        private final ReflectProperties.LazySoftVal moduleData$delegate = ReflectProperties.lazySoft(new KDeclarationContainerImpl$Data$moduleData$2(this));

        public Data() {
        }

        @NotNull
        public final RuntimeModuleData getModuleData() {
            return (RuntimeModuleData) this.moduleData$delegate.getValue(this, $$delegatedProperties[0]);
        }
    }

    /* compiled from: KDeclarationContainerImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0084\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess;", "", "(Ljava/lang/String;I)V", "accept", "", "member", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "DECLARED", "INHERITED", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    protected enum MemberBelonginess {
        DECLARED,
        INHERITED;

        public final boolean accept(@NotNull CallableMemberDescriptor member) {
            Intrinsics.checkParameterIsNotNull(member, "member");
            CallableMemberDescriptor.Kind kind = member.getKind();
            Intrinsics.checkExpressionValueIsNotNull(kind, "member.kind");
            return kind.isReal() == (this == DECLARED);
        }
    }

    private final void addParametersAndMasks(List<Class<?>> list, String str, boolean z) {
        List<Class<?>> loadParameterTypes = loadParameterTypes(str);
        list.addAll(loadParameterTypes);
        int size = ((loadParameterTypes.size() + 32) - 1) / 32;
        for (int i = 0; i < size; i++) {
            Class<?> cls = Integer.TYPE;
            Intrinsics.checkExpressionValueIsNotNull(cls, "Integer.TYPE");
            list.add(cls);
        }
        Class cls2 = z ? DEFAULT_CONSTRUCTOR_MARKER : Object.class;
        Intrinsics.checkExpressionValueIsNotNull(cls2, "if (isConstructor) DEFAU…RKER else Any::class.java");
        list.add(cls2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KPropertyImpl<?> createProperty(PropertyDescriptor propertyDescriptor) {
        int i = 0;
        int i2 = propertyDescriptor.getDispatchReceiverParameter() != null ? 1 : 0;
        if (propertyDescriptor.getExtensionReceiverParameter() != null) {
            i = 1;
        }
        int i3 = i2 + i;
        if (propertyDescriptor.isVar()) {
            if (i3 == 0) {
                return new KMutableProperty0Impl(this, propertyDescriptor);
            }
            if (i3 == 1) {
                return new KMutableProperty1Impl(this, propertyDescriptor);
            }
            if (i3 == 2) {
                return new KMutableProperty2Impl(this, propertyDescriptor);
            }
        } else if (i3 == 0) {
            return new KProperty0Impl(this, propertyDescriptor);
        } else {
            if (i3 == 1) {
                return new KProperty1Impl(this, propertyDescriptor);
            }
            if (i3 == 2) {
                return new KProperty2Impl(this, propertyDescriptor);
            }
        }
        throw new KotlinReflectionInternalError("Unsupported property: " + propertyDescriptor);
    }

    private final List<Class<?>> loadParameterTypes(String str) {
        boolean contains$default;
        int indexOf$default;
        int i;
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        while (str.charAt(i2) != ')') {
            int i3 = i2;
            while (str.charAt(i3) == '[') {
                i3++;
            }
            char charAt = str.charAt(i3);
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) "VZCBSIFJD", charAt, false, 2, (Object) null);
            if (contains$default) {
                i = i3 + 1;
            } else if (charAt == 'L') {
                indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, ';', i2, false, 4, (Object) null);
                i = indexOf$default + 1;
            } else {
                throw new KotlinReflectionInternalError(GeneratedOutlineSupport1.outline72("Unknown type prefix in the method signature: ", str));
            }
            arrayList.add(parseType(str, i2, i));
            i2 = i;
        }
        return arrayList;
    }

    private final Class<?> loadReturnType(String str) {
        int indexOf$default;
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, ')', 0, false, 6, (Object) null);
        return parseType(str, indexOf$default + 1, str.length());
    }

    private final Method lookupMethod(@NotNull Class<?> cls, String str, Class<?>[] clsArr, Class<?> cls2, boolean z) {
        Class<?>[] interfaces;
        Method lookupMethod;
        if (z) {
            clsArr[0] = cls;
        }
        Method tryGetMethod = tryGetMethod(cls, str, clsArr, cls2);
        if (tryGetMethod != null) {
            return tryGetMethod;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && (lookupMethod = lookupMethod(superclass, str, clsArr, cls2, z)) != null) {
            return lookupMethod;
        }
        for (Class<?> superInterface : cls.getInterfaces()) {
            Intrinsics.checkExpressionValueIsNotNull(superInterface, "superInterface");
            Method lookupMethod2 = lookupMethod(superInterface, str, clsArr, cls2, z);
            if (lookupMethod2 != null) {
                return lookupMethod2;
            }
            if (z) {
                ClassLoader classLoader = superInterface.getClassLoader();
                Intrinsics.checkExpressionValueIsNotNull(classLoader, "superInterface.classLoader");
                Class<?> tryLoadClass = ReflectJavaClassFinderKt.tryLoadClass(classLoader, superInterface.getName() + "$DefaultImpls");
                if (tryLoadClass != null) {
                    clsArr[0] = superInterface;
                    Method tryGetMethod2 = tryGetMethod(tryLoadClass, str, clsArr, cls2);
                    if (tryGetMethod2 != null) {
                        return tryGetMethod2;
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    private final Class<?> parseType(String str, int i, int i2) {
        String replace$default;
        char charAt = str.charAt(i);
        if (charAt != 'F') {
            if (charAt == 'L') {
                ClassLoader safeClassLoader = ReflectClassUtilKt.getSafeClassLoader(getJClass());
                String substring = str.substring(i + 1, i2 - 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                replace$default = StringsKt__StringsJVMKt.replace$default(substring, '/', '.', false, 4, (Object) null);
                Class<?> loadClass = safeClassLoader.loadClass(replace$default);
                Intrinsics.checkExpressionValueIsNotNull(loadClass, "jClass.safeClassLoader.l…d - 1).replace('/', '.'))");
                return loadClass;
            } else if (charAt == 'S') {
                return Short.TYPE;
            } else {
                if (charAt == 'V') {
                    Class<?> cls = Void.TYPE;
                    Intrinsics.checkExpressionValueIsNotNull(cls, "Void.TYPE");
                    return cls;
                } else if (charAt == 'I') {
                    return Integer.TYPE;
                } else {
                    if (charAt == 'J') {
                        return Long.TYPE;
                    }
                    if (charAt == 'Z') {
                        return Boolean.TYPE;
                    }
                    if (charAt != '[') {
                        switch (charAt) {
                            case 'B':
                                return Byte.TYPE;
                            case 'C':
                                return Character.TYPE;
                            case 'D':
                                return Double.TYPE;
                            default:
                                throw new KotlinReflectionInternalError(GeneratedOutlineSupport1.outline72("Unknown type prefix in the method signature: ", str));
                        }
                    }
                    return ReflectClassUtilKt.createArrayType(parseType(str, i + 1, i2));
                }
            }
        }
        return Float.TYPE;
    }

    private final Constructor<?> tryGetConstructor(@NotNull Class<?> cls, List<? extends Class<?>> list) {
        try {
            Object[] array = list.toArray(new Class[0]);
            if (array != null) {
                Class[] clsArr = (Class[]) array;
                return cls.getDeclaredConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length));
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005d A[LOOP:0: B:7:0x002a->B:21:0x005d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.reflect.Method tryGetMethod(@org.jetbrains.annotations.NotNull java.lang.Class<?> r7, java.lang.String r8, java.lang.Class<?>[] r9, java.lang.Class<?> r10) {
        /*
            r6 = this;
            r0 = 0
            int r1 = r9.length     // Catch: java.lang.NoSuchMethodException -> L60
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r9, r1)     // Catch: java.lang.NoSuchMethodException -> L60
            java.lang.Class[] r1 = (java.lang.Class[]) r1     // Catch: java.lang.NoSuchMethodException -> L60
            java.lang.reflect.Method r1 = r7.getDeclaredMethod(r8, r1)     // Catch: java.lang.NoSuchMethodException -> L60
            java.lang.String r2 = "result"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)     // Catch: java.lang.NoSuchMethodException -> L60
            java.lang.Class r2 = r1.getReturnType()     // Catch: java.lang.NoSuchMethodException -> L60
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r10)     // Catch: java.lang.NoSuchMethodException -> L60
            if (r2 == 0) goto L1e
            r0 = r1
            goto L60
        L1e:
            java.lang.reflect.Method[] r7 = r7.getDeclaredMethods()     // Catch: java.lang.NoSuchMethodException -> L60
            java.lang.String r1 = "declaredMethods"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r1)     // Catch: java.lang.NoSuchMethodException -> L60
            int r1 = r7.length     // Catch: java.lang.NoSuchMethodException -> L60
            r2 = 0
            r3 = r2
        L2a:
            if (r3 >= r1) goto L60
            r4 = r7[r3]     // Catch: java.lang.NoSuchMethodException -> L60
            java.lang.String r5 = "method"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L60
            java.lang.String r5 = r4.getName()     // Catch: java.lang.NoSuchMethodException -> L60
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r8)     // Catch: java.lang.NoSuchMethodException -> L60
            if (r5 == 0) goto L58
            java.lang.Class r5 = r4.getReturnType()     // Catch: java.lang.NoSuchMethodException -> L60
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r10)     // Catch: java.lang.NoSuchMethodException -> L60
            if (r5 == 0) goto L58
            java.lang.Class[] r5 = r4.getParameterTypes()     // Catch: java.lang.NoSuchMethodException -> L60
            if (r5 != 0) goto L50
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.NoSuchMethodException -> L60
        L50:
            boolean r5 = java.util.Arrays.equals(r5, r9)     // Catch: java.lang.NoSuchMethodException -> L60
            if (r5 == 0) goto L58
            r5 = 1
            goto L59
        L58:
            r5 = r2
        L59:
            if (r5 == 0) goto L5d
            r0 = r4
            goto L60
        L5d:
            int r3 = r3 + 1
            goto L2a
        L60:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KDeclarationContainerImpl.tryGetMethod(java.lang.Class, java.lang.String, java.lang.Class[], java.lang.Class):java.lang.reflect.Method");
    }

    @Nullable
    public final Constructor<?> findConstructorBySignature(@NotNull String desc) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        return tryGetConstructor(getJClass(), loadParameterTypes(desc));
    }

    @Nullable
    public final Constructor<?> findDefaultConstructor(@NotNull String desc) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        Class<?> jClass = getJClass();
        ArrayList arrayList = new ArrayList();
        addParametersAndMasks(arrayList, desc, true);
        return tryGetConstructor(jClass, arrayList);
    }

    @Nullable
    public final Method findDefaultMethod(@NotNull String name, @NotNull String desc, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        if (Intrinsics.areEqual(name, "<init>")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(getJClass());
        }
        addParametersAndMasks(arrayList, desc, false);
        Class<?> methodOwner = getMethodOwner();
        String outline72 = GeneratedOutlineSupport1.outline72(name, "$default");
        Object[] array = arrayList.toArray(new Class[0]);
        if (array != null) {
            return lookupMethod(methodOwner, outline72, (Class[]) array, loadReturnType(desc), z);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @NotNull
    public final FunctionDescriptor findFunctionDescriptor(@NotNull String name, @NotNull String signature) {
        Collection<FunctionDescriptor> functions;
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signature, "signature");
        if (Intrinsics.areEqual(name, "<init>")) {
            functions = CollectionsKt___CollectionsKt.toList(getConstructorDescriptors());
        } else {
            Name identifier = Name.identifier(name);
            Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(name)");
            functions = getFunctions(identifier);
        }
        Collection<FunctionDescriptor> collection = functions;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (Intrinsics.areEqual(RuntimeTypeMapper.INSTANCE.mapSignature((FunctionDescriptor) obj).asString(), signature)) {
                arrayList.add(obj);
            }
        }
        boolean z = true;
        if (arrayList.size() != 1) {
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(collection, "\n", null, null, 0, null, KDeclarationContainerImpl$findFunctionDescriptor$allMembers$1.INSTANCE, 30, null);
            StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Function '", name, "' (JVM signature: ", signature, ") not resolved in ");
            outline116.append(this);
            outline116.append(JsonReaderKt.COLON);
            if (joinToString$default.length() != 0) {
                z = false;
            }
            outline116.append(z ? " no members found" : '\n' + joinToString$default);
            throw new KotlinReflectionInternalError(outline116.toString());
        }
        return (FunctionDescriptor) CollectionsKt.single((List<? extends Object>) arrayList);
    }

    @Nullable
    public final Method findMethodBySignature(@NotNull String name, @NotNull String desc) {
        Method lookupMethod;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        if (Intrinsics.areEqual(name, "<init>")) {
            return null;
        }
        Object[] array = loadParameterTypes(desc).toArray(new Class[0]);
        if (array != null) {
            Class<?>[] clsArr = (Class[]) array;
            Class<?> loadReturnType = loadReturnType(desc);
            Method lookupMethod2 = lookupMethod(getMethodOwner(), name, clsArr, loadReturnType, false);
            if (lookupMethod2 != null) {
                return lookupMethod2;
            }
            if (getMethodOwner().isInterface() && (lookupMethod = lookupMethod(Object.class, name, clsArr, loadReturnType, false)) != null) {
                return lookupMethod;
            }
            return null;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @NotNull
    public final PropertyDescriptor findPropertyDescriptor(@NotNull String name, @NotNull String signature) {
        SortedMap sortedMap;
        String joinToString$default;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signature, "signature");
        MatchResult matchEntire = LOCAL_PROPERTY_SIGNATURE.matchEntire(signature);
        boolean z = true;
        if (matchEntire != null) {
            String str = matchEntire.getDestructured().getMatch().getGroupValues().get(1);
            PropertyDescriptor localProperty = getLocalProperty(Integer.parseInt(str));
            if (localProperty != null) {
                return localProperty;
            }
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Local property #", str, " not found in ");
            outline115.append(getJClass());
            throw new KotlinReflectionInternalError(outline115.toString());
        }
        Name identifier = Name.identifier(name);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(name)");
        Collection<PropertyDescriptor> properties = getProperties(identifier);
        ArrayList arrayList = new ArrayList();
        for (Object obj : properties) {
            if (Intrinsics.areEqual(RuntimeTypeMapper.INSTANCE.mapPropertySignature((PropertyDescriptor) obj).asString(), signature)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            if (arrayList.size() != 1) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Object obj2 : arrayList) {
                    Visibility visibility = ((PropertyDescriptor) obj2).getVisibility();
                    Object obj3 = linkedHashMap.get(visibility);
                    if (obj3 == null) {
                        obj3 = new ArrayList();
                        linkedHashMap.put(visibility, obj3);
                    }
                    ((List) obj3).add(obj2);
                }
                sortedMap = MapsKt__MapsJVMKt.toSortedMap(linkedHashMap, KDeclarationContainerImpl$findPropertyDescriptor$mostVisibleProperties$2.INSTANCE);
                Collection values = sortedMap.values();
                Intrinsics.checkExpressionValueIsNotNull(values, "properties\n             …                }).values");
                List mostVisibleProperties = (List) CollectionsKt.last(values);
                if (mostVisibleProperties.size() == 1) {
                    Intrinsics.checkExpressionValueIsNotNull(mostVisibleProperties, "mostVisibleProperties");
                    return (PropertyDescriptor) CollectionsKt.first((List<? extends Object>) mostVisibleProperties);
                }
                Name identifier2 = Name.identifier(name);
                Intrinsics.checkExpressionValueIsNotNull(identifier2, "Name.identifier(name)");
                joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(getProperties(identifier2), "\n", null, null, 0, null, KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1.INSTANCE, 30, null);
                StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Property '", name, "' (JVM signature: ", signature, ") not resolved in ");
                outline116.append(this);
                outline116.append(JsonReaderKt.COLON);
                if (joinToString$default.length() != 0) {
                    z = false;
                }
                outline116.append(z ? " no members found" : '\n' + joinToString$default);
                throw new KotlinReflectionInternalError(outline116.toString());
            }
            return (PropertyDescriptor) CollectionsKt.single((List<? extends Object>) arrayList);
        }
        StringBuilder outline1162 = GeneratedOutlineSupport1.outline116("Property '", name, "' (JVM signature: ", signature, ") not resolved in ");
        outline1162.append(this);
        throw new KotlinReflectionInternalError(outline1162.toString());
    }

    @NotNull
    public abstract Collection<ConstructorDescriptor> getConstructorDescriptors();

    @NotNull
    public abstract Collection<FunctionDescriptor> getFunctions(@NotNull Name name);

    @Nullable
    public abstract PropertyDescriptor getLocalProperty(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x001f A[SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Collection<kotlin.reflect.jvm.internal.KCallableImpl<?>> getMembers(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r8, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.KDeclarationContainerImpl.MemberBelonginess r9) {
        /*
            r7 = this;
            java.lang.String r0 = "scope"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = "belonginess"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            kotlin.reflect.jvm.internal.KDeclarationContainerImpl$getMembers$visitor$1 r0 = new kotlin.reflect.jvm.internal.KDeclarationContainerImpl$getMembers$visitor$1
            r0.<init>()
            r1 = 0
            r2 = 3
            java.util.Collection r8 = kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls.getContributedDescriptors$default(r8, r1, r1, r2, r1)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r8 = r8.iterator()
        L1f:
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto L56
            java.lang.Object r3 = r8.next()
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r3 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r3
            boolean r4 = r3 instanceof kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
            if (r4 == 0) goto L4f
            r4 = r3
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r4
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r5 = r4.getVisibility()
            kotlin.reflect.jvm.internal.impl.descriptors.Visibility r6 = kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.INVISIBLE_FAKE
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
            r5 = r5 ^ 1
            if (r5 == 0) goto L4f
            boolean r4 = r9.accept(r4)
            if (r4 == 0) goto L4f
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            java.lang.Object r3 = r3.accept(r0, r4)
            kotlin.reflect.jvm.internal.KCallableImpl r3 = (kotlin.reflect.jvm.internal.KCallableImpl) r3
            goto L50
        L4f:
            r3 = r1
        L50:
            if (r3 == 0) goto L1f
            r2.add(r3)
            goto L1f
        L56:
            java.util.List r8 = kotlin.collections.CollectionsKt.toList(r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KDeclarationContainerImpl.getMembers(kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.KDeclarationContainerImpl$MemberBelonginess):java.util.Collection");
    }

    @NotNull
    protected Class<?> getMethodOwner() {
        Class<?> wrapperByPrimitive = ReflectClassUtilKt.getWrapperByPrimitive(getJClass());
        return wrapperByPrimitive != null ? wrapperByPrimitive : getJClass();
    }

    @NotNull
    public abstract Collection<PropertyDescriptor> getProperties(@NotNull Name name);
}
