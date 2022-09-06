package com.amazon.blueshift.bluefront.android.http;

import android.net.Uri;
import com.amazon.blueshift.bluefront.android.common.BluefrontCredential;
import java.net.MalformedURLException;
/* loaded from: classes11.dex */
public interface SigningProtocol {
    URLWrapper sign(Uri uri, BluefrontCredential bluefrontCredential) throws MalformedURLException;
}
