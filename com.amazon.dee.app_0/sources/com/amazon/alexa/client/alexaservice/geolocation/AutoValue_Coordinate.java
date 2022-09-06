package com.amazon.alexa.client.alexaservice.geolocation;

import com.amazon.alexa.TKK;
import com.amazon.alexa.hYy;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
public final class AutoValue_Coordinate extends TKK {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<hYy> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Double> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("latitudeInDegrees", "longitudeInDegrees", "accuracyInMeters");
            this.zQM = gson;
            this.BIo = Util.renameFields(TKK.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, hYy hyy) throws IOException {
            if (hyy == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("latitudeInDegrees"));
            TypeAdapter<Double> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Double.class);
                this.zZm = typeAdapter;
            }
            TKK tkk = (TKK) hyy;
            typeAdapter.write(jsonWriter, Double.valueOf(tkk.zZm));
            jsonWriter.name(this.BIo.get("longitudeInDegrees"));
            TypeAdapter<Double> typeAdapter2 = this.zZm;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zQM.getAdapter(Double.class);
                this.zZm = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Double.valueOf(tkk.BIo));
            jsonWriter.name(this.BIo.get("accuracyInMeters"));
            TypeAdapter<Double> typeAdapter3 = this.zZm;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.zQM.getAdapter(Double.class);
                this.zZm = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Double.valueOf(tkk.zQM));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public hYy mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            double d = 0.0d;
            double d2 = 0.0d;
            double d3 = 0.0d;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("latitudeInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Double.class);
                            this.zZm = typeAdapter;
                        }
                        d = typeAdapter.mo8353read(jsonReader).doubleValue();
                    } else if (this.BIo.get("longitudeInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(Double.class);
                            this.zZm = typeAdapter2;
                        }
                        d2 = typeAdapter2.mo8353read(jsonReader).doubleValue();
                    } else if (this.BIo.get("accuracyInMeters").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter3 = this.zZm;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zQM.getAdapter(Double.class);
                            this.zZm = typeAdapter3;
                        }
                        d3 = typeAdapter3.mo8353read(jsonReader).doubleValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Coordinate(d, d2, d3);
        }
    }

    public AutoValue_Coordinate(double d, double d2, double d3) {
        super(d, d2, d3);
    }
}
