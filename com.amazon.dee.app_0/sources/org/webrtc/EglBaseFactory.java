package org.webrtc;

import android.opengl.EGLContext;
import org.webrtc.EglBase;
import org.webrtc.EglBase14;
/* loaded from: classes5.dex */
public final class EglBaseFactory {
    private EglBaseFactory() {
    }

    public static EglBase create() {
        return new EglBase14(null, EglBase.CONFIG_PLAIN);
    }

    public static EglBase create(EglBase.Context context) {
        return new EglBase14((EglBase14.Context) context, EglBase.CONFIG_PLAIN);
    }

    public static EglBase create(EGLContext eGLContext) {
        if (eGLContext == null) {
            return create();
        }
        return create(new EglBase14.Context(eGLContext));
    }
}
