package com.amazon.alexa.biloba.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.callback.OnClickListener;
import com.amazon.alexa.biloba.generated.models.EmergencyContact;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.view.comms.EmergencyContactView;
import com.amazon.alexa.biloba.view.comms.EmergencyContactViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ListItem;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.NoticeBannerView;
/* loaded from: classes6.dex */
public class EmergencyContactViewBindingImpl extends EmergencyContactViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback6;
    private long mDirtyFlags;
    private OnClickListenerImpl4 mHandlerAddEmergencyContactAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHandlerChangeEmergencyContactAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mHandlerChangeEmergencyContactNumberAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHandlerNavigateToCommsAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHandlerNavigateToNotificationSettingsAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mHandlerNavigateToShareSetupLinkAndroidViewViewOnClickListener;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    private final ConstraintLayout mboundView1;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private EmergencyContactView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToComms(view);
        }

        public OnClickListenerImpl setValue(EmergencyContactView emergencyContactView) {
            this.value = emergencyContactView;
            if (emergencyContactView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private EmergencyContactView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.changeEmergencyContact(view);
        }

        public OnClickListenerImpl1 setValue(EmergencyContactView emergencyContactView) {
            this.value = emergencyContactView;
            if (emergencyContactView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private EmergencyContactView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToNotificationSettings(view);
        }

        public OnClickListenerImpl2 setValue(EmergencyContactView emergencyContactView) {
            this.value = emergencyContactView;
            if (emergencyContactView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private EmergencyContactView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToShareSetupLink(view);
        }

        public OnClickListenerImpl3 setValue(EmergencyContactView emergencyContactView) {
            this.value = emergencyContactView;
            if (emergencyContactView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private EmergencyContactView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.addEmergencyContact(view);
        }

        public OnClickListenerImpl4 setValue(EmergencyContactView emergencyContactView) {
            this.value = emergencyContactView;
            if (emergencyContactView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private EmergencyContactView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.changeEmergencyContactNumber(view);
        }

        public OnClickListenerImpl5 setValue(EmergencyContactView emergencyContactView) {
            this.value = emergencyContactView;
            if (emergencyContactView == null) {
                return null;
            }
            return this;
        }
    }

    static {
        sViewsWithIds.put(R.id.emergency_contact_care_recipient_header, 21);
        sViewsWithIds.put(R.id.button_panel, 22);
    }

    public EmergencyContactViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 23, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetCareRecipient(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetEmergencyContact(LiveData<EmergencyContact> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
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

    private boolean onChangeViewmodelGetIsCareGiver(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsCareGiverEmergencyContact(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsCommsSetupByCareGiver(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsCommsSetupByCareRecipient(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsEmergencyContactNotSet(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsLoading(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsNotEmergencyContactWithNotification(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsNotEmergencyContactWithoutNotification(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetShowAddEmergencyContactButton(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetShowChangeEmergencyContactButton(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetShowChangeEmergencyContactLink(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetShowSendSetupLinkButton(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.biloba.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        EmergencyContactViewModel emergencyContactViewModel = this.mViewmodel;
        if (emergencyContactViewModel != null) {
            emergencyContactViewModel.sendRequest();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x047f  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x059a  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0640  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0663  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0677  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x06ac  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x06b8  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x06c3  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x06ea  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x06f9  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0714  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0723  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0732  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x0750  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x076b  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x077f  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x07ab  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x07b4  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x07d7  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x07e4  */
    /* JADX WARN: Removed duplicated region for block: B:461:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01a0  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 2031
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.databinding.EmergencyContactViewBindingImpl.executeBindings():void");
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
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewmodelGetError((LiveData) obj, i2);
            case 1:
                return onChangeViewmodelGetIsCommsSetupByCareGiver((LiveData) obj, i2);
            case 2:
                return onChangeViewmodelGetShowSendSetupLinkButton((LiveData) obj, i2);
            case 3:
                return onChangeViewmodelGetShowChangeEmergencyContactButton((LiveData) obj, i2);
            case 4:
                return onChangeViewmodelGetIsCareGiverEmergencyContact((LiveData) obj, i2);
            case 5:
                return onChangeViewmodelGetCareRecipient((LiveData) obj, i2);
            case 6:
                return onChangeViewmodelGetIsNotEmergencyContactWithoutNotification((LiveData) obj, i2);
            case 7:
                return onChangeViewmodelGetShowChangeEmergencyContactLink((LiveData) obj, i2);
            case 8:
                return onChangeViewmodelGetIsLoading((LiveData) obj, i2);
            case 9:
                return onChangeViewmodelGetIsCareGiver((LiveData) obj, i2);
            case 10:
                return onChangeViewmodelGetIsNotEmergencyContactWithNotification((LiveData) obj, i2);
            case 11:
                return onChangeViewmodelGetIsCommsSetupByCareRecipient((LiveData) obj, i2);
            case 12:
                return onChangeViewmodelGetIsEmergencyContactNotSet((LiveData) obj, i2);
            case 13:
                return onChangeViewmodelGetEmergencyContact((LiveData) obj, i2);
            case 14:
                return onChangeViewmodelGetShowAddEmergencyContactButton((LiveData) obj, i2);
            default:
                return false;
        }
    }

    @Override // com.amazon.alexa.biloba.databinding.EmergencyContactViewBinding
    public void setHandler(@Nullable EmergencyContactView emergencyContactView) {
        this.mHandler = emergencyContactView;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((EmergencyContactView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((EmergencyContactViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.EmergencyContactViewBinding
    public void setViewmodel(@Nullable EmergencyContactViewModel emergencyContactViewModel) {
        this.mViewmodel = emergencyContactViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 65536;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private EmergencyContactViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 15, (Button) objArr[19], (LinearLayout) objArr[22], (LinearLayout) objArr[13], (LinearLayout) objArr[16], (Button) objArr[15], (Button) objArr[18], (ListItemLink) objArr[3], (FontTextView) objArr[21], (ListItem) objArr[6], (FontTextView) objArr[2], (ListItem) objArr[4], (NoticeBannerView) objArr[10], (NoticeBannerView) objArr[9], (NoticeBannerView) objArr[12], (NoticeBannerView) objArr[7], (NoticeBannerView) objArr[8], (LinearLayout) objArr[5], (LinearLayout) objArr[11], (ErrorView) objArr[20], (Button) objArr[14], (Button) objArr[17]);
        this.mDirtyFlags = -1L;
        this.addEmergencyContactButton.setTag(null);
        this.buttonPanelCg.setTag(null);
        this.buttonPanelCr.setTag(null);
        this.changeEmergencyContactButton.setTag(null);
        this.changeEmergencyContactButtonCr.setTag(null);
        this.emergencyContactCareGiver.setTag(null);
        this.emergencyContactChange.setTag(null);
        this.emergencyContactDescription.setTag(null);
        this.emergencyContactNotSet.setTag(null);
        this.emergencyContactNoticeCommsNotSetupByCareGiver.setTag(null);
        this.emergencyContactNoticeCommsNotSetupByCareRecipient.setTag(null);
        this.emergencyContactNoticeCommsNotSetupByCareRecipientCr.setTag(null);
        this.emergencyContactNoticeNotSetAsEmergencyContactWithNotification.setTag(null);
        this.emergencyContactNoticeNotSetAsEmergencyContactWithoutNotification.setTag(null);
        this.emergencyContactNoticePanelCg.setTag(null);
        this.emergencyContactNoticePanelCr.setTag(null);
        this.errorView.setTag(null);
        this.mboundView0 = (FrameLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (ConstraintLayout) objArr[1];
        this.mboundView1.setTag(null);
        this.sendSetupLinkButton.setTag(null);
        this.setupCommsButton.setTag(null);
        setRootTag(view);
        this.mCallback6 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
