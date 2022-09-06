package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.CiJ;
import com.amazon.alexa.SlJ;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_ProgressReport extends SlJ {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<CiJ> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Long> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "progressReportDelayInMilliseconds", (Object) "progressReportIntervalInMilliseconds");
            this.zQM = gson;
            this.BIo = Util.renameFields(SlJ.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, CiJ ciJ) throws IOException {
            if (ciJ == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("progressReportDelayInMilliseconds"));
            TypeAdapter<Long> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Long.class);
                this.zZm = typeAdapter;
            }
            SlJ slJ = (SlJ) ciJ;
            typeAdapter.write(jsonWriter, Long.valueOf(slJ.zZm));
            jsonWriter.name(this.BIo.get("progressReportIntervalInMilliseconds"));
            TypeAdapter<Long> typeAdapter2 = this.zZm;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zQM.getAdapter(Long.class);
                this.zZm = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(slJ.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public CiJ mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            long j2 = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("progressReportDelayInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Long.class);
                            this.zZm = typeAdapter;
                        }
                        j = typeAdapter.mo8353read(jsonReader).longValue();
                    } else if (this.BIo.get("progressReportIntervalInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(Long.class);
                            this.zZm = typeAdapter2;
                        }
                        j2 = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ProgressReport(j, j2);
        }
    }

    public AutoValue_ProgressReport(long j, long j2) {
        super(j, j2);
    }
}
