package com.amazon.tarazed.ui;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import com.amazon.alexa.routing.api.RouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FontHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\nJ\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/tarazed/ui/FontHelper;", "", "()V", "FONTS_PATH", "", "createTypeface", "Landroid/graphics/Typeface;", "assetManager", "Landroid/content/res/AssetManager;", "fontName", "createTypeface$TarazedAndroidLibrary_release", "excludeExtension", RouteParameter.PATH, "findPath", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FontHelper {
    @NotNull
    public static final String FONTS_PATH = "fonts";
    public static final FontHelper INSTANCE = new FontHelper();

    private FontHelper() {
    }

    private final String excludeExtension(String str) {
        int lastIndexOf$default;
        lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str, '.', 0, false, 6, (Object) null);
        if (lastIndexOf$default != -1) {
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String substring = str.substring(0, lastIndexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        return str;
    }

    private final String findPath(AssetManager assetManager, String str) {
        String[] list = assetManager.list(FONTS_PATH);
        if (list != null) {
            for (String path : list) {
                FontHelper fontHelper = INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(path, "path");
                if (Intrinsics.areEqual(fontHelper.excludeExtension(path), str)) {
                    return GeneratedOutlineSupport1.outline72("fonts/", path);
                }
            }
            return null;
        }
        return null;
    }

    @Nullable
    public final Typeface createTypeface$TarazedAndroidLibrary_release(@NotNull AssetManager assetManager, @NotNull String fontName) {
        Intrinsics.checkParameterIsNotNull(assetManager, "assetManager");
        Intrinsics.checkParameterIsNotNull(fontName, "fontName");
        String findPath = findPath(assetManager, fontName);
        if (findPath != null) {
            return Typeface.createFromAsset(assetManager, findPath);
        }
        return null;
    }
}
