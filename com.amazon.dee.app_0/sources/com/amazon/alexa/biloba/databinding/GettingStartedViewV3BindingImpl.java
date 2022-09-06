package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.callback.OnClickListener;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewModelV3;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewV3;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.BulletListText;
/* loaded from: classes6.dex */
public class GettingStartedViewV3BindingImpl extends GettingStartedViewV3Binding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback1;
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.getting_started_image, 2);
        sViewsWithIds.put(R.id.getting_started_title, 3);
        sViewsWithIds.put(R.id.getting_started_bullet_1, 4);
        sViewsWithIds.put(R.id.getting_started_bullet_2, 5);
        sViewsWithIds.put(R.id.getting_started_bullet_3, 6);
        sViewsWithIds.put(R.id.getting_started_bullet_4, 7);
        sViewsWithIds.put(R.id.legacy_customer_title, 8);
        sViewsWithIds.put(R.id.legacy_customer_body, 9);
        sViewsWithIds.put(R.id.getting_started_button_layout, 10);
    }

    public GettingStartedViewV3BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    @Override // com.amazon.alexa.biloba.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        GettingStartedViewV3 gettingStartedViewV3 = this.mHandler;
        if (gettingStartedViewV3 != null) {
            gettingStartedViewV3.navigateToLearnMore();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GettingStartedViewV3 gettingStartedViewV3 = this.mHandler;
        boolean z = false;
        int i = ((5 & j) > 0L ? 1 : ((5 & j) == 0L ? 0 : -1));
        if (i != 0 && gettingStartedViewV3 != null) {
            z = gettingStartedViewV3.isLandscape();
        }
        if ((j & 4) != 0) {
            this.gettingStartedCreateRelationship.setOnClickListener(this.mCallback1);
        }
        if (i != 0) {
            ConstraintLayoutAdapter.setWidth(this.gettingStartedLayout, z);
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
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.amazon.alexa.biloba.databinding.GettingStartedViewV3Binding
    public void setHandler(@Nullable GettingStartedViewV3 gettingStartedViewV3) {
        this.mHandler = gettingStartedViewV3;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((GettingStartedViewV3) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((GettingStartedViewModelV3) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.GettingStartedViewV3Binding
    public void setViewModel(@Nullable GettingStartedViewModelV3 gettingStartedViewModelV3) {
        this.mViewModel = gettingStartedViewModelV3;
    }

    private GettingStartedViewV3BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (BulletListText) objArr[4], (BulletListText) objArr[5], (BulletListText) objArr[6], (BulletListText) objArr[7], (LinearLayout) objArr[10], (Button) objArr[1], (ImageView) objArr[2], (ConstraintLayout) objArr[0], (FontTextView) objArr[3], (FontTextView) objArr[9], (FontTextView) objArr[8]);
        this.mDirtyFlags = -1L;
        this.gettingStartedCreateRelationship.setTag(null);
        this.gettingStartedLayout.setTag(null);
        setRootTag(view);
        this.mCallback1 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
