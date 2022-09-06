package com.amazon.alexa.client.alexaservice.externalmediaplayer;

import com.amazon.alexa.Wea;
import com.amazon.alexa.WlR;
import com.amazon.alexa.vfn;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.amazon.alexa.zpo;
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
public final class AutoValue_Value extends vfn {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<WlR> {
        public volatile TypeAdapter<zpo> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<Wea> zQM;
        public volatile TypeAdapter<String> zZm;
        public volatile TypeAdapter<Long> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129(VoiceBridgePayloadUtil.PayloadKey.PLAYER_INFO_SOURCE, "playbackSourceId", "trackName", "trackId", "trackNumber");
            outline129.add("artist");
            outline129.add("artistId");
            outline129.add("album");
            outline129.add("albumId");
            outline129.add("coverUrls");
            outline129.add("coverId");
            outline129.add("mediaProvider");
            outline129.add("mediaType");
            outline129.add("durationInMilliseconds");
            this.Qle = gson;
            this.jiA = Util.renameFields(vfn.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, WlR wlR) throws IOException {
            if (wlR == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get(VoiceBridgePayloadUtil.PayloadKey.PLAYER_INFO_SOURCE));
            vfn vfnVar = (vfn) wlR;
            if (vfnVar.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, vfnVar.BIo);
            }
            jsonWriter.name(this.jiA.get("playbackSourceId"));
            if (vfnVar.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, vfnVar.zQM);
            }
            jsonWriter.name(this.jiA.get("trackName"));
            if (vfnVar.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.zZm;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, vfnVar.zyO);
            }
            jsonWriter.name(this.jiA.get("trackId"));
            if (vfnVar.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.zZm;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, vfnVar.jiA);
            }
            jsonWriter.name(this.jiA.get("trackNumber"));
            if (vfnVar.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.zZm;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, vfnVar.Qle);
            }
            jsonWriter.name(this.jiA.get("artist"));
            if (vfnVar.JTe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.zZm;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, vfnVar.JTe);
            }
            jsonWriter.name(this.jiA.get("artistId"));
            if (vfnVar.LPk == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter7 = this.zZm;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, vfnVar.LPk);
            }
            jsonWriter.name(this.jiA.get("album"));
            if (vfnVar.yPL == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter8 = this.zZm;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, vfnVar.yPL);
            }
            jsonWriter.name(this.jiA.get("albumId"));
            if (vfnVar.Mlj == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter9 = this.zZm;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, vfnVar.Mlj);
            }
            jsonWriter.name(this.jiA.get("coverUrls"));
            if (vfnVar.zzR == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<zpo> typeAdapter10 = this.BIo;
                if (typeAdapter10 == null) {
                    typeAdapter10 = this.Qle.getAdapter(zpo.class);
                    this.BIo = typeAdapter10;
                }
                typeAdapter10.write(jsonWriter, vfnVar.zzR);
            }
            jsonWriter.name(this.jiA.get("coverId"));
            if (vfnVar.lOf == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter11 = this.zZm;
                if (typeAdapter11 == null) {
                    typeAdapter11 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter11;
                }
                typeAdapter11.write(jsonWriter, vfnVar.lOf);
            }
            jsonWriter.name(this.jiA.get("mediaProvider"));
            if (vfnVar.dMe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter12 = this.zZm;
                if (typeAdapter12 == null) {
                    typeAdapter12 = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter12;
                }
                typeAdapter12.write(jsonWriter, vfnVar.dMe);
            }
            jsonWriter.name(this.jiA.get("mediaType"));
            if (vfnVar.uzr == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Wea> typeAdapter13 = this.zQM;
                if (typeAdapter13 == null) {
                    typeAdapter13 = this.Qle.getAdapter(Wea.class);
                    this.zQM = typeAdapter13;
                }
                typeAdapter13.write(jsonWriter, vfnVar.uzr);
            }
            jsonWriter.name(this.jiA.get("durationInMilliseconds"));
            TypeAdapter<Long> typeAdapter14 = this.zyO;
            if (typeAdapter14 == null) {
                typeAdapter14 = this.Qle.getAdapter(Long.class);
                this.zyO = typeAdapter14;
            }
            typeAdapter14.write(jsonWriter, Long.valueOf(vfnVar.HvC));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public WlR mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            String str7 = null;
            String str8 = null;
            String str9 = null;
            zpo zpoVar = null;
            String str10 = null;
            String str11 = null;
            Wea wea = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get(VoiceBridgePayloadUtil.PayloadKey.PLAYER_INFO_SOURCE).equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get("playbackSourceId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("trackName").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.zZm;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get("trackId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.zZm;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.jiA.get("trackNumber").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.zZm;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter5;
                        }
                        str5 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.jiA.get("artist").equals(nextName)) {
                        TypeAdapter<String> typeAdapter6 = this.zZm;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter6;
                        }
                        str6 = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.jiA.get("artistId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter7 = this.zZm;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter7;
                        }
                        str7 = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.jiA.get("album").equals(nextName)) {
                        TypeAdapter<String> typeAdapter8 = this.zZm;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter8;
                        }
                        str8 = typeAdapter8.mo8353read(jsonReader);
                    } else if (this.jiA.get("albumId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter9 = this.zZm;
                        if (typeAdapter9 == null) {
                            typeAdapter9 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter9;
                        }
                        str9 = typeAdapter9.mo8353read(jsonReader);
                    } else if (this.jiA.get("coverUrls").equals(nextName)) {
                        TypeAdapter<zpo> typeAdapter10 = this.BIo;
                        if (typeAdapter10 == null) {
                            typeAdapter10 = this.Qle.getAdapter(zpo.class);
                            this.BIo = typeAdapter10;
                        }
                        zpoVar = typeAdapter10.mo8353read(jsonReader);
                    } else if (this.jiA.get("coverId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter11 = this.zZm;
                        if (typeAdapter11 == null) {
                            typeAdapter11 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter11;
                        }
                        str10 = typeAdapter11.mo8353read(jsonReader);
                    } else if (this.jiA.get("mediaProvider").equals(nextName)) {
                        TypeAdapter<String> typeAdapter12 = this.zZm;
                        if (typeAdapter12 == null) {
                            typeAdapter12 = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter12;
                        }
                        str11 = typeAdapter12.mo8353read(jsonReader);
                    } else if (this.jiA.get("mediaType").equals(nextName)) {
                        TypeAdapter<Wea> typeAdapter13 = this.zQM;
                        if (typeAdapter13 == null) {
                            typeAdapter13 = this.Qle.getAdapter(Wea.class);
                            this.zQM = typeAdapter13;
                        }
                        wea = typeAdapter13.mo8353read(jsonReader);
                    } else if (this.jiA.get("durationInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter14 = this.zyO;
                        if (typeAdapter14 == null) {
                            typeAdapter14 = this.Qle.getAdapter(Long.class);
                            this.zyO = typeAdapter14;
                        }
                        j = typeAdapter14.mo8353read(jsonReader).longValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Value(str, str2, str3, str4, str5, str6, str7, str8, str9, zpoVar, str10, str11, wea, j);
        }
    }

    public AutoValue_Value(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, zpo zpoVar, String str10, String str11, Wea wea, long j) {
        super(str, str2, str3, str4, str5, str6, str7, str8, str9, zpoVar, str10, str11, wea, j);
    }
}
