package com.amazon.alexa.voice.tta;

import com.amazon.alexa.voice.tta.typing.TypingPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class TtaActivity_MembersInjector implements MembersInjector<TtaActivity> {
    private final Provider<TypingPresenter> typingPresenterProvider;

    public TtaActivity_MembersInjector(Provider<TypingPresenter> provider) {
        this.typingPresenterProvider = provider;
    }

    public static MembersInjector<TtaActivity> create(Provider<TypingPresenter> provider) {
        return new TtaActivity_MembersInjector(provider);
    }

    public static void injectTypingPresenter(TtaActivity ttaActivity, TypingPresenter typingPresenter) {
        ttaActivity.typingPresenter = typingPresenter;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TtaActivity ttaActivity) {
        injectTypingPresenter(ttaActivity, this.typingPresenterProvider.mo10268get());
    }
}
