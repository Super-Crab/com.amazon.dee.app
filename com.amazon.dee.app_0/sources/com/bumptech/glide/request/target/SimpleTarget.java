package com.bumptech.glide.request.target;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.util.Util;
/* loaded from: classes9.dex */
public abstract class SimpleTarget<Z> extends BaseTarget<Z> {
    private final int height;
    private final int width;

    public SimpleTarget() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Override // com.bumptech.glide.request.target.Target
    public final void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
        if (Util.isValidDimensions(this.width, this.height)) {
            sizeReadyCallback.onSizeReady(this.width, this.height);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ");
        outline107.append(this.width);
        outline107.append(" and height: ");
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(outline107, this.height, ", either provide dimensions in the constructor or call override()"));
    }

    @Override // com.bumptech.glide.request.target.Target
    public void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
    }

    public SimpleTarget(int i, int i2) {
        this.width = i;
        this.height = i2;
    }
}
