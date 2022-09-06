package com.amazon.alexa.voice.tta.search;

import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SearchInteractor.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
final class SearchInteractor$sam$io_reactivex_rxjava3_functions_Consumer$0 implements Consumer {
    private final /* synthetic */ Function1 function;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SearchInteractor$sam$io_reactivex_rxjava3_functions_Consumer$0(Function1 function1) {
        this.function = function1;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final /* synthetic */ void accept(Object obj) {
        Intrinsics.checkExpressionValueIsNotNull(this.function.mo12165invoke(obj), "invoke(...)");
    }
}
