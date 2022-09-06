package com.amazon.alexa.alertsca;

import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.alertsca.dependencies.AccessoryModule;
import com.amazon.alexa.alertsca.dependencies.AlertsEventBusModule;
import com.amazon.alexa.alertsca.dependencies.AlexaServicesModule;
import com.amazon.alexa.alertsca.dependencies.ApplicationModule;
import com.amazon.alexa.alertsca.dependencies.DataModule;
import com.amazon.alexa.alertsca.dependencies.DeviceInformationModule;
import com.amazon.alexa.alertsca.dependencies.ExecutorModule;
import com.amazon.alexa.alertsca.dependencies.GsonModule;
import com.amazon.alexa.alertsca.dependencies.MetricsModule;
import com.amazon.alexa.alertsca.device.AlertsEnabledDevices;
import com.amazon.alexa.alertsca.device.Device;
import com.amazon.alexa.alertsca.events.AlertScheduledEvent;
import com.amazon.alexa.alertsca.events.AlertUnscheduledEvent;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.networking.ConnectivityAuthority;
import com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationManager;
import com.amazon.alexa.alertsca.utils.StringUtils;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import dagger.Component;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class AlertsCapabilityAgentService extends AlexaCapabilityAgentService implements AlexaContextsProvider {
    private static final String ALARMS_ALERTS = "timers";
    private static final int DISABLE_TIMERS_ALARMS = 0;
    private static final String MAXIMUM_ALERTS = "maximumAlerts";
    private static final String OVERALL_ALERTS = "overall";
    private static final String TAG = AlertsCapabilityAgentService.class.getSimpleName();
    private static final String TIMERS_ALERTS = "alarms";
    @Inject
    AlertsCapabilityAgent alertsCapabilityAgent;
    @Inject
    AlertsEventBus alertsEventBus;
    @Inject
    AlexaAlertsNotificationManager alexaAlertsNotificationManager;
    @Inject
    AlexaMobileFrameworkApis alexaMobileFrameworkApis;
    @Inject
    ConnectivityAuthority connectivityAuthority;
    @Inject
    Gson gson;
    @Inject
    MetricsService metricsService;
    private ServiceForegroundWatcher serviceForegroundWatcher;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(modules = {ApplicationModule.class, DataModule.class, ExecutorModule.class, AlertsEventBusModule.class, GsonModule.class, AccessoryModule.class, MetricsModule.class, DeviceInformationModule.class, AlexaServicesModule.class})
    @Singleton
    /* loaded from: classes6.dex */
    public interface Injector {
        void inject(AlertsCapabilityAgentService alertsCapabilityAgentService);
    }

    private void doStopSelf() {
        stopSelf();
    }

    @Nullable
    private static JSONObject getAlertsWithNoTimersOrAlarmsConfig() {
        if (new AlertsEnabledDevices().isAlertsEnabled(Device.create())) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(OVERALL_ALERTS, 0);
            jSONObject2.put("timers", 0);
            jSONObject2.put("alarms", 0);
            jSONObject.put(MAXIMUM_ALERTS, jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void injectServiceDependencies() {
        DaggerAlertsCapabilityAgentService_Injector.builder().applicationModule(new ApplicationModule(this)).build().inject(this);
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public void doUnbind() {
    }

    @Override // com.amazon.alexa.api.AlexaContextsProvider
    public Set<AlexaContext> getAlexaContexts() {
        return Collections.singleton(this.alertsCapabilityAgent.getContext());
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public Set<AlexaCapability> getCapabilities() {
        AlexaCapability createWithConfigurations;
        if (this.alertsCapabilityAgent.areTimersAndAlarmsAvailable()) {
            createWithConfigurations = AlexaCapability.create("Alerts", "1.3");
        } else {
            createWithConfigurations = AlexaCapability.createWithConfigurations("Alerts", "1.3", getAlertsWithNoTimersOrAlarmsConfig());
        }
        HashSet hashSet = new HashSet();
        hashSet.add(createWithConfigurations);
        return hashSet;
    }

    @Subscribe
    public void on(AlertScheduledEvent alertScheduledEvent) {
        this.alexaMobileFrameworkApis.getContextProvider().cacheContexts(getAlexaContexts());
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        injectServiceDependencies();
        this.serviceForegroundWatcher = new ServiceForegroundWatcher(this, this.alexaAlertsNotificationManager, this.alertsEventBus, this.metricsService);
        this.connectivityAuthority.register();
        this.alertsEventBus.register(this);
        this.alexaMobileFrameworkApis.start();
        this.alexaMobileFrameworkApis.getContextProvider().setContextCachingEnabled(true);
        this.alexaMobileFrameworkApis.getContextProvider().registerContextsProvider(this);
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.connectivityAuthority.unregister();
        this.serviceForegroundWatcher.teardown();
        this.alertsCapabilityAgent.teardown();
        this.alexaAlertsNotificationManager.teardown();
        this.alexaMobileFrameworkApis.stop();
        this.alexaMobileFrameworkApis.destroy();
        this.alertsEventBus.unregister(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String str = "onStartCommand flags: " + i + ", startId: " + i2;
        if (intent != null && intent.getAction() != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onStartCommand received action ");
            outline107.append(intent.getAction());
            outline107.toString();
            String action = intent.getAction();
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != -1441850625) {
                if (hashCode == 2078383996 && action.equals("com.amazon.alexa.alertsca.intent.action.LOG_OUT")) {
                    c = 0;
                }
            } else if (action.equals("com.amazon.alexa.alertsca.intent.action.WAKE_UP")) {
                c = 1;
            }
            if (c == 0) {
                this.alertsCapabilityAgent.onLogOut();
                doStopSelf();
            } else if (c != 1) {
                this.alertsCapabilityAgent.handleIntent(intent);
            }
            this.metricsService.recordEvent(MetricsConstants.ALERTS.SERVICE.ACTIONS.element(StringUtils.getLastPart(intent.getAction(), 46)));
            return 2;
        }
        Log.w(TAG, "Intent or action is null, so there is nothing that service can do");
        this.metricsService.recordEvent(MetricsConstants.ALERTS.SERVICE.failure());
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(AlexaDirective alexaDirective) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("process directive: ");
        outline107.append(alexaDirective.getAlexaHeader().getName());
        outline107.toString();
        return this.alertsCapabilityAgent.process(alexaDirective);
    }

    @Subscribe
    public void on(AlertUnscheduledEvent alertUnscheduledEvent) {
        this.alexaMobileFrameworkApis.getContextProvider().cacheContexts(getAlexaContexts());
    }
}
