package com.amazon.alexa.voice.routing;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.voice.dagger.VoiceDependencies;
import com.amazon.alexa.voice.ftue.VoiceFtueActivity;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class VoiceRoutingAdapter implements RoutingAdapter {
    private static final String EXTRA_FRAGMENT_ARG_KEY = ":settings:fragment_args_key";
    private static final String EXTRA_SHOW_FRAGMENT_ARGS = ":settings:show_fragment_args";
    public static final String FTUE_STARTED_METRIC = "Ftue started:";
    private static final String PREFERNCE_KEY_DEFAULT_ASSISTANT = "default_assist";
    @VisibleForTesting
    static final String RAW_QUERY_STRING_KEY = "rawQueryString";
    private static final String TAG = "VoiceRoutingAdapter";
    private final Activity activity;
    private final SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new SimpleArrayMap<>();
    @Inject
    MetricsBridge metricsBridge;
    @Inject
    TypeToAlexaFeatureEnabler ttaFeatureEnabler;
    @Inject
    VoiceService voiceService;
    @Inject
    VoxMetricEventProcessingService voxMetricEventProcessingService;

    public VoiceRoutingAdapter(Activity activity) {
        this.activity = activity;
        this.configurations.put("voice-default-assistant", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("voice-ftue", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("voice", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("type-to-alexa", RoutingAdapter.RouteConfiguration.all());
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return this.configurations.get(route.getName());
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return 5;
    }

    @VisibleForTesting
    RouteToTypeAction getRouteToTypeAction() {
        return new RouteToTypeAction(this.ttaFeatureEnabler);
    }

    @VisibleForTesting
    RouteToVoiceAction getRouteToVoiceAction() {
        return new RouteToVoiceAction(this.voiceService, this.voxMetricEventProcessingService, this.ttaFeatureEnabler);
    }

    @VisibleForTesting
    void injectDependencies() {
        VoiceDependencies.inject(this);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        Route route = routeContext.getRoute();
        injectDependencies();
        if (route.is("voice-default-assistant")) {
            ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.Settings$ManageAssistActivity");
            try {
                this.activity.startActivity(new Intent("android.settings.SETTINGS").setComponent(componentName).addFlags(268435456).putExtra(EXTRA_FRAGMENT_ARG_KEY, PREFERNCE_KEY_DEFAULT_ASSISTANT).putExtra(EXTRA_SHOW_FRAGMENT_ARGS, GeneratedOutlineSupport1.outline11(EXTRA_FRAGMENT_ARG_KEY, PREFERNCE_KEY_DEFAULT_ASSISTANT)));
                runnable.run();
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, "Failed to open android settings app for voice assistant", e);
            }
        } else if (route.is("voice-ftue")) {
            Log.i(TAG, "Starting voice ftue activity");
            VoiceFtueActivity.launchVoiceFtue(this.activity, VoiceMetricsConstants.COMPONENT_VOICE_SETTINGS, routeContext.getString("context"));
            if (this.metricsBridge != null && routeContext.getString("referrer") != null) {
                MetricsBridge metricsBridge = this.metricsBridge;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FTUE_STARTED_METRIC);
                outline107.append(routeContext.getString("referrer"));
                metricsBridge.reportEvent(outline107.toString());
            }
            runnable.run();
        } else if (route.is("voice")) {
            getRouteToVoiceAction().startVoiceExperience(this.activity, routeContext);
            runnable.run();
        } else if (!route.is("type-to-alexa") || !getRouteToTypeAction().startTypeExperience(this.activity, routeContext)) {
        } else {
            runnable.run();
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
    }
}
