package com.amazon.alexa;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.client.core.networking.adapters.StronglyTypedString;
import com.amazon.dee.sdk.iotsoftap.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SqliteExternalComponentStateDao.java */
@Singleton
/* loaded from: classes.dex */
public class dCo implements TFi {
    public static final String zZm = "dCo";
    public final Whr BIo;
    public final DateFormat zQM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    @Inject
    public dCo(Whr whr) {
        this.BIo = whr;
        this.zQM.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
    }

    public final String[] zZm(Set<? extends StronglyTypedString> set) {
        String[] strArr = new String[set.size()];
        int i = 0;
        for (StronglyTypedString stronglyTypedString : set) {
            strArr[i] = stronglyTypedString.getValue();
            i++;
        }
        return strArr;
    }

    public final String zZm(String str, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            i--;
        }
        objArr[1] = sb.toString();
        return String.format("%s in (%s)", objArr);
    }
}
