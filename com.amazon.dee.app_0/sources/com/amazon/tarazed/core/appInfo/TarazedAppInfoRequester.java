package com.amazon.tarazed.core.appInfo;

import android.content.Context;
import com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfoSender;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.registry.TarazedComponentRegistry;
import com.amazon.tarazed.core.registry.component.AppEventHandlerProvider;
import com.amazon.tarazed.core.registry.component.AppMetadataProvider;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedEventHandler;
import com.amazon.tarazed.core.type.ProfileType;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedAppInfoRequester.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/tarazed/core/appInfo/TarazedAppInfoRequester;", "", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "tarazedEventDispatcher", "Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "sessionInfoSender", "Lcom/amazon/tarazed/core/appInfo/sessioninfo/SessionInfoSender;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;Lcom/amazon/tarazed/core/appInfo/sessioninfo/SessionInfoSender;)V", "requestAppInfo", "", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedAppInfoRequester {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "TarazedAppInfoRequester";
    private final Context context;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final SessionInfoSender sessionInfoSender;
    private final TarazedEventDispatcher tarazedEventDispatcher;

    /* compiled from: TarazedAppInfoRequester.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/core/appInfo/TarazedAppInfoRequester$Companion;", "", "()V", "TAG", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedAppInfoRequester(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedEventDispatcher tarazedEventDispatcher, @NotNull SessionInfoSender sessionInfoSender) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(tarazedEventDispatcher, "tarazedEventDispatcher");
        Intrinsics.checkParameterIsNotNull(sessionInfoSender, "sessionInfoSender");
        this.context = context;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.tarazedEventDispatcher = tarazedEventDispatcher;
        this.sessionInfoSender = sessionInfoSender;
    }

    public final void requestAppInfo() {
        String str;
        ProfileType profileType;
        List<TarazedEventHandler<?>> emptyList;
        this.logger.i(TAG, "Requesting app info");
        AppMetadataProvider appMetadataProvider = (AppMetadataProvider) TarazedComponentRegistry.INSTANCE.getComponent(AppMetadataProvider.class);
        AppEventHandlerProvider appEventHandlerProvider = (AppEventHandlerProvider) TarazedComponentRegistry.INSTANCE.getComponent(AppEventHandlerProvider.class);
        boolean screenReaderState = appMetadataProvider != null ? appMetadataProvider.getScreenReaderState() : false;
        if (appMetadataProvider == null || (str = appMetadataProvider.getTvRemoteType()) == null) {
            str = "";
        }
        if (appMetadataProvider == null || (profileType = appMetadataProvider.getActiveProfileType()) == null) {
            profileType = ProfileType.UNKNOWN;
        }
        if (appEventHandlerProvider == null || (emptyList = appEventHandlerProvider.getEventHandlers()) == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        for (TarazedEventHandler<?> tarazedEventHandler : emptyList) {
            this.tarazedEventDispatcher.registerHandler(tarazedEventHandler);
        }
        this.sessionInfoSender.sendSessionInfo(screenReaderState, str, profileType);
    }
}
