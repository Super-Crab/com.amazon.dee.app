package com.amazon.alexa.voice.ui.onedesign.scrim;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract;
import com.amazon.alexa.voice.ui.onedesign.util.RandomUtils;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.util.AlexaState;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes11.dex */
public final class ScrimPresenter implements ScrimContract.Presenter {
    private final CompositeDisposable composite = new CompositeDisposable();
    private final ScrimContract.Interactor interactor;
    private final Resources resources;
    private final ScrimContract.View view;

    public ScrimPresenter(ScrimContract.View view, ScrimContract.Interactor interactor, Resources resources) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
    }

    private CharSequence generateRandomHint() {
        String[] stringArray = this.resources.getStringArray(R.array.voice_ui_od_scrim_hints);
        return stringArray[RandomUtils.getRandomNumber(stringArray.length)];
    }

    private void updateHintState(boolean z, @Nullable CharSequence charSequence) {
        this.view.setHintVisible(z);
        if (z) {
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = generateRandomHint();
            }
            this.view.setHint(this.resources.getString(R.string.voice_ui_od_hints_try_saying), charSequence);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Presenter
    public void cancelClicked() {
        this.interactor.close();
    }

    public /* synthetic */ void lambda$start$0$ScrimPresenter(AlexaState alexaState) throws Throwable {
        updateHintState(false, null);
    }

    public /* synthetic */ void lambda$start$1$ScrimPresenter(AlexaState alexaState) throws Throwable {
        this.view.setChromeLocked(AlexaState.THINKING.equals(alexaState));
    }

    public /* synthetic */ void lambda$start$2$ScrimPresenter(Float f) throws Throwable {
        this.view.setChromeLevel(1.0f - f.floatValue());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Presenter
    public void start() {
        ScrimParametersModel scrimParameters = this.interactor.getScrimParameters();
        if (!scrimParameters.getHideCancelButton()) {
            this.view.showCancelButton();
        }
        if (scrimParameters.getShowHint()) {
            updateHintState(true, scrimParameters.getHint());
            this.composite.add(this.interactor.alexaState().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimPresenter$WP1suJ3yFtjD22hKWXixTQPm99A
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    ScrimPresenter.this.lambda$start$0$ScrimPresenter((AlexaState) obj);
                }
            }));
        }
        this.composite.add(this.interactor.alexaState().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimPresenter$8Xk2uqaWs3ByYxP1kztCmNVUzz8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ScrimPresenter.this.lambda$start$1$ScrimPresenter((AlexaState) obj);
            }
        }));
        this.composite.add(this.interactor.getSoundLevel().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.scrim.-$$Lambda$ScrimPresenter$NlZONa53fly3yI8XnkTmUS464Q4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ScrimPresenter.this.lambda$start$2$ScrimPresenter((Float) obj);
            }
        }));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Presenter
    public void stop() {
        this.composite.clear();
        this.interactor.stop();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Presenter
    public void ttaIngressClicked() {
        this.interactor.openTTA();
    }
}
