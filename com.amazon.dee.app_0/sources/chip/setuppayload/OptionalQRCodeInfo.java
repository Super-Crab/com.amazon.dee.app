package chip.setuppayload;
/* loaded from: classes.dex */
public class OptionalQRCodeInfo {
    public String data;
    public int int32;
    public int tag;
    public OptionalQRCodeInfoType type;

    /* loaded from: classes.dex */
    public enum OptionalQRCodeInfoType {
        TYPE_UNKNOWN,
        TYPE_STRING,
        TYPE_INT32,
        TYPE_INT64,
        TYPE_UINT32,
        TYPE_UINT64
    }
}
