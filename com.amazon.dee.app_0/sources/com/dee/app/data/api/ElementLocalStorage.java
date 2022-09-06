package com.dee.app.data.api;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.AppDataCacheEntry;
import rx.Observable;
/* loaded from: classes10.dex */
public interface ElementLocalStorage {
    Observable<Void> clear(String str) throws IllegalArgumentException;

    default Observable<AppDataCacheEntry> get(String str) throws IllegalArgumentException {
        return get(str, (Bundle) null);
    }

    Observable<AppDataCacheEntry> get(String str, @Nullable Bundle bundle) throws IllegalArgumentException;

    Observable<AppDataCacheEntry> put(String str, String str2, @Nullable Bundle bundle) throws IllegalArgumentException;

    default Observable<AppDataCacheEntry> put(String str, String str2, String str3, @Nullable Bundle bundle) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                return put(GeneratedOutlineSupport1.outline75(str, ".", str2), str3, bundle);
            }
            throw new IllegalArgumentException("Cannot put value with empty key argument.");
        }
        throw new IllegalArgumentException("Cannot put value with empty namespace argument.");
    }

    Observable<Void> remove(String str) throws IllegalArgumentException;

    default Observable<Void> remove(String str, String str2) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                return remove(str + "." + str2);
            }
            throw new IllegalArgumentException("Cannot removee value with empty key argument.");
        }
        throw new IllegalArgumentException("Cannot remove value with empty namespace argument.");
    }

    default Observable<AppDataCacheEntry> get(String str, String str2, @Nullable Bundle bundle) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                return get(str + "." + str2, bundle);
            }
            throw new IllegalArgumentException("Cannot get value with empty key argument.");
        }
        throw new IllegalArgumentException("Cannot get value with empty namespace argument.");
    }

    default Observable<AppDataCacheEntry> get(String str, String str2) throws IllegalArgumentException {
        return get(str, str2, null);
    }
}
