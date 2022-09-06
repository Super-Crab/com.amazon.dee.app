package com.amazon.alexa.voiceui.cards;

import android.view.ViewGroup;
import com.amazon.alexa.voiceui.accessibility.AccessibilityDelegate;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class CardModule_ProvidesAccessibilityDelegateFactory implements Factory<AccessibilityDelegate> {
    private final Provider<ViewGroup> cardContainerProvider;
    private final CardModule module;

    public CardModule_ProvidesAccessibilityDelegateFactory(CardModule cardModule, Provider<ViewGroup> provider) {
        this.module = cardModule;
        this.cardContainerProvider = provider;
    }

    public static CardModule_ProvidesAccessibilityDelegateFactory create(CardModule cardModule, Provider<ViewGroup> provider) {
        return new CardModule_ProvidesAccessibilityDelegateFactory(cardModule, provider);
    }

    public static AccessibilityDelegate provideInstance(CardModule cardModule, Provider<ViewGroup> provider) {
        return proxyProvidesAccessibilityDelegate(cardModule, provider.mo10268get());
    }

    public static AccessibilityDelegate proxyProvidesAccessibilityDelegate(CardModule cardModule, ViewGroup viewGroup) {
        return (AccessibilityDelegate) Preconditions.checkNotNull(cardModule.providesAccessibilityDelegate(viewGroup), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AccessibilityDelegate mo10268get() {
        return provideInstance(this.module, this.cardContainerProvider);
    }
}
