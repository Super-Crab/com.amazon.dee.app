package com.facebook.cache.common;

import android.net.Uri;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.internal.Preconditions;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class MultiCacheKey implements CacheKey {
    final List<CacheKey> mCacheKeys;

    public MultiCacheKey(List<CacheKey> cacheKeys) {
        this.mCacheKeys = (List) Preconditions.checkNotNull(cacheKeys);
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean containsUri(Uri uri) {
        for (int i = 0; i < this.mCacheKeys.size(); i++) {
            if (this.mCacheKeys.get(i).containsUri(uri)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean equals(@Nullable Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MultiCacheKey)) {
            return false;
        }
        return this.mCacheKeys.equals(((MultiCacheKey) o).mCacheKeys);
    }

    public List<CacheKey> getCacheKeys() {
        return this.mCacheKeys;
    }

    @Override // com.facebook.cache.common.CacheKey
    public String getUriString() {
        return this.mCacheKeys.get(0).getUriString();
    }

    @Override // com.facebook.cache.common.CacheKey
    public int hashCode() {
        return this.mCacheKeys.hashCode();
    }

    @Override // com.facebook.cache.common.CacheKey
    public boolean isResourceIdForDebugging() {
        return false;
    }

    @Override // com.facebook.cache.common.CacheKey
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MultiCacheKey:");
        outline107.append(this.mCacheKeys.toString());
        return outline107.toString();
    }
}
