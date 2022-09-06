package com.amazon.alexa.biloba.databinding;

import android.text.SpannableStringBuilder;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.view.comms.CommsSetupViewModel;
import com.amazon.alexa.biloba.view.comms.CommsShareSetupLinkView;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.NumberedListText;
/* loaded from: classes6.dex */
public class CommsShareSetupLinkViewBindingImpl extends CommsShareSetupLinkViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;

    public CommsShareSetupLinkViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetCareRecipient(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        SpannableStringBuilder spannableStringBuilder;
        String str;
        SpannableStringBuilder spannableStringBuilder2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CommsShareSetupLinkView commsShareSetupLinkView = this.mHandler;
        CommsSetupViewModel commsSetupViewModel = this.mViewmodel;
        int i = ((15 & j) > 0L ? 1 : ((15 & j) == 0L ? 0 : -1));
        if (i != 0) {
            spannableStringBuilder2 = ((j & 10) == 0 || commsShareSetupLinkView == null) ? null : commsShareSetupLinkView.getViewGuideText();
            LiveData<CareActor> careRecipient = commsSetupViewModel != null ? commsSetupViewModel.getCareRecipient() : null;
            updateLiveDataRegistration(0, careRecipient);
            String displayName = commsSetupViewModel != null ? commsSetupViewModel.getDisplayName(careRecipient) : null;
            str = (j & 13) != 0 ? this.commsSetupInstructMessage.getResources().getString(R.string.comms_share_setup_link_body, displayName) : null;
            spannableStringBuilder = commsShareSetupLinkView != null ? commsShareSetupLinkView.getShareLinkText(displayName) : null;
        } else {
            spannableStringBuilder = null;
            str = null;
            spannableStringBuilder2 = null;
        }
        if ((j & 13) != 0) {
            TextViewBindingAdapter.setText(this.commsSetupInstructMessage, str);
        }
        if (i != 0) {
            this.commsSetupInstructStep1.setPrimaryText(spannableStringBuilder);
        }
        if ((j & 10) != 0) {
            this.commsSetupInstructStep2.setPrimaryText(spannableStringBuilder2);
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
            return false;
        }
        return onChangeViewmodelGetCareRecipient((LiveData) obj, i2);
    }

    @Override // com.amazon.alexa.biloba.databinding.CommsShareSetupLinkViewBinding
    public void setHandler(@Nullable CommsShareSetupLinkView commsShareSetupLinkView) {
        this.mHandler = commsShareSetupLinkView;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((CommsShareSetupLinkView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((CommsSetupViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.CommsShareSetupLinkViewBinding
    public void setViewmodel(@Nullable CommsSetupViewModel commsSetupViewModel) {
        this.mViewmodel = commsSetupViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private CommsShareSetupLinkViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (FontTextView) objArr[1], (NumberedListText) objArr[2], (NumberedListText) objArr[3]);
        this.mDirtyFlags = -1L;
        this.commsSetupInstructMessage.setTag(null);
        this.commsSetupInstructStep1.setTag(null);
        this.commsSetupInstructStep2.setTag(null);
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
