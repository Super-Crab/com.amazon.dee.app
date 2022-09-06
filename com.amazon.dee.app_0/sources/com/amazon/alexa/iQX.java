package com.amazon.alexa;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.huZ;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* compiled from: SqliteClientMetricsDao.java */
/* loaded from: classes.dex */
public class iQX implements IUt {
    public static volatile iQX BIo = null;
    public static final String zZm = "iQX";
    public final pOk zQM;

    public iQX(pOk pok) {
        this.zQM = pok;
    }

    public static iQX zZm(pOk pok) {
        if (BIo == null) {
            synchronized (iQX.class) {
                if (BIo == null) {
                    BIo = new iQX(pok);
                }
            }
        }
        return BIo;
    }

    public synchronized void BIo(VZt vZt, lfx lfxVar) {
        String zZm2 = zZm(lfxVar);
        SQLiteDatabase writableDatabase = this.zQM.getWritableDatabase();
        writableDatabase.insert(zZm2, null, zZm(vZt, lfxVar));
        writableDatabase.close();
    }

    public synchronized List<VZt> zQM() {
        return zQM(lfx.VOICE_INTERACTION);
    }

    public final synchronized List<VZt> zQM(lfx lfxVar) {
        ArrayList arrayList;
        String zZm2 = zZm(lfxVar);
        arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = this.zQM.getWritableDatabase();
        writableDatabase.beginTransaction();
        Cursor query = writableDatabase.query(true, zZm2, null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            arrayList.add(zZm(query, lfxVar));
        }
        query.close();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            String[] strArr = {((huZ) ((VZt) it2.next())).zQM};
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            objArr[0] = WebConstants.UriConstants.QUESTIONMARK_KEY;
            writableDatabase.delete(zZm2, String.format(locale, "eventId IN (%s)", objArr), strArr);
        }
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        writableDatabase.close();
        return arrayList;
    }

    public final String zZm(lfx lfxVar) {
        return lfx.VOICE_INTERACTION.equals(lfxVar) ? "clientMetrics" : lfx.TEXT_INTERACTION.equals(lfxVar) ? "textClientMetrics" : "genericClientMetrics";
    }

    public synchronized List<VZt> BIo(XWx xWx) {
        return zZm(lfx.VOICE_INTERACTION, xWx);
    }

    public List<VZt> BIo() {
        return zQM(lfx.TEXT_INTERACTION);
    }

    public final synchronized List<VZt> zZm(lfx lfxVar, XWx xWx) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = this.zQM.getWritableDatabase();
        writableDatabase.beginTransaction();
        String str = lfx.VOICE_INTERACTION.equals(lfxVar) ? "clientMetrics" : "textClientMetrics";
        Cursor query = writableDatabase.query(true, str, null, "dialogTurnId == ?", new String[]{xWx.getValue()}, null, null, null, null);
        while (query.moveToNext()) {
            arrayList.add(zZm(query, lfxVar));
        }
        query.close();
        writableDatabase.delete(str, "dialogTurnId == ?", new String[]{xWx.getValue()});
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        writableDatabase.close();
        return arrayList;
    }

    public final boolean BIo(lfx lfxVar) {
        return lfx.VOICE_INTERACTION.equals(lfxVar) || lfx.TEXT_INTERACTION.equals(lfxVar);
    }

    public List<VZt> zZm(XWx xWx) {
        return zZm(lfx.TEXT_INTERACTION, xWx);
    }

    public synchronized List<VZt> zZm() {
        return zQM(lfx.GENERIC);
    }

    @VisibleForTesting
    public VZt zZm(Cursor cursor, lfx lfxVar) {
        String str;
        String str2;
        String string = cursor.getString(cursor.getColumnIndexOrThrow(JsonFields.EVENT_NAME));
        try {
            str = cursor.getString(cursor.getColumnIndexOrThrow("eventId"));
        } catch (SQLiteException unused) {
            str = new String(cursor.getBlob(cursor.getColumnIndexOrThrow("eventId")));
        }
        long j = cursor.getLong(cursor.getColumnIndexOrThrow(JsonFields.EVENT_TIMESTAMP));
        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("sourcePackageName"));
        String string3 = cursor.getString(cursor.getColumnIndexOrThrow("clientPackageName"));
        long j2 = -1;
        String str3 = null;
        if (BIo(lfxVar)) {
            str2 = zZm(cursor, AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, null);
            str3 = zZm(cursor, AccessoryMetricsConstants.DIALOG_TURN_ID, null);
        } else {
            Long l = -1L;
            long longValue = l.longValue();
            try {
                int columnIndexOrThrow = cursor.getColumnIndexOrThrow("latency");
                if (cursor.getType(columnIndexOrThrow) != 0) {
                    longValue = cursor.getLong(columnIndexOrThrow);
                }
            } catch (IllegalArgumentException unused2) {
                Log.w(zZm, "Could not find latency column");
            }
            j2 = Long.valueOf(longValue).longValue();
            str2 = null;
        }
        String zZm2 = zZm(cursor, "softwareVersion", "");
        String zZm3 = zZm(cursor, "apiCallId", "");
        huZ.zZm zzm = (huZ.zZm) VZt.zZm();
        if (string != null) {
            zzm.zZm = string;
            huZ.zZm zzm2 = (huZ.zZm) zzm.zQM(str).zZm(j).jiA(string2).BIo(string3);
            zzm2.JTe = str2;
            zzm2.LPk = str3;
            huZ.zZm zzm3 = (huZ.zZm) zzm2.zyO(zZm2);
            zzm3.yPL = Long.valueOf(j2);
            return zzm3.zZm(zZm3).zZm();
        }
        throw new NullPointerException("Null eventName");
    }

    public final String zZm(Cursor cursor, String str, String str2) {
        try {
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
            if (cursor.getType(columnIndexOrThrow) != 0) {
                String string = cursor.getString(columnIndexOrThrow);
                return string == null ? str2 : string;
            }
        } catch (IllegalArgumentException unused) {
            String str3 = zZm;
            Log.w(str3, "Could not find " + str + " column");
        }
        return str2;
    }

    public final ContentValues zZm(VZt vZt, lfx lfxVar) {
        ContentValues contentValues = new ContentValues();
        huZ huz = (huZ) vZt;
        contentValues.put("eventId", huz.zQM);
        contentValues.put(JsonFields.EVENT_NAME, huz.BIo);
        contentValues.put("sourcePackageName", huz.jiA);
        contentValues.put("clientPackageName", huz.Qle);
        contentValues.put("softwareVersion", huz.JTe);
        contentValues.put(JsonFields.EVENT_TIMESTAMP, Long.valueOf(huz.zyO));
        contentValues.put("apiCallId", huz.zzR);
        if (BIo(lfxVar)) {
            contentValues.put(AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, huz.LPk);
            contentValues.put(AccessoryMetricsConstants.DIALOG_TURN_ID, huz.yPL);
        } else {
            contentValues.put("latency", huz.Mlj);
        }
        return contentValues;
    }
}
