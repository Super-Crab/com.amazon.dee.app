package com.amazon.alexa.navigation.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public class ActivityMenuItem extends MenuItem {
    private static final String TAG = "ActivityMenuItem";
    private final Map<String, String> activityIntentExtras;
    private final String activityName;
    private Context context;
    @VisibleForTesting
    Map<String, Object> customEntries;
    @VisibleForTesting
    Intent intent;
    private MetricsComponents metricsComponents;
    private Provider<Mobilytics> mobilyticsProvider;

    public ActivityMenuItem(Context context, int i, int i2, @NonNull String str, @Nullable Map<String, String> map, boolean z, @NonNull MetricsComponents metricsComponents, @NonNull TestId testId, Provider<Mobilytics> provider) {
        super(i, i2, z, testId);
        this.context = context;
        this.activityName = str;
        this.activityIntentExtras = map;
        this.intent = new Intent();
        this.mobilyticsProvider = provider;
        this.metricsComponents = metricsComponents;
        this.customEntries = new HashMap();
        this.customEntries.put("subComponent", metricsComponents.subComponent);
    }

    @Override // com.amazon.alexa.navigation.menu.MenuItem
    public int getMenuItemLayout() {
        return R.layout.navigation_menu_item;
    }

    @Override // com.amazon.alexa.navigation.menu.MenuItem
    public void onClick(View view) {
        try {
            this.intent.setClass(this.context, Class.forName(this.activityName));
            if (this.activityIntentExtras != null) {
                for (Map.Entry<String, String> entry : this.activityIntentExtras.entrySet()) {
                    this.intent.putExtra(entry.getKey(), entry.getValue());
                }
            }
            try {
                this.context.startActivity(this.intent);
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, e.toString());
                this.mobilyticsProvider.mo10268get().recordOperationalEvent(this.mobilyticsProvider.mo10268get().createOperationalEvent("Error.MORE_ERROR_ACTIVITY", "error", "RightMenu", "subComponent", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
            }
        } catch (ClassNotFoundException e2) {
            Log.e(TAG, e2.toString());
            this.mobilyticsProvider.mo10268get().recordOperationalEvent(this.mobilyticsProvider.mo10268get().createOperationalEvent("Error.MORE_ERROR_CLASS", "error", "RightMenu", "subComponent", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        }
        this.mobilyticsProvider.mo10268get().recordUserInteractionEvent(this.mobilyticsProvider.mo10268get().createUserInteractionEvent(this.metricsComponents.metricName, "click", "RightMenu", "subComponent", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
    }
}
