package com.amazon.deecomms.common.ui.util;

import android.os.SystemClock;
import android.view.View;
/* loaded from: classes12.dex */
public abstract class OnSingleClickListener implements View.OnClickListener {
    private static final long DEFAULT_CLICK_INTERVAL = 600;
    private final long mClickInterval;
    private volatile long mLastClickTime;

    public OnSingleClickListener(long j) {
        this.mLastClickTime = 0L;
        this.mClickInterval = j;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.mLastClickTime >= this.mClickInterval) {
            onSingleClick(view);
            this.mLastClickTime = elapsedRealtime;
        }
    }

    public abstract void onSingleClick(View view);

    public OnSingleClickListener() {
        this(DEFAULT_CLICK_INTERVAL);
    }
}
