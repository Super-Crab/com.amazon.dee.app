package kotlinx.io.core.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Require.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0004\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0081\b¨\u0006\u0007"}, d2 = {"require", "", "condition", "", "message", "Lkotlin/Function0;", "", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class RequireKt {
    @PublishedApi
    public static final void require(boolean z, @NotNull final Function0<String> message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (z) {
            return;
        }
        new RequireFailureCapture() { // from class: kotlinx.io.core.internal.RequireKt$require$m$1
            @Override // kotlinx.io.core.internal.RequireFailureCapture
            @NotNull
            public Void doFail() {
                throw new IllegalArgumentException((String) Function0.this.mo12560invoke());
            }
        }.doFail();
        throw null;
    }
}
