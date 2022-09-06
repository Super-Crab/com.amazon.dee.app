package com.bugsnag.android;

import android.os.Handler;
import android.os.Looper;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.bugsnag.android.Error;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnrPlugin.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\r\u001a\u00020\u000eH\u0082 J\u0011\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\bH\u0082 J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/bugsnag/android/AnrPlugin;", "Lcom/bugsnag/android/BugsnagPlugin;", "()V", "client", "Lcom/bugsnag/android/Client;", "collector", "Lcom/bugsnag/android/AnrDetailsCollector;", "loaded", "", "getLoaded", "()Z", "setLoaded", "(Z)V", "disableAnrReporting", "", "enableAnrReporting", "callPreviousSigquitHandler", "loadPlugin", "notifyAnrDetected", "unloadPlugin", "Companion", "bugsnag-plugin-android-anr_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AnrPlugin implements BugsnagPlugin {
    public static final Companion Companion = new Companion(null);
    private Client client;
    private final AnrDetailsCollector collector = new AnrDetailsCollector();
    private boolean loaded;

    /* compiled from: AnrPlugin.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/bugsnag/android/AnrPlugin$Companion;", "", "()V", "bugsnag-plugin-android-anr_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        System.loadLibrary("bugsnag-plugin-android-anr");
    }

    public static final /* synthetic */ Client access$getClient$p(AnrPlugin anrPlugin) {
        Client client = anrPlugin.client;
        if (client == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("client");
        }
        return client;
    }

    private final native void disableAnrReporting();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void enableAnrReporting(boolean z);

    private final void notifyAnrDetected() {
        Looper mainLooper = Looper.getMainLooper();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(mainLooper, "Looper.getMainLooper()");
        Thread thread = mainLooper.getThread();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(thread, "thread");
        BugsnagException bugsnagException = new BugsnagException(CrashManagerActions.ANR, "Application did not respond to UI input", thread.getStackTrace());
        Client client = this.client;
        if (client == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("client");
        }
        Configuration configuration = client.config;
        Client client2 = this.client;
        if (client2 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("client");
        }
        Error error = new Error.Builder(configuration, bugsnagException, client2.sessionTracker, thread, true).severity(Severity.ERROR).severityReasonType("anrError").build();
        AnrDetailsCollector anrDetailsCollector = this.collector;
        Client client3 = this.client;
        if (client3 == null) {
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("client");
        }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(error, "error");
        anrDetailsCollector.collectAnrErrorDetails$bugsnag_plugin_android_anr_release(client3, error);
    }

    @Override // com.bugsnag.android.BugsnagPlugin
    public boolean getLoaded() {
        return this.loaded;
    }

    @Override // com.bugsnag.android.BugsnagPlugin
    public void loadPlugin(@NotNull final Client client) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(client, "client");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bugsnag.android.AnrPlugin$loadPlugin$1
            @Override // java.lang.Runnable
            public final void run() {
                AnrPlugin.this.client = client;
                AnrPlugin anrPlugin = AnrPlugin.this;
                Configuration configuration = client.config;
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(configuration, "client.config");
                anrPlugin.enableAnrReporting(configuration.getCallPreviousSigquitHandler());
                Logger.warn("Initialised ANR Plugin");
            }
        });
    }

    @Override // com.bugsnag.android.BugsnagPlugin
    public void setLoaded(boolean z) {
        this.loaded = z;
    }

    @Override // com.bugsnag.android.BugsnagPlugin
    public void unloadPlugin() {
        disableAnrReporting();
    }
}
