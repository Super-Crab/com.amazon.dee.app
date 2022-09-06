package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.callback.OnClickListener;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.view.account.ProfileSettingsView;
import com.amazon.alexa.biloba.view.account.ProfileSettingsViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ListItemLink;
/* loaded from: classes6.dex */
public class ProfileSettingsBindingImpl extends ProfileSettingsBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback5;
    private long mDirtyFlags;
    private OnClickListenerImpl3 mProfileSettingsViewChangeCareRecipientTimeZoneAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mProfileSettingsViewManageCareRelationshipAndroidViewViewOnClickListener;
    private OnClickListenerImpl mProfileSettingsViewNavigateToCareContactCardAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mProfileSettingsViewNavigateToCommsAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mProfileSettingsViewNavigateToCommsHelpAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mProfileSettingsViewNavigateToEmergencyContactsPageAndroidViewViewOnClickListener;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    private final ScrollView mboundView1;
    @NonNull
    private final View mboundView6;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private ProfileSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToCareContactCard(view);
        }

        public OnClickListenerImpl setValue(ProfileSettingsView profileSettingsView) {
            this.value = profileSettingsView;
            if (profileSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private ProfileSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToCommsHelp(view);
        }

        public OnClickListenerImpl1 setValue(ProfileSettingsView profileSettingsView) {
            this.value = profileSettingsView;
            if (profileSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private ProfileSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.manageCareRelationship(view);
        }

        public OnClickListenerImpl2 setValue(ProfileSettingsView profileSettingsView) {
            this.value = profileSettingsView;
            if (profileSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private ProfileSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.changeCareRecipientTimeZone(view);
        }

        public OnClickListenerImpl3 setValue(ProfileSettingsView profileSettingsView) {
            this.value = profileSettingsView;
            if (profileSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private ProfileSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToComms(view);
        }

        public OnClickListenerImpl4 setValue(ProfileSettingsView profileSettingsView) {
            this.value = profileSettingsView;
            if (profileSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private ProfileSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToEmergencyContactsPage(view);
        }

        public OnClickListenerImpl5 setValue(ProfileSettingsView profileSettingsView) {
            this.value = profileSettingsView;
            if (profileSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    static {
        sViewsWithIds.put(R.id.loading_view, 16);
    }

    public ProfileSettingsBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetCareActor(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetCareContact(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetError(LiveData<Throwable> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsEmergencyHelplineEnabled(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsLoading(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetTimeZone(MutableLiveData<String> mutableLiveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.biloba.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        ProfileSettingsViewModel profileSettingsViewModel = this.mViewmodel;
        if (profileSettingsViewModel != null) {
            profileSettingsViewModel.sendRequest();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0365  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 1423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.databinding.ProfileSettingsBindingImpl.executeBindings():void");
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
            this.mDirtyFlags = 256L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i == 1) {
                return onChangeViewmodelGetCareActor((LiveData) obj, i2);
            }
            if (i == 2) {
                return onChangeViewmodelGetCareContact((LiveData) obj, i2);
            }
            if (i == 3) {
                return onChangeViewmodelGetIsEmergencyHelplineEnabled((LiveData) obj, i2);
            }
            if (i == 4) {
                return onChangeViewmodelGetTimeZone((MutableLiveData) obj, i2);
            }
            if (i == 5) {
                return onChangeViewmodelGetIsLoading((LiveData) obj, i2);
            }
            return false;
        }
        return onChangeViewmodelGetError((LiveData) obj, i2);
    }

    @Override // com.amazon.alexa.biloba.databinding.ProfileSettingsBinding
    public void setProfileSettingsView(@Nullable ProfileSettingsView profileSettingsView) {
        this.mProfileSettingsView = profileSettingsView;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.profile_settings_view);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.profile_settings_view == i) {
            setProfileSettingsView((ProfileSettingsView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((ProfileSettingsViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.ProfileSettingsBinding
    public void setViewmodel(@Nullable ProfileSettingsViewModel profileSettingsViewModel) {
        this.mViewmodel = profileSettingsViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private ProfileSettingsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, (FontTextView) objArr[9], (FontTextView) objArr[10], (FontTextView) objArr[11], (FontTextView) objArr[12], (FontTextView) objArr[13], (FontTextView) objArr[7], (LinearLayout) objArr[8], (ErrorView) objArr[15], (View) objArr[16], (ListItemLink) objArr[4], (ListItemLink) objArr[5], (ListItemLink) objArr[2], (ListItemLink) objArr[3], (FontTextView) objArr[14]);
        this.mDirtyFlags = -1L;
        this.dropInDescription.setTag(null);
        this.dropInDisabledLearnMoreDescripiton.setTag(null);
        this.dropInDisabledLearnMoreLink.setTag(null);
        this.dropInManageDescription.setTag(null);
        this.dropInManageLink.setTag(null);
        this.dropInWhenCommsDisabled.setTag(null);
        this.dropInWhenCommsEnabled.setTag(null);
        this.errorView.setTag(null);
        this.mboundView0 = (FrameLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (ScrollView) objArr[1];
        this.mboundView1.setTag(null);
        this.mboundView6 = (View) objArr[6];
        this.mboundView6.setTag(null);
        this.profileSettingsAlexaCommunicationsEnabled.setTag(null);
        this.profileSettingsAlexaEmergencySettingsEnabled.setTag(null);
        this.profileSettingsCareRelationshipName.setTag(null);
        this.profileSettingsTimezone.setTag(null);
        this.subscriptionAndPaymentDescription.setTag(null);
        setRootTag(view);
        this.mCallback5 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
