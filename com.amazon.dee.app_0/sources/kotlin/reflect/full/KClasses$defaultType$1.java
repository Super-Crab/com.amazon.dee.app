package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.KClassImpl;
import org.jetbrains.annotations.NotNull;
/* compiled from: KClasses.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/lang/Class;", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class KClasses$defaultType$1 extends Lambda implements Function0<Class<? extends Object>> {
    final /* synthetic */ KClass $this_defaultType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClasses$defaultType$1(KClass kClass) {
        super(0);
        this.$this_defaultType = kClass;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final Class<? extends Object> mo12560invoke() {
        return ((KClassImpl) this.$this_defaultType).getJClass();
    }
}
