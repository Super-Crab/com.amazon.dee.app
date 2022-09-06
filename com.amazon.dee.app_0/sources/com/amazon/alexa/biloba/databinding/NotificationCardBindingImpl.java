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
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.cards.NotificationCard;
import com.amazon.alexa.font.FontTextView;
/* loaded from: classes6.dex */
public class NotificationCardBindingImpl extends NotificationCardBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.notification_card_title_container, 8);
        sViewsWithIds.put(R.id.notification_overflow_menu, 9);
    }

    public NotificationCardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.databinding.NotificationCardBindingImpl.executeBindings():void");
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

    @Override // com.amazon.alexa.biloba.databinding.NotificationCardBinding
    public void setCard(@Nullable NotificationCard notificationCard) {
        this.mCard = notificationCard;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.card);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.card == i) {
            setCard((NotificationCard) obj);
            return true;
        }
        return false;
    }

    private NotificationCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[5], (Button) objArr[6], (CardView) objArr[1], (FontTextView) objArr[4], (FontTextView) objArr[2], (RelativeLayout) objArr[0], (FontTextView) objArr[3], (LinearLayout) objArr[8], (Button) objArr[7], (LinearLayout) objArr[9]);
        this.mDirtyFlags = -1L;
        this.bilobaOverflowMenu.setTag(null);
        this.notificationCallButton.setTag(null);
        this.notificationCard.setTag(null);
        this.notificationCardBody.setTag(null);
        this.notificationCardHeader.setTag(null);
        this.notificationCardLayout.setTag(null);
        this.notificationCardTitle.setTag(null);
        this.notificationDropInButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
