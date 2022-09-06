package com.amazon.tarazed.dagger.modules;

import android.content.Context;
import com.amazon.tarazed.core.coroutine.dispatcher.DefaultDispatcherProvider;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.coroutine.scope.MainCoroutineScope;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.sessionclient.sessioncache.Post23SessionClientCache;
import com.amazon.tarazed.core.sessionclient.sessioncache.Post23SessionCredentialsCipher;
import com.amazon.tarazed.core.sessionclient.sessioncache.Pre23SessionClientCache;
import com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.tv.TVUIManager;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid1P;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid3P;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* compiled from: GlobalModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0010J\r\u0010\u0011\u001a\u00020\nH\u0001¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\u0014H\u0001¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b\u0018J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0014H\u0001¢\u0006\u0002\b\u001dJ%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\fH\u0001¢\u0006\u0002\b\"J\r\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%J\u001d\u0010&\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0014H\u0001¢\u0006\u0002\b'J\r\u0010(\u001a\u00020\bH\u0001¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\fH\u0001¢\u0006\u0002\b,J%\u0010-\u001a\u00020!2\u0006\u0010\t\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\fH\u0001¢\u0006\u0002\b.R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/amazon/tarazed/dagger/modules/GlobalModule;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "provideBizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "tarazedMetricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "provideBizMetricsHelper$TarazedAndroidLibrary_release", "provideDefaultDispatcherProvider", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "provideDefaultDispatcherProvider$TarazedAndroidLibrary_release", "provideDeviceInfoUtility", "provideDeviceInfoUtility$TarazedAndroidLibrary_release", "provideDeviceInfoUtilityAndroid", "Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_release", "provideMainCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "provideMainCoroutineScope$TarazedAndroidLibrary_release", "provideSessionClientCache", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionClientCache;", "metricsHelper", "deviceInfo", "provideSessionClientCache$TarazedAndroidLibrary_release", "provideTVIndicatorManager", "Lcom/amazon/tarazed/ui/tv/TVUIManager;", "viewGroupManager", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "provideTVIndicatorManager$TarazedAndroidLibrary_release", "provideTarazedLogger", "Lcom/amazon/tarazed/core/logging/TarazedLogger;", "provideTarazedLogger$TarazedAndroidLibrary_release", "provideTarazedMetricsHelper", "provideTarazedMetricsHelper$TarazedAndroidLibrary_release", "provideTarazedSessionLogger", "provideTarazedSessionLogger$TarazedAndroidLibrary_release", "provideTarazedSessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "provideTarazedSessionNotifier$TarazedAndroidLibrary_release", "provideViewGroupManager", "provideViewGroupManager$TarazedAndroidLibrary_release", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class GlobalModule {
    public static final Companion Companion = new Companion(null);
    private static final String PACKAGE_NAME_1P = "com.amazon.ods.kindleconnect";
    private final Context context;

    /* compiled from: GlobalModule.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/dagger/modules/GlobalModule$Companion;", "", "()V", "PACKAGE_NAME_1P", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public GlobalModule(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Provides
    @Singleton
    @NotNull
    public final BizMetricsHelper provideBizMetricsHelper$TarazedAndroidLibrary_release(@NotNull TarazedSessionLogger logger, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedMetricsHelper tarazedMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(tarazedMetricsHelper, "tarazedMetricsHelper");
        return new BizMetricsHelper(logger, deviceInfoUtility, tarazedMetricsHelper);
    }

    @Provides
    @Singleton
    @NotNull
    public final DispatcherProvider provideDefaultDispatcherProvider$TarazedAndroidLibrary_release() {
        return new DefaultDispatcherProvider();
    }

    @Provides
    @Singleton
    @NotNull
    public final DeviceInfoUtility provideDeviceInfoUtility$TarazedAndroidLibrary_release() {
        if (Intrinsics.areEqual(PACKAGE_NAME_1P, this.context.getPackageName())) {
            return new DeviceInfoUtilityAndroid1P(this.context);
        }
        return new DeviceInfoUtilityAndroid3P(this.context);
    }

    @Provides
    @Singleton
    @NotNull
    public final DeviceInfoUtilityAndroid provideDeviceInfoUtilityAndroid$TarazedAndroidLibrary_release() {
        if (Intrinsics.areEqual(PACKAGE_NAME_1P, this.context.getPackageName())) {
            return new DeviceInfoUtilityAndroid1P(this.context);
        }
        return new DeviceInfoUtilityAndroid3P(this.context);
    }

    @Provides
    @Singleton
    @NotNull
    public final CoroutineScope provideMainCoroutineScope$TarazedAndroidLibrary_release() {
        return new MainCoroutineScope(null, 1, null);
    }

    @Provides
    @Singleton
    @NotNull
    public final SessionClientCache provideSessionClientCache$TarazedAndroidLibrary_release(@NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull DeviceInfoUtilityAndroid deviceInfo) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        if (deviceInfo.getApiLevel() < 23) {
            return new Pre23SessionClientCache(logger, metricsHelper);
        }
        return new Post23SessionClientCache(this.context, logger, metricsHelper, new Post23SessionCredentialsCipher(this.context, logger));
    }

    @Provides
    @Singleton
    @NotNull
    public final TVUIManager provideTVIndicatorManager$TarazedAndroidLibrary_release(@NotNull TarazedSessionLogger logger, @NotNull ViewGroupManager viewGroupManager, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        return new TVUIManager(logger, metricsHelper, viewGroupManager, null, 8, null);
    }

    @Provides
    @Singleton
    @NotNull
    public final TarazedLogger provideTarazedLogger$TarazedAndroidLibrary_release() {
        return new TarazedLogger(this.context);
    }

    @Provides
    @Singleton
    @NotNull
    public final TarazedMetricsHelper provideTarazedMetricsHelper$TarazedAndroidLibrary_release(@NotNull TarazedSessionLogger logger, @NotNull DeviceInfoUtilityAndroid deviceInfoUtility) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        return new TarazedMetricsHelper(this.context, logger, deviceInfoUtility);
    }

    @Provides
    @Singleton
    @NotNull
    public final TarazedSessionLogger provideTarazedSessionLogger$TarazedAndroidLibrary_release() {
        return new TarazedSessionLogger(this.context);
    }

    @Provides
    @Singleton
    @NotNull
    public final TarazedSessionNotifier provideTarazedSessionNotifier$TarazedAndroidLibrary_release(@NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        return new TarazedSessionNotifier(metricsHelper);
    }

    @Provides
    @Singleton
    @NotNull
    public final ViewGroupManager provideViewGroupManager$TarazedAndroidLibrary_release(@NotNull DeviceInfoUtilityAndroid deviceInfoUtility, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        return new ViewGroupManager(this.context, logger, metricsHelper, deviceInfoUtility, null, null, null, 112, null);
    }
}
