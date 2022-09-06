package com.amazon.alexa.handsfree.settings.handsfreesettings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.metrics.NotificationMetricReporter;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
/* loaded from: classes8.dex */
public class AlexaSettingsLauncherActivity extends Activity {
    private final Initializer mInitializer;

    public AlexaSettingsLauncherActivity() {
        this(InitializerProvider.getInitializer());
    }

    void launchIntent(@NonNull Context context, @NonNull Intent intent) {
        context.startActivity(new Intent(context, AlexaSettingsActivity.class));
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mInitializer.initialize(this);
        new NotificationMetricReporter(this).reportNotificationClickMetric(getIntent());
        launchIntent(this, getIntent());
        finish();
    }

    public AlexaSettingsLauncherActivity(@NonNull Initializer initializer) {
        this.mInitializer = initializer;
    }
}
