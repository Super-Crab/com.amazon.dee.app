package com.amazon.alexa.accessory.davs.i18n;

import android.content.Context;
import com.amazon.alexa.accessory.persistence.FileBackedJsonRxMapStore;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import java.util.Set;
/* loaded from: classes.dex */
public class DiskDavsI18nConfigStore implements DavsI18nConfigStore {
    private static final String DIRECTED_ID_JSON_KEY = "directedId";
    private static final String I18N_CONFIG_JSON_KEY = "i18nConfig";
    private static final String I18N_CONFIG_STORE_PATH = "accessoriesI18NConfig.json";
    private static final String USERS_JSON_KEY = "users";
    private final RxMapStore<String, DavsI18nConfigData> store;

    public DiskDavsI18nConfigStore(Context context) {
        this.store = new FileBackedJsonRxMapStore(new File(context.getFilesDir(), I18N_CONFIG_STORE_PATH), DavsI18nConfigData.FACTORY, USERS_JSON_KEY, "directedId", I18N_CONFIG_JSON_KEY);
    }

    @Override // com.amazon.alexa.accessory.davs.i18n.DavsI18nConfigStore
    public Single<Set<DavsI18nConfigData>> getDavsI18nConfig(String str) {
        return this.store.get(str);
    }

    @Override // com.amazon.alexa.accessory.davs.i18n.DavsI18nConfigStore
    public Single<DavsI18nConfigData> putDavsI18nConfig(String str, DavsI18nConfigData davsI18nConfigData) {
        return this.store.put(str, davsI18nConfigData);
    }
}
