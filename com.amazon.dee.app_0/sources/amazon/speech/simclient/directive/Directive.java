package amazon.speech.simclient.directive;

import amazon.speech.simclient.directive.Piper;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public class Directive implements Parcelable {
    static final int DATA_SIZE_THRESHOLD = 32768;
    private static final String EMPTY_STRING = "";
    private static final int PARCEL_VERSION_EVENT_CORRELATION_TOKEN = 2;
    private static final int PARCEL_VERSION_FULL_JSON = 1;
    private final String mCorrelationToken;
    private final String mDirectiveId;
    private final String mEndpoint;
    private final String mEventCorrelationToken;
    private final String mFullJson;
    private final DirectiveKeys mKeys;
    private final String mName;
    private final String mNamespace;
    private final String mPayload;
    private final String mSequenceId;
    private Integer mServerParcelVersion;
    private static final String TAG = GeneratedOutlineSupport1.outline39(Directive.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private static final HandlerWrapper HANDLER = new HandlerWrapper(new Handler(Looper.getMainLooper()));
    static Piper.Factory FACTORY = Piper.Factory.DEFAULT;
    public static final Parcelable.Creator<Directive> CREATOR = new Parcelable.Creator<Directive>() { // from class: amazon.speech.simclient.directive.Directive.1
        private String readFromPipe(ParcelFileDescriptor parcelFileDescriptor) {
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
            try {
                Scanner useDelimiter = new Scanner(autoCloseInputStream, StandardCharsets.UTF_8.name()).useDelimiter("\\A");
                return useDelimiter.hasNext() ? useDelimiter.next() : null;
            } finally {
                try {
                    autoCloseInputStream.close();
                } catch (IOException unused) {
                }
            }
        }

        private String readFullJson(Parcel parcel) {
            if (parcel.hasFileDescriptors()) {
                String unused = Directive.TAG;
                try {
                    return readFromPipe((ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel));
                } catch (NullPointerException e) {
                    Log.e(Directive.TAG, "Failed to read full JSON from pipe", e);
                    return null;
                }
            }
            String unused2 = Directive.TAG;
            return parcel.readString();
        }

        private String readPayload(Parcel parcel) {
            if (parcel.hasFileDescriptors()) {
                String unused = Directive.TAG;
                return readFromPipe((ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel));
            }
            String unused2 = Directive.TAG;
            return parcel.readString();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Directive mo37createFromParcel(Parcel parcel) {
            String str;
            String str2;
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readPayload = readPayload(parcel);
            String readString6 = parcel.readString();
            int readInt = parcel.readInt();
            String str3 = null;
            if (readInt >= 2) {
                str = parcel.readString();
                str2 = parcel.readString();
            } else {
                str = null;
                str2 = null;
            }
            if (readInt >= 1) {
                str3 = readFullJson(parcel);
            }
            return new Directive(readString, readString2, readString3, readString4, new DirectiveKeys(readString5), readPayload, readString6, str3, str, str2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Directive[] mo38newArray(int i) {
            return new Directive[i];
        }
    };

    public Directive(String str, String str2, String str3, String str4, DirectiveKeys directiveKeys, String str5) {
        this(str, str2, str3, str4, directiveKeys, str5, null);
    }

    private void writeFullJson(Parcel parcel, int i) {
        ParcelFileDescriptor writeToPipe = describeContents() == 1 ? writeToPipe(getFullJson()) : null;
        if (writeToPipe != null) {
            writeToPipe.writeToParcel(parcel, i);
        } else {
            parcel.writeString(getFullJson());
        }
    }

    private void writePayload(Parcel parcel, int i) {
        ParcelFileDescriptor writeToPipe = describeContents() == 1 ? writeToPipe(this.mPayload) : null;
        if (writeToPipe != null) {
            writeToPipe.writeToParcel(parcel, i);
        } else {
            parcel.writeString(this.mPayload);
        }
    }

    private ParcelFileDescriptor writeToPipe(String str) {
        try {
            return new DataWriter(EXECUTOR, FACTORY, HANDLER).writeToPipe(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), null);
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        int length = this.mPayload.length() * 2;
        if (!TextUtils.isEmpty(getFullJson())) {
            length += getFullJson().length() * 2;
        }
        return length > 32768 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Directive)) {
            return false;
        }
        return this.mDirectiveId.equals(((Directive) obj).getDirectiveId());
    }

    public String getContentString() {
        return String.format("(%s_%s dirId:%s seqId:%s keys:%s payload:%s correlationToken:%s eventCorrelationToken:%s)", getNamespace(), getName(), getDirectiveId(), getSequenceId(), getKeys(), getPayload(), getCorrelationToken(), getEventCorrelationToken());
    }

    public String getCorrelationToken() {
        return this.mCorrelationToken;
    }

    public String getDirectiveId() {
        return this.mDirectiveId;
    }

    public String getEndpoint() {
        return this.mEndpoint;
    }

    public String getEventCorrelationToken() {
        return this.mEventCorrelationToken;
    }

    public String getFullJson() {
        return TextUtils.isEmpty(this.mFullJson) ? "" : this.mFullJson;
    }

    public DirectiveKeys getKeys() {
        return this.mKeys;
    }

    public String getName() {
        return this.mName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public String getPayload() {
        return this.mPayload;
    }

    public String getSequenceId() {
        return this.mSequenceId;
    }

    public int hashCode() {
        return this.mDirectiveId.hashCode();
    }

    void setServerParcelVersion(int i) {
        this.mServerParcelVersion = Integer.valueOf(i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDirectiveId);
        parcel.writeString(this.mSequenceId);
        parcel.writeString(this.mNamespace);
        parcel.writeString(this.mName);
        parcel.writeString(this.mKeys.getRawJson());
        writePayload(parcel, i);
        parcel.writeString(this.mCorrelationToken);
        parcel.writeInt(2);
        Integer num = this.mServerParcelVersion;
        if (num == null || num.intValue() >= 2) {
            parcel.writeString(this.mEventCorrelationToken);
            parcel.writeString(this.mEndpoint);
        }
        Integer num2 = this.mServerParcelVersion;
        if (num2 == null || num2.intValue() >= 1) {
            writeFullJson(parcel, i);
        }
    }

    public Directive(String str, String str2, String str3, String str4, DirectiveKeys directiveKeys, String str5, String str6) {
        this(str, str2, str3, str4, directiveKeys, str5, str6, null);
    }

    public Directive(String str, String str2, String str3, String str4, DirectiveKeys directiveKeys, String str5, String str6, String str7) {
        this(str, str2, str3, str4, directiveKeys, str5, str6, str7, null);
    }

    public Directive(String str, String str2, String str3, String str4, DirectiveKeys directiveKeys, String str5, String str6, String str7, String str8) {
        this(str, str2, str3, str4, directiveKeys, str5, str6, str7, str8, null);
    }

    public Directive(String str, String str2, String str3, String str4, DirectiveKeys directiveKeys, String str5, String str6, String str7, String str8, String str9) {
        if (str != null) {
            if (str3 == null) {
                throw new IllegalArgumentException("Namespace null");
            }
            if (str4 == null) {
                throw new IllegalArgumentException("Name null");
            }
            if (directiveKeys == null) {
                throw new IllegalArgumentException("Keys null");
            }
            if (str5 != null) {
                this.mDirectiveId = str;
                this.mSequenceId = str2;
                this.mNamespace = str3;
                this.mName = str4;
                this.mKeys = directiveKeys;
                this.mPayload = str5;
                this.mCorrelationToken = str6;
                this.mFullJson = str7;
                this.mEventCorrelationToken = str8;
                this.mEndpoint = str9;
                return;
            }
            throw new IllegalArgumentException("Payload null");
        }
        throw new IllegalArgumentException("DirectiveId null");
    }
}
