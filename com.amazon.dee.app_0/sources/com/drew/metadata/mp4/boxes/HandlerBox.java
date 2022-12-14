package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import java.io.IOException;
import java.nio.charset.Charset;
/* loaded from: classes2.dex */
public class HandlerBox extends FullBox {
    String handlerType;
    String name;

    public HandlerBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        sequentialReader.skip(4L);
        this.handlerType = sequentialReader.getString(4);
        sequentialReader.skip(12L);
        this.name = sequentialReader.getNullTerminatedString(((int) this.size) - 32, Charset.defaultCharset());
    }

    public String getHandlerType() {
        return this.handlerType;
    }
}
