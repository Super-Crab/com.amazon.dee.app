package com.amazon.alexa;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.amazon.alexa.WXj;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.amazon.alexa.utils.validation.Assertions;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SqliteGuaranteedDeliveryAlexaEventDao.java */
@Singleton
/* loaded from: classes.dex */
public class VBC implements ZPU {
    public static final String zZm = "VBC";
    public final ptB BIo;
    public final Gson zQM;

    @Inject
    public VBC(ptB ptb, Gson gson) {
        this.BIo = ptb;
        this.zQM = gson;
    }

    public synchronized void BIo() {
        this.BIo.close();
    }

    public synchronized void zZm(RrI rrI) {
        StringBuilder zZm2 = C0179Pya.zZm("Removing persisted alexa event: ");
        zZm2.append(((Fkl) rrI).zZm);
        zZm2.toString();
        SQLiteDatabase writableDatabase = this.BIo.getWritableDatabase();
        writableDatabase.delete("alexaEvents", "requestId = ?", new String[]{((Fkl) rrI).zZm});
        writableDatabase.close();
    }

    public synchronized void BIo(RrI rrI, JjI jjI) {
        String.format(Locale.US, "persisting alexa event: %s %s.%s", ((Fkl) rrI).zZm, ((WXj) jjI).zQM.getHeader().getNamespace().getValue(), ((WXj) jjI).zQM.getHeader().getName().getValue());
        SQLiteDatabase writableDatabase = this.BIo.getWritableDatabase();
        writableDatabase.insert("alexaEvents", null, zZm(rrI, jjI));
        writableDatabase.close();
    }

    public synchronized List<mDr> zZm() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = this.BIo.getReadableDatabase();
        Cursor query = readableDatabase.query(true, "alexaEvents", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            arrayList.add(zZm(query));
        }
        query.close();
        readableDatabase.close();
        StringBuilder zZm2 = C0179Pya.zZm("Getting persisted alexa events. Total: ");
        zZm2.append(arrayList.size());
        zZm2.toString();
        return arrayList;
    }

    public final String zZm(Cursor cursor, String str, @Nullable String str2) {
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

    public final mDr zZm(Cursor cursor) {
        String zZm2 = zZm(cursor, "eventPayload", "{}");
        String zZm3 = zZm(cursor, "eventHeader", "{}");
        Assertions.notEmpty(zZm3, "Header must not be empty");
        Message create = Message.create((Header) this.zQM.fromJson(zZm3, (Class<Object>) Header.class), RawStringPayload.create(zZm2));
        Fkl fkl = new Fkl(zZm(cursor, "requestId", ((Fkl) RrI.zZm()).zZm), null);
        WXj.zZm zzm = (WXj.zZm) JjI.BIo().zZm(create).zZm(true);
        zzm.zZm = AlexaClient.CLIENT;
        return new C0195dTB(fkl, zzm.zZm());
    }

    public final ContentValues zZm(RrI rrI, JjI jjI) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("requestId", ((Fkl) rrI).zZm);
        Message message = ((WXj) jjI).zQM;
        contentValues.put("eventHeader", this.zQM.toJson(message.getHeader()));
        contentValues.put("eventPayload", this.zQM.toJson(message.getPayload()));
        return contentValues;
    }
}
