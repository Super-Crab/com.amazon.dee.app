package com.amazon.dee.app.services.toolbar;

import androidx.annotation.Nullable;
import java.util.Map;
/* loaded from: classes12.dex */
class ToolbarOptions {
    private Map<String, String> viewOptions;
    private VisibilityOptions visibilityOptions;

    ToolbarOptions() {
    }

    @Nullable
    public Map<String, String> getViewOptions() {
        return this.viewOptions;
    }

    @Nullable
    public VisibilityOptions getVisibilityOptions() {
        return this.visibilityOptions;
    }
}
