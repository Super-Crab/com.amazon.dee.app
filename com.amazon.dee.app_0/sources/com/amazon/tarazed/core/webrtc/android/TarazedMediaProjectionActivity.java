package com.amazon.tarazed.core.webrtc.android;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessorykit.ApplicationLifecycleObserverEventEmitter;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedMediaProjectionActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0003J6\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\tH\u0002J6\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\tH\u0002J\"\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\rH\u0014J\b\u0010\u001c\u001a\u00020\rH\u0017R\u0012\u0010\u0004\u001a\u00020\u0005X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/TarazedMediaProjectionActivity;", "Landroid/app/Activity;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "mediaProjectionRequested", "", "permissionTimer", "Lkotlinx/coroutines/Job;", "handleFailure", "", "msg", "", "metric", "resultReceiver", "Landroid/os/ResultReceiver;", "resultCode", "", "isTimeout", "handleFailureNoFinish", "onActivityResult", "requestCode", "data", "Landroid/content/Intent;", "onDestroy", ApplicationLifecycleObserverEventEmitter.LIFECYCLE_EVENT_START, "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedMediaProjectionActivity extends Activity implements CoroutineScope {
    @NotNull
    public static final String EXTRA_KEY_SCREEN_CAPTURE_RESULT_RECEIVER = "com.amazon.tarazed.SCREEN_CAPTURE_RESULT_RECEIVER";
    private static final String METRIC_MEDIA_PROJECTION_ACTIVITY_DESTROYED = "MediaProjectionActivityDestroyed";
    private static final String METRIC_MEDIA_PROJECTION_PERMISSION_TIMEOUT = "MediaProjectionPermissionTimeout";
    private static final String METRIC_MEDIA_PROJECTION_REJECTED = "MediaProjectionPermissionRejected";
    private static final String METRIC_MEDIA_PROJECTION_UNEXPECTED_FAILURE = "MediaProjectionUnexpectedFailure";
    private static final int SCREEN_CAPTURE_REQUEST_CODE = 0;
    private static final int SCREEN_CAPTURE_RESULT_CODE_TIMEOUT = 2;
    private static final String TAG = "TarazedMediaProjActvty";
    @Nullable
    private static BizMetricsHelper bizMetricsHelper;
    private static boolean isWaitingForMediaProjectionResult;
    @Nullable
    private static TarazedSessionLogger logger;
    @Nullable
    private static TarazedMetricsHelper metricsHelper;
    @Nullable
    private static Activity self;
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    private HashMap _$_findViewCache;
    private boolean mediaProjectionRequested;
    private Job permissionTimer;
    public static final Companion Companion = new Companion(null);
    private static long permissionTimeoutMs = 90000;

    /* compiled from: TarazedMediaProjectionActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100¨\u00061"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/TarazedMediaProjectionActivity$Companion;", "", "()V", "EXTRA_KEY_SCREEN_CAPTURE_RESULT_RECEIVER", "", "METRIC_MEDIA_PROJECTION_ACTIVITY_DESTROYED", "METRIC_MEDIA_PROJECTION_PERMISSION_TIMEOUT", "METRIC_MEDIA_PROJECTION_REJECTED", "METRIC_MEDIA_PROJECTION_UNEXPECTED_FAILURE", "SCREEN_CAPTURE_REQUEST_CODE", "", "SCREEN_CAPTURE_RESULT_CODE_TIMEOUT", "TAG", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "getBizMetricsHelper$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "setBizMetricsHelper$TarazedMobileCore_release", "(Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "isWaitingForMediaProjectionResult", "", "isWaitingForMediaProjectionResult$TarazedMobileCore_release", "()Z", "setWaitingForMediaProjectionResult$TarazedMobileCore_release", "(Z)V", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "getLogger$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "setLogger$TarazedMobileCore_release", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "getMetricsHelper$TarazedMobileCore_release", "()Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "setMetricsHelper$TarazedMobileCore_release", "(Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "permissionTimeoutMs", "", "getPermissionTimeoutMs$TarazedMobileCore_release", "()J", "setPermissionTimeoutMs$TarazedMobileCore_release", "(J)V", "self", "Landroid/app/Activity;", "getSelf$TarazedMobileCore_release", "()Landroid/app/Activity;", "setSelf$TarazedMobileCore_release", "(Landroid/app/Activity;)V", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final BizMetricsHelper getBizMetricsHelper$TarazedMobileCore_release() {
            return TarazedMediaProjectionActivity.bizMetricsHelper;
        }

        @Nullable
        public final TarazedSessionLogger getLogger$TarazedMobileCore_release() {
            return TarazedMediaProjectionActivity.logger;
        }

        @Nullable
        public final TarazedMetricsHelper getMetricsHelper$TarazedMobileCore_release() {
            return TarazedMediaProjectionActivity.metricsHelper;
        }

        public final long getPermissionTimeoutMs$TarazedMobileCore_release() {
            return TarazedMediaProjectionActivity.permissionTimeoutMs;
        }

        @Nullable
        public final Activity getSelf$TarazedMobileCore_release() {
            return TarazedMediaProjectionActivity.self;
        }

        public final boolean isWaitingForMediaProjectionResult$TarazedMobileCore_release() {
            return TarazedMediaProjectionActivity.isWaitingForMediaProjectionResult;
        }

        public final void setBizMetricsHelper$TarazedMobileCore_release(@Nullable BizMetricsHelper bizMetricsHelper) {
            TarazedMediaProjectionActivity.bizMetricsHelper = bizMetricsHelper;
        }

        public final void setLogger$TarazedMobileCore_release(@Nullable TarazedSessionLogger tarazedSessionLogger) {
            TarazedMediaProjectionActivity.logger = tarazedSessionLogger;
        }

        public final void setMetricsHelper$TarazedMobileCore_release(@Nullable TarazedMetricsHelper tarazedMetricsHelper) {
            TarazedMediaProjectionActivity.metricsHelper = tarazedMetricsHelper;
        }

        public final void setPermissionTimeoutMs$TarazedMobileCore_release(long j) {
            TarazedMediaProjectionActivity.permissionTimeoutMs = j;
        }

        public final void setSelf$TarazedMobileCore_release(@Nullable Activity activity) {
            TarazedMediaProjectionActivity.self = activity;
        }

        public final void setWaitingForMediaProjectionResult$TarazedMobileCore_release(boolean z) {
            TarazedMediaProjectionActivity.isWaitingForMediaProjectionResult = z;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void handleFailure(String str, String str2, ResultReceiver resultReceiver, int i, boolean z) {
        handleFailureNoFinish(str, str2, resultReceiver, i, z);
        finish();
    }

    static /* synthetic */ void handleFailure$default(TarazedMediaProjectionActivity tarazedMediaProjectionActivity, String str, String str2, ResultReceiver resultReceiver, int i, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 1;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            z = false;
        }
        tarazedMediaProjectionActivity.handleFailure(str, str2, resultReceiver, i3, z);
    }

    private final void handleFailureNoFinish(String str, String str2, ResultReceiver resultReceiver, int i, boolean z) {
        TarazedSessionLogger tarazedSessionLogger = logger;
        if (tarazedSessionLogger != null) {
            tarazedSessionLogger.e(TAG, str);
        }
        TarazedMetricsHelper tarazedMetricsHelper = metricsHelper;
        if (tarazedMetricsHelper != null) {
            tarazedMetricsHelper.addCount(TAG, str2, 1.0d);
        } else {
            TarazedSessionLogger tarazedSessionLogger2 = logger;
            if (tarazedSessionLogger2 != null) {
                tarazedSessionLogger2.w(TAG, "Could not emit metric, metricsHelper is null: " + str2);
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put(BizMetricsConstants.OS_PERMISSION_DECISION_METADATA_NAME, z ? "timeout" : "denied");
        BizMetricsHelper bizMetricsHelper2 = bizMetricsHelper;
        if (bizMetricsHelper2 != null) {
            bizMetricsHelper2.publishBizMetric(BizMetricsConstants.OS_PERMISSION_DECISION_EVENT_NAME, hashMap);
        } else {
            TarazedSessionLogger tarazedSessionLogger3 = logger;
            if (tarazedSessionLogger3 != null) {
                tarazedSessionLogger3.w(TAG, "Could not emit bizMetric, bizMetricsHelper is null");
            }
        }
        if (resultReceiver != null) {
            resultReceiver.send(i, new Bundle());
        }
    }

    static /* synthetic */ void handleFailureNoFinish$default(TarazedMediaProjectionActivity tarazedMediaProjectionActivity, String str, String str2, ResultReceiver resultReceiver, int i, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 1;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            z = false;
        }
        tarazedMediaProjectionActivity.handleFailureNoFinish(str, str2, resultReceiver, i3, z);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, @Nullable Intent intent) {
        isWaitingForMediaProjectionResult = false;
        Job job = this.permissionTimer;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.permissionTimer = null;
        ResultReceiver resultReceiver = (ResultReceiver) getIntent().getParcelableExtra(EXTRA_KEY_SCREEN_CAPTURE_RESULT_RECEIVER);
        if (resultReceiver == null) {
            handleFailure$default(this, "Result receiver was null", METRIC_MEDIA_PROJECTION_UNEXPECTED_FAILURE, resultReceiver, 0, false, 24, null);
        } else if (i != 0) {
            handleFailure$default(this, GeneratedOutlineSupport1.outline49("Received invalid request code: ", i), METRIC_MEDIA_PROJECTION_UNEXPECTED_FAILURE, resultReceiver, 0, false, 24, null);
        } else if (2 == i2) {
            handleFailure("User did not respond to OS permission dialog, rejecting", METRIC_MEDIA_PROJECTION_PERMISSION_TIMEOUT, resultReceiver, 2, true);
        } else if (-1 != i2) {
            handleFailure$default(this, "MediaProjection request failed, rejected by user", METRIC_MEDIA_PROJECTION_REJECTED, resultReceiver, 2, false, 16, null);
        } else if (intent == null) {
            handleFailure$default(this, "MediaProjection data was null", METRIC_MEDIA_PROJECTION_UNEXPECTED_FAILURE, resultReceiver, 0, false, 24, null);
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaProjectionResultReceiver.BUNDLE_KEY_MEDIA_PROJECTION_INTENT, intent);
            resultReceiver.send(0, bundle);
            HashMap hashMap = new HashMap();
            hashMap.put(BizMetricsConstants.OS_PERMISSION_DECISION_METADATA_NAME, BizMetricsConstants.OS_PERMISSION_GRANTED_TRUE_VALUE);
            BizMetricsHelper bizMetricsHelper2 = bizMetricsHelper;
            if (bizMetricsHelper2 != null) {
                bizMetricsHelper2.publishBizMetric(BizMetricsConstants.OS_PERMISSION_DECISION_EVENT_NAME, hashMap);
            } else {
                TarazedSessionLogger tarazedSessionLogger = logger;
                if (tarazedSessionLogger != null) {
                    tarazedSessionLogger.w(TAG, "Could not emit bizMetric, bizMetricsHelper is null");
                }
            }
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (isWaitingForMediaProjectionResult) {
            isWaitingForMediaProjectionResult = false;
            handleFailureNoFinish$default(this, "Activity destroyed before result was set", METRIC_MEDIA_PROJECTION_ACTIVITY_DESTROYED, (ResultReceiver) getIntent().getParcelableExtra(EXTRA_KEY_SCREEN_CAPTURE_RESULT_RECEIVER), 0, false, 24, null);
        }
        CoroutineScopeKt.cancel$default(this, null, 1, null);
        super.onDestroy();
        self = null;
    }

    @Override // android.app.Activity
    @VisibleForTesting
    public void onStart() {
        Job launch$default;
        super.onStart();
        if (this.mediaProjectionRequested) {
            TarazedSessionLogger tarazedSessionLogger = logger;
            if (tarazedSessionLogger == null) {
                return;
            }
            tarazedSessionLogger.i(TAG, "MediaProjection already requested, not requesting again");
            return;
        }
        Object systemService = getSystemService("media_projection");
        if (systemService != null) {
            startActivityForResult(((MediaProjectionManager) systemService).createScreenCaptureIntent(), 0);
            isWaitingForMediaProjectionResult = true;
            self = this;
            this.mediaProjectionRequested = true;
            launch$default = BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getDefault(), null, new TarazedMediaProjectionActivity$onStart$1(this, null), 2, null);
            this.permissionTimer = launch$default;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.projection.MediaProjectionManager");
    }
}
