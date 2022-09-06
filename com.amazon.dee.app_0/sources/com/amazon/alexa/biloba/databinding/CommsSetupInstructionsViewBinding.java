package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.comms.CommsSetupInstructionsView;
import com.amazon.alexa.biloba.view.comms.CommsSetupViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.NumberedListText;
/* loaded from: classes6.dex */
public abstract class CommsSetupInstructionsViewBinding extends ViewDataBinding {
    @NonNull
    public final FontTextView commsSetupInstructMessage;
    @NonNull
    public final NumberedListText commsSetupInstructStep1;
    @NonNull
    public final NumberedListText commsSetupInstructStep2;
    @NonNull
    public final NumberedListText commsSetupInstructStep3;
    @NonNull
    public final NumberedListText commsSetupInstructStep4;
    @NonNull
    public final FontTextView commsSetupInstructionsMessage;
    @Bindable
    protected CommsSetupInstructionsView mHandler;
    @Bindable
    protected CommsSetupViewModel mViewmodel;
    @NonNull
    public final Button okButton;

    /* JADX INFO: Access modifiers changed from: protected */
    public CommsSetupInstructionsViewBinding(Object obj, View view, int i, FontTextView fontTextView, NumberedListText numberedListText, NumberedListText numberedListText2, NumberedListText numberedListText3, NumberedListText numberedListText4, FontTextView fontTextView2, Button button) {
        super(obj, view, i);
        this.commsSetupInstructMessage = fontTextView;
        this.commsSetupInstructStep1 = numberedListText;
        this.commsSetupInstructStep2 = numberedListText2;
        this.commsSetupInstructStep3 = numberedListText3;
        this.commsSetupInstructStep4 = numberedListText4;
        this.commsSetupInstructionsMessage = fontTextView2;
        this.okButton = button;
    }

    public static CommsSetupInstructionsViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CommsSetupInstructionsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public CommsSetupInstructionsView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public CommsSetupViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setHandler(@Nullable CommsSetupInstructionsView commsSetupInstructionsView);

    public abstract void setViewmodel(@Nullable CommsSetupViewModel commsSetupViewModel);

    @Deprecated
    public static CommsSetupInstructionsViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CommsSetupInstructionsViewBinding) ViewDataBinding.bind(obj, view, R.layout.comms_setup_instructions_view);
    }

    @NonNull
    @Deprecated
    public static CommsSetupInstructionsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CommsSetupInstructionsViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.comms_setup_instructions_view, viewGroup, z, obj);
    }

    @NonNull
    public static CommsSetupInstructionsViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CommsSetupInstructionsViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CommsSetupInstructionsViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.comms_setup_instructions_view, null, false, obj);
    }
}
