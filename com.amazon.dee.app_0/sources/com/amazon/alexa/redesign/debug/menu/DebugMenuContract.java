package com.amazon.alexa.redesign.debug.menu;

import com.amazon.alexa.redesign.entity.DebugMenuModel;
/* loaded from: classes10.dex */
public interface DebugMenuContract {

    /* loaded from: classes10.dex */
    public interface Interactor {
        DebugMenuModel getDebugModel();

        void setBypass(boolean z);

        void setLocalJSON(boolean z);
    }

    /* loaded from: classes10.dex */
    public interface Presenter {
        void onBypassSwitchChanged(boolean z);

        void onExitClicked();

        void onLocalJSONSwitchChanged(boolean z);

        void onStart();
    }

    /* loaded from: classes10.dex */
    public interface View {
        void bindDebugModel(DebugMenuModel debugMenuModel);

        void close();
    }
}
