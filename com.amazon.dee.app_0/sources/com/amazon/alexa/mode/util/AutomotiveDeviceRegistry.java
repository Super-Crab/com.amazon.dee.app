package com.amazon.alexa.mode.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.mode.R;
import com.amazon.alexa.mode.model.AmaDevice;
import com.amazon.alexa.mode.model.Manufacture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes9.dex */
public class AutomotiveDeviceRegistry {
    private static final String TAG = "AutomotiveDeviceRegistry";

    private static String getAmaDevicesJsonResource(Context context) {
        try {
            if (context.getResources() == null) {
                return "";
            }
            InputStream openRawResource = context.getResources().openRawResource(R.raw.ama_devices);
            byte[] bArr = new byte[openRawResource.available()];
            openRawResource.read(bArr);
            openRawResource.close();
            return new String(bArr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            String str = TAG;
            Log.e(str, "Read AMA devices json file error: " + e);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, String> parseAutomotiveDeviceTypes(String str) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        for (Manufacture manufacture : (List) new Gson().fromJson(str, TypeToken.getParameterized(ArrayList.class, Manufacture.class).getType())) {
            for (AmaDevice amaDevice : manufacture.getDevices()) {
                hashMap.put(amaDevice.getDeviceType(), manufacture.getManufacture());
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public Single<Map<String, String>> fetchAutomotiveDevices(final Context context) {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.mode.util.-$$Lambda$AutomotiveDeviceRegistry$cQ8gt0jAqPKTaKIE5V_ySkp5EnA
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Map parseAutomotiveDeviceTypes;
                parseAutomotiveDeviceTypes = AutomotiveDeviceRegistry.parseAutomotiveDeviceTypes(AutomotiveDeviceRegistry.getAmaDevicesJsonResource(context));
                return parseAutomotiveDeviceTypes;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
