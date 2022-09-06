package com.amazon.alexa;

import android.net.Uri;
import android.support.v4.media.MediaMetadataCompat;
import com.amazon.alexa.cJg;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_Value;
import com.amazon.alexa.vfn;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/* compiled from: Value.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class WlR {
    public static final WlR zZm = zZm().zZm();

    /* compiled from: Value.java */
    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class zZm {
        public abstract zZm BIo(String str);

        public abstract zZm JTe(String str);

        public abstract zZm LPk(String str);

        public abstract zZm Mlj(String str);

        public abstract zZm Qle(String str);

        public abstract zZm jiA(String str);

        public abstract zZm yPL(String str);

        public abstract zZm zQM(String str);

        public abstract zZm zZm(long j);

        public abstract zZm zZm(Wea wea);

        public abstract zZm zZm(zpo zpoVar);

        public abstract zZm zZm(String str);

        public abstract WlR zZm();

        public abstract zZm zyO(String str);

        public abstract zZm zzR(String str);
    }

    public static String zZm(String str, MediaMetadataCompat mediaMetadataCompat) {
        return mediaMetadataCompat.getString(str) != null ? mediaMetadataCompat.getString(str) : "";
    }

    public static zZm zZm() {
        zZm zZm2 = new vfn.zZm().JTe("").LPk("").Mlj("").zzR("").yPL("").zQM("").zyO("").zZm("").BIo("").jiA("").Qle("").zZm(Wea.TRACK).zZm(0L);
        cJg.zZm zzm = new cJg.zZm();
        Uri uri = Uri.EMPTY;
        if (uri != null) {
            zzm.jiA = uri;
            return zZm2.zZm(zzm.zZm(uri).BIo(Uri.EMPTY).zQM(Uri.EMPTY).zyO(Uri.EMPTY).zZm());
        }
        throw new NullPointerException("Null full");
    }

    public static TypeAdapter<WlR> zZm(Gson gson) {
        return new AutoValue_Value.GsonTypeAdapter(gson);
    }
}
