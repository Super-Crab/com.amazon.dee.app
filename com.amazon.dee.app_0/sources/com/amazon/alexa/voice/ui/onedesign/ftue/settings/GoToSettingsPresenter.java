package com.amazon.alexa.voice.ui.onedesign.ftue.settings;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class GoToSettingsPresenter implements GoToSettingsContract.Presenter {
    private final GoToSettingsContract.Interactor interactor;
    private final Resources resources;
    private final GoToSettingsContract.View view;

    public GoToSettingsPresenter(@NonNull GoToSettingsContract.View view, @NonNull GoToSettingsContract.Interactor interactor, @NonNull Resources resources) {
        Preconditions.nonNull(view, "view argument must be non-null.");
        Preconditions.nonNull(interactor, "interactor argument must be non-null.");
        Preconditions.nonNull(resources, "resources argument must be non-null.");
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.Presenter
    public void settingsClicked() {
        this.interactor.openSettings();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.Presenter
    public void start() {
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(this.resources.getString(R.string.voice_ui_od_permissions_settings_title));
        this.view.setContent(this.resources.getString(R.string.voice_ui_od_permissions_settings_content));
        this.view.setLinkText(this.resources.getString(R.string.voice_ui_od_permissions_settings_link));
    }
}
