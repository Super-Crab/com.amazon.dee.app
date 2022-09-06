package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TypeDeserializer.kt */
/* loaded from: classes4.dex */
public final class TypeDeserializer$typeConstructor$1$typeParametersCount$2 extends Lambda implements Function1<ProtoBuf.Type, Integer> {
    public static final TypeDeserializer$typeConstructor$1$typeParametersCount$2 INSTANCE = new TypeDeserializer$typeConstructor$1$typeParametersCount$2();

    TypeDeserializer$typeConstructor$1$typeParametersCount$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Integer mo12165invoke(ProtoBuf.Type type) {
        return Integer.valueOf(invoke2(type));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final int invoke2(@NotNull ProtoBuf.Type it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return it2.getArgumentCount();
    }
}
