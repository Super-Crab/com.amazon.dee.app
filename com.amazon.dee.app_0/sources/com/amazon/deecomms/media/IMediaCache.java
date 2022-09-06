package com.amazon.deecomms.media;

import com.amazon.deecomms.media.model.MediaFileContent;
import com.amazon.deecomms.media.model.MediaStreamContent;
import java.io.File;
import java.io.IOException;
/* loaded from: classes12.dex */
public interface IMediaCache {
    void clearAll() throws IOException;

    boolean exists(String str) throws IOException;

    MediaFileContent get(String str) throws IOException;

    File getDirectory();

    void put(String str, MediaFileContent mediaFileContent, boolean z) throws IOException;

    void put(String str, MediaStreamContent mediaStreamContent, boolean z) throws IOException;

    boolean remove(String str) throws IOException;
}
