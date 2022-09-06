package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaMember.kt */
/* loaded from: classes2.dex */
final class Java8ParameterNamesLoader {
    public static final Java8ParameterNamesLoader INSTANCE = new Java8ParameterNamesLoader();
    @Nullable
    private static Cache cache;

    /* compiled from: ReflectJavaMember.kt */
    /* loaded from: classes2.dex */
    public static final class Cache {
        @Nullable
        private final Method getName;
        @Nullable
        private final Method getParameters;

        public Cache(@Nullable Method method, @Nullable Method method2) {
            this.getParameters = method;
            this.getName = method2;
        }

        @Nullable
        public final Method getGetName() {
            return this.getName;
        }

        @Nullable
        public final Method getGetParameters() {
            return this.getParameters;
        }
    }

    private Java8ParameterNamesLoader() {
    }

    @NotNull
    public final Cache buildCache(@NotNull Member member) {
        Intrinsics.checkParameterIsNotNull(member, "member");
        Class<?> cls = member.getClass();
        try {
            return new Cache(cls.getMethod("getParameters", new Class[0]), ReflectClassUtilKt.getSafeClassLoader(cls).loadClass("java.lang.reflect.Parameter").getMethod("getName", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return new Cache(null, null);
        }
    }

    @Nullable
    public final List<String> loadParameterNames(@NotNull Member member) {
        Method getName;
        Intrinsics.checkParameterIsNotNull(member, "member");
        Cache cache2 = cache;
        if (cache2 == null) {
            cache2 = buildCache(member);
            cache = cache2;
        }
        Method getParameters = cache2.getGetParameters();
        if (getParameters == null || (getName = cache2.getGetName()) == null) {
            return null;
        }
        Object invoke = getParameters.invoke(member, new Object[0]);
        if (invoke != null) {
            Object[] objArr = (Object[]) invoke;
            ArrayList arrayList = new ArrayList(objArr.length);
            for (Object obj : objArr) {
                Object invoke2 = getName.invoke(obj, new Object[0]);
                if (invoke2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                arrayList.add((String) invoke2);
            }
            return arrayList;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<*>");
    }
}
