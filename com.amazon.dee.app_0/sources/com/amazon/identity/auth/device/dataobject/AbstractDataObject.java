package com.amazon.identity.auth.device.dataobject;

import android.content.ContentValues;
import android.content.Context;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.identity.auth.device.datastore.AbstractDataSource;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public abstract class AbstractDataObject {
    public static final int NON_EXISTENT_ROW = -1;
    private long rowId = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean areObjectsEqual(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public boolean delete(Context context) {
        boolean deleteRow = mo4057getDataSource(context).deleteRow(getRowId());
        if (deleteRow) {
            setRowId(-1L);
        }
        return deleteRow;
    }

    public abstract boolean equals(Object obj);

    /* renamed from: getDataSource */
    public abstract <K extends AbstractDataObject> AbstractDataSource<K> mo4057getDataSource(Context context);

    public long getRowId() {
        return this.rowId;
    }

    public abstract ContentValues getValuesForInsert();

    public long insert(Context context) {
        return mo4057getDataSource(context).insertRow(this);
    }

    public boolean insertOrUpdate(Context context) {
        if (getRowId() == -1) {
            return mo4057getDataSource(context).insertRow(this) != -1;
        }
        return mo4057getDataSource(context).updateRow(getRowId(), getValuesForInsert());
    }

    public void setRowId(long j) {
        this.rowId = j;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("rowid = ");
        outline107.append(getRowId());
        outline107.append(AccessoryMetricsConstants.DELIMITER);
        outline107.append(getValuesForInsert().toString());
        return outline107.toString();
    }

    public boolean update(Context context) {
        return mo4057getDataSource(context).updateRow(getRowId(), getValuesForInsert());
    }
}
