package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: UserSpeechProviderArbitrator.java */
@Singleton
/* loaded from: classes.dex */
public class iDr {
    public static final String zZm = "iDr";
    public final gSO BIo;

    @Inject
    public iDr(gSO gso) {
        this.BIo = gso;
    }

    public final boolean BIo(Set<mqw> set) {
        for (mqw mqwVar : set) {
            if (zQM(mqwVar)) {
                return true;
            }
        }
        return false;
    }

    public final boolean zQM(Set<mqw> set) {
        for (mqw mqwVar : set) {
            if (zZm(mqwVar)) {
                return true;
            }
        }
        return false;
    }

    public void zZm(jdJ jdj) {
        Set<mqw> zZm2 = jdj.zZm();
        boolean zQM = zQM(zZm2);
        boolean zZm3 = zZm(zZm2);
        boolean BIo = BIo(zZm2);
        for (mqw mqwVar : zZm2) {
            if (mqwVar.getMetadata().supportsWakeWord()) {
                boolean zZm4 = zZm(mqwVar, zQM, zZm3, BIo);
                mqwVar.setWakeWordDetectionEnabled(zZm4);
                if (zZm4) {
                    String str = zZm;
                    StringBuilder zZm5 = C0179Pya.zZm("wake word detection is handled by ");
                    zZm5.append(mqwVar.getMetadata().getProviderScope());
                    Log.i(str, zZm5.toString());
                }
            }
        }
    }

    public static boolean BIo(mqw mqwVar) {
        return mqwVar.getMetadata().getProviderScope() == AlexaUserSpeechProviderScope.APPLICATION && mqwVar.getMetadata().supportsWakeWord();
    }

    public static boolean zQM(mqw mqwVar) {
        return mqwVar.getMetadata().getProviderScope() == AlexaUserSpeechProviderScope.SYSTEM;
    }

    public boolean zZm(jdJ jdj, mqw mqwVar, esV esv) {
        Set<mqw> zZm2 = jdj.zZm();
        return esv != esV.WAKE_WORD || (mqwVar.getMetadata().supportsWakeWord() && zZm(mqwVar, zQM(zZm2), zZm(zZm2), BIo(zZm2)));
    }

    public final boolean zZm(mqw mqwVar, boolean z, boolean z2, boolean z3) {
        if (!mqwVar.getMetadata().supportsWakeWord()) {
            Log.w(zZm, "got wake word request from user speech provider that does not support wake word");
            return false;
        } else if (zZm(mqwVar)) {
            return true;
        } else {
            if (BIo(mqwVar)) {
                return !z;
            }
            if (zQM(mqwVar)) {
                return !z && !z2;
            }
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Wake word is not expected for ");
            zZm2.append(mqwVar.getMetadata().getProviderScope());
            Log.w(str, zZm2.toString());
            return false;
        }
    }

    public final boolean zZm(Set<mqw> set) {
        for (mqw mqwVar : set) {
            if (BIo(mqwVar)) {
                return true;
            }
        }
        return false;
    }

    public static boolean zZm(mqw mqwVar) {
        return mqwVar.getMetadata().getProviderScope() == AlexaUserSpeechProviderScope.EXTERNAL_DEVICE && mqwVar.getMetadata().supportsWakeWord();
    }
}
