package amazon.speech.broadcasthelpers;

import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes.dex */
public class WakeWordMapper {
    public static final String BASE_ALEXA = "alexa";
    public static final String BASE_AMAZON = "amazon";
    static final String LC_NULL = "";
    static final String LC_JAPAN = "ja";
    public static final String JP_ALEXA = "アレクサ";
    public static final String JP_AMAZON = "アマゾン";
    public static final String BASE_ECHO = "echo";
    public static final String JP_ECHO = "エコー";
    public static final String BASE_COMPUTER = "computer";
    public static final String JP_COMPUTER = "コンピューター";
    static final WakeWordMap[] WAKEWORD_MAPS = {new WakeWordMap(LC_JAPAN, "alexa", JP_ALEXA), new WakeWordMap(LC_JAPAN, "amazon", JP_AMAZON), new WakeWordMap(LC_JAPAN, BASE_ECHO, JP_ECHO), new WakeWordMap(LC_JAPAN, BASE_COMPUTER, JP_COMPUTER), new WakeWordMap("", "alexa", "alexa"), new WakeWordMap("", "amazon", "amazon"), new WakeWordMap("", BASE_ECHO, BASE_ECHO), new WakeWordMap("", BASE_COMPUTER, BASE_COMPUTER)};
    private static HashMap<String, WakeWordMap> WAKEWORD_CONVERSION_MAP = new HashMap<>();

    static {
        int i = 0;
        while (true) {
            WakeWordMap[] wakeWordMapArr = WAKEWORD_MAPS;
            if (i < wakeWordMapArr.length) {
                addItem(WAKEWORD_CONVERSION_MAP, wakeWordMapArr[i]);
                i++;
            } else {
                return;
            }
        }
    }

    private static void addItem(HashMap<String, WakeWordMap> hashMap, WakeWordMap wakeWordMap) {
        hashMap.put(wakeWordMap.mLocaleWord, wakeWordMap);
        hashMap.put(wakeWordMap.mBaseWord.concat(wakeWordMap.mI18nCode), wakeWordMap);
    }

    public static String getLocalizedWakeWord(String str, String str2) {
        if (str != null) {
            if (str2 != null) {
                WakeWordMap wakeWordMap = WAKEWORD_CONVERSION_MAP.get(str.concat(str2));
                return wakeWordMap == null ? str : wakeWordMap.mLocaleWord;
            }
            throw new IllegalArgumentException("LocaleCode cannot be null.");
        }
        throw new IllegalArgumentException("BaseWakeWord cannot be null.");
    }

    public static boolean isLocalizedWakeWord(String str) {
        if (str != null) {
            return WAKEWORD_CONVERSION_MAP.containsKey(str.toLowerCase(Locale.US));
        }
        throw new IllegalArgumentException("String cannot be null.");
    }
}
