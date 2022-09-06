package com.amazon.alexa.sharing.media.model;

import com.amazon.alexa.sharing.media.MediaFileContent;
/* loaded from: classes10.dex */
public interface ContentManagerInterface {
    void clearMediaCache();

    boolean existsInCache(String str);

    MediaFileContent getFromCache(String str);

    void putInCache(MediaFileContent mediaFileContent, String str, boolean z);

    boolean removeFromCache(String str);
}
