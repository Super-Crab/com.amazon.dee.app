package com.amazon.alexa.imageloader.api;

import android.widget.ImageView;
import java.net.URI;
/* loaded from: classes9.dex */
public interface ImageLoader {
    void loadInto(String str, ImageView imageView);

    void loadInto(URI uri, ImageView imageView);
}
