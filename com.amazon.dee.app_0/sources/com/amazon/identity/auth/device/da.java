package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.UserHandle;
import com.amazon.android.os.MultipleProfileHelper;
import com.amazon.identity.auth.device.utils.ReflectionHelper;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class da {
    private final UserHandle iH;
    private final int iI;
    private final int mFlags;
    public static final Class<?> iF = cC();
    private static final String TAG = da.class.getName();
    private static final Integer iG = a(iF);

    @SuppressLint({"NewApi"})
    da(int i, int i2, Object obj) {
        this.iI = i;
        this.mFlags = i2;
        this.iH = (UserHandle) obj;
    }

    private static Integer a(Class<?> cls) {
        if (iF == null) {
            io.dm(TAG);
            return null;
        }
        try {
            return (Integer) new ReflectionHelper().a(cls, "USER_OWNER");
        } catch (ReflectionHelper.CannotCallMethodException e) {
            io.c(TAG, "Cannot get USER_OWNER static field. Error: %s", e.getMessage());
            return null;
        }
    }

    public static da c(Object obj) {
        if (obj != null && "android.content.pm.UserInfo".equals(obj.getClass().getName())) {
            try {
                ReflectionHelper reflectionHelper = new ReflectionHelper();
                return new da(((Integer) reflectionHelper.b(obj, "id")).intValue(), ((Integer) reflectionHelper.b(obj, "flags")).intValue(), reflectionHelper.a("getUserHandle", obj, new Class[0], new Object[0]));
            } catch (ReflectionHelper.CannotCallMethodException e) {
                io.e(TAG, "Cannot construct Android User from User Info", e);
                return null;
            }
        }
        io.e(TAG, "Given Object is not a valid UserInfo class");
        return null;
    }

    public static int cA() {
        if (mz.iR()) {
            return MultipleProfileHelper.getCallingProfileId();
        }
        if (iF == null) {
            return 0;
        }
        try {
            return ((Integer) new ReflectionHelper().a("getCallingUserId", "android.os.UserHandle", new Class[0], new Object[0])).intValue();
        } catch (ReflectionHelper.CannotCallMethodException e) {
            io.c(TAG, "Cannot get getCallingUserId static field. Error: %s", e.getMessage());
            return 0;
        }
    }

    public static int cB() {
        if (mz.iR()) {
            return MultipleProfileHelper.getForegroundProfileId();
        }
        try {
            return ((Integer) new ReflectionHelper().a("getCurrentUser", ActivityManager.class, new Class[0], new Object[0])).intValue();
        } catch (ReflectionHelper.CannotCallMethodException unused) {
            io.dn(TAG);
            return 0;
        }
    }

    private static Class<?> cC() {
        try {
            return Class.forName("android.os.UserHandle");
        } catch (ClassNotFoundException unused) {
            io.a("Cannot find class %s", "android.os.UserHandle");
            return null;
        }
    }

    public static int cy() {
        Integer num = iG;
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static int cz() {
        if (mz.iR()) {
            return MultipleProfileHelper.myProfileId();
        }
        if (iF == null) {
            return 0;
        }
        try {
            return ((Integer) new ReflectionHelper().a("myUserId", "android.os.UserHandle", new Class[0], new Object[0])).intValue();
        } catch (ReflectionHelper.CannotCallMethodException e) {
            io.c(TAG, "Cannot get myUserId static field. Error: %s", e.getMessage());
            return 0;
        }
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof da) && this.iI == ((da) obj).iI;
    }

    public UserHandle getUserHandle() {
        return this.iH;
    }

    public int getUserId() {
        return this.iI;
    }

    public int hashCode() {
        return this.iI + 31;
    }
}
