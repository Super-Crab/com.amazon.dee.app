package lombok;
/* loaded from: classes4.dex */
public enum AccessLevel {
    PUBLIC,
    MODULE,
    PROTECTED,
    PACKAGE,
    PRIVATE,
    NONE;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static AccessLevel[] valuesCustom() {
        AccessLevel[] valuesCustom = values();
        int length = valuesCustom.length;
        AccessLevel[] accessLevelArr = new AccessLevel[length];
        System.arraycopy(valuesCustom, 0, accessLevelArr, 0, length);
        return accessLevelArr;
    }
}
