package com.amazon.dee.app.services.header;

import androidx.annotation.NonNull;
import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public interface HeaderCacheService {
    void cacheRequest(@NonNull JSONObject jSONObject);

    void loadCachedRequest();

    void setJavaScriptDelegate(@NonNull JavaScriptDelegate javaScriptDelegate);
}
