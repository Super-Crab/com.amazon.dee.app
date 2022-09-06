package com.amazon.alexa.accessorykit.finishsetup.presenters;

import com.amazon.alexa.accessorykit.finishsetup.FinishSetupEvent;
/* loaded from: classes6.dex */
public interface FinishSetupPresenter {
    boolean canPresent();

    void dismiss(FinishSetupEvent finishSetupEvent);

    void present(FinishSetupEvent finishSetupEvent);
}
