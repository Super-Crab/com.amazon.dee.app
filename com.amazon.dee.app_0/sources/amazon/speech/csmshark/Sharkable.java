package amazon.speech.csmshark;
/* loaded from: classes.dex */
public class Sharkable {
    final String contents;
    final long timeStamp = System.currentTimeMillis();
    final String type;

    public Sharkable(String str, String str2) {
        this.type = str;
        this.contents = str2;
    }
}
