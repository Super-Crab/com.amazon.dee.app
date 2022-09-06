package com.amazon.alexa.voiceui.voice;

import com.amazon.alexa.voiceui.cards.CardView;
import com.amazon.alexa.voiceui.chrome.VoiceChromeView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class VoiceView {
    private final CardView cardView;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final VoiceChromeView voiceChromeView;

    @Inject
    public VoiceView(VoiceChromeView voiceChromeView, CardView cardView) {
        this.voiceChromeView = voiceChromeView;
        this.cardView = cardView;
        observeCardRendering();
    }

    private void observeCardRendering() {
        this.disposable.add(this.cardView.onCardRenderObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voiceui.voice.-$$Lambda$VoiceView$R7iyJhGw1xwj8-911HikakbB-ho
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                VoiceView.this.lambda$observeCardRendering$0$VoiceView((Boolean) obj);
            }
        }));
    }

    public void cleanup() {
        this.cardView.cleanup();
        this.voiceChromeView.cleanup();
        this.disposable.clear();
    }

    public void hideCard() {
        this.cardView.hideCardSilently();
    }

    public void hideVoiceChrome() {
        this.voiceChromeView.hideVoiceChrome();
    }

    public boolean isCardVisible() {
        return this.cardView.isVisible();
    }

    public /* synthetic */ void lambda$observeCardRendering$0$VoiceView(Boolean bool) throws Throwable {
        if (bool.booleanValue()) {
            this.voiceChromeView.hideVoiceChrome();
        }
    }

    public Observable<Boolean> onCardRenderObservable() {
        return this.cardView.onCardRenderObservable();
    }

    public void prepareForConfigurationChange() {
        this.cardView.prepareForConfigurationChange();
        this.voiceChromeView.prepareForConfigurationChange();
    }

    public void showCard(String str) {
        this.cardView.showCard(str);
    }

    public void showVoiceChrome() {
        this.voiceChromeView.showVoiceChrome();
    }
}
