package kotlin.reflect.jvm.internal;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KDeclarationContainerImpl;
import kotlin.reflect.jvm.internal.KPackageImpl;
import org.jetbrains.annotations.NotNull;
/* compiled from: KPackageImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class KPackageImpl$Data$members$2 extends Lambda implements Function0<Collection<? extends KCallableImpl<?>>> {
    final /* synthetic */ KPackageImpl.Data this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KPackageImpl$Data$members$2(KPackageImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Collection<? extends KCallableImpl<?>> mo12560invoke() {
        KPackageImpl.Data data = this.this$0;
        return KPackageImpl.this.getMembers(data.getScope(), KDeclarationContainerImpl.MemberBelonginess.DECLARED);
    }
}
