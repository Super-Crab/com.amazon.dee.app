package com.amazon.alexa;

import android.net.Uri;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/* compiled from: PlaylistParser.java */
@Singleton
/* loaded from: classes.dex */
public class onD {
    public static final Pattern zZm = Pattern.compile("https?:.*");
    public final OkHttpClient BIo;

    /* compiled from: PlaylistParser.java */
    /* loaded from: classes.dex */
    static class zZm extends IOException {
        public zZm(Exception exc) {
            super(exc);
        }

        public zZm(String str) {
            super(str);
        }
    }

    @Inject
    public onD(OkHttpClient okHttpClient) {
        this.BIo = okHttpClient;
    }

    public static Response zZm(OkHttpClient okHttpClient, Uri uri) throws IOException {
        return okHttpClient.newCall(new Request.Builder().url(uri.toString()).get().build()).execute();
    }
}
