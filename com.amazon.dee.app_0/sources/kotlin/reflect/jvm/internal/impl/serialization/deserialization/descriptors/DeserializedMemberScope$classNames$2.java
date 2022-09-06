package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* compiled from: DeserializedMemberScope.kt */
/* loaded from: classes4.dex */
final class DeserializedMemberScope$classNames$2 extends Lambda implements Function0<Set<? extends Name>> {
    final /* synthetic */ Function0 $classNames;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedMemberScope$classNames$2(Function0 function0) {
        super(0);
        this.$classNames = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Set<? extends Name> mo12560invoke() {
        Set<? extends Name> set;
        set = CollectionsKt___CollectionsKt.toSet((Iterable) this.$classNames.mo12560invoke());
        return set;
    }
}
