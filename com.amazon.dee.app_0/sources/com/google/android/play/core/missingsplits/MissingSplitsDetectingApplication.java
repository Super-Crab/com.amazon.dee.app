package com.google.android.play.core.missingsplits;

import android.app.Application;
/* compiled from: com.google.android.play:core@@1.10.3 */
@Deprecated
/* loaded from: classes2.dex */
public class MissingSplitsDetectingApplication extends Application {
    private boolean onCreateCalled = false;

    @Override // android.app.Application
    public final void onCreate() {
        if (!this.onCreateCalled) {
            this.onCreateCalled = true;
            if (MissingSplitsManagerFactory.create(this).disableAppIfMissingRequiredSplits()) {
                return;
            }
            super.onCreate();
            onCreateCustom();
            return;
        }
        throw new IllegalStateException("The onCreate method must be invoked at most once.");
    }

    @Deprecated
    public void onCreateCustom() {
    }
}
