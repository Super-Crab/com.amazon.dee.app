package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TypeDeserializer.kt */
/* loaded from: classes4.dex */
public final class TypeDeserializer$classDescriptors$1 extends Lambda implements Function1<Integer, ClassDescriptor> {
    final /* synthetic */ TypeDeserializer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeDeserializer$classDescriptors$1(TypeDeserializer typeDeserializer) {
        super(1);
        this.this$0 = typeDeserializer;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ ClassDescriptor mo12165invoke(Integer num) {
        return invoke(num.intValue());
    }

    @Nullable
    public final ClassDescriptor invoke(int i) {
        ClassDescriptor computeClassDescriptor;
        computeClassDescriptor = this.this$0.computeClassDescriptor(i);
        return computeClassDescriptor;
    }
}
