package com.amazon.alexa.growth.coachmark;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.growth.R;
import com.amazon.alexa.growth.coachmark.model.ConfigurationProfileContent;
import com.amazon.alexa.growth.metrics.BIMetricsRecorder;
import com.amazon.alexa.growth.metrics.OEMetricsRecorder;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.latencyinfra.TimelineInputArgument;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.github.florent37.viewtooltip.ViewTooltip;
import com.google.android.exoplayer2.C;
import java.util.Locale;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
/* loaded from: classes8.dex */
public class CoachMark {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    @VisibleForTesting
    static final String COACH_MARK_NO_ID = "NO_ID";
    private static final String COACH_MARK_VIEW_STATUS = "CoachMarkViewStatus";
    @VisibleForTesting
    static final String COMPONENT_BACKEND_NAME = "CoachMarkBackend";
    @VisibleForTesting
    static final String COMPONENT_NAME = "CoachMark";
    @VisibleForTesting
    static final String GET_COACH_MARK_FAILED = "GetCoachMarkFailed";
    @VisibleForTesting
    static final String GET_COACH_MARK_SUCCEEDED = "GetCoachMarkSucceeded";
    @VisibleForTesting
    static final String REMOTE_CALL_WEBLAB = "ALEXA_COACH_MARKS_BACKEND_ANDROID";
    @VisibleForTesting
    static final String SEE_MORE_COACH_MARK_ID = "CoachMark_more_see_more_icon";
    @VisibleForTesting
    static final String SHOW_COACH_MARK = "ShowCoachMark";
    @VisibleForTesting
    static final String SHOW_COACH_MARK_REMOTELY = "ShowCoachMarkRemotely";
    private static final String TAG = "CoachMark";
    private final View anchorView;
    private final Activity anchorViewActivity;
    private final Context anchorViewContext;
    @VisibleForTesting
    boolean anchorViewLaidOut;
    private final BIMetricsRecorder biMetricsRecorder;
    @VisibleForTesting
    BroadcastReceiver broadcastReceiver;
    @VisibleForTesting
    String coachMarkId;
    @VisibleForTesting
    Subscription configProfileContentSubscription;
    private final CoralService coralService;
    @VisibleForTesting
    WrappingTextView customTextView;
    private final FeatureServiceV2 featureService;
    @VisibleForTesting
    ViewGroup fullscreenRootView;
    @VisibleForTesting
    FrameLayout fullscreenTouchInterceptorView;
    @VisibleForTesting
    IntentFilter intentFilter;
    private boolean isHorizontallyCentered;
    private boolean isVerticallyCentered;
    private final OEMetricsRecorder oeMetricsRecorder;
    @VisibleForTesting
    OnDismissListener onDismissListener;
    private boolean onLeftHalf;
    @VisibleForTesting
    OnShowListener onShowListener;
    private boolean onTopHalf;
    private ORIENTATION orientation;
    @VisibleForTesting
    Resources resources;
    private final TaskManager taskManager;
    @VisibleForTesting
    String text;
    private final TimelineInputArgument timelineBackendInputArgument;
    private final TimelineInputArgument timelineInputArgument;
    @VisibleForTesting
    ViewTooltip tooltip;
    @VisibleForTesting
    ViewTooltip.TooltipView tooltipView;
    private int viewCenterXCoordinate;
    private int viewCenterYCoordinate;
    private int viewEndXCoordinate;
    private int viewEndYCoordinate;
    private int viewXCoordinate;
    private int viewYCoordinate;
    @VisibleForTesting
    boolean showCalledBeforeAnchorViewVisible = false;
    @VisibleForTesting
    boolean coachMarkClosed = false;
    @VisibleForTesting
    ThemeHelper themeHelper = new ThemeHelper();

    @VisibleForTesting
    /* loaded from: classes8.dex */
    public class CoachMarkPositioning {
        public ViewTooltip.ALIGN alignment;
        public ViewTooltip.Position position;

        CoachMarkPositioning(ViewTooltip.Position position, ViewTooltip.ALIGN align) {
            CoachMark.this = r1;
            this.position = position;
            this.alignment = align;
        }
    }

    /* loaded from: classes8.dex */
    public enum ORIENTATION {
        VERTICAL,
        HORIZONTAL
    }

    /* loaded from: classes8.dex */
    public interface OnDismissListener {
        void onDismiss(View view);
    }

    /* loaded from: classes8.dex */
    public interface OnShowListener {
        void onShow(View view);
    }

    public CoachMark(@NonNull final View view, @Nullable String str, @Nullable ViewGroup viewGroup, @NonNull ORIENTATION orientation, @NonNull OEMetricsRecorder oEMetricsRecorder, @NonNull BIMetricsRecorder bIMetricsRecorder, @NonNull TaskManager taskManager, @NonNull FeatureServiceV2 featureServiceV2, @NonNull CoralService coralService) {
        this.anchorView = view;
        this.oeMetricsRecorder = oEMetricsRecorder;
        this.biMetricsRecorder = bIMetricsRecorder;
        this.taskManager = taskManager;
        this.featureService = featureServiceV2;
        this.orientation = orientation;
        this.coralService = coralService;
        this.coachMarkId = str == null ? generateUniqueId() : str;
        this.timelineInputArgument = OEMetricsRecorder.createRenderTimeEvent(new String[]{"CoachMark", this.coachMarkId});
        this.timelineBackendInputArgument = OEMetricsRecorder.createRenderTimeEvent(new String[]{COMPONENT_BACKEND_NAME, this.coachMarkId});
        this.anchorViewContext = view.getContext();
        this.resources = this.anchorViewContext.getResources();
        this.anchorViewActivity = Utils.getActivityFromView(view);
        if (viewGroup != null) {
            this.fullscreenRootView = viewGroup;
        } else {
            this.fullscreenRootView = (ViewGroup) this.anchorViewActivity.getWindow().getDecorView();
        }
        this.anchorViewLaidOut = view.isLaidOut();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Anchor view has been laid out: ");
        outline107.append(this.anchorViewLaidOut);
        outline107.toString();
        if (this.anchorViewLaidOut) {
            initializeCoachMark();
        } else {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.alexa.growth.coachmark.CoachMark.1
                {
                    CoachMark.this = this;
                }

                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (CoachMark.this.tooltip != null || !view.isLaidOut()) {
                        return;
                    }
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    CoachMark coachMark = CoachMark.this;
                    coachMark.anchorViewLaidOut = true;
                    coachMark.initializeCoachMark();
                    if (!CoachMark.this.showCalledBeforeAnchorViewVisible) {
                        return;
                    }
                    String unused = CoachMark.TAG;
                    CoachMark.this.show();
                }
            });
        }
    }

    public void afterRemoteCallTerminate() {
        this.oeMetricsRecorder.endEventTimer(this.timelineBackendInputArgument);
        String str = "call coachmark backend done for id: " + this.coachMarkId;
        this.anchorViewActivity.runOnUiThread(new $$Lambda$CoachMark$BMBrxOLOdJ9mv8DdkIunHr8AhH0(this));
        BIMetricsRecorder bIMetricsRecorder = this.biMetricsRecorder;
        String str2 = this.coachMarkId;
        bIMetricsRecorder.recordEventOccurrence(new String[]{str2, SHOW_COACH_MARK}, str2);
        BIMetricsRecorder bIMetricsRecorder2 = this.biMetricsRecorder;
        String str3 = this.coachMarkId;
        bIMetricsRecorder2.recordEventOccurrence(new String[]{str3, SHOW_COACH_MARK_REMOTELY}, str3);
    }

    public void closeNow() {
        Subscription subscription = this.configProfileContentSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        if (!readyToClose()) {
            return;
        }
        this.tooltip.closeNow();
        onClose();
    }

    private void createCustomTextView() {
        Typeface typeface;
        try {
            typeface = FontResolver.getFont(this.anchorViewContext, Font.AMAZON_EMBER_REGULAR);
        } catch (RuntimeException unused) {
            Log.e(TAG, "ember-regular font is not available. Falling back to default typeface.");
            typeface = Typeface.DEFAULT;
        }
        int round = Math.round(this.resources.getDimension(R.dimen.coachmark_bubble_max_width) - (this.resources.getDimension(R.dimen.coachmark_bubble_padding_horizontal) * 2.0f));
        int integer = this.resources.getInteger(R.integer.coachmark_text_view_padding);
        this.customTextView = new WrappingTextView(this.anchorViewContext);
        this.customTextView.setId(R.id.coach_mark_text);
        this.customTextView.setTypeface(typeface);
        this.customTextView.setTextSize(0, this.resources.getDimension(R.dimen.coachmark_bubble_text_size));
        this.customTextView.setGravity(8388627);
        this.customTextView.setMaxWidth(round);
        this.customTextView.setMaxLines(this.resources.getInteger(R.integer.coachmark_text_view_max_lines));
        this.customTextView.setEllipsize(TextUtils.TruncateAt.END);
        this.customTextView.setPadding(integer, integer, integer, integer);
        this.customTextView.setBackground(null);
    }

    private void dismiss() {
        if (!readyToClose()) {
            return;
        }
        if (this.onDismissListener != null) {
            this.tooltip.onHide(new ViewTooltip.ListenerHide() { // from class: com.amazon.alexa.growth.coachmark.-$$Lambda$CoachMark$wnwXDxlsBSULbysZAhlwytXwGYc
                @Override // com.github.florent37.viewtooltip.ViewTooltip.ListenerHide
                public final void onHide(View view) {
                    CoachMark.this.lambda$dismiss$4$CoachMark(view);
                }
            });
        }
        this.tooltip.close();
        onClose();
        this.anchorViewContext.getSharedPreferences(COACH_MARK_VIEW_STATUS, 0).edit().putBoolean(this.coachMarkId, true).apply();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void generateFullscreenTouchInterceptor() {
        this.fullscreenTouchInterceptorView = new FrameLayout(this.anchorViewContext);
        this.fullscreenTouchInterceptorView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.fullscreenTouchInterceptorView.bringToFront();
        this.fullscreenTouchInterceptorView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.alexa.growth.coachmark.-$$Lambda$CoachMark$5k0R5-p-iODCIahFeGW8VG7sdJs
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CoachMark.this.lambda$generateFullscreenTouchInterceptor$3$CoachMark(view, motionEvent);
            }
        });
        this.fullscreenRootView.addView(this.fullscreenTouchInterceptorView);
    }

    private String generateUniqueId() {
        StringBuilder sb = new StringBuilder();
        try {
            View view = this.anchorView;
            while (view.getId() == -1) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                sb.insert(0, "_" + viewGroup.indexOfChild(view));
                view = viewGroup;
            }
            sb.insert(0, view.getResources().getResourceEntryName(view.getId()));
        } catch (Exception unused) {
            sb.setLength(0);
            sb.append(COACH_MARK_NO_ID);
        }
        sb.insert(0, "CoachMark_");
        return sb.toString();
    }

    public void initializeCoachMark() {
        createCustomTextView();
        CoachMarkAnimation coachMarkAnimation = new CoachMarkAnimation(this.resources, this.customTextView);
        CoachMarkPositioning calculateCoachMarkPositioning = calculateCoachMarkPositioning();
        int round = Math.round(this.resources.getDimension(R.dimen.coachmark_bubble_padding_vertical));
        int round2 = Math.round(this.resources.getDimension(R.dimen.coachmark_bubble_padding_horizontal));
        int round3 = Math.round(this.resources.getDimension(R.dimen.coachmark_distance_with_view));
        int round4 = Math.round(this.resources.getDimension(R.dimen.coachmark_distance_with_arrow));
        int round5 = Math.round(this.resources.getDimension(R.dimen.coachmark_distance_with_boarder));
        this.tooltip = ViewTooltip.on(this.anchorViewActivity, this.fullscreenRootView, this.anchorView).testId(this.resources.getResourceName(R.id.coach_mark)).corner(Math.round(this.resources.getDimension(R.dimen.coachmark_corner_radius))).arrowHeight(Math.round(this.resources.getDimension(R.dimen.coachmark_arrow_height))).arrowWidth(Math.round(this.resources.getDimension(R.dimen.coachmark_arrow_width))).padding(round2, round, round2, round).withShadow(true).align(calculateCoachMarkPositioning.alignment).position(calculateCoachMarkPositioning.position).clickToHide(false).autoHide(false, 0L).customView(this.customTextView).distanceWithView(round3).distanceWithArrow(round4).margin(round5, 0, round5, 0).animation(coachMarkAnimation);
        setupCoachMarkColors();
    }

    private void listenToAnchorViewOnAttachState() {
        this.anchorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.amazon.alexa.growth.coachmark.CoachMark.3
            {
                CoachMark.this = this;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                CoachMark.this.closeNow();
                CoachMark.this.anchorView.removeOnAttachStateChangeListener(this);
            }
        });
    }

    private void onClose() {
        FrameLayout frameLayout;
        this.coachMarkClosed = true;
        ViewGroup viewGroup = this.fullscreenRootView;
        if (viewGroup == null || (frameLayout = this.fullscreenTouchInterceptorView) == null) {
            return;
        }
        viewGroup.removeView(frameLayout);
        this.fullscreenTouchInterceptorView = null;
    }

    public void onRemoteCallFail(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("call coachmark backend failed for id: ");
        outline107.append(this.coachMarkId);
        outline107.append(". Error: {}");
        Log.e(str, outline107.toString(), th);
        BIMetricsRecorder bIMetricsRecorder = this.biMetricsRecorder;
        String str2 = this.coachMarkId;
        bIMetricsRecorder.recordEventOccurrence(new String[]{str2, GET_COACH_MARK_FAILED}, str2);
    }

    public void onRemoteCallSucceed(ConfigurationProfileContent configurationProfileContent) {
        String text = configurationProfileContent.getCoachMarkItems().get(0).getTooltips().get(0).getText();
        if (text.isEmpty()) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("coachMarkText is empty for coachmark id ");
            outline107.append(this.coachMarkId);
            Log.w(str, outline107.toString());
            return;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("call coachmark backend succeeded for id: ");
        outline1072.append(this.coachMarkId);
        outline1072.toString();
        this.customTextView.setText(text);
        BIMetricsRecorder bIMetricsRecorder = this.biMetricsRecorder;
        String str2 = this.coachMarkId;
        bIMetricsRecorder.recordEventOccurrence(new String[]{str2, GET_COACH_MARK_SUCCEEDED}, str2);
    }

    private boolean readyToClose() {
        Activity activity;
        return !this.coachMarkClosed && (activity = this.anchorViewActivity) != null && !activity.isDestroyed();
    }

    public void renderCoachMark() {
        this.oeMetricsRecorder.startEventTimer(this.timelineInputArgument);
        Log.i(TAG, "Rendering coach mark.");
        this.tooltipView = this.tooltip.show();
        this.tooltipView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.growth.coachmark.-$$Lambda$CoachMark$j_BArTgEyymNlb9PIQ3WDqHzcyk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CoachMark.this.lambda$renderCoachMark$2$CoachMark(view);
            }
        });
        this.tooltipView.setLabelFor(this.anchorView.getId());
        generateFullscreenTouchInterceptor();
        this.oeMetricsRecorder.endEventTimer(this.timelineInputArgument);
    }

    @VisibleForTesting
    CoachMarkPositioning calculateCoachMarkPositioning() {
        ViewTooltip.Position position;
        ViewTooltip.ALIGN align;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.anchorViewActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        int[] iArr = new int[2];
        this.anchorView.getLocationOnScreen(iArr);
        boolean z = false;
        this.viewXCoordinate = iArr[0];
        this.viewYCoordinate = iArr[1];
        this.viewCenterXCoordinate = Math.round((this.anchorView.getMeasuredWidth() / 2.0f) + this.viewXCoordinate);
        this.viewCenterYCoordinate = Math.round((this.anchorView.getMeasuredHeight() / 2.0f) + this.viewYCoordinate);
        this.viewEndXCoordinate = this.anchorView.getMeasuredWidth() + this.viewXCoordinate;
        this.viewEndYCoordinate = this.anchorView.getMeasuredHeight() + this.viewYCoordinate;
        int i3 = i / 2;
        this.onLeftHalf = this.viewCenterXCoordinate < i3;
        int i4 = i2 / 2;
        this.onTopHalf = this.viewCenterYCoordinate < i4;
        this.isHorizontallyCentered = Math.abs(this.viewCenterXCoordinate - i3) < 5;
        if (Math.abs(this.viewCenterYCoordinate - i4) < 5) {
            z = true;
        }
        this.isVerticallyCentered = z;
        if (this.orientation == ORIENTATION.VERTICAL) {
            position = this.onTopHalf ? ViewTooltip.Position.BOTTOM : ViewTooltip.Position.TOP;
            if (this.isHorizontallyCentered) {
                align = ViewTooltip.ALIGN.CENTER;
            } else if (this.onLeftHalf) {
                align = ViewTooltip.ALIGN.START;
            } else {
                align = ViewTooltip.ALIGN.END;
            }
        } else {
            position = this.onLeftHalf ? ViewTooltip.Position.RIGHT : ViewTooltip.Position.LEFT;
            align = ViewTooltip.ALIGN.CENTER;
        }
        return new CoachMarkPositioning(position, align);
    }

    @VisibleForTesting
    String getLocaleParam() {
        Locale currentLocale = Utils.getCurrentLocale(this.resources.getConfiguration());
        if (currentLocale == null || C.LANGUAGE_UNDETERMINED.equals(currentLocale.toLanguageTag())) {
            return "";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("locale=");
        outline107.append(currentLocale.toLanguageTag());
        return outline107.toString();
    }

    public /* synthetic */ void lambda$dismiss$4$CoachMark(View view) {
        this.onDismissListener.onDismiss(view);
    }

    public /* synthetic */ boolean lambda$generateFullscreenTouchInterceptor$3$CoachMark(View view, MotionEvent motionEvent) {
        dismiss();
        return false;
    }

    public /* synthetic */ void lambda$renderCoachMark$2$CoachMark(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$show$0$CoachMark(View view) {
        this.onShowListener.onShow(view);
    }

    public /* synthetic */ void lambda$show$1$CoachMark() {
        if (this.anchorViewContext.getSharedPreferences(COACH_MARK_VIEW_STATUS, 0).getBoolean(this.coachMarkId, false)) {
            return;
        }
        listenToAnchorViewOnAttachState();
        registerOrientationChangeListener(this.anchorViewContext);
        if (SEE_MORE_COACH_MARK_ID.equals(this.coachMarkId) && this.featureService.hasAccess("ALEXA_COACH_MARKS_BACKEND_ANDROID", false)) {
            setCoachMarkRemotelyAndRender();
            return;
        }
        this.anchorViewActivity.runOnUiThread(new $$Lambda$CoachMark$BMBrxOLOdJ9mv8DdkIunHr8AhH0(this));
        BIMetricsRecorder bIMetricsRecorder = this.biMetricsRecorder;
        String str = this.coachMarkId;
        bIMetricsRecorder.recordEventOccurrence(new String[]{str, SHOW_COACH_MARK}, str);
    }

    @VisibleForTesting
    void registerOrientationChangeListener(Context context) {
        if (this.intentFilter == null) {
            this.intentFilter = new IntentFilter();
        }
        this.intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        if (this.broadcastReceiver == null) {
            this.broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.growth.coachmark.CoachMark.2
                {
                    CoachMark.this = this;
                }

                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    CoachMark.this.closeNow();
                    context2.unregisterReceiver(CoachMark.this.broadcastReceiver);
                    String unused = CoachMark.TAG;
                }
            };
        }
        context.registerReceiver(this.broadcastReceiver, this.intentFilter);
    }

    @VisibleForTesting
    void setCoachMarkRemotelyAndRender() {
        this.oeMetricsRecorder.startEventTimer(this.timelineBackendInputArgument);
        this.configProfileContentSubscription = this.coralService.request("/api/coachmarks/v1/configurations/menu-see-more").get().as(ConfigurationProfileContent.class).toObservable().subscribeOn(Schedulers.io()).doAfterTerminate(new Action0() { // from class: com.amazon.alexa.growth.coachmark.-$$Lambda$CoachMark$K1zbAh1oJYtuv31gyD4co8p2H1A
            @Override // rx.functions.Action0
            public final void call() {
                CoachMark.this.afterRemoteCallTerminate();
            }
        }).subscribe(new Action1() { // from class: com.amazon.alexa.growth.coachmark.-$$Lambda$CoachMark$pqRde9f1HeEhu7waBwYh0hNT-60
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CoachMark.this.onRemoteCallSucceed((ConfigurationProfileContent) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.growth.coachmark.-$$Lambda$CoachMark$OVelQ7MBAf2BQTprCm42EL4kWWI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CoachMark.this.onRemoteCallFail((Throwable) obj);
            }
        });
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public void setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }

    public void setTestId(String str) {
        this.tooltip.testId(str);
    }

    public void setText(String str) {
        this.text = str;
    }

    @VisibleForTesting
    void setupCoachMarkColors() {
        this.customTextView.setTextColor(this.themeHelper.getColorFromAttribute(this.anchorViewContext, this.themeHelper.isLightMode(this.anchorViewContext) ? R.attr.mosaicBackground : R.attr.mosaicNeutral10));
        this.tooltip.color(this.themeHelper.getColorFromAttribute(this.anchorViewContext, R.attr.mosaicAction20));
        this.tooltip.shadowColor(this.resources.getColor(R.color.coachmark_drop_shadow));
        this.tooltip.withShadow(true);
    }

    public void show() {
        if (!this.anchorViewLaidOut) {
            this.showCalledBeforeAnchorViewVisible = true;
        } else if ((!Utils.isDebuggable(this.anchorViewContext) && Utils.isScreenReaderEnabled(this.anchorViewContext)) || TextUtils.isEmpty(this.text)) {
        } else {
            this.customTextView.setText(this.text);
            if (this.onShowListener != null) {
                this.tooltip.onDisplay(new ViewTooltip.ListenerDisplay() { // from class: com.amazon.alexa.growth.coachmark.-$$Lambda$CoachMark$SSE7NV-fkgIO-Rd7hkfYmBitioI
                    @Override // com.github.florent37.viewtooltip.ViewTooltip.ListenerDisplay
                    public final void onDisplay(View view) {
                        CoachMark.this.lambda$show$0$CoachMark(view);
                    }
                });
            }
            this.taskManager.getExecutor(0).execute(new Runnable() { // from class: com.amazon.alexa.growth.coachmark.-$$Lambda$CoachMark$PXY_P2DxcgwwEXPOnqqTrTLGkUQ
                @Override // java.lang.Runnable
                public final void run() {
                    CoachMark.this.lambda$show$1$CoachMark();
                }
            });
        }
    }
}
