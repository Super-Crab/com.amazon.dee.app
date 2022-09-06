package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.comms.EmergencyView;
import com.amazon.alexa.biloba.view.comms.EmergencyViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.NoticeBannerView;
/* loaded from: classes6.dex */
public abstract class EmergencyViewBinding extends ViewDataBinding {
    @NonNull
    public final ListItemLink emergencyContactCareGiver;
    @NonNull
    public final FontTextView emergencyContactCareRecipientHeader;
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
    public final ListItemLink emergencyHelplineAddress;
    @NonNull
    public final FontTextView emergencyHelplineHeader;
    @NonNull
    public final ListItemLink emergencyHelplineStatus;
    @NonNull
    public final ErrorView errorView;
    @Bindable
    protected EmergencyView mHandler;
    @Bindable
    protected EmergencyViewModel mViewmodel;
    @NonNull
    public final LinearLayout urgentResponseSection;

    /* JADX INFO: Access modifiers changed from: protected */
    public EmergencyViewBinding(Object obj, View view, int i, ListItemLink listItemLink, FontTextView fontTextView, NoticeBannerView noticeBannerView, NoticeBannerView noticeBannerView2, NoticeBannerView noticeBannerView3, NoticeBannerView noticeBannerView4, NoticeBannerView noticeBannerView5, LinearLayout linearLayout, LinearLayout linearLayout2, ListItemLink listItemLink2, FontTextView fontTextView2, ListItemLink listItemLink3, ErrorView errorView, LinearLayout linearLayout3) {
        super(obj, view, i);
        this.emergencyContactCareGiver = listItemLink;
        this.emergencyContactCareRecipientHeader = fontTextView;
        this.emergencyContactNoticeCommsNotSetupByCareGiver = noticeBannerView;
        this.emergencyContactNoticeCommsNotSetupByCareRecipient = noticeBannerView2;
        this.emergencyContactNoticeCommsNotSetupByCareRecipientCr = noticeBannerView3;
        this.emergencyContactNoticeNotSetAsEmergencyContactWithNotification = noticeBannerView4;
        this.emergencyContactNoticeNotSetAsEmergencyContactWithoutNotification = noticeBannerView5;
        this.emergencyContactNoticePanelCg = linearLayout;
        this.emergencyContactNoticePanelCr = linearLayout2;
        this.emergencyHelplineAddress = listItemLink2;
        this.emergencyHelplineHeader = fontTextView2;
        this.emergencyHelplineStatus = listItemLink3;
        this.errorView = errorView;
        this.urgentResponseSection = linearLayout3;
    }

    public static EmergencyViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static EmergencyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public EmergencyView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public EmergencyViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setHandler(@Nullable EmergencyView emergencyView);

    public abstract void setViewmodel(@Nullable EmergencyViewModel emergencyViewModel);

    @Deprecated
    public static EmergencyViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (EmergencyViewBinding) ViewDataBinding.bind(obj, view, R.layout.emergency_view);
    }

    @NonNull
    @Deprecated
    public static EmergencyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (EmergencyViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.emergency_view, viewGroup, z, obj);
    }

    @NonNull
    public static EmergencyViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static EmergencyViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (EmergencyViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.emergency_view, null, false, obj);
    }
}
