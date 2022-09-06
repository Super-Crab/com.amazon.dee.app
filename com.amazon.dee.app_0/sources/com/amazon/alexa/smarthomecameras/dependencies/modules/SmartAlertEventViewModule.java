package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.app.Activity;
import android.content.Context;
import com.amazon.alexa.smarthomecameras.view.SmartAlertEventView;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes10.dex */
public class SmartAlertEventViewModule {
    private Activity activity;

    public SmartAlertEventViewModule(Activity activity) {
        Preconditions.checkNotNull(activity, "Activity cannot be null");
        this.activity = activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public SmartAlertEventView providesSmartAlertEventView(Context context, String str) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(str, "imageUrl cannot be null");
        return new SmartAlertEventView(this.activity, context, str);
    }
}
