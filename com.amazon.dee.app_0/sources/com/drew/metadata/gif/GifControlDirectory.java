package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class GifControlDirectory extends Directory {
    public static final int TAG_DELAY = 1;
    public static final int TAG_DISPOSAL_METHOD = 2;
    public static final int TAG_TRANSPARENT_COLOR_FLAG = 4;
    public static final int TAG_TRANSPARENT_COLOR_INDEX = 5;
    public static final int TAG_USER_INPUT_FLAG = 3;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    /* renamed from: com.drew.metadata.gif.GifControlDirectory$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod = new int[DisposalMethod.values().length];

        static {
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.DO_NOT_DISPOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.INVALID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.NOT_SPECIFIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.RESTORE_TO_BACKGROUND_COLOR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.RESTORE_TO_PREVIOUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.TO_BE_DEFINED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum DisposalMethod {
        NOT_SPECIFIED,
        DO_NOT_DISPOSE,
        RESTORE_TO_BACKGROUND_COLOR,
        RESTORE_TO_PREVIOUS,
        TO_BE_DEFINED,
        INVALID;

        public static DisposalMethod typeOf(int i) {
            switch (i) {
                case 0:
                    return NOT_SPECIFIED;
                case 1:
                    return DO_NOT_DISPOSE;
                case 2:
                    return RESTORE_TO_BACKGROUND_COLOR;
                case 3:
                    return RESTORE_TO_PREVIOUS;
                case 4:
                case 5:
                case 6:
                case 7:
                    return TO_BE_DEFINED;
                default:
                    return INVALID;
            }
        }

        @Override // java.lang.Enum
        public String toString() {
            int ordinal = ordinal();
            return ordinal != 0 ? ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? ordinal != 5 ? super.toString() : "Invalid value" : "To Be Defined" : "Restore to Previous" : "Restore to Background Color" : "Don't Dispose" : "Not Specified";
        }
    }

    static {
        _tagNameMap.put(1, "Delay");
        _tagNameMap.put(2, "Disposal Method");
        _tagNameMap.put(3, "User Input Flag");
        _tagNameMap.put(4, "Transparent Color Flag");
        _tagNameMap.put(5, "Transparent Color Index");
    }

    public GifControlDirectory() {
        setDescriptor(new GifControlDescriptor(this));
    }

    @NotNull
    public DisposalMethod getDisposalMethod() {
        return (DisposalMethod) getObject(2);
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "GIF Control";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public boolean isTransparent() {
        Boolean booleanObject = getBooleanObject(4);
        return booleanObject != null && booleanObject.booleanValue();
    }
}
