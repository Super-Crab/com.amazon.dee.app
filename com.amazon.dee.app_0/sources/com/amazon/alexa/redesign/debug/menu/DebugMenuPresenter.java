package com.amazon.alexa.redesign.debug.menu;

import com.amazon.alexa.redesign.debug.menu.DebugMenuContract;
/* loaded from: classes10.dex */
public class DebugMenuPresenter implements DebugMenuContract.Presenter {
    DebugMenuContract.Interactor interactor;
    DebugMenuContract.View view;

    public DebugMenuPresenter(DebugMenuContract.View view, DebugMenuContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.Presenter
    public void onBypassSwitchChanged(boolean z) {
        this.interactor.setBypass(z);
    }

    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.Presenter
    public void onExitClicked() {
        this.view.close();
    }

    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.Presenter
    public void onLocalJSONSwitchChanged(boolean z) {
        this.interactor.setLocalJSON(z);
    }

    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.Presenter
    public void onStart() {
        this.view.bindDebugModel(this.interactor.getDebugModel());
    }
}
