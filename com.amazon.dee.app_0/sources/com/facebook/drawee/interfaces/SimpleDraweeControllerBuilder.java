package com.facebook.drawee.interfaces;

import android.net.Uri;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface SimpleDraweeControllerBuilder {
    /* renamed from: build */
    DraweeController mo6879build();

    /* renamed from: setCallerContext */
    SimpleDraweeControllerBuilder mo6880setCallerContext(Object callerContext);

    /* renamed from: setOldController */
    SimpleDraweeControllerBuilder mo6881setOldController(@Nullable DraweeController oldController);

    /* renamed from: setUri */
    SimpleDraweeControllerBuilder mo6876setUri(Uri uri);

    /* renamed from: setUri */
    SimpleDraweeControllerBuilder mo6877setUri(@Nullable String uriString);
}
