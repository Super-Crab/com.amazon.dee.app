package com.google.android.play.core.splitinstall.testing;

import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.google.android.play.core.internal.zzag;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzy {
    private static final zzag zza = new zzag("LocalTestingConfigParser");
    private final XmlPullParser zzb;
    private final zzs zzc = zzt.zzc();

    zzy(XmlPullParser xmlPullParser) {
        this.zzb = xmlPullParser;
    }

    public static zzt zza(File file) {
        File file2 = new File(file, "local_testing_config.xml");
        if (!file2.exists()) {
            return zzt.zza;
        }
        try {
            FileReader fileReader = new FileReader(file2);
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                newPullParser.setInput(fileReader);
                final zzy zzyVar = new zzy(newPullParser);
                zzyVar.zze("local-testing-config", new zzx() { // from class: com.google.android.play.core.splitinstall.testing.zzu
                    @Override // com.google.android.play.core.splitinstall.testing.zzx
                    public final void zza() {
                        zzy.this.zzd();
                    }
                });
                zzt zze = zzyVar.zzc.zze();
                fileReader.close();
                return zze;
            } catch (Throwable th) {
                try {
                    fileReader.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException | RuntimeException | XmlPullParserException e) {
            zza.zze("%s can not be parsed, using default. Error: %s", "local_testing_config.xml", e.getMessage());
            return zzt.zza;
        }
    }

    public static /* synthetic */ void zzb(final zzy zzyVar) {
        for (int i = 0; i < zzyVar.zzb.getAttributeCount(); i++) {
            if ("defaultErrorCode".equals(zzyVar.zzb.getAttributeName(i))) {
                zzyVar.zzc.zza(com.google.android.play.core.splitinstall.model.zza.zza(zzyVar.zzb.getAttributeValue(i)));
            }
        }
        zzyVar.zze("split-install-error", new zzx() { // from class: com.google.android.play.core.splitinstall.testing.zzw
            @Override // com.google.android.play.core.splitinstall.testing.zzx
            public final void zza() {
                zzy.zzc(zzy.this);
            }
        });
    }

    public static /* synthetic */ void zzc(zzy zzyVar) {
        String str = null;
        String str2 = null;
        for (int i = 0; i < zzyVar.zzb.getAttributeCount(); i++) {
            if (HttpClientModule.ElementsRequestKey.MODULE.equals(zzyVar.zzb.getAttributeName(i))) {
                str = zzyVar.zzb.getAttributeValue(i);
            }
            if ("errorCode".equals(zzyVar.zzb.getAttributeName(i))) {
                str2 = zzyVar.zzb.getAttributeValue(i);
            }
        }
        if (str != null && str2 != null) {
            zzyVar.zzc.zzd().put(str, Integer.valueOf(com.google.android.play.core.splitinstall.model.zza.zza(str2)));
            do {
            } while (zzyVar.zzb.next() != 3);
            return;
        }
        throw new XmlPullParserException(String.format("'%s' element does not contain 'module'/'errorCode' attributes.", "split-install-error"), zzyVar.zzb, null);
    }

    private final void zze(String str, zzx zzxVar) throws IOException, XmlPullParserException {
        while (true) {
            int next = this.zzb.next();
            if (next == 3 || next == 1) {
                return;
            }
            if (this.zzb.getEventType() == 2) {
                if (this.zzb.getName().equals(str)) {
                    zzxVar.zza();
                } else {
                    throw new XmlPullParserException(String.format("Expected '%s' tag but found '%s'.", str, this.zzb.getName()), this.zzb, null);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd() throws IOException, XmlPullParserException {
        zze("split-install-errors", new zzx() { // from class: com.google.android.play.core.splitinstall.testing.zzv
            @Override // com.google.android.play.core.splitinstall.testing.zzx
            public final void zza() {
                zzy.zzb(zzy.this);
            }
        });
    }
}
