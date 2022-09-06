package com.amazon.tarazed.ui;

import android.content.Context;
import android.graphics.Rect;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.LayoutRes;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.tarazed.R;
import com.amazon.tarazed.application.lifecycle.TarazedAppLifeCycleOwner;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.databinding.ControlBarBinding;
import com.amazon.tarazed.databinding.DynamicSessionBorderBinding;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ViewGroupManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u0000 T2\u00020\u0001:\u0002TUBE\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u00106\u001a\u000207H\u0002J\u0006\u00108\u001a\u000207J\u0010\u00109\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010;J\u0018\u0010<\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010=\u001a\u00020>J \u0010?\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010=\u001a\u00020>2\u0006\u0010@\u001a\u00020\u0017J\u0006\u0010A\u001a\u000207J\u0010\u0010B\u001a\u00020%2\b\u0010:\u001a\u0004\u0018\u00010;J\u0010\u0010C\u001a\u00020%2\b\u0010:\u001a\u0004\u0018\u00010;J\u0018\u0010D\u001a\u0002072\u0006\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u0017H\u0002J\u0006\u0010G\u001a\u00020HJ\u0012\u0010I\u001a\u0004\u0018\u00010;2\b\b\u0001\u0010J\u001a\u00020\u0017J\u0006\u0010K\u001a\u000207J\u0010\u0010L\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010;J\u0010\u0010M\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010;J\u0018\u0010N\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010@\u001a\u00020\u0017J\u0006\u0010O\u001a\u000207J\u0006\u0010P\u001a\u000207J\b\u0010Q\u001a\u000207H\u0002J\u000e\u0010R\u001a\u0002072\u0006\u0010\u0002\u001a\u00020\u0003J\u0016\u0010S\u001a\u0002072\u0006\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u0017R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00178@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010&\u001a\u0004\u0018\u00010'8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010/\u001a\u0004\u0018\u0001008\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b1\u0010)\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u0006V"}, d2 = {"Lcom/amazon/tarazed/ui/ViewGroupManager;", "", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "deviceInfoUtility", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "staticViewGroup", "Landroid/widget/FrameLayout;", "draggableViewGroup", "transitionInflater", "Landroid/transition/TransitionInflater;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Landroid/widget/FrameLayout;Landroid/widget/FrameLayout;Landroid/transition/TransitionInflater;)V", "barBinding", "Lcom/amazon/tarazed/databinding/ControlBarBinding;", "getBarBinding$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/databinding/ControlBarBinding;", "setBarBinding$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/databinding/ControlBarBinding;)V", "barX", "", "getBarX$TarazedAndroidLibrary_release", "()I", "barY", "getBarY$TarazedAndroidLibrary_release", "borderBinding", "Lcom/amazon/tarazed/databinding/DynamicSessionBorderBinding;", "getBorderBinding$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/databinding/DynamicSessionBorderBinding;", "setBorderBinding$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/databinding/DynamicSessionBorderBinding;)V", "draggableParams", "Landroid/view/WindowManager$LayoutParams;", "isModal", "", "layoutInflater", "Landroid/view/LayoutInflater;", "layoutInflater$annotations", "()V", "getLayoutInflater$TarazedAndroidLibrary_release", "()Landroid/view/LayoutInflater;", "setLayoutInflater$TarazedAndroidLibrary_release", "(Landroid/view/LayoutInflater;)V", "staticParams", "windowManager", "Landroid/view/WindowManager;", "windowManager$annotations", "getWindowManager$TarazedAndroidLibrary_release", "()Landroid/view/WindowManager;", "setWindowManager$TarazedAndroidLibrary_release", "(Landroid/view/WindowManager;)V", "addDraggablePadding", "", "addViewGroupsToWindowManager", "addViewToDraggableViewGroup", "view", "Landroid/view/View;", "addViewToStaticViewGroup", "viewLayer", "Lcom/amazon/tarazed/ui/ViewGroupManager$ViewLayer;", "addViewToStaticViewGroupAnimated", "transitionRes", "cleanUp", "containsDraggableView", "containsStaticView", "createLayoutParams", ReactProperties.HereMapMarker.X, ReactProperties.HereMapMarker.Y, "getDraggableArea", "Landroid/graphics/Rect;", "inflateStaticView", TtmlNode.TAG_LAYOUT, "prepareModal", "removeViewFromDraggableViewGroup", "removeViewFromStaticViewGroup", "removeViewFromStaticViewGroupAnimated", "removeViewGroupsFromWindowManager", "resetModal", "setupForDataBinding", "updateContext", "updateDraggablePosition", "Companion", "ViewLayer", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ViewGroupManager {
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_NULL_LAYOUT_INFLATER = "NullLayoutInflater";
    private static final String METRIC_NULL_VIEW = "NullView";
    private static final String TAG = "ViewGroupManager";
    private static boolean underTest;
    @Nullable
    private ControlBarBinding barBinding;
    @Nullable
    private DynamicSessionBorderBinding borderBinding;
    private final Context context;
    private final DeviceInfoUtility deviceInfoUtility;
    private WindowManager.LayoutParams draggableParams;
    private final FrameLayout draggableViewGroup;
    private boolean isModal;
    @Nullable
    private LayoutInflater layoutInflater;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private WindowManager.LayoutParams staticParams;
    private final FrameLayout staticViewGroup;
    private final TransitionInflater transitionInflater;
    @Nullable
    private WindowManager windowManager;

    /* compiled from: ViewGroupManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/tarazed/ui/ViewGroupManager$Companion;", "", "()V", "METRIC_NULL_LAYOUT_INFLATER", "", "METRIC_NULL_VIEW", "TAG", "underTest", "", "underTest$annotations", "getUnderTest", "()Z", "setUnderTest", "(Z)V", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void underTest$annotations() {
        }

        public final boolean getUnderTest() {
            return ViewGroupManager.underTest;
        }

        public final void setUnderTest(boolean z) {
            ViewGroupManager.underTest = z;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: ViewGroupManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/ui/ViewGroupManager$ViewLayer;", "", "z", "", "(Ljava/lang/String;IF)V", "getZ", "()F", "TV_INDICATORS", "MENU", "ANNOTATIONS", "SESSION_BORDER", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public enum ViewLayer {
        TV_INDICATORS(1.0f),
        MENU(2.0f),
        ANNOTATIONS(3.0f),
        SESSION_BORDER(4.0f);
        
        private final float z;

        ViewLayer(float f) {
            this.z = f;
        }

        public final float getZ() {
            return this.z;
        }
    }

    @JvmOverloads
    public ViewGroupManager(@NotNull Context context, @NotNull TarazedSessionLogger tarazedSessionLogger, @NotNull TarazedMetricsHelper tarazedMetricsHelper, @NotNull DeviceInfoUtility deviceInfoUtility) {
        this(context, tarazedSessionLogger, tarazedMetricsHelper, deviceInfoUtility, null, null, null, 112, null);
    }

    @JvmOverloads
    public ViewGroupManager(@NotNull Context context, @NotNull TarazedSessionLogger tarazedSessionLogger, @NotNull TarazedMetricsHelper tarazedMetricsHelper, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull FrameLayout frameLayout) {
        this(context, tarazedSessionLogger, tarazedMetricsHelper, deviceInfoUtility, frameLayout, null, null, 96, null);
    }

    @JvmOverloads
    public ViewGroupManager(@NotNull Context context, @NotNull TarazedSessionLogger tarazedSessionLogger, @NotNull TarazedMetricsHelper tarazedMetricsHelper, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull FrameLayout frameLayout, @NotNull FrameLayout frameLayout2) {
        this(context, tarazedSessionLogger, tarazedMetricsHelper, deviceInfoUtility, frameLayout, frameLayout2, null, 64, null);
    }

    @JvmOverloads
    public ViewGroupManager(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull DeviceInfoUtility deviceInfoUtility, @NotNull FrameLayout staticViewGroup, @NotNull FrameLayout draggableViewGroup, @NotNull TransitionInflater transitionInflater) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(deviceInfoUtility, "deviceInfoUtility");
        Intrinsics.checkParameterIsNotNull(staticViewGroup, "staticViewGroup");
        Intrinsics.checkParameterIsNotNull(draggableViewGroup, "draggableViewGroup");
        Intrinsics.checkParameterIsNotNull(transitionInflater, "transitionInflater");
        this.context = context;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.deviceInfoUtility = deviceInfoUtility;
        this.staticViewGroup = staticViewGroup;
        this.draggableViewGroup = draggableViewGroup;
        this.transitionInflater = transitionInflater;
        updateContext(this.context);
        createLayoutParams(0, 0);
        if (this.deviceInfoUtility.isTouchableDevice()) {
            addDraggablePadding();
        }
    }

    private final void addDraggablePadding() {
        FrameLayout frameLayout = this.draggableViewGroup;
        if (frameLayout != null) {
            int dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.screen_margin);
            frameLayout.setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    private final void createLayoutParams(int i, int i2) {
        WindowManager.LayoutParams createNonModalLayoutParams;
        if (this.deviceInfoUtility.isTouchableDevice()) {
            createNonModalLayoutParams = WindowParamsHelper.INSTANCE.createStaticLayoutParams(this.deviceInfoUtility);
        } else if (this.isModal) {
            createNonModalLayoutParams = WindowParamsHelper.INSTANCE.createModalLayoutParams(this.deviceInfoUtility);
        } else {
            createNonModalLayoutParams = WindowParamsHelper.INSTANCE.createNonModalLayoutParams(this.deviceInfoUtility);
        }
        this.staticParams = createNonModalLayoutParams;
        this.draggableParams = WindowParamsHelper.INSTANCE.createDraggableLayoutParams(this.deviceInfoUtility, i, i2);
    }

    @VisibleForTesting
    public static /* synthetic */ void layoutInflater$annotations() {
    }

    private final void setupForDataBinding() {
        if (underTest) {
            this.logger.w(TAG, "Under test, skipping data binding");
            return;
        }
        LayoutInflater layoutInflater = this.layoutInflater;
        if (layoutInflater == null) {
            this.logger.e(TAG, "Layout inflater was null, cannot set up data binding, UI will misbehave");
            this.metricsHelper.addCountHighPriority(TAG, METRIC_NULL_LAYOUT_INFLATER, 1.0d);
            return;
        }
        if (layoutInflater == null) {
            Intrinsics.throwNpe();
        }
        this.barBinding = (ControlBarBinding) DataBindingUtil.inflate(layoutInflater, R.layout.control_bar, this.draggableViewGroup, false);
        ControlBarBinding controlBarBinding = this.barBinding;
        if (controlBarBinding != null) {
            controlBarBinding.setLifecycleOwner(TarazedAppLifeCycleOwner.INSTANCE.getOwner());
        }
        LayoutInflater layoutInflater2 = this.layoutInflater;
        if (layoutInflater2 == null) {
            Intrinsics.throwNpe();
        }
        this.borderBinding = (DynamicSessionBorderBinding) DataBindingUtil.inflate(layoutInflater2, R.layout.dynamic_session_border, this.staticViewGroup, false);
        DynamicSessionBorderBinding dynamicSessionBorderBinding = this.borderBinding;
        if (dynamicSessionBorderBinding == null) {
            return;
        }
        dynamicSessionBorderBinding.setLifecycleOwner(TarazedAppLifeCycleOwner.INSTANCE.getOwner());
    }

    @VisibleForTesting
    public static /* synthetic */ void windowManager$annotations() {
    }

    public final void addViewGroupsToWindowManager() {
        WindowManager windowManager;
        WindowManager windowManager2;
        createLayoutParams(getBarX$TarazedAndroidLibrary_release(), getBarY$TarazedAndroidLibrary_release());
        if (this.draggableViewGroup.getParent() == null && this.deviceInfoUtility.isTouchableDevice() && (windowManager2 = this.windowManager) != null) {
            FrameLayout frameLayout = this.draggableViewGroup;
            WindowManager.LayoutParams layoutParams = this.draggableParams;
            if (layoutParams == null) {
                Intrinsics.throwUninitializedPropertyAccessException("draggableParams");
            }
            windowManager2.addView(frameLayout, layoutParams);
        }
        if (this.staticViewGroup.getParent() != null || (windowManager = this.windowManager) == null) {
            return;
        }
        FrameLayout frameLayout2 = this.staticViewGroup;
        WindowManager.LayoutParams layoutParams2 = this.staticParams;
        if (layoutParams2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("staticParams");
        }
        windowManager.addView(frameLayout2, layoutParams2);
    }

    public final void addViewToDraggableViewGroup(@Nullable View view) {
        if (view == null) {
            this.logger.w(TAG, "addViewToDraggableViewGroup: view was null, ignoring");
            this.metricsHelper.addCount(TAG, METRIC_NULL_VIEW, 1.0d);
        } else if (containsDraggableView(view)) {
        } else {
            view.setZ(ViewLayer.MENU.getZ());
            this.draggableViewGroup.addView(view);
        }
    }

    public final void addViewToStaticViewGroup(@Nullable View view, @NotNull ViewLayer viewLayer) {
        Intrinsics.checkParameterIsNotNull(viewLayer, "viewLayer");
        if (view == null) {
            this.logger.w(TAG, "addViewToStaticViewGroup: view was null, ignoring");
            this.metricsHelper.addCount(TAG, METRIC_NULL_VIEW, 1.0d);
        } else if (containsStaticView(view)) {
        } else {
            view.setZ(viewLayer.getZ());
            this.staticViewGroup.addView(view);
        }
    }

    public final void addViewToStaticViewGroupAnimated(@Nullable View view, @NotNull ViewLayer viewLayer, int i) {
        Intrinsics.checkParameterIsNotNull(viewLayer, "viewLayer");
        if (view == null) {
            this.logger.w(TAG, "addViewToStaticViewGroupAnimated: view was null, ignoring");
            this.metricsHelper.addCount(TAG, METRIC_NULL_VIEW, 1.0d);
            return;
        }
        TransitionManager.beginDelayedTransition(this.staticViewGroup, this.transitionInflater.inflateTransition(i));
        addViewToStaticViewGroup(view, viewLayer);
    }

    public final void cleanUp() {
        this.barBinding = null;
        this.borderBinding = null;
        this.windowManager = null;
        this.layoutInflater = null;
    }

    public final boolean containsDraggableView(@Nullable View view) {
        return (view == null || this.draggableViewGroup.indexOfChild(view) == -1) ? false : true;
    }

    public final boolean containsStaticView(@Nullable View view) {
        return (view == null || this.staticViewGroup.indexOfChild(view) == -1) ? false : true;
    }

    @Nullable
    public final ControlBarBinding getBarBinding$TarazedAndroidLibrary_release() {
        return this.barBinding;
    }

    public final int getBarX$TarazedAndroidLibrary_release() {
        WindowManager.LayoutParams layoutParams = this.draggableParams;
        if (layoutParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("draggableParams");
        }
        return layoutParams.x;
    }

    public final int getBarY$TarazedAndroidLibrary_release() {
        WindowManager.LayoutParams layoutParams = this.draggableParams;
        if (layoutParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("draggableParams");
        }
        return layoutParams.y;
    }

    @Nullable
    public final DynamicSessionBorderBinding getBorderBinding$TarazedAndroidLibrary_release() {
        return this.borderBinding;
    }

    @NotNull
    public final Rect getDraggableArea() {
        return new Rect(0, 0, this.staticViewGroup.getWidth() - this.draggableViewGroup.getWidth(), this.staticViewGroup.getHeight() - this.draggableViewGroup.getHeight());
    }

    @Nullable
    public final LayoutInflater getLayoutInflater$TarazedAndroidLibrary_release() {
        return this.layoutInflater;
    }

    @Nullable
    public final WindowManager getWindowManager$TarazedAndroidLibrary_release() {
        return this.windowManager;
    }

    @Nullable
    public final View inflateStaticView(@LayoutRes int i) {
        LayoutInflater layoutInflater = this.layoutInflater;
        if (layoutInflater != null) {
            return layoutInflater.inflate(i, (ViewGroup) this.staticViewGroup, false);
        }
        return null;
    }

    public final void prepareModal() {
        if (this.deviceInfoUtility.isTouchableDevice() || this.isModal) {
            return;
        }
        WindowManager.LayoutParams createModalLayoutParams = WindowParamsHelper.INSTANCE.createModalLayoutParams(this.deviceInfoUtility);
        WindowManager windowManager = this.windowManager;
        if (windowManager != null) {
            windowManager.updateViewLayout(this.staticViewGroup, createModalLayoutParams);
        }
        this.isModal = true;
    }

    public final void removeViewFromDraggableViewGroup(@Nullable View view) {
        if (view == null) {
            this.logger.w(TAG, "removeViewFromDraggableViewGroup: view was null, ignoring");
            this.metricsHelper.addCount(TAG, METRIC_NULL_VIEW, 1.0d);
        } else if (!containsDraggableView(view)) {
        } else {
            this.draggableViewGroup.removeView(view);
        }
    }

    public final void removeViewFromStaticViewGroup(@Nullable View view) {
        if (view == null || !containsStaticView(view)) {
            return;
        }
        this.staticViewGroup.removeView(view);
    }

    public final void removeViewFromStaticViewGroupAnimated(@Nullable View view, int i) {
        if (view == null) {
            this.logger.w(TAG, "removeViewFromStaticViewGroupAnimated: view was null, ignoring");
            this.metricsHelper.addCount(TAG, METRIC_NULL_VIEW, 1.0d);
            return;
        }
        TransitionManager.beginDelayedTransition(this.staticViewGroup, this.transitionInflater.inflateTransition(i));
        removeViewFromStaticViewGroup(view);
    }

    public final void removeViewGroupsFromWindowManager() {
        WindowManager windowManager;
        WindowManager windowManager2;
        if (this.draggableViewGroup.getParent() != null && (windowManager2 = this.windowManager) != null) {
            windowManager2.removeViewImmediate(this.draggableViewGroup);
        }
        if (this.staticViewGroup.getParent() == null || (windowManager = this.windowManager) == null) {
            return;
        }
        windowManager.removeViewImmediate(this.staticViewGroup);
    }

    public final void resetModal() {
        if (this.deviceInfoUtility.isTouchableDevice() || !this.isModal) {
            return;
        }
        WindowManager.LayoutParams createNonModalLayoutParams = WindowParamsHelper.INSTANCE.createNonModalLayoutParams(this.deviceInfoUtility);
        WindowManager windowManager = this.windowManager;
        if (windowManager != null) {
            windowManager.updateViewLayout(this.staticViewGroup, createNonModalLayoutParams);
        }
        this.isModal = false;
    }

    public final void setBarBinding$TarazedAndroidLibrary_release(@Nullable ControlBarBinding controlBarBinding) {
        this.barBinding = controlBarBinding;
    }

    public final void setBorderBinding$TarazedAndroidLibrary_release(@Nullable DynamicSessionBorderBinding dynamicSessionBorderBinding) {
        this.borderBinding = dynamicSessionBorderBinding;
    }

    public final void setLayoutInflater$TarazedAndroidLibrary_release(@Nullable LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public final void setWindowManager$TarazedAndroidLibrary_release(@Nullable WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public final void updateContext(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("window");
        if (systemService != null) {
            this.windowManager = (WindowManager) systemService;
            Object systemService2 = context.getSystemService("layout_inflater");
            if (systemService2 != null) {
                this.layoutInflater = (LayoutInflater) systemService2;
                setupForDataBinding();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }

    public final void updateDraggablePosition(int i, int i2) {
        WindowManager.LayoutParams layoutParams = this.draggableParams;
        if (layoutParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("draggableParams");
        }
        layoutParams.x = i;
        WindowManager.LayoutParams layoutParams2 = this.draggableParams;
        if (layoutParams2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("draggableParams");
        }
        layoutParams2.y = i2;
        WindowManager windowManager = this.windowManager;
        if (windowManager != null) {
            FrameLayout frameLayout = this.draggableViewGroup;
            WindowManager.LayoutParams layoutParams3 = this.draggableParams;
            if (layoutParams3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("draggableParams");
            }
            windowManager.updateViewLayout(frameLayout, layoutParams3);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ViewGroupManager(android.content.Context r9, com.amazon.tarazed.core.logging.TarazedSessionLogger r10, com.amazon.tarazed.core.metrics.TarazedMetricsHelper r11, com.amazon.tarazed.core.utility.DeviceInfoUtility r12, android.widget.FrameLayout r13, android.widget.FrameLayout r14, android.transition.TransitionInflater r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r8 = this;
            r1 = r9
            r0 = r16 & 16
            if (r0 == 0) goto Lc
            android.widget.FrameLayout r0 = new android.widget.FrameLayout
            r0.<init>(r9)
            r5 = r0
            goto Ld
        Lc:
            r5 = r13
        Ld:
            r0 = r16 & 32
            if (r0 == 0) goto L18
            android.widget.FrameLayout r0 = new android.widget.FrameLayout
            r0.<init>(r9)
            r6 = r0
            goto L19
        L18:
            r6 = r14
        L19:
            r0 = r16 & 64
            if (r0 == 0) goto L28
            android.transition.TransitionInflater r0 = android.transition.TransitionInflater.from(r9)
            java.lang.String r2 = "TransitionInflater.from(context)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            r7 = r0
            goto L29
        L28:
            r7 = r15
        L29:
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.ui.ViewGroupManager.<init>(android.content.Context, com.amazon.tarazed.core.logging.TarazedSessionLogger, com.amazon.tarazed.core.metrics.TarazedMetricsHelper, com.amazon.tarazed.core.utility.DeviceInfoUtility, android.widget.FrameLayout, android.widget.FrameLayout, android.transition.TransitionInflater, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
