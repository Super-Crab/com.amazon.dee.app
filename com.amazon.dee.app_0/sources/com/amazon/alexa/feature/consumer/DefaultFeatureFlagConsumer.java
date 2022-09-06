package com.amazon.alexa.feature.consumer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes7.dex */
public class DefaultFeatureFlagConsumer implements FeatureFlagConsumer {
    private static final String AUTHORITY_POSTFIX = ".alexa.feature";
    @VisibleForTesting
    static final String COLUMN_FEATURE = "feature";
    private static final String DELETE_ALL_PATH = "deleteall";
    private static final String FEATURE_FLAGS_PATH = "featureflags";
    @VisibleForTesting
    static final String FEATURE_SELECTION = "feature = ?";
    private static final String SCHEME = "content";
    private static final String TAG = "DefaultFeatureFlagConsumer";
    private static volatile Uri deleteAllUri;
    private static volatile Uri featureFlagsUri;
    private final String authority;
    private final Context context;
    @Nullable
    private ContentObserver deleteAllContentObserver;
    @Nullable
    private ContentObserver featureChangedContentObserver;
    private static final String[] TO_STRING_ARRAY = new String[0];
    @VisibleForTesting
    static final String COLUMN_IS_ENABLED = "isEnabled";
    @VisibleForTesting
    static final String[] COLUMNS = {"feature", COLUMN_IS_ENABLED};
    @VisibleForTesting
    static final String FEATURE_ALL = null;
    @VisibleForTesting
    static final String[] FEATURE_ALL_SELECTION_ARGS = new String[0];
    private final ConcurrentHashMap<String, Boolean> cachedFeatureFlags = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Set<FeatureFlagListener>> featuresToNotify = new ConcurrentHashMap<>();
    private final Set<String> featuresToRefresh = Collections.newSetFromMap(new ConcurrentHashMap());
    private AtomicBoolean toRefreshAll = new AtomicBoolean(false);
    private AtomicBoolean isFeatureServiceReady = new AtomicBoolean(false);
    private final FeatureQuery featureQuery = new FeatureQuery() { // from class: com.amazon.alexa.feature.consumer.DefaultFeatureFlagConsumer.1
        @Override // com.amazon.alexa.feature.consumer.api.FeatureQuery
        public boolean isActive(String str) {
            return DefaultFeatureFlagConsumer.this.get(str);
        }
    };

    public DefaultFeatureFlagConsumer(Context context) {
        this.context = context;
        this.authority = getAuthorityString(context);
    }

    private boolean checkFeatureServiceAvailability() {
        if (!this.isFeatureServiceReady.get()) {
            refresh();
        }
        return this.isFeatureServiceReady.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clear() {
        this.cachedFeatureFlags.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean get(String str) {
        Boolean bool = this.cachedFeatureFlags.get(str);
        if (bool != null) {
            return bool.booleanValue();
        }
        if (!this.toRefreshAll.get()) {
            this.featuresToRefresh.add(str);
        }
        query(FEATURE_SELECTION, new String[]{str});
        Boolean bool2 = this.cachedFeatureFlags.get(str);
        if (bool2 == null) {
            return false;
        }
        return bool2.booleanValue();
    }

    private String getAuthorityString(Context context) {
        return context.getPackageName() + AUTHORITY_POSTFIX;
    }

    private Set<FeatureFlagListener> getFeatureFlagListeners(String str, FeatureFlagListener featureFlagListener) {
        Set<FeatureFlagListener> set = this.featuresToNotify.get(str);
        if (set == null) {
            set = Collections.newSetFromMap(new ConcurrentHashMap());
        }
        set.add(featureFlagListener);
        return set;
    }

    private void query(@Nullable String str, String[] strArr) {
        Cursor query = getContentResolver().query(getFeatureFlagUri(), COLUMNS, str, strArr, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    this.isFeatureServiceReady.set(true);
                    this.cachedFeatureFlags.put(query.getString(query.getColumnIndexOrThrow("feature")), Boolean.valueOf(query.getString(query.getColumnIndex(COLUMN_IS_ENABLED))));
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            query.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
            for (String str2 : strArr) {
                this.cachedFeatureFlags.putIfAbsent(str2, Boolean.FALSE);
            }
        }
        if (query != null) {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refresh() {
        if (this.toRefreshAll.get()) {
            loadAll();
        } else {
            load(this.featuresToRefresh);
        }
    }

    private void registerDeleteAllContentObserver() {
        try {
            if (this.deleteAllContentObserver != null) {
                return;
            }
            this.deleteAllContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.amazon.alexa.feature.consumer.DefaultFeatureFlagConsumer.3
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri) {
                    super.onChange(z);
                    if (DefaultFeatureFlagConsumer.this.getDeleteAllUri().equals(uri)) {
                        DefaultFeatureFlagConsumer.this.clear();
                    }
                }
            };
            getContentResolver().registerContentObserver(getDeleteAllUri(), false, this.deleteAllContentObserver);
        } catch (SecurityException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void registerFeatureChangedContentObserver() {
        try {
            if (this.featureChangedContentObserver != null) {
                return;
            }
            this.featureChangedContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.amazon.alexa.feature.consumer.DefaultFeatureFlagConsumer.2
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri) {
                    super.onChange(z);
                    if (DefaultFeatureFlagConsumer.this.getFeatureFlagUri().equals(uri)) {
                        DefaultFeatureFlagConsumer.this.refresh();
                    }
                }
            };
            getContentResolver().registerContentObserver(getFeatureFlagUri(), false, this.featureChangedContentObserver);
        } catch (SecurityException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void unregisterDeleteAllContentObserver() {
        try {
            if (this.deleteAllContentObserver == null) {
                return;
            }
            getContentResolver().unregisterContentObserver(this.deleteAllContentObserver);
            this.deleteAllContentObserver = null;
        } catch (SecurityException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void unregisterFlagChangedContentObserver() {
        try {
            if (this.featureChangedContentObserver == null) {
                return;
            }
            getContentResolver().unregisterContentObserver(this.featureChangedContentObserver);
            this.featureChangedContentObserver = null;
        } catch (SecurityException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer
    public void enqueueFeatureFlagListener(String str, FeatureFlagListener featureFlagListener) {
        if (!this.toRefreshAll.get()) {
            this.featuresToRefresh.add(str);
        }
        if (checkFeatureServiceAvailability()) {
            featureFlagListener.onFeatureServiceReady(get(str));
            return;
        }
        this.featuresToNotify.put(str, getFeatureFlagListeners(str, featureFlagListener));
    }

    @VisibleForTesting
    ContentResolver getContentResolver() {
        return this.context.getContentResolver();
    }

    @VisibleForTesting
    ContentObserver getDeleteAllContentObserver() {
        return this.deleteAllContentObserver;
    }

    @VisibleForTesting
    Uri getDeleteAllUri() {
        if (deleteAllUri == null) {
            deleteAllUri = new Uri.Builder().scheme("content").authority(this.authority).appendPath(FEATURE_FLAGS_PATH).appendPath(DELETE_ALL_PATH).build();
        }
        return deleteAllUri;
    }

    @VisibleForTesting
    ContentObserver getFeatureChangedContentObserver() {
        return this.featureChangedContentObserver;
    }

    @VisibleForTesting
    Uri getFeatureFlagUri() {
        if (featureFlagsUri == null) {
            featureFlagsUri = new Uri.Builder().scheme("content").authority(this.authority).appendPath(FEATURE_FLAGS_PATH).build();
        }
        return featureFlagsUri;
    }

    @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer
    public FeatureQuery getFeatureQuery() {
        return this.featureQuery;
    }

    @VisibleForTesting
    ConcurrentHashMap<String, Set<FeatureFlagListener>> getFeaturesToNotify() {
        return this.featuresToNotify;
    }

    @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer
    public void initialize() {
        registerFeatureChangedContentObserver();
        registerDeleteAllContentObserver();
    }

    @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer
    public void load(Set<String> set) {
        this.featuresToRefresh.addAll(set);
        query(FEATURE_SELECTION, (String[]) set.toArray(TO_STRING_ARRAY));
        notifyListeners();
    }

    @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer
    public void loadAll() {
        this.cachedFeatureFlags.clear();
        this.toRefreshAll.set(true);
        query(FEATURE_ALL, FEATURE_ALL_SELECTION_ARGS);
        notifyListeners();
    }

    @VisibleForTesting
    void notifyListeners() {
        if (this.featuresToNotify.isEmpty() || !this.isFeatureServiceReady.get()) {
            return;
        }
        for (Map.Entry<String, Set<FeatureFlagListener>> entry : this.featuresToNotify.entrySet()) {
            String key = entry.getKey();
            Boolean bool = this.cachedFeatureFlags.get(key);
            boolean booleanValue = bool != null ? bool.booleanValue() : false;
            for (FeatureFlagListener featureFlagListener : entry.getValue()) {
                featureFlagListener.onFeatureServiceReady(booleanValue);
            }
            this.featuresToNotify.remove(key);
        }
    }

    @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer
    public void teardown() {
        unregisterDeleteAllContentObserver();
        unregisterFlagChangedContentObserver();
    }
}
