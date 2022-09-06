package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@KeepForSdk
@KeepName
@SafeParcelable.Class(creator = "DataHolderCreator", validate = true)
/* loaded from: classes2.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR = new zac();
    private static final Builder zaly = new zab(new String[0], null);
    private boolean mClosed;
    @SafeParcelable.VersionField(id = 1000)
    private final int zalf;
    @SafeParcelable.Field(getter = "getColumns", id = 1)
    private final String[] zalq;
    private Bundle zalr;
    @SafeParcelable.Field(getter = "getWindows", id = 2)
    private final CursorWindow[] zals;
    @SafeParcelable.Field(getter = "getStatusCode", id = 3)
    private final int zalt;
    @SafeParcelable.Field(getter = "getMetadata", id = 4)
    private final Bundle zalu;
    private int[] zalv;
    private int zalw;
    private boolean zalx;

    @KeepForSdk
    /* loaded from: classes2.dex */
    public static class Builder {
        private final String[] zalq;
        private final ArrayList<HashMap<String, Object>> zalz;
        private final String zama;
        private final HashMap<Object, Integer> zamb;
        private boolean zamc;
        private String zamd;

        private Builder(String[] strArr, String str) {
            this.zalq = (String[]) Preconditions.checkNotNull(strArr);
            this.zalz = new ArrayList<>();
            this.zama = str;
            this.zamb = new HashMap<>();
            this.zamc = false;
            this.zamd = null;
        }

        @KeepForSdk
        public DataHolder build(int i) {
            return new DataHolder(this, i, (Bundle) null, (zab) null);
        }

        @KeepForSdk
        public Builder withRow(ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap<String, Object> hashMap = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return zaa(hashMap);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.android.gms.common.data.DataHolder.Builder zaa(java.util.HashMap<java.lang.String, java.lang.Object> r5) {
            /*
                r4 = this;
                com.google.android.gms.common.internal.Asserts.checkNotNull(r5)
                java.lang.String r0 = r4.zama
                r1 = -1
                if (r0 != 0) goto La
            L8:
                r0 = r1
                goto L2f
            La:
                java.lang.Object r0 = r5.get(r0)
                if (r0 != 0) goto L11
                goto L8
            L11:
                java.util.HashMap<java.lang.Object, java.lang.Integer> r2 = r4.zamb
                java.lang.Object r2 = r2.get(r0)
                java.lang.Integer r2 = (java.lang.Integer) r2
                if (r2 != 0) goto L2b
                java.util.HashMap<java.lang.Object, java.lang.Integer> r2 = r4.zamb
                java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> r3 = r4.zalz
                int r3 = r3.size()
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r2.put(r0, r3)
                goto L8
            L2b:
                int r0 = r2.intValue()
            L2f:
                if (r0 != r1) goto L37
                java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> r0 = r4.zalz
                r0.add(r5)
                goto L41
            L37:
                java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> r1 = r4.zalz
                r1.remove(r0)
                java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> r1 = r4.zalz
                r1.add(r0, r5)
            L41:
                r5 = 0
                r4.zamc = r5
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.Builder.zaa(java.util.HashMap):com.google.android.gms.common.data.DataHolder$Builder");
        }

        @KeepForSdk
        public DataHolder build(int i, Bundle bundle) {
            return new DataHolder(this, i, bundle, -1, (zab) null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ Builder(String[] strArr, String str, zab zabVar) {
            this(strArr, null);
        }
    }

    /* loaded from: classes2.dex */
    public static class zaa extends RuntimeException {
        public zaa(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public DataHolder(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) String[] strArr, @SafeParcelable.Param(id = 2) CursorWindow[] cursorWindowArr, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) Bundle bundle) {
        this.mClosed = false;
        this.zalx = true;
        this.zalf = i;
        this.zalq = strArr;
        this.zals = cursorWindowArr;
        this.zalt = i2;
        this.zalu = bundle;
    }

    @KeepForSdk
    public static Builder builder(String[] strArr) {
        return new Builder(strArr, null, null);
    }

    @KeepForSdk
    public static DataHolder empty(int i) {
        return new DataHolder(zaly, i, (Bundle) null);
    }

    private static CursorWindow[] zaa(CursorWrapper cursorWrapper) {
        int i;
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                i = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                i = window.getNumRows();
            }
            while (i < count) {
                if (cursorWrapper.moveToPosition(i)) {
                    CursorWindow window2 = cursorWrapper.getWindow();
                    if (window2 != null) {
                        window2.acquireReference();
                        cursorWrapper.setWindow(null);
                    } else {
                        window2 = new CursorWindow(false);
                        window2.setStartPosition(i);
                        cursorWrapper.fillWindow(i, window2);
                    }
                    if (window2.getNumRows() == 0) {
                        break;
                    }
                    arrayList.add(window2);
                    i = window2.getStartPosition() + window2.getNumRows();
                } else {
                    break;
                }
            }
            cursorWrapper.close();
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @KeepForSdk
    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.zals.length; i++) {
                    this.zals[i].close();
                }
            }
        }
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.zalx && this.zals.length > 0 && !isClosed()) {
                close();
                String obj = toString();
                StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 178);
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(obj);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    @KeepForSdk
    public final boolean getBoolean(String str, int i, int i2) {
        zaa(str, i);
        return Long.valueOf(this.zals[i2].getLong(i, this.zalr.getInt(str))).longValue() == 1;
    }

    @KeepForSdk
    public final byte[] getByteArray(String str, int i, int i2) {
        zaa(str, i);
        return this.zals[i2].getBlob(i, this.zalr.getInt(str));
    }

    @KeepForSdk
    public final int getCount() {
        return this.zalw;
    }

    @KeepForSdk
    public final int getInteger(String str, int i, int i2) {
        zaa(str, i);
        return this.zals[i2].getInt(i, this.zalr.getInt(str));
    }

    @KeepForSdk
    public final long getLong(String str, int i, int i2) {
        zaa(str, i);
        return this.zals[i2].getLong(i, this.zalr.getInt(str));
    }

    @KeepForSdk
    public final Bundle getMetadata() {
        return this.zalu;
    }

    @KeepForSdk
    public final int getStatusCode() {
        return this.zalt;
    }

    @KeepForSdk
    public final String getString(String str, int i, int i2) {
        zaa(str, i);
        return this.zals[i2].getString(i, this.zalr.getInt(str));
    }

    @KeepForSdk
    public final int getWindowIndex(int i) {
        int i2 = 0;
        Preconditions.checkState(i >= 0 && i < this.zalw);
        while (true) {
            int[] iArr = this.zalv;
            if (i2 >= iArr.length) {
                break;
            } else if (i < iArr[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.zalv.length ? i2 - 1 : i2;
    }

    @KeepForSdk
    public final boolean hasColumn(String str) {
        return this.zalr.containsKey(str);
    }

    @KeepForSdk
    public final boolean hasNull(String str, int i, int i2) {
        zaa(str, i);
        return this.zals[i2].isNull(i, this.zalr.getInt(str));
    }

    @KeepForSdk
    public final boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zalq, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zals, i, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zalf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }

    public final double zab(String str, int i, int i2) {
        zaa(str, i);
        return this.zals[i2].getDouble(i, this.zalr.getInt(str));
    }

    public final void zaca() {
        this.zalr = new Bundle();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.zalq;
            if (i2 >= strArr.length) {
                break;
            }
            this.zalr.putInt(strArr[i2], i2);
            i2++;
        }
        this.zalv = new int[this.zals.length];
        int i3 = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zals;
            if (i < cursorWindowArr.length) {
                this.zalv[i] = i3;
                i3 += this.zals[i].getNumRows() - (i3 - cursorWindowArr[i].getStartPosition());
                i++;
            } else {
                this.zalw = i3;
                return;
            }
        }
    }

    @KeepForSdk
    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zalx = true;
        this.zalf = 1;
        this.zalq = (String[]) Preconditions.checkNotNull(strArr);
        this.zals = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zalt = i;
        this.zalu = bundle;
        zaca();
    }

    private DataHolder(CursorWrapper cursorWrapper, int i, Bundle bundle) {
        this(cursorWrapper.getColumnNames(), zaa(cursorWrapper), i, bundle);
    }

    @KeepForSdk
    public DataHolder(Cursor cursor, int i, Bundle bundle) {
        this(new CursorWrapper(cursor), i, bundle);
    }

    private DataHolder(Builder builder, int i, Bundle bundle) {
        this(builder.zalq, zaa(builder, -1), i, (Bundle) null);
    }

    private DataHolder(Builder builder, int i, Bundle bundle, int i2) {
        this(builder.zalq, zaa(builder, -1), i, bundle);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, zab zabVar) {
        this(builder, i, (Bundle) null);
    }

    private static CursorWindow[] zaa(Builder builder, int i) {
        if (builder.zalq.length == 0) {
            return new CursorWindow[0];
        }
        List subList = (i < 0 || i >= builder.zalz.size()) ? builder.zalz : builder.zalz.subList(0, i);
        int size = subList.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(builder.zalq.length);
        boolean z = false;
        CursorWindow cursorWindow2 = cursorWindow;
        int i2 = 0;
        while (i2 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    StringBuilder sb = new StringBuilder(72);
                    sb.append("Allocating additional cursor window for large data set (row ");
                    sb.append(i2);
                    sb.append(")");
                    sb.toString();
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zalq.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) subList.get(i2);
                boolean z2 = true;
                for (int i3 = 0; i3 < builder.zalq.length && z2; i3++) {
                    String str = builder.zalq[i3];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z2 = cursorWindow2.putNull(i2, i3);
                    } else if (obj instanceof String) {
                        z2 = cursorWindow2.putString((String) obj, i2, i3);
                    } else if (obj instanceof Long) {
                        z2 = cursorWindow2.putLong(((Long) obj).longValue(), i2, i3);
                    } else if (obj instanceof Integer) {
                        z2 = cursorWindow2.putLong(((Integer) obj).intValue(), i2, i3);
                    } else if (obj instanceof Boolean) {
                        z2 = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i2, i3);
                    } else if (obj instanceof byte[]) {
                        z2 = cursorWindow2.putBlob((byte[]) obj, i2, i3);
                    } else if (obj instanceof Double) {
                        z2 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i2, i3);
                    } else if (obj instanceof Float) {
                        z2 = cursorWindow2.putDouble(((Float) obj).floatValue(), i2, i3);
                    } else {
                        String valueOf = String.valueOf(obj);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 32 + valueOf.length());
                        sb2.append("Unsupported object for column ");
                        sb2.append(str);
                        sb2.append(RealTimeTextConstants.COLON_SPACE);
                        sb2.append(valueOf);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                }
                if (z2) {
                    z = false;
                } else if (!z) {
                    StringBuilder sb3 = new StringBuilder(74);
                    sb3.append("Couldn't populate window data for row ");
                    sb3.append(i2);
                    sb3.append(" - allocating new window.");
                    sb3.toString();
                    cursorWindow2.freeLastRow();
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zalq.length);
                    arrayList.add(cursorWindow2);
                    i2--;
                    z = true;
                } else {
                    throw new zaa("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                }
                i2++;
            } catch (RuntimeException e) {
                int size2 = arrayList.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    ((CursorWindow) arrayList.get(i4)).close();
                }
                throw e;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, int i2, zab zabVar) {
        this(builder, i, bundle, -1);
    }

    private final void zaa(String str, int i) {
        Bundle bundle = this.zalr;
        if (bundle != null && bundle.containsKey(str)) {
            if (!isClosed()) {
                if (i >= 0 && i < this.zalw) {
                    return;
                }
                throw new CursorIndexOutOfBoundsException(i, this.zalw);
            }
            throw new IllegalArgumentException("Buffer is closed.");
        }
        String valueOf = String.valueOf(str);
        throw new IllegalArgumentException(valueOf.length() != 0 ? "No such column: ".concat(valueOf) : new String("No such column: "));
    }

    public final float zaa(String str, int i, int i2) {
        zaa(str, i);
        return this.zals[i2].getFloat(i, this.zalr.getInt(str));
    }

    public final void zaa(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zaa(str, i);
        this.zals[i2].copyStringToBuffer(i, this.zalr.getInt(str), charArrayBuffer);
    }
}
