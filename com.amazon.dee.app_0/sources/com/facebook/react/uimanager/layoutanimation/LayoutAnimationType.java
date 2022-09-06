package com.facebook.react.uimanager.layoutanimation;
/* loaded from: classes2.dex */
enum LayoutAnimationType {
    CREATE,
    UPDATE,
    DELETE;

    /* renamed from: com.facebook.react.uimanager.layoutanimation.LayoutAnimationType$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType = new int[LayoutAnimationType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType[LayoutAnimationType.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType[LayoutAnimationType.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType[LayoutAnimationType.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static String toString(LayoutAnimationType layoutAnimationType) {
        int ordinal = layoutAnimationType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return "update";
            }
            if (ordinal == 2) {
                return "delete";
            }
            throw new IllegalArgumentException("Unsupported LayoutAnimationType: " + layoutAnimationType);
        }
        return "create";
    }
}
