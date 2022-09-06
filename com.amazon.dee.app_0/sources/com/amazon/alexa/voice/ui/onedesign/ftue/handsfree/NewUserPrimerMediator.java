package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.ftue.PermissionsDelegate;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class NewUserPrimerMediator extends HandsfreePrimerMediator {
    private final String[] allPermissions;
    private PermissionsDelegate requester;

    public NewUserPrimerMediator(@NonNull ViewController viewController, @NonNull PermissionsDelegate permissionsDelegate, @NonNull String[] strArr) {
        super(viewController);
        this.requester = permissionsDelegate;
        this.allPermissions = (String[]) strArr.clone();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Mediator
    public void requestPermissions() {
        this.requester.requestPermissions((String[]) this.allPermissions.clone());
    }
}
