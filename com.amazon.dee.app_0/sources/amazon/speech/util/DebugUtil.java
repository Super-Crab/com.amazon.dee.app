package amazon.speech.util;

import android.os.Build;
/* loaded from: classes.dex */
public class DebugUtil {
    private static final boolean ENABLE_ALL_LOGS;
    private static final String LOG_PREFIX_SPEECH = "SPCH";
    private static final String LOG_PROP_ADW = "persist.amazon.speech.debug.adw";
    private static final String LOG_PROP_AP = "persist.amazon.speech.debug.ap";
    private static final String LOG_PROP_AUDIO_STREAM_PROVIDER_SERVICE = "persist.amazon.speech.debug.asps";
    private static final String LOG_PROP_CONF = "persist.amazon.speech.debug.conf";
    private static final String LOG_PROP_CSM = "persist.amazon.speech.debug.csm";
    private static final String LOG_PROP_DAVS = "persist.amazon.speech.debug.davs";
    private static final String LOG_PROP_ESF = "persist.amazon.speech.debug.esf";
    private static final String LOG_PROP_MA = "persist.amazon.speech.debug.ma";
    private static final String LOG_PROP_MIC = "persist.amazon.speech.debug.mic";
    private static final String LOG_PROP_OGG = "persist.amazon.speech.debug.ogg";
    private static final String LOG_PROP_SCL = "persist.amazon.speech.debug.scl";
    private static final String LOG_PROP_SHARK = "persist.amazon.speech.debug.shark";
    private static final String LOG_PROP_SIM = "persist.amazon.speech.debug.sim";
    private static final String LOG_PROP_SPEECH_ALL = "persist.amazon.speech.debug.all";
    private static final String LOG_PROP_UTL = "persist.amazon.speech.debug.util";
    private static final String LOG_PROP_WW = "persist.amazon.speech.debug.ww";
    private static final String TAG = getTag(Module.UTL, DebugUtil.class);
    private static final SystemPropertiesHelper sProps = new SystemPropertiesHelper();

    /* renamed from: amazon.speech.util.DebugUtil$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$util$DebugUtil$Module = new int[Module.values().length];

        static {
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.SIM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.WW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.AP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.SCL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.UTL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.MIC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.CONF.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.SHARK.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.DAVS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.ASPS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.CSM.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.OGG.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.ESF.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.ADW.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$amazon$speech$util$DebugUtil$Module[Module.MA.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum Module {
        SIM,
        WW,
        AP,
        SCL,
        UTL,
        MIC,
        CONF,
        ASPS,
        SHARK,
        DAVS,
        CSM,
        OGG,
        ESF,
        ADW,
        MA
    }

    static {
        ENABLE_ALL_LOGS = "userdebug".equals(Build.TYPE);
    }

    public static boolean getShouldDebug(Module module) {
        if (!ENABLE_ALL_LOGS && sProps.getInt(LOG_PROP_SPEECH_ALL, 0) <= 0) {
            if (module == null) {
                return false;
            }
            switch (module.ordinal()) {
                case 0:
                    return sProps.getInt(LOG_PROP_SIM, 0) > 0;
                case 1:
                    return sProps.getInt(LOG_PROP_WW, 0) > 0;
                case 2:
                    return sProps.getInt(LOG_PROP_AP, 0) > 0;
                case 3:
                    return sProps.getInt(LOG_PROP_SCL, 0) > 0;
                case 4:
                    return sProps.getInt(LOG_PROP_UTL, 0) > 0;
                case 5:
                    return sProps.getInt(LOG_PROP_MIC, 0) > 0;
                case 6:
                    return sProps.getInt(LOG_PROP_CONF, 0) > 0;
                case 7:
                    return sProps.getInt(LOG_PROP_AUDIO_STREAM_PROVIDER_SERVICE, 0) > 0;
                case 8:
                    return sProps.getInt(LOG_PROP_SHARK, 0) > 0;
                case 9:
                    return sProps.getInt(LOG_PROP_DAVS, 0) > 0;
                case 10:
                    return sProps.getInt(LOG_PROP_CSM, 0) > 0;
                case 11:
                    return sProps.getInt(LOG_PROP_OGG, 0) > 0;
                case 12:
                    return sProps.getInt(LOG_PROP_ESF, 0) > 0;
                case 13:
                    return sProps.getInt(LOG_PROP_ADW, 0) > 0;
                case 14:
                    return sProps.getInt(LOG_PROP_MA, 0) > 0;
                default:
                    return false;
            }
        }
        return true;
    }

    public static boolean getShouldDebugByProperty(String str) {
        return ENABLE_ALL_LOGS || sProps.getInt(LOG_PROP_SPEECH_ALL, 0) > 0 || sProps.getInt(str, 0) > 0;
    }

    public static String getTag(Module module, Class cls) {
        return getTagWithPrefix(module != null ? module.name() : "", cls);
    }

    public static String getTagWithPrefix(String str, Class cls) {
        return String.format("%s-%s_%s", LOG_PREFIX_SPEECH, str, cls != null ? cls.getSimpleName() : "");
    }
}
