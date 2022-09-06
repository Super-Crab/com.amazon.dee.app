package com.amazon.alexa;

import android.net.Uri;
import android.util.Log;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: AlexaServiceSettingsStore.java */
@Singleton
/* loaded from: classes.dex */
public class Box {
    public static final List<String> BIo = Collections.emptyList();
    public static final String zZm = "Box";
    public final Lazy<PersistentStorage> zQM;
    public final Lazy<Gson> zyO;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AlexaServiceSettingsStore.java */
    /* loaded from: classes.dex */
    public enum zZm {
        ENDPOINT("endpoint"),
        TIMEZONE("timezone"),
        CAPABILITIES_ENDPOINT("capabilitiesEndpoint"),
        LAST_USER_ACTIVITY("lastUserActivity"),
        LOCALE("locale"),
        LOCALES(SystemModelI18nConfigTransformer.KEY_LOCALES),
        IS_LOCALE_DEVICE_DEFAULT("isLocaleDeviceDefault"),
        SUPPORTS_MOBILE_DOWNCHANNEL("supportsMobileDownchannel"),
        VERSION_NAME(ModelTransformer.KEY_FIRMWARE_VERSION_NAME),
        KEY_LAST_UNLOCKED("lastUnlocked");
        
        public final String key;

        zZm(String str) {
            this.key = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.key;
        }
    }

    @Inject
    public Box(@Named("AlexaServiceSettings") Lazy<PersistentStorage> lazy, Lazy<Gson> lazy2) {
        this.zQM = lazy;
        this.zyO = lazy2;
    }

    public synchronized Uri BIo() {
        return Uri.parse(zZm(zZm.CAPABILITIES_ENDPOINT, ""));
    }

    public synchronized String JTe() {
        return zZm(zZm.VERSION_NAME, "");
    }

    public synchronized boolean LPk() {
        return zZm(zZm.CAPABILITIES_ENDPOINT);
    }

    public synchronized void Mlj() {
        BIo(zZm.LOCALE);
        BIo(zZm.LOCALES);
        BIo(zZm.IS_LOCALE_DEVICE_DEFAULT);
    }

    public String Qle() {
        return this.zQM.mo358get().getString(zZm.KEY_LAST_UNLOCKED.toString(), "1970-01-01T00:00:00.00Z");
    }

    public synchronized TimeZone jiA() {
        String zZm2 = zZm(zZm.TIMEZONE, "");
        if (zZm2.isEmpty()) {
            return null;
        }
        return TimeZone.getTimeZone(zZm2);
    }

    public synchronized boolean yPL() {
        return zZm(zZm.ENDPOINT);
    }

    public synchronized Uri zQM() {
        return Uri.parse(zZm(zZm.ENDPOINT, ""));
    }

    public synchronized void zZm(TimeZone timeZone) {
        BIo(zZm.TIMEZONE, timeZone.getID());
    }

    public synchronized boolean zyO() {
        return this.zQM.mo358get().getBoolean(zZm.SUPPORTS_MOBILE_DOWNCHANNEL.toString(), false);
    }

    public synchronized void zzR() {
        BIo(zZm.TIMEZONE, "");
    }

    public final void BIo(zZm zzm, String str) {
        this.zQM.mo358get().edit().set(zzm.toString(), str).commitAsynchronously();
    }

    public synchronized void zZm(Uri uri) {
        BIo(zZm.ENDPOINT, uri.toString());
    }

    public final void BIo(zZm zzm) {
        this.zQM.mo358get().edit().remove(zzm.toString()).commitAsynchronously();
    }

    @Nullable
    public synchronized mAU zZm() {
        boolean z = this.zQM.mo358get().getBoolean(zZm.IS_LOCALE_DEVICE_DEFAULT.toString(), false);
        if (zZm(zZm.LOCALES)) {
            zZm zzm = zZm.LOCALES;
            List<String> list = BIo;
            try {
                List<String> list2 = (List) this.zyO.mo358get().fromJson(this.zQM.mo358get().getString(zzm.toString(), list.toString()), new Zka(this).getType());
                if (list2 != null) {
                    if (!list2.isEmpty()) {
                        list = list2;
                    }
                }
            } catch (JsonParseException e) {
                String str = zZm;
                Log.e(str, "Parsing error encountered while deserializing " + zzm, e);
            }
            ArrayList arrayList = new ArrayList();
            for (String str2 : list) {
                arrayList.add(Locale.forLanguageTag(str2));
            }
            String str3 = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("getAlexaLocale: ");
            zZm2.append(arrayList.toString());
            Log.i(str3, zZm2.toString());
            return mAU.zZm(arrayList, z);
        } else if (!zZm(zZm.LOCALE)) {
            return null;
        } else {
            String zZm3 = zZm(zZm.LOCALE, "");
            String str4 = zZm;
            Log.i(str4, "getAlexaLocale: " + zZm3);
            return mAU.zZm(Collections.singletonList(Locale.forLanguageTag(zZm3)), z);
        }
    }

    public void BIo(String str) {
        this.zQM.mo358get().edit().set(zZm.KEY_LAST_UNLOCKED.toString(), str).commitAsynchronously();
    }

    public synchronized void zZm(mAU mau) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        if (mau != null) {
            jFa jfa = (jFa) mau;
            z = jfa.BIo;
            for (Locale locale : jfa.zZm) {
                arrayList.add(locale.toLanguageTag());
            }
        }
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("setAlexaLocale to: ");
        zZm2.append(arrayList.toString());
        Log.i(str, zZm2.toString());
        zZm zzm = zZm.LOCALES;
        this.zQM.mo358get().edit().set(zzm.toString(), this.zyO.mo358get().toJson(arrayList)).commitAsynchronously();
        this.zQM.mo358get().edit().set(zZm.IS_LOCALE_DEVICE_DEFAULT.toString(), z).commitAsynchronously();
    }

    public synchronized void zZm(boolean z) {
        this.zQM.mo358get().edit().set(zZm.SUPPORTS_MOBILE_DOWNCHANNEL.toString(), z).commitAsynchronously();
    }

    public synchronized void zZm(String str) {
        BIo(zZm.VERSION_NAME, str);
    }

    public final boolean zZm(zZm zzm) {
        return this.zQM.mo358get().contains(zzm.toString());
    }

    public final String zZm(zZm zzm, String str) {
        return this.zQM.mo358get().getString(zzm.toString(), str);
    }
}
