package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TypeDeserializer.kt */
/* loaded from: classes4.dex */
public final class TypeDeserializer$typeConstructor$1 extends Lambda implements Function1<Integer, ClassDescriptor> {
    final /* synthetic */ ProtoBuf.Type $proto;
    final /* synthetic */ TypeDeserializer this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeDeserializer$typeConstructor$1(TypeDeserializer typeDeserializer, ProtoBuf.Type type) {
        super(1);
        this.this$0 = typeDeserializer;
        this.$proto = type;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ ClassDescriptor mo12165invoke(Integer num) {
        return invoke(num.intValue());
    }

    @NotNull
    public final ClassDescriptor invoke(int i) {
        DeserializationContext deserializationContext;
        Sequence generateSequence;
        Sequence map;
        List<Integer> mutableList;
        Sequence generateSequence2;
        int count;
        DeserializationContext deserializationContext2;
        deserializationContext = this.this$0.c;
        ClassId classId = NameResolverUtilKt.getClassId(deserializationContext.getNameResolver(), i);
        generateSequence = SequencesKt__SequencesKt.generateSequence(this.$proto, new TypeDeserializer$typeConstructor$1$typeParametersCount$1(this));
        map = SequencesKt___SequencesKt.map(generateSequence, TypeDeserializer$typeConstructor$1$typeParametersCount$2.INSTANCE);
        mutableList = SequencesKt___SequencesKt.toMutableList(map);
        generateSequence2 = SequencesKt__SequencesKt.generateSequence(classId, TypeDeserializer$typeConstructor$1$classNestingLevel$1.INSTANCE);
        count = SequencesKt___SequencesKt.count(generateSequence2);
        while (mutableList.size() < count) {
            mutableList.add(0);
        }
        deserializationContext2 = this.this$0.c;
        return deserializationContext2.getComponents().getNotFoundClasses().getClass(classId, mutableList);
    }
}
