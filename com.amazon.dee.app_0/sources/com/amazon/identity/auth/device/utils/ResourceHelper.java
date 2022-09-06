package com.amazon.identity.auth.device.utils;

import android.content.Context;
import android.content.res.Resources;
import com.amazon.identity.auth.device.io;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ResourceHelper {
    private static final String TAG = "com.amazon.identity.auth.device.utils.ResourceHelper";

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class ResourceNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ResourceNotFoundException(String str) {
            super(str);
        }
    }

    private ResourceHelper() {
    }

    public static int A(Context context, String str) {
        return r(context, "anim", str);
    }

    public static int B(Context context, String str) {
        return r(context, TtmlNode.TAG_LAYOUT, str);
    }

    public static int r(Context context, String str, String str2) {
        int identifier = context.getResources().getIdentifier(str2, str, context.getPackageName());
        if (identifier != 0) {
            return identifier;
        }
        io.w(TAG, String.format("The %s resource %s has not been found", str, str2));
        throw new ResourceNotFoundException(String.format("%s Resource %s not found", str, str2));
    }

    public static String y(Context context, String str) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(str, "string", context.getPackageName());
        if (identifier != 0) {
            return resources.getString(identifier);
        }
        io.w(TAG, String.format("The String resource %s has not been found", str));
        throw new ResourceNotFoundException(String.format("String Resource %s not found", str));
    }

    public static int z(Context context, String str) {
        return r(context, "id", str);
    }
}
