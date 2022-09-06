package com.amazon.alexa.identity;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.identity.api.CommsProfile;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserIdentityStorage;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
/* loaded from: classes9.dex */
public class PersistentUserIdentityStorage implements UserIdentityStorage {
    @VisibleForTesting
    static final String CURRENT_SCHEMA_VERSION = "5";
    private static final String TAG = Utils.tag(PersistentUserIdentityStorage.class);
    private static final String USER_STORAGE_NAMESPACE = "service.identity";
    private final PersistentStorage storage;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface StorageKey {
        public static final String COMMS_AOR = "user.profile.comms.aor";
        public static final String COMMS_COMMS_ID = "user.profile.comms.commsId";
        public static final String COMMS_EMAIL = "user.profile.comms.email";
        public static final String COMMS_HASHED_COMMS_ID = "user.profile.comms.hashedCommsId";
        public static final String COMMS_HOUSEHOLD_ID = "user.profile.comms.householdId";
        public static final String COMMS_PHONE_NUMBER = "user.profile.comms.phoneNumber";
        public static final String DELEGATED_PROFILE_DIRECTED_ID = "user.delegatedProfile.directedId";
        public static final String DELEGATED_PROFILE_FIRST_NAME = "user.delegatedProfile.firstName";
        public static final String DELEGATED_PROFILE_LAST_NAME = "user.delegatedProfile.lastName";
        public static final String DELEGATED_PROFILE_PERSON_ID = "user.delegatedProfile.personId";
        public static final String PROFILE_DIRECTED_ID = "user.profile.directedId";
        public static final String PROFILE_FIRST_NAME = "user.profile.firstName";
        public static final String PROFILE_HAS_COMMS = "user.profile.hasComms";
        public static final String PROFILE_LAST_NAME = "user.profile.lastName";
        public static final String PROFILE_PERSON_ID = "user.profile.personId";
        public static final String SCHEMA_VERSION = "user.[version]";
        public static final String USER_ACCESS_TOKEN = "user.accessToken";
        public static final String USER_COUNTRY_OF_RESIDENCE = "user.countryOfResidence";
        public static final String USER_DIRECTED_ID = "user.directedId";
        public static final String USER_EFFECTIVE_MARKETPLACE = "user.effectiveMarketplace";
        public static final String USER_EMAIL = "user.email";
        public static final String USER_EULA_ACCEPTED = "user.eulaAccepted";
        public static final String USER_FEATURES = "user.features";
        public static final String USER_HAS_DELEGATED_PROFILE = "user.hasDelegatedProfile";
        public static final String USER_HAS_DEVICES = "user.hasDevices";
        public static final String USER_HAS_PROFILE = "user.hasProfile";
        public static final String USER_ID = "user.id";
        public static final String USER_MARKETPLACE = "user.marketplace";
        public static final String USER_NAME = "user.name";
        public static final String USER_ORIGINAL_MARKETPLACE = "user.originalMarketplace";
        public static final String USER_TOKENS = "user.tokens";
    }

    public PersistentUserIdentityStorage(@NonNull PersistentStorage.Factory factory) {
        this.storage = factory.create(USER_STORAGE_NAMESPACE);
    }

    private Marketplace getMarketplace(String str, @Nullable Marketplace marketplace) {
        return Marketplace.findMarketplaceById(this.storage.getString(str), marketplace);
    }

    private static String marketplaceIdStringFrom(@Nullable Marketplace marketplace) {
        if (marketplace != null) {
            return marketplace.getObfuscatedId().toString();
        }
        return null;
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityStorage
    public void clear() {
        Log.i(TAG, "Clearing User");
        this.storage.edit().remove(StorageKey.SCHEMA_VERSION).remove(StorageKey.USER_ACCESS_TOKEN).remove(StorageKey.USER_COUNTRY_OF_RESIDENCE).remove(StorageKey.USER_DIRECTED_ID).remove(StorageKey.USER_EFFECTIVE_MARKETPLACE).remove(StorageKey.USER_EMAIL).remove(StorageKey.USER_EULA_ACCEPTED).remove(StorageKey.USER_FEATURES).remove(StorageKey.USER_HAS_DEVICES).remove(StorageKey.USER_ID).remove(StorageKey.USER_MARKETPLACE).remove(StorageKey.USER_NAME).remove(StorageKey.USER_ORIGINAL_MARKETPLACE).remove(StorageKey.USER_TOKENS).remove(StorageKey.USER_HAS_PROFILE).remove(StorageKey.PROFILE_DIRECTED_ID).remove(StorageKey.PROFILE_FIRST_NAME).remove(StorageKey.PROFILE_LAST_NAME).remove(StorageKey.PROFILE_PERSON_ID).remove(StorageKey.PROFILE_HAS_COMMS).remove(StorageKey.COMMS_AOR).remove(StorageKey.COMMS_COMMS_ID).remove(StorageKey.COMMS_EMAIL).remove(StorageKey.COMMS_HASHED_COMMS_ID).remove(StorageKey.COMMS_HOUSEHOLD_ID).remove(StorageKey.COMMS_PHONE_NUMBER).remove(StorageKey.USER_HAS_DELEGATED_PROFILE).remove(StorageKey.DELEGATED_PROFILE_DIRECTED_ID).remove(StorageKey.DELEGATED_PROFILE_PERSON_ID).remove(StorageKey.DELEGATED_PROFILE_FIRST_NAME).remove(StorageKey.DELEGATED_PROFILE_LAST_NAME).commit();
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityStorage
    @Nullable
    public UserIdentity get() {
        if (this.storage.contains(StorageKey.USER_ID) && CURRENT_SCHEMA_VERSION.equals(this.storage.getString(StorageKey.SCHEMA_VERSION))) {
            Utils.safeFormat("Retrieving User: %s", this.storage.getString(StorageKey.USER_ID));
            UserIdentity.Builder withTokens = UserIdentity.builder().withAccessToken(this.storage.getString(StorageKey.USER_ACCESS_TOKEN)).withCountryOfResidence(this.storage.getString(StorageKey.USER_COUNTRY_OF_RESIDENCE)).withDirectedId(this.storage.getString(StorageKey.USER_DIRECTED_ID)).withEffectiveMarketplace(getMarketplace(StorageKey.USER_EFFECTIVE_MARKETPLACE, Marketplace.USA)).withEmail(this.storage.getString(StorageKey.USER_EMAIL)).hasAcceptedEula(this.storage.getBoolean(StorageKey.USER_EULA_ACCEPTED)).withFeatures(this.storage.getStringSet(StorageKey.USER_FEATURES, Collections.emptySet())).hasActiveDevices(this.storage.getBoolean(StorageKey.USER_HAS_DEVICES)).withId(this.storage.getString(StorageKey.USER_ID)).withMarketplace(getMarketplace(StorageKey.USER_MARKETPLACE, Marketplace.USA)).withName(this.storage.getString(StorageKey.USER_NAME)).withOriginalMarketplace(getMarketplace(StorageKey.USER_ORIGINAL_MARKETPLACE, null)).withTokens(this.storage.getStringMap(StorageKey.USER_TOKENS, Collections.emptyMap()));
            if (this.storage.getBoolean(StorageKey.USER_HAS_PROFILE, false)) {
                UserProfile.Builder withPersonId = UserProfile.builder().withDirectedId(this.storage.getString(StorageKey.PROFILE_DIRECTED_ID)).withFirstName(this.storage.getString(StorageKey.PROFILE_FIRST_NAME)).withLastName(this.storage.getString(StorageKey.PROFILE_LAST_NAME)).withPersonId(this.storage.getString(StorageKey.PROFILE_PERSON_ID));
                if (this.storage.getBoolean(StorageKey.PROFILE_HAS_COMMS, false)) {
                    withPersonId.withCommsProfile(CommsProfile.builder().withAor(this.storage.getString(StorageKey.COMMS_AOR)).withCommsId(this.storage.getString(StorageKey.COMMS_COMMS_ID)).withEmail(this.storage.getString(StorageKey.COMMS_EMAIL)).withHashedCommsId(this.storage.getString(StorageKey.COMMS_HASHED_COMMS_ID)).withHouseholdId(this.storage.getString(StorageKey.COMMS_HOUSEHOLD_ID)).withPhoneNumber(this.storage.getString(StorageKey.COMMS_PHONE_NUMBER)).build());
                }
                withTokens.withUserProfile(withPersonId.build());
            }
            if (this.storage.getBoolean(StorageKey.USER_HAS_DELEGATED_PROFILE, false)) {
                withTokens.withDelegatedProfile(UserProfile.builder().withDirectedId(this.storage.getString(StorageKey.DELEGATED_PROFILE_DIRECTED_ID)).withPersonId(this.storage.getString(StorageKey.DELEGATED_PROFILE_PERSON_ID)).withFirstName(this.storage.getString(StorageKey.DELEGATED_PROFILE_FIRST_NAME)).withLastName(this.storage.getString(StorageKey.DELEGATED_PROFILE_LAST_NAME)).build());
            }
            return withTokens.build();
        }
        return null;
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityStorage
    @Nullable
    public String getId() {
        return this.storage.getString(StorageKey.USER_ID);
    }

    @Override // com.amazon.alexa.identity.api.UserIdentityStorage
    public void put(@Nullable UserIdentity userIdentity) {
        if (userIdentity == null) {
            clear();
            return;
        }
        Utils.safeFormat("Saving user: %s", userIdentity.getId());
        PersistentStorage.Transaction transaction = this.storage.edit().set(StorageKey.SCHEMA_VERSION, CURRENT_SCHEMA_VERSION);
        transaction.set(StorageKey.USER_ACCESS_TOKEN, userIdentity.getAccessToken()).set(StorageKey.USER_COUNTRY_OF_RESIDENCE, userIdentity.getCountryOfResidence()).set(StorageKey.USER_DIRECTED_ID, userIdentity.getDirectedId()).set(StorageKey.USER_EFFECTIVE_MARKETPLACE, marketplaceIdStringFrom(userIdentity.getEffectiveMarketplace())).set(StorageKey.USER_EMAIL, userIdentity.getEmail()).set(StorageKey.USER_EULA_ACCEPTED, userIdentity.hasAcceptedEula()).set(StorageKey.USER_FEATURES, userIdentity.getFeatures()).set(StorageKey.USER_HAS_DEVICES, userIdentity.hasActiveDevices()).set(StorageKey.USER_ID, userIdentity.getId()).set(StorageKey.USER_MARKETPLACE, marketplaceIdStringFrom(userIdentity.getMarketplace())).set(StorageKey.USER_NAME, userIdentity.getName()).set(StorageKey.USER_ORIGINAL_MARKETPLACE, marketplaceIdStringFrom(userIdentity.getOriginalMarketplace())).set(StorageKey.USER_TOKENS, userIdentity.getTokens()).set(StorageKey.USER_HAS_PROFILE, false).set(StorageKey.USER_HAS_DELEGATED_PROFILE, false);
        UserProfile userProfile = userIdentity.getUserProfile();
        if (userProfile != null) {
            transaction.set(StorageKey.USER_HAS_PROFILE, true).set(StorageKey.PROFILE_DIRECTED_ID, userProfile.getDirectedId()).set(StorageKey.PROFILE_FIRST_NAME, userProfile.getFirstName()).set(StorageKey.PROFILE_LAST_NAME, userProfile.getLastName()).set(StorageKey.PROFILE_PERSON_ID, userProfile.getPersonId()).set(StorageKey.PROFILE_HAS_COMMS, false);
            CommsProfile commsProfile = userProfile.getCommsProfile();
            if (commsProfile != null) {
                transaction.set(StorageKey.PROFILE_HAS_COMMS, true).set(StorageKey.COMMS_AOR, commsProfile.getAor()).set(StorageKey.COMMS_COMMS_ID, commsProfile.getCommsId()).set(StorageKey.COMMS_EMAIL, commsProfile.getEmail()).set(StorageKey.COMMS_HASHED_COMMS_ID, commsProfile.getHashedCommsId()).set(StorageKey.COMMS_HOUSEHOLD_ID, commsProfile.getHouseholdId()).set(StorageKey.COMMS_PHONE_NUMBER, commsProfile.getPhoneNumber());
            }
        }
        UserProfile delegatedProfile = userIdentity.getDelegatedProfile();
        if (delegatedProfile != null) {
            transaction.set(StorageKey.USER_HAS_DELEGATED_PROFILE, true).set(StorageKey.DELEGATED_PROFILE_DIRECTED_ID, delegatedProfile.getDirectedId()).set(StorageKey.DELEGATED_PROFILE_PERSON_ID, delegatedProfile.getPersonId()).set(StorageKey.DELEGATED_PROFILE_FIRST_NAME, delegatedProfile.getFirstName()).set(StorageKey.DELEGATED_PROFILE_LAST_NAME, delegatedProfile.getLastName());
        }
        transaction.commit();
    }
}
