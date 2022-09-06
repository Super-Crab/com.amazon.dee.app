package net.sqlcipher;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public class MatrixCursor extends AbstractCursor {
    private final int columnCount;
    private final String[] columnNames;
    private Object[] data;
    private int rowCount;

    /* loaded from: classes4.dex */
    public class RowBuilder {
        private final int endIndex;
        private int index;

        RowBuilder(int i, int i2) {
            this.index = i;
            this.endIndex = i2;
        }

        public RowBuilder add(Object obj) {
            if (this.index != this.endIndex) {
                Object[] objArr = MatrixCursor.this.data;
                int i = this.index;
                this.index = i + 1;
                objArr[i] = obj;
                return this;
            }
            throw new CursorIndexOutOfBoundsException("No more columns left.");
        }
    }

    public MatrixCursor(String[] strArr, int i) {
        this.rowCount = 0;
        this.columnNames = strArr;
        this.columnCount = strArr.length;
        this.data = new Object[this.columnCount * (i >= 1 ? i : 1)];
    }

    private void ensureCapacity(int i) {
        Object[] objArr = this.data;
        if (i > objArr.length) {
            int length = objArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.data = new Object[i];
            System.arraycopy(objArr, 0, this.data, 0, objArr.length);
        }
    }

    private Object get(int i) {
        int i2;
        if (i >= 0 && i < (i2 = this.columnCount)) {
            int i3 = this.mPos;
            if (i3 >= 0) {
                if (i3 < this.rowCount) {
                    return this.data[(i3 * i2) + i];
                }
                throw new CursorIndexOutOfBoundsException("After last row.");
            }
            throw new CursorIndexOutOfBoundsException("Before first row.");
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Requested column: ", i, ", # of columns: ");
        outline109.append(this.columnCount);
        throw new CursorIndexOutOfBoundsException(outline109.toString());
    }

    public void addRow(Object[] objArr) {
        int length = objArr.length;
        int i = this.columnCount;
        if (length == i) {
            int i2 = this.rowCount;
            this.rowCount = i2 + 1;
            int i3 = i2 * i;
            ensureCapacity(i + i3);
            System.arraycopy(objArr, 0, this.data, i3, this.columnCount);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("columnNames.length = ");
        outline107.append(this.columnCount);
        outline107.append(", columnValues.length = ");
        outline107.append(objArr.length);
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return this.columnNames;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public int getCount() {
        return this.rowCount;
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public double getDouble(int i) {
        Object obj = get(i);
        return obj == null ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : obj instanceof Number ? ((Number) obj).doubleValue() : Double.parseDouble(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public float getFloat(int i) {
        Object obj = get(i);
        if (obj == null) {
            return 0.0f;
        }
        return obj instanceof Number ? ((Number) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public int getInt(int i) {
        Object obj = get(i);
        if (obj == null) {
            return 0;
        }
        return obj instanceof Number ? ((Number) obj).intValue() : Integer.parseInt(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public long getLong(int i) {
        Object obj = get(i);
        if (obj == null) {
            return 0L;
        }
        return obj instanceof Number ? ((Number) obj).longValue() : Long.parseLong(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public short getShort(int i) {
        Object obj = get(i);
        if (obj == null) {
            return (short) 0;
        }
        return obj instanceof Number ? ((Number) obj).shortValue() : Short.parseShort(obj.toString());
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public String getString(int i) {
        Object obj = get(i);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor, net.sqlcipher.Cursor
    public int getType(int i) {
        return DatabaseUtils.getTypeOfObject(get(i));
    }

    @Override // net.sqlcipher.AbstractCursor, android.database.Cursor
    public boolean isNull(int i) {
        return get(i) == null;
    }

    public RowBuilder newRow() {
        this.rowCount++;
        int i = this.rowCount * this.columnCount;
        ensureCapacity(i);
        return new RowBuilder(i - this.columnCount, i);
    }

    public MatrixCursor(String[] strArr) {
        this(strArr, 16);
    }

    public void addRow(Iterable<?> iterable) {
        int i = this.rowCount;
        int i2 = this.columnCount;
        int i3 = i * i2;
        int i4 = i2 + i3;
        ensureCapacity(i4);
        if (iterable instanceof ArrayList) {
            addRow((ArrayList) iterable, i3);
            return;
        }
        Object[] objArr = this.data;
        for (Object obj : iterable) {
            if (i3 != i4) {
                objArr[i3] = obj;
                i3++;
            } else {
                throw new IllegalArgumentException("columnValues.size() > columnNames.length");
            }
        }
        if (i3 == i4) {
            this.rowCount++;
            return;
        }
        throw new IllegalArgumentException("columnValues.size() < columnNames.length");
    }

    private void addRow(ArrayList<?> arrayList, int i) {
        int size = arrayList.size();
        if (size == this.columnCount) {
            this.rowCount++;
            Object[] objArr = this.data;
            for (int i2 = 0; i2 < size; i2++) {
                objArr[i + i2] = arrayList.get(i2);
            }
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("columnNames.length = ");
        outline107.append(this.columnCount);
        outline107.append(", columnValues.size() = ");
        outline107.append(size);
        throw new IllegalArgumentException(outline107.toString());
    }
}
