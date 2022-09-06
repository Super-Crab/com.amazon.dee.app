package com.drew.metadata.mp4;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class Mp4Descriptor<T extends Directory> extends TagDescriptor<Mp4Directory> {
    public Mp4Descriptor(@NotNull Mp4Directory mp4Directory) {
        super(mp4Directory);
    }

    private String getCompatibleBrandsDescription() {
        String[] stringArray = ((Mp4Directory) this._directory).getStringArray(3);
        if (stringArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            String lookup = Mp4Dictionary.lookup(1, str);
            if (lookup != null) {
                str = lookup;
            }
            arrayList.add(str);
        }
        return Arrays.toString(arrayList.toArray());
    }

    private String getDurationDescription() {
        Long longObject = ((Mp4Directory) this._directory).getLongObject(259);
        if (longObject == null) {
            return null;
        }
        Integer valueOf = Integer.valueOf((int) (longObject.longValue() / Math.pow(60.0d, 2.0d)));
        Integer valueOf2 = Integer.valueOf((int) ((longObject.longValue() / Math.pow(60.0d, 1.0d)) - (valueOf.intValue() * 60)));
        return String.format("%1$02d:%2$02d:%3$02d", valueOf, valueOf2, Integer.valueOf((int) Math.ceil((longObject.longValue() / Math.pow(60.0d, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR)) - (valueOf2.intValue() * 60))));
    }

    private String getMajorBrandDescription() {
        byte[] byteArray = ((Mp4Directory) this._directory).getByteArray(1);
        if (byteArray == null) {
            return null;
        }
        return Mp4Dictionary.lookup(1, new String(byteArray));
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return i != 1 ? i != 3 ? i != 259 ? ((Mp4Directory) this._directory).getString(i) : getDurationDescription() : getCompatibleBrandsDescription() : getMajorBrandDescription();
    }
}
