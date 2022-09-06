package com.amazon.alexa.accessory.persistence;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.io.OutputStreamSink;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.cache.disk.DefaultDiskStorage;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class FileBackedJsonRxMapStore<V extends JsonObjectSerializable> implements RxMapStore<String, V> {
    private static final String OBFUSCATE_TAG = "_v2";
    private static final String TAG = "FileBackedJsonRxMapStore:";
    private Disposable cleanUpDisposable;
    private final File file;
    private final File fileTmp;
    private boolean initialized;
    private final String keyNameKey;
    private final Object lock;
    private final File obfuscatedFile;
    private final Map<String, Set<V>> store;
    private final String storeNameKey;
    private final JsonObjectSerializable.Factory<V> valueFactory;
    private final String valuesNameKey;
    private final Subject<Map<String, Set<V>>> valuesSubject;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class FileStoreMetricsRecorder {
        private FileStoreMetricsRecorder() {
        }

        static void recordCacheLoaded(String str, boolean z) {
            GeneratedOutlineSupport1.outline171(MetricsConstants.FileJsonStore.CACHE_LOADED, str, z, null);
        }

        static void recordDidPersist(String str, boolean z) {
            GeneratedOutlineSupport1.outline171(MetricsConstants.FileJsonStore.DID_PERSIST, str, z, null);
        }

        static void recordDirectoryCreated(String str, boolean z) {
            GeneratedOutlineSupport1.outline171(MetricsConstants.FileJsonStore.DIRECTORY_CREATED, str, z, null);
        }

        static void recordDirectoryIsValid(String str, boolean z) {
            GeneratedOutlineSupport1.outline171(MetricsConstants.FileJsonStore.DIRECTORY_IS_VALID, str, z, null);
        }

        static void recordFileExists(String str, boolean z) {
            GeneratedOutlineSupport1.outline171(MetricsConstants.FileJsonStore.FILE_EXISTS, str, z, null);
        }
    }

    public FileBackedJsonRxMapStore(File file, JsonObjectSerializable.Factory<V> factory, String str, String str2, String str3) {
        Preconditions.notNull(file, "file");
        Preconditions.notNull(factory, "factory");
        Preconditions.notNull(str, "storeNameKey");
        Preconditions.notNull(str2, "keyNameKey");
        Preconditions.notNull(str3, "valuesNameKey");
        this.file = file;
        this.valueFactory = factory;
        this.storeNameKey = str;
        this.keyNameKey = str2;
        this.valuesNameKey = str3;
        this.obfuscatedFile = getObfuscatedFile(file);
        File parentFile = file.getParentFile();
        this.fileTmp = new File(parentFile, file.getName() + DefaultDiskStorage.FileType.TEMP);
        this.store = new HashMap();
        this.lock = new Object();
        this.valuesSubject = BehaviorSubject.create();
    }

    private JSONObject createJsonFromStore() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, Set<V>> entry : this.store.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(this.valuesNameKey, JsonUtils.toJsonArray(new ArrayList(entry.getValue())));
            jSONObject.put(this.keyNameKey, entry.getKey());
            jSONArray.put(jSONObject);
        }
        return new JSONObject().put(this.storeNameKey, jSONArray);
    }

    private Map<String, Set<V>> createStoreFromJson() throws IOException, JSONException {
        JSONObject jSONObject;
        if (this.file.exists()) {
            jSONObject = new JSONObject(IOUtils.fileToString(this.file));
            Logger.d("Migration: obfuscated file: \n%s\n Deleted the file: %b", this.file.getAbsolutePath(), Boolean.valueOf(this.file.delete()));
        } else {
            jSONObject = new JSONObject(IOUtils.obfuscatedFileToString(this.obfuscatedFile));
        }
        HashMap hashMap = new HashMap();
        if (jSONObject.length() == 0) {
            return hashMap;
        }
        JSONArray jSONArray = jSONObject.getJSONArray(this.storeNameKey);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString(this.keyNameKey);
            JSONArray jSONArray2 = jSONObject2.getJSONArray(this.valuesNameKey);
            HashSet hashSet = new HashSet();
            hashSet.addAll(JsonUtils.fromJsonArray(jSONArray2, this.valueFactory));
            hashMap.put(string, hashSet);
        }
        return hashMap;
    }

    private void ensureInitialized() throws IOException {
        if (this.initialized) {
            return;
        }
        if (!this.file.getParentFile().isDirectory()) {
            if (this.file.getParentFile().mkdirs()) {
                FileStoreMetricsRecorder.recordDirectoryCreated(this.storeNameKey, true);
            } else {
                FileStoreMetricsRecorder.recordDirectoryIsValid(this.storeNameKey, false);
                FileStoreMetricsRecorder.recordDirectoryCreated(this.storeNameKey, false);
                throw new IOException(String.format(Locale.US, "Couldn't make directory %s", this.file.getParentFile()));
            }
        }
        FileStoreMetricsRecorder.recordDirectoryIsValid(this.storeNameKey, true);
        this.initialized = true;
        if (!this.file.exists() && !this.obfuscatedFile.exists()) {
            FileStoreMetricsRecorder.recordFileExists(this.storeNameKey, false);
            this.valuesSubject.onNext(Collections.emptyMap());
            return;
        }
        FileStoreMetricsRecorder.recordFileExists(this.storeNameKey, true);
        try {
            this.store.putAll(createStoreFromJson());
            FileStoreMetricsRecorder.recordCacheLoaded(this.storeNameKey, true);
        } catch (JSONException e) {
            FileStoreMetricsRecorder.recordCacheLoaded(this.storeNameKey, false);
            boolean delete = this.file.delete();
            boolean delete2 = this.obfuscatedFile.delete();
            Logger.e("Corruption: Unable to read file: \n%s\n Deleted the file: %b \n %s", this.file.getAbsolutePath(), Boolean.valueOf(delete), e);
            Logger.e("Corruption: Unable to read obfuscated file: \n%s\n Deleted the file: %b \n %s", this.obfuscatedFile.getAbsolutePath(), Boolean.valueOf(delete2), e);
        }
        this.valuesSubject.onNext(safeCopy());
    }

    private File getObfuscatedFile(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return new File(file.getParentFile(), GeneratedOutlineSupport1.outline72(name, OBFUSCATE_TAG));
        }
        File parentFile = file.getParentFile();
        StringBuilder sb = new StringBuilder();
        GeneratedOutlineSupport1.outline150(name, 0, lastIndexOf, sb, OBFUSCATE_TAG);
        return new File(parentFile, GeneratedOutlineSupport1.outline55(name, lastIndexOf, sb));
    }

    private Map<String, Set<V>> safeCopy() {
        HashMap hashMap = new HashMap(this.store.size());
        for (Map.Entry<String, Set<V>> entry : this.store.entrySet()) {
            hashMap.put(entry.getKey(), Collections.unmodifiableSet(new HashSet(entry.getValue())));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    private void writeStoreToFile() throws IOException, JSONException {
        this.fileTmp.delete();
        try {
            String jSONObject = createJsonFromStore().toString(4);
            byte[] bytes = jSONObject.getBytes();
            OutputStreamSink outputStreamSink = new OutputStreamSink(new BufferedOutputStream(new FileOutputStream(this.fileTmp)));
            IOUtils.transferObfuscated(new ByteArraySource(bytes), outputStreamSink);
            outputStreamSink.flush();
            outputStreamSink.close();
            if (this.fileTmp.renameTo(this.obfuscatedFile)) {
                FileStoreMetricsRecorder.recordDidPersist(this.storeNameKey, true);
                Logger.d("Wrote file %s : %s", this.file.getAbsolutePath(), jSONObject);
                return;
            }
            throw new IOException(String.format(Locale.US, "Could not rename store file %s to %s!", this.fileTmp, this.obfuscatedFile));
        } catch (IOException | JSONException e) {
            FileStoreMetricsRecorder.recordDidPersist(this.storeNameKey, false);
            throw e;
        }
    }

    @Override // com.amazon.alexa.accessory.persistence.RxMapStore
    @SuppressLint({"CheckResult"})
    public void cleanCache(UserSupplier userSupplier) {
        synchronized (this.lock) {
            if (this.cleanUpDisposable != null) {
                return;
            }
            this.cleanUpDisposable = userSupplier.queryUser().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$FileBackedJsonRxMapStore$BySGG2QOhcSzVKeofQdWOiA5OzU
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FileBackedJsonRxMapStore.this.lambda$cleanCache$7$FileBackedJsonRxMapStore((User) obj);
                }
            }, $$Lambda$FileBackedJsonRxMapStore$5Flvqhd_R7s2kPvkvUj64GGC_U.INSTANCE);
        }
    }

    @Override // com.amazon.alexa.accessory.persistence.RxMapStore
    public Completable clear() {
        return Completable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$FileBackedJsonRxMapStore$oWfdaVj2GQ8ybpL_ryieMed9K18
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FileBackedJsonRxMapStore.this.lambda$clear$0$FileBackedJsonRxMapStore();
            }
        }).subscribeOn(Schedulers.io());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.accessory.persistence.RxMapStore
    public /* bridge */ /* synthetic */ Completable delete(String str, Object obj) {
        return delete(str, (String) ((JsonObjectSerializable) obj));
    }

    public /* synthetic */ void lambda$cleanCache$7$FileBackedJsonRxMapStore(User user) throws Throwable {
        if (user != User.ABSENT) {
            return;
        }
        Logger.d("%s Cleaning up after logout", TAG);
        clear().subscribe($$Lambda$FileBackedJsonRxMapStore$3NfeJ2moKb1aANBhNtJHZTbUf5s.INSTANCE, $$Lambda$FileBackedJsonRxMapStore$C20uNeLthl0tL7f8zc902cc496g.INSTANCE);
    }

    public /* synthetic */ CompletableSource lambda$clear$0$FileBackedJsonRxMapStore() throws Throwable {
        Completable complete;
        synchronized (this.lock) {
            ensureInitialized();
            this.file.delete();
            this.obfuscatedFile.delete();
            this.store.clear();
            this.valuesSubject.onNext(safeCopy());
            complete = Completable.complete();
        }
        return complete;
    }

    public /* synthetic */ CompletableSource lambda$delete$3$FileBackedJsonRxMapStore(String str, JsonObjectSerializable jsonObjectSerializable) throws Throwable {
        synchronized (this.lock) {
            ensureInitialized();
            Set<V> set = this.store.get(str);
            if (set != null && !set.isEmpty()) {
                set.remove(jsonObjectSerializable);
                if (set.isEmpty()) {
                    this.store.remove(str);
                } else {
                    this.store.put(str, set);
                }
                writeStoreToFile();
                this.valuesSubject.onNext(safeCopy());
                return Completable.complete();
            }
            return Completable.complete();
        }
    }

    public /* synthetic */ SingleSource lambda$get$1$FileBackedJsonRxMapStore(String str) throws Throwable {
        synchronized (this.lock) {
            ensureInitialized();
            Set<V> set = this.store.get(str);
            if (set == null) {
                return Single.just(Collections.emptySet());
            }
            return Single.just(Collections.unmodifiableSet(new HashSet(set)));
        }
    }

    public /* synthetic */ SingleSource lambda$put$2$FileBackedJsonRxMapStore(String str, JsonObjectSerializable jsonObjectSerializable) throws Throwable {
        Single just;
        synchronized (this.lock) {
            ensureInitialized();
            Set<V> set = this.store.get(str);
            if (set == null) {
                set = new HashSet<>();
            }
            set.remove(jsonObjectSerializable);
            set.add(jsonObjectSerializable);
            this.store.put(str, set);
            writeStoreToFile();
            this.valuesSubject.onNext(safeCopy());
            just = Single.just(jsonObjectSerializable);
        }
        return just;
    }

    public /* synthetic */ ObservableSource lambda$queryValues$4$FileBackedJsonRxMapStore() throws Throwable {
        Subject<Map<String, Set<V>>> subject;
        synchronized (this.lock) {
            ensureInitialized();
            subject = this.valuesSubject;
        }
        return subject;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.accessory.persistence.RxMapStore
    public /* bridge */ /* synthetic */ Single put(String str, Object obj) {
        return put(str, (String) ((JsonObjectSerializable) obj));
    }

    @Override // com.amazon.alexa.accessory.persistence.RxMapStore
    public Observable<Map<String, Set<V>>> queryValues() {
        return Observable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$FileBackedJsonRxMapStore$LXGsjJdsMt7GCWx7CoylO-7JpO8
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FileBackedJsonRxMapStore.this.lambda$queryValues$4$FileBackedJsonRxMapStore();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Completable delete(final String str, final V v) {
        return Completable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$FileBackedJsonRxMapStore$6djpU2k0Uy4OYHKWYIesIX_-wPw
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FileBackedJsonRxMapStore.this.lambda$delete$3$FileBackedJsonRxMapStore(str, v);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override // com.amazon.alexa.accessory.persistence.RxMapStore
    public Single<Set<V>> get(final String str) {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$FileBackedJsonRxMapStore$xhQUO-f580RC6u62vR4iCoa3PcM
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FileBackedJsonRxMapStore.this.lambda$get$1$FileBackedJsonRxMapStore(str);
            }
        }).subscribeOn(Schedulers.io());
    }

    public Single<V> put(final String str, final V v) {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.persistence.-$$Lambda$FileBackedJsonRxMapStore$wOpL5ifxqUDBP5HkvXCL1Uao2Vc
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FileBackedJsonRxMapStore.this.lambda$put$2$FileBackedJsonRxMapStore(str, v);
            }
        }).subscribeOn(Schedulers.io());
    }
}
