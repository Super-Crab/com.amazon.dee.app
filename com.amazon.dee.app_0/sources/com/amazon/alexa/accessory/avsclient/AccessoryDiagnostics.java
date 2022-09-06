package com.amazon.alexa.accessory.avsclient;

import com.amazon.alexa.accessory.avsclient.AccessoryDiagnostics;
import com.amazon.alexa.accessory.internal.http.HttpBody;
import com.amazon.alexa.accessory.internal.http.HttpMethod;
import com.amazon.alexa.accessory.internal.http.HttpRequest;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.io.OutputStreamSink;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class AccessoryDiagnostics {
    private static final String APP_LOGS_KEY = "app-logs.txt";

    /* loaded from: classes.dex */
    public static final class AccessoryDevice {
        final Device.DeviceInformation deviceInformation;
        final Firmware.FirmwareInformation firmwareInformation;

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryDevice{deviceInformation=");
            outline107.append(this.deviceInformation);
            outline107.append(", firmwareInformation=");
            outline107.append(this.firmwareInformation);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        private AccessoryDevice(Device.DeviceInformation deviceInformation, Firmware.FirmwareInformation firmwareInformation) {
            this.deviceInformation = deviceInformation;
            this.firmwareInformation = firmwareInformation;
        }
    }

    private AccessoryDiagnostics() {
        throw new IllegalStateException("No instances!");
    }

    public static Single<AccessoryDevice> getAccessoryDeviceWithHighestDeviceId(final AccessorySession accessorySession) {
        if (accessorySession == null) {
            return Single.error(new IOException("Session was null"));
        }
        return accessorySession.getDeviceRepository().queryDeviceInformationSet().firstOrError().map($$Lambda$AccessoryDiagnostics$jw9A2kVkZFVWwMYoXj3GWB6cUFs.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$WXUoySdl6uCR9k2Ghcb6TnJRpQU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource map;
                map = AccessorySession.this.getFirmwareRepository().queryInformationSet().map($$Lambda$AccessoryDiagnostics$wlgl6GG1Pd_XwQCF9NY1jSiVO1c.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$U4gMco24fh_n2dWFP_QCxFNwDh4
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        return AccessoryDiagnostics.lambda$null$10(Device.DeviceInformation.this, (Firmware.FirmwareInformation) obj2);
                    }
                });
                return map;
            }
        });
    }

    public static Single<AccessoryDevice> getDevice(final com.amazon.alexa.accessory.AccessorySession accessorySession) {
        if (accessorySession == null) {
            return Single.error(new IOException("Session was null"));
        }
        return accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$AccessoryDiagnostics$Y8Kkq1jm_fzqF51556GxZP6_zRo.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$TShVTyhgRgyIxyPGnsjYuAEWz58
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource map;
                map = com.amazon.alexa.accessory.AccessorySession.this.getFirmwareRepositoryV2().queryInformationSet().map($$Lambda$AccessoryDiagnostics$dwkOzzGClupE2fdpoGFU5gckc.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$_OX95s1tUWeoS7mKm-LsaABjrXc
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        return AccessoryDiagnostics.lambda$null$4(Device.DeviceInformation.this, (Firmware.FirmwareInformation) obj2);
                    }
                });
                return map;
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Device.DeviceInformation lambda$getAccessoryDeviceWithHighestDeviceId$7(Set set) throws Throwable {
        return (Device.DeviceInformation) Collections.max(set, $$Lambda$AccessoryDiagnostics$mFJ8QqCIfc6tB0U0mjOxXjn3HeE.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Device.DeviceInformation lambda$getDevice$1(Set set) throws Throwable {
        return (Device.DeviceInformation) Collections.max(set, $$Lambda$AccessoryDiagnostics$EhsUoZ8vHbTAUc9OCxcHPW7A7_I.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$0(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AccessoryDevice lambda$null$10(Device.DeviceInformation deviceInformation, Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        return new AccessoryDevice(deviceInformation, firmwareInformation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SingleSource lambda$null$16(Source source) throws Throwable {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            IOUtils.transfer(source, new OutputStreamSink(byteArrayOutputStream));
            Single just = Single.just(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
            return just;
        } finally {
            IOUtils.closeQuietly(source);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$2(Firmware.FirmwareInformation firmwareInformation, Firmware.FirmwareInformation firmwareInformation2) {
        return firmwareInformation.getDeviceId() - firmwareInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Firmware.FirmwareInformation lambda$null$3(Set set) throws Throwable {
        return (Firmware.FirmwareInformation) Collections.max(set, $$Lambda$AccessoryDiagnostics$PjjGlB8bdJbgqIKQZK_f2KYJxc.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AccessoryDevice lambda$null$4(Device.DeviceInformation deviceInformation, Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        return new AccessoryDevice(deviceInformation, firmwareInformation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$6(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$8(Firmware.FirmwareInformation firmwareInformation, Firmware.FirmwareInformation firmwareInformation2) {
        return firmwareInformation.getDeviceId() - firmwareInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Firmware.FirmwareInformation lambda$null$9(Set set) throws Throwable {
        return (Firmware.FirmwareInformation) Collections.max(set, $$Lambda$AccessoryDiagnostics$IAsEulSfHO5dYBE0Hym_DFhzXo.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CompletableSource lambda$uploadApplicationLogs$13(final AccessoryDevice accessoryDevice) throws Throwable {
        if (accessoryDevice == null) {
            return Completable.error(new IllegalStateException("device was null"));
        }
        Logger.Processor processor = Logger.getProcessor();
        if (processor == null) {
            return Completable.error(new IllegalStateException("Processor was null"));
        }
        return uploadLogs(APP_LOGS_KEY, processor.toString().getBytes(), accessoryDevice).doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$Ody8_ICMqEPoXIuk5JhGWej9EWE
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Logger.d("Successfully uploaded application logs for device=%s", AccessoryDiagnostics.AccessoryDevice.this);
            }
        });
    }

    public static Completable uploadAccessoryClientDiagnostics(AccessorySession accessorySession, final AccessoryDevice accessoryDevice, DiagnosticsOuterClass.DiagnosticsType diagnosticsType) {
        if (accessorySession == null) {
            return Completable.error(new IllegalStateException("session was null"));
        }
        if (accessoryDevice == null) {
            return Completable.error(new IllegalStateException("device was null"));
        }
        if (diagnosticsType == null) {
            return Completable.error(new IllegalStateException("type was null"));
        }
        return accessorySession.getDiagnosticsRepository().queryDiagnostics(diagnosticsType).flatMapCompletable(uploadLogSource(diagnosticsType.toString(), accessoryDevice)).doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$I07JYsQ5z22PUvRvMgOUxk1YCFM
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Logger.d("AccessoryClient: Successfully uploaded accessory diagnostics for device=%s", AccessoryDiagnostics.AccessoryDevice.this);
            }
        });
    }

    public static Completable uploadAccessoryDiagnostics(com.amazon.alexa.accessory.AccessorySession accessorySession, final AccessoryDevice accessoryDevice, DiagnosticsOuterClass.DiagnosticsType diagnosticsType) {
        if (accessorySession == null) {
            return Completable.error(new IllegalStateException("session was null"));
        }
        if (accessoryDevice == null) {
            return Completable.error(new IllegalStateException("device was null"));
        }
        if (diagnosticsType == null) {
            return Completable.error(new IllegalStateException("type was null"));
        }
        return accessorySession.getDiagnosticsRepository().queryDiagnostics(diagnosticsType).subscribeOn(AndroidSchedulers.mainThread()).flatMapCompletable(uploadLogSource(diagnosticsType.toString(), accessoryDevice)).doOnComplete(new Action() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$8ydQa-egL_6y1NQZO-fOXbRPNx8
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Logger.d("Successfully uploaded accessory diagnostics for device=%s", AccessoryDiagnostics.AccessoryDevice.this);
            }
        });
    }

    public static Completable uploadApplicationLogs(final AccessoryDevice accessoryDevice) {
        return Completable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$HLIQ47ai2wYElSclC6pKpB6Znt0
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AccessoryDiagnostics.lambda$uploadApplicationLogs$13(AccessoryDiagnostics.AccessoryDevice.this);
            }
        });
    }

    private static Function<Source, Completable> uploadLogSource(final String str, final AccessoryDevice accessoryDevice) {
        return new Function() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$JymHC_u9wcKaRWdQBILvwaIXwSk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Completable subscribeOn;
                subscribeOn = Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$bWa5cXx8dg6OhHhkTmAlH8kgBXc
                    @Override // io.reactivex.rxjava3.functions.Supplier
                    /* renamed from: get */
                    public final Object mo10365get() {
                        return AccessoryDiagnostics.lambda$null$16(Source.this);
                    }
                }).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.avsclient.-$$Lambda$AccessoryDiagnostics$tcuP6K8wOCczT7QiE6ABLFxaIFU
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        CompletableSource uploadLogs;
                        uploadLogs = AccessoryDiagnostics.uploadLogs(r1, (byte[]) obj2, r2);
                        return uploadLogs;
                    }
                }).subscribeOn(Schedulers.io());
                return subscribeOn;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Completable uploadLogs(String str, byte[] bArr, AccessoryDevice accessoryDevice) throws IOException {
        if (bArr != null && bArr.length != 0) {
            if (str != null && str.length() != 0) {
                Logger.d("Sending logs to Spectator. fileName=%s, device=%s", str, accessoryDevice);
                byte[] bytes = ("Files: " + str + ".0\nMFBS/1.0 1\nContent-Name: " + str + "\nContent-Encoding: text\nContent-Length: " + bArr.length + "\n\n").getBytes();
                final byte[] bArr2 = new byte[bytes.length + bArr.length];
                System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
                System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
                HttpRequest.Builder createBuilder = HttpRequest.createBuilder();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("https://det-ta-g7g.amazon.com/DeviceEventProxy/DETLogServlet?key=");
                outline107.append(System.currentTimeMillis());
                outline107.append(".");
                outline107.append(str);
                return createBuilder.url(outline107.toString()).method(HttpMethod.POST).header("X-DSN", accessoryDevice.deviceInformation.getSerialNumber()).header(SipHeaders.SIP_HEADER_DEVICE_TYPE, accessoryDevice.deviceInformation.getDeviceType()).header("X-DeviceFirmwareVersion", String.valueOf(accessoryDevice.firmwareInformation.getVersion())).header("X-Content-Type", "BinaryFile").body(new HttpBody() { // from class: com.amazon.alexa.accessory.avsclient.AccessoryDiagnostics.1
                    @Override // com.amazon.alexa.accessory.internal.http.HttpBody
                    public String getContentType() {
                        return "application/octet-stream";
                    }

                    @Override // com.amazon.alexa.accessory.internal.http.HttpBody
                    public void writeTo(Sink sink) throws IOException {
                        IOUtils.transfer(new ByteArraySource(bArr2), sink);
                    }
                }).build().newCall().executeCompletable();
            }
            return Completable.error(new IOException("name is empty"));
        }
        return Completable.error(new IOException("content is zero bytes"));
    }
}
