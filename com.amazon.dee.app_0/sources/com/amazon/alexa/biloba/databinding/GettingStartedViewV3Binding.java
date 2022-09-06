package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewModelV3;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewV3;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.BulletListText;
/* loaded from: classes6.dex */
public abstract class GettingStartedViewV3Binding extends ViewDataBinding {
    @NonNull
    public final BulletListText gettingStartedBullet1;
    @NonNull
    public final BulletListText gettingStartedBullet2;
    @NonNull
    public final BulletListText gettingStartedBullet3;
    @NonNull
    public final BulletListText gettingStartedBullet4;
    @NonNull
    public final LinearLayout gettingStartedButtonLayout;
    @NonNull
    public final Button gettingStartedCreateRelationship;
    @NonNull
    public final ImageView gettingStartedImage;
    @NonNull
    public final ConstraintLayout gettingStartedLayout;
    @NonNull
    public final FontTextView gettingStartedTitle;
    @NonNull
    public final FontTextView legacyCustomerBody;
    @NonNull
    public final FontTextView legacyCustomerTitle;
    @Bindable
    protected GettingStartedViewV3 mHandler;
    @Bindable
    protected GettingStartedViewModelV3 mViewModel;

    /* JADX INFO: Access modifiers changed from: protected */
    public GettingStartedViewV3Binding(Object obj, View view, int i, BulletListText bulletListText, BulletListText bulletListText2, BulletListText bulletListText3, BulletListText bulletListText4, LinearLayout linearLayout, Button button, ImageView imageView, ConstraintLayout constraintLayout, FontTextView fontTextView, FontTextView fontTextView2, FontTextView fontTextView3) {
        super(obj, view, i);
        this.gettingStartedBullet1 = bulletListText;
        this.gettingStartedBullet2 = bulletListText2;
        this.gettingStartedBullet3 = bulletListText3;
        this.gettingStartedBullet4 = bulletListText4;
        this.gettingStartedButtonLayout = linearLayout;
        this.gettingStartedCreateRelationship = button;
        this.gettingStartedImage = imageView;
        this.gettingStartedLayout = constraintLayout;
        this.gettingStartedTitle = fontTextView;
        this.legacyCustomerBody = fontTextView2;
        this.legacyCustomerTitle = fontTextView3;
    }

    public static GettingStartedViewV3Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static GettingStartedViewV3Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public GettingStartedViewV3 getHandler() {
        return this.mHandler;
    }

    @Nullable
    public GettingStartedViewModelV3 getViewModel() {
        return this.mViewModel;
    }

    public abstract void setHandler(@Nullable GettingStartedViewV3 gettingStartedViewV3);

    public abstract void setViewModel(@Nullable GettingStartedViewModelV3 gettingStartedViewModelV3);

    @Deprecated
    public static GettingStartedViewV3Binding bind(@NonNull View view, @Nullable Object obj) {
        return (GettingStartedViewV3Binding) ViewDataBinding.bind(obj, view, R.layout.getting_started_view_v3);
    }

    @NonNull
    @Deprecated
    public static GettingStartedViewV3Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (GettingStartedViewV3Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.getting_started_view_v3, viewGroup, z, obj);
    }

    @NonNull
    public static GettingStartedViewV3Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static GettingStartedViewV3Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (GettingStartedViewV3Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.getting_started_view_v3, null, false, obj);
    }
}
