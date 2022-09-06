package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.infoModal.InfoModalView;
import com.amazon.alexa.biloba.view.infoModal.InfoModalViewModel;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public abstract class InfoModalViewBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout commsPanel;
    @NonNull
    public final LinearLayout commsSection;
    @NonNull
    public final FontTextView infoModalBodyDescription1;
    @NonNull
    public final FontTextView infoModalBodyDescription2;
    @NonNull
    public final FontTextView infoModalBodyDescription3;
    @NonNull
    public final FontTextView infoModalCaptionHeader;
    @NonNull
    public final FontTextView infoModalCommsDescription1;
    @NonNull
    public final FontTextView infoModalCommsTitle;
    @NonNull
    public final FontTextView infoModalHeader;
    @NonNull
    public final Button infoModalOkButton;
    @Bindable
    protected InfoModalView mHandler;
    @Bindable
    protected InfoModalViewModel mViewModel;

    /* JADX INFO: Access modifiers changed from: protected */
    public InfoModalViewBinding(Object obj, View view, int i, LinearLayout linearLayout, LinearLayout linearLayout2, FontTextView fontTextView, FontTextView fontTextView2, FontTextView fontTextView3, FontTextView fontTextView4, FontTextView fontTextView5, FontTextView fontTextView6, FontTextView fontTextView7, Button button) {
        super(obj, view, i);
        this.commsPanel = linearLayout;
        this.commsSection = linearLayout2;
        this.infoModalBodyDescription1 = fontTextView;
        this.infoModalBodyDescription2 = fontTextView2;
        this.infoModalBodyDescription3 = fontTextView3;
        this.infoModalCaptionHeader = fontTextView4;
        this.infoModalCommsDescription1 = fontTextView5;
        this.infoModalCommsTitle = fontTextView6;
        this.infoModalHeader = fontTextView7;
        this.infoModalOkButton = button;
    }

    public static InfoModalViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static InfoModalViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public InfoModalView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public InfoModalViewModel getViewModel() {
        return this.mViewModel;
    }

    public abstract void setHandler(@Nullable InfoModalView infoModalView);

    public abstract void setViewModel(@Nullable InfoModalViewModel infoModalViewModel);

    @Deprecated
    public static InfoModalViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (InfoModalViewBinding) ViewDataBinding.bind(obj, view, R.layout.info_modal_view);
    }

    @NonNull
    @Deprecated
    public static InfoModalViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (InfoModalViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.info_modal_view, viewGroup, z, obj);
    }

    @NonNull
    public static InfoModalViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static InfoModalViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (InfoModalViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.info_modal_view, null, false, obj);
    }
}
