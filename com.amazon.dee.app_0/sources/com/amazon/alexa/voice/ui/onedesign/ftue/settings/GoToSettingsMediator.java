package com.amazon.alexa.voice.ui.onedesign.ftue.settings;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class GoToSettingsMediator implements GoToSettingsContract.Mediator {
    private final ViewController controller;
    private final ActivityStarter starter;

    public GoToSettingsMediator(@NonNull ViewController viewController, @NonNull ActivityStarter activityStarter) {
        Preconditions.nonNull(viewController, "controller argument must be non-null.");
        Preconditions.nonNull(activityStarter, "starter argument must be non-null.");
        this.controller = viewController;
        this.starter = activityStarter;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.Mediator
    public void openSettings() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setFlags(268435456);
        intent.setData(Uri.fromParts("package", this.controller.getContext().getPackageName(), null));
        this.starter.startActivityForResult(intent, 0);
    }
}
