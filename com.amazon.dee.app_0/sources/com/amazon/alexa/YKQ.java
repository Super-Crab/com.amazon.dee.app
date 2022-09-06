package com.amazon.alexa;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity;
import com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateProvider;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.alexa.lNG;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.google.auto.value.AutoValue;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: ExternalComponentStateAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class YKQ {
    public static final String zZm = "YKQ";
    public final LrI BIo;
    public final zZm JTe;
    public final Set<ExternalComponentStateProvider> LPk;
    public final Set<Namespace> Mlj;
    public final zyO Qle;
    public final TimeProvider jiA;
    public final Shr<ExternalComponentStateProvider> yPL;
    public final ICz zQM;
    public final AlexaClientEventBus zyO;

    /* compiled from: ExternalComponentStateAuthority.java */
    /* loaded from: classes.dex */
    private class BIo implements Callable<zQM> {
        public final AlexaClientEventBus BIo;
        public final TimeProvider zQM;
        public final ExternalComponentStateProvider zZm;

        public BIo(YKQ ykq, ExternalComponentStateProvider externalComponentStateProvider, AlexaClientEventBus alexaClientEventBus, TimeProvider timeProvider) {
            this.zZm = externalComponentStateProvider;
            this.BIo = alexaClientEventBus;
            this.zQM = timeProvider;
        }

        @Override // java.util.concurrent.Callable
        public zQM call() throws Exception {
            C0179Pya.zZm(C0179Pya.zZm("Fetching context from "), this.zZm, YKQ.zZm);
            long elapsedRealTime = this.zQM.elapsedRealTime();
            Set<ComponentState> states = this.zZm.getStates();
            this.BIo.zyO(new qib(PackageName.create(this.zZm.getPackageName()), this.zQM.elapsedRealTime() - elapsedRealTime, !states.isEmpty()));
            return new sSP(this.zZm, states);
        }
    }

    /* compiled from: ExternalComponentStateAuthority.java */
    @AutoValue
    @VisibleForTesting
    /* loaded from: classes.dex */
    static abstract class zQM {
        public abstract ExternalComponentStateProvider BIo();

        public abstract Set<ComponentState> zZm();
    }

    /* compiled from: ExternalComponentStateAuthority.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    static class zZm {
        public ExecutorCompletionService<zQM> zZm(ExecutorService executorService) {
            return new ExecutorCompletionService<>(executorService);
        }
    }

    /* compiled from: ExternalComponentStateAuthority.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    static class zyO {
        public ExecutorService zZm(int i) {
            return ExecutorFactory.newFixedSizeThreadPool(YKQ.class.getSimpleName(), i);
        }
    }

    @Inject
    public YKQ(LrI lrI, ICz iCz, AlexaClientEventBus alexaClientEventBus, TimeProvider timeProvider, @Named("namespaces_without_capabilities") Set<Namespace> set) {
        zyO zyo = new zyO();
        zZm zzm = new zZm();
        this.BIo = lrI;
        this.zQM = iCz;
        this.zyO = alexaClientEventBus;
        this.jiA = timeProvider;
        this.Qle = zyo;
        this.JTe = zzm;
        this.yPL = new Shr<>();
        this.LPk = new LinkedHashSet();
        this.Mlj = set;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public synchronized void on(xZV xzv) {
        if (((uyC) xzv).BIo != null) {
            for (ExternalComponentStateProvider externalComponentStateProvider : this.yPL.BIo(((uyC) xzv).BIo)) {
                zZm(externalComponentStateProvider);
            }
        }
    }

    public synchronized void BIo(ExtendedClient extendedClient, ExternalComponentStateProvider externalComponentStateProvider) {
        String str = zZm;
        Log.i(str, "Registering " + externalComponentStateProvider + " for namespaces: " + externalComponentStateProvider.getComponentStateNamespaces());
        Set<Namespace> componentStateNamespaces = externalComponentStateProvider.getComponentStateNamespaces();
        this.LPk.add(externalComponentStateProvider);
        this.yPL.zZm(extendedClient, externalComponentStateProvider);
        this.zyO.zyO(new xTZ(componentStateNamespaces, true));
        if (this.LPk.size() >= 16) {
            this.zyO.zyO(new fpL(this.LPk.size()));
        }
    }

    public synchronized Set<ComponentState> zZm() {
        HashSet hashSet;
        ExecutorCompletionService<zQM> executorCompletionService;
        long elapsedRealTime;
        long elapsedRealTime2 = this.jiA.elapsedRealTime();
        ExecutorService zZm2 = this.Qle.zZm(Math.max(1, Math.min(this.LPk.size(), 16)));
        ExecutorCompletionService<zQM> zZm3 = this.JTe.zZm(zZm2);
        HashSet<Future> hashSet2 = new HashSet(this.LPk.size());
        for (ExternalComponentStateProvider externalComponentStateProvider : this.LPk) {
            hashSet2.add(zZm3.submit(new BIo(this, externalComponentStateProvider, this.zyO, this.jiA)));
        }
        ICz iCz = this.zQM;
        gZg gzg = new gZg(this);
        long elapsedRealTime3 = iCz.zQM.elapsedRealTime();
        HashSet<ComponentState> hashSet3 = new HashSet();
        dCo dco = (dCo) iCz.BIo;
        SQLiteDatabase readableDatabase = dco.BIo.getReadableDatabase();
        HashSet<ExternalComponentStateEntity> hashSet4 = new HashSet();
        Cursor query = readableDatabase.query("externalComponentState", null, dco.zZm("namespace", gzg.size()), dco.zZm(gzg), null, null, null);
        query.moveToFirst();
        while (!query.isAfterLast()) {
            String string = query.getString(query.getColumnIndex("namespace"));
            String string2 = query.getString(query.getColumnIndex("name"));
            long j = elapsedRealTime2;
            String string3 = query.getString(query.getColumnIndex("payload"));
            String string4 = query.getString(query.getColumnIndex(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME));
            ExecutorService executorService = zZm2;
            int i = query.getInt(query.getColumnIndex("packageVersionCode"));
            Date date = null;
            ExecutorCompletionService<zQM> executorCompletionService2 = zZm3;
            try {
                date = dco.zQM.parse(query.getString(query.getColumnIndex("dateUpdated")));
            } catch (ParseException e) {
                Log.e(dCo.zZm, e.getMessage());
            }
            Date date2 = date;
            lNG.zZm zzm = (lNG.zZm) ExternalComponentStateEntity.zZm().zZm(Namespace.create(string)).zZm(Name.create(string2)).zZm(string3).zZm(PackageName.create(string4)).zZm(i);
            zzm.Qle = date2;
            hashSet4.add(zzm.zZm());
            query.moveToNext();
            elapsedRealTime2 = j;
            zZm2 = executorService;
            zZm3 = executorCompletionService2;
        }
        long j2 = elapsedRealTime2;
        ExecutorService executorService2 = zZm2;
        ExecutorCompletionService<zQM> executorCompletionService3 = zZm3;
        query.close();
        for (ExternalComponentStateEntity externalComponentStateEntity : hashSet4) {
            hashSet3.add(ComponentState.create(externalComponentStateEntity));
        }
        iCz.zyO.zyO(new YiY(iCz.zQM.elapsedRealTime() - elapsedRealTime3));
        long elapsedRealTime4 = this.jiA.elapsedRealTime();
        HashSet<zQM> hashSet5 = new HashSet();
        long j3 = 200;
        int i2 = 0;
        while (i2 < hashSet2.size()) {
            try {
                elapsedRealTime = this.jiA.elapsedRealTime();
                executorCompletionService = executorCompletionService3;
            } catch (InterruptedException | CancellationException | ExecutionException e2) {
                e = e2;
                executorCompletionService = executorCompletionService3;
            }
            try {
                Future<zQM> poll = executorCompletionService.poll(j3, TimeUnit.MILLISECONDS);
                if (poll != null && !poll.isCancelled()) {
                    hashSet5.add(poll.get());
                } else if (poll == null) {
                    Log.e(zZm, "Retrieved future was null (timeout likely elapsed). Skipping");
                } else {
                    Log.wtf(zZm, "Future was cancelled. Why is it here?");
                }
                j3 = Math.max(0L, j3 - (this.jiA.elapsedRealTime() - elapsedRealTime));
            } catch (InterruptedException e3) {
                e = e3;
                Log.w(zZm, "Error fetching contexts", e);
                i2++;
                executorCompletionService3 = executorCompletionService;
            } catch (CancellationException e4) {
                e = e4;
                Log.w(zZm, "Error fetching contexts", e);
                i2++;
                executorCompletionService3 = executorCompletionService;
            } catch (ExecutionException e5) {
                e = e5;
                Log.w(zZm, "Error fetching contexts", e);
                i2++;
                executorCompletionService3 = executorCompletionService;
            }
            i2++;
            executorCompletionService3 = executorCompletionService;
        }
        if (j3 < 100) {
            String str = zZm;
            StringBuilder sb = new StringBuilder();
            sb.append("Took longer than expected to fetch contexts. Total time taken: ");
            sb.append(Long.toString(elapsedRealTime4 - this.jiA.elapsedRealTime()));
            Log.w(str, sb.toString());
        }
        for (Future future : hashSet2) {
            future.cancel(true);
        }
        HashSet<ComponentState> hashSet6 = new HashSet();
        for (zQM zqm : hashSet5) {
            ExternalComponentStateProvider BIo2 = zqm.BIo();
            Set<ComponentState> zZm4 = zqm.zZm();
            HashSet hashSet7 = new HashSet();
            for (ComponentState componentState : zZm4) {
                if (componentState != null && zZm(componentState.getHeader().BIo(), BIo2.getPackageName())) {
                    hashSet7.add(componentState);
                }
            }
            hashSet6.addAll(hashSet7);
            zZm(this.yPL.BIo((Shr<ExternalComponentStateProvider>) BIo2), hashSet7);
        }
        executorService2.shutdownNow();
        long elapsedRealTime5 = this.jiA.elapsedRealTime() - j2;
        Log.i(zZm, "Collecting external component states took " + elapsedRealTime5 + "ms");
        this.zyO.zyO(new fqV(elapsedRealTime5, this.LPk.size()));
        hashSet = new HashSet(hashSet6);
        HashSet hashSet8 = new HashSet();
        for (ComponentState componentState2 : hashSet6) {
            hashSet8.add(componentState2.getHeader());
        }
        for (ComponentState componentState3 : hashSet3) {
            if (!hashSet8.contains(componentState3.getHeader())) {
                hashSet.add(componentState3);
            }
        }
        return hashSet;
    }

    public synchronized void BIo(ExtendedClient extendedClient, Set<Namespace> set) {
        Iterator<Namespace> it2 = set.iterator();
        while (it2.hasNext()) {
            if (!zZm(it2.next(), extendedClient.getPackageName())) {
                it2.remove();
            }
        }
        if (!set.isEmpty()) {
            this.zQM.zZm(set);
        }
    }

    public synchronized void BIo() {
        ((dCo) this.zQM.BIo).BIo.close();
    }

    public synchronized void zZm(ExtendedClient extendedClient, ExternalComponentStateProvider externalComponentStateProvider) {
        if (extendedClient.equals(this.yPL.BIo((Shr<ExternalComponentStateProvider>) externalComponentStateProvider))) {
            zZm(externalComponentStateProvider);
        }
    }

    public synchronized void zZm(ExtendedClient extendedClient, Set<ComponentState> set) {
        int i;
        Iterator<ComponentState> it2 = set.iterator();
        while (it2.hasNext()) {
            if (!zZm(it2.next().getHeader().BIo(), extendedClient.getPackageName())) {
                it2.remove();
            }
        }
        if (!set.isEmpty()) {
            ICz iCz = this.zQM;
            long elapsedRealTime = iCz.zQM.elapsedRealTime();
            HashSet<ExternalComponentStateEntity> hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (ComponentState componentState : set) {
                hashSet2.add(componentState.getHeader().BIo());
            }
            dCo dco = (dCo) iCz.BIo;
            SQLiteDatabase readableDatabase = dco.BIo.getReadableDatabase();
            HashSet<Yxp> hashSet3 = new HashSet();
            Cursor query = readableDatabase.query("externalComponentStateCachingPreference", null, dco.zZm("namespace", hashSet2.size()), dco.zZm(hashSet2), null, null, null);
            query.moveToFirst();
            while (true) {
                Date date = null;
                boolean z = false;
                if (query.isAfterLast()) {
                    break;
                }
                String string = query.getString(query.getColumnIndex("namespace"));
                if (query.getInt(query.getColumnIndex("isCachingEnabled")) != 0) {
                    z = true;
                }
                try {
                    date = dco.zQM.parse(query.getString(query.getColumnIndex("dateUpdated")));
                } catch (ParseException e) {
                    Log.e(dCo.zZm, e.getMessage());
                }
                hashSet3.add(kvw.zZm(Namespace.create(string), z, date));
                query.moveToNext();
            }
            query.close();
            HashSet hashSet4 = new HashSet();
            for (Yxp yxp : hashSet3) {
                if (!yxp.BIo) {
                    hashSet4.add(yxp.zZm);
                }
            }
            for (ComponentState componentState2 : set) {
                if (!hashSet4.contains(componentState2.getHeader().BIo())) {
                    ExternalComponentStateEntity.zZm zZm2 = ExternalComponentStateEntity.zZm().zZm(componentState2.getHeader().BIo()).zZm(componentState2.getHeader().zZm()).zZm(iCz.jiA.toJson(componentState2.getPayload())).zZm(iCz.zZm(extendedClient));
                    try {
                        i = iCz.zZm.getPackageManager().getPackageInfo(extendedClient.getPackageName(), 0).versionCode;
                    } catch (PackageManager.NameNotFoundException unused) {
                        i = 0;
                    }
                    hashSet.add(zZm2.zZm(i).zZm());
                }
            }
            SQLiteDatabase writableDatabase = ((dCo) iCz.BIo).BIo.getWritableDatabase();
            writableDatabase.beginTransaction();
            for (ExternalComponentStateEntity externalComponentStateEntity : hashSet) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("namespace", externalComponentStateEntity.zyO().getValue());
                contentValues.put("name", externalComponentStateEntity.zQM().getValue());
                contentValues.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, externalComponentStateEntity.jiA().getValue());
                contentValues.put("payload", externalComponentStateEntity.JTe());
                contentValues.put("packageVersionCode", Integer.valueOf(externalComponentStateEntity.Qle()));
                writableDatabase.insertWithOnConflict("externalComponentState", null, contentValues, 5);
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            iCz.zyO.zyO(new ZBY(iCz.zQM.elapsedRealTime() - elapsedRealTime));
        }
    }

    public synchronized void zZm(ExtendedClient extendedClient) {
        ICz iCz = this.zQM;
        PackageName zZm2 = iCz.zZm(extendedClient);
        long elapsedRealTime = iCz.zQM.elapsedRealTime();
        TFi tFi = iCz.BIo;
        Set<? extends StronglyTypedString> singleton = Collections.singleton(zZm2);
        dCo dco = (dCo) tFi;
        dco.BIo.getWritableDatabase().delete("externalComponentState", dco.zZm(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, singleton.size()), dco.zZm(singleton));
        iCz.zyO.zyO(JjA.zZm(iCz.zQM.elapsedRealTime() - elapsedRealTime));
    }

    public synchronized void zZm(ExtendedClient extendedClient, Set<Namespace> set, boolean z) {
        Iterator<Namespace> it2 = set.iterator();
        while (it2.hasNext()) {
            if (!zZm(it2.next(), extendedClient.getPackageName())) {
                it2.remove();
            }
        }
        if (!set.isEmpty()) {
            this.zQM.zZm(set, z);
        }
    }

    public synchronized void zZm(ExtendedClient extendedClient, boolean z) {
        this.zQM.zZm(this.BIo.zZm(extendedClient.getPackageName()), z);
    }

    public final void zZm(ExternalComponentStateProvider externalComponentStateProvider) {
        String str = zZm;
        Log.i(str, "Deregistering " + externalComponentStateProvider + " for namespaces: " + externalComponentStateProvider.getComponentStateNamespaces());
        this.LPk.remove(externalComponentStateProvider);
        this.yPL.remove(externalComponentStateProvider);
        this.zyO.zyO(new xTZ(externalComponentStateProvider.getComponentStateNamespaces(), false));
    }

    public final boolean zZm(Namespace namespace, String str) {
        return !AvsApiConstants.zZm.contains(namespace) && (this.BIo.zZm(str).contains(namespace) || this.Mlj.contains(namespace));
    }
}
