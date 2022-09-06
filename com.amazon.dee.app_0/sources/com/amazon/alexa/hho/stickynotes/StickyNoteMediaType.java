package com.amazon.alexa.hho.stickynotes;

import android.util.Log;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public enum StickyNoteMediaType {
    VIDEO,
    THUMBNAIL,
    IMAGE;
    
    private static final String TAG = StickyNoteMediaType.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static StickyNoteMediaType fromString(String str) {
        try {
            return valueOf(str);
        } catch (IllegalArgumentException e) {
            String str2 = TAG;
            Log.e(str2, "Unsupported media type: " + str, e);
            return null;
        }
    }
}
