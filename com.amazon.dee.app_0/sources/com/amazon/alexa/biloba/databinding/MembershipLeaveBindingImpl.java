package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.generated.callback.OnClickListener;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.view.account.MembershipLeaveView;
import com.amazon.alexa.biloba.view.account.MembershipViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.BulletListText;
/* loaded from: classes6.dex */
public class MembershipLeaveBindingImpl extends MembershipLeaveBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private final View.OnClickListener mCallback11;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;
    @NonNull
    private final LinearLayout mboundView1;

    public MembershipLeaveBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetCareContact(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsLoading(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.biloba.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        MembershipLeaveView membershipLeaveView = this.mHandler;
        if (membershipLeaveView != null) {
            membershipLeaveView.onConfirm();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            r19 = this;
            r1 = r19
            monitor-enter(r19)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> Lb7
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> Lb7
            monitor-exit(r19)     // Catch: java.lang.Throwable -> Lb7
            com.amazon.alexa.biloba.view.account.MembershipLeaveView r0 = r1.mHandler
            com.amazon.alexa.biloba.view.account.MembershipViewModel r6 = r1.mViewmodel
            r7 = 30
            long r7 = r7 & r2
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            r8 = 31
            long r8 = r8 & r2
            int r8 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            r9 = 26
            r11 = 25
            r13 = 0
            if (r8 == 0) goto L82
            long r15 = r2 & r11
            int r8 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            r15 = 1
            if (r8 == 0) goto L41
            if (r6 == 0) goto L2d
            androidx.lifecycle.LiveData r8 = r6.getIsLoading()
            goto L2e
        L2d:
            r8 = 0
        L2e:
            r1.updateLiveDataRegistration(r13, r8)
            if (r8 == 0) goto L3a
            java.lang.Object r8 = r8.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L3b
        L3a:
            r8 = 0
        L3b:
            java.lang.Boolean r14 = java.lang.Boolean.TRUE
            if (r8 == r14) goto L41
            r8 = r15
            goto L42
        L41:
            r8 = r13
        L42:
            if (r7 == 0) goto L83
            if (r6 == 0) goto L4b
            androidx.lifecycle.LiveData r14 = r6.getCareContact()
            goto L4c
        L4b:
            r14 = 0
        L4c:
            r1.updateLiveDataRegistration(r15, r14)
            long r17 = r2 & r9
            int r17 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r17 == 0) goto L6e
            if (r6 == 0) goto L5c
            java.lang.String r6 = r6.getDisplayName(r14)
            goto L5d
        L5c:
            r6 = 0
        L5d:
            com.amazon.alexa.font.FontTextView r11 = r1.membershipDisableQuestion
            android.content.res.Resources r11 = r11.getResources()
            int r12 = com.amazon.alexa.biloba.R.string.membership_delete_dialog_question
            java.lang.Object[] r15 = new java.lang.Object[r15]
            r15[r13] = r6
            java.lang.String r6 = r11.getString(r12, r15)
            goto L6f
        L6e:
            r6 = 0
        L6f:
            if (r0 == 0) goto L84
            java.lang.String r11 = r0.getMembershipDeleteBullet1(r14)
            java.lang.String r12 = r0.getMembershipDeleteBullet3(r14)
            java.lang.String r13 = r0.getMembershipDeleteBullet4(r14)
            java.lang.String r14 = r0.getMembershipDeleteBullet2(r14)
            goto L88
        L82:
            r8 = r13
        L83:
            r6 = 0
        L84:
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L88:
            if (r7 == 0) goto L9e
            com.amazon.alexa.mosaic.view.BulletListText r0 = r1.membershipDeleteBullet1
            r0.setPrimaryText(r11)
            com.amazon.alexa.mosaic.view.BulletListText r0 = r1.membershipDeleteBullet2
            r0.setPrimaryText(r14)
            com.amazon.alexa.mosaic.view.BulletListText r0 = r1.membershipDeleteBullet3
            r0.setPrimaryText(r12)
            com.amazon.alexa.mosaic.view.BulletListText r0 = r1.membershipDeleteBullet4
            r0.setPrimaryText(r13)
        L9e:
            long r9 = r9 & r2
            int r0 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r0 == 0) goto La8
            com.amazon.alexa.font.FontTextView r0 = r1.membershipDisableQuestion
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        La8:
            r6 = 25
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto Lb6
            android.widget.Button r0 = r1.okButton
            android.view.View$OnClickListener r2 = r1.mCallback11
            androidx.databinding.adapters.ViewBindingAdapter.setOnClick(r0, r2, r8)
        Lb6:
            return
        Lb7:
            r0 = move-exception
            monitor-exit(r19)     // Catch: java.lang.Throwable -> Lb7
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.databinding.MembershipLeaveBindingImpl.executeBindings():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i == 1) {
                return onChangeViewmodelGetCareContact((LiveData) obj, i2);
            }
            return false;
        }
        return onChangeViewmodelGetIsLoading((LiveData) obj, i2);
    }

    @Override // com.amazon.alexa.biloba.databinding.MembershipLeaveBinding
    public void setHandler(@Nullable MembershipLeaveView membershipLeaveView) {
        this.mHandler = membershipLeaveView;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((MembershipLeaveView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((MembershipViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.MembershipLeaveBinding
    public void setViewmodel(@Nullable MembershipViewModel membershipViewModel) {
        this.mViewmodel = membershipViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private MembershipLeaveBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (BulletListText) objArr[3], (BulletListText) objArr[4], (BulletListText) objArr[5], (BulletListText) objArr[6], (FontTextView) objArr[2], (Button) objArr[7]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (LinearLayout) objArr[1];
        this.mboundView1.setTag(null);
        this.membershipDeleteBullet1.setTag(null);
        this.membershipDeleteBullet2.setTag(null);
        this.membershipDeleteBullet3.setTag(null);
        this.membershipDeleteBullet4.setTag(null);
        this.membershipDisableQuestion.setTag(null);
        this.okButton.setTag(null);
        setRootTag(view);
        this.mCallback11 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
