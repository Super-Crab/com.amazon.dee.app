package com.amazon.alexa.accessory.transport.codecs.muffin;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.io.BufferPool;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Buffer;
import com.amazon.alexa.accessory.io.DataSink;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.transport.TransportPriority;
import com.amazon.alexa.accessory.transport.operations.TransportOperation;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.io.IOException;
import java.util.Locale;
/* loaded from: classes6.dex */
public final class MuffinPacketTransportOperation implements TransportOperation {
    private final SizedSource data;
    private final String key;
    private final TransportPriority priority;
    private final int stream;
    private final AccessoryTransport transport;

    public MuffinPacketTransportOperation(String str, AccessoryTransport accessoryTransport, SizedSource sizedSource, int i, TransportPriority transportPriority) {
        Preconditions.notNull(str, "key");
        Preconditions.notNull(accessoryTransport, "transport");
        Preconditions.notNull(sizedSource, "data");
        Preconditions.notNull(transportPriority, "priority");
        this.key = str;
        this.transport = accessoryTransport;
        this.data = sizedSource;
        this.stream = i;
        this.priority = transportPriority;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public void execute() throws IOException {
        Logger.d(String.format(Locale.US, "Writing a packet v1 (%d bytes) stream %d %s", Integer.valueOf(this.data.size()), Integer.valueOf(this.stream), this.transport.getAccessory()));
        DataSink dataSink = new DataSink(this.transport.sink());
        Logger.v("Writing a v1 transport packet {");
        dataSink.writeBits(1, 4);
        Logger.v(" - version=%d", 1);
        dataSink.writeBits(this.stream, 5);
        Logger.v(" - stream=%d", Integer.valueOf(this.stream));
        dataSink.writeBits(0, 6);
        Logger.v(" - flags=%d", 0);
        if (this.data.size() <= 65535) {
            boolean z = this.data.size() > 255;
            int i = z ? 1 : 0;
            int i2 = z ? 1 : 0;
            dataSink.writeBit(i);
            Logger.v(" - extendedLength=%b", Boolean.valueOf(z));
            dataSink.writeBits(this.data.size(), z ? 16 : 8);
            Logger.v(" - length=%d", Integer.valueOf(this.data.size()));
            if (Logger.shouldLog(Logger.Level.VERBOSE)) {
                BufferPool shared = BufferPool.shared();
                Buffer request = shared.request(this.data.size());
                IOUtils.transfer(this.data, request);
                Logger.v(" - payload:", request.data(), 0, request.size());
                Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                IOUtils.transfer(request, dataSink);
                shared.recycle(request);
            } else {
                dataSink.write(this.data);
            }
            this.data.reset();
            IOUtils.closeQuietly(this.data);
            return;
        }
        throw new IOException(String.format(Locale.US, "Exceeded maximum payload size %d bytes. Maximum is %d bytes", Integer.valueOf(this.data.size()), 65535));
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public String getKey() {
        return this.key;
    }

    @Override // com.amazon.alexa.accessory.transport.operations.TransportOperation
    public TransportPriority getPriority() {
        return this.priority;
    }
}
