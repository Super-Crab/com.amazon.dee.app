package com.amazon.tarazed.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import com.amazon.tarazed.R;
import com.amazon.tarazed.ui.menu.databinding.BarButtonHandlers;
import com.amazon.tarazed.ui.menu.databinding.BorderVisibilityObservable;
import com.amazon.tarazed.ui.menu.databinding.ControlButtonStateObservable;
import com.amazon.tarazed.ui.menu.databinding.StatusTextObservable;
/* loaded from: classes13.dex */
public abstract class ControlBarBinding extends ViewDataBinding {
    @NonNull
    public final View bar;
    @NonNull
    public final View barBorder;
    @NonNull
    public final ImageView barMoveBtn;
    @NonNull
    public final Group barNotificationGroup;
    @NonNull
    public final ImageView barNotificationIcon;
    @NonNull
    public final TextView barNotificationMessage;
    @NonNull
    public final TextView barStatus;
    @NonNull
    public final TextView barTitle;
    @NonNull
    public final View barVerticalDivider;
    @NonNull
    public final Barrier controlBtnBarrierRight;
    @NonNull
    public final Barrier heightBarrier;
    @Bindable
    protected BorderVisibilityObservable mBarBorderVisible;
    @Bindable
    protected BarButtonHandlers mBarButtonHandlers;
    @Bindable
    protected ControlButtonStateObservable mControlButtonState;
    @Bindable
    protected ObservableField<String> mMessageText;
    @Bindable
    protected ObservableBoolean mNotificationVisible;
    @Bindable
    protected ObservableBoolean mOverflowMenuVisible;
    @Bindable
    protected ObservableBoolean mPauseResumeClickable;
    @Bindable
    protected StatusTextObservable mStatusText;
    @NonNull
    public final Barrier notificationTopBarrier;
    @NonNull
    public final Barrier overflowBtnBarrier;
    @NonNull
    public final ImageButton overflowCloseBtn;
    @NonNull
    public final ImageView overflowEndBtn;
    @NonNull
    public final TextView overflowEndText;
    @NonNull
    public final View overflowHorizontalDividerBottom;
    @NonNull
    public final View overflowHorizontalDividerTop;
    @NonNull
    public final Group overflowMenuGroup;
    @NonNull
    public final ImageButton overflowOpenBtn;
    @NonNull
    public final ImageButton pauseBtn;
    @NonNull
    public final ImageButton playBtn;
    @NonNull
    public final Space spaceBarBottom;
    @NonNull
    public final Space spaceBarTop;
    @NonNull
    public final Space spaceNotificationBottom;
    @NonNull
    public final Space spaceOverflowBottom;
    @NonNull
    public final ImageButton spinnerBtn;

    /* JADX INFO: Access modifiers changed from: protected */
    public ControlBarBinding(Object obj, View view, int i, View view2, View view3, ImageView imageView, Group group, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, View view4, Barrier barrier, Barrier barrier2, Barrier barrier3, Barrier barrier4, ImageButton imageButton, ImageView imageView3, TextView textView4, View view5, View view6, Group group2, ImageButton imageButton2, ImageButton imageButton3, ImageButton imageButton4, Space space, Space space2, Space space3, Space space4, ImageButton imageButton5) {
        super(obj, view, i);
        this.bar = view2;
        this.barBorder = view3;
        this.barMoveBtn = imageView;
        this.barNotificationGroup = group;
        this.barNotificationIcon = imageView2;
        this.barNotificationMessage = textView;
        this.barStatus = textView2;
        this.barTitle = textView3;
        this.barVerticalDivider = view4;
        this.controlBtnBarrierRight = barrier;
        this.heightBarrier = barrier2;
        this.notificationTopBarrier = barrier3;
        this.overflowBtnBarrier = barrier4;
        this.overflowCloseBtn = imageButton;
        this.overflowEndBtn = imageView3;
        this.overflowEndText = textView4;
        this.overflowHorizontalDividerBottom = view5;
        this.overflowHorizontalDividerTop = view6;
        this.overflowMenuGroup = group2;
        this.overflowOpenBtn = imageButton2;
        this.pauseBtn = imageButton3;
        this.playBtn = imageButton4;
        this.spaceBarBottom = space;
        this.spaceBarTop = space2;
        this.spaceNotificationBottom = space3;
        this.spaceOverflowBottom = space4;
        this.spinnerBtn = imageButton5;
    }

    public static ControlBarBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ControlBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public BorderVisibilityObservable getBarBorderVisible() {
        return this.mBarBorderVisible;
    }

    @Nullable
    public BarButtonHandlers getBarButtonHandlers() {
        return this.mBarButtonHandlers;
    }

    @Nullable
    public ControlButtonStateObservable getControlButtonState() {
        return this.mControlButtonState;
    }

    @Nullable
    public ObservableField<String> getMessageText() {
        return this.mMessageText;
    }

    @Nullable
    public ObservableBoolean getNotificationVisible() {
        return this.mNotificationVisible;
    }

    @Nullable
    public ObservableBoolean getOverflowMenuVisible() {
        return this.mOverflowMenuVisible;
    }

    @Nullable
    public ObservableBoolean getPauseResumeClickable() {
        return this.mPauseResumeClickable;
    }

    @Nullable
    public StatusTextObservable getStatusText() {
        return this.mStatusText;
    }

    public abstract void setBarBorderVisible(@Nullable BorderVisibilityObservable borderVisibilityObservable);

    public abstract void setBarButtonHandlers(@Nullable BarButtonHandlers barButtonHandlers);

    public abstract void setControlButtonState(@Nullable ControlButtonStateObservable controlButtonStateObservable);

    public abstract void setMessageText(@Nullable ObservableField<String> observableField);

    public abstract void setNotificationVisible(@Nullable ObservableBoolean observableBoolean);

    public abstract void setOverflowMenuVisible(@Nullable ObservableBoolean observableBoolean);

    public abstract void setPauseResumeClickable(@Nullable ObservableBoolean observableBoolean);

    public abstract void setStatusText(@Nullable StatusTextObservable statusTextObservable);

    @Deprecated
    public static ControlBarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ControlBarBinding) ViewDataBinding.bind(obj, view, R.layout.control_bar);
    }

    @NonNull
    @Deprecated
    public static ControlBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ControlBarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.control_bar, viewGroup, z, obj);
    }

    @NonNull
    public static ControlBarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ControlBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ControlBarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.control_bar, null, false, obj);
    }
}
