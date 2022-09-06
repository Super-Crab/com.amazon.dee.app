package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.compat.alerts.AlertsListener;
import com.amazon.alexa.api.compat.alerts.AlertsListenerAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlertsSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000b2\u00020\u00012\u00020\u0002:\u0001\u000bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/api/compat/AlertsSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/AlertsApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "deregisterListener", "", "alertsListener", "Lcom/amazon/alexa/api/compat/alerts/AlertsListener;", "registerListener", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlertsSubcomponent extends Subcomponent implements AlertsApi {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Map<AlertsListener, AlertsListenerAdapter> listeners = new LinkedHashMap();

    /* compiled from: AlertsSubcomponent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/api/compat/AlertsSubcomponent$Companion;", "", "()V", "listeners", "", "Lcom/amazon/alexa/api/compat/alerts/AlertsListener;", "Lcom/amazon/alexa/api/compat/alerts/AlertsListenerAdapter;", "getListeners", "()Ljava/util/Map;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Map<AlertsListener, AlertsListenerAdapter> getListeners() {
            return AlertsSubcomponent.listeners;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlertsSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.AlertsApi
    public void deregisterListener(@NotNull AlertsListener alertsListener) {
        Intrinsics.checkParameterIsNotNull(alertsListener, "alertsListener");
        AlertsListenerAdapter remove = listeners.remove(alertsListener);
        if (remove != null) {
            getFrameworkApis().getAlerts().deregisterListener(remove);
        }
    }

    @Override // com.amazon.alexa.api.compat.AlertsApi
    public void registerListener(@NotNull AlertsListener alertsListener) {
        Intrinsics.checkParameterIsNotNull(alertsListener, "alertsListener");
        AlertsListenerAdapter alertsListenerAdapter = new AlertsListenerAdapter(alertsListener);
        listeners.put(alertsListener, alertsListenerAdapter);
        getFrameworkApis().getAlerts().registerListener(alertsListenerAdapter);
    }
}
