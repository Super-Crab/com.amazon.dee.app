package com.amazon.alexa.tarazed.service;

import com.amazon.alexa.tarazed.account.AztecTokenProviderAlexaMobile;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DefaultAlexaTarazedService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/tarazed/account/AztecTokenProviderAlexaMobile;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public final class DefaultAlexaTarazedService$initTarazed$2 extends Lambda implements Function0<AztecTokenProviderAlexaMobile> {
    final /* synthetic */ DefaultAlexaTarazedService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultAlexaTarazedService$initTarazed$2(DefaultAlexaTarazedService defaultAlexaTarazedService) {
        super(0);
        this.this$0 = defaultAlexaTarazedService;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final AztecTokenProviderAlexaMobile mo12560invoke() {
        AztecTokenProviderAlexaMobile mo10268get = this.this$0.getAztecTokenProvider$AlexaMobileAndroidTarazedIntegration_release().mo10268get();
        Intrinsics.checkExpressionValueIsNotNull(mo10268get, "aztecTokenProvider.get()");
        return mo10268get;
    }
}
