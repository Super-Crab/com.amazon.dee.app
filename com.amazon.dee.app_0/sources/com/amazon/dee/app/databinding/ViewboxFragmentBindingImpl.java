package com.amazon.dee.app.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.generated.callback.OnClickListener;
import com.amazon.dee.app.ui.clouddrive.ViewBoxImageView;
import com.amazon.dee.app.ui.clouddrive.ViewBoxOverlayView;
import com.amazon.dee.app.ui.clouddrive.ViewBoxViewModel;
/* loaded from: classes12.dex */
public class ViewboxFragmentBindingImpl extends ViewboxFragmentBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback10;
    @Nullable
    private final View.OnClickListener mCallback8;
    @Nullable
    private final View.OnClickListener mCallback9;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.viewbox, 6);
        sViewsWithIds.put(R.id.image, 7);
        sViewsWithIds.put(R.id.overlay, 8);
    }

    public ViewboxFragmentBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    @Override // com.amazon.dee.app.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            ViewBoxViewModel viewBoxViewModel = this.mViewModel;
            if (viewBoxViewModel != null) {
                z = true;
            }
            if (!z) {
                return;
            }
            viewBoxViewModel.gotoPrimePhotosApp();
        } else if (i == 2) {
            ViewBoxViewModel viewBoxViewModel2 = this.mViewModel;
            if (viewBoxViewModel2 != null) {
                z = true;
            }
            if (!z) {
                return;
            }
            viewBoxViewModel2.openTermsAndConditions();
        } else if (i != 3) {
        } else {
            ViewBoxViewModel viewBoxViewModel3 = this.mViewModel;
            if (viewBoxViewModel3 != null) {
                z = true;
            }
            if (!z) {
                return;
            }
            viewBoxViewModel3.back();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        if ((j & 2) != 0) {
            this.btnCancel.setOnClickListener(this.mCallback10);
            FontAdapter.setFont(this.btnCancel, "ember-medium");
            FontAdapter.setFont(this.btnDone, "ember-medium");
            FontAdapter.setFont(this.description, "ember-regular");
            this.primePhotos.setOnClickListener(this.mCallback8);
            FontAdapter.setFont(this.primePhotos, "ember-regular");
            this.tac.setOnClickListener(this.mCallback9);
            FontAdapter.setFont(this.tac, "ember-regular");
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

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (19 == i) {
            setViewModel((ViewBoxViewModel) obj);
            return true;
        }
        return false;
    }

    @Override // com.amazon.dee.app.databinding.ViewboxFragmentBinding
    public void setViewModel(@Nullable ViewBoxViewModel viewBoxViewModel) {
        this.mViewModel = viewBoxViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(19);
        super.requestRebind();
    }

    private ViewboxFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[4], (Button) objArr[5], (TextView) objArr[1], (RelativeLayout) objArr[0], (ViewBoxImageView) objArr[7], (ViewBoxOverlayView) objArr[8], (TextView) objArr[2], (TextView) objArr[3], (FrameLayout) objArr[6]);
        this.mDirtyFlags = -1L;
        this.btnCancel.setTag(null);
        this.btnDone.setTag(null);
        this.description.setTag(null);
        this.fullscreenContent.setTag(null);
        this.primePhotos.setTag(null);
        this.tac.setTag(null);
        setRootTag(view);
        this.mCallback10 = new OnClickListener(this, 3);
        this.mCallback9 = new OnClickListener(this, 2);
        this.mCallback8 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
