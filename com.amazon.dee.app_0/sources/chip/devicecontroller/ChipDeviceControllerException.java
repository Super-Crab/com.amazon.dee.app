package chip.devicecontroller;
/* loaded from: classes.dex */
public class ChipDeviceControllerException extends Exception {
    private static final long serialVersionUID = 1;
    public int errorCode;

    public ChipDeviceControllerException() {
    }

    public ChipDeviceControllerException(int i, String str) {
        super(str == null ? String.format("Error Code %d", Integer.valueOf(i)) : str);
        this.errorCode = i;
    }
}
