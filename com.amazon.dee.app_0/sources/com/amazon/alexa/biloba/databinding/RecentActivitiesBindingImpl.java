package com.amazon.alexa.biloba.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.callback.OnClickListener;
import com.amazon.alexa.biloba.view.recent.RecentActivityListViewModel;
import com.amazon.alexa.mosaic.view.ErrorView;
/* loaded from: classes6.dex */
public class RecentActivitiesBindingImpl extends RecentActivitiesBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback3;
    private long mDirtyFlags;
    @NonNull
    private final FrameLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.loading_view, 3);
        sViewsWithIds.put(R.id.recent_activities, 4);
        sViewsWithIds.put(R.id.recent_activities_list_view, 5);
    }

    public RecentActivitiesBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
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
        RecentActivityListViewModel recentActivityListViewModel = this.mViewmodel;
        if (recentActivityListViewModel != null) {
            recentActivityListViewModel.sendRequest();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        int i;
        int i2;
        boolean z2;
        boolean z3;
        LiveData<Throwable> liveData;
        Throwable th;
        boolean z4;
        long j2;
        int i3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        RecentActivityListViewModel recentActivityListViewModel = this.mViewmodel;
        int i4 = ((j & 15) > 0L ? 1 : ((j & 15) == 0L ? 0 : -1));
        long j3 = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        int i5 = 0;
        if (i4 != 0) {
            int i6 = ((j & 13) > 0L ? 1 : ((j & 13) == 0L ? 0 : -1));
            if (i6 != 0) {
                liveData = recentActivityListViewModel != null ? recentActivityListViewModel.getError() : null;
                updateLiveDataRegistration(0, liveData);
                th = liveData != null ? liveData.getValue() : null;
                boolean z5 = th == null;
                z3 = th != null;
                if (i6 != 0) {
                    j |= z5 ? 128L : 64L;
                }
                if ((j & 13) != 0) {
                    j = z3 ? j | 512 | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : j | 256 | 1024;
                }
                i = z5 ? 0 : 8;
                i3 = z3 ? 0 : 8;
            } else {
                i = 0;
                i3 = 0;
                z3 = false;
                liveData = null;
                th = null;
            }
            LiveData<Boolean> isLoading = recentActivityListViewModel != null ? recentActivityListViewModel.getIsLoading() : null;
            updateLiveDataRegistration(1, isLoading);
            Boolean value = isLoading != null ? isLoading.getValue() : null;
            z2 = ((j & 14) == 0 || value == Boolean.TRUE) ? false : true;
            z = value == Boolean.TRUE;
            if ((j & 15) != 0) {
                j = z ? j | PlaybackStateCompat.ACTION_PLAY_FROM_URI : j | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            i2 = i3;
        } else {
            z = false;
            i = 0;
            i2 = 0;
            z2 = false;
            z3 = false;
            liveData = null;
            th = null;
        }
        if ((j & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
            if (recentActivityListViewModel != null) {
                liveData = recentActivityListViewModel.getError();
            }
            updateLiveDataRegistration(0, liveData);
            if (liveData != null) {
                th = liveData.getValue();
            }
            z4 = th != null;
            if ((j & 13) != 0) {
                if (z4) {
                    j2 = j | 512;
                } else {
                    j2 = j | 256;
                    j3 = 1024;
                }
                j = j2 | j3;
            }
        } else {
            z4 = z3;
        }
        int i7 = ((j & 15) > 0L ? 1 : ((j & 15) == 0L ? 0 : -1));
        if (i7 != 0) {
            boolean z6 = z ? z4 : false;
            if (i7 != 0) {
                j |= z6 ? 32L : 16L;
            }
            if (!z6) {
                i5 = 8;
            }
        }
        String errorViewMetricName = ((j & 512) == 0 || recentActivityListViewModel == null) ? null : recentActivityListViewModel.getErrorViewMetricName();
        int i8 = ((j & 13) > 0L ? 1 : ((j & 13) == 0L ? 0 : -1));
        if (i8 == 0 || !z4) {
            errorViewMetricName = null;
        }
        if (i8 != 0) {
            this.errorView.setMetricName(errorViewMetricName);
            this.errorView.setVisibility(i2);
            this.refreshContainer.setVisibility(i);
        }
        if ((14 & j) != 0) {
            ViewBindingAdapter.setOnClick(this.errorView, this.mCallback3, z2);
        }
        if ((j & 15) != 0) {
            this.loadingView.setVisibility(i5);
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
            setViewmodel((RecentActivityListViewModel) obj);
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.biloba.databinding.RecentActivitiesBinding
    public void setViewmodel(@Nullable RecentActivityListViewModel recentActivityListViewModel) {
        this.mViewmodel = recentActivityListViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private RecentActivitiesBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (ErrorView) objArr[2], (View) objArr[3], (LinearLayout) objArr[4], (RecyclerView) objArr[5], (SwipeRefreshLayout) objArr[1]);
        this.mDirtyFlags = -1L;
        this.errorView.setTag(null);
        this.mboundView0 = (FrameLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.refreshContainer.setTag(null);
        setRootTag(view);
        this.mCallback3 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
