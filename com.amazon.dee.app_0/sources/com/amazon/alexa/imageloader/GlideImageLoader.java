package com.amazon.alexa.imageloader;

import android.net.Uri;
import android.widget.ImageView;
import com.amazon.alexa.imageloader.api.ImageLoader;
import java.io.File;
import java.net.URI;
/* loaded from: classes9.dex */
public class GlideImageLoader implements ImageLoader {
    @Override // com.amazon.alexa.imageloader.api.ImageLoader
    public void loadInto(URI uri, ImageView imageView) {
        GlideApp.with(imageView.getContext()).mo6758load(Uri.parse(uri.toString())).into(imageView);
    }

    @Override // com.amazon.alexa.imageloader.api.ImageLoader
    public void loadInto(String str, ImageView imageView) {
        GlideApp.with(imageView.getContext()).mo6759load(new File(str)).into(imageView);
    }
}
