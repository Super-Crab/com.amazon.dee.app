package kotlin.reflect.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KFunctionImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/calls/Caller;", "Ljava/lang/reflect/Member;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KFunctionImpl$caller$2 extends Lambda implements Function0<Caller<? extends Member>> {
    final /* synthetic */ KFunctionImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KFunctionImpl$caller$2(KFunctionImpl kFunctionImpl) {
        super(0);
        this.this$0 = kFunctionImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Caller<? extends Member> mo12560invoke() {
        int collectionSizeOrDefault;
        Object constructor;
        Caller createInstanceMethodCaller;
        int collectionSizeOrDefault2;
        JvmFunctionSignature mapSignature = RuntimeTypeMapper.INSTANCE.mapSignature(this.this$0.mo11480getDescriptor());
        if (mapSignature instanceof JvmFunctionSignature.KotlinConstructor) {
            if (this.this$0.isAnnotationConstructor()) {
                Class<?> jClass = this.this$0.getContainer().getJClass();
                List<KParameter> parameters = this.this$0.getParameters();
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault2);
                for (KParameter kParameter : parameters) {
                    String name = kParameter.getName();
                    if (name == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(name);
                }
                return new AnnotationConstructorCaller(jClass, arrayList, AnnotationConstructorCaller.CallMode.POSITIONAL_CALL, AnnotationConstructorCaller.Origin.KOTLIN, null, 16, null);
            }
            constructor = this.this$0.getContainer().findConstructorBySignature(((JvmFunctionSignature.KotlinConstructor) mapSignature).getConstructorDesc());
        } else if (mapSignature instanceof JvmFunctionSignature.KotlinFunction) {
            JvmFunctionSignature.KotlinFunction kotlinFunction = (JvmFunctionSignature.KotlinFunction) mapSignature;
            constructor = this.this$0.getContainer().findMethodBySignature(kotlinFunction.getMethodName(), kotlinFunction.getMethodDesc());
        } else if (mapSignature instanceof JvmFunctionSignature.JavaMethod) {
            constructor = ((JvmFunctionSignature.JavaMethod) mapSignature).getMethod();
        } else if (!(mapSignature instanceof JvmFunctionSignature.JavaConstructor)) {
            if (!(mapSignature instanceof JvmFunctionSignature.FakeJavaAnnotationConstructor)) {
                throw new NoWhenBranchMatchedException();
            }
            List<Method> methods = ((JvmFunctionSignature.FakeJavaAnnotationConstructor) mapSignature).getMethods();
            Class<?> jClass2 = this.this$0.getContainer().getJClass();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(methods, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (Method it2 : methods) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                arrayList2.add(it2.getName());
            }
            return new AnnotationConstructorCaller(jClass2, arrayList2, AnnotationConstructorCaller.CallMode.POSITIONAL_CALL, AnnotationConstructorCaller.Origin.JAVA, methods);
        } else {
            constructor = ((JvmFunctionSignature.JavaConstructor) mapSignature).getConstructor();
        }
        if (constructor instanceof Constructor) {
            KFunctionImpl kFunctionImpl = this.this$0;
            createInstanceMethodCaller = kFunctionImpl.createConstructorCaller((Constructor) constructor, kFunctionImpl.mo11480getDescriptor());
        } else if (constructor instanceof Method) {
            Method method = (Method) constructor;
            createInstanceMethodCaller = !Modifier.isStatic(method.getModifiers()) ? this.this$0.createInstanceMethodCaller(method) : this.this$0.mo11480getDescriptor().mo12070getAnnotations().mo11701findAnnotation(UtilKt.getJVM_STATIC()) != null ? this.this$0.createJvmStaticInObjectCaller(method) : this.this$0.createStaticMethodCaller(method);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not compute caller for function: ");
            outline107.append(this.this$0.mo11480getDescriptor());
            outline107.append(" (member = ");
            outline107.append(constructor);
            outline107.append(')');
            throw new KotlinReflectionInternalError(outline107.toString());
        }
        return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(createInstanceMethodCaller, this.this$0.mo11480getDescriptor(), false, 2, null);
    }
}
