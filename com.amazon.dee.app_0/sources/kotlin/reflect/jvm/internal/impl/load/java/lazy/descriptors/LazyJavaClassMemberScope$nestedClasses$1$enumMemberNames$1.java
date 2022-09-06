package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Set;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
/* compiled from: LazyJavaClassMemberScope.kt */
/* loaded from: classes3.dex */
final class LazyJavaClassMemberScope$nestedClasses$1$enumMemberNames$1 extends Lambda implements Function0<Set<? extends Name>> {
    final /* synthetic */ LazyJavaClassMemberScope$nestedClasses$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassMemberScope$nestedClasses$1$enumMemberNames$1(LazyJavaClassMemberScope$nestedClasses$1 lazyJavaClassMemberScope$nestedClasses$1) {
        super(0);
        this.this$0 = lazyJavaClassMemberScope$nestedClasses$1;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Set<? extends Name> mo12560invoke() {
        Set<? extends Name> plus;
        plus = SetsKt___SetsKt.plus((Set) this.this$0.this$0.getFunctionNames(), (Iterable) this.this$0.this$0.getVariableNames());
        return plus;
    }
}
