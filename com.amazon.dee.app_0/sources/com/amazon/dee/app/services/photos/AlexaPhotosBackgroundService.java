package com.amazon.dee.app.services.photos;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundService;
import com.amazon.dee.app.services.photos.PhotoService;
import com.amazon.dee.app.util.Utils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.dee.app.http.CoralService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import rx.Completable;
import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes12.dex */
public class AlexaPhotosBackgroundService implements PhotoService {
    private static final String DEFAULT_IMAGE_EXTENSION = ".jpg";
    private static final String DEFAULT_IMAGE_TYPE = "image/jpg";
    private static final String UPLOAD_URI = "/v1/photos/background";
    private final Context context;
    private final CoralService coralService;
    private final MAPAccountManager mapAccountManager;
    private final TokenManagement tokenManagement;
    private static final String TAG = Log.tag(AlexaPhotosBackgroundService.class);
    private static final Integer MAX_RETRIES = 2;

    @VisibleForTesting
    /* loaded from: classes12.dex */
    public static class UploadRequestInternal {
        public long contentLength;
        public String contentType;
        public String fileInputStream;
        public Map<String, String> headers;
    }

    @VisibleForTesting
    /* loaded from: classes12.dex */
    public static class UploadResponseInternal {
        public String documentId;
        public String urlLocation;
    }

    public AlexaPhotosBackgroundService(@NonNull Context context, @NonNull CoralService coralService) {
        this.context = context;
        this.coralService = coralService;
        this.mapAccountManager = new MAPAccountManager(context);
        this.tokenManagement = new TokenManagement(context);
    }

    private void buildCommonHeaders(CoralService.Request request) throws MAPCallbackErrorException, InterruptedException, ExecutionException {
        String string = this.tokenManagement.getToken(this.mapAccountManager.getAccount(), TokenKeys.getAccessTokenKeyForPackage(this.context.getPackageName()), null, null).get().getString("value_key");
        CoralService.Request withHeader = request.withHeader("Accept", "application/json").withHeader("Content-Type", "application/json").withHeader("X-Request-ID", UUID.randomUUID().toString());
        withHeader.withHeader("Authorization", "Bearer " + string);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Observable lambda$deleteImage$3(String str) {
        PhotoService.DeleteResponse deleteResponse = new PhotoService.DeleteResponse();
        deleteResponse.setId(str);
        return Observable.just(deleteResponse);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Observable lambda$uploadImage$2(UploadResponseInternal uploadResponseInternal) {
        PhotoService.UploadResponse uploadResponse = new PhotoService.UploadResponse();
        uploadResponse.setId(uploadResponseInternal.documentId);
        uploadResponse.setUrl(uploadResponseInternal.urlLocation);
        return Observable.just(uploadResponse);
    }

    @Override // com.amazon.dee.app.services.photos.PhotoService
    public Observable<PhotoService.DeleteResponse> deleteImage(@NonNull final String str) {
        return Observable.defer(new Func0() { // from class: com.amazon.dee.app.services.photos.-$$Lambda$AlexaPhotosBackgroundService$HiRhlqIj_tPj7vuNnGKu6ifkfgM
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return AlexaPhotosBackgroundService.lambda$deleteImage$3(str);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    @Override // com.amazon.dee.app.services.photos.PhotoService
    public Completable initialize() {
        return Completable.complete();
    }

    public /* synthetic */ Observable lambda$uploadImage$0$AlexaPhotosBackgroundService(Uri uri) {
        File file;
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        FileInputStream fileInputStream2 = null;
        try {
            File copyContentStreamToStagingFile = Utils.copyContentStreamToStagingFile(this.context, uri, String.valueOf(new Random().nextInt(100)) + uri.getLastPathSegment() + ".jpg");
            try {
                fileInputStream = new FileInputStream(copyContentStreamToStagingFile);
            } catch (IOException e) {
                file = copyContentStreamToStagingFile;
                e = e;
            } catch (Throwable th) {
                file = copyContentStreamToStagingFile;
                th = th;
            }
            try {
                Utils.copyStream(fileInputStream, byteArrayOutputStream);
                UploadRequestInternal uploadRequestInternal = new UploadRequestInternal();
                uploadRequestInternal.contentType = DEFAULT_IMAGE_TYPE;
                uploadRequestInternal.fileInputStream = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                uploadRequestInternal.contentLength = copyContentStreamToStagingFile.length();
                Observable just = Observable.just(uploadRequestInternal);
                Utils.closeQuietly(fileInputStream);
                Utils.closeQuietly(byteArrayOutputStream);
                if (!copyContentStreamToStagingFile.delete()) {
                    Log.e(TAG, "Failed to delete staged upload file: {}", copyContentStreamToStagingFile.getName());
                }
                return just;
            } catch (IOException e2) {
                file = copyContentStreamToStagingFile;
                e = e2;
                fileInputStream2 = fileInputStream;
                try {
                    Observable error = Observable.error(e);
                    Utils.closeQuietly(fileInputStream2);
                    Utils.closeQuietly(byteArrayOutputStream);
                    if (file != null && !file.delete()) {
                        Log.e(TAG, "Failed to delete staged upload file: {}", file.getName());
                    }
                    return error;
                } catch (Throwable th2) {
                    th = th2;
                    Utils.closeQuietly(fileInputStream2);
                    Utils.closeQuietly(byteArrayOutputStream);
                    if (file != null && !file.delete()) {
                        Log.e(TAG, "Failed to delete staged upload file: {}", file.getName());
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                file = copyContentStreamToStagingFile;
                th = th3;
                fileInputStream2 = fileInputStream;
                Utils.closeQuietly(fileInputStream2);
                Utils.closeQuietly(byteArrayOutputStream);
                if (file != null) {
                    Log.e(TAG, "Failed to delete staged upload file: {}", file.getName());
                }
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            file = null;
        } catch (Throwable th4) {
            th = th4;
            file = null;
        }
    }

    public /* synthetic */ Observable lambda$uploadImage$1$AlexaPhotosBackgroundService(UploadRequestInternal uploadRequestInternal) {
        CoralService.Request request = this.coralService.request(UPLOAD_URI);
        try {
            buildCommonHeaders(request);
            return request.post(uploadRequestInternal).as(UploadResponseInternal.class).toObservable();
        } catch (Exception e) {
            return Observable.error(e);
        }
    }

    @Override // com.amazon.dee.app.services.photos.PhotoService
    public Observable<PhotoService.UploadResponse> uploadImage(@NonNull final Uri uri) {
        return Observable.defer(new Func0() { // from class: com.amazon.dee.app.services.photos.-$$Lambda$AlexaPhotosBackgroundService$oli_WwMnR95Lhjn4s3aX5w9-5hk
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public final Object mo13098call() {
                return AlexaPhotosBackgroundService.this.lambda$uploadImage$0$AlexaPhotosBackgroundService(uri);
            }
        }).switchMap(new Func1() { // from class: com.amazon.dee.app.services.photos.-$$Lambda$AlexaPhotosBackgroundService$BnbE-_bE-wZDFyygqRHOCvx2tx8
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return AlexaPhotosBackgroundService.this.lambda$uploadImage$1$AlexaPhotosBackgroundService((AlexaPhotosBackgroundService.UploadRequestInternal) obj);
            }
        }).switchMap($$Lambda$AlexaPhotosBackgroundService$a132pnR9ZrFtgQGFEbShM1m2cls.INSTANCE).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).retry(MAX_RETRIES.intValue());
    }

    public AlexaPhotosBackgroundService(@NonNull Context context, @NonNull CoralService coralService, @NonNull MAPAccountManager mAPAccountManager, @NonNull TokenManagement tokenManagement) {
        this.context = context;
        this.coralService = coralService;
        this.mapAccountManager = mAPAccountManager;
        this.tokenManagement = tokenManagement;
    }
}
