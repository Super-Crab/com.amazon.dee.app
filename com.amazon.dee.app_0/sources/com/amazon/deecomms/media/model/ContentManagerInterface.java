package com.amazon.deecomms.media.model;
/* loaded from: classes12.dex */
public interface ContentManagerInterface {
    void clearExpiredMedia();

    void clearMediaCache();

    boolean existsInCache(String str);

    MediaFileContent getFromCache(String str);

    void putInCache(MediaFileContent mediaFileContent, String str, boolean z);

    boolean removeFromCache(String str);
}
