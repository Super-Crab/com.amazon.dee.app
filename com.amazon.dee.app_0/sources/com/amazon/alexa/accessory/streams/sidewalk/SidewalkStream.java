package com.amazon.alexa.accessory.streams.sidewalk;

import com.amazon.alexa.accessory.AccessoryStream;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProvider;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.accessory.transport.TransportTransaction;
import java.io.IOException;
/* loaded from: classes6.dex */
public class SidewalkStream implements AccessoryStream {
    private static final int DISPATCHER_PACKET_SIZE = 255;
    private static final String TAG = "SidewalkStream:";
    private boolean disposed = false;
    private SidewalkProvider sidewalkProvider;
    private TransportDispatcher transportDispatcher;

    public SidewalkStream(SidewalkProvider sidewalkProvider, TransportDispatcher transportDispatcher) {
        Preconditions.notNull(sidewalkProvider, "sidewalkProvider");
        Preconditions.notNull(transportDispatcher, "transportDispatcher");
        this.sidewalkProvider = sidewalkProvider;
        this.transportDispatcher = transportDispatcher;
    }

    public void dispatchData(SizedSource sizedSource) {
        try {
            int size = sizedSource.size();
            Logger.d("%s Received %d bytes data from client to be sent to accessory", TAG, Integer.valueOf(size));
            int i = 0;
            while (i < size) {
                int min = Math.min(size - i, 255);
                byte[] bArr = new byte[min];
                i += sizedSource.read(bArr, 0, min);
                dispatchData(bArr, i == size);
            }
            Logger.d("%s Dispatched %d bytes data from client to accessory successfully", TAG, Integer.valueOf(i));
        } catch (IOException e) {
            Logger.e("%s Error in dispatching data to transportDispatcher", TAG, e);
        }
    }

    public void dispose() {
        if (this.disposed) {
            Logger.d("%s stream already disposed", TAG);
            return;
        }
        Logger.d("%s Disposing stream...", TAG);
        this.disposed = true;
        this.sidewalkProvider = null;
        this.transportDispatcher = null;
    }

    @Override // com.amazon.alexa.accessory.AccessoryStream
    public int getId() {
        return 7;
    }

    @Override // com.amazon.alexa.accessory.AccessoryStream
    public boolean handleData(SizedSource sizedSource) {
        if (sizedSource == null) {
            Logger.d("%s Received null data from accessory", TAG);
            return false;
        } else if (this.disposed || this.sidewalkProvider == null) {
            Logger.e("%s Unable to handle data. disposed: %b and sidewalkProvider: %s", TAG, Boolean.valueOf(this.disposed), this.sidewalkProvider);
            return false;
        } else {
            Logger.d("%s providing data from accessory to repository", TAG);
            this.sidewalkProvider.provideData(sizedSource);
            return true;
        }
    }

    private void dispatchData(byte[] bArr, boolean z) {
        this.transportDispatcher.dispatch(TransportTransaction.newBuilder().stream(7).data(new ByteArraySource(bArr)).commit(z).build());
    }
}
