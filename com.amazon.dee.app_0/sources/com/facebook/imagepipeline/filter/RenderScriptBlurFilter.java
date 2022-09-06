package com.facebook.imagepipeline.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import androidx.annotation.RequiresApi;
import com.facebook.common.internal.Preconditions;
/* loaded from: classes2.dex */
public abstract class RenderScriptBlurFilter {
    public static final int BLUR_MAX_RADIUS = 25;

    @RequiresApi(17)
    public static void blurBitmap(final Bitmap dest, final Bitmap src, final Context context, final int radius) {
        RenderScript renderScript;
        Preconditions.checkNotNull(dest);
        Preconditions.checkNotNull(src);
        Preconditions.checkNotNull(context);
        Preconditions.checkArgument(Boolean.valueOf(radius > 0 && radius <= 25));
        try {
            renderScript = (RenderScript) Preconditions.checkNotNull(RenderScript.create(context));
            try {
                ScriptIntrinsicBlur create = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
                Allocation allocation = (Allocation) Preconditions.checkNotNull(Allocation.createFromBitmap(renderScript, src));
                Allocation allocation2 = (Allocation) Preconditions.checkNotNull(Allocation.createFromBitmap(renderScript, dest));
                create.setRadius(radius);
                create.setInput(allocation);
                create.forEach(allocation2);
                allocation2.copyTo(dest);
                create.destroy();
                allocation.destroy();
                allocation2.destroy();
                if (renderScript == null) {
                    return;
                }
                renderScript.destroy();
            } catch (Throwable th) {
                th = th;
                if (renderScript != null) {
                    renderScript.destroy();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            renderScript = null;
        }
    }

    public static boolean canUseRenderScript() {
        int i = Build.VERSION.SDK_INT;
        return true;
    }
}
