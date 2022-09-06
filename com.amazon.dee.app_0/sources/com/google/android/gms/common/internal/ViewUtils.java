package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes2.dex */
public class ViewUtils {
    private ViewUtils() {
    }

    @KeepForSdk
    public static String getXmlAttributeString(String str, String str2, Context context, AttributeSet attributeSet, boolean z, boolean z2, String str3) {
        String attributeValue = attributeSet == null ? null : attributeSet.getAttributeValue(str, str2);
        if (attributeValue != null && attributeValue.startsWith("@string/") && z) {
            String substring = attributeValue.substring(8);
            String packageName = context.getPackageName();
            TypedValue typedValue = new TypedValue();
            try {
                Resources resources = context.getResources();
                StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 8 + String.valueOf(substring).length());
                sb.append(packageName);
                sb.append(":string/");
                sb.append(substring);
                resources.getValue(sb.toString(), typedValue, true);
            } catch (Resources.NotFoundException unused) {
                Log.w(str3, GeneratedOutlineSupport1.outline31(attributeValue.length() + GeneratedOutlineSupport1.outline6(str2, 30), "Could not find resource for ", str2, RealTimeTextConstants.COLON_SPACE, attributeValue));
            }
            CharSequence charSequence = typedValue.string;
            if (charSequence != null) {
                attributeValue = charSequence.toString();
            } else {
                String valueOf = String.valueOf(typedValue);
                Log.w(str3, GeneratedOutlineSupport1.outline31(valueOf.length() + GeneratedOutlineSupport1.outline6(str2, 28), "Resource ", str2, " was not a string: ", valueOf));
            }
        }
        if (z2 && attributeValue == null) {
            StringBuilder sb2 = new StringBuilder(GeneratedOutlineSupport1.outline6(str2, 33));
            sb2.append("Required XML attribute \"");
            sb2.append(str2);
            sb2.append("\" missing");
            Log.w(str3, sb2.toString());
        }
        return attributeValue;
    }
}
