package com.amazon.alexa.tarazed.utils;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.tarazed.dagger.scope.TarazedIntegrationScope;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.tarazed.arcus.ArcusConstants;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FeatureChecker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ!\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J!\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0011\u0010\u0014\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/tarazed/utils/FeatureChecker;", "", "featureService", "Lcom/amazon/alexa/featureservice/api/FeatureServiceV2;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "mainScope", "Lkotlinx/coroutines/CoroutineScope;", "(Lcom/amazon/alexa/featureservice/api/FeatureServiceV2;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lkotlinx/coroutines/CoroutineScope;)V", "checkFeature", "", "featureName", "", "defaultValue", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkFeatureWithTimeout", ArcusConstants.KEY_IS_TARAZED_ENABLED, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "FeatureUpdateListener", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
@TarazedIntegrationScope
/* loaded from: classes10.dex */
public final class FeatureChecker {
    private static final String ALEXA_ANDROID_MSS_FEATURE_NAME = "ALEXA_ANDROID_MAYDAY_SCREEN_SHARING";
    public static final Companion Companion = new Companion(null);
    private static final long FEATURE_CHECK_TIMEOUT_MILLIS = 15000;
    private static final String METRIC_FEATURE_CHECK_TIMEOUT = "FeatureCheckTimeout";
    private static final String TAG = "TarazedFtCheck";
    private final DispatcherProvider dispatchers;
    private final FeatureServiceV2 featureService;
    private final TarazedSessionLogger logger;
    private final CoroutineScope mainScope;
    private final TarazedMetricsHelper metricsHelper;

    /* compiled from: FeatureChecker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/tarazed/utils/FeatureChecker$Companion;", "", "()V", "ALEXA_ANDROID_MSS_FEATURE_NAME", "", "FEATURE_CHECK_TIMEOUT_MILLIS", "", "METRIC_FEATURE_CHECK_TIMEOUT", "TAG", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FeatureChecker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/tarazed/utils/FeatureChecker$FeatureUpdateListener;", "Lcom/amazon/alexa/featureservice/api/FeatureServiceV2$FeatureUpdateListener;", "continuation", "Lkotlin/coroutines/Continuation;", "", "featureName", "", "defaultValue", "(Lcom/amazon/alexa/tarazed/utils/FeatureChecker;Lkotlin/coroutines/Continuation;Ljava/lang/String;Z)V", "hasResumed", "Lkotlinx/atomicfu/AtomicBoolean;", "onFeatureUpdate", "", "name", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public final class FeatureUpdateListener implements FeatureServiceV2.FeatureUpdateListener {
        private final Continuation<Boolean> continuation;
        private final boolean defaultValue;
        private final String featureName;
        private final AtomicBoolean hasResumed;
        final /* synthetic */ FeatureChecker this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public FeatureUpdateListener(@NotNull FeatureChecker featureChecker, @NotNull Continuation<? super Boolean> continuation, String featureName, boolean z) {
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            Intrinsics.checkParameterIsNotNull(featureName, "featureName");
            this.this$0 = featureChecker;
            this.continuation = continuation;
            this.featureName = featureName;
            this.defaultValue = z;
            this.hasResumed = AtomicFU.atomic(false);
        }

        @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
        public void onFeatureUpdate(@Nullable String str) {
            BuildersKt__Builders_commonKt.launch$default(this.this$0.mainScope, null, null, new FeatureChecker$FeatureUpdateListener$onFeatureUpdate$1(this, null), 3, null);
            boolean hasAccess = this.this$0.featureService.hasAccess(this.featureName, this.defaultValue);
            TarazedSessionLogger tarazedSessionLogger = this.this$0.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Feature access for ");
            outline107.append(this.featureName);
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(hasAccess);
            tarazedSessionLogger.i(FeatureChecker.TAG, outline107.toString());
            if (!this.hasResumed.getAndSet(true)) {
                Continuation<Boolean> continuation = this.continuation;
                Boolean valueOf = Boolean.valueOf(hasAccess);
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m10378constructorimpl(valueOf));
            }
        }
    }

    @Inject
    public FeatureChecker(@NotNull FeatureServiceV2 featureService, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull DispatcherProvider dispatchers, @NotNull CoroutineScope mainScope) {
        Intrinsics.checkParameterIsNotNull(featureService, "featureService");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Intrinsics.checkParameterIsNotNull(mainScope, "mainScope");
        this.featureService = featureService;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.dispatchers = dispatchers;
        this.mainScope = mainScope;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object checkFeature(@NotNull String str, boolean z, @NotNull Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(this.dispatchers.main(), new FeatureChecker$checkFeature$2(this, str, z, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object checkFeatureWithTimeout(@org.jetbrains.annotations.NotNull java.lang.String r7, boolean r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeatureWithTimeout$1
            if (r0 == 0) goto L13
            r0 = r9
            com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeatureWithTimeout$1 r0 = (com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeatureWithTimeout$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeatureWithTimeout$1 r0 = new com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeatureWithTimeout$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            boolean r8 = r0.Z$0
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r0 = r0.L$0
            com.amazon.alexa.tarazed.utils.FeatureChecker r0 = (com.amazon.alexa.tarazed.utils.FeatureChecker) r0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L33
            goto L58
        L33:
            r9 = move-exception
            goto L61
        L35:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3d:
            kotlin.ResultKt.throwOnFailure(r9)
            r4 = 15000(0x3a98, double:7.411E-320)
            com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeatureWithTimeout$2 r9 = new com.amazon.alexa.tarazed.utils.FeatureChecker$checkFeatureWithTimeout$2     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5f
            r2 = 0
            r9.<init>(r6, r7, r8, r2)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5f
            r0.L$0 = r6     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5f
            r0.L$1 = r7     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5f
            r0.Z$0 = r8     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5f
            r0.label = r3     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5f
            java.lang.Object r9 = kotlinx.coroutines.TimeoutKt.withTimeout(r4, r9, r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5f
            if (r9 != r1) goto L57
            return r1
        L57:
            r0 = r6
        L58:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L33
            boolean r7 = r9.booleanValue()     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L33
            goto L90
        L5f:
            r9 = move-exception
            r0 = r6
        L61:
            com.amazon.tarazed.core.logging.TarazedSessionLogger r1 = r0.logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "15000 ms timeout elapsed while checking "
            r2.append(r3)
            java.lang.String r3 = "status of "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = ", returning default value of "
            r2.append(r7)
            r2.append(r8)
            java.lang.String r7 = r2.toString()
            java.lang.String r2 = "TarazedFtCheck"
            r1.e(r2, r7, r9)
            com.amazon.tarazed.core.metrics.TarazedMetricsHelper r7 = r0.metricsHelper
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r9 = "FeatureCheckTimeout"
            r7.addCountHighPriority(r2, r9, r0)
            r7 = r8
        L90:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.tarazed.utils.FeatureChecker.checkFeatureWithTimeout(java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object isTarazedEnabled(@NotNull Continuation<? super Boolean> continuation) {
        return checkFeatureWithTimeout("ALEXA_ANDROID_MAYDAY_SCREEN_SHARING", false, continuation);
    }
}
