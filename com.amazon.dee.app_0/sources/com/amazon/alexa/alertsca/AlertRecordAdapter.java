package com.amazon.alexa.alertsca;

import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes6.dex */
public class AlertRecordAdapter extends TypeAdapter<AlertRecord> {
    private static final String CRAZY_ALERT_ISO_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String SCHEDULED_TIME_NAME = "scheduledTime";
    private static final String TOKEN_NAME = "token";
    private static final String TYPE_NAME = "type";

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public AlertRecord mo8353read(JsonReader jsonReader) throws IOException {
        AlertRecord.Builder builder = AlertRecord.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != 3575610) {
                if (hashCode != 110541305) {
                    if (hashCode == 1681484058 && nextName.equals("scheduledTime")) {
                        c = 2;
                    }
                } else if (nextName.equals("token")) {
                    c = 0;
                }
            } else if (nextName.equals("type")) {
                c = 1;
            }
            if (c == 0) {
                builder.setToken(AlertsToken.create(jsonReader.nextString()));
            } else if (c == 1) {
                builder.setType(AlertType.valueOf(jsonReader.nextString()));
            } else if (c != 2) {
                jsonReader.skipValue();
            } else {
                try {
                    builder.setScheduledTime(new SimpleDateFormat(CRAZY_ALERT_ISO_TIME_FORMAT, Locale.US).parse(jsonReader.nextString()));
                } catch (ParseException e) {
                    throw new IOException(e);
                }
            }
        }
        jsonReader.endObject();
        return builder.build();
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, AlertRecord alertRecord) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("token").value(alertRecord.getToken().getValue());
        jsonWriter.name("type").value(alertRecord.getType().toString());
        jsonWriter.name("scheduledTime").value(new SimpleDateFormat(CRAZY_ALERT_ISO_TIME_FORMAT, Locale.US).format(alertRecord.getScheduledTime()));
        jsonWriter.endObject();
    }
}
