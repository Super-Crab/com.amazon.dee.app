package com.amazon.alexa.voice.ui.onedesign.util.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
/* loaded from: classes11.dex */
final class RoundedCornerImageLoaderTask extends CancellableRunnable {
    private final Context context;
    private final ImageView imageView;
    private final RequestManager requestManager;
    private final String url;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RoundedCornerImageLoaderTask(@NonNull ImageView imageView, @NonNull RequestManager requestManager, @NonNull Context context, @NonNull String str) {
        this.imageView = imageView;
        this.requestManager = requestManager;
        this.context = context;
        this.url = str;
    }

    private Transformation<Bitmap> createTransform(int i, ImageView.ScaleType scaleType) {
        RoundedCorners roundedCorners = new RoundedCorners(i);
        if (scaleType == ImageView.ScaleType.CENTER_CROP) {
            return new MultiTransformation(new CenterCrop(), roundedCorners);
        }
        return scaleType == ImageView.ScaleType.FIT_CENTER ? new MultiTransformation(new FitCenter(), roundedCorners) : roundedCorners;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.util.image.CancellableRunnable
    public void onCancelled() {
        this.imageView.removeCallbacks(this);
        this.requestManager.clear(this.imageView);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.util.image.CancellableRunnable, java.lang.Runnable
    public void run() {
        int max = this.imageView.getMaxWidth() != Integer.MAX_VALUE ? Math.max(this.imageView.getWidth(), this.imageView.getMaxWidth()) : this.imageView.getWidth();
        int max2 = this.imageView.getMaxHeight() != Integer.MAX_VALUE ? Math.max(this.imageView.getHeight(), this.imageView.getMaxHeight()) : this.imageView.getHeight();
        this.requestManager.mo6762load(RenderingServiceUrls.scaleToHeight(this.url, max2)).mo1615apply(new RequestOptions().mo1600override(max, max2).mo1610transform(createTransform(this.context.getResources().getDimensionPixelSize(R.dimen.voice_ui_od_image_rounded_corner_radius), this.imageView.getScaleType()))).into(this.imageView);
    }
}
