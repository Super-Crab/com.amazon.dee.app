package com.amazon.deecomms.nativemodules;

import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.photos.FileTransmitter;
import dagger.Lazy;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.nativemodules.-$$Lambda$CommsMessagingMediaBridge$c4A4eJX128rDRRupMK_3nysxyk0  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsMessagingMediaBridge$c4A4eJX128rDRRupMK_3nysxyk0 implements Lazy {
    public static final /* synthetic */ $$Lambda$CommsMessagingMediaBridge$c4A4eJX128rDRRupMK_3nysxyk0 INSTANCE = new $$Lambda$CommsMessagingMediaBridge$c4A4eJX128rDRRupMK_3nysxyk0();

    private /* synthetic */ $$Lambda$CommsMessagingMediaBridge$c4A4eJX128rDRRupMK_3nysxyk0() {
    }

    @Override // dagger.Lazy
    /* renamed from: get */
    public final Object mo358get() {
        FileTransmitter fileTransmitter;
        fileTransmitter = CommsDaggerWrapper.getComponent().getFileTransmitter();
        return fileTransmitter;
    }
}
