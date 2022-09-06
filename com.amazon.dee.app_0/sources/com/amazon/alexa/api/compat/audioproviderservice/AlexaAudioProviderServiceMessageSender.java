package com.amazon.alexa.api.compat.audioproviderservice;

import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.compat.resultcallback.ResultCallbacks;
import com.amazon.alexa.api.compat.resultcallback.ResultCallbacksAdapter;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaAudioProviderServiceMessageSender.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00132\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/amazon/alexa/api/compat/audioproviderservice/AlexaAudioProviderServiceMessageSender;", "", "binder", "Landroid/os/IBinder;", "messageReceiversManager", "Lcom/amazon/alexa/api/messages/MessageReceiversManager;", "(Landroid/os/IBinder;Lcom/amazon/alexa/api/messages/MessageReceiversManager;)V", "<set-?>", "Lcom/amazon/alexa/api/AlexaAudioProviderServiceMessageSender;", "messageSender", "getMessageSender$AlexaMobileAndroidVoiceSDKApiCompat_release", "()Lcom/amazon/alexa/api/AlexaAudioProviderServiceMessageSender;", "setMessageSender$AlexaMobileAndroidVoiceSDKApiCompat_release", "(Lcom/amazon/alexa/api/AlexaAudioProviderServiceMessageSender;)V", "getLocale", "", "client", "Lcom/amazon/alexa/api/ExtendedClient;", "getSupportedLocales", "", "isWakeWordRecognitionEnabled", "", "release", "", "setLocale", "locale", "resultCallbacks", "Lcom/amazon/alexa/api/compat/resultcallback/ResultCallbacks;", "setWakeWordConfidenceThreshold", "threshold", "", "setWakeWordRecognitionEnabled", "enabled", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaAudioProviderServiceMessageSender {
    private final IBinder binder;
    private final MessageReceiversManager messageReceiversManager;
    @NotNull
    private com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender messageSender;

    public AlexaAudioProviderServiceMessageSender(@NotNull IBinder binder, @NotNull MessageReceiversManager messageReceiversManager) {
        Intrinsics.checkParameterIsNotNull(binder, "binder");
        Intrinsics.checkParameterIsNotNull(messageReceiversManager, "messageReceiversManager");
        this.binder = binder;
        this.messageReceiversManager = messageReceiversManager;
        this.messageSender = new com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender(this.binder, this.messageReceiversManager);
    }

    @NotNull
    public final String getLocale(@NotNull ExtendedClient client) throws RemoteException {
        Intrinsics.checkParameterIsNotNull(client, "client");
        String locale = this.messageSender.getLocale(client);
        Intrinsics.checkExpressionValueIsNotNull(locale, "messageSender.getLocale(client)");
        return locale;
    }

    @NotNull
    public final com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender getMessageSender$AlexaMobileAndroidVoiceSDKApiCompat_release() {
        return this.messageSender;
    }

    @NotNull
    public final List<String> getSupportedLocales(@NotNull ExtendedClient client) throws RemoteException {
        Intrinsics.checkParameterIsNotNull(client, "client");
        List<String> supportedLocales = this.messageSender.getSupportedLocales(client);
        Intrinsics.checkExpressionValueIsNotNull(supportedLocales, "messageSender.getSupportedLocales(client)");
        return supportedLocales;
    }

    public final boolean isWakeWordRecognitionEnabled(@NotNull ExtendedClient client) throws RemoteException {
        Intrinsics.checkParameterIsNotNull(client, "client");
        return this.messageSender.isWakeWordRecognitionEnabled(client);
    }

    public final void release() {
        this.messageSender.release();
    }

    public final void setLocale(@NotNull ExtendedClient client, @NotNull String locale, @NotNull ResultCallbacks resultCallbacks) throws RemoteException {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(resultCallbacks, "resultCallbacks");
        this.messageSender.setLocale(client, locale, new ResultCallbacksAdapter(resultCallbacks));
    }

    public final /* synthetic */ void setMessageSender$AlexaMobileAndroidVoiceSDKApiCompat_release(@NotNull com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender alexaAudioProviderServiceMessageSender) {
        Intrinsics.checkParameterIsNotNull(alexaAudioProviderServiceMessageSender, "<set-?>");
        this.messageSender = alexaAudioProviderServiceMessageSender;
    }

    public final void setWakeWordConfidenceThreshold(@NotNull ExtendedClient client, int i, @NotNull ResultCallbacks resultCallbacks) throws RemoteException {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(resultCallbacks, "resultCallbacks");
        this.messageSender.setWakeWordConfidenceThreshold(client, i, new ResultCallbacksAdapter(resultCallbacks));
    }

    public final void setWakeWordRecognitionEnabled(@NotNull ExtendedClient client, boolean z, @NotNull ResultCallbacks resultCallbacks) throws RemoteException {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(resultCallbacks, "resultCallbacks");
        this.messageSender.setWakeWordRecognitionEnabled(client, z, new ResultCallbacksAdapter(resultCallbacks));
    }
}
