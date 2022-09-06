package com.amazon.dee.app.services.header;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.web.HeaderInfoBridge;
import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import java.lang.ref.WeakReference;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class DefaultHeaderCacheService implements HeaderCacheService {
    private static final String TAG = Log.tag(DefaultHeaderCacheService.class);
    @Nullable
    private JSONObject cachedHeader = null;
    @Nullable
    private WeakReference<JavaScriptDelegate> javaScriptDelegateRef;

    @Override // com.amazon.dee.app.services.header.HeaderCacheService
    public void cacheRequest(@NonNull JSONObject jSONObject) {
        this.cachedHeader = jSONObject;
    }

    @Override // com.amazon.dee.app.services.header.HeaderCacheService
    public void loadCachedRequest() {
        WeakReference<JavaScriptDelegate> weakReference = this.javaScriptDelegateRef;
        if (weakReference == null || this.cachedHeader == null) {
            return;
        }
        try {
            HeaderInfoBridge.setHeader(weakReference.get(), this.cachedHeader);
        } catch (Exception e) {
            Log.e(TAG, "header error: ", e);
        }
    }

    @Override // com.amazon.dee.app.services.header.HeaderCacheService
    public void setJavaScriptDelegate(@NonNull JavaScriptDelegate javaScriptDelegate) {
        this.javaScriptDelegateRef = new WeakReference<>(javaScriptDelegate);
    }
}
