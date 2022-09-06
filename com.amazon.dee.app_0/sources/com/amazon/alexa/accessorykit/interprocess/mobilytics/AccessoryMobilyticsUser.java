package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.interprocess.identity.Person;
import com.amazon.alexa.accessorykit.interprocess.mobilytics.AccessoryMobilyticsUserProvider;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class AccessoryMobilyticsUser implements MobilyticsUser {
    public static final AccessoryMobilyticsUser ABSENT = new AccessoryMobilyticsUser("Unknown", "Unknown", Person.ABSENT, $$Lambda$AccessoryMobilyticsUser$n1huZlLYHcChbMh9s_r51AdeoxU.INSTANCE);
    private final String directedId;
    private final AccessoryMobilyticsUserProvider.StringFeatureChecker featureChecker;
    private final String hashedCommsId;
    private final String householdCommsId;
    private final String marketplaceId;
    private final String personId;

    /* renamed from: com.amazon.alexa.accessorykit.interprocess.mobilytics.AccessoryMobilyticsUser$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$mobilytics$identity$MobilyticsUser$Attribute = new int[MobilyticsUser.Attribute.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$identity$MobilyticsUser$Attribute[MobilyticsUser.Attribute.HASHED_COMMS_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$mobilytics$identity$MobilyticsUser$Attribute[MobilyticsUser.Attribute.HOUSEHOLD_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public AccessoryMobilyticsUser(@NonNull String str, @NonNull String str2, @NonNull Person person, @NonNull AccessoryMobilyticsUserProvider.StringFeatureChecker stringFeatureChecker) {
        Preconditions.notNull(str, "directedId");
        Preconditions.notNull(str2, WebConstants.WebviewConstants.MARKETPLACE_ID);
        Preconditions.notNull(person, ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR);
        Preconditions.notNull(stringFeatureChecker, "featureChecker");
        if (str.isEmpty()) {
            this.directedId = "Unknown";
        } else {
            this.directedId = str;
        }
        if (str2.isEmpty()) {
            this.marketplaceId = "Unknown";
        } else {
            this.marketplaceId = str2;
        }
        if (person.personId.isEmpty()) {
            this.personId = "Unknown";
        } else {
            this.personId = person.personId;
        }
        String str3 = person.househouldCommsId;
        if (str3 != null && !str3.isEmpty()) {
            this.householdCommsId = person.househouldCommsId;
        } else {
            this.householdCommsId = "Unknown";
        }
        String str4 = person.hashedCommsId;
        if (str4 != null && !str4.isEmpty()) {
            this.hashedCommsId = person.hashedCommsId;
        } else {
            this.hashedCommsId = "Unknown";
        }
        this.featureChecker = stringFeatureChecker;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(String str) {
        return false;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @Nullable
    public String attribute(MobilyticsUser.Attribute attribute) {
        int ordinal = attribute.ordinal();
        if (ordinal != 0) {
            return ordinal != 1 ? "Unknown" : this.householdCommsId;
        }
        return this.hashedCommsId;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String directedId() {
        return this.directedId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AccessoryMobilyticsUser.class != obj.getClass()) {
            return false;
        }
        AccessoryMobilyticsUser accessoryMobilyticsUser = (AccessoryMobilyticsUser) obj;
        return this.directedId.equals(accessoryMobilyticsUser.directedId) && this.marketplaceId.equals(accessoryMobilyticsUser.marketplaceId) && this.personId.equals(accessoryMobilyticsUser.personId) && this.hashedCommsId.equals(accessoryMobilyticsUser.hashedCommsId) && this.householdCommsId.equals(accessoryMobilyticsUser.householdCommsId);
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    public boolean hasFeature(@NonNull String str) {
        return this.featureChecker.hasAccess(str);
    }

    public int hashCode() {
        return Objects.hash(this.directedId, this.marketplaceId, this.personId, this.hashedCommsId, this.householdCommsId);
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String marketplaceId() {
        return this.marketplaceId;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUser
    @NonNull
    public String personId() {
        return this.personId;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryMobilyticsUser{directedId='");
        GeneratedOutlineSupport1.outline176(outline107, this.directedId, Chars.QUOTE, ", marketplaceId='");
        GeneratedOutlineSupport1.outline176(outline107, this.marketplaceId, Chars.QUOTE, ", personId='");
        GeneratedOutlineSupport1.outline176(outline107, this.personId, Chars.QUOTE, ", hashedCommsId='");
        GeneratedOutlineSupport1.outline176(outline107, this.hashedCommsId, Chars.QUOTE, ", householdCommsId='");
        GeneratedOutlineSupport1.outline176(outline107, this.householdCommsId, Chars.QUOTE, ", featureChecker=");
        outline107.append(this.featureChecker);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
