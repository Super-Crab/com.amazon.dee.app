package com.amazon.dee.app.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
public class ViewboxFragmentBindingLandImpl extends ViewboxFragmentBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback12;
    @Nullable
    private final View.OnClickListener mCallback13;
    @Nullable
    private final View.OnClickListener mCallback14;
    private long mDirtyFlags;
    @NonNull
    private final ScrollView mboundView0;

    static {
        sViewsWithIds.put(R.id.fullscreen_content, 6);
        sViewsWithIds.put(R.id.viewbox, 7);
        sViewsWithIds.put(R.id.image, 8);
        sViewsWithIds.put(R.id.overlay, 9);
    }

    public ViewboxFragmentBindingLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
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
            this.btnCancel.setOnClickListener(this.mCallback14);
            FontAdapter.setFont(this.btnCancel, "ember-medium");
            FontAdapter.setFont(this.btnDone, "ember-medium");
            FontAdapter.setFont(this.description, "ember-regular");
            this.primePhotos.setOnClickListener(this.mCallback12);
            FontAdapter.setFont(this.primePhotos, "ember-regular");
            this.tac.setOnClickListener(this.mCallback13);
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

    private ViewboxFragmentBindingLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[4], (Button) objArr[5], (TextView) objArr[1], (RelativeLayout) objArr[6], (ViewBoxImageView) objArr[8], (ViewBoxOverlayView) objArr[9], (TextView) objArr[2], (TextView) objArr[3], (FrameLayout) objArr[7]);
        this.mDirtyFlags = -1L;
        this.btnCancel.setTag(null);
        this.btnDone.setTag(null);
        this.description.setTag(null);
        this.mboundView0 = (ScrollView) objArr[0];
        this.mboundView0.setTag(null);
        this.primePhotos.setTag(null);
        this.tac.setTag(null);
        setRootTag(view);
        this.mCallback14 = new OnClickListener(this, 3);
        this.mCallback12 = new OnClickListener(this, 1);
        this.mCallback13 = new OnClickListener(this, 2);
        invalidateAll();
    }
}
