package com.amazon.alexa.client.alexaservice.capabilities.legacy;

import com.amazon.alexa.Cta;
import com.amazon.alexa.zoO;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public class LegacyFlagsAdapter extends TypeAdapter<Cta> {
    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, Cta cta) throws IOException {
        if (cta == null) {
            jsonWriter.nullValue();
            return;
        }
        zoO zoo = (zoO) cta;
        jsonWriter.beginObject().name("SUPPORTS_TTS_SPEECHMARKS").value(zoo.zZm).name("SUPPORTS_LYRICS_IN_CARD").value(zoo.zyO).name("SUPPORTS_PFM_CHANGED").value(zoo.jiA).name("SPEECH_SYNTH_SUPPORTS_TTS_URLS").value(zoo.BIo).name("AUDIO_PLAYER_SUPPORTS_TTS_URLS").value(zoo.zQM).name("SUPPORTS_SCRUBBING").value(zoo.Qle).name("SUPPORTS_HOME_AUTOMATION").value(zoo.JTe).name("SUPPORTS_KEYS_IN_HEADER").value(zoo.LPk).name("SUPPORTS_MIXING_BEHAVIOR_FOR_AUDIO_PLAYER").value(zoo.yPL).name("SUPPORTS_COMMS").value(zoo.Mlj).name("SUPPORTS_DROPIN_OUTBOUND").value(zoo.zzR).name("SUPPORTS_SIP_OUTBOUND_CALLING").value(zoo.lOf).name("SUPPORTS_VIDEO_CALLING").value(zoo.dMe).name("VOICE_PROFILE_SWITCHING_DISABLED").value(zoo.uzr).name("SUPPORTS_ARBITRATION").value(zoo.HvC).name("SUPPORTS_SECURE_LOCKSCREEN").value(zoo.vkx).name("SUPPORTS_DATAMART_NAMESPACE").value(zoo.Tbw).name("SUPPORTS_TARGET_PLATFORM").value(zoo.XWf).name("AXON_SUPPORT").value(zoo.wDP).name("FRIENDLY_NAME_TEMPLATE").value(zoo.Qgh).name("SCREEN_WIDTH").value(zoo.noQ).endObject();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public Cta mo8353read(JsonReader jsonReader) throws IOException {
        boolean z;
        char c;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.beginObject();
        String str = "";
        String str2 = str;
        String str3 = str2;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = true;
        boolean z5 = false;
        boolean z6 = true;
        int i = 0;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = true;
        boolean z10 = true;
        boolean z11 = false;
        boolean z12 = false;
        boolean z13 = true;
        boolean z14 = true;
        boolean z15 = true;
        boolean z16 = true;
        boolean z17 = false;
        boolean z18 = false;
        while (jsonReader.hasNext()) {
            boolean z19 = z4;
            String nextName = jsonReader.nextName();
            switch (nextName.hashCode()) {
                case -2051480015:
                    z = z3;
                    if (nextName.equals("SUPPORTS_PFM_CHANGED")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -2016001372:
                    z = z3;
                    if (nextName.equals("SUPPORTS_ARBITRATION")) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case -1989570185:
                    z = z3;
                    if (nextName.equals("SUPPORTS_KEYS_IN_HEADER")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -1647161835:
                    z = z3;
                    if (nextName.equals("SUPPORTS_SIP_OUTBOUND_CALLING")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case -1156951203:
                    z = z3;
                    if (nextName.equals("SUPPORTS_TTS_SPEECHMARKS")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1150703913:
                    z = z3;
                    if (nextName.equals("SUPPORTS_DATAMART_NAMESPACE")) {
                        c = 17;
                        break;
                    }
                    c = 65535;
                    break;
                case -889599206:
                    z = z3;
                    if (nextName.equals("FRIENDLY_NAME_TEMPLATE")) {
                        c = 19;
                        break;
                    }
                    c = 65535;
                    break;
                case -271002288:
                    z = z3;
                    if (nextName.equals("VOICE_PROFILE_SWITCHING_DISABLED")) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                case -250396401:
                    z = z3;
                    if (nextName.equals("SUPPORTS_MIXING_BEHAVIOR_FOR_AUDIO_PLAYER")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case -83239738:
                    z = z3;
                    if (nextName.equals("AXON_SUPPORT")) {
                        c = 16;
                        break;
                    }
                    c = 65535;
                    break;
                case -17927061:
                    z = z3;
                    if (nextName.equals("SPEECH_SYNTH_SUPPORTS_TTS_URLS")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 510924108:
                    z = z3;
                    if (nextName.equals("SUPPORTS_COMMS")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 974578245:
                    z = z3;
                    if (nextName.equals("SUPPORTS_VIDEO_CALLING")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 1139093692:
                    z = z3;
                    if (nextName.equals("SUPPORTS_HOME_AUTOMATION")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 1159112118:
                    z = z3;
                    if (nextName.equals("AUDIO_PLAYER_SUPPORTS_TTS_URLS")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1395484196:
                    z = z3;
                    if (nextName.equals("SUPPORTS_SECURE_LOCKSCREEN")) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                case 1396248486:
                    z = z3;
                    if (nextName.equals("SUPPORTS_TARGET_PLATFORM")) {
                        c = 18;
                        break;
                    }
                    c = 65535;
                    break;
                case 1551250112:
                    z = z3;
                    if (nextName.equals("SUPPORTS_DROPIN_OUTBOUND")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 1805573844:
                    z = z3;
                    if (nextName.equals("SUPPORTS_SCRUBBING")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1900760083:
                    z = z3;
                    if (nextName.equals("SCREEN_WIDTH")) {
                        c = 20;
                        break;
                    }
                    c = 65535;
                    break;
                case 2134150074:
                    z = z3;
                    if (nextName.equals("SUPPORTS_LYRICS_IN_CARD")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    z = z3;
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    z5 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 1:
                    z2 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 2:
                    z6 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 3:
                    z7 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 4:
                    z8 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 5:
                    z9 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 6:
                    z10 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 7:
                    z11 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case '\b':
                    z12 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case '\t':
                    z13 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case '\n':
                    z14 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 11:
                    z15 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case '\f':
                    z3 = jsonReader.nextBoolean();
                    z4 = z19;
                    continue;
                case '\r':
                    z4 = jsonReader.nextBoolean();
                    break;
                case 14:
                    z16 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 15:
                    z17 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 16:
                    z18 = jsonReader.nextBoolean();
                    z4 = z19;
                    break;
                case 17:
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                    } else {
                        str2 = jsonReader.nextString();
                    }
                    z4 = z19;
                    break;
                case 18:
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                    } else {
                        str3 = jsonReader.nextString();
                    }
                    z4 = z19;
                    break;
                case 19:
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                    } else {
                        str = jsonReader.nextString();
                    }
                    z4 = z19;
                    break;
                case 20:
                    i = jsonReader.nextInt();
                    z4 = z19;
                    break;
                default:
                    jsonReader.skipValue();
                    z4 = z19;
                    break;
            }
            z3 = z;
        }
        boolean z20 = z3;
        boolean z21 = z4;
        jsonReader.endObject();
        zoO.zZm zzm = new zoO.zZm();
        zzm.zZm = Boolean.valueOf(z5);
        return zzm.LPk(z2).Mlj(z6).zZm(str).zZm(i).uzr(z7).BIo(z8).zzR(z9).Qle(z10).JTe(z11).yPL(z12).zyO(z13).jiA(z14).dMe(z15).HvC(z20).vkx(z21).zZm(z16).lOf(z17).BIo(str2).zQM(str3).zQM(z18).zZm();
    }
}
