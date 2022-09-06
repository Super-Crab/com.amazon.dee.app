package com.facebook.react.views.switchview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
/* loaded from: classes2.dex */
class ReactSwitch extends SwitchCompat {
    private boolean mAllowChange;
    @Nullable
    private Integer mTrackColorForFalse;
    @Nullable
    private Integer mTrackColorForTrue;

    public ReactSwitch(Context context) {
        super(context);
        this.mAllowChange = true;
        this.mTrackColorForFalse = null;
        this.mTrackColorForTrue = null;
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.mAllowChange && isChecked() != z) {
            this.mAllowChange = false;
            super.setChecked(z);
            setTrackColor(z);
            return;
        }
        super.setChecked(isChecked());
    }

    void setColor(Drawable drawable, @Nullable Integer num) {
        if (num == null) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOn(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            setTrackColor(z);
        }
        this.mAllowChange = true;
    }

    public void setThumbColor(@Nullable Integer num) {
        setColor(super.getThumbDrawable(), num);
    }

    public void setTrackColor(@Nullable Integer num) {
        setColor(super.getTrackDrawable(), num);
    }

    public void setTrackColorForFalse(@Nullable Integer num) {
        if (num == this.mTrackColorForFalse) {
            return;
        }
        this.mTrackColorForFalse = num;
        if (isChecked()) {
            return;
        }
        setTrackColor(this.mTrackColorForFalse);
    }

    public void setTrackColorForTrue(@Nullable Integer num) {
        if (num == this.mTrackColorForTrue) {
            return;
        }
        this.mTrackColorForTrue = num;
        if (!isChecked()) {
            return;
        }
        setTrackColor(this.mTrackColorForTrue);
    }

    private void setTrackColor(boolean z) {
        if (this.mTrackColorForTrue == null && this.mTrackColorForFalse == null) {
            return;
        }
        setTrackColor(z ? this.mTrackColorForTrue : this.mTrackColorForFalse);
    }
}
