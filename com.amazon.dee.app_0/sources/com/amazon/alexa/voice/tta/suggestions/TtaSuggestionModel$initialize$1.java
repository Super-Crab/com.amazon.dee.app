package com.amazon.alexa.voice.tta.suggestions;

import com.amazon.alexa.voice.tta.simba.Suggestion;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TtaSuggestionModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u001d\u0010\u0002\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "p1", "", "Lcom/amazon/alexa/voice/tta/simba/Suggestion;", "Lkotlin/ParameterName;", "name", "suggestions", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final /* synthetic */ class TtaSuggestionModel$initialize$1 extends FunctionReference implements Function1<List<? extends Suggestion>, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public TtaSuggestionModel$initialize$1(TtaSuggestionModel ttaSuggestionModel) {
        super(1, ttaSuggestionModel);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "onGetSuggestionsResponse";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(TtaSuggestionModel.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "onGetSuggestionsResponse(Ljava/util/List;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(List<? extends Suggestion> list) {
        invoke2((List<Suggestion>) list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable List<Suggestion> list) {
        ((TtaSuggestionModel) this.receiver).onGetSuggestionsResponse(list);
    }
}
