package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CancellableContinuationImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B.\u0012'\u0010\u0002\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016R/\u0010\u0002\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003j\u0002`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/InvokeOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "handler", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "(Lkotlin/jvm/functions/Function1;)V", "invoke", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class InvokeOnCancel extends CancelHandler {
    private final Function1<Throwable, Unit> handler;

    /* JADX WARN: Multi-variable type inference failed */
    public InvokeOnCancel(@NotNull Function1<? super Throwable, Unit> function1) {
        this.handler = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InvokeOnCancel[");
        outline107.append(DebugStringsKt.getClassSimpleName(this.handler));
        outline107.append('@');
        outline107.append(DebugStringsKt.getHexAddress(this));
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        this.handler.mo12165invoke(th);
    }
}