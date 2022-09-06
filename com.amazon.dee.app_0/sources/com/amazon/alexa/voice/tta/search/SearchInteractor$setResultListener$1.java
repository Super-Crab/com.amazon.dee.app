package com.amazon.alexa.voice.tta.search;

import com.amazon.alexa.voice.ui.tta.search.ResultItem;
import com.amazon.alexa.voice.ui.tta.search.TtaResultListener;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SearchInteractor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012F\u0010\u0002\u001aB\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u001f\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b0\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "p1", "", "Lcom/amazon/alexa/voice/ui/tta/search/ResultItem;", "kotlin.jvm.PlatformType", "Lkotlin/ParameterName;", "name", "p0", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final /* synthetic */ class SearchInteractor$setResultListener$1 extends FunctionReference implements Function1<List<ResultItem>, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SearchInteractor$setResultListener$1(TtaResultListener ttaResultListener) {
        super(1, ttaResultListener);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "onUpdate";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(TtaResultListener.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "onUpdate(Ljava/util/List;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(List<ResultItem> list) {
        invoke2(list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull List<ResultItem> p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        ((TtaResultListener) this.receiver).onUpdate(p1);
    }
}
