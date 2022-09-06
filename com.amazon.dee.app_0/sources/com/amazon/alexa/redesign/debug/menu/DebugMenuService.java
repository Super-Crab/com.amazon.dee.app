package com.amazon.alexa.redesign.debug.menu;

import com.amazon.alexa.redesign.entity.DebugMenuModel;
/* loaded from: classes10.dex */
public class DebugMenuService {
    private static DebugMenuService self;
    private DebugMenuModel model;

    public static DebugMenuService getInstance() {
        if (self == null) {
            self = new DebugMenuService();
            self.model = new DebugMenuModel();
        }
        return self;
    }

    public DebugMenuModel getModel() {
        return this.model;
    }

    public void setBypass(boolean z) {
        this.model.setBypass(z);
    }

    public void setLocalJSON(boolean z) {
        this.model.setLocalJSON(z);
    }
}
