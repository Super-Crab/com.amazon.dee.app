package com.amazon.alexa.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.net.URL;
/* loaded from: classes9.dex */
public class GlideRequests extends RequestManager {
    public GlideRequests(@NonNull Glide glide, @NonNull Lifecycle lifecycle, @NonNull RequestManagerTreeNode requestManagerTreeNode, @NonNull Context context) {
        super(glide, lifecycle, requestManagerTreeNode, context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bumptech.glide.RequestManager
    public void setRequestOptions(@NonNull RequestOptions requestOptions) {
        if (requestOptions instanceof GlideOptions) {
            super.setRequestOptions(requestOptions);
        } else {
            super.setRequestOptions(new GlideOptions().mo1570apply(requestOptions));
        }
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    /* renamed from: applyDefaultRequestOptions */
    public GlideRequests mo1633applyDefaultRequestOptions(@NonNull RequestOptions requestOptions) {
        return (GlideRequests) super.mo1633applyDefaultRequestOptions(requestOptions);
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    @CheckResult
    /* renamed from: as */
    public <ResourceType> GlideRequest<ResourceType> mo1634as(@NonNull Class<ResourceType> cls) {
        return new GlideRequest<>(this.glide, this, cls, this.context);
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    @CheckResult
    /* renamed from: asBitmap */
    public GlideRequest<Bitmap> mo1635asBitmap() {
        return (GlideRequest) super.mo1635asBitmap();
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    @CheckResult
    /* renamed from: asDrawable */
    public GlideRequest<Drawable> mo1636asDrawable() {
        return (GlideRequest) super.mo1636asDrawable();
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    @CheckResult
    /* renamed from: asFile */
    public GlideRequest<File> mo1637asFile() {
        return (GlideRequest) super.mo1637asFile();
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    @CheckResult
    /* renamed from: asGif */
    public GlideRequest<GifDrawable> mo1638asGif() {
        return (GlideRequest) super.mo1638asGif();
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    @CheckResult
    /* renamed from: download */
    public GlideRequest<File> mo1639download(@Nullable Object obj) {
        return (GlideRequest) super.mo1639download(obj);
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    @CheckResult
    /* renamed from: downloadOnly */
    public GlideRequest<File> mo1640downloadOnly() {
        return (GlideRequest) super.mo1640downloadOnly();
    }

    @Override // com.bumptech.glide.RequestManager
    @NonNull
    /* renamed from: setDefaultRequestOptions */
    public GlideRequests mo1650setDefaultRequestOptions(@NonNull RequestOptions requestOptions) {
        return (GlideRequests) super.mo1650setDefaultRequestOptions(requestOptions);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<Drawable> mo6756load(@Nullable Bitmap bitmap) {
        return (GlideRequest) super.mo6756load(bitmap);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<Drawable> mo6757load(@Nullable Drawable drawable) {
        return (GlideRequest) super.mo6757load(drawable);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<Drawable> mo6762load(@Nullable String str) {
        return (GlideRequest) super.mo6762load(str);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<Drawable> mo6758load(@Nullable Uri uri) {
        return (GlideRequest) super.mo6758load(uri);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<Drawable> mo6759load(@Nullable File file) {
        return (GlideRequest) super.mo6759load(file);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<Drawable> mo6760load(@Nullable Integer num) {
        return (GlideRequest) super.mo6760load(num);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @CheckResult
    @Deprecated
    /* renamed from: load */
    public GlideRequest<Drawable> mo6763load(@Nullable URL url) {
        return (GlideRequest) super.mo6763load(url);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<Drawable> mo6764load(@Nullable byte[] bArr) {
        return (GlideRequest) super.mo6764load(bArr);
    }

    @Override // com.bumptech.glide.RequestManager, com.bumptech.glide.ModelTypes
    @NonNull
    @CheckResult
    /* renamed from: load */
    public GlideRequest<Drawable> mo6761load(@Nullable Object obj) {
        return (GlideRequest) super.mo6761load(obj);
    }
}
