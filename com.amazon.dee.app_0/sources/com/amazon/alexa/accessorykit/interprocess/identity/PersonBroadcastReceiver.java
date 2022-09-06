package com.amazon.alexa.accessorykit.interprocess.identity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import io.reactivex.rxjava3.functions.Consumer;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class PersonBroadcastReceiver extends BroadcastReceiver {
    static final String PERSON_PAYLOAD_KEY = "personPayload";
    private static final String TAG = "PersonSupplierReceiver:";
    static final String PERSON_CHANGED_INTENT_ACTION = "com.amazon.alexa.accessorykit.action.personchanged";
    static final Intent PERSON_CHANGED_BASE_INTENT = new Intent(PERSON_CHANGED_INTENT_ACTION).setPackage(AccessoriesFactory.getAppName());

    @VisibleForTesting
    static Person getPersonFromIntent(@NonNull Intent intent) {
        String stringExtra = intent.getStringExtra(PERSON_PAYLOAD_KEY);
        if (stringExtra != null) {
            try {
                return Person.FACTORY.mo1239create(new JSONObject(stringExtra));
            } catch (JSONException e) {
                throw new IllegalStateException("Could not parse Person", e);
            }
        }
        throw new IllegalStateException("Could not parse Person, string payload was null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$persistPerson$0(BroadcastReceiver.PendingResult pendingResult, Person person) throws Throwable {
        Logger.d("%s successfully received and persisted person %s", TAG, person);
        pendingResult.finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$persistPerson$1(Person person, BroadcastReceiver.PendingResult pendingResult, Throwable th) throws Throwable {
        Logger.e("%s failed to persist person %s", th, TAG, person);
        pendingResult.finish();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!PERSON_CHANGED_INTENT_ACTION.equals(intent.getAction())) {
            return;
        }
        persistPerson(intent, goAsync(), context);
    }

    @SuppressLint({"CheckResult"})
    @VisibleForTesting
    void persistPerson(@NonNull Intent intent, @NonNull final BroadcastReceiver.PendingResult pendingResult, @NonNull Context context) {
        Preconditions.notNull(intent, MAPAccountManager.KEY_INTENT);
        Preconditions.notNull(pendingResult, "pendingResult");
        Preconditions.notNull(context, "context");
        final Person personFromIntent = getPersonFromIntent(intent);
        FileBackedPersonSupplier.commitPerson(context, personFromIntent).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.identity.-$$Lambda$PersonBroadcastReceiver$JptdU-PKMMLddu9eXsEGPrz4M6U
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PersonBroadcastReceiver.lambda$persistPerson$0(pendingResult, (Person) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.identity.-$$Lambda$PersonBroadcastReceiver$ca8QJeGXxE4GCBBpCpqwtPB1r88
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PersonBroadcastReceiver.lambda$persistPerson$1(Person.this, pendingResult, (Throwable) obj);
            }
        });
    }
}
