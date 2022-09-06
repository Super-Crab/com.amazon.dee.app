package com.amazon.alexa.accessory.capabilities.bulkdata.session;

import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataRequestMetadata;
import com.amazon.alexa.accessory.capabilities.bulkdata.identifiers.DataIdentifier;
import com.amazon.alexa.accessory.capabilities.bulkdata.identifiers.SessionIdentifier;
import com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Pipe;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Bulkdata;
import com.amazon.alexa.accessory.streams.bulkdata.BulkDataStream;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.google.protobuf.ByteString;
import java.io.IOException;
/* loaded from: classes.dex */
public class DefaultBulkDataSession implements BulkDataSession {
    private static final int BUFFER_SIZE = 20480;
    private static final int CLIENT_TIMEOUT_MILLIS = 10000;
    private final AccessoryDescriptor accessoryDescriptor;
    private final BulkDataRequestMetadata bulkDataRequestMetadata;
    private final BulkDataStream bulkDataStream;
    private final ControlStream controlStream;
    private final DataIdentifier identifier;
    private final Pipe pipe;
    private BulkDataSession.State state;

    public DefaultBulkDataSession(DataIdentifier dataIdentifier, BulkDataRequestMetadata bulkDataRequestMetadata, ControlStream controlStream, AccessoryDescriptor accessoryDescriptor) {
        Preconditions.notNull(dataIdentifier, "identifier");
        Preconditions.notNull(bulkDataRequestMetadata, "bulkDataRequestMetadata");
        Preconditions.notNull(controlStream, "controlStream");
        Preconditions.notNull(accessoryDescriptor, "accessoryDescriptor");
        this.identifier = dataIdentifier;
        this.bulkDataRequestMetadata = bulkDataRequestMetadata;
        this.controlStream = controlStream;
        this.accessoryDescriptor = accessoryDescriptor;
        this.state = BulkDataSession.State.CREATED;
        this.pipe = new Pipe(20480, 10000L);
        this.bulkDataStream = new BulkDataStream(this.pipe, new BulkDataStream.Callback() { // from class: com.amazon.alexa.accessory.capabilities.bulkdata.session.-$$Lambda$DefaultBulkDataSession$IJwiKWYXcuw62X8CUvY5FjayKAs
            @Override // com.amazon.alexa.accessory.streams.bulkdata.BulkDataStream.Callback
            public final void onError(Throwable th) {
                DefaultBulkDataSession.this.lambda$new$0$DefaultBulkDataSession(th);
            }
        });
        accessoryDescriptor.add(this.bulkDataStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: abortSession */
    public void lambda$new$0$DefaultBulkDataSession(BulkDataSession bulkDataSession) {
        BulkDataSession.State state = this.state;
        if (state != BulkDataSession.State.ABORTED && state != BulkDataSession.State.COMPLETED) {
            Logger.e("Something has gone wrong when reading data from session with identifier %s, aborting.", this.identifier);
            closeSession(bulkDataSession);
            if (this.state == BulkDataSession.State.STARTED) {
                this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.STOP_BULK_DATA_TRANSFER).setStopBulkDataTransfer(Bulkdata.StopBulkDataTransfer.newBuilder().mo10084build()).mo10084build()));
            }
            this.accessoryDescriptor.remove(this.bulkDataStream);
            this.state = BulkDataSession.State.ABORTED;
            return;
        }
        Logger.w("Attempted to call abort() while the session is already %s, ignoring request", this.state.toString());
    }

    private void closeSession(BulkDataSession bulkDataSession) {
        Logger.v("Closing the session with identifier %s.", this.identifier);
        try {
            bulkDataSession.getSource().close();
        } catch (IOException e) {
            Logger.e("Encountered a problem when closing the data read source (i.e. pipe) for %s.", e, bulkDataSession.getDataIdentifier());
        }
    }

    private void completeSession(BulkDataSession bulkDataSession, int i) {
        BulkDataSession.State state = this.state;
        if (state != BulkDataSession.State.ABORTED && state != BulkDataSession.State.COMPLETED) {
            Logger.v("We are completing the session with identifier %s", this.identifier);
            closeSession(bulkDataSession);
            if (this.state == BulkDataSession.State.STARTED) {
                this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.BULK_DATA_TRANSFER_COMPLETE).setBulkDataTransferComplete(Bulkdata.BulkDataTransferComplete.newBuilder().setIdentifier(ByteString.copyFrom(this.identifier.getBytes())).setCategory(i).mo10084build()).mo10084build()));
            }
            this.accessoryDescriptor.remove(this.bulkDataStream);
            this.state = BulkDataSession.State.COMPLETED;
            return;
        }
        Logger.w("Attempted to call complete() while the session is already %s, ignoring request", this.state.toString());
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public synchronized void abort() {
        lambda$new$0$DefaultBulkDataSession(this);
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public synchronized void complete() {
        completeSession(this, this.bulkDataRequestMetadata.getCategory());
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public DataIdentifier getDataIdentifier() {
        return this.identifier;
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public SessionIdentifier getSessionIdentifier() {
        return new SessionIdentifier(this.identifier.getBytes());
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public Source getSource() {
        return this.pipe;
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public synchronized BulkDataSession.State getState() {
        return this.state;
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public synchronized boolean isAborted() {
        return this.state == BulkDataSession.State.ABORTED;
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public synchronized boolean isComplete() {
        return this.state == BulkDataSession.State.COMPLETED;
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.session.BulkDataSession
    public synchronized void start(BulkDataSession.SessionStartCallback sessionStartCallback, int i) {
        if (this.state == BulkDataSession.State.CREATED) {
            sessionStartCallback.onSessionStart(this, i);
            this.state = BulkDataSession.State.STARTED;
        }
    }
}
