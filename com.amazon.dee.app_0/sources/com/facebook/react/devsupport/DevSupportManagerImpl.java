package com.facebook.react.devsupport;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.packagerconnection.RequestHandler;
import java.util.Map;
/* loaded from: classes2.dex */
public final class DevSupportManagerImpl extends DevSupportManagerBase {
    public DevSupportManagerImpl(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper, @Nullable String str, boolean z, int i) {
        super(context, reactInstanceManagerDevHelper, str, z, null, null, i, null);
    }

    public DevSupportManagerImpl(Context context, ReactInstanceManagerDevHelper reactInstanceManagerDevHelper, @Nullable String str, boolean z, @Nullable RedBoxHandler redBoxHandler, @Nullable DevBundleDownloadListener devBundleDownloadListener, int i, @Nullable Map<String, RequestHandler> map) {
        super(context, reactInstanceManagerDevHelper, str, z, redBoxHandler, devBundleDownloadListener, i, map);
    }
}
