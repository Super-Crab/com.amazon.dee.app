package com.amazon.deecomms.calling.ui;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
/* loaded from: classes12.dex */
public class GlideImageLoader implements ImageLoader {
    @Override // com.amazon.deecomms.calling.ui.ImageLoader
    public void loadImage(ImageView imageView, String str) {
        Glide.with(imageView).mo6762load(str).mo1615apply(new RequestOptions().mo1578diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)).into(imageView);
    }
}
