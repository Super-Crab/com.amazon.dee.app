package com.amazon.alexa.accessory.transport.rfcomm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder;
import com.amazon.alexa.accessory.internal.bluetooth.spp.SppSocketProducer;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.InputStreamSource;
import com.amazon.alexa.accessory.io.OutputStreamSink;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessorykit.ModelTransformer;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
/* loaded from: classes6.dex */
public final class RfcommTransport implements AccessoryTransport {
    private static final int BOND = 1;
    private static final int CLOSE = 2;
    private static final int CONNECT = 3;
    private static final int CONNECTION_MAX_RETRIES = 4;
    private static final long CONNECT_TIMEOUT_MILLIS = 60000;
    private static final int JITTER = 500;
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_IDLE = 0;
    private static final int UNUSED = -1;
    private final Accessory accessory;
    private final AccessoryMetricsService accessoryMetricsService;
    private final BluetoothManager bluetoothManager;
    private final BluetoothDeviceBonder bonder;
    private volatile IOException cause;
    private Handler handler;
    private final Object monitor;
    private final Random random;
    private final UUID rfcommChannelId;
    private Sink sink;
    private BluetoothSocket socket;
    private Source source;
    private final SppSocketProducer sppSocketProducer;
    private volatile int state;
    private HandlerThread thread;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public final class HandlerCallback implements Handler.Callback {
        HandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                RfcommTransport.this.internalClose();
                return true;
            } else if (i != 3) {
                return false;
            } else {
                RfcommTransport.this.connect(message.arg1);
                return true;
            }
        }
    }

    public RfcommTransport(Context context, Accessory accessory, BluetoothDeviceBonder bluetoothDeviceBonder, UUID uuid, SppSocketProducer sppSocketProducer) {
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(bluetoothDeviceBonder, "deviceBonder");
        Preconditions.notNull(uuid, "rfcommChannelId");
        Preconditions.notNull(sppSocketProducer, "sppSocketProducer");
        Preconditions.precondition(accessory.getTransport() == AccessoryTransport.Type.RFCOMM, "Accessory must support RFCOMM transport");
        this.accessory = accessory;
        this.rfcommChannelId = uuid;
        this.sppSocketProducer = sppSocketProducer;
        this.monitor = new Object();
        this.state = 0;
        this.bonder = bluetoothDeviceBonder;
        this.bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.random = new Random();
        this.accessoryMetricsService = AccessoryMetricsServiceHolder.getInstance().get();
    }

    private void awaitConnection() throws IOException {
        if (this.state == 2) {
            return;
        }
        synchronized (this.monitor) {
            Logger.d("RFComm state : " + this.state);
            if (this.state == 2) {
                return;
            }
            if (this.state == 0) {
                if (this.cause == null) {
                    this.state = 1;
                    this.thread = new HandlerThread(getClass().getSimpleName());
                    this.thread.start();
                    this.handler = new Handler(this.thread.getLooper(), new HandlerCallback());
                    this.handler.sendMessage(obtainConnectionAttemptMessage(0));
                } else {
                    throw this.cause;
                }
            }
            if (this.state == 1) {
                IOUtils.waitUntilNotified(this.monitor, 60000L);
            }
            if (this.cause != null) {
                throw this.cause;
            }
        }
    }

    private void closeResources() {
        Logger.d("Closing resources");
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter != null && adapter.isEnabled()) {
            this.bonder.cancel(adapter.getRemoteDevice(this.accessory.getAddress()));
        }
        IOUtils.closeQuietly(this.source);
        IOUtils.closeQuietly(this.sink);
        BluetoothSocket bluetoothSocket = this.socket;
        if (bluetoothSocket != null) {
            IOUtils.closeQuietly(bluetoothSocket);
            this.socket = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalClose() {
        Logger.d("Internal close resources");
        closeResources();
        synchronized (this.monitor) {
            this.state = 0;
        }
    }

    private void notifyConnectFailed(IOException iOException) {
        synchronized (this.monitor) {
            this.cause = iOException;
            this.state = 0;
            this.monitor.notifyAll();
        }
    }

    private Message obtainConnectionAttemptMessage(int i) {
        return this.handler.obtainMessage(3, i, -1);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.state == 0) {
            return;
        }
        this.handler.sendEmptyMessage(2);
        this.thread.quitSafely();
    }

    @VisibleForTesting
    void connect(int i) {
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        Logger.d("Attempting connection #" + i);
        if (adapter != null && adapter.isEnabled()) {
            String address = this.accessory.getAddress();
            try {
                Logger.d("Retrieving the RFComm socket.");
                this.socket = this.sppSocketProducer.getAndRemoveCachedConnectedSocket(address);
                if (this.socket == null) {
                    this.socket = this.sppSocketProducer.createAndConnectSocket(address, this.rfcommChannelId);
                }
                this.source = new InputStreamSource(this.socket.getInputStream());
                this.sink = new OutputStreamSink(new BufferedOutputStream(this.socket.getOutputStream()));
                synchronized (this.monitor) {
                    this.state = 2;
                    this.monitor.notifyAll();
                }
                return;
            } catch (IOException e) {
                if (i < 4) {
                    long nextInt = (1 << (i + 10)) + this.random.nextInt(500);
                    Logger.d("RfcommTransport.connect: retrying failed socket connection in " + nextInt + "ms. attempt = " + i);
                    int i2 = i + 1;
                    this.handler.sendMessageDelayed(obtainConnectionAttemptMessage(i2), nextInt);
                    this.accessoryMetricsService.recordCounter(MetricsConstants.Session.RETRY_FAILED_RFCOMM_CONNECTION, MetricsConstants.Session.SESSION_CONNECTION, (double) i2, null);
                    return;
                }
                closeResources();
                this.accessoryMetricsService.recordCounter(MetricsConstants.Session.EXHAUSTED_RFCOMM_CONNECTION_ATTEMPT, MetricsConstants.Session.SESSION_CONNECTION, 1.0d, null);
                notifyConnectFailed(e);
                return;
            } catch (SecurityException e2) {
                Logger.e("RfcommTransport.connect failed: ", e2);
                closeResources();
                this.accessoryMetricsService.recordCounter(MetricsConstants.Session.FAILED_RFCOMM_CONNECTION_SECURITY_EXCEPTION, MetricsConstants.Session.SESSION_CONNECTION, 1.0d, null);
                notifyConnectFailed(new IOException(e2));
                return;
            }
        }
        notifyConnectFailed(new IOException("BluetoothAdapter is unavailable. Cannot connect"));
    }

    @Override // com.amazon.alexa.accessory.AccessoryTransport
    public Accessory getAccessory() {
        return this.accessory;
    }

    @VisibleForTesting(otherwise = 5)
    int peekAtState() {
        return this.state;
    }

    @Override // com.amazon.alexa.accessory.AccessoryTransport
    public Sink sink() throws IOException {
        awaitConnection();
        return this.sink;
    }

    @Override // com.amazon.alexa.accessory.AccessoryTransport
    public Source source() throws IOException {
        awaitConnection();
        return this.source;
    }
}
