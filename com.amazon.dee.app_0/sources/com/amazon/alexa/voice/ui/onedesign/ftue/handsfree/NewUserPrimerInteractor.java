package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.NewUserPrimerContract;
/* loaded from: classes11.dex */
public final class NewUserPrimerInteractor extends HandsfreePrimerInteractor implements NewUserPrimerContract.Interactor {
    private final OnPermissionResultReceivedListener listener;
    private final HandsfreePrimerContract.Mediator mediator;

    public NewUserPrimerInteractor(@NonNull HandsfreePrimerContract.Mediator mediator, @NonNull OnPermissionResultReceivedListener onPermissionResultReceivedListener) {
        super(mediator);
        this.listener = onPermissionResultReceivedListener;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.NewUserPrimerContract.Interactor
    public void permissionsResultReceived() {
        this.listener.onPermissionResultReceived();
    }
}
