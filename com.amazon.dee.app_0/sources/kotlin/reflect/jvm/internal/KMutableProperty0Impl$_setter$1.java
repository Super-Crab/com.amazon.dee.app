package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KMutableProperty0Impl;
import org.jetbrains.annotations.NotNull;
/* compiled from: KProperty0Impl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/KMutableProperty0Impl$Setter;", "R", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class KMutableProperty0Impl$_setter$1 extends Lambda implements Function0<KMutableProperty0Impl.Setter<R>> {
    final /* synthetic */ KMutableProperty0Impl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KMutableProperty0Impl$_setter$1(KMutableProperty0Impl kMutableProperty0Impl) {
        super(0);
        this.this$0 = kMutableProperty0Impl;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final KMutableProperty0Impl.Setter<R> mo12560invoke() {
        return new KMutableProperty0Impl.Setter<>(this.this$0);
    }
}
