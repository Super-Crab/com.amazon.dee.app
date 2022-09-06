package com.amazon.mobile.heremapsexplore.Constants;

import com.here.sdk.core.Anchor2D;
/* loaded from: classes13.dex */
public interface MarkerLayoutPositionable {

    /* loaded from: classes13.dex */
    public interface Anchored {
        Anchor2D getAnchor();
    }

    /* loaded from: classes13.dex */
    public enum LayoutPosition implements Anchored {
        CENTER { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable.LayoutPosition.1
            @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable.Anchored
            public Anchor2D getAnchor() {
                return new Anchor2D(0.5d, 0.5d);
            }
        },
        BOTTOM_CENTER { // from class: com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable.LayoutPosition.2
            @Override // com.amazon.mobile.heremapsexplore.Constants.MarkerLayoutPositionable.Anchored
            public Anchor2D getAnchor() {
                return new Anchor2D(0.5d, 1.0d);
            }
        }
    }

    LayoutPosition getLayoutPosition();
}
