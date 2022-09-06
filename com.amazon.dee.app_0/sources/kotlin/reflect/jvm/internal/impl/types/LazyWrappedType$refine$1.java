package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SpecialTypes.kt */
/* loaded from: classes4.dex */
public final class LazyWrappedType$refine$1 extends Lambda implements Function0<KotlinType> {
    final /* synthetic */ KotlinTypeRefiner $kotlinTypeRefiner;
    final /* synthetic */ LazyWrappedType this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyWrappedType$refine$1(LazyWrappedType lazyWrappedType, KotlinTypeRefiner kotlinTypeRefiner) {
        super(0);
        this.this$0 = lazyWrappedType;
        this.$kotlinTypeRefiner = kotlinTypeRefiner;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final KotlinType mo12560invoke() {
        Function0 function0;
        KotlinTypeRefiner kotlinTypeRefiner = this.$kotlinTypeRefiner;
        function0 = this.this$0.computation;
        return kotlinTypeRefiner.refineType((KotlinType) function0.mo12560invoke());
    }
}
