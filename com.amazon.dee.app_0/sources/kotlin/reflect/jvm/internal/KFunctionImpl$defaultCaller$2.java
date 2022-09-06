package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KFunctionImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/calls/Caller;", "Ljava/lang/reflect/Member;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KFunctionImpl$defaultCaller$2 extends Lambda implements Function0<Caller<? extends Member>> {
    final /* synthetic */ KFunctionImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KFunctionImpl$defaultCaller$2(KFunctionImpl kFunctionImpl) {
        super(0);
        this.this$0 = kFunctionImpl;
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [java.lang.reflect.Member] */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    /* renamed from: invoke  reason: collision with other method in class */
    public final Caller<? extends Member> mo12560invoke() {
        GenericDeclaration genericDeclaration;
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        Caller caller;
        ?? mo11499getMember;
        JvmFunctionSignature mapSignature = RuntimeTypeMapper.INSTANCE.mapSignature(this.this$0.mo11480getDescriptor());
        if (mapSignature instanceof JvmFunctionSignature.KotlinFunction) {
            KDeclarationContainerImpl container = this.this$0.getContainer();
            JvmFunctionSignature.KotlinFunction kotlinFunction = (JvmFunctionSignature.KotlinFunction) mapSignature;
            String methodName = kotlinFunction.getMethodName();
            String methodDesc = kotlinFunction.getMethodDesc();
            if (this.this$0.getCaller().mo11499getMember() == 0) {
                Intrinsics.throwNpe();
            }
            genericDeclaration = container.findDefaultMethod(methodName, methodDesc, !Modifier.isStatic(mo11499getMember.getModifiers()));
        } else if (mapSignature instanceof JvmFunctionSignature.KotlinConstructor) {
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
                return new AnnotationConstructorCaller(jClass, arrayList, AnnotationConstructorCaller.CallMode.CALL_BY_NAME, AnnotationConstructorCaller.Origin.KOTLIN, null, 16, null);
            }
            genericDeclaration = this.this$0.getContainer().findDefaultConstructor(((JvmFunctionSignature.KotlinConstructor) mapSignature).getConstructorDesc());
        } else if (mapSignature instanceof JvmFunctionSignature.FakeJavaAnnotationConstructor) {
            List<Method> methods = ((JvmFunctionSignature.FakeJavaAnnotationConstructor) mapSignature).getMethods();
            Class<?> jClass2 = this.this$0.getContainer().getJClass();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(methods, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (Method it2 : methods) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                arrayList2.add(it2.getName());
            }
            return new AnnotationConstructorCaller(jClass2, arrayList2, AnnotationConstructorCaller.CallMode.CALL_BY_NAME, AnnotationConstructorCaller.Origin.JAVA, methods);
        } else {
            genericDeclaration = null;
        }
        if (genericDeclaration instanceof Constructor) {
            KFunctionImpl kFunctionImpl = this.this$0;
            caller = kFunctionImpl.createConstructorCaller((Constructor) genericDeclaration, kFunctionImpl.mo11480getDescriptor());
        } else if (genericDeclaration instanceof Method) {
            if (this.this$0.mo11480getDescriptor().mo12070getAnnotations().mo11701findAnnotation(UtilKt.getJVM_STATIC()) != null) {
                DeclarationDescriptor mo11607getContainingDeclaration = this.this$0.mo11480getDescriptor().mo11607getContainingDeclaration();
                if (mo11607getContainingDeclaration == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
                if (!((ClassDescriptor) mo11607getContainingDeclaration).isCompanionObject()) {
                    caller = this.this$0.createJvmStaticInObjectCaller((Method) genericDeclaration);
                }
            }
            caller = this.this$0.createStaticMethodCaller((Method) genericDeclaration);
        } else {
            caller = null;
        }
        if (caller != null) {
            return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded(caller, this.this$0.mo11480getDescriptor(), true);
        }
        return null;
    }
}
