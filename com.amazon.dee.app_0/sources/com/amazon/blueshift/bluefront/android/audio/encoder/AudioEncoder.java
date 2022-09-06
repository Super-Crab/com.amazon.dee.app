package com.amazon.blueshift.bluefront.android.audio.encoder;

import com.google.common.net.MediaType;
import java.io.Closeable;
/* loaded from: classes11.dex */
public interface AudioEncoder extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    byte[] encode(short[] sArr, int i) throws AudioEncoderException;

    int getFrameSize();

    MediaType getMediaType();

    int getPacketSize();
}
