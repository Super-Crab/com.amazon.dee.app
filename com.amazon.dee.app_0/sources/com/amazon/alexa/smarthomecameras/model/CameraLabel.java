package com.amazon.alexa.smarthomecameras.model;

import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes10.dex */
public class CameraLabel {
    private final String cameraLabel;

    private CameraLabel(String str) {
        this.cameraLabel = str;
    }

    @Nullable
    public static CameraLabel create(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new CameraLabel(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CameraLabel) {
            return this.cameraLabel.equals(((CameraLabel) obj).cameraLabel);
        }
        return false;
    }

    public final String getValue() {
        return this.cameraLabel;
    }

    public int hashCode() {
        return Objects.hash(this.cameraLabel);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline90(GeneratedOutlineSupport1.outline107("CameraLabel{cameraLabel='"), this.cameraLabel, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
