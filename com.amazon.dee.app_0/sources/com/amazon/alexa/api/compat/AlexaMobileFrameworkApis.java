package com.amazon.alexa.api.compat;

import android.content.Context;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaMobileFrameworkApis.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 J2\u00020\u0001:\u0001JB\u000f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010F\u001a\u00020GJ\u0006\u0010H\u001a\u00020GJ\u0006\u0010I\u001a\u00020GR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010.\u001a\u00020/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u00102\u001a\u000203¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u000207¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010:\u001a\u00020;¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0011\u0010>\u001a\u00020?¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0011\u0010B\u001a\u00020C¢\u0006\b\n\u0000\u001a\u0004\bD\u0010E¨\u0006K"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaMobileFrameworkApis;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "debugTag", "", "(Landroid/content/Context;Ljava/lang/String;)V", "alerts", "Lcom/amazon/alexa/api/compat/AlertsApi;", "getAlerts", "()Lcom/amazon/alexa/api/compat/AlertsApi;", "attentionSystem", "Lcom/amazon/alexa/api/compat/AttentionSystemApi;", "getAttentionSystem", "()Lcom/amazon/alexa/api/compat/AttentionSystemApi;", "audioPlaybackControl", "Lcom/amazon/alexa/api/compat/AudioPlaybackControlApi;", "getAudioPlaybackControl", "()Lcom/amazon/alexa/api/compat/AudioPlaybackControlApi;", "audioTaskScheduler", "Lcom/amazon/alexa/api/compat/AudioTaskSchedulerApi;", "getAudioTaskScheduler", "()Lcom/amazon/alexa/api/compat/AudioTaskSchedulerApi;", "cards", "Lcom/amazon/alexa/api/compat/CardsApi;", "getCards", "()Lcom/amazon/alexa/api/compat/CardsApi;", "contextProvider", "Lcom/amazon/alexa/api/compat/ContextProviderApi;", "getContextProvider", "()Lcom/amazon/alexa/api/compat/ContextProviderApi;", "dialog", "Lcom/amazon/alexa/api/compat/DialogApi;", "getDialog", "()Lcom/amazon/alexa/api/compat/DialogApi;", "eventSender", "Lcom/amazon/alexa/api/compat/EventSenderApi;", "getEventSender", "()Lcom/amazon/alexa/api/compat/EventSenderApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "locale", "Lcom/amazon/alexa/api/compat/LocaleApi;", "getLocale", "()Lcom/amazon/alexa/api/compat/LocaleApi;", "metrics", "Lcom/amazon/alexa/api/compat/MetricsApi;", "getMetrics", "()Lcom/amazon/alexa/api/compat/MetricsApi;", "readiness", "Lcom/amazon/alexa/api/compat/ReadinessApi;", "getReadiness", "()Lcom/amazon/alexa/api/compat/ReadinessApi;", "text", "Lcom/amazon/alexa/api/compat/TextApi;", "getText", "()Lcom/amazon/alexa/api/compat/TextApi;", "userSpeechProviders", "Lcom/amazon/alexa/api/compat/UserSpeechProvidersApi;", "getUserSpeechProviders", "()Lcom/amazon/alexa/api/compat/UserSpeechProvidersApi;", "visualTask", "Lcom/amazon/alexa/api/compat/VisualTaskApi;", "getVisualTask", "()Lcom/amazon/alexa/api/compat/VisualTaskApi;", AlexaMetricsConstants.EventConstants.WAKE_WORD, "Lcom/amazon/alexa/api/compat/WakeWordApi;", "getWakeWord", "()Lcom/amazon/alexa/api/compat/WakeWordApi;", "destroy", "", "start", "stop", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaMobileFrameworkApis {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final AlertsApi alerts;
    @NotNull
    private final AttentionSystemApi attentionSystem;
    @NotNull
    private final AudioPlaybackControlApi audioPlaybackControl;
    @NotNull
    private final AudioTaskSchedulerApi audioTaskScheduler;
    @NotNull
    private final CardsApi cards;
    @NotNull
    private final ContextProviderApi contextProvider;
    @NotNull
    private final DialogApi dialog;
    @NotNull
    private final EventSenderApi eventSender;
    private final com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis;
    @NotNull
    private final LocaleApi locale;
    @NotNull
    private final MetricsApi metrics;
    @NotNull
    private final ReadinessApi readiness;
    @NotNull
    private final TextApi text;
    @NotNull
    private final UserSpeechProvidersApi userSpeechProviders;
    @NotNull
    private final VisualTaskApi visualTask;
    @NotNull
    private final WakeWordApi wakeWord;

    /* compiled from: AlexaMobileFrameworkApis.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\b\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaMobileFrameworkApis$Companion;", "", "()V", "disableAlexa", "", "context", "Landroid/content/Context;", "enableAlexa", "isAlexaEnabled", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void disableAlexa(@Nullable Context context) {
            com.amazon.alexa.api.AlexaMobileFrameworkApis.disableAlexa(context);
        }

        @JvmStatic
        public final void enableAlexa(@Nullable Context context) {
            com.amazon.alexa.api.AlexaMobileFrameworkApis.enableAlexa(context);
        }

        @JvmStatic
        public final boolean isAlexaEnabled(@Nullable Context context) {
            return com.amazon.alexa.api.AlexaMobileFrameworkApis.isAlexaEnabled(context);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AlexaMobileFrameworkApis(@NotNull Context context, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.frameworkApis = new com.amazon.alexa.api.AlexaMobileFrameworkApis(context, str);
        this.audioPlaybackControl = new AudioPlaybackControlSubcomponent(this.frameworkApis);
        this.dialog = new DialogSubcomponent(this.frameworkApis);
        this.wakeWord = new WakeWordSubcomponent(this.frameworkApis);
        this.attentionSystem = new AttentionSystemSubcomponent(this.frameworkApis);
        this.readiness = new ReadinessSubcomponent(this.frameworkApis);
        this.cards = new CardsSubcomponent(this.frameworkApis);
        this.contextProvider = new ContextProviderSubcomponent(this.frameworkApis);
        this.eventSender = new EventSenderSubcomponent(this.frameworkApis);
        this.audioTaskScheduler = new AudioTaskSchedulerSubcomponent(this.frameworkApis);
        this.metrics = new MetricsSubcomponent(this.frameworkApis);
        this.locale = new LocaleSubcomponent(this.frameworkApis);
        this.userSpeechProviders = new UserSpeechProvidersSubcomponent(this.frameworkApis);
        this.alerts = new AlertsSubcomponent(this.frameworkApis);
        this.text = new TextSubcomponent(this.frameworkApis);
        this.visualTask = new VisualTaskSubcomponent(this.frameworkApis);
    }

    @JvmStatic
    public static final void disableAlexa(@Nullable Context context) {
        Companion.disableAlexa(context);
    }

    @JvmStatic
    public static final void enableAlexa(@Nullable Context context) {
        Companion.enableAlexa(context);
    }

    @JvmStatic
    public static final boolean isAlexaEnabled(@Nullable Context context) {
        return Companion.isAlexaEnabled(context);
    }

    public final void destroy() {
        this.frameworkApis.destroy();
    }

    @NotNull
    public final AlertsApi getAlerts() {
        return this.alerts;
    }

    @NotNull
    public final AttentionSystemApi getAttentionSystem() {
        return this.attentionSystem;
    }

    @NotNull
    public final AudioPlaybackControlApi getAudioPlaybackControl() {
        return this.audioPlaybackControl;
    }

    @NotNull
    public final AudioTaskSchedulerApi getAudioTaskScheduler() {
        return this.audioTaskScheduler;
    }

    @NotNull
    public final CardsApi getCards() {
        return this.cards;
    }

    @NotNull
    public final ContextProviderApi getContextProvider() {
        return this.contextProvider;
    }

    @NotNull
    public final DialogApi getDialog() {
        return this.dialog;
    }

    @NotNull
    public final EventSenderApi getEventSender() {
        return this.eventSender;
    }

    @NotNull
    public final LocaleApi getLocale() {
        return this.locale;
    }

    @NotNull
    public final MetricsApi getMetrics() {
        return this.metrics;
    }

    @NotNull
    public final ReadinessApi getReadiness() {
        return this.readiness;
    }

    @NotNull
    public final TextApi getText() {
        return this.text;
    }

    @NotNull
    public final UserSpeechProvidersApi getUserSpeechProviders() {
        return this.userSpeechProviders;
    }

    @NotNull
    public final VisualTaskApi getVisualTask() {
        return this.visualTask;
    }

    @NotNull
    public final WakeWordApi getWakeWord() {
        return this.wakeWord;
    }

    public final void start() {
        this.frameworkApis.start();
    }

    public final void stop() {
        this.frameworkApis.stop();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Use the constructor with the debugTag argument")
    public AlexaMobileFrameworkApis(@NotNull Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }
}
