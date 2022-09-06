package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaPlayerInfoCardListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CardsSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/CardsSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/CardsApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "deregisterCardRendererListener", "", "cardListener", "Lcom/amazon/alexa/api/AlexaCardListener;", "deregisterPlayerInfoCardListener", "Lcom/amazon/alexa/api/AlexaPlayerInfoCardListener;", "registerCardRendererListener", "registerPlayerInfoCardListener", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CardsSubcomponent extends Subcomponent implements CardsApi {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardsSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.CardsApi
    public void deregisterCardRendererListener(@NotNull AlexaCardListener cardListener) {
        Intrinsics.checkParameterIsNotNull(cardListener, "cardListener");
        getFrameworkApis().getCards().deregisterListener(cardListener);
    }

    @Override // com.amazon.alexa.api.compat.CardsApi
    public void deregisterPlayerInfoCardListener(@NotNull AlexaPlayerInfoCardListener cardListener) {
        Intrinsics.checkParameterIsNotNull(cardListener, "cardListener");
        getFrameworkApis().getCards().deregisterListener(cardListener);
    }

    @Override // com.amazon.alexa.api.compat.CardsApi
    public void registerCardRendererListener(@NotNull AlexaCardListener cardListener) {
        Intrinsics.checkParameterIsNotNull(cardListener, "cardListener");
        getFrameworkApis().getCards().registerListener(cardListener);
    }

    @Override // com.amazon.alexa.api.compat.CardsApi
    public void registerPlayerInfoCardListener(@NotNull AlexaPlayerInfoCardListener cardListener) {
        Intrinsics.checkParameterIsNotNull(cardListener, "cardListener");
        getFrameworkApis().getCards().registerListener(cardListener);
    }
}
