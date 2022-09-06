package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.amazon.device.messaging.ADMConstants;
import com.bugsnag.android.JsonStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class SessionTrackingPayload implements JsonStream.Streamable {
    private final Map<String, Object> appDataSummary;
    private final Map<String, Object> deviceDataSummary;
    private final List<File> files;
    private final Notifier notifier = Notifier.getInstance();
    private final Session session;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionTrackingPayload(Session session, List<File> list, AppData appData, DeviceData deviceData) {
        this.appDataSummary = appData.getAppDataSummary();
        this.deviceDataSummary = deviceData.getDeviceDataSummary();
        this.session = session;
        this.files = list;
    }

    Map<String, Object> getDevice() {
        return this.deviceDataSummary;
    }

    Session getSession() {
        return this.session;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject();
        jsonStream.mo6745name("notifier").value((JsonStream.Streamable) this.notifier);
        jsonStream.mo6745name(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT).value(this.appDataSummary);
        jsonStream.mo6745name("device").value(this.deviceDataSummary);
        jsonStream.mo6745name("sessions").beginArray();
        Session session = this.session;
        if (session == null) {
            for (File file : this.files) {
                jsonStream.value(file);
            }
        } else {
            jsonStream.value((JsonStream.Streamable) session);
        }
        jsonStream.endArray();
        jsonStream.endObject();
    }
}
