package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.comms.CommsSetupViewModel;
import com.amazon.alexa.biloba.view.comms.CommsShareSetupLinkView;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.NumberedListText;
/* loaded from: classes6.dex */
public abstract class CommsShareSetupLinkViewBinding extends ViewDataBinding {
    @NonNull
    public final FontTextView commsSetupInstructMessage;
    @NonNull
    public final NumberedListText commsSetupInstructStep1;
    @NonNull
    public final NumberedListText commsSetupInstructStep2;
    @Bindable
    protected CommsShareSetupLinkView mHandler;
    @Bindable
    protected CommsSetupViewModel mViewmodel;

    /* JADX INFO: Access modifiers changed from: protected */
    public CommsShareSetupLinkViewBinding(Object obj, View view, int i, FontTextView fontTextView, NumberedListText numberedListText, NumberedListText numberedListText2) {
        super(obj, view, i);
        this.commsSetupInstructMessage = fontTextView;
        this.commsSetupInstructStep1 = numberedListText;
        this.commsSetupInstructStep2 = numberedListText2;
    }

    public static CommsShareSetupLinkViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CommsShareSetupLinkViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public CommsShareSetupLinkView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public CommsSetupViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setHandler(@Nullable CommsShareSetupLinkView commsShareSetupLinkView);

    public abstract void setViewmodel(@Nullable CommsSetupViewModel commsSetupViewModel);

    @Deprecated
    public static CommsShareSetupLinkViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CommsShareSetupLinkViewBinding) ViewDataBinding.bind(obj, view, R.layout.comms_share_setup_link_view);
    }

    @NonNull
    @Deprecated
    public static CommsShareSetupLinkViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CommsShareSetupLinkViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.comms_share_setup_link_view, viewGroup, z, obj);
    }

    @NonNull
    public static CommsShareSetupLinkViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CommsShareSetupLinkViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CommsShareSetupLinkViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.comms_share_setup_link_view, null, false, obj);
    }
}
