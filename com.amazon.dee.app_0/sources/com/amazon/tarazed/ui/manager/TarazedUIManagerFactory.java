package com.amazon.tarazed.ui.manager;

import android.content.Context;
import android.os.Handler;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.scopes.LibraryScope;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.border.DynamicBorderManager;
import com.amazon.tarazed.ui.border.StaticBorderManager;
import com.amazon.tarazed.ui.menu.MenuBarManager;
import com.amazon.tarazed.ui.menu.MenuDrawerManager;
import com.amazon.tarazed.ui.menu.databinding.BorderVisibilityObservable;
import com.amazon.tarazed.ui.menu.databinding.MediaConnectedManager;
import com.amazon.tarazed.ui.toast.ToastManager;
import com.amazon.tarazed.ui.tv.TVUIManager;
import javax.inject.Inject;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedUIManagerFactory.kt */
@LibraryScope
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001Bs\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!J\u0016\u0010#\u001a\u00020\u001d2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/tarazed/ui/manager/TarazedUIManagerFactory;", "", "context", "Landroid/content/Context;", "viewGroupManager", "Ljavax/inject/Provider;", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "tvManager", "Lcom/amazon/tarazed/ui/tv/TVUIManager;", "mainLooperHandler", "Landroid/os/Handler;", "activityTracker", "Lcom/amazon/tarazed/activity/ActivityTracker;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "arcusHelper", "Lcom/amazon/tarazed/arcus/ArcusHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Ljavax/inject/Provider;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Ljavax/inject/Provider;Landroid/os/Handler;Lcom/amazon/tarazed/activity/ActivityTracker;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lcom/amazon/tarazed/arcus/ArcusHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "create", "Lcom/amazon/tarazed/ui/manager/TarazedUIManager;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "playbackStateObservable", "Landroidx/databinding/ObservableField;", "", "createBarUIManager", "createDrawerUIManager", "setUpMediaConnectedObservable", "Landroidx/databinding/ObservableBoolean;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedUIManagerFactory {
    private final ActivityTracker activityTracker;
    private final ArcusHelper arcusHelper;
    private final BizMetricsHelper bizMetricsHelper;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private final DispatcherProvider dispatchers;
    private final TarazedSessionLogger logger;
    private final Handler mainLooperHandler;
    private final TarazedMetricsHelper metricsHelper;
    private final TarazedSessionNotifier notifier;
    private final Provider<TVUIManager> tvManager;
    private final Provider<ViewGroupManager> viewGroupManager;

    @Inject
    public TarazedUIManagerFactory(@NotNull Context context, @NotNull Provider<ViewGroupManager> viewGroupManager, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedSessionNotifier notifier, @NotNull Provider<TVUIManager> tvManager, @NotNull Handler mainLooperHandler, @NotNull ActivityTracker activityTracker, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatchers, @NotNull ArcusHelper arcusHelper, @NotNull BizMetricsHelper bizMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        Intrinsics.checkParameterIsNotNull(tvManager, "tvManager");
        Intrinsics.checkParameterIsNotNull(mainLooperHandler, "mainLooperHandler");
        Intrinsics.checkParameterIsNotNull(activityTracker, "activityTracker");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Intrinsics.checkParameterIsNotNull(arcusHelper, "arcusHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.context = context;
        this.viewGroupManager = viewGroupManager;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.notifier = notifier;
        this.tvManager = tvManager;
        this.mainLooperHandler = mainLooperHandler;
        this.activityTracker = activityTracker;
        this.coroutineScope = coroutineScope;
        this.dispatchers = dispatchers;
        this.arcusHelper = arcusHelper;
        this.bizMetricsHelper = bizMetricsHelper;
    }

    private final TarazedUIManager createBarUIManager(ObservableField<String> observableField) {
        ObservableBoolean upMediaConnectedObservable = setUpMediaConnectedObservable();
        BorderVisibilityObservable borderVisibilityObservable = new BorderVisibilityObservable(observableField, upMediaConnectedObservable);
        ViewGroupManager mo10268get = this.viewGroupManager.mo10268get();
        Intrinsics.checkExpressionValueIsNotNull(mo10268get, "viewGroupManager.get()");
        DynamicBorderManager dynamicBorderManager = new DynamicBorderManager(mo10268get, borderVisibilityObservable);
        Context context = this.context;
        ViewGroupManager mo10268get2 = this.viewGroupManager.mo10268get();
        Intrinsics.checkExpressionValueIsNotNull(mo10268get2, "viewGroupManager.get()");
        MenuBarManager menuBarManager = new MenuBarManager(context, mo10268get2, observableField, upMediaConnectedObservable, this.notifier, this.arcusHelper, borderVisibilityObservable, this.bizMetricsHelper);
        ViewGroupManager mo10268get3 = this.viewGroupManager.mo10268get();
        Intrinsics.checkExpressionValueIsNotNull(mo10268get3, "viewGroupManager.get()");
        return new TarazedBarUIManager(mo10268get3, this.notifier, this.logger, this.activityTracker, this.coroutineScope, this.dispatchers, dynamicBorderManager, menuBarManager, this.metricsHelper);
    }

    private final TarazedUIManager createDrawerUIManager(DeviceInfoUtility deviceInfoUtility) {
        Context context = this.context;
        ViewGroupManager mo10268get = this.viewGroupManager.mo10268get();
        Intrinsics.checkExpressionValueIsNotNull(mo10268get, "viewGroupManager.get()");
        StaticBorderManager staticBorderManager = new StaticBorderManager(context, mo10268get);
        ToastManager toastManager = new ToastManager(this.context, this.mainLooperHandler, deviceInfoUtility);
        Context context2 = this.context;
        ViewGroupManager mo10268get2 = this.viewGroupManager.mo10268get();
        Intrinsics.checkExpressionValueIsNotNull(mo10268get2, "viewGroupManager.get()");
        MenuDrawerManager menuDrawerManager = new MenuDrawerManager(context2, mo10268get2, deviceInfoUtility, this.logger, this.metricsHelper);
        Context context3 = this.context;
        ViewGroupManager mo10268get3 = this.viewGroupManager.mo10268get();
        Intrinsics.checkExpressionValueIsNotNull(mo10268get3, "viewGroupManager.get()");
        ViewGroupManager viewGroupManager = mo10268get3;
        TarazedSessionNotifier tarazedSessionNotifier = this.notifier;
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        TarazedMetricsHelper tarazedMetricsHelper = this.metricsHelper;
        TVUIManager mo10268get4 = this.tvManager.mo10268get();
        Intrinsics.checkExpressionValueIsNotNull(mo10268get4, "tvManager.get()");
        return new TarazedDrawerUIManager(context3, viewGroupManager, deviceInfoUtility, tarazedSessionNotifier, tarazedSessionLogger, tarazedMetricsHelper, mo10268get4, this.mainLooperHandler, this.activityTracker, toastManager, staticBorderManager, menuDrawerManager);
    }

    private final ObservableBoolean setUpMediaConnectedObservable() {
        ObservableBoolean observableBoolean = new ObservableBoolean(false);
        new MediaConnectedManager(observableBoolean).subscribeToNotifier$TarazedAndroidLibrary_release(this.notifier);
        return observableBoolean;
    }

    @NotNull
    public final TarazedUIManager create(@NotNull DeviceInfoUtility deviceInfoUtility, @NotNull ObservableField<String> playbackStateObservable) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(playbackStateObservable, "playbackStateObservable");
        if (deviceInfoUtility.isTouchableDevice()) {
            return createBarUIManager(playbackStateObservable);
        }
        return createDrawerUIManager(deviceInfoUtility);
    }
}
