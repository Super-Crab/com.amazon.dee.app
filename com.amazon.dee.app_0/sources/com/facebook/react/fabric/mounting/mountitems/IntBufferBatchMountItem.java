package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricComponents;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.systrace.Systrace;
/* loaded from: classes2.dex */
public class IntBufferBatchMountItem implements MountItem {
    static final int INSTRUCTION_CREATE = 2;
    static final int INSTRUCTION_DELETE = 4;
    static final int INSTRUCTION_FLAG_MULTIPLE = 1;
    static final int INSTRUCTION_INSERT = 8;
    static final int INSTRUCTION_REMOVE = 16;
    static final int INSTRUCTION_UPDATE_EVENT_EMITTER = 256;
    static final int INSTRUCTION_UPDATE_LAYOUT = 128;
    static final int INSTRUCTION_UPDATE_PADDING = 512;
    static final int INSTRUCTION_UPDATE_PROPS = 32;
    static final int INSTRUCTION_UPDATE_STATE = 64;
    static final String TAG = "IntBufferBatchMountItem";
    private final int mCommitNumber;
    @NonNull
    private final ThemedReactContext mContext;
    @NonNull
    private final int[] mIntBuffer;
    private final int mIntBufferLen;
    @NonNull
    private final Object[] mObjBuffer;
    private final int mObjBufferLen;
    private final int mRootTag;

    public IntBufferBatchMountItem(int i, @Nullable ThemedReactContext themedReactContext, int[] iArr, Object[] objArr, int i2) {
        this.mRootTag = i;
        this.mCommitNumber = i2;
        this.mContext = themedReactContext;
        this.mIntBuffer = iArr;
        this.mObjBuffer = objArr;
        int[] iArr2 = this.mIntBuffer;
        int i3 = 0;
        this.mIntBufferLen = iArr2 != null ? iArr2.length : 0;
        Object[] objArr2 = this.mObjBuffer;
        this.mObjBufferLen = objArr2 != null ? objArr2.length : i3;
    }

    private void beginMarkers(String str) {
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("FabricUIManager::", str, " - ");
        outline115.append(this.mIntBufferLen);
        outline115.append(" intBufSize  - ");
        outline115.append(this.mObjBufferLen);
        outline115.append(" objBufSize");
        Systrace.beginSection(0L, outline115.toString());
        int i = this.mCommitNumber;
        if (i > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START, null, i);
        }
    }

    private static EventEmitterWrapper castToEventEmitter(Object obj) {
        if (obj != null) {
            return (EventEmitterWrapper) obj;
        }
        return null;
    }

    private static ReadableMap castToProps(Object obj) {
        if (obj != null) {
            return (ReadableMap) obj;
        }
        return null;
    }

    private static StateWrapper castToState(Object obj) {
        if (obj != null) {
            return (StateWrapper) obj;
        }
        return null;
    }

    private void endMarkers() {
        int i = this.mCommitNumber;
        if (i > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END, null, i);
        }
        Systrace.endSection(0L);
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.mContext == null) {
            String str = TAG;
            FLog.e(str, "Cannot execute batch of %s MountItems; no context. Hopefully this is because StopSurface was called.", str);
            return;
        }
        beginMarkers("mountViews");
        int i6 = 0;
        int i7 = 0;
        while (i6 < this.mIntBufferLen) {
            int[] iArr = this.mIntBuffer;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            int i10 = i9 & (-2);
            if ((i9 & 1) != 0) {
                i6 = i8 + 1;
                i = iArr[i8];
            } else {
                i6 = i8;
                i = 1;
            }
            int i11 = i7;
            for (int i12 = 0; i12 < i; i12++) {
                if (i10 == 2) {
                    int i13 = i11 + 1;
                    String fabricComponentName = FabricComponents.getFabricComponentName((String) this.mObjBuffer[i11]);
                    int i14 = i6 + 1;
                    int i15 = i13 + 1;
                    int i16 = i15 + 1;
                    int i17 = i14 + 1;
                    mountingManager.createView(this.mContext, fabricComponentName, this.mIntBuffer[i6], castToProps(this.mObjBuffer[i13]), castToState(this.mObjBuffer[i15]), this.mIntBuffer[i14] == 1);
                    i11 = i16;
                    i6 = i17;
                } else {
                    if (i10 == 4) {
                        i2 = i6 + 1;
                        mountingManager.deleteView(this.mIntBuffer[i6]);
                    } else {
                        if (i10 == 8) {
                            int[] iArr2 = this.mIntBuffer;
                            int i18 = i6 + 1;
                            int i19 = i18 + 1;
                            i5 = i19 + 1;
                            mountingManager.addViewAt(iArr2[i18], iArr2[i6], iArr2[i19]);
                        } else if (i10 == 16) {
                            int[] iArr3 = this.mIntBuffer;
                            int i20 = i6 + 1;
                            int i21 = i20 + 1;
                            i5 = i21 + 1;
                            mountingManager.removeViewAt(iArr3[i6], iArr3[i20], iArr3[i21]);
                        } else {
                            if (i10 == 32) {
                                i2 = i6 + 1;
                                i3 = i11 + 1;
                                mountingManager.updateProps(this.mIntBuffer[i6], castToProps(this.mObjBuffer[i11]));
                            } else if (i10 == 64) {
                                i2 = i6 + 1;
                                i3 = i11 + 1;
                                mountingManager.updateState(this.mIntBuffer[i6], castToState(this.mObjBuffer[i11]));
                            } else {
                                if (i10 == 128) {
                                    int[] iArr4 = this.mIntBuffer;
                                    int i22 = i6 + 1;
                                    int i23 = iArr4[i6];
                                    int i24 = i22 + 1;
                                    int i25 = iArr4[i22];
                                    int i26 = i24 + 1;
                                    int i27 = iArr4[i24];
                                    int i28 = i26 + 1;
                                    mountingManager.updateLayout(i23, i25, i27, iArr4[i26], iArr4[i28]);
                                    i4 = i28 + 1 + 1;
                                } else if (i10 == 512) {
                                    int[] iArr5 = this.mIntBuffer;
                                    int i29 = i6 + 1;
                                    int i30 = iArr5[i6];
                                    int i31 = i29 + 1;
                                    int i32 = iArr5[i29];
                                    int i33 = i31 + 1;
                                    int i34 = iArr5[i31];
                                    int i35 = i33 + 1;
                                    i4 = i35 + 1;
                                    mountingManager.updatePadding(i30, i32, i34, iArr5[i33], iArr5[i35]);
                                } else if (i10 == 256) {
                                    i2 = i6 + 1;
                                    i3 = i11 + 1;
                                    mountingManager.updateEventEmitter(this.mIntBuffer[i6], castToEventEmitter(this.mObjBuffer[i11]));
                                } else {
                                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline53("Invalid type argument to IntBufferBatchMountItem: ", i10, " at index: ", i6));
                                }
                                i6 = i4;
                            }
                            i11 = i3;
                        }
                        i6 = i5;
                    }
                    i6 = i2;
                }
            }
            i7 = i11;
        }
        endMarkers();
    }

    public int getRootTag() {
        return this.mRootTag;
    }

    public boolean shouldSchedule() {
        return this.mIntBufferLen != 0;
    }

    public String toString() {
        int i;
        int i2;
        int i3;
        int i4;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("IntBufferBatchMountItem:");
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.mIntBufferLen) {
                int i7 = i5 + 1;
                int i8 = this.mIntBuffer[i5];
                int i9 = i8 & (-2);
                if ((i8 & 1) != 0) {
                    i = this.mIntBuffer[i7];
                    i7++;
                } else {
                    i = 1;
                }
                int i10 = i6;
                for (int i11 = 0; i11 < i; i11++) {
                    if (i9 == 2) {
                        i3 = i10 + 1 + 2;
                        int i12 = i7 + 1;
                        i4 = i12 + 1;
                        sb.append(String.format("CREATE [%d] - layoutable:%d - %s\n", Integer.valueOf(this.mIntBuffer[i7]), Integer.valueOf(this.mIntBuffer[i12]), FabricComponents.getFabricComponentName((String) this.mObjBuffer[i10])));
                    } else {
                        if (i9 == 4) {
                            i2 = i7 + 1;
                            sb.append(String.format("DELETE [%d]\n", Integer.valueOf(this.mIntBuffer[i7])));
                        } else {
                            if (i9 == 8) {
                                int i13 = i7 + 1;
                                int i14 = i13 + 1;
                                i4 = i14 + 1;
                                sb.append(String.format("INSERT [%d]->[%d] @%d\n", Integer.valueOf(this.mIntBuffer[i7]), Integer.valueOf(this.mIntBuffer[i13]), Integer.valueOf(this.mIntBuffer[i14])));
                            } else if (i9 == 16) {
                                int i15 = i7 + 1;
                                int i16 = i15 + 1;
                                i4 = i16 + 1;
                                sb.append(String.format("REMOVE [%d]->[%d] @%d\n", Integer.valueOf(this.mIntBuffer[i7]), Integer.valueOf(this.mIntBuffer[i15]), Integer.valueOf(this.mIntBuffer[i16])));
                            } else if (i9 == 32) {
                                i3 = i10 + 1;
                                castToProps(this.mObjBuffer[i10]);
                                i4 = i7 + 1;
                                sb.append(String.format("UPDATE PROPS [%d]: %s\n", Integer.valueOf(this.mIntBuffer[i7]), "<hidden>"));
                            } else if (i9 == 64) {
                                i10++;
                                i2 = i7 + 1;
                                sb.append(String.format("UPDATE STATE [%d]\n", Integer.valueOf(this.mIntBuffer[i7])));
                            } else {
                                if (i9 == 128) {
                                    int i17 = i7 + 1;
                                    int i18 = i17 + 1;
                                    int i19 = i18 + 1;
                                    int i20 = i19 + 1;
                                    int i21 = i20 + 1;
                                    sb.append(String.format("UPDATE LAYOUT [%d]: x:%d y:%d w:%d h:%d layoutDirection:%d\n", Integer.valueOf(this.mIntBuffer[i7]), Integer.valueOf(this.mIntBuffer[i17]), Integer.valueOf(this.mIntBuffer[i18]), Integer.valueOf(this.mIntBuffer[i19]), Integer.valueOf(this.mIntBuffer[i20]), Integer.valueOf(this.mIntBuffer[i21])));
                                    i7 = i21 + 1;
                                } else if (i9 == 512) {
                                    int i22 = i7 + 1;
                                    int i23 = i22 + 1;
                                    int i24 = i23 + 1;
                                    int i25 = i24 + 1;
                                    sb.append(String.format("UPDATE PADDING [%d]: top:%d right:%d bottom:%d left:%d\n", Integer.valueOf(this.mIntBuffer[i7]), Integer.valueOf(this.mIntBuffer[i22]), Integer.valueOf(this.mIntBuffer[i23]), Integer.valueOf(this.mIntBuffer[i24]), Integer.valueOf(this.mIntBuffer[i25])));
                                    i7 = i25 + 1;
                                } else if (i9 != 256) {
                                    FLog.e(TAG, "String so far: " + sb.toString());
                                    throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i9 + " at index: " + i7);
                                } else {
                                    i10++;
                                    i2 = i7 + 1;
                                    sb.append(String.format("UPDATE EVENTEMITTER [%d]\n", Integer.valueOf(this.mIntBuffer[i7])));
                                }
                            }
                            i7 = i4;
                        }
                        i7 = i2;
                    }
                    i10 = i3;
                    i7 = i4;
                }
                i5 = i7;
                i6 = i10;
            }
            return sb.toString();
        } catch (Exception e) {
            FLog.e(TAG, "Caught exception trying to print", e);
            StringBuilder sb2 = new StringBuilder();
            for (int i26 = 0; i26 < this.mIntBufferLen; i26++) {
                sb2.append(this.mIntBuffer[i26]);
                sb2.append(", ");
            }
            FLog.e(TAG, sb2.toString());
            for (int i27 = 0; i27 < this.mObjBufferLen; i27++) {
                String str = TAG;
                Object[] objArr = this.mObjBuffer;
                FLog.e(str, objArr[i27] != null ? objArr[i27].toString() : "null");
            }
            return "";
        }
    }
}
