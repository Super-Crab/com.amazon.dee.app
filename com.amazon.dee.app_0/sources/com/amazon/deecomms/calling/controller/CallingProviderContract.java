package com.amazon.deecomms.calling.controller;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.R;
import com.android.tools.r8.GeneratedOutlineSupport;
import javax.annotation.Nonnull;
/* loaded from: classes12.dex */
public final class CallingProviderContract {
    public static final String CALLS_PATH = "calls";
    public static final String CONTENT_SCHEME = "content";

    private CallingProviderContract() {
    }

    public static String getAuthorityId(@Nonnull Context context) {
        return context.getString(R.string.callingProviderAuthoritiesId);
    }

    public static Uri getCallUri(@Nonnull Context context) {
        return new Uri.Builder().scheme("content").authority(getAuthorityId(context)).path(CALLS_PATH).build();
    }

    public static String getCallsContentType(@Nonnull Context context) {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("vnd.android.cursor.item/vnd.");
        outline1.append(getAuthorityId(context));
        outline1.append(".calls");
        return outline1.toString();
    }

    @VisibleForTesting
    static CallingProviderContract getInstance() {
        return new CallingProviderContract();
    }
}
