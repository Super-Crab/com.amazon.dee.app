package com.bugsnag.android;

import com.bugsnag.android.NativeInterface;
import java.util.Observable;
import java.util.Observer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ClientConfigObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J\u001c\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/bugsnag/android/ClientConfigObserver;", "Ljava/util/Observer;", "client", "Lcom/bugsnag/android/Client;", "config", "Lcom/bugsnag/android/Configuration;", "(Lcom/bugsnag/android/Client;Lcom/bugsnag/android/Configuration;)V", "handleNotifyReleaseStages", "", "update", "o", "Ljava/util/Observable;", "arg", "", "bugsnag-android-core_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ClientConfigObserver implements Observer {
    private final Client client;
    private final Configuration config;

    public ClientConfigObserver(@NotNull Client client, @NotNull Configuration config) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(client, "client");
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(config, "config");
        this.client = client;
        this.config = config;
    }

    private final void handleNotifyReleaseStages() {
        Configuration configuration = this.config;
        if (configuration.shouldNotifyForReleaseStage(configuration.getReleaseStage())) {
            if (this.config.getDetectAnrs()) {
                this.client.enableAnrReporting();
            }
            if (!this.config.getDetectNdkCrashes()) {
                return;
            }
            this.client.enableNdkCrashReporting();
            return;
        }
        this.client.disableAnrReporting();
        this.client.disableNdkCrashReporting();
    }

    @Override // java.util.Observer
    public void update(@Nullable Observable observable, @Nullable Object obj) {
        if (obj != null) {
            NativeInterface.MessageType messageType = ((NativeInterface.Message) obj).type;
            if (messageType == NativeInterface.MessageType.UPDATE_NOTIFY_RELEASE_STAGES) {
                handleNotifyReleaseStages();
                return;
            } else if (messageType != NativeInterface.MessageType.UPDATE_RELEASE_STAGE) {
                return;
            } else {
                handleNotifyReleaseStages();
                return;
            }
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bugsnag.android.NativeInterface.Message");
    }
}
