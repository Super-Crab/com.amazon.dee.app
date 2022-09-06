package com.amazon.alexa.voice.ui.onedesign.util.image;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.Glide;
/* loaded from: classes11.dex */
public final class RoundedCornerImageLoader implements ImageLoader {
    private CancellableRunnable imageLoadingTask;
    private final ImageView imageView;

    public RoundedCornerImageLoader(@NonNull ImageView imageView) {
        this.imageView = imageView;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.util.image.ImageLoader
    public void load(String str) {
        Context applicationContext = this.imageView.getContext().getApplicationContext();
        load(new RoundedCornerImageLoaderTask(this.imageView, Glide.with(applicationContext), applicationContext, str));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.util.image.ImageLoader
    public void unload() {
        CancellableRunnable cancellableRunnable = this.imageLoadingTask;
        if (cancellableRunnable != null) {
            cancellableRunnable.cancel();
        }
    }

    @VisibleForTesting
    void load(CancellableRunnable cancellableRunnable) {
        unload();
        this.imageLoadingTask = cancellableRunnable;
        this.imageView.post(cancellableRunnable);
    }
}
