package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.Field;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReflectJavaClass.kt */
/* loaded from: classes2.dex */
public final /* synthetic */ class ReflectJavaClass$fields$2 extends FunctionReference implements Function1<Field, ReflectJavaField> {
    public static final ReflectJavaClass$fields$2 INSTANCE = new ReflectJavaClass$fields$2();

    ReflectJavaClass$fields$2() {
        super(1);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "<init>";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ReflectJavaField.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "<init>(Ljava/lang/reflect/Field;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final ReflectJavaField mo12165invoke(@NotNull Field p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        return new ReflectJavaField(p1);
    }
}
