package com.amazon.alexa.biloba.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
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
import com.amazon.alexa.biloba.generated.models.EmergencyAddress;
import com.amazon.alexa.biloba.generated.models.EmergencyContact;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.view.comms.EmergencyView;
import com.amazon.alexa.biloba.view.comms.EmergencyViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.NoticeBannerView;
/* loaded from: classes6.dex */
public class EmergencyViewBindingImpl extends EmergencyViewBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback9;
    private long mDirtyFlags;
    private OnClickListenerImpl3 mHandlerAddEmergencyContactAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mHandlerChangeEmergencyContactNumberAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHandlerNavigateToCommsAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHandlerNavigateToNotificationSettingsAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHandlerNavigateToShareSetupLinkAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mHandlerStartEmergencyAddressWebviewAndroidViewViewOnClickListener;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    private final ConstraintLayout mboundView1;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private EmergencyView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToComms(view);
        }

        public OnClickListenerImpl setValue(EmergencyView emergencyView) {
            this.value = emergencyView;
            if (emergencyView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private EmergencyView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToNotificationSettings(view);
        }

        public OnClickListenerImpl1 setValue(EmergencyView emergencyView) {
            this.value = emergencyView;
            if (emergencyView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private EmergencyView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToShareSetupLink(view);
        }

        public OnClickListenerImpl2 setValue(EmergencyView emergencyView) {
            this.value = emergencyView;
            if (emergencyView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private EmergencyView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.addEmergencyContact(view);
        }

        public OnClickListenerImpl3 setValue(EmergencyView emergencyView) {
            this.value = emergencyView;
            if (emergencyView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private EmergencyView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.startEmergencyAddressWebview(view);
        }

        public OnClickListenerImpl4 setValue(EmergencyView emergencyView) {
            this.value = emergencyView;
            if (emergencyView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private EmergencyView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.changeEmergencyContactNumber(view);
        }

        public OnClickListenerImpl5 setValue(EmergencyView emergencyView) {
            this.value = emergencyView;
            if (emergencyView == null) {
                return null;
            }
            return this;
        }
    }

    static {
        sViewsWithIds.put(R.id.emergency_contact_care_recipient_header, 14);
        sViewsWithIds.put(R.id.emergency_helpline_header, 15);
    }

    public EmergencyViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 16, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetCareRecipient(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetEmergencyAddress(LiveData<EmergencyAddress> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetEmergencyContact(LiveData<EmergencyContact> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
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

    private boolean onChangeViewmodelGetHasSubscription(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsCareGiver(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsCommsSetupByCareRecipient(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsCommsSetupByCurrentActor(LiveData<Boolean> liveData, int i) {
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
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsEmergencyHelplineEnabled(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsLoading(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsNotEmergencyContactWithNotification(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsNotEmergencyContactWithoutNotification(LiveData<Boolean> liveData, int i) {
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
        EmergencyViewModel emergencyViewModel = this.mViewmodel;
        if (emergencyViewModel != null) {
            emergencyViewModel.sendRequest();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0573  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0577  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x058c  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x05af  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x05d7  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0609  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0616  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0646  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x065c  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x066b  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0690  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0699  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x06af  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x06bc  */
    /* JADX WARN: Removed duplicated region for block: B:361:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 1733
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.databinding.EmergencyViewBindingImpl.executeBindings():void");
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
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewmodelGetError((LiveData) obj, i2);
            case 1:
                return onChangeViewmodelGetCareRecipient((LiveData) obj, i2);
            case 2:
                return onChangeViewmodelGetIsNotEmergencyContactWithoutNotification((LiveData) obj, i2);
            case 3:
                return onChangeViewmodelGetIsLoading((LiveData) obj, i2);
            case 4:
                return onChangeViewmodelGetIsCareGiver((LiveData) obj, i2);
            case 5:
                return onChangeViewmodelGetIsNotEmergencyContactWithNotification((LiveData) obj, i2);
            case 6:
                return onChangeViewmodelGetHasSubscription((LiveData) obj, i2);
            case 7:
                return onChangeViewmodelGetEmergencyAddress((LiveData) obj, i2);
            case 8:
                return onChangeViewmodelGetIsCommsSetupByCareRecipient((LiveData) obj, i2);
            case 9:
                return onChangeViewmodelGetIsEmergencyContactNotSet((LiveData) obj, i2);
            case 10:
                return onChangeViewmodelGetIsEmergencyHelplineEnabled((LiveData) obj, i2);
            case 11:
                return onChangeViewmodelGetIsCommsSetupByCurrentActor((LiveData) obj, i2);
            case 12:
                return onChangeViewmodelGetEmergencyContact((LiveData) obj, i2);
            default:
                return false;
        }
    }

    @Override // com.amazon.alexa.biloba.databinding.EmergencyViewBinding
    public void setHandler(@Nullable EmergencyView emergencyView) {
        this.mHandler = emergencyView;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((EmergencyView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((EmergencyViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.EmergencyViewBinding
    public void setViewmodel(@Nullable EmergencyViewModel emergencyViewModel) {
        this.mViewmodel = emergencyViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16384;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private EmergencyViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 13, (ListItemLink) objArr[9], (FontTextView) objArr[14], (NoticeBannerView) objArr[3], (NoticeBannerView) objArr[4], (NoticeBannerView) objArr[8], (NoticeBannerView) objArr[5], (NoticeBannerView) objArr[6], (LinearLayout) objArr[2], (LinearLayout) objArr[7], (ListItemLink) objArr[12], (FontTextView) objArr[15], (ListItemLink) objArr[11], (ErrorView) objArr[13], (LinearLayout) objArr[10]);
        this.mDirtyFlags = -1L;
        this.emergencyContactCareGiver.setTag(null);
        this.emergencyContactNoticeCommsNotSetupByCareGiver.setTag(null);
        this.emergencyContactNoticeCommsNotSetupByCareRecipient.setTag(null);
        this.emergencyContactNoticeCommsNotSetupByCareRecipientCr.setTag(null);
        this.emergencyContactNoticeNotSetAsEmergencyContactWithNotification.setTag(null);
        this.emergencyContactNoticeNotSetAsEmergencyContactWithoutNotification.setTag(null);
        this.emergencyContactNoticePanelCg.setTag(null);
        this.emergencyContactNoticePanelCr.setTag(null);
        this.emergencyHelplineAddress.setTag(null);
        this.emergencyHelplineStatus.setTag(null);
        this.errorView.setTag(null);
        this.mboundView0 = (FrameLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (ConstraintLayout) objArr[1];
        this.mboundView1.setTag(null);
        this.urgentResponseSection.setTag(null);
        setRootTag(view);
        this.mCallback9 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
