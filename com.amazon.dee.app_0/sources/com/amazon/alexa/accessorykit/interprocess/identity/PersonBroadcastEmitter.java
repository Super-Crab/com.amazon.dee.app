package com.amazon.alexa.accessorykit.interprocess.identity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import io.reactivex.rxjava3.functions.Consumer;
import org.json.JSONException;
/* loaded from: classes6.dex */
public class PersonBroadcastEmitter {
    @VisibleForTesting
    static final String CALLING_CLASS = "com.amazon.alexa.accessorykit.interprocess.identity.PersonBroadcastEmitter";
    private static final String TAG = "PersonBroadcastEmitter:";
    private boolean active = false;
    private final Context context;
    private final IdentityService identityService;
    private final Object lock;
    private final UserSupplier userSupplier;

    public PersonBroadcastEmitter(@NonNull Context context, @NonNull UserSupplier userSupplier, @NonNull IdentityService identityService) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(identityService, "identityService");
        this.context = context;
        this.userSupplier = userSupplier;
        this.identityService = identityService;
        this.lock = new Object();
    }

    private static Intent createPersonChangedIntent(@NonNull Person person, @NonNull Context context) throws JSONException {
        return new Intent(PersonBroadcastReceiver.PERSON_CHANGED_BASE_INTENT).setClass(context, PersonBroadcastReceiver.class).putExtra("personPayload", person.toJsonObject().toString());
    }

    private static Person getPersonForIdentity(@NonNull IdentityService identityService) {
        UserIdentity user = identityService.getUser(CALLING_CLASS);
        if (user == null) {
            return Person.ABSENT;
        }
        UserProfile userProfile = user.getUserProfile();
        if (userProfile == null) {
            return Person.ABSENT;
        }
        if (TextUtils.isEmpty(userProfile.getDirectedId())) {
            return Person.ABSENT;
        }
        String directedId = userProfile.getDirectedId();
        String str = null;
        String hashedCommsId = userProfile.getCommsProfile() != null ? userProfile.getCommsProfile().getHashedCommsId() : null;
        if (userProfile.getCommsProfile() != null) {
            str = userProfile.getCommsProfile().getHouseholdId();
        }
        return new Person(directedId, hashedCommsId, str);
    }

    @SuppressLint({"CheckResult"})
    public PersonBroadcastEmitter activate() {
        synchronized (this.lock) {
            if (this.active) {
                return this;
            }
            this.active = true;
            Logger.d("%s activate()", TAG);
            this.userSupplier.queryUser().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.identity.-$$Lambda$PersonBroadcastEmitter$w-wjGKshhSmunC54z1G69LwsZwk
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    PersonBroadcastEmitter.this.lambda$activate$0$PersonBroadcastEmitter((User) obj);
                }
            });
            return this;
        }
    }

    public /* synthetic */ void lambda$activate$0$PersonBroadcastEmitter(User user) throws Throwable {
        Person personForIdentity = getPersonForIdentity(this.identityService);
        Logger.d("%s Emitting current Person: %s", TAG, personForIdentity);
        Context context = this.context;
        context.sendBroadcast(createPersonChangedIntent(personForIdentity, context), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }
}
