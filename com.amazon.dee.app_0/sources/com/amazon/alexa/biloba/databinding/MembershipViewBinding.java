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
import com.amazon.alexa.biloba.view.account.MembershipView;
import com.amazon.alexa.biloba.view.account.MembershipViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.SectionHeader;
/* loaded from: classes6.dex */
public abstract class MembershipViewBinding extends ViewDataBinding {
    @NonNull
    public final FontTextView deleteConnectionHint;
    @Bindable
    protected MembershipView mMembershipView;
    @Bindable
    protected MembershipViewModel mViewmodel;
    @NonNull
    public final FontTextView membershipCareGiverName;
    @NonNull
    public final FontTextView membershipCareRecipientName;
    @NonNull
    public final FontTextView membershipSettingsLink;
    @NonNull
    public final SectionHeader membershipTitleLabel;

    /* JADX INFO: Access modifiers changed from: protected */
    public MembershipViewBinding(Object obj, View view, int i, FontTextView fontTextView, FontTextView fontTextView2, FontTextView fontTextView3, FontTextView fontTextView4, SectionHeader sectionHeader) {
        super(obj, view, i);
        this.deleteConnectionHint = fontTextView;
        this.membershipCareGiverName = fontTextView2;
        this.membershipCareRecipientName = fontTextView3;
        this.membershipSettingsLink = fontTextView4;
        this.membershipTitleLabel = sectionHeader;
    }

    public static MembershipViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MembershipViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public MembershipView getMembershipView() {
        return this.mMembershipView;
    }

    @Nullable
    public MembershipViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setMembershipView(@Nullable MembershipView membershipView);

    public abstract void setViewmodel(@Nullable MembershipViewModel membershipViewModel);

    @Deprecated
    public static MembershipViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MembershipViewBinding) ViewDataBinding.bind(obj, view, R.layout.membership_view);
    }

    @NonNull
    @Deprecated
    public static MembershipViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MembershipViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.membership_view, viewGroup, z, obj);
    }

    @NonNull
    public static MembershipViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MembershipViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MembershipViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.membership_view, null, false, obj);
    }
}
