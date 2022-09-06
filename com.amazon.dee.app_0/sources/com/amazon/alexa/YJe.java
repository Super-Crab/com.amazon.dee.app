package com.amazon.alexa;

import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.YJe.zQM;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.uTP;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dagger.Lazy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
/* compiled from: CachingPersister.java */
/* loaded from: classes.dex */
public class YJe<Key extends zQM, Value> {
    public static final String zZm = "YJe";
    public final Class<Value> BIo;
    public final Lazy<uTP> zQM;
    public final zyO<Key> zyO;
    public final Map<Key, Value> jiA = new HashMap();
    public final Map<Key, Value> Qle = new HashMap();
    public final Set<Key> JTe = new HashSet();

    /* compiled from: CachingPersister.java */
    /* loaded from: classes.dex */
    private class BIo implements uTP.zZm {
        public final Key BIo;
        public Value zQM;
        public final ConditionVariable zZm = new ConditionVariable();

        public BIo(Key key) {
            this.BIo = key;
        }

        @Override // com.amazon.alexa.uTP.zZm
        public void zZm(PersistentStorage persistentStorage, Gson gson) {
            synchronized (YJe.this) {
                this.zQM = (Value) YJe.zZm(YJe.this, persistentStorage, gson, this.BIo);
                this.zZm.open();
            }
        }
    }

    /* compiled from: CachingPersister.java */
    /* loaded from: classes.dex */
    private class jiA implements uTP.BIo {
        public final Value BIo;
        public final Key zZm;

        public jiA(Key key, Value value) {
            this.zZm = key;
            this.BIo = value;
        }

        @Override // com.amazon.alexa.uTP.BIo
        public void zZm(PersistentStorage.Transaction transaction, Gson gson) {
            synchronized (YJe.this) {
                YJe.zZm(YJe.this, transaction, gson, this.zZm, this.BIo);
            }
        }
    }

    /* compiled from: CachingPersister.java */
    /* loaded from: classes.dex */
    public interface zQM {
        String name();
    }

    /* compiled from: CachingPersister.java */
    /* loaded from: classes.dex */
    private class zZm implements uTP.zZm {
        public final ConditionVariable zZm = new ConditionVariable();
        public final Map<Key, Value> BIo = new HashMap();

        public zZm() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.alexa.uTP.zZm
        public void zZm(PersistentStorage persistentStorage, Gson gson) {
            synchronized (YJe.this) {
                Set<String> keys = persistentStorage.getKeys();
                keys.remove("timestamp");
                for (String str : keys) {
                    zQM zqm = (zQM) YJe.this.zyO.create(str);
                    this.BIo.put(zqm, YJe.zZm(YJe.this, persistentStorage, gson, zqm));
                }
                for (zQM zqm2 : YJe.this.Qle.keySet()) {
                    if (!this.BIo.containsKey(zqm2)) {
                        this.BIo.put(zqm2, YJe.this.Qle.get(zqm2));
                    }
                }
                for (zQM zqm3 : YJe.this.jiA.keySet()) {
                    if (!this.BIo.containsKey(zqm3)) {
                        this.BIo.put(zqm3, YJe.this.jiA.get(zqm3));
                    }
                }
                this.zZm.open();
            }
        }
    }

    /* compiled from: CachingPersister.java */
    /* loaded from: classes.dex */
    public interface zyO<Key> {
        Key create(String str);
    }

    @Inject
    public YJe(Class<Value> cls, zyO<Key> zyo, Lazy<uTP> lazy) {
        this.BIo = cls;
        this.zyO = zyo;
        this.zQM = lazy;
    }

    @Nullable
    public synchronized Value zyO(Key key) {
        BIo bIo;
        bIo = new BIo(key);
        this.zQM.mo358get().BIo(bIo);
        if (!bIo.zZm.block(100L)) {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("loading of ");
            zZm2.append(bIo.BIo.name());
            zZm2.append(" timed out");
            Log.e(str, zZm2.toString());
        }
        return bIo.zQM;
    }

    public synchronized Map<Key, Value> BIo() {
        zZm zzm;
        zzm = new zZm();
        this.zQM.mo358get().BIo(zzm);
        if (!zzm.zZm.block(100L)) {
            Log.e(zZm, "loading of all keys timed out");
        }
        return zzm.BIo;
    }

    public synchronized void zQM(Key key) {
        this.JTe.add(key);
    }

    public synchronized void zZm(Key key, Value value) {
        this.jiA.put(key, value);
    }

    public synchronized void zQM(Key key, Value value) {
        uTP mo358get = this.zQM.mo358get();
        mo358get.jiA.submit(new SIO(mo358get, new jiA(key, value)));
    }

    public synchronized void zZm(Key key) {
        BIo bIo = new BIo(key);
        uTP mo358get = this.zQM.mo358get();
        mo358get.LPk = mo358get.jiA.submit(new aum(mo358get, bIo));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object zZm(YJe yJe, PersistentStorage persistentStorage, Gson gson, zQM zqm) {
        if (!yJe.Qle.containsKey(zqm)) {
            String string = persistentStorage.getString(zqm.name());
            if (string != null && !"null".equals(string)) {
                try {
                    yJe.Qle.put(zqm, gson.fromJson(string, (Class<Object>) yJe.BIo));
                } catch (JsonSyntaxException | NullPointerException e) {
                    String str = zZm;
                    Log.w(str, "Failed to restore state for key: " + zqm, e);
                    persistentStorage.edit().remove(zqm.name()).commitAsynchronously();
                }
            } else {
                yJe.Qle.put(zqm, yJe.jiA.get(zqm));
            }
        }
        return yJe.Qle.get(zqm);
    }

    public synchronized void BIo(Key key, Value value) {
        this.zQM.mo358get().BIo(new jiA(key, value));
    }

    public void BIo(Key key) {
        this.Qle.remove(key);
        this.jiA.remove(key);
        this.zQM.mo358get().zyO.mo358get().edit().remove(key.name()).commitSynchronously();
    }

    public static /* synthetic */ void zZm(YJe yJe, PersistentStorage.Transaction transaction, Gson gson, zQM zqm, Object obj) {
        yJe.Qle.put(zqm, obj);
        if (!yJe.JTe.contains(zqm)) {
            transaction.set(zqm.name(), gson.toJson(obj));
        }
    }

    public void zZm() {
        this.Qle.clear();
        this.jiA.clear();
        this.zQM.mo358get().zZm();
    }
}
