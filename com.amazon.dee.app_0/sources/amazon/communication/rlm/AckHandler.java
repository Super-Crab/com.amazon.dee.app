package amazon.communication.rlm;
/* loaded from: classes.dex */
public interface AckHandler {
    void onAck(int i);

    void onNack(int i, int i2, String str);

    void onPack(int i, int i2, String str);
}
