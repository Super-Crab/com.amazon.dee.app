package com.amazon.alexa.voice.ui.onedesign.ftue.primer;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsController;
import com.amazon.alexa.voice.ui.onedesign.transitions.DismissContentTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.PushContentTransition;
import com.amazon.regulator.Component;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class PrimerMediator implements PrimerContract.Mediator {
    private final String[] allPermissions;
    private final ViewController controller;
    private final String[] minimumPermissions;

    public PrimerMediator(@NonNull ViewController viewController, @NonNull String[] strArr, @NonNull String[] strArr2) {
        Preconditions.nonNull(viewController, "controller argument must be non-null.");
        Preconditions.nonNull(strArr, "allPermissions argument must be non-null.");
        Preconditions.nonNull(strArr2, "minimumPermissions argument must be non-null.");
        this.controller = viewController;
        this.allPermissions = (String[]) strArr.clone();
        this.minimumPermissions = (String[]) strArr2.clone();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Mediator
    @NonNull
    public String[] getMinimumRequiredPermissions() {
        return (String[]) this.minimumPermissions.clone();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Mediator
    @NonNull
    public String[] getPermissions() {
        return (String[]) this.allPermissions.clone();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Mediator
    public void notifyVoicePermissionGrantedListener() {
        Component component = this.controller.getRouter().getComponent();
        if (component.isRegistered(VoicePermissionGrantedListener.class)) {
            ((VoicePermissionGrantedListener) component.get(VoicePermissionGrantedListener.class)).onVoicePermissionGranted();
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Mediator
    public void showPermissionSettings() {
        this.controller.getRouter().replaceCurrentController(new ControllerTransaction(GoToSettingsController.create(), new DismissContentTransition(), new PushContentTransition()));
    }
}
