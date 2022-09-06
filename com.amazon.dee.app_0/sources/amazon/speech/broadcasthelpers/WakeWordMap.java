package amazon.speech.broadcasthelpers;
/* loaded from: classes.dex */
public class WakeWordMap {
    final String mBaseWord;
    final String mI18nCode;
    final String mLocaleWord;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WakeWordMap(String str, String str2, String str3) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("BaseWakeWord cannot be null.");
            }
            if (str3 != null) {
                this.mI18nCode = str;
                this.mBaseWord = str2;
                this.mLocaleWord = str3;
                return;
            }
            throw new IllegalArgumentException("LocalizedWakeWord cannot be null.");
        }
        throw new IllegalArgumentException("LocaleCode cannot be null.");
    }
}
