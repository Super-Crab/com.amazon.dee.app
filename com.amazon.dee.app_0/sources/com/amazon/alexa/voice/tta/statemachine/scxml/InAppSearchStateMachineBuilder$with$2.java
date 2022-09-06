package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.scxml.SCXMLContext;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: InAppSearchStateMachineBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "c", "Lcom/amazon/scxml/SCXMLContext;", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchContext;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
final class InAppSearchStateMachineBuilder$with$2 extends Lambda implements Function1<SCXMLContext<InAppSearchContext>, Boolean> {
    final /* synthetic */ NativeEvaluators $nativeEvaluators;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InAppSearchStateMachineBuilder$with$2(NativeEvaluators nativeEvaluators) {
        super(1);
        this.$nativeEvaluators = nativeEvaluators;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(SCXMLContext<InAppSearchContext> sCXMLContext) {
        return Boolean.valueOf(invoke2(sCXMLContext));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull SCXMLContext<InAppSearchContext> c) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        return this.$nativeEvaluators.simbaCallContextsMatch(c);
    }
}
