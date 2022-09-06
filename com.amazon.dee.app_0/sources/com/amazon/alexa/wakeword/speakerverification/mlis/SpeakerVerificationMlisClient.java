package com.amazon.alexa.wakeword.speakerverification.mlis;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.comms.mediaInsights.Trace;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.davs.NetworkManager;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentUtterance;
import com.amazon.alexa.wakeword.speakerverification.enrollment.IdentityServiceTokenProvider;
import com.amazon.alexa.wakeword.speakerverification.errors.TrainingFailure;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsReporter;
import com.amazon.alexa.wakeword.speakerverification.mlis.MlisEndpointUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class SpeakerVerificationMlisClient {
    private static final short AUDIO_FORMAT_PCM = 1;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BYTE_DATA_FIELD_SIZE = 36;
    private static final int BYTE_FIELD_SIZE = 4;
    private static final String COMPLETE_PATH = "complete";
    private static final String DATA_HEADER = "data";
    private static final String FILE_FORMAT = "WAVE";
    private static final String FORMAT_HEADER = "fmt ";
    private static final int FORMAT_HEADER_BLOCK_SIZE = 16;
    private static final String JSON_DATA = "application/json";
    private static final int NUMBER_OF_CHANNELS = 1;
    private static final String PARTS_PATH = "parts";
    private static final int RESPONSE_204 = 204;
    private static final int RESPONSE_400 = 400;
    private static final int RESPONSE_EMPTY_UPLOAD_ID_FAILURE = -2;
    private static final int RESPONSE_MANIFEST_FAILURE = -5;
    private static final int RESPONSE_UPLOAD_AUDIO_PART_FAILURE = -3;
    private static final int RESPONSE_UPLOAD_METADATA_PART_FAILURE = -4;
    private static final int RESPONSE_UPLOAD_NOT_COMPLETE_FAILURE = -1;
    private static final String RIFF_HEADER_ID = "RIFF";
    private static final int RIFF_HEADER_SIZE = 44;
    private static final int SAMPLE_RATE = 16000;
    private static final String TAG = "SpeakerVerificationMlisClient";
    private static final String UPLOAD_PATH = "uploads";
    private Context mContext;
    private final Lazy<CrashReportRecorder> mCrashReportRecorderLazy;
    private EnrollmentUtterance mEnrollmentUtterance;
    private IdentityServiceTokenProvider mIdentityServiceTokenProvider;
    private JSONObject mManifest;
    private NetworkManager mNetworkManager;
    private Parts mParts;
    private SpeakerVerificationManifest mSpeakerVerificationManifest;
    private SpeakerVerificationMetricsReporter mSpeakerVerificationMetricsReporter;
    private SpeakerVerificationAudio mSvAudio;
    private SpeakerVerificationMetadata mSvMetadata;

    public SpeakerVerificationMlisClient(Context context) {
        this.mIdentityServiceTokenProvider = new IdentityServiceTokenProvider((IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class));
        this.mNetworkManager = new NetworkManager(this.mIdentityServiceTokenProvider);
        this.mContext = context;
        this.mSpeakerVerificationMetricsReporter = new SpeakerVerificationMetricsReporter(context, SpeakerVerificationMetricsReporter.MetricsSource.ENROLLMENT);
        this.mCrashReportRecorderLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).crashReportRecorderLazy();
    }

    private byte[] audioAsbytes(short[] sArr) {
        ByteBuffer allocate = ByteBuffer.allocate(sArr.length * 2);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        for (short s : sArr) {
            allocate.putShort(s);
        }
        return injectRiffHeader(allocate.array());
    }

    private static String getEndPointFromMarketplace(Marketplace marketplace) {
        Preconditions.notNull(marketplace, "marketplace should not be null");
        String endPoint = MlisEndpointUtil.RegionEndpoint.fromRegion(marketplace.getRegion().toString()).getEndPoint();
        GeneratedOutlineSupport1.outline167("Setting MLIS Endpoint: ", endPoint, TAG);
        return endPoint;
    }

    private String getEndpoint() {
        return getEndPointFromMarketplace(getMarketplace());
    }

    private Marketplace getMarketplace() {
        return this.mIdentityServiceTokenProvider.getMarketPlace();
    }

    private Response getMlisResponse(Request request) throws IOException {
        return this.mNetworkManager.getUnauthorizedHttpClient().newCall(request).execute();
    }

    private boolean uploadAudioPart(String str) {
        return uploadPart(str, this.mParts.getSvAudio().getId(), this.mParts.getSvAudio().getContentType(), audioAsbytes(this.mParts.getSvAudio().getAudio()));
    }

    private boolean uploadMetadataPart(String str) {
        return uploadPart(str, this.mParts.getSvMetadata().getId(), this.mParts.getSvMetadata().getContentType(), this.mParts.getSvMetadata().getMetadata());
    }

    int completeUpload(String str) {
        GeneratedOutlineSupport1.outline167("complete upload with ", str, TAG);
        try {
            Response mlisResponse = getMlisResponse(new Request.Builder().url(Uri.parse(getEndpoint()).buildUpon().appendPath(UPLOAD_PATH).appendPath(str).appendPath(COMPLETE_PATH).build().toString()).post(RequestBody.create((MediaType) null, new byte[0])).header("Authorization", getToken()).build());
            if (mlisResponse.code() == 204) {
                Log.d(TAG, "Complete upload finished successfully");
            } else if (mlisResponse.code() == 400) {
                Log.d(TAG, "Complete upload failed with response 400");
            } else {
                String str2 = TAG;
                Log.d(str2, "Complete upload failed with response: " + mlisResponse.code());
            }
            return mlisResponse.code();
        } catch (IOException e) {
            String str3 = TAG;
            Log.e(str3, "IO Exception " + e);
            onNonFatalError(e, "completeUpload");
            return -1;
        }
    }

    JSONObject createSpeakerVerificationManifest() throws JSONException {
        return this.mSpeakerVerificationManifest.createSVManifest();
    }

    @VisibleForTesting
    String createUpload(JSONObject jSONObject) {
        String uri = Uri.parse(getEndpoint()).buildUpon().appendPath(UPLOAD_PATH).build().toString();
        String str = TAG;
        Log.d(str, "Create upload for mManifest " + jSONObject);
        String str2 = "";
        try {
            Response mlisResponse = getMlisResponse(new Request.Builder().url(uri).addHeader("Authorization", getToken()).post(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).build());
            if (mlisResponse.isSuccessful() && mlisResponse.body() != null) {
                str2 = new JSONObject(mlisResponse.body().string()).getString("id");
            }
        } catch (IOException e) {
            String str3 = TAG;
            Log.e(str3, "IO Exception occured " + e);
            onNonFatalError(e, "createUpload");
        } catch (JSONException e2) {
            String str4 = TAG;
            Log.e(str4, "Json Exception occured" + e2);
            onNonFatalError(e2, "createUpload");
        }
        GeneratedOutlineSupport1.outline167("Upload id ", str2, TAG);
        return str2;
    }

    public int getBitRate() {
        return Trace.PAYLOAD_DATA_MAX_LENGTH;
    }

    public int getBlockAlign() {
        return 2;
    }

    @VisibleForTesting
    String getToken() {
        return this.mIdentityServiceTokenProvider.getToken() != null ? this.mIdentityServiceTokenProvider.getToken().getValue() : "";
    }

    public byte[] headerToByteArray(int i) {
        ByteBuffer order = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN);
        try {
            order.put(RIFF_HEADER_ID.getBytes("ASCII"), 0, 4);
            order.putInt(i + 36);
            order.put("WAVE".getBytes("ASCII"), 0, 4);
            order.put("fmt ".getBytes("ASCII"), 0, 4);
            order.putInt(16);
            order.putShort((short) 1);
            order.putShort((short) 1);
            order.putInt(16000);
            order.putInt(getBitRate());
            order.putShort((short) getBlockAlign());
            order.putShort((short) 16);
            order.put("data".getBytes("ASCII"), 0, 4);
            order.putInt(i);
        } catch (IOException unused) {
            Log.e(TAG, "Exception in byte array");
        }
        return order.array();
    }

    public byte[] injectRiffHeader(byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 44);
        allocate.put(headerToByteArray(bArr.length));
        allocate.put(bArr);
        return allocate.array();
    }

    public void onNonFatalError(@NonNull Exception exc, @NonNull String str) {
        this.mCrashReportRecorderLazy.mo358get().reportNonFatalCrash(this.mContext, str, exc);
    }

    public boolean startMlisUpload(EnrollmentUtterance enrollmentUtterance) {
        this.mEnrollmentUtterance = enrollmentUtterance;
        this.mSpeakerVerificationManifest = new SpeakerVerificationManifest();
        this.mSvMetadata = new SpeakerVerificationMetadata(enrollmentUtterance.getMetadata());
        this.mSvAudio = new SpeakerVerificationAudio(enrollmentUtterance.getAudio());
        this.mParts = new Parts(this.mSvAudio, this.mSvMetadata);
        try {
            this.mManifest = createSpeakerVerificationManifest();
            int upload = upload();
            if (upload == 204) {
                this.mSpeakerVerificationMetricsReporter.onUploadUtterancesSuccess();
                return true;
            }
            this.mSpeakerVerificationMetricsReporter.onUploadUtterancesFailure(TrainingFailure.UPLOAD_ENROLLMENT_UTTERANCES, upload);
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            onNonFatalError(e, "startMlisUpload");
            this.mSpeakerVerificationMetricsReporter.onUploadUtterancesFailure(TrainingFailure.UPLOAD_ENROLLMENT_UTTERANCES, -5);
            return false;
        }
    }

    public int upload() {
        String createUpload = createUpload(this.mManifest);
        if (StringUtils.isNotEmpty(createUpload)) {
            if (!uploadAudioPart(createUpload)) {
                return -3;
            }
            if (uploadMetadataPart(createUpload)) {
                return completeUpload(createUpload);
            }
            return -4;
        }
        return -2;
    }

    @VisibleForTesting
    boolean uploadPart(String str, int i, String str2, byte[] bArr) {
        String uri = Uri.parse(getEndpoint()).buildUpon().appendPath(UPLOAD_PATH).appendPath(str).appendPath(PARTS_PATH).appendPath(Integer.toString(i)).build().toString();
        String str3 = TAG;
        Log.d(str3, "Uploading part id: " + i);
        try {
            Response mlisResponse = getMlisResponse(new Request.Builder().url(uri).header("Authorization", getToken()).post(RequestBody.create(MediaType.parse(str2), bArr)).build());
            if (mlisResponse.code() == 204) {
                Log.d(TAG, "Part upload success");
                return true;
            }
            String str4 = TAG;
            Log.d(str4, "Part upload failed with response " + mlisResponse.code());
            return false;
        } catch (IOException e) {
            String str5 = TAG;
            Log.e(str5, "IO Exception occurred " + e);
            onNonFatalError(e, "uploadPart");
            return false;
        }
    }

    @VisibleForTesting
    public SpeakerVerificationMlisClient(@NonNull Context context, @NonNull IdentityServiceTokenProvider identityServiceTokenProvider, @NonNull NetworkManager networkManager, @NonNull SpeakerVerificationMetricsReporter speakerVerificationMetricsReporter, @NonNull Lazy<CrashReportRecorder> lazy) {
        this.mContext = context;
        this.mIdentityServiceTokenProvider = identityServiceTokenProvider;
        this.mNetworkManager = networkManager;
        this.mSpeakerVerificationMetricsReporter = speakerVerificationMetricsReporter;
        this.mCrashReportRecorderLazy = lazy;
    }
}
