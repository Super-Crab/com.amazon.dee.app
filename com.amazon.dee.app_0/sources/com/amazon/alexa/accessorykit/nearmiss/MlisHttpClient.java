package com.amazon.alexa.accessorykit.nearmiss;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.http.HttpBody;
import com.amazon.alexa.accessory.internal.http.HttpCall;
import com.amazon.alexa.accessory.internal.http.HttpMethod;
import com.amazon.alexa.accessory.internal.http.HttpRequest;
import com.amazon.alexa.accessory.internal.http.JsonHttpBody;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.nearmiss.Audio;
import com.amazon.alexa.accessory.nearmiss.Data;
import com.amazon.alexa.accessory.nearmiss.DataPart;
import com.amazon.alexa.accessory.nearmiss.MetaData;
import com.amazon.alexa.accessory.nearmiss.MlisClient;
import com.amazon.alexa.accessory.nearmiss.NearMissManifest;
import com.amazon.alexa.accessorykit.interprocess.environment.AccessoryEnvironmentServicePreferences;
import com.amazon.alexa.accessorykit.nearmiss.MlisEndpointUtil;
import com.amazon.alexa.accessorykit.utils.AccessTokenUtils;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class MlisHttpClient implements MlisClient {
    private Context context;
    private String testEndpoint;
    private final UserSupplier userSupplier;

    public MlisHttpClient(Context context, UserSupplier userSupplier) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(userSupplier, "userSupplier");
        this.context = context;
        this.userSupplier = userSupplier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: completeUpload */
    public Completable lambda$upload$1$MlisHttpClient(final String str, final String str2) {
        return AccessTokenUtils.getAccessToken(this.userSupplier).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.nearmiss.-$$Lambda$MlisHttpClient$tCWqzDOArUBhBXpgjEd8D4V3IL4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MlisHttpClient.lambda$completeUpload$2(str, str2, (String) obj);
            }
        });
    }

    private Single<String> createUpload(final NearMissManifest nearMissManifest, final String str) {
        return AccessTokenUtils.getAccessToken(this.userSupplier).flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.nearmiss.-$$Lambda$MlisHttpClient$4Sd-HrpJ9qIiyGym1M6uOjAKFGs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MlisHttpClient.lambda$createUpload$7(NearMissManifest.this, str, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CompletableSource lambda$completeUpload$2(String str, String str2, String str3) throws Throwable {
        Logger.d("MlisHttpClient completing upload with id=%s", str);
        HttpRequest.Builder createBuilder = HttpRequest.createBuilder();
        return createBuilder.url(str2 + "/uploads/" + str + "/complete").method(HttpMethod.POST).header("Authorization", str3).build().newCall().executeCompletable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SingleSource lambda$createUpload$7(NearMissManifest nearMissManifest, String str, String str2) throws Throwable {
        Logger.d("MlisHttpClient creating upload for manifest %s", nearMissManifest.toJsonObject());
        HttpRequest.Builder createBuilder = HttpRequest.createBuilder();
        return createBuilder.url(str + "/uploads").method(HttpMethod.POST).header("Authorization", str2).body(new JsonHttpBody(nearMissManifest)).build().newCall().executeSingle().map($$Lambda$MlisHttpClient$cp9y4Mqsg9D6c9pYqvqzmALjNHY.INSTANCE).map($$Lambda$MlisHttpClient$Qd_euoQ4L8qNydYltUHylJwvyaE.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ JSONObject lambda$null$5(HttpCall.HttpResult httpResult) throws Throwable {
        String str = new String(httpResult.response);
        if (httpResult.statuseCode == 201) {
            try {
                return new JSONObject(str);
            } catch (JSONException e) {
                throw new IOException(e);
            }
        }
        throw new IOException("Bad status code " + httpResult);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CompletableSource lambda$uploadJsonPart$4(String str, int i, String str2, JsonObjectSerializable jsonObjectSerializable, String str3) throws Throwable {
        Logger.d("MlisHttpClient uploading json part. uploadId=%s, partId=%d", str, Integer.valueOf(i));
        HttpRequest.Builder createBuilder = HttpRequest.createBuilder();
        return createBuilder.url(str2 + "/uploads/" + str + "/parts/" + i).method(HttpMethod.POST).header("Authorization", str3).body(new JsonHttpBody(jsonObjectSerializable)).build().newCall().executeCompletable();
    }

    private Completable uploadJsonPart(final String str, final int i, final JsonObjectSerializable jsonObjectSerializable, final String str2) {
        return AccessTokenUtils.getAccessToken(this.userSupplier).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.nearmiss.-$$Lambda$MlisHttpClient$KytocQyKEaWQwpsnirhF2WBS_is
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MlisHttpClient.lambda$uploadJsonPart$4(str, i, str2, jsonObjectSerializable, (String) obj);
            }
        });
    }

    private Completable uploadPart(final String str, final int i, final String str2, final Source source, final String str3) {
        return AccessTokenUtils.getAccessToken(this.userSupplier).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.nearmiss.-$$Lambda$MlisHttpClient$yXrEslikwN1UJQ70fspCUU2eEDs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MlisHttpClient.this.lambda$uploadPart$3$MlisHttpClient(str, i, str2, str3, source, (String) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$upload$0$MlisHttpClient(NearMissManifest nearMissManifest, String str, String str2) throws Throwable {
        Logger.d("MlisHttpClient starting upload");
        Audio audio = nearMissManifest.getParts().getAudio();
        Data data = audio.getData();
        DataPart dataPart = data.getDataPart();
        MetaData metaData = audio.getMetaData();
        return uploadJsonPart(str2, metaData.getMetaDataPart().getId(), metaData.getPayload(), str).andThen(uploadPart(str2, dataPart.getId(), dataPart.getContentType().toString(), data.getPayload(), str)).andThen(Single.just(str2));
    }

    public /* synthetic */ CompletableSource lambda$uploadPart$3$MlisHttpClient(String str, int i, final String str2, String str3, final Source source, String str4) throws Throwable {
        Logger.d("MlisHttpClient uploading part. uploadId=%s, partId=%d, mimeType=%s", str, Integer.valueOf(i), str2);
        HttpRequest.Builder createBuilder = HttpRequest.createBuilder();
        return createBuilder.url(str3 + "/uploads/" + str + "/parts/" + i).method(HttpMethod.POST).header("Authorization", str4).body(new HttpBody() { // from class: com.amazon.alexa.accessorykit.nearmiss.MlisHttpClient.1
            @Override // com.amazon.alexa.accessory.internal.http.HttpBody
            public String getContentType() {
                return str2;
            }

            @Override // com.amazon.alexa.accessory.internal.http.HttpBody
            public void writeTo(Sink sink) throws IOException {
                IOUtils.transfer(source, sink);
            }
        }).build().newCall().executeCompletable();
    }

    @VisibleForTesting
    void setTestEndpoint(String str) {
        this.testEndpoint = str;
    }

    @Override // com.amazon.alexa.accessory.nearmiss.MlisClient
    public Completable upload(final NearMissManifest nearMissManifest) {
        Preconditions.notNull(nearMissManifest, "manifest");
        Logger.d("MlisHttpClient uploading manifest: " + nearMissManifest);
        String alexaApiHost = AccessoryEnvironmentServicePreferences.getInstance(this.context).getAlexaApiHost();
        final String str = this.testEndpoint;
        if (str == null) {
            str = MlisEndpointUtil.RegionEndpoint.dataRegionFromApiHost(alexaApiHost).getEndPoint();
        }
        return createUpload(nearMissManifest, str).flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.nearmiss.-$$Lambda$MlisHttpClient$GuG4zPyv1q4nIxGfTc6dZmoinK4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MlisHttpClient.this.lambda$upload$0$MlisHttpClient(nearMissManifest, str, (String) obj);
            }
        }).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.nearmiss.-$$Lambda$MlisHttpClient$ncMxM6hBU0mrVDxfK44GoXXNLKE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MlisHttpClient.this.lambda$upload$1$MlisHttpClient(str, (String) obj);
            }
        }).subscribeOn(Schedulers.io());
    }
}
