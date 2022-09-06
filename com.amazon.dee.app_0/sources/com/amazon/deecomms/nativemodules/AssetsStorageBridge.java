package com.amazon.deecomms.nativemodules;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class AssetsStorageBridge extends ReactContextBaseJavaModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AssetsStorageBridge.class);

    public AssetsStorageBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private static int convertPixelsToDp(@NonNull Resources resources, int i) {
        return Math.round(i / (resources.getDisplayMetrics().xdpi / 160.0f));
    }

    private static ReadableMap createBase64Image(@NonNull String str, int i, int i2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("base64Representation", str);
        createMap.putInt("width", i);
        createMap.putInt("height", i2);
        return createMap;
    }

    private ReadableMap getBase64Image(int i) throws IOException {
        Resources resources = getReactApplicationContext().getResources();
        InputStream openRawResource = resources.openRawResource(i);
        try {
            String encodeToString = Base64.encodeToString(streamToBytes(openRawResource), 0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(resources, i, options);
            ReadableMap createBase64Image = createBase64Image(encodeToString, convertPixelsToDp(resources, options.outWidth), convertPixelsToDp(resources, options.outHeight));
            openRawResource.close();
            return createBase64Image;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (openRawResource != null) {
                    try {
                        openRawResource.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private ReadableMap getBase64ImageByName(String str) throws IOException {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        return getBase64Image(reactApplicationContext.getResources().getIdentifier(str, "drawable", reactApplicationContext.getPackageName()));
    }

    private static byte[] streamToBytes(@NonNull InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @ReactMethod
    public void getImageByName(String str, Promise promise) {
        try {
            promise.resolve(getBase64ImageByName(str));
        } catch (Resources.NotFoundException | IOException e) {
            promise.reject(e);
            CommsLogger commsLogger = LOG;
            commsLogger.e("Loading resource failed. Requested resource name: " + str, e);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsAssetsStorage";
    }
}
