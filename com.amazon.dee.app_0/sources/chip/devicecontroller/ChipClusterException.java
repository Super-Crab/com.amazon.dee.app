package chip.devicecontroller;
/* loaded from: classes.dex */
public class ChipClusterException extends Exception {
    private static final long serialVersionUID = 1;
    public int errorCode;

    public ChipClusterException() {
    }

    public ChipClusterException(int i) {
        super(String.format("CHIP cluster error: %d", Integer.valueOf(i)));
        this.errorCode = i;
    }
}
