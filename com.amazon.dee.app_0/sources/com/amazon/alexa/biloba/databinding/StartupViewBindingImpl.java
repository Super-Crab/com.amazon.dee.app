package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.callback.OnClickListener;
import com.amazon.alexa.biloba.view.startup.StartupViewModel;
import com.amazon.alexa.mosaic.view.ErrorView;
/* loaded from: classes6.dex */
public class StartupViewBindingImpl extends StartupViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback2;
    private long mDirtyFlags;
    @NonNull
    private final FrameLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.loading_view, 2);
    }

    public StartupViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetError(LiveData<Throwable> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsLoading(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.biloba.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        StartupViewModel startupViewModel = this.mViewmodel;
        if (startupViewModel != null) {
            startupViewModel.sendRequest();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int i;
        boolean z;
        int i2;
        boolean z2;
        String str;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        StartupViewModel startupViewModel = this.mViewmodel;
        if ((15 & j) != 0) {
            int i3 = ((j & 13) > 0L ? 1 : ((j & 13) == 0L ? 0 : -1));
            if (i3 != 0) {
                LiveData<Throwable> error = startupViewModel != null ? startupViewModel.getError() : null;
                updateLiveDataRegistration(0, error);
                z2 = (error != null ? error.getValue() : null) != null;
                if (i3 != 0) {
                    if (z2) {
                        j2 = j | 32;
                        j3 = 128;
                    } else {
                        j2 = j | 16;
                        j3 = 64;
                    }
                    j = j2 | j3;
                }
                i = z2 ? 0 : 8;
            } else {
                i = 0;
                z2 = false;
            }
            int i4 = ((j & 14) > 0L ? 1 : ((j & 14) == 0L ? 0 : -1));
            if (i4 != 0) {
                LiveData<Boolean> isLoading = startupViewModel != null ? startupViewModel.getIsLoading() : null;
                updateLiveDataRegistration(1, isLoading);
                Boolean value = isLoading != null ? isLoading.getValue() : null;
                z = value != Boolean.TRUE;
                boolean z3 = value == Boolean.TRUE;
                if (i4 != 0) {
                    j |= z3 ? 512L : 256L;
                }
                if (!z3) {
                    i2 = 8;
                }
            } else {
                z = false;
            }
            i2 = 0;
        } else {
            i = 0;
            z = false;
            i2 = 0;
            z2 = false;
        }
        String errorViewMetricName = ((j & 32) == 0 || startupViewModel == null) ? null : startupViewModel.getErrorViewMetricName();
        int i5 = ((j & 13) > 0L ? 1 : ((j & 13) == 0L ? 0 : -1));
        if (i5 != 0) {
            str = z2 ? errorViewMetricName : null;
        } else {
            str = null;
        }
        if (i5 != 0) {
            this.errorView.setMetricName(str);
            this.errorView.setVisibility(i);
        }
        if ((j & 14) != 0) {
            ViewBindingAdapter.setOnClick(this.errorView, this.mCallback2, z);
            this.loadingView.setVisibility(i2);
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
            this.mDirtyFlags = 8L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i == 1) {
                return onChangeViewmodelGetIsLoading((LiveData) obj, i2);
            }
            return false;
        }
        return onChangeViewmodelGetError((LiveData) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewmodel == i) {
            setViewmodel((StartupViewModel) obj);
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.biloba.databinding.StartupViewBinding
    public void setViewmodel(@Nullable StartupViewModel startupViewModel) {
        this.mViewmodel = startupViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private StartupViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (ErrorView) objArr[1], (View) objArr[2]);
        this.mDirtyFlags = -1L;
        this.errorView.setTag(null);
        this.mboundView0 = (FrameLayout) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        this.mCallback2 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
