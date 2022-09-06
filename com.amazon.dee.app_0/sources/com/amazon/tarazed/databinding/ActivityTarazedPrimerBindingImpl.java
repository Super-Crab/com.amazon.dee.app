package com.amazon.tarazed.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.tarazed.BR;
import com.amazon.tarazed.R;
import com.amazon.tarazed.activity.TarazedPrimerActivity;
import com.amazon.tarazed.generated.callback.OnClickListener;
import com.amazon.tarazed.ui.menu.databinding.DataBindingAdapters;
/* loaded from: classes13.dex */
public class ActivityTarazedPrimerBindingImpl extends ActivityTarazedPrimerBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback1;
    @Nullable
    private final View.OnClickListener mCallback2;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.primer_amazon_logo, 6);
        sViewsWithIds.put(R.id.primer_scroll_view, 7);
    }

    public ActivityTarazedPrimerBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    @Override // com.amazon.tarazed.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            TarazedPrimerActivity.ButtonHandlers buttonHandlers = this.mButtonHandlers;
            if (buttonHandlers != null) {
                z = true;
            }
            if (!z) {
                return;
            }
            buttonHandlers.onDecline();
        } else if (i != 2) {
        } else {
            TarazedPrimerActivity.ButtonHandlers buttonHandlers2 = this.mButtonHandlers;
            if (buttonHandlers2 != null) {
                z = true;
            }
            if (!z) {
                return;
            }
            buttonHandlers2.onAccept();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = 0;
        int i2 = ((j & 2) > 0L ? 1 : ((j & 2) == 0L ? 0 : -1));
        if (i2 != 0) {
            i = R.raw.primer_body;
        }
        if (i2 != 0) {
            DataBindingAdapters.setFont$TarazedAndroidLibrary_release(this.primerBody, "ember-regular");
            DataBindingAdapters.setHtml$TarazedAndroidLibrary_release(this.primerBody, i);
            this.primerContinueButton.setOnClickListener(this.mCallback2);
            DataBindingAdapters.setFont$TarazedAndroidLibrary_release(this.primerContinueButton, "ember-regular");
            this.primerDeclineButton.setOnClickListener(this.mCallback1);
            DataBindingAdapters.setFont$TarazedAndroidLibrary_release(this.primerSubtitle, "ember-regular");
            DataBindingAdapters.setFont$TarazedAndroidLibrary_release(this.primerTitle, "ember-regular");
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
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.amazon.tarazed.databinding.ActivityTarazedPrimerBinding
    public void setButtonHandlers(@Nullable TarazedPrimerActivity.ButtonHandlers buttonHandlers) {
        this.mButtonHandlers = buttonHandlers;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.buttonHandlers);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.buttonHandlers == i) {
            setButtonHandlers((TarazedPrimerActivity.ButtonHandlers) obj);
            return true;
        }
        return false;
    }

    private ActivityTarazedPrimerBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (ImageView) objArr[6], (TextView) objArr[4], (Button) objArr[5], (ImageButton) objArr[1], (ScrollView) objArr[7], (TextView) objArr[3], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        this.activityTarazedPrimer.setTag(null);
        this.primerBody.setTag(null);
        this.primerContinueButton.setTag(null);
        this.primerDeclineButton.setTag(null);
        this.primerSubtitle.setTag(null);
        this.primerTitle.setTag(null);
        setRootTag(view);
        this.mCallback1 = new OnClickListener(this, 1);
        this.mCallback2 = new OnClickListener(this, 2);
        invalidateAll();
    }
}
