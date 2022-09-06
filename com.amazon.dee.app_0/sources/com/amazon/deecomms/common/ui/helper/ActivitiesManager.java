package com.amazon.deecomms.common.ui.helper;

import android.app.Activity;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class ActivitiesManager {
    private Activity topActivity;

    @Nullable
    public synchronized Activity getTopActivity() {
        return this.topActivity;
    }

    public synchronized void setTopActivity(@Nullable Activity activity) {
        this.topActivity = activity;
    }
}
