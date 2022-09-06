package kotlin.reflect.jvm.internal;

import com.dee.app.metrics.MetricsConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import org.jetbrains.annotations.NotNull;
/* compiled from: RuntimeTypeMapper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0005\u0005\u0006\u0007\b\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u0082\u0001\u0005\n\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "", "()V", "asString", "", "FakeJavaAnnotationConstructor", "JavaConstructor", "JavaMethod", "KotlinConstructor", "KotlinFunction", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$JavaMethod;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$JavaConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$FakeJavaAnnotationConstructor;", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class JvmFunctionSignature {

    /* compiled from: RuntimeTypeMapper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$FakeJavaAnnotationConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "getJClass", "()Ljava/lang/Class;", "methods", "", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "getMethods", "()Ljava/util/List;", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class FakeJavaAnnotationConstructor extends JvmFunctionSignature {
        @NotNull
        private final Class<?> jClass;
        @NotNull
        private final List<Method> methods;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FakeJavaAnnotationConstructor(@NotNull Class<?> jClass) {
            super(null);
            List<Method> sortedWith;
            Intrinsics.checkParameterIsNotNull(jClass, "jClass");
            this.jClass = jClass;
            Method[] declaredMethods = this.jClass.getDeclaredMethods();
            Intrinsics.checkExpressionValueIsNotNull(declaredMethods, "jClass.declaredMethods");
            sortedWith = ArraysKt___ArraysKt.sortedWith(declaredMethods, new Comparator<T>() { // from class: kotlin.reflect.jvm.internal.JvmFunctionSignature$FakeJavaAnnotationConstructor$$special$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int compareValues;
                    Method it2 = (Method) t;
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    String name = it2.getName();
                    Method it3 = (Method) t2;
                    Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(name, it3.getName());
                    return compareValues;
                }
            });
            this.methods = sortedWith;
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        @NotNull
        public String asString() {
            String joinToString$default;
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.methods, "", "<init>(", ")V", 0, null, JvmFunctionSignature$FakeJavaAnnotationConstructor$asString$1.INSTANCE, 24, null);
            return joinToString$default;
        }

        @NotNull
        public final List<Method> getMethods() {
            return this.methods;
        }
    }

    /* compiled from: RuntimeTypeMapper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$JavaConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "constructor", "Ljava/lang/reflect/Constructor;", "(Ljava/lang/reflect/Constructor;)V", "getConstructor", "()Ljava/lang/reflect/Constructor;", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class JavaConstructor extends JvmFunctionSignature {
        @NotNull
        private final Constructor<?> constructor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JavaConstructor(@NotNull Constructor<?> constructor) {
            super(null);
            Intrinsics.checkParameterIsNotNull(constructor, "constructor");
            this.constructor = constructor;
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        @NotNull
        public String asString() {
            String joinToString$default;
            Class<?>[] parameterTypes = this.constructor.getParameterTypes();
            Intrinsics.checkExpressionValueIsNotNull(parameterTypes, "constructor.parameterTypes");
            joinToString$default = ArraysKt___ArraysKt.joinToString$default(parameterTypes, "", "<init>(", ")V", 0, (CharSequence) null, JvmFunctionSignature$JavaConstructor$asString$1.INSTANCE, 24, (Object) null);
            return joinToString$default;
        }

        @NotNull
        public final Constructor<?> getConstructor() {
            return this.constructor;
        }
    }

    /* compiled from: RuntimeTypeMapper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$JavaMethod;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", MetricsConstants.NativeFetch.METHOD, "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", "getMethod", "()Ljava/lang/reflect/Method;", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class JavaMethod extends JvmFunctionSignature {
        @NotNull
        private final Method method;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JavaMethod(@NotNull Method method) {
            super(null);
            Intrinsics.checkParameterIsNotNull(method, "method");
            this.method = method;
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        @NotNull
        public String asString() {
            String signature;
            signature = RuntimeTypeMapperKt.getSignature(this.method);
            return signature;
        }

        @NotNull
        public final Method getMethod() {
            return this.method;
        }
    }

    /* compiled from: RuntimeTypeMapper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinConstructor;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "signature", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMemberSignature$Method;", "(Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;)V", "_signature", "", "constructorDesc", "getConstructorDesc", "()Ljava/lang/String;", "getSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "asString", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class KotlinConstructor extends JvmFunctionSignature {
        private final String _signature;
        @NotNull
        private final JvmMemberSignature.Method signature;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KotlinConstructor(@NotNull JvmMemberSignature.Method signature) {
            super(null);
            Intrinsics.checkParameterIsNotNull(signature, "signature");
            this.signature = signature;
            this._signature = this.signature.asString();
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        @NotNull
        public String asString() {
            return this._signature;
        }

        @NotNull
        public final String getConstructorDesc() {
            return this.signature.getDesc();
        }
    }

    /* compiled from: RuntimeTypeMapper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "signature", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMemberSignature$Method;", "(Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;)V", "_signature", "", "methodDesc", "getMethodDesc", "()Ljava/lang/String;", "methodName", "getMethodName", "getSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "asString", "kotlin-reflection"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class KotlinFunction extends JvmFunctionSignature {
        private final String _signature;
        @NotNull
        private final JvmMemberSignature.Method signature;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KotlinFunction(@NotNull JvmMemberSignature.Method signature) {
            super(null);
            Intrinsics.checkParameterIsNotNull(signature, "signature");
            this.signature = signature;
            this._signature = this.signature.asString();
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        @NotNull
        public String asString() {
            return this._signature;
        }

        @NotNull
        public final String getMethodDesc() {
            return this.signature.getDesc();
        }

        @NotNull
        public final String getMethodName() {
            return this.signature.getName();
        }
    }

    private JvmFunctionSignature() {
    }

    @NotNull
    public abstract String asString();

    public /* synthetic */ JvmFunctionSignature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
