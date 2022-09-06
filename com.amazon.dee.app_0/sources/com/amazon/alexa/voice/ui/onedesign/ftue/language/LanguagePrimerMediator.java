package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.locale.LocaleUpdateCallback;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
import java.util.Locale;
/* loaded from: classes11.dex */
public class LanguagePrimerMediator implements LanguagePrimerContract.Mediator, LocaleUpdateCallback {
    private final ViewController controller;
    private final Handler handler;
    private final LocaleAuthorityWrapper localeAuthorityWrapper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LanguagePrimerMediator(@NonNull ViewController viewController, @NonNull LocaleAuthorityWrapper localeAuthorityWrapper) {
        this(viewController, localeAuthorityWrapper, new Handler());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.Mediator
    public void close() {
        Router router = this.controller.getRouter();
        if (this.controller.isAttached()) {
            router.popController(this.controller);
        }
        Component component = this.controller.getComponent();
        if (component.isRegistered(OnLanguagePrimerDismissedListener.class)) {
            ((OnLanguagePrimerDismissedListener) component.get(OnLanguagePrimerDismissedListener.class)).onLanguagePrimerDismissed();
        }
    }

    @Override // com.amazon.alexa.voice.ui.locale.LocaleUpdateCallback
    public void onFailure(int i, @Nullable String str) {
        this.handler.post(new $$Lambda$eDNm8AP8sdK5xeNPxwx3zNUo60(this));
    }

    @Override // com.amazon.alexa.voice.ui.locale.LocaleUpdateCallback
    public void onSuccess() {
        this.handler.post(new $$Lambda$eDNm8AP8sdK5xeNPxwx3zNUo60(this));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.Mediator
    public void updateLanguageAndExit(Language language) {
        this.localeAuthorityWrapper.setLocale(new Locale(language.getLanguage(), language.getCountry()), this);
    }

    @VisibleForTesting
    LanguagePrimerMediator(@NonNull ViewController viewController, @NonNull LocaleAuthorityWrapper localeAuthorityWrapper, @NonNull Handler handler) {
        this.controller = viewController;
        this.localeAuthorityWrapper = localeAuthorityWrapper;
        this.handler = handler;
    }
}
