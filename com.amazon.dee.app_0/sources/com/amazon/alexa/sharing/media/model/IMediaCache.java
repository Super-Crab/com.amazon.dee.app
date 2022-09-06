package com.amazon.alexa.sharing.media.model;

import com.amazon.alexa.sharing.media.MediaFileContent;
import java.io.File;
import java.io.IOException;
/* loaded from: classes10.dex */
public interface IMediaCache {
    void clearAll() throws IOException;

    boolean exists(String str) throws IOException;

    MediaFileContent get(String str) throws IOException;

    File getDirectory();

    void put(String str, MediaFileContent mediaFileContent, boolean z) throws IOException;

    boolean remove(String str) throws IOException;
}
