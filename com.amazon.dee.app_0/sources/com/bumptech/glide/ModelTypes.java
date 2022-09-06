package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.net.URL;
/* loaded from: classes.dex */
interface ModelTypes<T> {
    @NonNull
    @CheckResult
    /* renamed from: load */
    T mo6756load(@Nullable Bitmap bitmap);

    @NonNull
    @CheckResult
    /* renamed from: load */
    T mo6757load(@Nullable Drawable drawable);

    @NonNull
    @CheckResult
    /* renamed from: load */
    T mo6758load(@Nullable Uri uri);

    @NonNull
    @CheckResult
    /* renamed from: load */
    T mo6759load(@Nullable File file);

    @NonNull
    @CheckResult
    /* renamed from: load */
    T mo6760load(@Nullable @DrawableRes @RawRes Integer num);

    @NonNull
    @CheckResult
    /* renamed from: load */
    T mo6761load(@Nullable Object obj);

    @NonNull
    @CheckResult
    /* renamed from: load */
    T mo6762load(@Nullable String str);

    @CheckResult
    @Deprecated
    /* renamed from: load */
    T mo6763load(@Nullable URL url);

    @NonNull
    @CheckResult
    /* renamed from: load */
    T mo6764load(@Nullable byte[] bArr);
}
