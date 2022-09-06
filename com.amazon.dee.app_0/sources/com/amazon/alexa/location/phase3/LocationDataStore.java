package com.amazon.alexa.location.phase3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.GuardedBy;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.Encryptor;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class LocationDataStore<T> {
    private static final String selection = "key = ?";
    @GuardedBy("encryptorLock")
    private Encryptor encryptor;
    private final Class<T> itemClass;
    private final LazyComponent<Mobilytics> mobilytics;
    private final SQLiteDatabase sqLiteDatabase;
    private final String tableName;
    private static final String TAG = "LocationDataStore";
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);
    private static final String[] projection = {"key", "value"};
    private final Gson gson = new Gson();
    private Object encryptorLock = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationDataStore(SQLiteDatabase sQLiteDatabase, String str, Class<T> cls, Encryptor encryptor, LazyComponent<Mobilytics> lazyComponent) {
        this.sqLiteDatabase = sQLiteDatabase;
        this.tableName = str;
        this.itemClass = cls;
        this.encryptor = encryptor;
        this.mobilytics = lazyComponent;
    }

    private void updateDataItem(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        synchronized (this.encryptorLock) {
            byte[] encrypt = this.encryptor.encrypt(str2.getBytes(Charset.forName("UTF-8")));
            contentValues.put("key", str);
            contentValues.put("value", encrypt);
            this.sqLiteDatabase.insertWithOnConflict(this.tableName, null, contentValues, 5);
        }
    }

    public /* synthetic */ void lambda$retrieveValueAsRx$0$LocationDataStore(String str, SingleEmitter singleEmitter) throws Throwable {
        T retrieveValue = retrieveValue(str);
        if (retrieveValue == null) {
            singleEmitter.onError(new NoSuchElementException(GeneratedOutlineSupport1.outline72("missing: ", str)));
        }
        singleEmitter.onSuccess(retrieveValue);
    }

    public void removeAll() {
        try {
            this.sqLiteDatabase.delete(this.tableName, null, null);
        } catch (Throwable th) {
            throw new LocationDataStoreException("RemoveAll error", th);
        }
    }

    public Completable removeAllAsRx() {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$Ew-OYjSzVlwtf_7NfDbxPyEa6Ns
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                LocationDataStore.this.removeAll();
            }
        }).subscribeOn(Schedulers.io());
    }

    /* renamed from: removeValues */
    public void lambda$removeValuesAsRx$2$LocationDataStore(Iterable<String> iterable) {
        this.sqLiteDatabase.beginTransaction();
        try {
            Iterator<String> it2 = iterable.iterator();
            while (it2.hasNext()) {
                this.sqLiteDatabase.delete(this.tableName, selection, new String[]{it2.next()});
            }
            this.sqLiteDatabase.setTransactionSuccessful();
        } finally {
        }
    }

    public Completable removeValuesAsRx(final Iterable<String> iterable) {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$LocationDataStore$QlzwDHOydri9JtRLU764KSRCrkg
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                LocationDataStore.this.lambda$removeValuesAsRx$2$LocationDataStore(iterable);
            }
        }).subscribeOn(Schedulers.io());
    }

    public Map<String, T> retrieveAll() {
        HashMap hashMap = new HashMap();
        synchronized (this.encryptorLock) {
            Cursor query = this.sqLiteDatabase.query(this.tableName, projection, null, null, null, null, null);
            try {
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    hashMap.put(query.getString(query.getColumnIndexOrThrow("key")), this.gson.fromJson(new String(this.encryptor.decrypt(query.getBlob(query.getColumnIndexOrThrow("value"))), Charset.forName("UTF-8")), (Class<Object>) this.itemClass));
                    query.moveToNext();
                }
                query.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        return hashMap;
    }

    public Single<Map<String, T>> retrieveAllAsRx() {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$rYeNJiOq8gXxbhEcSbkkAdjQ4Ws
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LocationDataStore.this.retrieveAll();
            }
        }).subscribeOn(Schedulers.io());
    }

    public T retrieveValue(String str) {
        String[] strArr = {str};
        synchronized (this.encryptorLock) {
            Cursor query = this.sqLiteDatabase.query(this.tableName, projection, selection, strArr, null, null, null, "1");
            if (query.getCount() == 0) {
                query.close();
                return null;
            }
            query.moveToFirst();
            try {
                T t = (T) this.gson.fromJson(new String(this.encryptor.decrypt(query.getBlob(query.getColumnIndexOrThrow("value"))), Charset.forName("UTF-8")), (Class<Object>) this.itemClass);
                query.close();
                return t;
            } catch (Throwable th) {
                this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.CANARY_DECRYPT_ERROR, true, COMPONENT_NAME, COMPONENT_NAME, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
                throw th;
            }
        }
    }

    public Single<T> retrieveValueAsRx(final String str) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$LocationDataStore$CmRuCkp4yArxFXJAkWKQ1PdyZkE
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                LocationDataStore.this.lambda$retrieveValueAsRx$0$LocationDataStore(str, singleEmitter);
            }
        }).subscribeOn(Schedulers.io());
    }

    /* renamed from: storeValue */
    public void lambda$storeValueAsRx$1$LocationDataStore(String str, T t) {
        try {
            String json = this.gson.toJson(t);
            if (this.sqLiteDatabase.isOpen()) {
                updateDataItem(str, json);
                return;
            }
            Log.e(TAG, "Could not store {" + str + ":" + t + "} because app is shutting down.");
        } catch (Throwable th) {
            throw new LocationDataStoreException("StoreValue error", th);
        }
    }

    public Completable storeValueAsRx(final String str, final T t) {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$LocationDataStore$B5TJE04nG3hVcdzdYPzuONrFrCw
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                LocationDataStore.this.lambda$storeValueAsRx$1$LocationDataStore(str, t);
            }
        }).subscribeOn(Schedulers.io());
    }
}
