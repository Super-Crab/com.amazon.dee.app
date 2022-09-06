package com.amazon.alexa.accessory.internal.bluetooth;

import android.os.ParcelUuid;
import android.text.TextUtils;
import com.amazon.alexa.accessory.internal.util.UuidUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class SdpRecords {
    private final Set<UUID> uuids;

    public SdpRecords(ParcelUuid[] parcelUuidArr) {
        this.uuids = UuidUtils.setOfUuidsFromParcelArray(parcelUuidArr);
    }

    public boolean containsUuid(UUID uuid) {
        return this.uuids.contains(uuid) || this.uuids.contains(UuidUtils.invertUuid(uuid));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SdpRecord={");
        outline107.append(TextUtils.join(", ", this.uuids));
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public SdpRecords() {
        this.uuids = Collections.emptySet();
    }
}
