package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class TypefaceCompatBaseImpl {
    private static final int INVALID_KEY = 0;
    private static final String TAG = "TypefaceCompatBaseImpl";
    private ConcurrentHashMap<Long, FontResourcesParserCompat.FontFamilyFilesResourceEntry> mFontFamilies = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface StyleExtractor<T> {
        int getWeight(T t);

        boolean isItalic(T t);
    }

    private void addFontFamily(Typeface typeface, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry) {
        long uniqueKey = getUniqueKey(typeface);
        if (uniqueKey != 0) {
            this.mFontFamilies.put(Long.valueOf(uniqueKey), fontFamilyFilesResourceEntry);
        }
    }

    private FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, int i) {
        return (FontResourcesParserCompat.FontFileResourceEntry) findBestFont(fontFamilyFilesResourceEntry.getEntries(), i, new StyleExtractor<FontResourcesParserCompat.FontFileResourceEntry>() { // from class: androidx.core.graphics.TypefaceCompatBaseImpl.2
            @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
            public int getWeight(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
                return fontFileResourceEntry.getWeight();
            }

            @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
            public boolean isItalic(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
                return fontFileResourceEntry.isItalic();
            }
        });
    }

    private static <T> T findBestFont(T[] tArr, int i, StyleExtractor<T> styleExtractor) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        int i3 = Integer.MAX_VALUE;
        T t = null;
        for (T t2 : tArr) {
            int abs = (Math.abs(styleExtractor.getWeight(t2) - i2) * 2) + (styleExtractor.isItalic(t2) == z ? 0 : 1);
            if (t == null || i3 > abs) {
                t = t2;
                i3 = abs;
            }
        }
        return t;
    }

    private static long getUniqueKey(@Nullable Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Could not retrieve font from family.", e);
            return 0L;
        } catch (NoSuchFieldException e2) {
            Log.e(TAG, "Could not retrieve font from family.", e2);
            return 0L;
        }
    }

    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        FontResourcesParserCompat.FontFileResourceEntry findBestEntry = findBestEntry(fontFamilyFilesResourceEntry, i);
        if (findBestEntry == null) {
            return null;
        }
        Typeface createFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, resources, findBestEntry.getResourceId(), findBestEntry.getFileName(), i);
        addFontFamily(createFromResourcesFontFile, fontFamilyFilesResourceEntry);
        return createFromResourcesFontFile;
    }

    @Nullable
    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (fontInfoArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(findBestInfo(fontInfoArr, i).getUri());
        } catch (IOException unused) {
            inputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Typeface createFromInputStream = createFromInputStream(context, inputStream);
            TypefaceCompatUtil.closeQuietly(inputStream);
            return createFromInputStream;
        } catch (IOException unused2) {
            TypefaceCompatUtil.closeQuietly(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStream;
            TypefaceCompatUtil.closeQuietly(inputStream2);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (TypefaceCompatUtil.copyToFile(tempFile, inputStream)) {
                return Typeface.createFromFile(tempFile.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (TypefaceCompatUtil.copyToFile(tempFile, resources, i)) {
                return Typeface.createFromFile(tempFile.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        return (FontsContractCompat.FontInfo) findBestFont(fontInfoArr, i, new StyleExtractor<FontsContractCompat.FontInfo>() { // from class: androidx.core.graphics.TypefaceCompatBaseImpl.1
            @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
            public int getWeight(FontsContractCompat.FontInfo fontInfo) {
                return fontInfo.getWeight();
            }

            @Override // androidx.core.graphics.TypefaceCompatBaseImpl.StyleExtractor
            public boolean isItalic(FontsContractCompat.FontInfo fontInfo) {
                return fontInfo.isItalic();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public FontResourcesParserCompat.FontFamilyFilesResourceEntry getFontFamily(Typeface typeface) {
        long uniqueKey = getUniqueKey(typeface);
        if (uniqueKey == 0) {
            return null;
        }
        return this.mFontFamilies.get(Long.valueOf(uniqueKey));
    }
}