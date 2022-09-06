package kotlin.reflect.jvm;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;
import org.jetbrains.annotations.NotNull;
/* compiled from: KCallablesJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\",\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"value", "", "isAccessible", "Lkotlin/reflect/KCallable;", "(Lkotlin/reflect/KCallable;)Z", "setAccessible", "(Lkotlin/reflect/KCallable;Z)V", "kotlin-reflection"}, k = 2, mv = {1, 1, 16})
@JvmName(name = "KCallablesJvm")
/* loaded from: classes2.dex */
public final class KCallablesJvm {
    public static final boolean isAccessible(@NotNull KCallable<?> isAccessible) {
        Caller<?> defaultCaller;
        Intrinsics.checkParameterIsNotNull(isAccessible, "$this$isAccessible");
        if (isAccessible instanceof KMutableProperty) {
            KProperty kProperty = (KProperty) isAccessible;
            Field javaField = ReflectJvmMapping.getJavaField(kProperty);
            if (!(javaField != null ? javaField.isAccessible() : true)) {
                return false;
            }
            Method javaGetter = ReflectJvmMapping.getJavaGetter(kProperty);
            if (!(javaGetter != null ? javaGetter.isAccessible() : true)) {
                return false;
            }
            Method javaSetter = ReflectJvmMapping.getJavaSetter((KMutableProperty) isAccessible);
            if (!(javaSetter != null ? javaSetter.isAccessible() : true)) {
                return false;
            }
        } else if (isAccessible instanceof KProperty) {
            KProperty kProperty2 = (KProperty) isAccessible;
            Field javaField2 = ReflectJvmMapping.getJavaField(kProperty2);
            if (!(javaField2 != null ? javaField2.isAccessible() : true)) {
                return false;
            }
            Method javaGetter2 = ReflectJvmMapping.getJavaGetter(kProperty2);
            if (!(javaGetter2 != null ? javaGetter2.isAccessible() : true)) {
                return false;
            }
        } else if (isAccessible instanceof KProperty.Getter) {
            Field javaField3 = ReflectJvmMapping.getJavaField(((KProperty.Getter) isAccessible).mo11473getProperty());
            if (!(javaField3 != null ? javaField3.isAccessible() : true)) {
                return false;
            }
            Method javaMethod = ReflectJvmMapping.getJavaMethod((KFunction) isAccessible);
            if (!(javaMethod != null ? javaMethod.isAccessible() : true)) {
                return false;
            }
        } else if (isAccessible instanceof KMutableProperty.Setter) {
            Field javaField4 = ReflectJvmMapping.getJavaField(((KMutableProperty.Setter) isAccessible).mo11473getProperty());
            if (!(javaField4 != null ? javaField4.isAccessible() : true)) {
                return false;
            }
            Method javaMethod2 = ReflectJvmMapping.getJavaMethod((KFunction) isAccessible);
            if (!(javaMethod2 != null ? javaMethod2.isAccessible() : true)) {
                return false;
            }
        } else if (isAccessible instanceof KFunction) {
            KFunction kFunction = (KFunction) isAccessible;
            Method javaMethod3 = ReflectJvmMapping.getJavaMethod(kFunction);
            if (!(javaMethod3 != null ? javaMethod3.isAccessible() : true)) {
                return false;
            }
            KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(isAccessible);
            Object mo11499getMember = (asKCallableImpl == null || (defaultCaller = asKCallableImpl.getDefaultCaller()) == null) ? null : defaultCaller.mo11499getMember();
            if (!(mo11499getMember instanceof AccessibleObject)) {
                mo11499getMember = null;
            }
            AccessibleObject accessibleObject = (AccessibleObject) mo11499getMember;
            if (!(accessibleObject != null ? accessibleObject.isAccessible() : true)) {
                return false;
            }
            Constructor javaConstructor = ReflectJvmMapping.getJavaConstructor(kFunction);
            if (!(javaConstructor != null ? javaConstructor.isAccessible() : true)) {
                return false;
            }
        } else {
            throw new UnsupportedOperationException("Unknown callable: " + isAccessible + " (" + isAccessible.getClass() + ')');
        }
        return true;
    }

    public static final void setAccessible(@NotNull KCallable<?> isAccessible, boolean z) {
        Caller<?> defaultCaller;
        Intrinsics.checkParameterIsNotNull(isAccessible, "$this$isAccessible");
        if (isAccessible instanceof KMutableProperty) {
            KProperty kProperty = (KProperty) isAccessible;
            Field javaField = ReflectJvmMapping.getJavaField(kProperty);
            if (javaField != null) {
                javaField.setAccessible(z);
            }
            Method javaGetter = ReflectJvmMapping.getJavaGetter(kProperty);
            if (javaGetter != null) {
                javaGetter.setAccessible(z);
            }
            Method javaSetter = ReflectJvmMapping.getJavaSetter((KMutableProperty) isAccessible);
            if (javaSetter == null) {
                return;
            }
            javaSetter.setAccessible(z);
        } else if (isAccessible instanceof KProperty) {
            KProperty kProperty2 = (KProperty) isAccessible;
            Field javaField2 = ReflectJvmMapping.getJavaField(kProperty2);
            if (javaField2 != null) {
                javaField2.setAccessible(z);
            }
            Method javaGetter2 = ReflectJvmMapping.getJavaGetter(kProperty2);
            if (javaGetter2 == null) {
                return;
            }
            javaGetter2.setAccessible(z);
        } else if (isAccessible instanceof KProperty.Getter) {
            Field javaField3 = ReflectJvmMapping.getJavaField(((KProperty.Getter) isAccessible).mo11473getProperty());
            if (javaField3 != null) {
                javaField3.setAccessible(z);
            }
            Method javaMethod = ReflectJvmMapping.getJavaMethod((KFunction) isAccessible);
            if (javaMethod == null) {
                return;
            }
            javaMethod.setAccessible(z);
        } else if (isAccessible instanceof KMutableProperty.Setter) {
            Field javaField4 = ReflectJvmMapping.getJavaField(((KMutableProperty.Setter) isAccessible).mo11473getProperty());
            if (javaField4 != null) {
                javaField4.setAccessible(z);
            }
            Method javaMethod2 = ReflectJvmMapping.getJavaMethod((KFunction) isAccessible);
            if (javaMethod2 == null) {
                return;
            }
            javaMethod2.setAccessible(z);
        } else if (isAccessible instanceof KFunction) {
            KFunction kFunction = (KFunction) isAccessible;
            Method javaMethod3 = ReflectJvmMapping.getJavaMethod(kFunction);
            if (javaMethod3 != null) {
                javaMethod3.setAccessible(z);
            }
            KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(isAccessible);
            Object mo11499getMember = (asKCallableImpl == null || (defaultCaller = asKCallableImpl.getDefaultCaller()) == null) ? null : defaultCaller.mo11499getMember();
            if (!(mo11499getMember instanceof AccessibleObject)) {
                mo11499getMember = null;
            }
            AccessibleObject accessibleObject = (AccessibleObject) mo11499getMember;
            if (accessibleObject != null) {
                accessibleObject.setAccessible(true);
            }
            Constructor javaConstructor = ReflectJvmMapping.getJavaConstructor(kFunction);
            if (javaConstructor == null) {
                return;
            }
            javaConstructor.setAccessible(z);
        } else {
            throw new UnsupportedOperationException("Unknown callable: " + isAccessible + " (" + isAccessible.getClass() + ')');
        }
    }
}
