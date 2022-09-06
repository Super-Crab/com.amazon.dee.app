package com.amazon.tarazed.ui.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.tarazed.R;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.databinding.ControlBarBinding;
import com.amazon.tarazed.ui.ViewGroupManager;
import com.amazon.tarazed.ui.menu.databinding.BarButtonHandlers;
import com.amazon.tarazed.ui.menu.databinding.BarNotificationTextManager;
import com.amazon.tarazed.ui.menu.databinding.BarNotificationVisibilityManager;
import com.amazon.tarazed.ui.menu.databinding.BorderVisibilityObservable;
import com.amazon.tarazed.ui.menu.databinding.ControlButtonStateObservable;
import com.amazon.tarazed.ui.menu.databinding.PauseResumeClickableManager;
import com.amazon.tarazed.ui.menu.databinding.StatusTextObservable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: MenuBarManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u00020\"H\u0002J\u0018\u00107\u001a\u0002032\u0006\u0010-\u001a\u00020\"2\u0006\u00100\u001a\u00020\"H\u0002J\u0006\u00108\u001a\u000209J\b\u0010:\u001a\u000209H\u0002J\b\u0010;\u001a\u000209H\u0002J\b\u0010<\u001a\u000209H\u0002J\u0006\u0010=\u001a\u000209R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\u00020\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u0010/¨\u0006>"}, d2 = {"Lcom/amazon/tarazed/ui/menu/MenuBarManager;", "", "context", "Landroid/content/Context;", "viewGroupManager", "Lcom/amazon/tarazed/ui/ViewGroupManager;", "playbackStateObservable", "Landroidx/databinding/ObservableField;", "", "mediaConnectedObservable", "Landroidx/databinding/ObservableBoolean;", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "arcusHelper", "Lcom/amazon/tarazed/arcus/ArcusHelper;", "borderVisibilityObservable", "Lcom/amazon/tarazed/ui/menu/databinding/BorderVisibilityObservable;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/ui/ViewGroupManager;Landroidx/databinding/ObservableField;Landroidx/databinding/ObservableBoolean;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/arcus/ArcusHelper;Lcom/amazon/tarazed/ui/menu/databinding/BorderVisibilityObservable;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "alreadyStarted", "", "bar", "Landroid/view/View;", "barNotificationTextManager", "Lcom/amazon/tarazed/ui/menu/databinding/BarNotificationTextManager;", "barNotificationTextObservable", "barNotificationVisibilityManager", "Lcom/amazon/tarazed/ui/menu/databinding/BarNotificationVisibilityManager;", "barNotificationVisibleObservable", "initialTouchX", "", "initialTouchY", "initialX", "", "initialY", "isShown", "overflowMenuVisible", "pauseResumeClickableManager", "Lcom/amazon/tarazed/ui/menu/databinding/PauseResumeClickableManager;", "pauseResumeClickableObservable", "previousX", "previousY", "statusTextObservable", "Lcom/amazon/tarazed/ui/menu/databinding/StatusTextObservable;", ReactProperties.HereMapMarker.X, "getX", "()I", ReactProperties.HereMapMarker.Y, "getY", "calculateNewPosition", "Landroid/graphics/Point;", "event", "Landroid/view/MotionEvent;", "calculateStartingXOffset", "clampToDraggableArea", "hideSessionControls", "", "setOnTouch", "setPreviousPosition", "setStartingPosition", "showSessionControls", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MenuBarManager {
    private boolean alreadyStarted;
    private final ArcusHelper arcusHelper;
    private final View bar;
    private final BarNotificationTextManager barNotificationTextManager;
    private final ObservableField<String> barNotificationTextObservable;
    private final BarNotificationVisibilityManager barNotificationVisibilityManager;
    private final ObservableBoolean barNotificationVisibleObservable;
    private final Context context;
    private float initialTouchX;
    private float initialTouchY;
    private int initialX;
    private int initialY;
    private boolean isShown;
    private final ObservableBoolean overflowMenuVisible;
    private final PauseResumeClickableManager pauseResumeClickableManager;
    private final ObservableBoolean pauseResumeClickableObservable;
    private int previousX;
    private int previousY;
    private final StatusTextObservable statusTextObservable;
    private final ViewGroupManager viewGroupManager;

    public MenuBarManager(@NotNull Context context, @NotNull ViewGroupManager viewGroupManager, @NotNull ObservableField<String> playbackStateObservable, @NotNull ObservableBoolean mediaConnectedObservable, @NotNull TarazedSessionNotifier notifier, @NotNull ArcusHelper arcusHelper, @NotNull BorderVisibilityObservable borderVisibilityObservable, @NotNull BizMetricsHelper bizMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(viewGroupManager, "viewGroupManager");
        Intrinsics.checkParameterIsNotNull(playbackStateObservable, "playbackStateObservable");
        Intrinsics.checkParameterIsNotNull(mediaConnectedObservable, "mediaConnectedObservable");
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        Intrinsics.checkParameterIsNotNull(arcusHelper, "arcusHelper");
        Intrinsics.checkParameterIsNotNull(borderVisibilityObservable, "borderVisibilityObservable");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.context = context;
        this.viewGroupManager = viewGroupManager;
        this.arcusHelper = arcusHelper;
        ControlBarBinding barBinding$TarazedAndroidLibrary_release = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        this.bar = barBinding$TarazedAndroidLibrary_release != null ? barBinding$TarazedAndroidLibrary_release.getRoot() : null;
        this.statusTextObservable = new StatusTextObservable(this.context, playbackStateObservable, mediaConnectedObservable);
        this.overflowMenuVisible = new ObservableBoolean(false);
        this.barNotificationVisibleObservable = new ObservableBoolean(false);
        this.barNotificationVisibilityManager = new BarNotificationVisibilityManager(playbackStateObservable, this.barNotificationVisibleObservable);
        this.barNotificationTextObservable = new ObservableField<>();
        this.barNotificationTextManager = new BarNotificationTextManager(this.context, playbackStateObservable, this.barNotificationTextObservable, bizMetricsHelper);
        this.pauseResumeClickableObservable = new ObservableBoolean(false);
        this.pauseResumeClickableManager = new PauseResumeClickableManager(this.pauseResumeClickableObservable);
        ControlBarBinding barBinding$TarazedAndroidLibrary_release2 = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        if (barBinding$TarazedAndroidLibrary_release2 != null) {
            barBinding$TarazedAndroidLibrary_release2.setStatusText(this.statusTextObservable);
        }
        ControlBarBinding barBinding$TarazedAndroidLibrary_release3 = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        if (barBinding$TarazedAndroidLibrary_release3 != null) {
            barBinding$TarazedAndroidLibrary_release3.setBarBorderVisible(borderVisibilityObservable);
        }
        ControlBarBinding barBinding$TarazedAndroidLibrary_release4 = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        if (barBinding$TarazedAndroidLibrary_release4 != null) {
            barBinding$TarazedAndroidLibrary_release4.setBarButtonHandlers(new BarButtonHandlers(this.context, playbackStateObservable, this.overflowMenuVisible, bizMetricsHelper));
        }
        ControlBarBinding barBinding$TarazedAndroidLibrary_release5 = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        if (barBinding$TarazedAndroidLibrary_release5 != null) {
            barBinding$TarazedAndroidLibrary_release5.setOverflowMenuVisible(this.overflowMenuVisible);
        }
        ControlBarBinding barBinding$TarazedAndroidLibrary_release6 = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        if (barBinding$TarazedAndroidLibrary_release6 != null) {
            barBinding$TarazedAndroidLibrary_release6.setNotificationVisible(this.barNotificationVisibleObservable);
        }
        ControlBarBinding barBinding$TarazedAndroidLibrary_release7 = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        if (barBinding$TarazedAndroidLibrary_release7 != null) {
            barBinding$TarazedAndroidLibrary_release7.setMessageText(this.barNotificationTextObservable);
        }
        ControlBarBinding barBinding$TarazedAndroidLibrary_release8 = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        if (barBinding$TarazedAndroidLibrary_release8 != null) {
            barBinding$TarazedAndroidLibrary_release8.setPauseResumeClickable(this.pauseResumeClickableObservable);
        }
        ControlBarBinding barBinding$TarazedAndroidLibrary_release9 = this.viewGroupManager.getBarBinding$TarazedAndroidLibrary_release();
        if (barBinding$TarazedAndroidLibrary_release9 != null) {
            barBinding$TarazedAndroidLibrary_release9.setControlButtonState(new ControlButtonStateObservable(playbackStateObservable, mediaConnectedObservable));
        }
        this.pauseResumeClickableManager.subscribeToNotifier$TarazedAndroidLibrary_release(notifier);
        this.barNotificationVisibilityManager.subscribeToNotifier$TarazedAndroidLibrary_release(notifier);
        this.barNotificationTextManager.subscribeToNotifier$TarazedAndroidLibrary_release(notifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Point calculateNewPosition(MotionEvent motionEvent) {
        return clampToDraggableArea(this.initialX + ((int) (motionEvent.getRawX() - this.initialTouchX)), this.initialY + ((int) (motionEvent.getRawY() - this.initialTouchY)));
    }

    private final int calculateStartingXOffset() {
        Point point = new Point();
        Object systemService = this.context.getSystemService("window");
        if (systemService != null) {
            ((WindowManager) systemService).getDefaultDisplay().getSize(point);
            return ((point.x / 2) - (this.context.getResources().getDimensionPixelSize(R.dimen.bar_width) / 2)) - this.context.getResources().getDimensionPixelOffset(R.dimen.screen_margin);
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Point clampToDraggableArea(int i, int i2) {
        int coerceIn;
        int coerceIn2;
        Rect draggableArea = this.viewGroupManager.getDraggableArea();
        coerceIn = RangesKt___RangesKt.coerceIn(i, draggableArea.left, draggableArea.right);
        coerceIn2 = RangesKt___RangesKt.coerceIn(i2, draggableArea.top, draggableArea.bottom);
        return new Point(coerceIn, coerceIn2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getX() {
        return this.viewGroupManager.getBarX$TarazedAndroidLibrary_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getY() {
        return this.viewGroupManager.getBarY$TarazedAndroidLibrary_release();
    }

    private final void setOnTouch() {
        View view = this.bar;
        if (view != null) {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.tarazed.ui.menu.MenuBarManager$setOnTouch$1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent event) {
                    int x;
                    int y;
                    Point clampToDraggableArea;
                    ViewGroupManager viewGroupManager;
                    int x2;
                    int y2;
                    Point calculateNewPosition;
                    ViewGroupManager viewGroupManager2;
                    view2.performClick();
                    Intrinsics.checkExpressionValueIsNotNull(event, "event");
                    int action = event.getAction();
                    if (action != 0) {
                        if (action != 2) {
                            return true;
                        }
                        calculateNewPosition = MenuBarManager.this.calculateNewPosition(event);
                        viewGroupManager2 = MenuBarManager.this.viewGroupManager;
                        viewGroupManager2.updateDraggablePosition(calculateNewPosition.x, calculateNewPosition.y);
                        return true;
                    }
                    MenuBarManager menuBarManager = MenuBarManager.this;
                    x = menuBarManager.getX();
                    y = MenuBarManager.this.getY();
                    clampToDraggableArea = menuBarManager.clampToDraggableArea(x, y);
                    viewGroupManager = MenuBarManager.this.viewGroupManager;
                    viewGroupManager.updateDraggablePosition(clampToDraggableArea.x, clampToDraggableArea.y);
                    MenuBarManager menuBarManager2 = MenuBarManager.this;
                    x2 = menuBarManager2.getX();
                    menuBarManager2.initialX = x2;
                    MenuBarManager menuBarManager3 = MenuBarManager.this;
                    y2 = menuBarManager3.getY();
                    menuBarManager3.initialY = y2;
                    MenuBarManager.this.initialTouchX = event.getRawX();
                    MenuBarManager.this.initialTouchY = event.getRawY();
                    return true;
                }
            });
        }
    }

    private final void setPreviousPosition() {
        this.viewGroupManager.updateDraggablePosition(this.previousX, this.previousY);
    }

    private final void setStartingPosition() {
        this.viewGroupManager.updateDraggablePosition(calculateStartingXOffset(), this.context.getResources().getDimensionPixelOffset(R.dimen.screen_margin));
    }

    public final void hideSessionControls() {
        if (this.isShown) {
            this.previousX = getX();
            this.previousY = getY();
            this.viewGroupManager.removeViewFromDraggableViewGroup(this.bar);
            this.isShown = false;
        }
    }

    public final void showSessionControls() {
        if (this.isShown) {
            return;
        }
        setOnTouch();
        if (!this.alreadyStarted) {
            setStartingPosition();
            this.alreadyStarted = true;
        } else {
            setPreviousPosition();
        }
        this.viewGroupManager.addViewToDraggableViewGroup(this.bar);
        this.isShown = true;
    }
}
