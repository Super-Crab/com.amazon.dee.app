package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.cards.CardButton;
import com.amazon.alexa.biloba.view.cards.TipsCard;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public class TipsCardBindingImpl extends TipsCardBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.tips_card_title_container, 7);
        sViewsWithIds.put(R.id.tips_overflow_menu, 8);
    }

    public TipsCardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int i;
        View.OnClickListener onClickListener;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        View.OnClickListener onClickListener2;
        CardButton cardButton;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        TipsCard tipsCard = this.mCard;
        int i2 = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        int i3 = 0;
        View.OnClickListener onClickListener3 = null;
        if (i2 != 0) {
            if (tipsCard != null) {
                onClickListener2 = tipsCard.getDismissClickListener();
                i = tipsCard.getBackgroundColorAttribute(getRoot().getContext());
                cardButton = tipsCard.getPrimaryAction();
                str3 = tipsCard.getDescription();
                str4 = tipsCard.getIconUrl();
                str5 = tipsCard.getTitle();
            } else {
                i = 0;
                str5 = null;
                onClickListener2 = null;
                cardButton = null;
                str3 = null;
                str4 = null;
            }
            boolean z = onClickListener2 == null;
            if (i2 != 0) {
                j |= z ? 8L : 4L;
            }
            if (cardButton != null) {
                onClickListener3 = cardButton.getClickListener();
                str = cardButton.getButtonText();
            } else {
                str = null;
            }
            if (z) {
                i3 = 8;
            }
            View.OnClickListener onClickListener4 = onClickListener2;
            str2 = str5;
            onClickListener = onClickListener3;
            onClickListener3 = onClickListener4;
        } else {
            i = 0;
            onClickListener = null;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
        }
        if ((j & 3) != 0) {
            this.bilobaOverflowMenu.setOnClickListener(onClickListener3);
            this.bilobaOverflowMenu.setVisibility(i3);
            this.tipsCard.setCardBackgroundColor(i);
            TextViewBindingAdapter.setText(this.tipsCardBody, str3);
            TextViewBindingAdapter.setText(this.tipsCardButton, str);
            this.tipsCardButton.setOnClickListener(onClickListener);
            ImageViewAdapter.setImageUrl(this.tipsCardIcon, str4);
            TextViewBindingAdapter.setText(this.tipsCardTitle, str2);
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

    @Override // com.amazon.alexa.biloba.databinding.TipsCardBinding
    public void setCard(@Nullable TipsCard tipsCard) {
        this.mCard = tipsCard;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.card);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.card == i) {
            setCard((TipsCard) obj);
            return true;
        }
        return false;
    }

    private TipsCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[4], (CardView) objArr[1], (FontTextView) objArr[3], (Button) objArr[5], (ImageView) objArr[6], (RelativeLayout) objArr[0], (FontTextView) objArr[2], (LinearLayout) objArr[7], (LinearLayout) objArr[8]);
        this.mDirtyFlags = -1L;
        this.bilobaOverflowMenu.setTag(null);
        this.tipsCard.setTag(null);
        this.tipsCardBody.setTag(null);
        this.tipsCardButton.setTag(null);
        this.tipsCardIcon.setTag(null);
        this.tipsCardLayout.setTag(null);
        this.tipsCardTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
