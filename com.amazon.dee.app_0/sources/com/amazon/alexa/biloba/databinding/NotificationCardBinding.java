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
import com.amazon.alexa.biloba.view.cards.NotificationCard;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public abstract class NotificationCardBinding extends ViewDataBinding {
    @NonNull
    public final ImageView bilobaOverflowMenu;
    @Bindable
    protected NotificationCard mCard;
    @NonNull
    public final Button notificationCallButton;
    @NonNull
    public final CardView notificationCard;
    @NonNull
    public final FontTextView notificationCardBody;
    @NonNull
    public final FontTextView notificationCardHeader;
    @NonNull
    public final RelativeLayout notificationCardLayout;
    @NonNull
    public final FontTextView notificationCardTitle;
    @NonNull
    public final LinearLayout notificationCardTitleContainer;
    @NonNull
    public final Button notificationDropInButton;
    @NonNull
    public final LinearLayout notificationOverflowMenu;

    /* JADX INFO: Access modifiers changed from: protected */
    public NotificationCardBinding(Object obj, View view, int i, ImageView imageView, Button button, CardView cardView, FontTextView fontTextView, FontTextView fontTextView2, RelativeLayout relativeLayout, FontTextView fontTextView3, LinearLayout linearLayout, Button button2, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.bilobaOverflowMenu = imageView;
        this.notificationCallButton = button;
        this.notificationCard = cardView;
        this.notificationCardBody = fontTextView;
        this.notificationCardHeader = fontTextView2;
        this.notificationCardLayout = relativeLayout;
        this.notificationCardTitle = fontTextView3;
        this.notificationCardTitleContainer = linearLayout;
        this.notificationDropInButton = button2;
        this.notificationOverflowMenu = linearLayout2;
    }

    public static NotificationCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static NotificationCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public NotificationCard getCard() {
        return this.mCard;
    }

    public abstract void setCard(@Nullable NotificationCard notificationCard);

    @Deprecated
    public static NotificationCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (NotificationCardBinding) ViewDataBinding.bind(obj, view, R.layout.notification_card);
    }

    @NonNull
    @Deprecated
    public static NotificationCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (NotificationCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.notification_card, viewGroup, z, obj);
    }

    @NonNull
    public static NotificationCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static NotificationCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (NotificationCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.notification_card, null, false, obj);
    }
}
