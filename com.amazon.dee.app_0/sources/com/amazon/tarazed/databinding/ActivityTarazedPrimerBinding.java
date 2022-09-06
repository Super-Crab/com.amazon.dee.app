package com.amazon.tarazed.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.tarazed.R;
import com.amazon.tarazed.activity.TarazedPrimerActivity;
/* loaded from: classes13.dex */
public abstract class ActivityTarazedPrimerBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout activityTarazedPrimer;
    @Bindable
    protected TarazedPrimerActivity.ButtonHandlers mButtonHandlers;
    @NonNull
    public final ImageView primerAmazonLogo;
    @NonNull
    public final TextView primerBody;
    @NonNull
    public final Button primerContinueButton;
    @NonNull
    public final ImageButton primerDeclineButton;
    @NonNull
    public final ScrollView primerScrollView;
    @NonNull
    public final TextView primerSubtitle;
    @NonNull
    public final TextView primerTitle;

    /* JADX INFO: Access modifiers changed from: protected */
    public ActivityTarazedPrimerBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, Button button, ImageButton imageButton, ScrollView scrollView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.activityTarazedPrimer = constraintLayout;
        this.primerAmazonLogo = imageView;
        this.primerBody = textView;
        this.primerContinueButton = button;
        this.primerDeclineButton = imageButton;
        this.primerScrollView = scrollView;
        this.primerSubtitle = textView2;
        this.primerTitle = textView3;
    }

    public static ActivityTarazedPrimerBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityTarazedPrimerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public TarazedPrimerActivity.ButtonHandlers getButtonHandlers() {
        return this.mButtonHandlers;
    }

    public abstract void setButtonHandlers(@Nullable TarazedPrimerActivity.ButtonHandlers buttonHandlers);

    @Deprecated
    public static ActivityTarazedPrimerBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityTarazedPrimerBinding) ViewDataBinding.bind(obj, view, R.layout.activity_tarazed_primer);
    }

    @NonNull
    @Deprecated
    public static ActivityTarazedPrimerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityTarazedPrimerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_tarazed_primer, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityTarazedPrimerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityTarazedPrimerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityTarazedPrimerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_tarazed_primer, null, false, obj);
    }
}
