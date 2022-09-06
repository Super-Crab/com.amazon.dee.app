package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ClassDeserializer.kt */
/* loaded from: classes4.dex */
public final class ClassDeserializer$classes$1 extends Lambda implements Function1<ClassDeserializer.ClassKey, ClassDescriptor> {
    final /* synthetic */ ClassDeserializer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassDeserializer$classes$1(ClassDeserializer classDeserializer) {
        super(1);
        this.this$0 = classDeserializer;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final ClassDescriptor mo12165invoke(@NotNull ClassDeserializer.ClassKey key) {
        ClassDescriptor createClass;
        Intrinsics.checkParameterIsNotNull(key, "key");
        createClass = this.this$0.createClass(key);
        return createClass;
    }
}
