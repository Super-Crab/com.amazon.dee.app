package com.amazon.alexa.client.alexaservice.capabilities.v2;

import android.content.ComponentName;
import com.amazon.alexa.AbstractC0232taD;
import com.amazon.alexa.Iab;
import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.iaZ;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
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
public final class AutoValue_ExternalCapabilityAgentRegistrationData extends AbstractC0232taD {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Iab> {
        public volatile TypeAdapter<ComponentName> BIo;
        public final Gson jiA;
        public volatile TypeAdapter<iaZ> zQM;
        public volatile TypeAdapter<PackageName> zZm;
        public final Map<String, String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "componentName", "externalCapabilityAgentRegistrationRawData");
            this.jiA = gson;
            this.zyO = Util.renameFields(AbstractC0232taD.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Iab iab) throws IOException {
            if (iab == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zyO.get(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME));
            AbstractC0232taD abstractC0232taD = (AbstractC0232taD) iab;
            if (abstractC0232taD.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<PackageName> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.jiA.getAdapter(PackageName.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, abstractC0232taD.zZm);
            }
            jsonWriter.name(this.zyO.get("componentName"));
            if (abstractC0232taD.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ComponentName> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.jiA.getAdapter(ComponentName.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, abstractC0232taD.BIo);
            }
            jsonWriter.name(this.zyO.get("externalCapabilityAgentRegistrationRawData"));
            if (abstractC0232taD.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<iaZ> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.jiA.getAdapter(iaZ.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, abstractC0232taD.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Iab mo8353read(JsonReader jsonReader) throws IOException {
            PackageName packageName = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            ComponentName componentName = null;
            iaZ iaz = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zyO.get(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME).equals(nextName)) {
                        TypeAdapter<PackageName> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.jiA.getAdapter(PackageName.class);
                            this.zZm = typeAdapter;
                        }
                        packageName = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zyO.get("componentName").equals(nextName)) {
                        TypeAdapter<ComponentName> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.jiA.getAdapter(ComponentName.class);
                            this.BIo = typeAdapter2;
                        }
                        componentName = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zyO.get("externalCapabilityAgentRegistrationRawData").equals(nextName)) {
                        TypeAdapter<iaZ> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.jiA.getAdapter(iaZ.class);
                            this.zQM = typeAdapter3;
                        }
                        iaz = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ExternalCapabilityAgentRegistrationData(packageName, componentName, iaz);
        }
    }

    public AutoValue_ExternalCapabilityAgentRegistrationData(PackageName packageName, ComponentName componentName, iaZ iaz) {
        super(packageName, componentName, iaz);
    }
}
