package com.amazon.alexa.voiceui.accessibility;

import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AccessibilityHandler_Factory implements Factory<AccessibilityHandler> {
    private final Provider<AccessibilityDelegate> accessibilityDelegateProvider;
    private final Provider<AttentionSystemContract> attentionSystemContractProvider;

    public AccessibilityHandler_Factory(Provider<AttentionSystemContract> provider, Provider<AccessibilityDelegate> provider2) {
        this.attentionSystemContractProvider = provider;
        this.accessibilityDelegateProvider = provider2;
    }

    public static AccessibilityHandler_Factory create(Provider<AttentionSystemContract> provider, Provider<AccessibilityDelegate> provider2) {
        return new AccessibilityHandler_Factory(provider, provider2);
    }

    public static AccessibilityHandler newAccessibilityHandler(AttentionSystemContract attentionSystemContract, AccessibilityDelegate accessibilityDelegate) {
        return new AccessibilityHandler(attentionSystemContract, accessibilityDelegate);
    }

    public static AccessibilityHandler provideInstance(Provider<AttentionSystemContract> provider, Provider<AccessibilityDelegate> provider2) {
        return new AccessibilityHandler(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccessibilityHandler mo10268get() {
        return provideInstance(this.attentionSystemContractProvider, this.accessibilityDelegateProvider);
    }
}
