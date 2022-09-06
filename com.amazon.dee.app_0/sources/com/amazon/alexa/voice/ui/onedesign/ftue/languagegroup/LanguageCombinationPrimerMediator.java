package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.locale.LocaleUpdateCallback;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.Language;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes11.dex */
public class LanguageCombinationPrimerMediator implements LanguageCombinationPrimerContract.Mediator, LocaleUpdateCallback {
    private final ViewController controller;
    private final Handler handler;
    private final LocaleAuthorityWrapper localeAuthorityWrapper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LanguageCombinationPrimerMediator(@NonNull ViewController viewController, @NonNull LocaleAuthorityWrapper localeAuthorityWrapper) {
        this(viewController, localeAuthorityWrapper, new Handler());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.Mediator
    public void close() {
        Router router = this.controller.getRouter();
        if (this.controller.isAttached()) {
            router.popController(this.controller);
        }
        Component component = this.controller.getComponent();
        if (component.isRegistered(OnLanguageCombinationPrimerDismissedListener.class)) {
            ((OnLanguageCombinationPrimerDismissedListener) component.get(OnLanguageCombinationPrimerDismissedListener.class)).onLanguageCombinationPrimerDismissed();
        }
    }

    @Override // com.amazon.alexa.voice.ui.locale.LocaleUpdateCallback
    public void onFailure(int i, @Nullable String str) {
        this.handler.post(new $$Lambda$Hnrp9FSaPncX12n6iJRL2dSlEg(this));
    }

    @Override // com.amazon.alexa.voice.ui.locale.LocaleUpdateCallback
    public void onSuccess() {
        this.handler.post(new $$Lambda$Hnrp9FSaPncX12n6iJRL2dSlEg(this));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.Mediator
    public void updateLanguageCombinationAndExit(LanguageGroup languageGroup) {
        ArrayList arrayList = new ArrayList();
        for (Language language : languageGroup.getLanguages()) {
            arrayList.add(new Locale(language.getLanguage(), language.getCountry()));
        }
        this.localeAuthorityWrapper.setLocales(arrayList, this);
    }

    @VisibleForTesting
    LanguageCombinationPrimerMediator(@NonNull ViewController viewController, @NonNull LocaleAuthorityWrapper localeAuthorityWrapper, @NonNull Handler handler) {
        this.controller = viewController;
        this.localeAuthorityWrapper = localeAuthorityWrapper;
        this.handler = handler;
    }
}
