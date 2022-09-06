package com.amazon.alexa.accessory.internal.bluetooth.spp;

import android.bluetooth.BluetoothSocket;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.UUID;
/* loaded from: classes.dex */
public interface SppSocketProducer {
    BluetoothSocket createAndConnectSocket(String str, UUID uuid) throws IOException;

    @Nullable
    BluetoothSocket getAndRemoveCachedConnectedSocket(String str);
}
