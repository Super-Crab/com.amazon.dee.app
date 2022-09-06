package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
public final class JvmBuiltInsSettings$createMockJavaIoSerializableType$superTypes$1 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ JvmBuiltInsSettings this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltInsSettings$createMockJavaIoSerializableType$superTypes$1(JvmBuiltInsSettings jvmBuiltInsSettings) {
        super(0);
        this.this$0 = jvmBuiltInsSettings;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final SimpleType mo12560invoke() {
        ModuleDescriptor moduleDescriptor;
        moduleDescriptor = this.this$0.moduleDescriptor;
        SimpleType anyType = moduleDescriptor.getBuiltIns().getAnyType();
        Intrinsics.checkExpressionValueIsNotNull(anyType, "moduleDescriptor.builtIns.anyType");
        return anyType;
    }
}
