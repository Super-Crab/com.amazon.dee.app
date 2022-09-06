package com.amazon.dee.app.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.generated.callback.OnClickListener;
import com.amazon.dee.app.ui.external.ExternalUIHandler;
/* loaded from: classes12.dex */
public class ExternalUiBindingImpl extends ExternalUiBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback11;
    private long mDirtyFlags;
    @NonNull
    private final ImageView mboundView1;

    static {
        sViewsWithIds.put(R.id.external_ui_header, 2);
        sViewsWithIds.put(R.id.external_ui_title, 3);
        sViewsWithIds.put(R.id.external_ui_content, 4);
    }

    public ExternalUiBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    @Override // com.amazon.dee.app.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        ExternalUIHandler externalUIHandler = this.mHandler;
        if (externalUIHandler != null) {
            externalUIHandler.onCancelClicked();
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
            this.mboundView1.setOnClickListener(this.mCallback11);
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

    @Override // com.amazon.dee.app.databinding.ExternalUiBinding
    public void setHandler(@Nullable ExternalUIHandler externalUIHandler) {
        this.mHandler = externalUIHandler;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (11 == i) {
            setHandler((ExternalUIHandler) obj);
            return true;
        }
        return false;
    }

    private ExternalUiBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (RelativeLayout) objArr[4], (LinearLayout) objArr[0], (RelativeLayout) objArr[2], (TextView) objArr[3]);
        this.mDirtyFlags = -1L;
        this.externalUiFrameLayout.setTag(null);
        this.mboundView1 = (ImageView) objArr[1];
        this.mboundView1.setTag(null);
        setRootTag(view);
        this.mCallback11 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
