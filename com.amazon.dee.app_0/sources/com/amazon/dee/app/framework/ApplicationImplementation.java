package com.amazon.dee.app.framework;

import android.app.Application;
import android.content.res.Configuration;
import com.amazon.dee.app.dependencies.ApplicationComponent;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public interface ApplicationImplementation extends Application.ActivityLifecycleCallbacks {
    ApplicationComponent getComponent();

    boolean isColdStart();

    boolean isNoActivityVisible();

    boolean isSingleSignOnBuild();

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Application application);

    void onTrimMemory(int i);

    void reportMemoryStats();
}
