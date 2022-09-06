package com.amazon.tarazed.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.amazon.tarazed.BR;
import com.amazon.tarazed.R;
import com.amazon.tarazed.generated.callback.OnClickListener;
import com.amazon.tarazed.ui.menu.databinding.BarButtonHandlers;
import com.amazon.tarazed.ui.menu.databinding.BorderVisibilityObservable;
import com.amazon.tarazed.ui.menu.databinding.ControlButtonState;
import com.amazon.tarazed.ui.menu.databinding.ControlButtonStateObservable;
import com.amazon.tarazed.ui.menu.databinding.DataBindingAdapters;
import com.amazon.tarazed.ui.menu.databinding.StatusTextObservable;
/* loaded from: classes13.dex */
public class ControlBarBindingImpl extends ControlBarBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback3;
    @Nullable
    private final View.OnClickListener mCallback4;
    @Nullable
    private final View.OnClickListener mCallback5;
    @Nullable
    private final View.OnClickListener mCallback6;
    @Nullable
    private final View.OnClickListener mCallback7;
    @Nullable
    private final View.OnClickListener mCallback8;
    @Nullable
    private final View.OnClickListener mCallback9;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.notification_top_barrier, 16);
        sViewsWithIds.put(R.id.height_barrier, 17);
        sViewsWithIds.put(R.id.bar, 18);
        sViewsWithIds.put(R.id.space_bar_top, 19);
        sViewsWithIds.put(R.id.space_overflow_bottom, 20);
        sViewsWithIds.put(R.id.bar_notification_icon, 21);
        sViewsWithIds.put(R.id.space_notification_bottom, 22);
        sViewsWithIds.put(R.id.overflow_btn_barrier, 23);
        sViewsWithIds.put(R.id.control_btn_barrier_right, 24);
        sViewsWithIds.put(R.id.space_bar_bottom, 25);
        sViewsWithIds.put(R.id.bar_vertical_divider, 26);
        sViewsWithIds.put(R.id.bar_move_btn, 27);
    }

    public ControlBarBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 28, sIncludes, sViewsWithIds));
    }

    private boolean onChangeBarBorderVisible(BorderVisibilityObservable borderVisibilityObservable, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeControlButtonState(ControlButtonStateObservable controlButtonStateObservable, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMessageText(ObservableField<String> observableField, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeNotificationVisible(ObservableBoolean observableBoolean, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeOverflowMenuVisible(ObservableBoolean observableBoolean, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangePauseResumeClickable(ObservableBoolean observableBoolean, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeStatusText(StatusTextObservable statusTextObservable, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // com.amazon.tarazed.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = true;
        switch (i) {
            case 1:
                BarButtonHandlers barButtonHandlers = this.mBarButtonHandlers;
                if (barButtonHandlers == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                barButtonHandlers.onClickEnd();
                return;
            case 2:
                BarButtonHandlers barButtonHandlers2 = this.mBarButtonHandlers;
                if (barButtonHandlers2 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                barButtonHandlers2.onClickEnd();
                return;
            case 3:
                BarButtonHandlers barButtonHandlers3 = this.mBarButtonHandlers;
                if (barButtonHandlers3 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                barButtonHandlers3.onClickPauseResume();
                return;
            case 4:
                BarButtonHandlers barButtonHandlers4 = this.mBarButtonHandlers;
                if (barButtonHandlers4 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                barButtonHandlers4.onClickPauseResume();
                return;
            case 5:
                BarButtonHandlers barButtonHandlers5 = this.mBarButtonHandlers;
                if (barButtonHandlers5 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                barButtonHandlers5.onClickPauseResume();
                return;
            case 6:
                BarButtonHandlers barButtonHandlers6 = this.mBarButtonHandlers;
                if (barButtonHandlers6 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                barButtonHandlers6.onClickOpenMenu();
                return;
            case 7:
                BarButtonHandlers barButtonHandlers7 = this.mBarButtonHandlers;
                if (barButtonHandlers7 == null) {
                    z = false;
                }
                if (!z) {
                    return;
                }
                barButtonHandlers7.onClickCloseMenu();
                return;
            default:
                return;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        Drawable drawable;
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z2;
        int i6;
        int i7;
        int i8;
        long j2;
        long j3;
        long j4;
        long j5;
        View view;
        int i9;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        StatusTextObservable statusTextObservable = this.mStatusText;
        ObservableBoolean observableBoolean = this.mPauseResumeClickable;
        BorderVisibilityObservable borderVisibilityObservable = this.mBarBorderVisible;
        ObservableBoolean observableBoolean2 = this.mNotificationVisible;
        ControlButtonStateObservable controlButtonStateObservable = this.mControlButtonState;
        ObservableField<String> observableField = this.mMessageText;
        ObservableBoolean observableBoolean3 = this.mOverflowMenuVisible;
        String str = ((j & 257) == 0 || statusTextObservable == null) ? null : statusTextObservable.get();
        int i10 = 0;
        boolean z3 = ((j & 258) == 0 || observableBoolean == null) ? false : observableBoolean.get();
        int i11 = ((j & 260) > 0L ? 1 : ((j & 260) == 0L ? 0 : -1));
        if (i11 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(borderVisibilityObservable != null ? borderVisibilityObservable.get() : null);
            if (i11 != 0) {
                j |= safeUnbox ? 16384L : PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            if (safeUnbox) {
                view = this.barBorder;
                i9 = R.drawable.bar_border_orange;
            } else {
                view = this.barBorder;
                i9 = R.drawable.bar_border_gray;
            }
            drawable = ViewDataBinding.getDrawableFromResource(view, i9);
        } else {
            drawable = null;
        }
        int i12 = ((j & 264) > 0L ? 1 : ((j & 264) == 0L ? 0 : -1));
        if (i12 != 0) {
            z = observableBoolean2 != null ? observableBoolean2.get() : false;
            if (i12 != 0) {
                j = z ? j | 65536 : j | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            i = z ? 0 : 8;
        } else {
            i = 0;
            z = false;
        }
        int i13 = ((j & 272) > 0L ? 1 : ((j & 272) == 0L ? 0 : -1));
        if (i13 != 0) {
            ControlButtonState controlButtonState = controlButtonStateObservable != null ? controlButtonStateObservable.get() : null;
            boolean z4 = controlButtonState == ControlButtonState.SPINNER;
            boolean z5 = controlButtonState == ControlButtonState.PAUSE;
            boolean z6 = controlButtonState == ControlButtonState.PLAY;
            if (i13 != 0) {
                j |= z4 ? 1024L : 512L;
            }
            if ((j & 272) != 0) {
                j |= z5 ? 4194304L : PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            if ((j & 272) != 0) {
                j |= z6 ? 16777216L : 8388608L;
            }
            i3 = z4 ? 0 : 8;
            i4 = z5 ? 0 : 8;
            i2 = z6 ? 0 : 8;
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        String str2 = ((j & 288) == 0 || observableField == null) ? null : observableField.get();
        int i14 = ((j & 328) > 0L ? 1 : ((j & 328) == 0L ? 0 : -1));
        if (i14 != 0) {
            z2 = observableBoolean3 != null ? observableBoolean3.get() : false;
            if (i14 != 0) {
                if (z2) {
                    j4 = j | PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    j5 = 1073741824;
                } else {
                    j4 = j | PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    j5 = 536870912;
                }
                j = j4 | j5;
            }
            if ((j & 320) != 0) {
                if (z2) {
                    j2 = j | 67108864;
                    j3 = 268435456;
                } else {
                    j2 = j | 33554432;
                    j3 = 134217728;
                }
                j = j2 | j3;
            }
            if ((j & 320) != 0) {
                i5 = z2 ? 8 : 0;
                i6 = z2 ? 0 : 8;
            } else {
                i5 = 0;
                i6 = 0;
            }
        } else {
            i5 = 0;
            z2 = false;
            i6 = 0;
        }
        if ((j & 1073872896) != 0) {
            if (observableBoolean2 != null) {
                z = observableBoolean2.get();
            }
            if ((j & 264) != 0) {
                j = z ? j | 65536 : j | PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
        }
        int i15 = ((j & 328) > 0L ? 1 : ((j & 328) == 0L ? 0 : -1));
        if (i15 != 0) {
            boolean z7 = z2 ? true : z;
            if (!z2) {
                z = false;
            }
            if (i15 != 0) {
                j |= z7 ? PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM : PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            if ((j & 328) != 0) {
                j |= z ? 1048576L : PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            int i16 = z7 ? 0 : 8;
            if (!z) {
                i10 = 8;
            }
            i8 = i16;
            i7 = i10;
        } else {
            i7 = 0;
            i8 = 0;
        }
        if ((j & 260) != 0) {
            DataBindingAdapters.setDynamicBackground$TarazedAndroidLibrary_release(this.barBorder, drawable);
        }
        if ((j & 264) != 0) {
            this.barNotificationGroup.setVisibility(i);
        }
        if ((j & 288) != 0) {
            TextViewBindingAdapter.setText(this.barNotificationMessage, str2);
        }
        if ((256 & j) != 0) {
            DataBindingAdapters.setFont$TarazedAndroidLibrary_release(this.barNotificationMessage, "ember-regular");
            DataBindingAdapters.setFont$TarazedAndroidLibrary_release(this.barStatus, "ember-bold");
            DataBindingAdapters.addOnTextChangedListener$TarazedAndroidLibrary_release(this.barStatus, true);
            DataBindingAdapters.setFont$TarazedAndroidLibrary_release(this.barTitle, "ember-regular");
            this.overflowCloseBtn.setOnClickListener(this.mCallback9);
            this.overflowEndBtn.setOnClickListener(this.mCallback4);
            DataBindingAdapters.setFont$TarazedAndroidLibrary_release(this.overflowEndText, "ember-regular");
            this.overflowEndText.setOnClickListener(this.mCallback3);
            this.overflowOpenBtn.setOnClickListener(this.mCallback8);
        }
        if ((257 & j) != 0) {
            TextViewBindingAdapter.setText(this.barStatus, str);
        }
        if ((j & 320) != 0) {
            int i17 = i6;
            this.overflowCloseBtn.setVisibility(i17);
            this.overflowMenuGroup.setVisibility(i17);
            this.overflowOpenBtn.setVisibility(i5);
        }
        if ((j & 328) != 0) {
            this.overflowHorizontalDividerBottom.setVisibility(i7);
            this.overflowHorizontalDividerTop.setVisibility(i8);
        }
        if ((j & 272) != 0) {
            this.pauseBtn.setVisibility(i4);
            this.playBtn.setVisibility(i2);
            this.spinnerBtn.setVisibility(i3);
        }
        if ((j & 258) != 0) {
            ViewBindingAdapter.setOnClick(this.pauseBtn, this.mCallback5, z3);
            DataBindingAdapters.setButtonEnabled$TarazedAndroidLibrary_release(this.playBtn, z3);
            ViewBindingAdapter.setOnClick(this.playBtn, this.mCallback6, z3);
            ViewBindingAdapter.setOnClick(this.spinnerBtn, this.mCallback7, z3);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeStatusText((StatusTextObservable) obj, i2);
            case 1:
                return onChangePauseResumeClickable((ObservableBoolean) obj, i2);
            case 2:
                return onChangeBarBorderVisible((BorderVisibilityObservable) obj, i2);
            case 3:
                return onChangeNotificationVisible((ObservableBoolean) obj, i2);
            case 4:
                return onChangeControlButtonState((ControlButtonStateObservable) obj, i2);
            case 5:
                return onChangeMessageText((ObservableField) obj, i2);
            case 6:
                return onChangeOverflowMenuVisible((ObservableBoolean) obj, i2);
            default:
                return false;
        }
    }

    @Override // com.amazon.tarazed.databinding.ControlBarBinding
    public void setBarBorderVisible(@Nullable BorderVisibilityObservable borderVisibilityObservable) {
        updateRegistration(2, borderVisibilityObservable);
        this.mBarBorderVisible = borderVisibilityObservable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.barBorderVisible);
        super.requestRebind();
    }

    @Override // com.amazon.tarazed.databinding.ControlBarBinding
    public void setBarButtonHandlers(@Nullable BarButtonHandlers barButtonHandlers) {
        this.mBarButtonHandlers = barButtonHandlers;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.barButtonHandlers);
        super.requestRebind();
    }

    @Override // com.amazon.tarazed.databinding.ControlBarBinding
    public void setControlButtonState(@Nullable ControlButtonStateObservable controlButtonStateObservable) {
        updateRegistration(4, controlButtonStateObservable);
        this.mControlButtonState = controlButtonStateObservable;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.controlButtonState);
        super.requestRebind();
    }

    @Override // com.amazon.tarazed.databinding.ControlBarBinding
    public void setMessageText(@Nullable ObservableField<String> observableField) {
        updateRegistration(5, observableField);
        this.mMessageText = observableField;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.messageText);
        super.requestRebind();
    }

    @Override // com.amazon.tarazed.databinding.ControlBarBinding
    public void setNotificationVisible(@Nullable ObservableBoolean observableBoolean) {
        updateRegistration(3, observableBoolean);
        this.mNotificationVisible = observableBoolean;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.notificationVisible);
        super.requestRebind();
    }

    @Override // com.amazon.tarazed.databinding.ControlBarBinding
    public void setOverflowMenuVisible(@Nullable ObservableBoolean observableBoolean) {
        updateRegistration(6, observableBoolean);
        this.mOverflowMenuVisible = observableBoolean;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.overflowMenuVisible);
        super.requestRebind();
    }

    @Override // com.amazon.tarazed.databinding.ControlBarBinding
    public void setPauseResumeClickable(@Nullable ObservableBoolean observableBoolean) {
        updateRegistration(1, observableBoolean);
        this.mPauseResumeClickable = observableBoolean;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.pauseResumeClickable);
        super.requestRebind();
    }

    @Override // com.amazon.tarazed.databinding.ControlBarBinding
    public void setStatusText(@Nullable StatusTextObservable statusTextObservable) {
        updateRegistration(0, statusTextObservable);
        this.mStatusText = statusTextObservable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.statusText);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.statusText == i) {
            setStatusText((StatusTextObservable) obj);
        } else if (BR.pauseResumeClickable == i) {
            setPauseResumeClickable((ObservableBoolean) obj);
        } else if (BR.barBorderVisible == i) {
            setBarBorderVisible((BorderVisibilityObservable) obj);
        } else if (BR.notificationVisible == i) {
            setNotificationVisible((ObservableBoolean) obj);
        } else if (BR.controlButtonState == i) {
            setControlButtonState((ControlButtonStateObservable) obj);
        } else if (BR.messageText == i) {
            setMessageText((ObservableField) obj);
        } else if (BR.barButtonHandlers == i) {
            setBarButtonHandlers((BarButtonHandlers) obj);
        } else if (BR.overflowMenuVisible != i) {
            return false;
        } else {
            setOverflowMenuVisible((ObservableBoolean) obj);
        }
        return true;
    }

    private ControlBarBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 7, (View) objArr[18], (View) objArr[5], (ImageView) objArr[27], (Group) objArr[2], (ImageView) objArr[21], (TextView) objArr[8], (TextView) objArr[13], (TextView) objArr[12], (View) objArr[26], (Barrier) objArr[24], (Barrier) objArr[17], (Barrier) objArr[16], (Barrier) objArr[23], (ImageButton) objArr[15], (ImageView) objArr[7], (TextView) objArr[6], (View) objArr[4], (View) objArr[3], (Group) objArr[1], (ImageButton) objArr[14], (ImageButton) objArr[9], (ImageButton) objArr[10], (Space) objArr[25], (Space) objArr[19], (Space) objArr[22], (Space) objArr[20], (ImageButton) objArr[11]);
        this.mDirtyFlags = -1L;
        this.barBorder.setTag(null);
        this.barNotificationGroup.setTag(null);
        this.barNotificationMessage.setTag(null);
        this.barStatus.setTag(null);
        this.barTitle.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.overflowCloseBtn.setTag(null);
        this.overflowEndBtn.setTag(null);
        this.overflowEndText.setTag(null);
        this.overflowHorizontalDividerBottom.setTag(null);
        this.overflowHorizontalDividerTop.setTag(null);
        this.overflowMenuGroup.setTag(null);
        this.overflowOpenBtn.setTag(null);
        this.pauseBtn.setTag(null);
        this.playBtn.setTag(null);
        this.spinnerBtn.setTag(null);
        setRootTag(view);
        this.mCallback6 = new OnClickListener(this, 4);
        this.mCallback9 = new OnClickListener(this, 7);
        this.mCallback5 = new OnClickListener(this, 3);
        this.mCallback8 = new OnClickListener(this, 6);
        this.mCallback4 = new OnClickListener(this, 2);
        this.mCallback7 = new OnClickListener(this, 5);
        this.mCallback3 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
