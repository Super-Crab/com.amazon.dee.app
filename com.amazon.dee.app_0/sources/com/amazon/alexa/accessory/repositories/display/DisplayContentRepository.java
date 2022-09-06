package com.amazon.alexa.accessory.repositories.display;

import com.amazon.alexa.accessory.protocol.Cardrendering;
import io.reactivex.rxjava3.core.Completable;
/* loaded from: classes6.dex */
public interface DisplayContentRepository {
    public static final int MAX_CHUNK_SIZE_IN_BYTES = 1024;

    Completable setDisplayContentByChunks(String str, Cardrendering.DisplayContentType displayContentType, int i, int i2, int i3);
}
