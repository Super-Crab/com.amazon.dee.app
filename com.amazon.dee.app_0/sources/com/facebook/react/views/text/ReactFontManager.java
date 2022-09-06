package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class ReactFontManager {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final String FONTS_ASSET_PATH = "fonts/";
    private static ReactFontManager sReactFontManagerInstance;
    private final Map<String, FontFamily> mFontCache = new HashMap();
    private final Map<String, Typeface> mCustomTypefaceCache = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FontFamily {
        private SparseArray<Typeface> mTypefaceSparseArray;

        public Typeface getTypeface(int i) {
            return this.mTypefaceSparseArray.get(i);
        }

        public void setTypeface(int i, Typeface typeface) {
            this.mTypefaceSparseArray.put(i, typeface);
        }

        private FontFamily() {
            this.mTypefaceSparseArray = new SparseArray<>(4);
        }
    }

    private ReactFontManager() {
    }

    @Nullable
    private static Typeface createTypeface(String str, int i, AssetManager assetManager) {
        String str2 = EXTENSIONS[i];
        for (String str3 : FILE_EXTENSIONS) {
            try {
                return Typeface.createFromAsset(assetManager, GeneratedOutlineSupport1.outline76(FONTS_ASSET_PATH, str, str2, str3));
            } catch (RuntimeException unused) {
            }
        }
        return Typeface.create(str, i);
    }

    public static ReactFontManager getInstance() {
        if (sReactFontManagerInstance == null) {
            sReactFontManagerInstance = new ReactFontManager();
        }
        return sReactFontManagerInstance;
    }

    public void addCustomFont(@NonNull Context context, @NonNull String str, int i) {
        Typeface font = ResourcesCompat.getFont(context, i);
        if (font != null) {
            this.mCustomTypefaceCache.put(str, font);
        }
    }

    @Nullable
    public Typeface getTypeface(String str, int i, AssetManager assetManager) {
        return getTypeface(str, i, 0, assetManager);
    }

    public void setTypeface(String str, int i, Typeface typeface) {
        if (typeface != null) {
            FontFamily fontFamily = this.mFontCache.get(str);
            if (fontFamily == null) {
                fontFamily = new FontFamily();
                this.mFontCache.put(str, fontFamily);
            }
            fontFamily.setTypeface(i, typeface);
        }
    }

    @Nullable
    public Typeface getTypeface(String str, int i, int i2, AssetManager assetManager) {
        if (this.mCustomTypefaceCache.containsKey(str)) {
            Typeface typeface = this.mCustomTypefaceCache.get(str);
            if (Build.VERSION.SDK_INT >= 28 && i2 >= 100 && i2 <= 1000) {
                return Typeface.create(typeface, i2, (i & 2) != 0);
            }
            return Typeface.create(typeface, i);
        }
        FontFamily fontFamily = this.mFontCache.get(str);
        if (fontFamily == null) {
            fontFamily = new FontFamily();
            this.mFontCache.put(str, fontFamily);
        }
        Typeface typeface2 = fontFamily.getTypeface(i);
        if (typeface2 == null && (typeface2 = createTypeface(str, i, assetManager)) != null) {
            fontFamily.setTypeface(i, typeface2);
        }
        return typeface2;
    }
}
