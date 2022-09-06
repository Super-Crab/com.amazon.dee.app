package amazon.communication;
/* loaded from: classes.dex */
public enum Priority {
    PRIORITY_HIGH,
    PRIORITY_NORMAL,
    PRIORITY_LOW;

    /* renamed from: amazon.communication.Priority$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$communication$Priority = new int[Priority.values().length];

        static {
            try {
                $SwitchMap$amazon$communication$Priority[Priority.PRIORITY_HIGH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$communication$Priority[Priority.PRIORITY_NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public amazon.communication.connection.Priority convertToConnectionPriority() {
        int ordinal = ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                return amazon.communication.connection.Priority.PRIORITY_LOW;
            }
            return amazon.communication.connection.Priority.PRIORITY_NORMAL;
        }
        return amazon.communication.connection.Priority.PRIORITY_HIGH;
    }
}
