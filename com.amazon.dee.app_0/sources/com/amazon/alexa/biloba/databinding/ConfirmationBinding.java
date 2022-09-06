package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public abstract class ConfirmationBinding extends ViewDataBinding {
    @NonNull
    public final FontTextView bodyText;
    @NonNull
    public final ImageView controlIcon;
    @NonNull
    public final FontTextView headlineText;
    @NonNull
    public final FontTextView hintText;
    @NonNull
    public final FontTextView hyperlinkText;
    @NonNull
    public final FontTextView linkText;
    @Bindable
    protected ConfirmationViewModel mViewModel;
    @NonNull
    public final Button okButton;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfirmationBinding(Object obj, View view, int i, FontTextView fontTextView, ImageView imageView, FontTextView fontTextView2, FontTextView fontTextView3, FontTextView fontTextView4, FontTextView fontTextView5, Button button) {
        super(obj, view, i);
        this.bodyText = fontTextView;
        this.controlIcon = imageView;
        this.headlineText = fontTextView2;
        this.hintText = fontTextView3;
        this.hyperlinkText = fontTextView4;
        this.linkText = fontTextView5;
        this.okButton = button;
    }

    public static ConfirmationBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public ConfirmationViewModel getViewModel() {
        return this.mViewModel;
    }

    public abstract void setViewModel(@Nullable ConfirmationViewModel confirmationViewModel);

    @Deprecated
    public static ConfirmationBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ConfirmationBinding) ViewDataBinding.bind(obj, view, R.layout.confirmation);
    }

    @NonNull
    @Deprecated
    public static ConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ConfirmationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.confirmation, viewGroup, z, obj);
    }

    @NonNull
    public static ConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ConfirmationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.confirmation, null, false, obj);
    }
}
