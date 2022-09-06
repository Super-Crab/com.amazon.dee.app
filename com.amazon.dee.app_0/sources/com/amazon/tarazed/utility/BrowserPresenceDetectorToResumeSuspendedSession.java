package com.amazon.tarazed.utility;

import android.content.Context;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.utility.BrowserPresenceDetector;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.scopes.LibraryScope;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BrowserPresenceDetectorToResumeSuspendedSession.kt */
@LibraryScope
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u0012H\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0002\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lcom/amazon/tarazed/utility/BrowserPresenceDetectorToResumeSuspendedSession;", "Lcom/amazon/tarazed/core/utility/BrowserPresenceDetector;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "eventDispatcher", "Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;)V", "connectIot", "", "connectIot$TarazedAndroidLibrary_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCachedCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isBrowserPresent", "timeout", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BrowserPresenceDetectorToResumeSuspendedSession extends BrowserPresenceDetector {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_BROWSER_DETECTOR_IOT_FAILURE = "BrowserDetectorIoTFailure";
    private static final String TAG = "BrwsrDtectorResume";
    private final Context context;
    private final TarazedEventDispatcher eventDispatcher;
    private final TarazedIoTManager iotManager;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BrowserPresenceDetectorToResumeSuspendedSession.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/utility/BrowserPresenceDetectorToResumeSuspendedSession$Companion;", "", "()V", "METRIC_BROWSER_DETECTOR_IOT_FAILURE", "", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ BrowserPresenceDetectorToResumeSuspendedSession(android.content.Context r9, com.amazon.tarazed.core.logging.TarazedSessionLogger r10, com.amazon.tarazed.core.utility.DeviceInfoUtility r11, com.amazon.tarazed.core.metrics.TarazedMetricsHelper r12, com.amazon.tarazed.core.notifier.TarazedSessionNotifier r13, com.amazon.tarazed.core.signaling.TarazedEventDispatcher r14, com.amazon.tarazed.core.signaling.TarazedIoTManager r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r8 = this;
            r2 = r10
            r0 = r16 & 8
            if (r0 == 0) goto Le
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r0 = new com.amazon.tarazed.core.metrics.TarazedMetricsHelper
            r1 = r9
            r3 = r11
            r0.<init>(r9, r10, r11)
            r4 = r0
            goto L11
        Le:
            r1 = r9
            r3 = r11
            r4 = r12
        L11:
            r0 = r16 & 16
            if (r0 == 0) goto L1c
            com.amazon.tarazed.core.notifier.TarazedSessionNotifier r0 = new com.amazon.tarazed.core.notifier.TarazedSessionNotifier
            r0.<init>(r4)
            r5 = r0
            goto L1d
        L1c:
            r5 = r13
        L1d:
            r0 = r16 & 32
            if (r0 == 0) goto L28
            com.amazon.tarazed.core.signaling.TarazedEventDispatcher r0 = new com.amazon.tarazed.core.signaling.TarazedEventDispatcher
            r0.<init>(r10, r4)
            r6 = r0
            goto L29
        L28:
            r6 = r14
        L29:
            r0 = r16 & 64
            if (r0 == 0) goto L34
            com.amazon.tarazed.core.signaling.TarazedIoTManager r0 = new com.amazon.tarazed.core.signaling.TarazedIoTManager
            r0.<init>(r6, r10, r4, r5)
            r7 = r0
            goto L35
        L34:
            r7 = r15
        L35:
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.<init>(android.content.Context, com.amazon.tarazed.core.logging.TarazedSessionLogger, com.amazon.tarazed.core.utility.DeviceInfoUtility, com.amazon.tarazed.core.metrics.TarazedMetricsHelper, com.amazon.tarazed.core.notifier.TarazedSessionNotifier, com.amazon.tarazed.core.signaling.TarazedEventDispatcher, com.amazon.tarazed.core.signaling.TarazedIoTManager, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @androidx.annotation.VisibleForTesting
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object connectIot$TarazedAndroidLibrary_release(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$connectIot$1
            if (r0 == 0) goto L13
            r0 = r9
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$connectIot$1 r0 = (com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$connectIot$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$connectIot$1 r0 = new com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$connectIot$1
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L48
            if (r2 == r5) goto L40
            if (r2 != r3) goto L38
            java.lang.Object r1 = r0.L$1
            com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse r1 = (com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse) r1
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r0 = (com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession) r0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L35
            goto L70
        L35:
            r9 = move-exception
            r2 = r0
            goto L76
        L38:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L40:
            java.lang.Object r2 = r0.L$0
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r2 = (com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L59
        L48:
            kotlin.ResultKt.throwOnFailure(r9)
            android.content.Context r9 = r8.context
            r0.L$0 = r8
            r0.label = r5
            java.lang.Object r9 = r8.getCachedCredentials(r9, r0)
            if (r9 != r1) goto L58
            return r1
        L58:
            r2 = r8
        L59:
            com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse r9 = (com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse) r9
            if (r9 == 0) goto L8d
            com.amazon.tarazed.core.signaling.TarazedIoTManager r6 = r2.iotManager     // Catch: java.lang.Exception -> L75
            com.amazon.tarazed.core.sessionclient.model.createcredentials.SignalingCredentials r7 = r9.getSignalingCredentials()     // Catch: java.lang.Exception -> L75
            r0.L$0 = r2     // Catch: java.lang.Exception -> L75
            r0.L$1 = r9     // Catch: java.lang.Exception -> L75
            r0.label = r3     // Catch: java.lang.Exception -> L75
            java.lang.Object r9 = r6.connect(r7, r4, r0)     // Catch: java.lang.Exception -> L75
            if (r9 != r1) goto L70
            return r1
        L70:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r9
        L75:
            r9 = move-exception
        L76:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r0 = r2.logger
            java.lang.String r1 = "BrwsrDtectorResume"
            java.lang.String r3 = "Failed to connect to IoT"
            r0.e(r1, r3, r9)
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r9 = r2.metricsHelper
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r0 = "BrowserDetectorIoTFailure"
            r9.addCountHighPriority(r1, r0, r2)
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r9
        L8d:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.connectIot$TarazedAndroidLibrary_release(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0062 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object getCachedCredentials(@org.jetbrains.annotations.NotNull android.content.Context r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$getCachedCredentials$1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$getCachedCredentials$1 r0 = (com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$getCachedCredentials$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$getCachedCredentials$1 r0 = new com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession$getCachedCredentials$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            android.content.Context r5 = (android.content.Context) r5
            java.lang.Object r5 = r0.L$0
            com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession r5 = (com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4d
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService$Companion r6 = com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService.Companion
            com.amazon.tarazed.core.logging.TarazedSessionLogger r2 = r4.logger
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.getCachedCredentials(r5, r2, r0)
            if (r6 != r1) goto L4d
            return r1
        L4d:
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L62
            kotlinx.serialization.json.Json$Default r5 = kotlinx.serialization.json.Json.Default
            kotlinx.serialization.json.Json r5 = r5.getNonstrict()
            com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse$Companion r0 = com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse.Companion
            kotlinx.serialization.KSerializer r0 = r0.serializer()
            java.lang.Object r5 = r5.parse(r0, r6)
            return r5
        L62:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession.getCachedCredentials(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.amazon.tarazed.core.utility.BrowserPresenceDetector
    @Nullable
    public Object isBrowserPresent(long j, @NotNull Continuation<? super Boolean> continuation) {
        return CoroutineScopeKt.coroutineScope(new BrowserPresenceDetectorToResumeSuspendedSession$isBrowserPresent$2(this, j, null), continuation);
    }

    @Inject
    public BrowserPresenceDetectorToResumeSuspendedSession(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedEventDispatcher eventDispatcher, @NotNull TarazedIoTManager iotManager) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(eventDispatcher, "eventDispatcher");
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        this.context = context;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.eventDispatcher = eventDispatcher;
        this.iotManager = iotManager;
    }
}
