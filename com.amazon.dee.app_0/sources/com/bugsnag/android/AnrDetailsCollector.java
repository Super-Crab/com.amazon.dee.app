package com.bugsnag.android;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import androidx.annotation.VisibleForTesting;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AnrDetailsCollector.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ\u001f\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u0011J\u0017\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J\u001d\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/bugsnag/android/AnrDetailsCollector;", "", "()V", "handlerThread", "Landroid/os/HandlerThread;", "addErrorStateInfo", "", "error", "Lcom/bugsnag/android/Error;", "anrState", "Landroid/app/ActivityManager$ProcessErrorStateInfo;", "addErrorStateInfo$bugsnag_plugin_android_anr_release", "captureProcessErrorState", "am", "Landroid/app/ActivityManager;", "pid", "", "captureProcessErrorState$bugsnag_plugin_android_anr_release", "collectAnrDetails", "ctx", "Landroid/content/Context;", "collectAnrDetails$bugsnag_plugin_android_anr_release", "collectAnrErrorDetails", "client", "Lcom/bugsnag/android/Client;", "collectAnrErrorDetails$bugsnag_plugin_android_anr_release", "Companion", "bugsnag-plugin-android-anr_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AnrDetailsCollector {
    public static final Companion Companion = new Companion(null);
    private static final long INFO_POLL_THRESHOLD_MS = 100;
    private static final int MAX_ATTEMPTS = 300;
    private final HandlerThread handlerThread = new HandlerThread("bugsnag-anr-collector");

    /* compiled from: AnrDetailsCollector.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/bugsnag/android/AnrDetailsCollector$Companion;", "", "()V", "INFO_POLL_THRESHOLD_MS", "", "MAX_ATTEMPTS", "", "bugsnag-plugin-android-anr_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AnrDetailsCollector() {
        this.handlerThread.start();
    }

    public final void addErrorStateInfo$bugsnag_plugin_android_anr_release(@NotNull Error error, @NotNull ActivityManager.ProcessErrorStateInfo anrState) {
        boolean startsWith$default;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(error, "error");
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(anrState, "anrState");
        String msg = anrState.shortMsg;
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(msg, CrashManagerActions.ANR, false, 2, null);
        if (startsWith$default) {
            msg = StringsKt__StringsJVMKt.replaceFirst$default(msg, CrashManagerActions.ANR, "", false, 4, (Object) null);
        }
        error.setExceptionMessage(msg);
    }

    @VisibleForTesting
    @Nullable
    public final ActivityManager.ProcessErrorStateInfo captureProcessErrorState$bugsnag_plugin_android_anr_release(@NotNull ActivityManager am, int i) {
        Object obj;
        boolean z;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(am, "am");
        try {
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = am.getProcessesInErrorState();
            if (processesInErrorState == null) {
                processesInErrorState = CollectionsKt__CollectionsKt.emptyList();
            }
            Iterator<T> it2 = processesInErrorState.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (((ActivityManager.ProcessErrorStateInfo) obj).pid == i) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
            return (ActivityManager.ProcessErrorStateInfo) obj;
        } catch (RuntimeException unused) {
            return null;
        }
    }

    @Nullable
    public final ActivityManager.ProcessErrorStateInfo collectAnrDetails$bugsnag_plugin_android_anr_release(@NotNull Context ctx) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Object systemService = ctx.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        if (systemService != null) {
            return captureProcessErrorState$bugsnag_plugin_android_anr_release((ActivityManager) systemService, Process.myPid());
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
    }

    public final void collectAnrErrorDetails$bugsnag_plugin_android_anr_release(@NotNull final Client client, @NotNull final Error error) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(client, "client");
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(error, "error");
        final Handler handler = new Handler(this.handlerThread.getLooper());
        final AtomicInteger atomicInteger = new AtomicInteger();
        handler.post(new Runnable() { // from class: com.bugsnag.android.AnrDetailsCollector$collectAnrErrorDetails$1
            @Override // java.lang.Runnable
            public void run() {
                AnrDetailsCollector anrDetailsCollector = AnrDetailsCollector.this;
                Context context = client.appContext;
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(context, "client.appContext");
                ActivityManager.ProcessErrorStateInfo collectAnrDetails$bugsnag_plugin_android_anr_release = anrDetailsCollector.collectAnrDetails$bugsnag_plugin_android_anr_release(context);
                if (collectAnrDetails$bugsnag_plugin_android_anr_release == null) {
                    if (atomicInteger.getAndIncrement() >= 300) {
                        return;
                    }
                    handler.postDelayed(this, 100L);
                    return;
                }
                AnrDetailsCollector.this.addErrorStateInfo$bugsnag_plugin_android_anr_release(error, collectAnrDetails$bugsnag_plugin_android_anr_release);
                client.notify(error, DeliveryStyle.ASYNC_WITH_CACHE, (Callback) null);
            }
        });
    }
}
