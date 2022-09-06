package com.amazon.alexa.accessory.streams.diagnostics;

import com.amazon.alexa.accessory.AccessoryStream;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.SizedSource;
/* loaded from: classes6.dex */
public final class DiagnosticsStream implements AccessoryStream {
    private final byte[] buffer;
    private final Callback callback;
    private boolean finished;
    private int read;
    private final Sink sink;
    private final int size;

    /* loaded from: classes6.dex */
    public interface Callback {
        void onComplete();
    }

    public DiagnosticsStream(Sink sink, int i, Callback callback) {
        Preconditions.notNull(sink, "sink");
        Preconditions.notNegative(i, "size");
        Preconditions.notNull(callback, "callback");
        this.sink = sink;
        this.size = i;
        this.callback = callback;
        this.buffer = new byte[2048];
    }

    @Override // com.amazon.alexa.accessory.AccessoryStream
    public int getId() {
        return 3;
    }

    @Override // com.amazon.alexa.accessory.AccessoryStream
    public boolean handleData(SizedSource sizedSource) throws Exception {
        if (this.finished) {
            return false;
        }
        sizedSource.reset();
        int min = Math.min(this.size - this.read, sizedSource.size());
        this.read += min;
        IOUtils.transfer(sizedSource, this.sink, this.buffer, min);
        Logger.d("Diagnostics stream: read %d/%d", Integer.valueOf(this.read), Integer.valueOf(this.size));
        if (this.read >= this.size) {
            this.finished = true;
            this.callback.onComplete();
        }
        return true;
    }
}
