package com.amazon.alexa.biloba.databinding;

import android.text.SpannableStringBuilder;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
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
import com.amazon.alexa.biloba.view.comms.CommsSetupInstructionsView;
import com.amazon.alexa.biloba.view.comms.CommsSetupViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.NumberedListText;
/* loaded from: classes6.dex */
public class CommsSetupInstructionsViewBindingImpl extends CommsSetupInstructionsViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private OnClickListenerImpl mHandlerOnClickOkAndroidViewViewOnClickListener;
    @NonNull
    private final LinearLayout mboundView0;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private CommsSetupInstructionsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onClickOk(view);
        }

        public OnClickListenerImpl setValue(CommsSetupInstructionsView commsSetupInstructionsView) {
            this.value = commsSetupInstructionsView;
            if (commsSetupInstructionsView == null) {
                return null;
            }
            return this;
        }
    }

    static {
        sViewsWithIds.put(R.id.comms_setup_instruct_step1, 4);
        sViewsWithIds.put(R.id.comms_setup_instruct_step2, 5);
        sViewsWithIds.put(R.id.comms_setup_instruct_step3, 6);
        sViewsWithIds.put(R.id.comms_setup_instruct_step4, 7);
    }

    public CommsSetupInstructionsViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
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
        OnClickListenerImpl onClickListenerImpl;
        SpannableStringBuilder spannableStringBuilder;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CommsSetupInstructionsView commsSetupInstructionsView = this.mHandler;
        CommsSetupViewModel commsSetupViewModel = this.mViewmodel;
        int i = ((10 & j) > 0L ? 1 : ((10 & j) == 0L ? 0 : -1));
        String str = null;
        if (i == 0 || commsSetupInstructionsView == null) {
            onClickListenerImpl = null;
            spannableStringBuilder = null;
        } else {
            spannableStringBuilder = commsSetupInstructionsView.getResendLinkText();
            OnClickListenerImpl onClickListenerImpl2 = this.mHandlerOnClickOkAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mHandlerOnClickOkAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(commsSetupInstructionsView);
        }
        int i2 = ((13 & j) > 0L ? 1 : ((13 & j) == 0L ? 0 : -1));
        if (i2 != 0) {
            LiveData<CareActor> careRecipient = commsSetupViewModel != null ? commsSetupViewModel.getCareRecipient() : null;
            updateLiveDataRegistration(0, careRecipient);
            if (commsSetupViewModel != null) {
                str = commsSetupViewModel.getDisplayName(careRecipient);
            }
            str = this.commsSetupInstructMessage.getResources().getString(R.string.comms_setup_instruct_message, str);
        }
        if (i2 != 0) {
            TextViewBindingAdapter.setText(this.commsSetupInstructMessage, str);
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.commsSetupInstructionsMessage, spannableStringBuilder);
            this.okButton.setOnClickListener(onClickListenerImpl);
        }
        if ((j & 8) != 0) {
            TextViewWithLinks.setTextViewHasLinks(this.commsSetupInstructionsMessage, true);
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

    @Override // com.amazon.alexa.biloba.databinding.CommsSetupInstructionsViewBinding
    public void setHandler(@Nullable CommsSetupInstructionsView commsSetupInstructionsView) {
        this.mHandler = commsSetupInstructionsView;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((CommsSetupInstructionsView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((CommsSetupViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.CommsSetupInstructionsViewBinding
    public void setViewmodel(@Nullable CommsSetupViewModel commsSetupViewModel) {
        this.mViewmodel = commsSetupViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private CommsSetupInstructionsViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (FontTextView) objArr[1], (NumberedListText) objArr[4], (NumberedListText) objArr[5], (NumberedListText) objArr[6], (NumberedListText) objArr[7], (FontTextView) objArr[2], (Button) objArr[3]);
        this.mDirtyFlags = -1L;
        this.commsSetupInstructMessage.setTag(null);
        this.commsSetupInstructionsMessage.setTag(null);
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.okButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
