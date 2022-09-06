package com.amazon.comms.ringservice.webrtc;

import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.calling.service.DataChannelEvent;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.PeerConnectionClient;
import com.amazon.comms.util.LooperExecutor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.webrtc.DataChannel;
/* loaded from: classes12.dex */
public class DataChannelObserver implements DataChannel.Observer {
    private static final CommsLogger log = CommsLogger.getLogger(DataChannelObserver.class);
    private final DataChannel dataChannel;
    private final PeerConnectionClient.PeerConnectionEvents events;
    private final LooperExecutor executor;

    public DataChannelObserver(DataChannel dataChannel, PeerConnectionClient.PeerConnectionEvents peerConnectionEvents, LooperExecutor looperExecutor) {
        this.dataChannel = dataChannel;
        this.events = peerConnectionEvents;
        this.executor = looperExecutor;
    }

    @Override // org.webrtc.DataChannel.Observer
    public void onBufferedAmountChange(long j) {
        final DataChannelEvent build = DataChannelEvent.builder().param("label", this.dataChannel.label()).param(DataChannelEvent.BUFFERED_AMOUNT, Long.valueOf(j)).eventType(DataChannelEvent.Type.BufferedAmountChange).build();
        this.executor.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.DataChannelObserver.1
            @Override // java.lang.Runnable
            public void run() {
                DataChannelObserver.this.events.onDataChannelEvent(build);
            }
        });
    }

    @Override // org.webrtc.DataChannel.Observer
    public void onMessage(DataChannel.Buffer buffer) {
        byte[] bArr = new byte[buffer.data.remaining()];
        buffer.data.slice().get(bArr);
        final DataChannelDTO dataChannelDTO = new DataChannelDTO(this.dataChannel.label(), bArr, buffer.binary);
        this.executor.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.DataChannelObserver.3
            @Override // java.lang.Runnable
            public void run() {
                CommsLogger commsLogger = DataChannelObserver.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received data channel message from label: ");
                outline107.append(DataChannelObserver.this.dataChannel.label());
                commsLogger.i(outline107.toString());
                DataChannelObserver.this.events.onDataChannelEvent(DataChannelEvent.builder().param("label", DataChannelObserver.this.dataChannel.label()).param("data", dataChannelDTO).eventType(DataChannelEvent.Type.ReceivedMessage).build());
            }
        });
    }

    @Override // org.webrtc.DataChannel.Observer
    public void onStateChange() {
        final DataChannelEvent build = DataChannelEvent.builder().param("label", this.dataChannel.label()).param("state", this.dataChannel.state().name()).eventType(DataChannelEvent.Type.StateChanged).build();
        this.executor.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.DataChannelObserver.2
            @Override // java.lang.Runnable
            public void run() {
                DataChannelObserver.this.events.onDataChannelEvent(build);
            }
        });
    }
}
