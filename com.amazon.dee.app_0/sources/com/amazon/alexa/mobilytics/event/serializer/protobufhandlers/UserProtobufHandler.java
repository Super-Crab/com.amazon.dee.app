package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.amazon.alexa.mobilytics.protobuf.CustomerProto;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.google.common.base.Preconditions;
import java.util.EnumSet;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class UserProtobufHandler implements ProtobufHandler {
    private final MobilyticsUserProvider userProvider;

    @Inject
    public UserProtobufHandler(@NonNull MobilyticsUserProvider mobilyticsUserProvider) {
        this.userProvider = (MobilyticsUserProvider) Preconditions.checkNotNull(mobilyticsUserProvider);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler
    @Nullable
    public MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent) {
        String str;
        String str2;
        String str3;
        String str4;
        CustomerProto.Builder newBuilder = CustomerProto.newBuilder();
        MobilyticsUser user = this.userProvider.user();
        String str5 = "Unknown";
        if (user != null) {
            str5 = user.directedId();
            str = user.marketplaceId();
            str2 = user.personId();
            str3 = user.countryOfResidence();
            str4 = user.personIdv2();
            Iterator it2 = EnumSet.allOf(MobilyticsUser.Attribute.class).iterator();
            while (it2.hasNext()) {
                MobilyticsUser.Attribute attribute = (MobilyticsUser.Attribute) it2.next();
                String attribute2 = user.attribute(attribute);
                if (!TextUtils.isEmpty(attribute2)) {
                    String id = attribute.id();
                    char c = 65535;
                    int hashCode = id.hashCode();
                    if (hashCode != -1628399755) {
                        if (hashCode == 1825225050 && id.equals("householdId")) {
                            c = 1;
                        }
                    } else if (id.equals(MetricKeys.META_HASHED_COMMS_ID)) {
                        c = 0;
                    }
                    if (c == 0) {
                        newBuilder.setHashedCommsId(attribute2);
                    } else if (c == 1) {
                        newBuilder.setHouseholdId(attribute2);
                    }
                }
            }
        } else {
            str = str5;
            str2 = str;
            str3 = str2;
            str4 = str3;
        }
        newBuilder.setDirectedId(str5).setMarketplaceId(str).setPersonId(str2).setCountryOfResidence(str3).setPersonIdv2(str4);
        return MobilyticsMessageProto.newBuilder().setCustomer(newBuilder).mo10084build();
    }
}
