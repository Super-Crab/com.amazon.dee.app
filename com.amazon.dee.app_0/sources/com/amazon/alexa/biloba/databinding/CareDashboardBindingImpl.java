package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.callback.OnClickListener;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardView;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.AlertBannerButtonView;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.RoundImageButtonView;
import com.amazon.alexa.mosaic.view.SectionHeader;
/* loaded from: classes6.dex */
public class CareDashboardBindingImpl extends CareDashboardBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final View.OnClickListener mCallback4;
    private long mDirtyFlags;
    private OnClickListenerImpl mHandlerBottomLeftButtonOnClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHandlerNavigateToAllActivitiesPageAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mHandlerNavigateToSettingsPageAndroidViewViewOnClickListener;
    private OnClickListenerImpl7 mHandlerNavigateToTimeZonePickerAndroidViewViewOnClickListener;
    private OnClickListenerImpl10 mHandlerOnAssistClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl9 mHandlerOnCallClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl11 mHandlerOnDropinClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mHandlerOnMessageClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl6 mHandlerStartCareAdminWebviewAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mHandlerStartCircleMembersWebviewAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHandlerTopLeftButtonOnClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl8 mHandlerTopRightButtonOnClickAndroidViewViewOnClickListener;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    private final LinearLayout mboundView16;
    @NonNull
    private final RoundImageButtonView mboundView5;
    @NonNull
    private final RoundImageButtonView mboundView6;
    @NonNull
    private final RoundImageButtonView mboundView7;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.bottomLeftButtonOnClick(view);
        }

        public OnClickListenerImpl setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.topLeftButtonOnClick(view);
        }

        public OnClickListenerImpl1 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl10 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onAssistClicked(view);
        }

        public OnClickListenerImpl10 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl11 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onDropinClicked(view);
        }

        public OnClickListenerImpl11 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToAllActivitiesPage(view);
        }

        public OnClickListenerImpl2 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onMessageClicked(view);
        }

        public OnClickListenerImpl3 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToSettingsPage(view);
        }

        public OnClickListenerImpl4 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.startCircleMembersWebview(view);
        }

        public OnClickListenerImpl5 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl6 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.startCareAdminWebview(view);
        }

        public OnClickListenerImpl6 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl7 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.navigateToTimeZonePicker(view);
        }

        public OnClickListenerImpl7 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl8 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.topRightButtonOnClick(view);
        }

        public OnClickListenerImpl8 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl9 implements View.OnClickListener {
        private BilobaDashboardView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onCallClicked(view);
        }

        public OnClickListenerImpl9 setValue(BilobaDashboardView bilobaDashboardView) {
            this.value = bilobaDashboardView;
            if (bilobaDashboardView == null) {
                return null;
            }
            return this;
        }
    }

    static {
        sViewsWithIds.put(R.id.loading_view, 22);
        sViewsWithIds.put(R.id.care_cards_layout, 23);
        sViewsWithIds.put(R.id.dashboard_cards, 24);
        sViewsWithIds.put(R.id.activity_panel, 25);
        sViewsWithIds.put(R.id.switcher, 26);
        sViewsWithIds.put(R.id.dashboard_activities_list_view, 27);
        sViewsWithIds.put(R.id.manage_title, 28);
    }

    public CareDashboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 29, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetCareContact(LiveData<CareActor> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
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

    private boolean onChangeViewmodelGetIsDropInEnabled(LiveData<Boolean> liveData, int i) {
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
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetIsRemoteManagementEnabled(LiveData<Boolean> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetTimeZone(MutableLiveData<String> mutableLiveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.biloba.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        BilobaDashboardViewModel bilobaDashboardViewModel = this.mViewmodel;
        if (bilobaDashboardViewModel != null) {
            bilobaDashboardViewModel.sendRequest();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:249:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x052e  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x053b  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0599  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0614  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0677  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x06a2  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x06b1  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x06bc  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:332:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 1750
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.databinding.CareDashboardBindingImpl.executeBindings():void");
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
                return onChangeViewmodelGetIsDropInEnabled((LiveData) obj, i2);
            }
            if (i == 2) {
                return onChangeViewmodelGetIsLoading((LiveData) obj, i2);
            }
            if (i == 3) {
                return onChangeViewmodelGetIsRemoteManagementEnabled((LiveData) obj, i2);
            }
            if (i == 4) {
                return onChangeViewmodelGetCareContact((LiveData) obj, i2);
            }
            if (i == 5) {
                return onChangeViewmodelGetTimeZone((MutableLiveData) obj, i2);
            }
            return false;
        }
        return onChangeViewmodelGetError((LiveData) obj, i2);
    }

    @Override // com.amazon.alexa.biloba.databinding.CareDashboardBinding
    public void setHandler(@Nullable BilobaDashboardView bilobaDashboardView) {
        this.mHandler = bilobaDashboardView;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((BilobaDashboardView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((BilobaDashboardViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.CareDashboardBinding
    public void setViewmodel(@Nullable BilobaDashboardViewModel bilobaDashboardViewModel) {
        this.mViewmodel = bilobaDashboardViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private CareDashboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, (RelativeLayout) objArr[25], (FontTextView) objArr[13], (RoundImageButtonView) objArr[8], (AlertBannerButtonView) objArr[17], (AlertBannerButtonView) objArr[18], (LinearLayout) objArr[23], (FontTextView) objArr[2], (ListItemLink) objArr[10], (FontTextView) objArr[20], (SectionHeader) objArr[9], (LinearLayout) objArr[4], (SectionHeader) objArr[3], (RecyclerView) objArr[27], (RecyclerView) objArr[24], (SwipeRefreshLayout) objArr[1], (ErrorView) objArr[21], (View) objArr[22], (SectionHeader) objArr[28], (SectionHeader) objArr[12], (ViewSwitcher) objArr[26], (FontTextView) objArr[19], (AlertBannerButtonView) objArr[14], (AlertBannerButtonView) objArr[15], (FontTextView) objArr[11]);
        this.mDirtyFlags = -1L;
        this.allRecentActivitiesLink.setTag(null);
        this.assistButton.setTag(null);
        this.bottomLeftButton.setTag(null);
        this.bottomRightButton.setTag(null);
        this.careContactName.setTag(null);
        this.cgAdminIngress.setTag(null);
        this.changeTimezoneDashboard.setTag(null);
        this.circleOfSupportHeader.setTag(null);
        this.commsPanel.setTag(null);
        this.commsPanelHeader.setTag(null);
        this.dashboardRefreshContainer.setTag(null);
        this.errorView.setTag(null);
        this.mboundView0 = (FrameLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView16 = (LinearLayout) objArr[16];
        this.mboundView16.setTag(null);
        this.mboundView5 = (RoundImageButtonView) objArr[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (RoundImageButtonView) objArr[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (RoundImageButtonView) objArr[7];
        this.mboundView7.setTag(null);
        this.recentTitle.setTag(null);
        this.timezoneDashboard.setTag(null);
        this.topLeftButton.setTag(null);
        this.topRightButton.setTag(null);
        this.viewYourCircleMembersLink.setTag(null);
        setRootTag(view);
        this.mCallback4 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
