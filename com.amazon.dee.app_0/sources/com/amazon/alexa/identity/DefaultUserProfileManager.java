package com.amazon.alexa.identity;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.CommsProfile;
import com.amazon.alexa.identity.api.ProfileAttributes;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.identity.api.UserProfileManager;
import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import dagger.Lazy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/* loaded from: classes9.dex */
public class DefaultUserProfileManager implements UserProfileManager {
    private static final String TAG = Utils.tag(DefaultUserProfileManager.class);
    private final CommsManager commsManager;
    private final Lazy<CommsServiceV2> commsServiceV2Lazy;

    public DefaultUserProfileManager(CommsManager commsManager, @NonNull Lazy<CommsServiceV2> lazy) {
        this.commsManager = commsManager;
        this.commsServiceV2Lazy = lazy;
    }

    @Override // com.amazon.alexa.identity.api.UserProfileManager
    public UserProfile getCurrentProfile() {
        Log.i(TAG, "IdentityV2 : Getting current profile from Comms.");
        if (!this.commsServiceV2Lazy.mo358get().oobeService().hasSelectedProfile()) {
            return null;
        }
        UserProfile.Builder withLastName = UserProfile.builder().withDirectedId(this.commsManager.getDirectedId()).withFirstName(this.commsManager.getFirstName()).withLastName(this.commsManager.getLastName());
        CommsIdentity commsIdentity = this.commsManager.getCommsIdentity();
        if (commsIdentity != null && !TextUtils.isEmpty(commsIdentity.getCommsId())) {
            return withLastName.withCommsProfile(CommsProfile.builder().withCommsId(commsIdentity.getCommsId()).withPhoneNumber(commsIdentity.getPhoneNumber()).withAor(commsIdentity.getAor()).withHashedCommsId(commsIdentity.getHashedCommsId()).withHouseholdId(commsIdentity.getHomeGroupId()).withEmail(commsIdentity.getEmail()).build()).build();
        }
        return withLastName.build();
    }

    @Override // com.amazon.alexa.identity.api.UserProfileManager
    public UserProfile getCurrentProfile(String str) {
        Log.i(TAG, "Getting current profile from payload.");
        try {
            JSONObject jSONObject = (JSONObject) new JSONParser().parse(str);
            UserProfile.Builder withPersonId = UserProfile.builder().withDirectedId((String) jSONObject.get("directedId")).withFirstName((String) jSONObject.get("firstName")).withLastName((String) jSONObject.get("lastName")).withPersonId((String) jSONObject.get(ProfileAttributes.PERSON_ID));
            if (TextUtils.isEmpty((String) jSONObject.get("commsId")) && TextUtils.isEmpty((String) jSONObject.get("phoneNumber"))) {
                return withPersonId.build();
            }
            return withPersonId.withCommsProfile(CommsProfile.builder().withCommsId((String) jSONObject.get("commsId")).withPhoneNumber((String) jSONObject.get("phoneNumber")).build()).build();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Exception occurred while retrieving profile from payload.", e);
        }
    }
}
