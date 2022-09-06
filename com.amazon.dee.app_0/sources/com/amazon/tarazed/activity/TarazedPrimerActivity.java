package com.amazon.tarazed.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.tarazed.R;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.TarazedIntents;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.type.Response;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import com.amazon.tarazed.databinding.ActivityTarazedPrimerBinding;
import com.amazon.tarazed.receiver.PrimerBroadcastReceiver;
import com.amazon.tarazed.session.dialog.PrimerPermissionDialog;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedPrimerActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 >2\u00020\u0001:\u0002=>B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0001¢\u0006\u0002\b2J\u0012\u00103\u001a\u00020/2\b\u00104\u001a\u0004\u0018\u000105H\u0014J\b\u00106\u001a\u00020/H\u0017J\b\u00107\u001a\u00020/H\u0002J\u0018\u00108\u001a\u00020/2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u00020\u00168\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0002\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u001d8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020#8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/amazon/tarazed/activity/TarazedPrimerActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "arcusHelper", "Lcom/amazon/tarazed/arcus/ArcusHelper;", "getArcusHelper$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/arcus/ArcusHelper;", "setArcusHelper$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/arcus/ArcusHelper;)V", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "getBizMetricsHelper$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "setBizMetricsHelper$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$TarazedAndroidLibrary_release", "()Lkotlinx/coroutines/CoroutineScope;", "setCoroutineScope$TarazedAndroidLibrary_release", "(Lkotlinx/coroutines/CoroutineScope;)V", "isUnderTest", "", "isUnderTest$annotations", "isUnderTest$TarazedAndroidLibrary_release", "()Z", "setUnderTest$TarazedAndroidLibrary_release", "(Z)V", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "getLogger$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "setLogger$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "getMetricsHelper$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "setMetricsHelper$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "primerBroadcastReceiver", "Lcom/amazon/tarazed/receiver/PrimerBroadcastReceiver;", "primerFrozenBizMetricTimer", "Lkotlinx/coroutines/Job;", "primerResultReceiver", "Landroid/os/ResultReceiver;", "configureActivityBinding", "", "activityBinding", "Lcom/amazon/tarazed/databinding/ActivityTarazedPrimerBinding;", "configureActivityBinding$TarazedAndroidLibrary_release", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "registerBroadcastReceiver", "sendDialogResponseBizMetrics", "dialogResponse", "Lcom/amazon/tarazed/core/type/Response;", "dialogTypeEventName", "", "ButtonHandlers", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedPrimerActivity extends FragmentActivity {
    @NotNull
    public static final String ACTION_CLOSE_PRIMER = "com.amazon.tarazed.CLOSE_PRIMER";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EXTRA_KEY_PRIMER_RESULT_RECEIVER = "com.amazon.tarazed.PRIMER_RESULT_RECEIVER";
    @NotNull
    public static final String METRICS_NULL_PRIMER_RESULT_RECEIVER = "NullPrimerResultReceiver";
    @NotNull
    public static final String METRICS_PRIMER_ACCEPTED = "PrimerAccepted";
    @NotNull
    public static final String METRICS_PRIMER_CRASHED = "PrimerCrashed";
    @NotNull
    public static final String METRICS_PRIMER_REJECTED = "PrimerRejected";
    public static final long PRIMER_FROZEN_TIMEOUT_MS = 100000;
    @NotNull
    public static final String TAG = "TarazedPrimerActivity";
    private static boolean didCustomerAcceptSession;
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public ArcusHelper arcusHelper;
    @Inject
    @NotNull
    public BizMetricsHelper bizMetricsHelper;
    @Inject
    @NotNull
    public CoroutineScope coroutineScope;
    private boolean isUnderTest;
    @Inject
    @NotNull
    public TarazedSessionLogger logger;
    @Inject
    @NotNull
    public TarazedMetricsHelper metricsHelper;
    private PrimerBroadcastReceiver primerBroadcastReceiver;
    private Job primerFrozenBizMetricTimer;
    private ResultReceiver primerResultReceiver;

    /* compiled from: TarazedPrimerActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/activity/TarazedPrimerActivity$ButtonHandlers;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "Ljava/lang/ref/WeakReference;", "onAccept", "", "onDecline", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class ButtonHandlers {
        private WeakReference<Activity> activity;

        public ButtonHandlers(@NotNull Activity activity) {
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            this.activity = new WeakReference<>(activity);
        }

        public final void onAccept() {
            TarazedPrimerActivity.didCustomerAcceptSession = true;
            Activity activity = this.activity.get();
            if (activity != null) {
                activity.finish();
            }
        }

        public final void onDecline() {
            Activity activity = this.activity.get();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /* compiled from: TarazedPrimerActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/activity/TarazedPrimerActivity$Companion;", "", "()V", "ACTION_CLOSE_PRIMER", "", "EXTRA_KEY_PRIMER_RESULT_RECEIVER", "METRICS_NULL_PRIMER_RESULT_RECEIVER", "METRICS_PRIMER_ACCEPTED", "METRICS_PRIMER_CRASHED", "METRICS_PRIMER_REJECTED", "PRIMER_FROZEN_TIMEOUT_MS", "", "TAG", "didCustomerAcceptSession", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Response.values().length];

        static {
            $EnumSwitchMapping$0[Response.ACCEPTED.ordinal()] = 1;
            $EnumSwitchMapping$0[Response.DENIED.ordinal()] = 2;
            $EnumSwitchMapping$0[Response.TIMEOUT.ordinal()] = 3;
        }
    }

    @VisibleForTesting
    public static /* synthetic */ void isUnderTest$annotations() {
    }

    private final void registerBroadcastReceiver() {
        this.primerBroadcastReceiver = new PrimerBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter(ACTION_CLOSE_PRIMER);
        PrimerBroadcastReceiver primerBroadcastReceiver = this.primerBroadcastReceiver;
        if (primerBroadcastReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("primerBroadcastReceiver");
        }
        registerReceiver(primerBroadcastReceiver, intentFilter);
    }

    private final void sendDialogResponseBizMetrics(Response response, String str) {
        String str2;
        HashMap hashMap = new HashMap();
        int i = WhenMappings.$EnumSwitchMapping$0[response.ordinal()];
        if (i == 1) {
            str2 = BizMetricsConstants.ACCEPTED_BY_CUSTOMER_TRUE;
        } else if (i == 2) {
            str2 = "denied";
        } else if (i != 3) {
            throw new NoWhenBranchMatchedException();
        } else {
            str2 = "timeout";
        }
        hashMap.put(BizMetricsConstants.ACCEPTED_BY_CUSTOMER_METADATA_NAME, str2);
        BizMetricsHelper bizMetricsHelper = this.bizMetricsHelper;
        if (bizMetricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bizMetricsHelper");
        }
        bizMetricsHelper.publishBizMetric(str, hashMap);
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

    @VisibleForTesting
    public final void configureActivityBinding$TarazedAndroidLibrary_release(@NotNull ActivityTarazedPrimerBinding activityBinding) {
        Intrinsics.checkParameterIsNotNull(activityBinding, "activityBinding");
        activityBinding.setButtonHandlers(new ButtonHandlers(this));
    }

    @NotNull
    public final ArcusHelper getArcusHelper$TarazedAndroidLibrary_release() {
        ArcusHelper arcusHelper = this.arcusHelper;
        if (arcusHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arcusHelper");
        }
        return arcusHelper;
    }

    @NotNull
    public final BizMetricsHelper getBizMetricsHelper$TarazedAndroidLibrary_release() {
        BizMetricsHelper bizMetricsHelper = this.bizMetricsHelper;
        if (bizMetricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bizMetricsHelper");
        }
        return bizMetricsHelper;
    }

    @NotNull
    public final CoroutineScope getCoroutineScope$TarazedAndroidLibrary_release() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
        }
        return coroutineScope;
    }

    @NotNull
    public final TarazedSessionLogger getLogger$TarazedAndroidLibrary_release() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return tarazedSessionLogger;
    }

    @NotNull
    public final TarazedMetricsHelper getMetricsHelper$TarazedAndroidLibrary_release() {
        TarazedMetricsHelper tarazedMetricsHelper = this.metricsHelper;
        if (tarazedMetricsHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
        }
        return tarazedMetricsHelper;
    }

    public final boolean isUnderTest$TarazedAndroidLibrary_release() {
        return this.isUnderTest;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        Job launch$default;
        try {
            super.onCreate(bundle);
            LibraryInjector.getComponent().inject(this);
            this.primerResultReceiver = (ResultReceiver) getIntent().getParcelableExtra(EXTRA_KEY_PRIMER_RESULT_RECEIVER);
            if (this.primerResultReceiver == null) {
                TarazedSessionLogger tarazedSessionLogger = this.logger;
                if (tarazedSessionLogger == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("logger");
                }
                tarazedSessionLogger.e(TAG, "Error fetching primer resultReceiver, aborting the session");
                TarazedMetricsHelper tarazedMetricsHelper = this.metricsHelper;
                if (tarazedMetricsHelper == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
                }
                tarazedMetricsHelper.addCountHighPriority(TAG, METRICS_NULL_PRIMER_RESULT_RECEIVER, 1.0d);
                throw new NullPointerException("null primer result reciver");
            }
            registerBroadcastReceiver();
            if (!this.isUnderTest) {
                ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_tarazed_primer);
                Intrinsics.checkExpressionValueIsNotNull(contentView, "DataBindingUtil.setConte…_primer\n                )");
                configureActivityBinding$TarazedAndroidLibrary_release((ActivityTarazedPrimerBinding) contentView);
            }
            Job.DefaultImpls.cancel$default(PrimerPermissionDialog.Companion.getPrimerFailureToLoadBizMetricTimer$TarazedAndroidLibrary_release(), (CancellationException) null, 1, (Object) null);
            HashMap hashMap = new HashMap();
            hashMap.put(BizMetricsConstants.DISPLAYED_MSS_PRIMER_METADATA_NAME, BizMetricsConstants.DISPLAYED_MSS_PRIMER_TRUE_VALUE);
            BizMetricsHelper bizMetricsHelper = this.bizMetricsHelper;
            if (bizMetricsHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bizMetricsHelper");
            }
            bizMetricsHelper.publishBizMetric(BizMetricsConstants.DISPLAY_PRIMER_EVENT_NAME, hashMap);
            CoroutineScope coroutineScope = this.coroutineScope;
            if (coroutineScope == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new TarazedPrimerActivity$onCreate$1(this, null), 3, null);
            this.primerFrozenBizMetricTimer = launch$default;
        } catch (Exception e) {
            BizMetricsHelper bizMetricsHelper2 = this.bizMetricsHelper;
            if (bizMetricsHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bizMetricsHelper");
            }
            BizMetricsHelper.publishBizMetric$default(bizMetricsHelper2, BizMetricsConstants.PRIMER_CRASHED_EVENT_NAME, null, 2, null);
            TarazedMetricsHelper tarazedMetricsHelper2 = this.metricsHelper;
            if (tarazedMetricsHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
            }
            tarazedMetricsHelper2.addCountHighPriority(TAG, METRICS_PRIMER_CRASHED, 1.0d);
            TarazedSessionLogger tarazedSessionLogger2 = this.logger;
            if (tarazedSessionLogger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception in onCreate method of tarazedPrimerActivity: ");
            outline107.append(e.getMessage());
            tarazedSessionLogger2.e(TAG, outline107.toString());
            Intent intent = new Intent(this, TarazedSessionAndroidService.class);
            intent.setAction(TarazedIntents.SessionAndroidService.END_SESSION_IMMEDIATE);
            startService(intent);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    @VisibleForTesting
    public void onDestroy() {
        Job job = this.primerFrozenBizMetricTimer;
        if (job == null) {
            Intrinsics.throwUninitializedPropertyAccessException("primerFrozenBizMetricTimer");
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        PrimerBroadcastReceiver primerBroadcastReceiver = this.primerBroadcastReceiver;
        if (primerBroadcastReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("primerBroadcastReceiver");
        }
        unregisterReceiver(primerBroadcastReceiver);
        if (didCustomerAcceptSession) {
            ResultReceiver resultReceiver = this.primerResultReceiver;
            if (resultReceiver != null) {
                resultReceiver.send(1, new Bundle());
            }
            sendDialogResponseBizMetrics(Response.ACCEPTED, BizMetricsConstants.ASK_START_SESSION_EVENT_NAME);
            TarazedMetricsHelper tarazedMetricsHelper = this.metricsHelper;
            if (tarazedMetricsHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
            }
            tarazedMetricsHelper.addCountHighPriority(TAG, METRICS_PRIMER_ACCEPTED, 1.0d);
        } else {
            ResultReceiver resultReceiver2 = this.primerResultReceiver;
            if (resultReceiver2 != null) {
                resultReceiver2.send(0, new Bundle());
            }
            sendDialogResponseBizMetrics(Response.DENIED, BizMetricsConstants.ASK_START_SESSION_EVENT_NAME);
            TarazedMetricsHelper tarazedMetricsHelper2 = this.metricsHelper;
            if (tarazedMetricsHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("metricsHelper");
            }
            tarazedMetricsHelper2.addCountHighPriority(TAG, METRICS_PRIMER_REJECTED, 1.0d);
        }
        didCustomerAcceptSession = false;
        super.onDestroy();
    }

    public final void setArcusHelper$TarazedAndroidLibrary_release(@NotNull ArcusHelper arcusHelper) {
        Intrinsics.checkParameterIsNotNull(arcusHelper, "<set-?>");
        this.arcusHelper = arcusHelper;
    }

    public final void setBizMetricsHelper$TarazedAndroidLibrary_release(@NotNull BizMetricsHelper bizMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "<set-?>");
        this.bizMetricsHelper = bizMetricsHelper;
    }

    public final void setCoroutineScope$TarazedAndroidLibrary_release(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "<set-?>");
        this.coroutineScope = coroutineScope;
    }

    public final void setLogger$TarazedAndroidLibrary_release(@NotNull TarazedSessionLogger tarazedSessionLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedSessionLogger, "<set-?>");
        this.logger = tarazedSessionLogger;
    }

    public final void setMetricsHelper$TarazedAndroidLibrary_release(@NotNull TarazedMetricsHelper tarazedMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(tarazedMetricsHelper, "<set-?>");
        this.metricsHelper = tarazedMetricsHelper;
    }

    public final void setUnderTest$TarazedAndroidLibrary_release(boolean z) {
        this.isUnderTest = z;
    }
}
