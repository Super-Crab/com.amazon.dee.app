package com.google.android.gms.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
/* loaded from: classes2.dex */
public final class zae extends Drawable implements Drawable.Callback {
    private int mAlpha;
    private int mFrom;
    private boolean zamz;
    private int zanh;
    private long zani;
    private int zanj;
    private int zank;
    private int zanl;
    private boolean zanm;
    private zai zann;
    private Drawable zano;
    private Drawable zanp;
    private boolean zanq;
    private boolean zanr;
    private boolean zans;
    private int zant;

    public zae(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zag.zanu : drawable;
        this.zano = drawable;
        drawable.setCallback(this);
        zai zaiVar = this.zann;
        zaiVar.zanw = drawable.getChangingConfigurations() | zaiVar.zanw;
        drawable2 = drawable2 == null ? zag.zanu : drawable2;
        this.zanp = drawable2;
        drawable2.setCallback(this);
        zai zaiVar2 = this.zann;
        zaiVar2.zanw = drawable2.getChangingConfigurations() | zaiVar2.zanw;
    }

    private final boolean canConstantState() {
        if (!this.zanq) {
            this.zanr = (this.zano.getConstantState() == null || this.zanp.getConstantState() == null) ? false : true;
            this.zanq = true;
        }
        return this.zanr;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i = this.zanh;
        boolean z = true;
        if (i != 1) {
            if (i == 2 && this.zani >= 0) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zani)) / this.zanl;
                if (uptimeMillis < 1.0f) {
                    z = false;
                }
                if (z) {
                    this.zanh = 0;
                }
                this.mAlpha = (int) ((this.zanj * Math.min(uptimeMillis, 1.0f)) + 0.0f);
            }
        } else {
            this.zani = SystemClock.uptimeMillis();
            this.zanh = 2;
            z = false;
        }
        int i2 = this.mAlpha;
        boolean z2 = this.zamz;
        Drawable drawable = this.zano;
        Drawable drawable2 = this.zanp;
        if (z) {
            if (!z2 || i2 == 0) {
                drawable.draw(canvas);
            }
            int i3 = this.zank;
            if (i2 != i3) {
                return;
            }
            drawable2.setAlpha(i3);
            drawable2.draw(canvas);
            return;
        }
        if (z2) {
            drawable.setAlpha(this.zank - i2);
        }
        drawable.draw(canvas);
        if (z2) {
            drawable.setAlpha(this.zank);
        }
        if (i2 > 0) {
            drawable2.setAlpha(i2);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zank);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        zai zaiVar = this.zann;
        return changingConfigurations | zaiVar.mChangingConfigurations | zaiVar.zanw;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (canConstantState()) {
            this.zann.mChangingConfigurations = getChangingConfigurations();
            return this.zann;
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.zano.getIntrinsicHeight(), this.zanp.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.zano.getIntrinsicWidth(), this.zanp.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if (!this.zans) {
            this.zant = Drawable.resolveOpacity(this.zano.getOpacity(), this.zanp.getOpacity());
            this.zans = true;
        }
        return this.zant;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.zanm && super.mutate() == this) {
            if (canConstantState()) {
                this.zano.mutate();
                this.zanp.mutate();
                this.zanm = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        this.zano.setBounds(rect);
        this.zanp.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.mAlpha == this.zank) {
            this.mAlpha = i;
        }
        this.zank = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.zano.setColorFilter(colorFilter);
        this.zanp.setColorFilter(colorFilter);
    }

    public final void startTransition(int i) {
        this.mFrom = 0;
        this.zanj = this.zank;
        this.mAlpha = 0;
        this.zanl = 250;
        this.zanh = 1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final Drawable zacf() {
        return this.zanp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zae(zai zaiVar) {
        this.zanh = 0;
        this.zank = 255;
        this.mAlpha = 0;
        this.zamz = true;
        this.zann = new zai(zaiVar);
    }
}
