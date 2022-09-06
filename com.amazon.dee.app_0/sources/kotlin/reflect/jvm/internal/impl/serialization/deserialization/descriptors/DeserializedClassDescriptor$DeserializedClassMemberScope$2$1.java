package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeserializedClassDescriptor.kt */
/* loaded from: classes4.dex */
final class DeserializedClassDescriptor$DeserializedClassMemberScope$2$1 extends Lambda implements Function0<List<? extends Name>> {
    final /* synthetic */ List $it;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedClassDescriptor$DeserializedClassMemberScope$2$1(List list) {
        super(0);
        this.$it = list;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends Name> mo12560invoke() {
        return this.$it;
    }
}
