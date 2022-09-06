package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPackageImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KPackageImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KPackageImpl$Data$scope$2 extends Lambda implements Function0<MemberScope> {
    final /* synthetic */ KPackageImpl.Data this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KPackageImpl$Data$scope$2(KPackageImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final MemberScope mo12560invoke() {
        ReflectKotlinClass kotlinClass;
        kotlinClass = this.this$0.getKotlinClass();
        if (kotlinClass != null) {
            return this.this$0.getModuleData().getPackagePartScopeCache().getPackagePartScope(kotlinClass);
        }
        return MemberScope.Empty.INSTANCE;
    }
}
