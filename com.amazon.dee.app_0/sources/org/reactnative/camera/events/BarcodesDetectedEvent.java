package org.reactnative.camera.events;

import android.util.Base64;
import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import kotlin.jvm.internal.ShortCompanionObject;
import org.reactnative.camera.CameraViewManager;
/* loaded from: classes5.dex */
public class BarcodesDetectedEvent extends Event<BarcodesDetectedEvent> {
    private static final Pools.SynchronizedPool<BarcodesDetectedEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private WritableArray mBarcodes;
    private byte[] mCompressedImage;

    private BarcodesDetectedEvent() {
    }

    private void init(int i, WritableArray writableArray, byte[] bArr) {
        super.init(i);
        this.mBarcodes = writableArray;
        this.mCompressedImage = bArr;
    }

    public static BarcodesDetectedEvent obtain(int i, WritableArray writableArray, byte[] bArr) {
        BarcodesDetectedEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new BarcodesDetectedEvent();
        }
        acquire.init(i, writableArray, bArr);
        return acquire;
    }

    private WritableMap serializeEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("type", "barcode");
        createMap.putArray("barcodes", this.mBarcodes);
        createMap.putInt("target", getViewTag());
        byte[] bArr = this.mCompressedImage;
        if (bArr != null) {
            createMap.putString("image", Base64.encodeToString(bArr, 2));
        }
        return createMap;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return this.mBarcodes.size() > 32767 ? ShortCompanionObject.MAX_VALUE : (short) this.mBarcodes.size();
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return CameraViewManager.Events.EVENT_ON_BARCODES_DETECTED.toString();
    }
}
