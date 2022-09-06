package com.amazon.alexa.accessorykit.finishsetup;
/* loaded from: classes6.dex */
public interface FinishSetupViewCoordinator {
    void activate();

    void deactivate();

    void dismiss(FinishSetupEvent finishSetupEvent);

    void dismissAll();

    void present(FinishSetupEvent finishSetupEvent);
}
