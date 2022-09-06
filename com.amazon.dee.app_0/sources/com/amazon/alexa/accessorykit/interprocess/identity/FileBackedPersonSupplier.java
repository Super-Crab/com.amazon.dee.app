package com.amazon.alexa.accessorykit.interprocess.identity;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.persistence.FileBackedJsonRxMapStore;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Supplier;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class FileBackedPersonSupplier implements PersonSupplier {
    private static final String CURRENT_PERSON_KEY = "currentPerson";
    @VisibleForTesting
    static final String PERSON_STORE_FILE_PATH = "/accessories/personStore.json";
    private static final String TAG = "FileBackedPersonSupplier: ";
    private static RxMapStore<String, Person> mostRecentPersonStore;
    private final Context context;

    public FileBackedPersonSupplier(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Single<Person> commitPerson(@NonNull Context context, @NonNull Person person) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(person, ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR);
        return getStore(context).put(CURRENT_PERSON_KEY, person);
    }

    private static synchronized RxMapStore<String, Person> getStore(Context context) {
        RxMapStore<String, Person> rxMapStore;
        synchronized (FileBackedPersonSupplier.class) {
            Preconditions.notNull(context, "context");
            if (mostRecentPersonStore == null) {
                mostRecentPersonStore = new FileBackedJsonRxMapStore(new File(context.getFilesDir(), PERSON_STORE_FILE_PATH), Person.FACTORY, "personStore", "personSets", ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR);
            }
            rxMapStore = mostRecentPersonStore;
        }
        return rxMapStore;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Person lambda$null$0(Map map) throws Throwable {
        Set set = (Set) map.get(CURRENT_PERSON_KEY);
        if (set == null) {
            return Person.ABSENT;
        }
        Iterator it2 = set.iterator();
        if (!it2.hasNext()) {
            return Person.ABSENT;
        }
        return (Person) it2.next();
    }

    @VisibleForTesting
    static void resetStoreForTest() {
        mostRecentPersonStore = null;
    }

    public /* synthetic */ ObservableSource lambda$queryPerson$2$FileBackedPersonSupplier() throws Throwable {
        return getStore(this.context).queryValues().map($$Lambda$FileBackedPersonSupplier$VDTNA6CaMeh09fv5ZqlJd1yfnn0.INSTANCE).onErrorReturn($$Lambda$FileBackedPersonSupplier$UdsEQUd3lEiRbIbSZwGYU86qxI.INSTANCE);
    }

    @Override // com.amazon.alexa.accessorykit.interprocess.identity.PersonSupplier
    public Observable<Person> queryPerson() {
        return Observable.defer(new Supplier() { // from class: com.amazon.alexa.accessorykit.interprocess.identity.-$$Lambda$FileBackedPersonSupplier$Nh7LC4_dd3gNbvHi4aJu8GyDbec
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FileBackedPersonSupplier.this.lambda$queryPerson$2$FileBackedPersonSupplier();
            }
        });
    }
}
