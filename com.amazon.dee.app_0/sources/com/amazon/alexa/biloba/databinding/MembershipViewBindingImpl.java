package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.view.account.MembershipView;
import com.amazon.alexa.biloba.view.account.MembershipViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.SectionHeader;
/* loaded from: classes6.dex */
public class MembershipViewBindingImpl extends MembershipViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ScrollView mboundView0;
    @NonNull
    private final LinearLayout mboundView1;

    static {
        sViewsWithIds.put(R.id.membership_title_label, 6);
    }

    public MembershipViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
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

    private boolean onChangeViewmodelGetCareGiver(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetCareRecipient(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0084  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.databinding.MembershipViewBindingImpl.executeBindings():void");
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
            this.mDirtyFlags = 32L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i == 1) {
                return onChangeViewmodelGetCareContact((LiveData) obj, i2);
            }
            if (i == 2) {
                return onChangeViewmodelGetCareRecipient((LiveData) obj, i2);
            }
            return false;
        }
        return onChangeViewmodelGetCareGiver((LiveData) obj, i2);
    }

    @Override // com.amazon.alexa.biloba.databinding.MembershipViewBinding
    public void setMembershipView(@Nullable MembershipView membershipView) {
        this.mMembershipView = membershipView;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.membership_view);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.membership_view == i) {
            setMembershipView((MembershipView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((MembershipViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.MembershipViewBinding
    public void setViewmodel(@Nullable MembershipViewModel membershipViewModel) {
        this.mViewmodel = membershipViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private MembershipViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (FontTextView) objArr[4], (FontTextView) objArr[2], (FontTextView) objArr[3], (FontTextView) objArr[5], (SectionHeader) objArr[6]);
        this.mDirtyFlags = -1L;
        this.deleteConnectionHint.setTag(null);
        this.mboundView0 = (ScrollView) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (LinearLayout) objArr[1];
        this.mboundView1.setTag(null);
        this.membershipCareGiverName.setTag(null);
        this.membershipCareRecipientName.setTag(null);
        this.membershipSettingsLink.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
