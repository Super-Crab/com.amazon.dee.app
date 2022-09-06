package org.reactnative.frame;

import com.google.android.gms.vision.Frame;
import org.reactnative.camera.utils.ImageDimensions;
/* loaded from: classes5.dex */
public class RNFrame {
    private ImageDimensions mDimensions;
    private Frame mFrame;

    public RNFrame(Frame frame, ImageDimensions imageDimensions) {
        this.mFrame = frame;
        this.mDimensions = imageDimensions;
    }

    public ImageDimensions getDimensions() {
        return this.mDimensions;
    }

    public Frame getFrame() {
        return this.mFrame;
    }
}
