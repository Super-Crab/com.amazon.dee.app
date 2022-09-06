package com.amazon.alexa.voice.ui.onedesign.hints;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.hints.HintsContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class HintsPresenter implements HintsContract.Presenter {
    private final HintsContract.Interactor interactor;
    private final Resources resources;
    private final HintsContract.View view;

    public HintsPresenter(@NonNull HintsContract.View view, @NonNull HintsContract.Interactor interactor, @NonNull Resources resources) {
        Preconditions.nonNull(view, "view must be non-null.");
        Preconditions.nonNull(interactor, "interactor must be non-null.");
        Preconditions.nonNull(resources, "resources must be non-null.");
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.Presenter
    public void doneClicked() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.Presenter
    public void start() {
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(this.resources.getString(R.string.voice_ui_od_hints_try_saying));
        this.view.setWeatherHint(this.resources.getString(R.string.voice_ui_od_hints_weather));
        this.view.setShoppingHint(this.resources.getString(R.string.voice_ui_od_hints_shopping_list));
        this.view.setSunsetHint(this.resources.getString(R.string.voice_ui_od_hints_sunset));
        this.view.setDoneButtonText(this.resources.getString(R.string.voice_ui_od_hints_done_button_text));
    }
}
