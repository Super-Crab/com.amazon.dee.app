package com.amazon.alexa.voice.ui.onedesign.ftue.primer;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.ftue.PermissionsDelegate;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class PrimerInteractor implements PrimerContract.Interactor {
    private final PrimerContract.Mediator mediator;
    private final PermissionsDelegate requester;

    public PrimerInteractor(@NonNull PrimerContract.Mediator mediator, @NonNull PermissionsDelegate permissionsDelegate) {
        Preconditions.nonNull(mediator, "mediator argument must be non-null.");
        Preconditions.nonNull(permissionsDelegate, "requester argument must be non-null.");
        this.mediator = mediator;
        this.requester = permissionsDelegate;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Interactor
    public void askForOsPermissions() {
        this.requester.requestPermissions(this.mediator.getPermissions());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Interactor
    public void close() {
        deferPrimer();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Interactor
    public void deferPrimer() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.Interactor
    public void validatePermissions() {
        String[] minimumRequiredPermissions = this.mediator.getMinimumRequiredPermissions();
        if (minimumRequiredPermissions != null && minimumRequiredPermissions.length > 0) {
            for (String str : minimumRequiredPermissions) {
                if (!this.requester.hasPermission(str)) {
                    this.mediator.showPermissionSettings();
                    return;
                }
            }
        }
        this.mediator.notifyVoicePermissionGrantedListener();
    }
}
