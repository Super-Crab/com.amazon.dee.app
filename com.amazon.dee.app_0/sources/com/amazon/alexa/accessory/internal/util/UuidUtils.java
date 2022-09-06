package com.amazon.alexa.accessory.internal.util;

import android.os.ParcelUuid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes.dex */
public final class UuidUtils {
    private UuidUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static boolean containsUuid(ParcelUuid[] parcelUuidArr, UUID uuid) {
        if (parcelUuidArr != null && uuid != null) {
            for (ParcelUuid parcelUuid : parcelUuidArr) {
                if (uuid.equals(parcelUuid.getUuid())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static UUID invertUuid(UUID uuid) {
        Preconditions.notNull(uuid, "uuid");
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long mostSignificantBits = uuid.getMostSignificantBits();
        return new UUID(((leastSignificantBits & 255) << 56) | ((leastSignificantBits >> 56) & 255) | (((leastSignificantBits >> 48) & 255) << 8) | (((leastSignificantBits >> 40) & 255) << 16) | (((leastSignificantBits >> 32) & 255) << 24) | (((leastSignificantBits >> 24) & 255) << 32) | (((leastSignificantBits >> 16) & 255) << 40) | (((leastSignificantBits >> 8) & 255) << 48), ((mostSignificantBits & 255) << 56) | ((mostSignificantBits >> 56) & 255) | (((mostSignificantBits >> 48) & 255) << 8) | (((mostSignificantBits >> 40) & 255) << 16) | (((mostSignificantBits >> 32) & 255) << 24) | (((mostSignificantBits >> 24) & 255) << 32) | (((mostSignificantBits >> 16) & 255) << 40) | (((mostSignificantBits >> 8) & 255) << 48));
    }

    public static Set<UUID> setOfUuidsFromParcelArray(ParcelUuid[] parcelUuidArr) {
        Preconditions.notNull(parcelUuidArr, "parcelUuids");
        HashSet hashSet = new HashSet();
        for (ParcelUuid parcelUuid : parcelUuidArr) {
            hashSet.add(parcelUuid.getUuid());
        }
        return hashSet;
    }
}
