package com.amazon.alexa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.utils.TimeProvider;
import com.google.gson.Gson;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ExternalComponentStateStore.java */
@Singleton
/* loaded from: classes.dex */
public class ICz {
    public final TFi BIo;
    public final Gson jiA;
    public final TimeProvider zQM;
    public final Context zZm;
    public final AlexaClientEventBus zyO;

    @Inject
    public ICz(Context context, TFi tFi, TimeProvider timeProvider, Gson gson, AlexaClientEventBus alexaClientEventBus) {
        this.zZm = context;
        this.BIo = tFi;
        this.zQM = timeProvider;
        this.zyO = alexaClientEventBus;
        this.jiA = gson;
    }

    public void zZm(Set<Namespace> set) {
        long elapsedRealTime = this.zQM.elapsedRealTime();
        dCo dco = (dCo) this.BIo;
        dco.BIo.getWritableDatabase().delete("externalComponentState", dco.zZm("namespace", set.size()), dco.zZm(set));
        this.zyO.zyO(new WXz(this.zQM.elapsedRealTime() - elapsedRealTime));
    }

    public void zZm(Set<Namespace> set, boolean z) {
        HashSet hashSet = new HashSet();
        for (Namespace namespace : set) {
            hashSet.add(kvw.zZm(namespace, z, null));
        }
        if (!hashSet.isEmpty()) {
            SQLiteDatabase writableDatabase = ((dCo) this.BIo).BIo.getWritableDatabase();
            writableDatabase.beginTransaction();
            for (Object obj : hashSet) {
                ContentValues contentValues = new ContentValues();
                Yxp yxp = (Yxp) obj;
                contentValues.put("namespace", yxp.zZm.getValue());
                contentValues.put("isCachingEnabled", Boolean.valueOf(yxp.BIo));
                writableDatabase.insertWithOnConflict("externalComponentStateCachingPreference", null, contentValues, 5);
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
        }
        if (!z) {
            zZm(set);
        }
    }

    public final PackageName zZm(ExtendedClient extendedClient) {
        return PackageName.create(extendedClient.getPackageName());
    }
}
