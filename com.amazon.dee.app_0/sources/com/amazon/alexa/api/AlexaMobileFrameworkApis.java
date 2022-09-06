package com.amazon.alexa.api;

import android.content.Context;
import com.amazon.alexa.api.AlexaMobileFrameworkApisSpecification;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes6.dex */
public class AlexaMobileFrameworkApis {
    private final f alertsSubcomponent;
    private final AlexaMobileFrameworkApisSpecification apiSpecification;
    private final m attentionSystemSubcomponent;
    private final s audioTaskSchedulerSubcomponent;
    private final z capabilitiesSubcomponent;
    private final ae capabilityAgentsSubcomponent;
    private final ah cardsSubcomponent;
    private final am dialogSubcomponent;
    private final av localeSubcomponent;
    private final ba mediaPlaybackControllerSubcomponent;
    private final bf metricsSubcomponent;
    private final bk readinessSubcomponent;
    private final bu systemSubcomponent;
    private final bw textSubcomponent;
    private final by userSpeechProvidersSubcomponent;
    private final ce visualTaskSubcomponent;
    private final cg wakeWordSubcomponent;

    @Deprecated
    public AlexaMobileFrameworkApis(Context context) {
        this(context, null);
    }

    public AlexaMobileFrameworkApis(Context context, @Nullable String str) {
        AlexaServicesConnection alexaServicesConnection = new AlexaServicesConnection(context.getApplicationContext(), str);
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.systemSubcomponent = new bu(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.dialogSubcomponent = new am(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.wakeWordSubcomponent = new cg(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.attentionSystemSubcomponent = new m(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.readinessSubcomponent = new bk(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.cardsSubcomponent = new ah(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.capabilitiesSubcomponent = new z(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.audioTaskSchedulerSubcomponent = new s(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.metricsSubcomponent = new bf(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.localeSubcomponent = new av(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.userSpeechProvidersSubcomponent = new by(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.alertsSubcomponent = new f(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.textSubcomponent = new bw(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.visualTaskSubcomponent = new ce(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.mediaPlaybackControllerSubcomponent = new ba(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        this.capabilityAgentsSubcomponent = new ae(alexaServicesConnection, atomicBoolean, concurrentLinkedQueue);
        AlexaMobileFrameworkApisSpecification.Subcomponent[] subcomponentArr = {this.systemSubcomponent, this.dialogSubcomponent, this.wakeWordSubcomponent, this.attentionSystemSubcomponent, this.readinessSubcomponent, this.cardsSubcomponent, this.capabilitiesSubcomponent, this.audioTaskSchedulerSubcomponent, this.metricsSubcomponent, this.localeSubcomponent, this.userSpeechProvidersSubcomponent, this.alertsSubcomponent, this.visualTaskSubcomponent, this.textSubcomponent, this.mediaPlaybackControllerSubcomponent, this.capabilityAgentsSubcomponent};
        this.apiSpecification = new AlexaMobileFrameworkApisSpecification(alexaServicesConnection, atomicBoolean, subcomponentArr, new AlexaMobileFrameworkApisSpecification.ConnectionListener(subcomponentArr, concurrentLinkedQueue, atomicBoolean, alexaServicesConnection));
    }

    public static void disableAlexa(Context context) {
        AlexaMobileFrameworkApisSpecification.disableAlexa(context);
    }

    public static void enableAlexa(Context context) {
        AlexaMobileFrameworkApisSpecification.enableAlexa(context);
    }

    public static boolean isAlexaEnabled(Context context) {
        return AlexaMobileFrameworkApisSpecification.isAlexaEnabled(context);
    }

    public void destroy() {
        this.apiSpecification.destroy();
    }

    public AlertsApi getAlerts() {
        return this.alertsSubcomponent;
    }

    public AttentionSystemApi getAttentionSystem() {
        return this.attentionSystemSubcomponent;
    }

    public AudioTaskSchedulerApi getAudioTaskScheduler() {
        return this.audioTaskSchedulerSubcomponent;
    }

    public CapabilitiesApi getCapabilities() {
        return this.capabilitiesSubcomponent;
    }

    public ac getCapabilityAgentsApi() {
        return this.capabilityAgentsSubcomponent;
    }

    public CardsApi getCards() {
        return this.cardsSubcomponent;
    }

    public DialogApi getDialog() {
        return this.dialogSubcomponent;
    }

    public LocaleApi getLocale() {
        return this.localeSubcomponent;
    }

    public MediaPlaybackControllerApi getMediaPlaybackController() {
        return this.mediaPlaybackControllerSubcomponent;
    }

    public MetricsApi getMetrics() {
        return this.metricsSubcomponent;
    }

    public ReadinessApi getReadiness() {
        return this.readinessSubcomponent;
    }

    public SystemApi getSystem() {
        return this.systemSubcomponent;
    }

    public TextApi getText() {
        return this.textSubcomponent;
    }

    public UserSpeechProvidersApi getUserSpeechProviders() {
        return this.userSpeechProvidersSubcomponent;
    }

    public VisualTaskApi getVisualTask() {
        return this.visualTaskSubcomponent;
    }

    public WakeWordApi getWakeWord() {
        return this.wakeWordSubcomponent;
    }

    public boolean isStarted() {
        return this.apiSpecification.isStarted();
    }

    public void start() {
        this.apiSpecification.start();
    }

    public void stop() {
        this.apiSpecification.stop();
    }
}
