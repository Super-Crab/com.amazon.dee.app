package com.amazon.alexa.biloba.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.SpannableStringBuilder;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.generated.callback.OnClickListener;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public class ConfirmationBindingImpl extends ConfirmationBinding implements OnClickListener.Listener {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private final View.OnClickListener mCallback7;
    @Nullable
    private final View.OnClickListener mCallback8;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    public ConfirmationBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    @Override // com.amazon.alexa.biloba.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = false;
        if (i == 1) {
            ConfirmationViewModel confirmationViewModel = this.mViewModel;
            if (confirmationViewModel != null) {
                z = true;
            }
            if (!z) {
                return;
            }
            confirmationViewModel.onTapLink();
        } else if (i != 2) {
        } else {
            ConfirmationViewModel confirmationViewModel2 = this.mViewModel;
            if (confirmationViewModel2 != null) {
                z = true;
            }
            if (!z) {
                return;
            }
            confirmationViewModel2.onTapOkButton();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        SpannableStringBuilder spannableStringBuilder;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        String str7;
        String str8;
        String str9;
        String str10;
        SpannableStringBuilder spannableStringBuilder2;
        int i8;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ConfirmationViewModel confirmationViewModel = this.mViewModel;
        int i9 = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        int i10 = 0;
        if (i9 != 0) {
            if (confirmationViewModel != null) {
                i2 = confirmationViewModel.getIconResId();
                i8 = confirmationViewModel.getIconColor();
                str8 = confirmationViewModel.getIconContentDescription();
                str2 = confirmationViewModel.getHintText();
                str3 = confirmationViewModel.getBodyText();
                str9 = confirmationViewModel.getHeadlineText();
                str10 = confirmationViewModel.getLinkText();
                spannableStringBuilder2 = confirmationViewModel.getInlineHyperlinkText();
                str7 = confirmationViewModel.getOkButtonText();
            } else {
                str7 = null;
                str8 = null;
                str2 = null;
                str3 = null;
                str9 = null;
                str10 = null;
                spannableStringBuilder2 = null;
                i2 = 0;
                i8 = 0;
            }
            boolean z = i2 != 0;
            boolean z2 = str2 != null;
            boolean z3 = str3 != null;
            boolean z4 = str10 != null;
            boolean z5 = spannableStringBuilder2 != null;
            boolean z6 = str7 != null;
            if (i9 != 0) {
                j |= z ? 32L : 16L;
            }
            if ((j & 3) != 0) {
                j |= z2 ? PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH : 1024L;
            }
            if ((j & 3) != 0) {
                j |= z3 ? 8L : 4L;
            }
            if ((j & 3) != 0) {
                j |= z4 ? PlaybackStateCompat.ACTION_PLAY_FROM_URI : PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
            if ((j & 3) != 0) {
                j |= z5 ? 128L : 64L;
            }
            if ((j & 3) != 0) {
                j |= z6 ? 512L : 256L;
            }
            int i11 = z ? 0 : 8;
            int i12 = z2 ? 0 : 8;
            int i13 = z3 ? 0 : 8;
            int i14 = z4 ? 0 : 8;
            int i15 = z5 ? 0 : 8;
            if (!z6) {
                i10 = 8;
            }
            str5 = str7;
            i7 = i10;
            str6 = str8;
            str = str9;
            str4 = str10;
            spannableStringBuilder = spannableStringBuilder2;
            i = i11;
            i10 = i13;
            i6 = i14;
            i4 = i15;
            i5 = i8;
            i3 = i12;
        } else {
            str = null;
            spannableStringBuilder = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            str6 = null;
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
        }
        if ((3 & j) != 0) {
            TextViewBindingAdapter.setText(this.bodyText, str3);
            this.bodyText.setVisibility(i10);
            ImageViewAdapter.setSrc(this.controlIcon, i2);
            this.controlIcon.setVisibility(i);
            TextViewBindingAdapter.setText(this.headlineText, str);
            TextViewBindingAdapter.setText(this.hintText, str2);
            this.hintText.setVisibility(i3);
            this.hyperlinkText.setVisibility(i4);
            TextViewBindingAdapter.setText(this.hyperlinkText, spannableStringBuilder);
            TextViewBindingAdapter.setText(this.linkText, str4);
            this.linkText.setVisibility(i6);
            TextViewBindingAdapter.setText(this.okButton, str5);
            this.okButton.setVisibility(i7);
            if (ViewDataBinding.getBuildSdkInt() >= 21) {
                this.controlIcon.setImageTintList(Converters.convertColorToColorStateList(i5));
            }
            if (ViewDataBinding.getBuildSdkInt() >= 4) {
                this.controlIcon.setContentDescription(str6);
            }
        }
        if ((j & 2) != 0) {
            TextViewWithLinks.setTextViewHasLinks(this.hyperlinkText, true);
            this.linkText.setOnClickListener(this.mCallback7);
            this.okButton.setOnClickListener(this.mCallback8);
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
            this.mDirtyFlags = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewModel == i) {
            setViewModel((ConfirmationViewModel) obj);
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.biloba.databinding.ConfirmationBinding
    public void setViewModel(@Nullable ConfirmationViewModel confirmationViewModel) {
        this.mViewModel = confirmationViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    private ConfirmationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (FontTextView) objArr[3], (ImageView) objArr[1], (FontTextView) objArr[2], (FontTextView) objArr[6], (FontTextView) objArr[5], (FontTextView) objArr[4], (Button) objArr[7]);
        this.mDirtyFlags = -1L;
        this.bodyText.setTag(null);
        this.controlIcon.setTag(null);
        this.headlineText.setTag(null);
        this.hintText.setTag(null);
        this.hyperlinkText.setTag(null);
        this.linkText.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.okButton.setTag(null);
        setRootTag(view);
        this.mCallback8 = new OnClickListener(this, 2);
        this.mCallback7 = new OnClickListener(this, 1);
        invalidateAll();
    }
}
