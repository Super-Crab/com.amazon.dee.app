package com.amazon.alexa.voice.ui.onedesign.util.image;

import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class RenderingServiceUrls {
    private static final List<String> RENDERING_SERVICE_HOST_HINTS = Arrays.asList("images-amazon.com", "media-amazon.com");
    private static final List<String> imageTypeValues = Collections.unmodifiableList(Arrays.asList(ImageType.JPG, ImageType.PNG, ImageType.UNSUPPORTED));

    private RenderingServiceUrls() {
        throw new IllegalStateException("No instances, bud!");
    }

    private static String fromUrl(String str) {
        if (str == null) {
            return null;
        }
        String substring = str.substring(str.lastIndexOf(".") + 1);
        for (String str2 : imageTypeValues) {
            if (str2.equals(substring)) {
                return str2;
            }
        }
        return ImageType.UNSUPPORTED;
    }

    private static boolean isRenderingServiceUrl(String str) {
        for (String str2 : RENDERING_SERVICE_HOST_HINTS) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String scaleToHeight(String str, int i) {
        if (!isRenderingServiceUrl(str)) {
            return str;
        }
        String fromUrl = fromUrl(str);
        if (ImageType.UNSUPPORTED.equals(fromUrl)) {
            return str;
        }
        String replaceAll = str.replaceAll(GeneratedOutlineSupport1.outline78(fromUrl, GeneratedOutlineSupport1.outline107(ArcusConfig.PATH_SEPARATOR), "$"), String.format(Locale.getDefault(), "\\._SY%d_\\.%s", Integer.valueOf(i), fromUrl));
        Logger.verbose(String.format(Locale.getDefault(), "Client applied conditions to Rendering Service url: %s", replaceAll));
        return replaceAll;
    }
}
