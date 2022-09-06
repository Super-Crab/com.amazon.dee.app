package com.amazon.alexa.accessory.transport.gatt;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.io.Buffer;
import com.amazon.alexa.accessory.io.Sink;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes6.dex */
final class GattSink implements Sink {
    private final BluetoothGatt bluetoothGatt;
    private final Buffer buffer;
    private Throwable cause;
    private final BluetoothGattCharacteristic characteristic;
    private volatile boolean closed;
    private final Object monitor = new Object();
    private final long timeoutMillis;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GattSink(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, long j) {
        this.bluetoothGatt = bluetoothGatt;
        this.characteristic = bluetoothGattCharacteristic;
        this.timeoutMillis = j;
        this.buffer = new Buffer(i);
    }

    private void writeCharacteristic() throws IOException {
        if (this.buffer.size() == 0) {
            return;
        }
        Logger.d("Writing characteristic", this.buffer.data(), 0, this.buffer.size());
        this.characteristic.setValue(Arrays.copyOf(this.buffer.data(), this.buffer.size()));
        if (this.bluetoothGatt.writeCharacteristic(this.characteristic)) {
            synchronized (this.monitor) {
                IOUtils.waitUntilNotified(this.monitor, this.timeoutMillis);
                if (this.cause != null) {
                    throw new IOException(this.cause);
                }
            }
            this.buffer.close();
            return;
        }
        throw new IOException("Failed to write Alexa accessory characteristic");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        synchronized (this.monitor) {
            this.monitor.notifyAll();
        }
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void flush() throws IOException {
        if (!this.closed) {
            writeCharacteristic();
            return;
        }
        throw new IOException("Gatt sink is closed");
    }

    public void notifyCharacteristicWriteCompleted() {
        synchronized (this.monitor) {
            this.monitor.notifyAll();
        }
    }

    public void notifyCharacteristicWriteFailed(Throwable th) {
        synchronized (this.monitor) {
            this.cause = th;
            this.monitor.notifyAll();
        }
    }

    @Override // com.amazon.alexa.accessory.io.Sink
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (!this.closed) {
            int i3 = i2;
            while (i3 > 0 && !this.closed) {
                if (this.buffer.capacity() == this.buffer.size()) {
                    writeCharacteristic();
                }
                int min = Math.min(this.buffer.capacity() - this.buffer.size(), i3);
                this.buffer.write(bArr, (i + i2) - i3, min);
                i3 -= min;
            }
            return;
        }
        throw new IOException("Gatt sink is closed");
    }
}
