package com.amazon.alexa.voice.tta.search;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: SearchModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "response", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
final class SearchModel$updateInteractionResponse$1 extends Lambda implements Function1<String, Unit> {
    public static final SearchModel$updateInteractionResponse$1 INSTANCE = new SearchModel$updateInteractionResponse$1();

    SearchModel$updateInteractionResponse$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable String str) {
        GeneratedOutlineSupport1.outline158("SIMBA search result interaction response: ", str);
    }
}
