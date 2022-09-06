package com.amazon.alexa.biloba.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.infoModal.InfoModalView;
import com.amazon.alexa.biloba.view.infoModal.InfoModalViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.RoundImageButtonView;
/* loaded from: classes6.dex */
public class InfoModalViewBindingImpl extends InfoModalViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private OnClickListenerImpl mHandlerHandleOkButtonAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHandlerOnCallClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mHandlerOnDropinClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHandlerOnMessageClickedAndroidViewViewOnClickListener;
    @NonNull
    private final ConstraintLayout mboundView0;
    @NonNull
    private final RoundImageButtonView mboundView10;
    @NonNull
    private final RoundImageButtonView mboundView11;
    @NonNull
    private final RoundImageButtonView mboundView9;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private InfoModalView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.handleOkButton(view);
        }

        public OnClickListenerImpl setValue(InfoModalView infoModalView) {
            this.value = infoModalView;
            if (infoModalView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private InfoModalView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onCallClicked(view);
        }

        public OnClickListenerImpl1 setValue(InfoModalView infoModalView) {
            this.value = infoModalView;
            if (infoModalView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private InfoModalView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onMessageClicked(view);
        }

        public OnClickListenerImpl2 setValue(InfoModalView infoModalView) {
            this.value = infoModalView;
            if (infoModalView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private InfoModalView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onDropinClicked(view);
        }

        public OnClickListenerImpl3 setValue(InfoModalView infoModalView) {
            this.value = infoModalView;
            if (infoModalView == null) {
                return null;
            }
            return this;
        }
    }

    static {
        sViewsWithIds.put(R.id.comms_panel, 13);
    }

    public InfoModalViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int i;
        OnClickListenerImpl2 onClickListenerImpl2;
        OnClickListenerImpl3 onClickListenerImpl3;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl1 onClickListenerImpl1;
        int i2;
        OnClickListenerImpl2 onClickListenerImpl22;
        OnClickListenerImpl3 onClickListenerImpl32;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        OnClickListenerImpl onClickListenerImpl4;
        OnClickListenerImpl1 onClickListenerImpl12;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        int i10;
        boolean z;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        boolean z2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        InfoModalView infoModalView = this.mHandler;
        InfoModalViewModel infoModalViewModel = this.mViewModel;
        int i11 = ((j & 5) > 0L ? 1 : ((j & 5) == 0L ? 0 : -1));
        int i12 = 0;
        if (i11 != 0) {
            if (infoModalView != null) {
                z2 = infoModalView.isDropInEnabledForCareContact();
                OnClickListenerImpl onClickListenerImpl5 = this.mHandlerHandleOkButtonAndroidViewViewOnClickListener;
                if (onClickListenerImpl5 == null) {
                    onClickListenerImpl5 = new OnClickListenerImpl();
                    this.mHandlerHandleOkButtonAndroidViewViewOnClickListener = onClickListenerImpl5;
                }
                onClickListenerImpl = onClickListenerImpl5.setValue(infoModalView);
                OnClickListenerImpl1 onClickListenerImpl13 = this.mHandlerOnCallClickedAndroidViewViewOnClickListener;
                if (onClickListenerImpl13 == null) {
                    onClickListenerImpl13 = new OnClickListenerImpl1();
                    this.mHandlerOnCallClickedAndroidViewViewOnClickListener = onClickListenerImpl13;
                }
                onClickListenerImpl1 = onClickListenerImpl13.setValue(infoModalView);
                OnClickListenerImpl2 onClickListenerImpl23 = this.mHandlerOnMessageClickedAndroidViewViewOnClickListener;
                if (onClickListenerImpl23 == null) {
                    onClickListenerImpl23 = new OnClickListenerImpl2();
                    this.mHandlerOnMessageClickedAndroidViewViewOnClickListener = onClickListenerImpl23;
                }
                onClickListenerImpl2 = onClickListenerImpl23.setValue(infoModalView);
                OnClickListenerImpl3 onClickListenerImpl33 = this.mHandlerOnDropinClickedAndroidViewViewOnClickListener;
                if (onClickListenerImpl33 == null) {
                    onClickListenerImpl33 = new OnClickListenerImpl3();
                    this.mHandlerOnDropinClickedAndroidViewViewOnClickListener = onClickListenerImpl33;
                }
                onClickListenerImpl3 = onClickListenerImpl33.setValue(infoModalView);
            } else {
                z2 = false;
                onClickListenerImpl2 = null;
                onClickListenerImpl3 = null;
                onClickListenerImpl = null;
                onClickListenerImpl1 = null;
            }
            if (i11 != 0) {
                j |= z2 ? 16L : 8L;
            }
            i = z2 ? 0 : 8;
        } else {
            i = 0;
            onClickListenerImpl2 = null;
            onClickListenerImpl3 = null;
            onClickListenerImpl = null;
            onClickListenerImpl1 = null;
        }
        int i13 = ((j & 6) > 0L ? 1 : ((j & 6) == 0L ? 0 : -1));
        if (i13 != 0) {
            if (infoModalViewModel != null) {
                String description1 = infoModalViewModel.getDescription1();
                String captionHeadlineText = infoModalViewModel.getCaptionHeadlineText();
                str12 = infoModalViewModel.getHeadlineText();
                str13 = infoModalViewModel.getCommsSectionTitle();
                str14 = infoModalViewModel.getOkButtonText();
                str15 = infoModalViewModel.getDescription2();
                z = infoModalViewModel.getShowCommsSection();
                str16 = infoModalViewModel.getDescription3();
                str10 = infoModalViewModel.getCommsSectionDescription1();
                str9 = captionHeadlineText;
                str11 = description1;
            } else {
                z = false;
                str9 = null;
                str10 = null;
                str11 = null;
                str12 = null;
                str13 = null;
                str14 = null;
                str15 = null;
                str16 = null;
            }
            boolean z3 = true;
            boolean z4 = str11 == null;
            boolean z5 = str9 == null;
            boolean z6 = str12 == null;
            boolean z7 = str13 == null;
            boolean z8 = str14 == null;
            boolean z9 = str15 == null;
            boolean z10 = !z;
            boolean z11 = str16 == null;
            if (str10 != null) {
                z3 = false;
            }
            if (i13 != 0) {
                j |= z4 ? 64L : 32L;
            }
            if ((j & 6) != 0) {
                j |= z5 ? 16384L : PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            if ((j & 6) != 0) {
                j |= z6 ? 1048576L : PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            if ((j & 6) != 0) {
                j |= z7 ? 4194304L : PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            if ((j & 6) != 0) {
                j |= z8 ? PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM : PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            if ((j & 6) != 0) {
                j |= z9 ? 256L : 128L;
            }
            if ((j & 6) != 0) {
                j |= z10 ? 1024L : 512L;
            }
            if ((j & 6) != 0) {
                j |= z11 ? PlaybackStateCompat.ACTION_SET_REPEAT_MODE : PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            if ((j & 6) != 0) {
                j |= z3 ? 65536L : PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            i4 = z4 ? 8 : 0;
            int i14 = z5 ? 8 : 0;
            int i15 = z6 ? 8 : 0;
            int i16 = z7 ? 8 : 0;
            int i17 = z8 ? 8 : 0;
            int i18 = z9 ? 8 : 0;
            int i19 = z10 ? 8 : 0;
            int i20 = z11 ? 8 : 0;
            if (z3) {
                i12 = 8;
            }
            str7 = str12;
            str6 = str13;
            str8 = str14;
            str3 = str16;
            i8 = i15;
            i7 = i16;
            i9 = i17;
            i3 = i18;
            i2 = i;
            str = str9;
            onClickListenerImpl22 = onClickListenerImpl2;
            onClickListenerImpl32 = onClickListenerImpl3;
            str2 = str11;
            i5 = i14;
            i6 = i20;
            OnClickListenerImpl onClickListenerImpl6 = onClickListenerImpl;
            i10 = i12;
            i12 = i19;
            onClickListenerImpl4 = onClickListenerImpl6;
            OnClickListenerImpl1 onClickListenerImpl14 = onClickListenerImpl1;
            str5 = str10;
            str4 = str15;
            onClickListenerImpl12 = onClickListenerImpl14;
        } else {
            i2 = i;
            onClickListenerImpl22 = onClickListenerImpl2;
            onClickListenerImpl32 = onClickListenerImpl3;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 0;
            i9 = 0;
            onClickListenerImpl4 = onClickListenerImpl;
            onClickListenerImpl12 = onClickListenerImpl1;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            str6 = null;
            str7 = null;
            str8 = null;
            i10 = 0;
        }
        if ((j & 6) != 0) {
            this.commsSection.setVisibility(i12);
            TextViewBindingAdapter.setText(this.infoModalBodyDescription1, str2);
            this.infoModalBodyDescription1.setVisibility(i4);
            TextViewBindingAdapter.setText(this.infoModalBodyDescription2, str4);
            this.infoModalBodyDescription2.setVisibility(i3);
            TextViewBindingAdapter.setText(this.infoModalBodyDescription3, str3);
            this.infoModalBodyDescription3.setVisibility(i6);
            TextViewBindingAdapter.setText(this.infoModalCaptionHeader, str);
            this.infoModalCaptionHeader.setVisibility(i5);
            TextViewBindingAdapter.setText(this.infoModalCommsDescription1, str5);
            this.infoModalCommsDescription1.setVisibility(i10);
            TextViewBindingAdapter.setText(this.infoModalCommsTitle, str6);
            this.infoModalCommsTitle.setVisibility(i7);
            TextViewBindingAdapter.setText(this.infoModalHeader, str7);
            this.infoModalHeader.setVisibility(i8);
            TextViewBindingAdapter.setText(this.infoModalOkButton, str8);
            this.infoModalOkButton.setVisibility(i9);
        }
        if ((j & 5) != 0) {
            this.infoModalOkButton.setOnClickListener(onClickListenerImpl4);
            this.mboundView10.setOnClickListener(onClickListenerImpl12);
            this.mboundView11.setOnClickListener(onClickListenerImpl22);
            this.mboundView9.setVisibility(i2);
            this.mboundView9.setOnClickListener(onClickListenerImpl32);
        }
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
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.amazon.alexa.biloba.databinding.InfoModalViewBinding
    public void setHandler(@Nullable InfoModalView infoModalView) {
        this.mHandler = infoModalView;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((InfoModalView) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((InfoModalViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.InfoModalViewBinding
    public void setViewModel(@Nullable InfoModalViewModel infoModalViewModel) {
        this.mViewModel = infoModalViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    private InfoModalViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (LinearLayout) objArr[13], (LinearLayout) objArr[6], (FontTextView) objArr[3], (FontTextView) objArr[4], (FontTextView) objArr[5], (FontTextView) objArr[1], (FontTextView) objArr[8], (FontTextView) objArr[7], (FontTextView) objArr[2], (Button) objArr[12]);
        this.mDirtyFlags = -1L;
        this.commsSection.setTag(null);
        this.infoModalBodyDescription1.setTag(null);
        this.infoModalBodyDescription2.setTag(null);
        this.infoModalBodyDescription3.setTag(null);
        this.infoModalCaptionHeader.setTag(null);
        this.infoModalCommsDescription1.setTag(null);
        this.infoModalCommsTitle.setTag(null);
        this.infoModalHeader.setTag(null);
        this.infoModalOkButton.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (RoundImageButtonView) objArr[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (RoundImageButtonView) objArr[11];
        this.mboundView11.setTag(null);
        this.mboundView9 = (RoundImageButtonView) objArr[9];
        this.mboundView9.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
