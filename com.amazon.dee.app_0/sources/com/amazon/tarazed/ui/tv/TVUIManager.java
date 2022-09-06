package com.amazon.tarazed.ui.tv;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.DrawableRes;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TVUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 '2\u00020\u0001:\u0002'(B+\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\r\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\r\u0010\u0019\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u001aJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\rJ\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0002J\u0012\u0010\"\u001a\u0004\u0018\u00010#2\b\b\u0001\u0010$\u001a\u00020\u000fJ\r\u0010%\u001a\u00020\u0017H\u0000¢\u0006\u0002\b&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/amazon/tarazed/ui/tv/TVUIManager;", "", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "viewGroupManager", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "tvUIView", "Landroid/view/View;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/ui/ViewGroupManager;Landroid/view/View;)V", "registeredTVUIReinflatedListeners", "", "Lcom/amazon/tarazed/ui/tv/TVUIManager$TVUIReinflatedListener;", "uiLocation", "", "uiLocation$annotations", "()V", "getUiLocation$TarazedAndroidLibrary_release", "()I", "setUiLocation$TarazedAndroidLibrary_release", "(I)V", "hide", "Lkotlinx/coroutines/Job;", "hide$TarazedAndroidLibrary_release", "moveUI", "moveUI$TarazedAndroidLibrary_release", "notifyTVUIReinflated", "", "registerTVUIReinflatedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setAlignment", "layoutParams", "Landroid/widget/RelativeLayout$LayoutParams;", "setVirtualRemoteDrawable", "Landroid/widget/FrameLayout;", "remoteDrawableId", "show", "show$TarazedAndroidLibrary_release", "Companion", "TVUIReinflatedListener", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TVUIManager {
    private static final int BOTTOM_END = 0;
    private static final int BOTTOM_START = 1;
    private static final String METRIC_NULL_VIEW = "NullView";
    private static final String TAG = "TVUIManager";
    private static final int TOP_END = 3;
    private static final int TOP_START = 2;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private List<TVUIReinflatedListener> registeredTVUIReinflatedListeners;
    private View tvUIView;
    private int uiLocation;
    private final ViewGroupManager viewGroupManager;
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final int[][] UI_ALIGNMENTS = {new int[]{12, 21}, new int[]{12, 20}, new int[]{10, 20}, new int[]{10, 21}};
    private static final float[] SCRIM_ROTATIONS = {45.0f, 135.0f, 225.0f, 315.0f};

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TVUIManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/tarazed/ui/tv/TVUIManager$Companion;", "", "()V", "BOTTOM_END", "", "BOTTOM_START", "METRIC_NULL_VIEW", "", "SCRIM_ROTATIONS", "", "TAG", "TOP_END", "TOP_START", "UI_ALIGNMENTS", "", "", "[[I", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: TVUIManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/tarazed/ui/tv/TVUIManager$TVUIReinflatedListener;", "", "onUIReinflated", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public interface TVUIReinflatedListener {
        void onUIReinflated();
    }

    @JvmOverloads
    public TVUIManager(@NotNull TarazedSessionLogger tarazedSessionLogger, @NotNull TarazedMetricsHelper tarazedMetricsHelper, @NotNull ViewGroupManager viewGroupManager) {
        this(tarazedSessionLogger, tarazedMetricsHelper, viewGroupManager, null, 8, null);
    }

    @JvmOverloads
    public TVUIManager(@NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull ViewGroupManager viewGroupManager, @Nullable View view) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.viewGroupManager = viewGroupManager;
        this.tvUIView = view;
        if (this.tvUIView == null) {
            this.logger.e(TAG, "View is null, UI will misbehave");
            this.metricsHelper.addCountHighPriority(TAG, METRIC_NULL_VIEW, 1.0d);
        }
        this.registeredTVUIReinflatedListeners = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyTVUIReinflated() {
        for (TVUIReinflatedListener tVUIReinflatedListener : this.registeredTVUIReinflatedListeners) {
            tVUIReinflatedListener.onUIReinflated();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAlignment(RelativeLayout.LayoutParams layoutParams) {
        int[] iArr = UI_ALIGNMENTS[this.uiLocation];
        layoutParams.removeRule(20);
        layoutParams.removeRule(21);
        layoutParams.removeRule(10);
        layoutParams.removeRule(12);
        for (int i : iArr) {
            layoutParams.addRule(i, 1);
        }
    }

    @VisibleForTesting
    public static /* synthetic */ void uiLocation$annotations() {
    }

    public final int getUiLocation$TarazedAndroidLibrary_release() {
        return this.uiLocation;
    }

    @NotNull
    public final Job hide$TarazedAndroidLibrary_release() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TVUIManager$hide$1(this, null), 2, null);
        return launch$default;
    }

    @NotNull
    public final Job moveUI$TarazedAndroidLibrary_release() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TVUIManager$moveUI$1(this, null), 2, null);
        return launch$default;
    }

    public final void registerTVUIReinflatedListener(@NotNull TVUIReinflatedListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.registeredTVUIReinflatedListeners.add(listener);
    }

    public final void setUiLocation$TarazedAndroidLibrary_release(int i) {
        this.uiLocation = i;
    }

    @Nullable
    public final FrameLayout setVirtualRemoteDrawable(@DrawableRes int i) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TVUIManager$setVirtualRemoteDrawable$1(this, i, null), 2, null);
        View view = this.tvUIView;
        if (view != null) {
            return (FrameLayout) view.findViewById(R.id.virtual_remote_container);
        }
        return null;
    }

    @NotNull
    public final Job show$TarazedAndroidLibrary_release() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new TVUIManager$show$1(this, null), 2, null);
        return launch$default;
    }

    public /* synthetic */ TVUIManager(TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, ViewGroupManager viewGroupManager, View view, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(tarazedSessionLogger, tarazedMetricsHelper, viewGroupManager, (i & 8) != 0 ? viewGroupManager.inflateStaticView(R.layout.tv_ui) : view);
    }
}
