package androidx.work;

import android.app.Notification;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class ForegroundInfo {
    private final int mForegroundServiceType;
    private final Notification mNotification;
    private final int mNotificationId;

    public ForegroundInfo(int i, @NonNull Notification notification) {
        this(i, notification, 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ForegroundInfo.class != obj.getClass()) {
            return false;
        }
        ForegroundInfo foregroundInfo = (ForegroundInfo) obj;
        if (this.mNotificationId != foregroundInfo.mNotificationId || this.mForegroundServiceType != foregroundInfo.mForegroundServiceType) {
            return false;
        }
        return this.mNotification.equals(foregroundInfo.mNotification);
    }

    public int getForegroundServiceType() {
        return this.mForegroundServiceType;
    }

    @NonNull
    public Notification getNotification() {
        return this.mNotification;
    }

    public int getNotificationId() {
        return this.mNotificationId;
    }

    public int hashCode() {
        return this.mNotification.hashCode() + (((this.mNotificationId * 31) + this.mForegroundServiceType) * 31);
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("ForegroundInfo{", "mNotificationId=");
        outline112.append(this.mNotificationId);
        outline112.append(", mForegroundServiceType=");
        outline112.append(this.mForegroundServiceType);
        outline112.append(", mNotification=");
        outline112.append(this.mNotification);
        outline112.append(JsonReaderKt.END_OBJ);
        return outline112.toString();
    }

    public ForegroundInfo(int i, @NonNull Notification notification, int i2) {
        this.mNotificationId = i;
        this.mNotification = notification;
        this.mForegroundServiceType = i2;
    }
}
