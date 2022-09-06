package com.amazon.alexa.redesign.debug.menu;

import com.amazon.alexa.redesign.debug.menu.DebugMenuContract;
import com.amazon.alexa.redesign.entity.DebugMenuModel;
/* loaded from: classes10.dex */
public class DebugMenuInteractor implements DebugMenuContract.Interactor {
    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.Interactor
    public DebugMenuModel getDebugModel() {
        return DebugMenuService.getInstance().getModel();
    }

    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.Interactor
    public void setBypass(boolean z) {
        DebugMenuService.getInstance().setBypass(z);
    }

    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.Interactor
    public void setLocalJSON(boolean z) {
        DebugMenuService.getInstance().setLocalJSON(z);
    }
}
