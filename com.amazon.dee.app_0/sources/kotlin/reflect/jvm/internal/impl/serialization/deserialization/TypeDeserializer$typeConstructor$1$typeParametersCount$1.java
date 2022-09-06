package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TypeDeserializer.kt */
/* loaded from: classes4.dex */
public final class TypeDeserializer$typeConstructor$1$typeParametersCount$1 extends Lambda implements Function1<ProtoBuf.Type, ProtoBuf.Type> {
    final /* synthetic */ TypeDeserializer$typeConstructor$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeDeserializer$typeConstructor$1$typeParametersCount$1(TypeDeserializer$typeConstructor$1 typeDeserializer$typeConstructor$1) {
        super(1);
        this.this$0 = typeDeserializer$typeConstructor$1;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final ProtoBuf.Type mo12165invoke(@NotNull ProtoBuf.Type it2) {
        DeserializationContext deserializationContext;
        Intrinsics.checkParameterIsNotNull(it2, "it");
        deserializationContext = this.this$0.this$0.c;
        return ProtoTypeTableUtilKt.outerType(it2, deserializationContext.getTypeTable());
    }
}
