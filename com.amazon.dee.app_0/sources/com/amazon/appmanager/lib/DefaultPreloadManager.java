package com.amazon.appmanager.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.preload.attribution.country.AttributionLocationManager;
import com.amazon.appmanager.R;
import com.amazon.maft.metrics.MetricsFactory;
import com.amazon.maft.metrics.PmetMetrics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class DefaultPreloadManager extends PreloadManager {
    public static final String EMPTY_PRELOAD_ID = "";
    public static final String ERROR_METRIC_NAME = "com.amazon.appmanager.lib.PreloadManager.ERROR";
    public static final String METRIC_PATH_DELIMITER = "/";
    public static final String METRIC_PATH_FIRST = "first";
    public static final String METRIC_PATH_START = "start";
    public static final String PREFERENCES_NAME = "com.amazon.appmanager.lib.PreloadManager";
    public static final String PREFERENCES_PRELOAD_ID = "preload_id";
    public static final Signature SIGNATURE_AMAZON = new Signature("3082056130820349020601200123ab56300d06092a864886f70d01010b05003074311430120603550408130b4e657661646120433d5553313e303c060355040b13354a617661204f626a656374205369676e696e67204f3d416d617a6f6e205365727669636573204c4c43204c3d4c6173205665676173311c301a06035504031313416d617a6f6e2053656c662d5369676e204341301e170d3039303331333138343132365a170d3333313032333030303030305a3074311430120603550408130b4e657661646120433d5553313e303c060355040b13354a617661204f626a656374205369676e696e67204f3d416d617a6f6e205365727669636573204c4c43204c3d4c6173205665676173311c301a06035504031313416d617a6f6e205365727669636573204c4c4330820222300d06092a864886f70d01010105000382020f003082020a0282020100ac60dafe1c962683e46a6e5b5c2945f0f1a75ef8f268f67994c3841a9366aec784ab1baf7ef8c90e95616272fbc71535a478812f872163e0c07a562c6e5acda475f12ec78212ba395ff90b21bc8c0e4ffc927cbdcec695d91c9c369481a7a230956ff3150f43e5ba307c345558095f52c8d830bfd3b29e0f4c81e7e2cf7154dc1a4ae7b6647a983079256fa507eefada8cab1c59c8220e65feee0c0f46f0c2010df8749d4011508f2ae92d73d4b16247e5f068ce826897ff6fe1986a5f296910ab402f8c9625dedd7acf40ea15f11b012f6fde1569527d7604139d3d7f072a71747c6bf0de03e4fe84fa246a9ce581f483d0922e10225e732e632e98861644bf3b5919927086a2ace441daec6bfa237d9a61ea4a529126f7d55dde74039bad8dbfa1d5c51b9c845f504b24893a2857202b1b6e9cb9f1d0746140b58c4aff193446cd20b1d0346258b33dc8ed6fc454d872e255e8cc6d357cc4483b1473adf20bba5243979b63374ae989d8c11b46a136c698c52558593bd5bbcbbad3b5c42a74a2b91387d03d952dd84090aa033cc36b20b8781713544b2a7049725f745d9d2849c9e0199ff6c27bb002831dbe4183b87caf311852bc4709134417a8f4f4887f02f2c550cfd1bcdf6bfba151aef437e4bb67aa48315224c2052bd6e2d5c2daf533e00e73816885dd30ec16f6b0c5eb5876bb2f619986ba30fbad68139c4c08c70203010001300d06092a864886f70d01010b0500038202010050c9ccc6a3ea540ba5e7a0e9723e1e92d4e250edd32b33b56b3400b9b3a9648c2a830df99040226dd223d64f68088192188f790e0a44db321f487774f119ce9f72528e57b38456b17def3a59353211fa3e3510500359e787e075007f52edf39a45cd376a7b8988910ebefb9b09c2a6ace54526a540ab50bac457e5a45e3f17acc27c2ff7beed697c403fdca64396057e4c3780e21ff47cdba1cb7579ef72ac6c0917d8dad13083d6648ba42a1af8dc5176258ea265f7aa255738a429ea6a4363ca93185fb8ae1774684c3633a6762b22f0582f844fcf4cb6fe266eb986a2ac028e5f7fc8cf99d91ac36a556a4be9ea250fa440a958e2c4f9a05387b333b947ae7a40e107a8d9dd28b327c5645f5e5fbdd114f03770970f6c288bb7c4ce690209b1c44e6839c433c2c3cceb9b2a32c98a33144ed3d323a749dec1d0ecd73e0f1bdc37fffcb89ee2c19b413f8442aeaf2aaaa2272f54ce88653bdcd5c81073f32d07907750ee75b09244c3bbb1653021574289107fb39abe22227b8825cb4fbe469b7aca007b49e3f57716cc96f0f89770254233cb4ef86b3c9d76a79e0401816ed9695c5b4728be2df38455050091a3182968bf02a7cd7a7d16995a2463116c2b76ea722cb550864146c52bebeaa39d9ad32a2da1556dc54e79d7c6a3d16756bf26c7eb499e6e369170b732c67d2ee2377d38e157f440e7c63642d581ea65dd1e");
    public static final String URI_PRELOAD_ID_FORMAT = "content://com.amazon.appmanager.preload.app_info.provider/%s";
    private Set<Signature> signaturesAccepted;
    private String preloadId = null;
    private boolean triedGettingPreloadId = false;

    private synchronized String getPreloadId(Context context, MetricsFactory metricsFactory) throws RemoteException, SecurityException {
        if (this.triedGettingPreloadId) {
            return this.preloadId;
        }
        boolean z = true;
        this.triedGettingPreloadId = true;
        PmetMetrics mo4086newPmetMetrics = metricsFactory.mo4086newPmetMetrics(PREFERENCES_NAME);
        LinkedList linkedList = new LinkedList();
        linkedList.add("start");
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
        this.preloadId = sharedPreferences.getString(PREFERENCES_PRELOAD_ID, null);
        if (this.preloadId != null) {
            z = false;
        }
        if (z) {
            linkedList.add(METRIC_PATH_FIRST);
        }
        if (z || "".equals(this.preloadId)) {
            this.preloadId = getPreloadIdFromAllSources(context, metricsFactory);
            if (this.preloadId == null) {
                sharedPreferences.edit().putString(PREFERENCES_PRELOAD_ID, "").apply();
                linkedList.add(context.getPackageName());
                mo4086newPmetMetrics.addCount(TextUtils.join("/", linkedList), 1.0d).record();
                return null;
            }
            sharedPreferences.edit().putString(PREFERENCES_PRELOAD_ID, this.preloadId).apply();
            if (!z) {
                linkedList.add(context.getPackageName());
            }
        }
        linkedList.add(this.preloadId);
        mo4086newPmetMetrics.addCount(TextUtils.join("/", linkedList), 1.0d).record();
        return this.preloadId;
    }

    private String getPreloadIdFromAllSources(Context context, MetricsFactory metricsFactory) throws RemoteException, SecurityException {
        String preloadIdFromContentProvider = getPreloadIdFromContentProvider(context, metricsFactory);
        return preloadIdFromContentProvider == null ? getPreloadIdFromEmbeddedFile(context, metricsFactory) : preloadIdFromContentProvider;
    }

    private String getPreloadIdFromContentProvider(Context context, MetricsFactory metricsFactory) throws RemoteException, SecurityException {
        Uri parse = Uri.parse(String.format(URI_PRELOAD_ID_FORMAT, context.getPackageName()));
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(parse.getEncodedAuthority(), 0);
        if (resolveContentProvider == null) {
            return null;
        }
        if (isSignatureValid(context, resolveContentProvider.packageName)) {
            Cursor query = context.getContentResolver().query(parse, null, null, null, null);
            if (query != null) {
                if (query.getCount() == 0) {
                    return null;
                }
                try {
                    query.moveToFirst();
                    return query.getString(0);
                } finally {
                    query.close();
                }
            }
            metricsFactory.mo4086newPmetMetrics(ERROR_METRIC_NAME).addCount(RemoteException.class.getSimpleName(), 1.0d).record();
            throw new RemoteException();
        }
        metricsFactory.mo4086newPmetMetrics(ERROR_METRIC_NAME).addCount(SecurityException.class.getSimpleName(), 1.0d).record();
        throw new SecurityException("ContentProvider package has invalid signature.");
    }

    private String getPreloadIdFromEmbeddedFile(Context context, MetricsFactory metricsFactory) {
        return readStringFromRawResourceFile(context, metricsFactory, R.raw.plm_associate_tag);
    }

    public static String readStringFromRawResourceFile(Context context, MetricsFactory metricsFactory, int i) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i), Charset.forName("UTF-8")));
            } catch (Throwable th) {
                th = th;
            }
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine.trim());
                }
                String sb2 = sb.toString();
                if (sb2.length() <= 0) {
                    sb2 = null;
                }
                try {
                    bufferedReader.close();
                    return sb2;
                } catch (IOException e) {
                    metricsFactory.mo4086newPmetMetrics(ERROR_METRIC_NAME).addCount(IOException.class.getSimpleName(), 1.0d).record();
                    throw new RuntimeException(e);
                }
            } catch (Resources.NotFoundException e2) {
                e = e2;
                metricsFactory.mo4086newPmetMetrics(ERROR_METRIC_NAME).addCount(Resources.NotFoundException.class.getSimpleName(), 1.0d).record();
                throw new RuntimeException(e);
            } catch (IOException e3) {
                e = e3;
                metricsFactory.mo4086newPmetMetrics(ERROR_METRIC_NAME).addCount(IOException.class.getSimpleName(), 1.0d).record();
                throw new RuntimeException(e);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e4) {
                        metricsFactory.mo4086newPmetMetrics(ERROR_METRIC_NAME).addCount(IOException.class.getSimpleName(), 1.0d).record();
                        throw new RuntimeException(e4);
                    }
                }
                throw th;
            }
        } catch (Resources.NotFoundException e5) {
            e = e5;
        } catch (IOException e6) {
            e = e6;
        }
    }

    @Override // com.amazon.appmanager.lib.PreloadManager
    @Nullable
    public String getPreloadAssociateTag(@NonNull Context context, @NonNull MetricsFactory metricsFactory, @NonNull String str) throws RemoteException, UnrecognizedMarketplaceException, IllegalArgumentException, SecurityException {
        if (context != null && metricsFactory != null && str != null) {
            try {
                ObfuscatedMarketplaceId valueOf = ObfuscatedMarketplaceId.valueOf(str);
                String preloadId = getPreloadId(context, metricsFactory);
                if (preloadId != null) {
                    return String.format(Locale.ENGLISH, AttributionLocationManager.COUNTRY_REGION_FORMAT, preloadId, valueOf.toRegionId());
                }
                return null;
            } catch (IllegalArgumentException unused) {
                metricsFactory.mo4086newPmetMetrics(ERROR_METRIC_NAME).addProperty("obfuscatedMarketplaceId", str).addCount(UnrecognizedMarketplaceException.class.getSimpleName(), 1.0d).record();
                return null;
            }
        }
        throw new IllegalArgumentException("None of the arguments may be null.");
    }

    @Override // com.amazon.appmanager.lib.PreloadManager
    @Nullable
    public String getPreloadAssociateTagWithoutRegionId(@NonNull Context context, @NonNull MetricsFactory metricsFactory) throws RemoteException, IllegalArgumentException, SecurityException {
        if (context != null && metricsFactory != null) {
            return getPreloadId(context, metricsFactory);
        }
        throw new IllegalArgumentException("None of the arguments may be null.");
    }

    protected boolean isSignatureValid(Context context, String str) {
        if (this.signaturesAccepted == null) {
            this.signaturesAccepted = new HashSet();
            this.signaturesAccepted.add(SIGNATURE_AMAZON);
            try {
                for (Signature signature : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
                    this.signaturesAccepted.add(signature);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        try {
            for (Signature signature2 : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                if (this.signaturesAccepted.contains(signature2)) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        return false;
    }
}
