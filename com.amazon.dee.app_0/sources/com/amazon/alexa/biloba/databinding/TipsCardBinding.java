package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.cards.TipsCard;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public abstract class TipsCardBinding extends ViewDataBinding {
    @NonNull
    public final ImageView bilobaOverflowMenu;
    @Bindable
    protected TipsCard mCard;
    @NonNull
    public final CardView tipsCard;
    @NonNull
    public final FontTextView tipsCardBody;
    @NonNull
    public final Button tipsCardButton;
    @NonNull
    public final ImageView tipsCardIcon;
    @NonNull
    public final RelativeLayout tipsCardLayout;
    @NonNull
    public final FontTextView tipsCardTitle;
    @NonNull
    public final LinearLayout tipsCardTitleContainer;
    @NonNull
    public final LinearLayout tipsOverflowMenu;

    /* JADX INFO: Access modifiers changed from: protected */
    public TipsCardBinding(Object obj, View view, int i, ImageView imageView, CardView cardView, FontTextView fontTextView, Button button, ImageView imageView2, RelativeLayout relativeLayout, FontTextView fontTextView2, LinearLayout linearLayout, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.bilobaOverflowMenu = imageView;
        this.tipsCard = cardView;
        this.tipsCardBody = fontTextView;
        this.tipsCardButton = button;
        this.tipsCardIcon = imageView2;
        this.tipsCardLayout = relativeLayout;
        this.tipsCardTitle = fontTextView2;
        this.tipsCardTitleContainer = linearLayout;
        this.tipsOverflowMenu = linearLayout2;
    }

    public static TipsCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TipsCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public TipsCard getCard() {
        return this.mCard;
    }

    public abstract void setCard(@Nullable TipsCard tipsCard);

    @Deprecated
    public static TipsCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TipsCardBinding) ViewDataBinding.bind(obj, view, R.layout.tips_card);
    }

    @NonNull
    @Deprecated
    public static TipsCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TipsCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tips_card, viewGroup, z, obj);
    }

    @NonNull
    public static TipsCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TipsCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TipsCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.tips_card, null, false, obj);
    }
}
