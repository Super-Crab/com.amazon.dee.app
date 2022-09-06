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
import com.amazon.alexa.biloba.view.comms.EmergencyContactView;
import com.amazon.alexa.biloba.view.comms.EmergencyContactViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ListItem;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.NoticeBannerView;
/* loaded from: classes6.dex */
public abstract class EmergencyContactViewBinding extends ViewDataBinding {
    @NonNull
    public final Button addEmergencyContactButton;
    @NonNull
    public final LinearLayout buttonPanel;
    @NonNull
    public final LinearLayout buttonPanelCg;
    @NonNull
    public final LinearLayout buttonPanelCr;
    @NonNull
    public final Button changeEmergencyContactButton;
    @NonNull
    public final Button changeEmergencyContactButtonCr;
    @NonNull
    public final ListItemLink emergencyContactCareGiver;
    @NonNull
    public final FontTextView emergencyContactCareRecipientHeader;
    @NonNull
    public final ListItem emergencyContactChange;
    @NonNull
    public final FontTextView emergencyContactDescription;
    @NonNull
    public final ListItem emergencyContactNotSet;
    @NonNull
    public final NoticeBannerView emergencyContactNoticeCommsNotSetupByCareGiver;
    @NonNull
    public final NoticeBannerView emergencyContactNoticeCommsNotSetupByCareRecipient;
    @NonNull
    public final NoticeBannerView emergencyContactNoticeCommsNotSetupByCareRecipientCr;
    @NonNull
    public final NoticeBannerView emergencyContactNoticeNotSetAsEmergencyContactWithNotification;
    @NonNull
    public final NoticeBannerView emergencyContactNoticeNotSetAsEmergencyContactWithoutNotification;
    @NonNull
    public final LinearLayout emergencyContactNoticePanelCg;
    @NonNull
    public final LinearLayout emergencyContactNoticePanelCr;
    @NonNull
    public final ErrorView errorView;
    @Bindable
    protected EmergencyContactView mHandler;
    @Bindable
    protected EmergencyContactViewModel mViewmodel;
    @NonNull
    public final Button sendSetupLinkButton;
    @NonNull
    public final Button setupCommsButton;

    /* JADX INFO: Access modifiers changed from: protected */
    public EmergencyContactViewBinding(Object obj, View view, int i, Button button, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, Button button2, Button button3, ListItemLink listItemLink, FontTextView fontTextView, ListItem listItem, FontTextView fontTextView2, ListItem listItem2, NoticeBannerView noticeBannerView, NoticeBannerView noticeBannerView2, NoticeBannerView noticeBannerView3, NoticeBannerView noticeBannerView4, NoticeBannerView noticeBannerView5, LinearLayout linearLayout4, LinearLayout linearLayout5, ErrorView errorView, Button button4, Button button5) {
        super(obj, view, i);
        this.addEmergencyContactButton = button;
        this.buttonPanel = linearLayout;
        this.buttonPanelCg = linearLayout2;
        this.buttonPanelCr = linearLayout3;
        this.changeEmergencyContactButton = button2;
        this.changeEmergencyContactButtonCr = button3;
        this.emergencyContactCareGiver = listItemLink;
        this.emergencyContactCareRecipientHeader = fontTextView;
        this.emergencyContactChange = listItem;
        this.emergencyContactDescription = fontTextView2;
        this.emergencyContactNotSet = listItem2;
        this.emergencyContactNoticeCommsNotSetupByCareGiver = noticeBannerView;
        this.emergencyContactNoticeCommsNotSetupByCareRecipient = noticeBannerView2;
        this.emergencyContactNoticeCommsNotSetupByCareRecipientCr = noticeBannerView3;
        this.emergencyContactNoticeNotSetAsEmergencyContactWithNotification = noticeBannerView4;
        this.emergencyContactNoticeNotSetAsEmergencyContactWithoutNotification = noticeBannerView5;
        this.emergencyContactNoticePanelCg = linearLayout4;
        this.emergencyContactNoticePanelCr = linearLayout5;
        this.errorView = errorView;
        this.sendSetupLinkButton = button4;
        this.setupCommsButton = button5;
    }

    public static EmergencyContactViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static EmergencyContactViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public EmergencyContactView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public EmergencyContactViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setHandler(@Nullable EmergencyContactView emergencyContactView);

    public abstract void setViewmodel(@Nullable EmergencyContactViewModel emergencyContactViewModel);

    @Deprecated
    public static EmergencyContactViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (EmergencyContactViewBinding) ViewDataBinding.bind(obj, view, R.layout.emergency_contact_view);
    }

    @NonNull
    @Deprecated
    public static EmergencyContactViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (EmergencyContactViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.emergency_contact_view, viewGroup, z, obj);
    }

    @NonNull
    public static EmergencyContactViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EmergencyContactViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (EmergencyContactViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.emergency_contact_view, null, false, obj);
    }
}
