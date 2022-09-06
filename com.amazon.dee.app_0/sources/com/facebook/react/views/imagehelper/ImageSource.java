package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.infer.annotation.Assertions;
/* loaded from: classes2.dex */
public class ImageSource {
    private boolean isResource;
    private double mSize;
    private String mSource;
    @Nullable
    private Uri mUri;

    public ImageSource(Context context, String str, double d, double d2) {
        this.mSource = str;
        this.mSize = d * d2;
        this.mUri = computeUri(context);
    }

    private Uri computeLocalUri(Context context) {
        this.isResource = true;
        return ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(context, this.mSource);
    }

    private Uri computeUri(Context context) {
        try {
            Uri parse = Uri.parse(this.mSource);
            return parse.getScheme() == null ? computeLocalUri(context) : parse;
        } catch (Exception unused) {
            return computeLocalUri(context);
        }
    }

    public double getSize() {
        return this.mSize;
    }

    public String getSource() {
        return this.mSource;
    }

    public Uri getUri() {
        return (Uri) Assertions.assertNotNull(this.mUri);
    }

    public boolean isResource() {
        return this.isResource;
    }

    public ImageSource(Context context, String str) {
        this(context, str, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }
}
