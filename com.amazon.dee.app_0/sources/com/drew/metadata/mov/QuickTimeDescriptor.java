package com.drew.metadata.mov;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.TagDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class QuickTimeDescriptor extends TagDescriptor<QuickTimeDirectory> {
    public QuickTimeDescriptor(@NotNull QuickTimeDirectory quickTimeDirectory) {
        super(quickTimeDirectory);
    }

    private String getCompatibleBrandsDescription() {
        String[] stringArray = ((QuickTimeDirectory) this._directory).getStringArray(4098);
        if (stringArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            String lookup = QuickTimeDictionary.lookup(4096, str);
            if (lookup != null) {
                str = lookup;
            }
            arrayList.add(str);
        }
        return Arrays.toString(arrayList.toArray());
    }

    private String getDurationDescription() {
        Long longObject = ((QuickTimeDirectory) this._directory).getLongObject(259);
        if (longObject == null) {
            return null;
        }
        Integer valueOf = Integer.valueOf((int) (longObject.longValue() / Math.pow(60.0d, 2.0d)));
        Integer valueOf2 = Integer.valueOf((int) ((longObject.longValue() / Math.pow(60.0d, 1.0d)) - (valueOf.intValue() * 60)));
        return String.format("%1$02d:%2$02d:%3$02d", valueOf, valueOf2, Integer.valueOf((int) Math.ceil((longObject.longValue() / Math.pow(60.0d, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR)) - (valueOf2.intValue() * 60))));
    }

    private String getMajorBrandDescription() {
        byte[] byteArray = ((QuickTimeDirectory) this._directory).getByteArray(4096);
        if (byteArray == null) {
            return null;
        }
        return QuickTimeDictionary.lookup(4096, new String(byteArray));
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return i != 259 ? i != 4096 ? i != 4098 ? super.getDescription(i) : getCompatibleBrandsDescription() : getMajorBrandDescription() : getDurationDescription();
    }
}
