package com.amazon.alexa.accessory.repositories.sidewalk;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProducer;
import java.io.IOException;
/* loaded from: classes6.dex */
public class MemorySidewalkSink implements Sink {
    private static final String TAG = "MemorySidewalkSink:";
    private boolean closed;
    private SidewalkProducer.ActionHandler sidewalkActionHandler;

    public MemorySidewalkSink(SidewalkProducer.ActionHandler actionHandler) {
        Preconditions.notNull(actionHandler, "sidewalkActionHandler");
        this.sidewalkActionHandler = actionHandler;
        this.closed = false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            Logger.d("%s Sink already closed", TAG);
            return;
        }
        Logger.d("%s Closing Sink...", TAG);
        this.closed = true;
        this.sidewalkActionHandler = null;
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void flush() throws IOException {
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            Logger.d("%s cannot write data to handler. Sink is closed", TAG);
        } else {
            this.sidewalkActionHandler.handleWriteData(new ByteArraySource(bArr, i, i2));
        }
    }
}
