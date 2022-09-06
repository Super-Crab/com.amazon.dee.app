package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: DescriptorRendererImpl.kt */
/* loaded from: classes4.dex */
final class DescriptorRendererImpl$renderConstructor$1 extends Lambda implements Function1<ValueParameterDescriptor, String> {
    public static final DescriptorRendererImpl$renderConstructor$1 INSTANCE = new DescriptorRendererImpl$renderConstructor$1();

    DescriptorRendererImpl$renderConstructor$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(ValueParameterDescriptor valueParameterDescriptor) {
        return "";
    }
}
