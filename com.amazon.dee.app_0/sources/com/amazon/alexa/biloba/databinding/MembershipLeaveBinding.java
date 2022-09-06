package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.account.MembershipLeaveView;
import com.amazon.alexa.biloba.view.account.MembershipViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.BulletListText;
/* loaded from: classes6.dex */
public abstract class MembershipLeaveBinding extends ViewDataBinding {
    @Bindable
    protected MembershipLeaveView mHandler;
    @Bindable
    protected MembershipViewModel mViewmodel;
    @NonNull
    public final BulletListText membershipDeleteBullet1;
    @NonNull
    public final BulletListText membershipDeleteBullet2;
    @NonNull
    public final BulletListText membershipDeleteBullet3;
    @NonNull
    public final BulletListText membershipDeleteBullet4;
    @NonNull
    public final FontTextView membershipDisableQuestion;
    @NonNull
    public final Button okButton;

    /* JADX INFO: Access modifiers changed from: protected */
    public MembershipLeaveBinding(Object obj, View view, int i, BulletListText bulletListText, BulletListText bulletListText2, BulletListText bulletListText3, BulletListText bulletListText4, FontTextView fontTextView, Button button) {
        super(obj, view, i);
        this.membershipDeleteBullet1 = bulletListText;
        this.membershipDeleteBullet2 = bulletListText2;
        this.membershipDeleteBullet3 = bulletListText3;
        this.membershipDeleteBullet4 = bulletListText4;
        this.membershipDisableQuestion = fontTextView;
        this.okButton = button;
    }

    public static MembershipLeaveBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MembershipLeaveBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public MembershipLeaveView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public MembershipViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setHandler(@Nullable MembershipLeaveView membershipLeaveView);

    public abstract void setViewmodel(@Nullable MembershipViewModel membershipViewModel);

    @Deprecated
    public static MembershipLeaveBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MembershipLeaveBinding) ViewDataBinding.bind(obj, view, R.layout.membership_leave);
    }

    @NonNull
    @Deprecated
    public static MembershipLeaveBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MembershipLeaveBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.membership_leave, viewGroup, z, obj);
    }

    @NonNull
    public static MembershipLeaveBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MembershipLeaveBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MembershipLeaveBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.membership_leave, null, false, obj);
    }
}
