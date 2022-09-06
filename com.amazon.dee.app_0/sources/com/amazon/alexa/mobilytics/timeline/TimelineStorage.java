package com.amazon.alexa.mobilytics.timeline;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class TimelineStorage {
    public static final String PERSISTANCE_KEY_TL_KEY_SET = "tl_key_set";
    public static final String PERSISTANCE_KEY_TL_SESSION_SET = "tl_set";
    private final PersistentStorage sessionStorage;
    private final PersistentStorage timelineStorage;
    private final Map<String, TimelineEvent> map = new HashMap();
    @NonNull
    private final String tlSessionId = generateTimelineSessionId();

    @Inject
    public TimelineStorage(@NonNull PersistentStorage.Factory factory) {
        this.sessionStorage = ((PersistentStorage.Factory) Preconditions.checkNotNull(factory)).create(Constants.MOBILYTICS_SESSION_STORAGE);
        this.timelineStorage = ((PersistentStorage.Factory) Preconditions.checkNotNull(factory)).create(this.tlSessionId);
        storeTimelineSessionId(this.tlSessionId);
    }

    @NonNull
    private String generateTimelineSessionId() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mobilytics.timeline_");
        outline107.append(String.valueOf(System.currentTimeMillis()));
        return outline107.toString();
    }

    private void persistanceClear(String str) {
        Set<String> stringSet = this.timelineStorage.getStringSet(PERSISTANCE_KEY_TL_KEY_SET, null);
        if (stringSet != null && stringSet.contains(str)) {
            stringSet.remove(str);
        }
        PersistentStorage.Transaction edit = this.timelineStorage.edit();
        if (stringSet != null && !stringSet.isEmpty()) {
            edit.set(PERSISTANCE_KEY_TL_KEY_SET, stringSet);
        } else {
            edit.remove(PERSISTANCE_KEY_TL_KEY_SET);
        }
        edit.remove(str);
        edit.commit();
    }

    private void storeTimelineSessionId(@NonNull String str) {
        PersistentStorage persistentStorage;
        if (TextUtils.isEmpty(str) || (persistentStorage = this.sessionStorage) == null) {
            return;
        }
        Set<String> stringSet = persistentStorage.getStringSet(PERSISTANCE_KEY_TL_SESSION_SET, new LinkedHashSet());
        stringSet.add(str);
        this.sessionStorage.edit().remove(PERSISTANCE_KEY_TL_SESSION_SET).set(PERSISTANCE_KEY_TL_SESSION_SET, stringSet).commit();
    }

    public boolean doesExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.map.containsKey(str);
    }

    public TimelineEvent get(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return this.map.get(getKey(str, str2));
    }

    public String getCurrentTimelineSessionId() {
        return this.tlSessionId;
    }

    public Iterator<TimelineEvent> getIterator() {
        return this.map.values().iterator();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getKey(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return GeneratedOutlineSupport1.outline75(str, ".", str2);
    }

    public boolean isEmpty() {
        Map<String, TimelineEvent> map = this.map;
        return map == null || map.isEmpty();
    }

    public void persistanceWrite(TimelineEvent timelineEvent) {
        persistanceWrite(getKey(timelineEvent.timelineName(), timelineEvent.timelineNamespace()), timelineEvent);
    }

    public Set<String> readTimelineSessionSet() {
        PersistentStorage persistentStorage = this.sessionStorage;
        if (persistentStorage != null) {
            return persistentStorage.getStringSet(PERSISTANCE_KEY_TL_SESSION_SET, null);
        }
        return null;
    }

    public void remove(String str) {
        if (TextUtils.isEmpty(str) || !this.map.containsKey(str)) {
            return;
        }
        this.map.remove(str);
        persistanceClear(str);
    }

    public void saveSessionSet(Set<String> set) {
        PersistentStorage persistentStorage = this.sessionStorage;
        if (persistentStorage != null) {
            persistentStorage.edit().remove(PERSISTANCE_KEY_TL_SESSION_SET).set(PERSISTANCE_KEY_TL_SESSION_SET, set).commit();
        }
    }

    public int size() {
        Map<String, TimelineEvent> map = this.map;
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    public boolean store(String str, String str2, TimelineEvent timelineEvent) {
        if (timelineEvent == null) {
            return false;
        }
        String key = getKey(str, str2);
        if (TextUtils.isEmpty(key) || this.map.containsKey(key)) {
            return false;
        }
        this.map.put(key, timelineEvent);
        persistanceWrite(key, timelineEvent);
        return true;
    }

    private void persistanceWrite(String str, TimelineEvent timelineEvent) {
        Set<String> stringSet = this.timelineStorage.getStringSet(PERSISTANCE_KEY_TL_KEY_SET, null);
        if (stringSet == null) {
            stringSet = new LinkedHashSet<>();
        }
        if (!stringSet.contains(str)) {
            stringSet.add(str);
        }
        this.timelineStorage.edit().set(str, timelineEvent.serializeToMap()).set(PERSISTANCE_KEY_TL_KEY_SET, stringSet).commit();
    }

    public boolean doesExist(String str, String str2) {
        return doesExist(getKey(str, str2));
    }
}
