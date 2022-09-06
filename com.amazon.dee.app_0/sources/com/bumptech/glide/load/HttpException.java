package com.bumptech.glide.load;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes.dex */
public final class HttpException extends IOException {
    public static final int UNKNOWN = -1;
    private static final long serialVersionUID = 1;
    private final int statusCode;

    public HttpException(int i) {
        this(GeneratedOutlineSupport1.outline49("Http request failed with status code: ", i), i);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public HttpException(String str) {
        this(str, -1);
    }

    public HttpException(String str, int i) {
        this(str, i, null);
    }

    public HttpException(String str, int i, @Nullable Throwable th) {
        super(str, th);
        this.statusCode = i;
    }
}
