package com.amazon.alexa.presence.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.alexa.presence.bleconn.identity.model.IdentityRepo;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class PersistentMap implements Map<String, String> {
    private static final String DEFAULT_PREF_STORAGE_LOCATION = "DEFAULT_STORE";
    private static final Logger LOG = LoggerFactory.getLogger(IdentityRepo.class);
    private final Context context;

    public PersistentMap(Context context) {
        this.context = context;
    }

    private SharedPreferences storage() {
        return this.context.getSharedPreferences(DEFAULT_PREF_STORAGE_LOCATION, 4);
    }

    @Override // java.util.Map
    public void clear() {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, String>> entrySet() {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends String> map) {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public int size() {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public Collection<String> values() {
        throw new RuntimeException("Not Implemented");
    }

    @Override // java.util.Map
    public String get(Object obj) {
        String string = storage().getString(obj.toString(), null);
        LOG.debug("Loading %s = %s", obj, string);
        return string;
    }

    @Override // java.util.Map
    public String put(String str, String str2) {
        LOG.debug("Storing %s = %s", str, str2);
        SharedPreferences.Editor edit = storage().edit();
        edit.putString(str, str2);
        int i = 0;
        while (true) {
            if (i >= 5) {
                break;
            } else if (edit.commit()) {
                LOG.debug("%s committed", str);
                break;
            } else {
                LOG.debug("Commit failed");
                i++;
            }
        }
        return str2;
    }

    @Override // java.util.Map
    public String remove(Object obj) {
        LOG.debug("Removing %s", obj);
        String str = get(obj);
        SharedPreferences.Editor edit = storage().edit();
        edit.remove(obj.toString());
        edit.apply();
        return str;
    }
}
