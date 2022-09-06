package amazon.communication.rlm;
/* loaded from: classes.dex */
public final class PackCodes {
    public static final int PACK_CODE_MAX = 3999;
    public static final int PACK_CODE_MIN = 3000;
    public static final int RECEIVING_DEVICE_DOES_NOT_SUPPORT_RELIABLE_MESSAGING = 3000;

    private PackCodes() {
    }

    public static boolean isPackCode(int i) {
        return 3000 <= i && i <= 3999;
    }
}
