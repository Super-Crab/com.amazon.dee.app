package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ScopesHolderForClass.kt */
/* loaded from: classes2.dex */
public final class ScopesHolderForClass$getScope$1 extends Lambda implements Function0<T> {
    final /* synthetic */ KotlinTypeRefiner $kotlinTypeRefiner;
    final /* synthetic */ ScopesHolderForClass this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScopesHolderForClass$getScope$1(ScopesHolderForClass scopesHolderForClass, KotlinTypeRefiner kotlinTypeRefiner) {
        super(0);
        this.this$0 = scopesHolderForClass;
        this.$kotlinTypeRefiner = kotlinTypeRefiner;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TT; */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final MemberScope mo12560invoke() {
        Function1 function1;
        function1 = this.this$0.scopeFactory;
        return (MemberScope) function1.mo12165invoke(this.$kotlinTypeRefiner);
    }
}
