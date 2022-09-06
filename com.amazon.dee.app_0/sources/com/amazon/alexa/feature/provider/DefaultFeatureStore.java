package com.amazon.alexa.feature.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.feature.provider.api.FeatureStore;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.api.PlatformFeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.tasks.api.TaskManager;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public class DefaultFeatureStore implements FeatureStore {
    private static final String AUTHORITY_POSTFIX = ".alexa.feature";
    @VisibleForTesting
    static final String[] COLUMNS = {"feature"};
    @VisibleForTesting
    static final String COLUMN_FEATURE = "feature";
    @VisibleForTesting
    static final String COLUMN_IS_ENABLED = "isEnabled";
    public static final String FEATURES_UPDATED_INTERNAL_EVENT = "featureServiceV2:internal:featuresUpdated";
    private static final String FEATURE_FLAGS_PATH = "featureflags";
    @VisibleForTesting
    static final String FEATURE_SELECTION = "feature = ?";
    private static final String SCHEME = "content";
    private static final String TAG = "DefaultFeatureStore";
    private static volatile Uri featureFlagsUri;
    private final String authority;
    private final Context context;
    private final Provider<EventBus> eventBus;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private MultiFilterSubscriber.FilterUuid fsv2EventSubscriberUuid;
    private final Provider<IdentityService> identityServiceProvider;
    private Subscriber.SubscriberUuid subscriberUuid;
    private final Provider<TaskManager> taskManager;
    private final Object writeLock = new Object();
    private final Gson gson = new Gson();

    public DefaultFeatureStore(ComponentGetter componentGetter, Context context) {
        this.context = context;
        this.authority = getAuthority(context);
        this.eventBus = componentGetter.get(EventBus.class);
        this.taskManager = componentGetter.get(TaskManager.class);
        this.identityServiceProvider = componentGetter.get(IdentityService.class);
        this.featureServiceV2Provider = componentGetter.get(FeatureServiceV2.class);
    }

    private void bulkInsert(Iterable<String> iterable, boolean z) {
        try {
            ArrayList arrayList = new ArrayList();
            for (String str : iterable) {
                arrayList.add(createFeatureFlagContentValues(str, z));
            }
            synchronized (this.writeLock) {
                if (getContentResolver() != null) {
                    getContentResolver().bulkInsert(getFeatureFlagUri(), (ContentValues[]) arrayList.toArray(new ContentValues[0]));
                } else {
                    Log.i(TAG, "ContentResolver is null");
                }
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void clearFeatures() {
        this.taskManager.mo10268get().getExecutor(0).execute(new Runnable() { // from class: com.amazon.alexa.feature.provider.-$$Lambda$aqy6gO4sJbt3SJShJT8MVSqhTnU
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFeatureStore.this.delete();
            }
        });
    }

    private ContentValues createFeatureFlagContentValues(String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("feature", str);
        contentValues.put(COLUMN_IS_ENABLED, Boolean.valueOf(z));
        return contentValues;
    }

    private static String getAuthority(Context context) {
        return context.getPackageName() + AUTHORITY_POSTFIX;
    }

    private Set<String> getFsv2Features() {
        HashSet hashSet = new HashSet();
        if (this.featureServiceV2Provider.mo10268get() instanceof PlatformFeatureServiceV2) {
            for (Map.Entry entry : ((Map) this.gson.fromJson(((PlatformFeatureServiceV2) this.featureServiceV2Provider.mo10268get()).getSerializedFeatureCache(), new TypeToken<Map<String, Feature>>() { // from class: com.amazon.alexa.feature.provider.DefaultFeatureStore.1
            }.getType())).entrySet()) {
                if (!((Feature) entry.getValue()).getTreatment().equals("C")) {
                    hashSet.add(entry.getKey());
                }
            }
        }
        return hashSet;
    }

    private void insertFeatures(final Iterable<String> iterable) {
        this.taskManager.mo10268get().getExecutor(0).execute(new Runnable() { // from class: com.amazon.alexa.feature.provider.-$$Lambda$DefaultFeatureStore$bK5bu89iE4FRQjwkrpzBfbpyZy4
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFeatureStore.this.lambda$insertFeatures$2$DefaultFeatureStore(iterable);
            }
        });
    }

    @Override // com.amazon.alexa.feature.provider.api.FeatureStore
    public void delete() {
        try {
            synchronized (this.writeLock) {
                if (getContentResolver() != null) {
                    getContentResolver().delete(getFeatureFlagUri(), null, null);
                } else {
                    Log.i(TAG, "ContentResolver is null");
                }
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @VisibleForTesting
    ContentResolver getContentResolver() {
        return this.context.getContentResolver();
    }

    @VisibleForTesting
    Uri getFeatureFlagUri() {
        if (featureFlagsUri == null) {
            featureFlagsUri = new Uri.Builder().scheme("content").authority(this.authority).appendPath(FEATURE_FLAGS_PATH).build();
        }
        return featureFlagsUri;
    }

    @Override // com.amazon.alexa.feature.provider.api.FeatureStore
    public void initialize() {
        MultiFilterSubscriber subscriber = this.eventBus.mo10268get().getSubscriber();
        this.subscriberUuid = subscriber.getSubscriberUuid();
        subscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.feature.provider.-$$Lambda$DefaultFeatureStore$3TvwxVRzrKRWqPhe2npb_b3t0T4
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultFeatureStore.this.lambda$initialize$0$DefaultFeatureStore(message);
            }
        });
        this.fsv2EventSubscriberUuid = this.eventBus.mo10268get().getNewSubscriber().subscribeFilter(new EventTypeMessageFilter("featureServiceV2:internal:featuresUpdated"), new MessageHandler() { // from class: com.amazon.alexa.feature.provider.-$$Lambda$DefaultFeatureStore$OMpAaXnCuqB0TM4KVri1D5rOFN8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultFeatureStore.this.lambda$initialize$1$DefaultFeatureStore(message);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$0$DefaultFeatureStore(Message message) {
        updateFeatures();
    }

    public /* synthetic */ void lambda$initialize$1$DefaultFeatureStore(Message message) {
        updateFeatures();
    }

    @Override // com.amazon.alexa.feature.provider.api.FeatureStore
    /* renamed from: store */
    public void lambda$insertFeatures$2$DefaultFeatureStore(Iterable<String> iterable) {
        HashSet hashSet = new HashSet();
        for (String str : iterable) {
            hashSet.add(str);
        }
        HashSet hashSet2 = new HashSet();
        Cursor query = getContentResolver().query(getFeatureFlagUri(), COLUMNS, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    String string = query.getString(query.getColumnIndex("feature"));
                    if (!hashSet.contains(string)) {
                        hashSet2.add(string);
                    }
                } finally {
                }
            }
        }
        if (query != null) {
            query.close();
        }
        if (hashSet2.size() > 0) {
            try {
                getContentResolver().delete(getFeatureFlagUri(), FEATURE_SELECTION, (String[]) hashSet2.toArray(new String[0]));
            } catch (IllegalArgumentException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        bulkInsert(iterable, true);
    }

    @VisibleForTesting
    void updateFeatures() {
        UserIdentity user = this.identityServiceProvider.mo10268get().getUser(TAG);
        if (user == null) {
            clearFeatures();
            return;
        }
        Set<String> fsv2Features = getFsv2Features();
        fsv2Features.addAll(user.getFeatures());
        insertFeatures(fsv2Features);
    }
}
