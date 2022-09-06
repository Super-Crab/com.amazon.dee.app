package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class AlexaContext implements Parcelable {
    public static final Parcelable.Creator<AlexaContext> CREATOR = new Parcelable.Creator<AlexaContext>() { // from class: com.amazon.alexa.api.AlexaContext.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaContext mo780createFromParcel(Parcel parcel) {
            return new AlexaContext(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaContext[] mo781newArray(int i) {
            return new AlexaContext[i];
        }
    };
    private final AlexaHeader alexaHeader;
    private final AlexaPayload alexaPayload;

    /* loaded from: classes6.dex */
    static class AlexaContextBundleAdapter implements u<AlexaContext> {
        static final String KEY_CORRELATION_TOKEN = "correlationToken";
        static final String KEY_NAME = "name";
        static final String KEY_NAMESPACE = "namespace";
        static final String KEY_PAYLOAD = "payload";
        static final String KEY_PAYLOAD_VERSION = "payloadVersion";
        private static final String TAG = "AlexaContextBundleAdapter";

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        @Nullable
        /* renamed from: createFromBundle */
        public AlexaContext mo844createFromBundle(Bundle bundle) {
            Preconditions.notNull(bundle, "Bundle can't be null");
            String string = bundle.getString("payload");
            String string2 = bundle.getString("namespace");
            String string3 = bundle.getString("name");
            String string4 = bundle.getString("correlationToken");
            String string5 = bundle.getString("payloadVersion");
            if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3) || TextUtils.isEmpty(string)) {
                String str = TAG;
                Log.w(str, "Could not create AlexaContext from Bundle: " + bundle);
                return null;
            }
            return new AlexaContext(AlexaHeader.builder().setName(string3).setNamespace(string2).setCorrelationToken(string4).setPayloadVersion(string5).build(), new AlexaPayload(string));
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(AlexaContext alexaContext) {
            Preconditions.notNull(alexaContext, "AlexaContext can't be null");
            Bundle bundle = new Bundle();
            bundle.putString("namespace", alexaContext.getAlexaHeader().getNamespace());
            bundle.putString("name", alexaContext.getAlexaHeader().getName());
            bundle.putString("correlationToken", alexaContext.getAlexaHeader().getCorrelationToken());
            bundle.putString("payloadVersion", alexaContext.getAlexaHeader().getPayloadVersion());
            bundle.putString("payload", alexaContext.getAlexaPayload().getPayload());
            return bundle;
        }
    }

    protected AlexaContext(Parcel parcel) {
        this.alexaHeader = (AlexaHeader) parcel.readParcelable(AlexaHeader.class.getClassLoader());
        this.alexaPayload = (AlexaPayload) parcel.readParcelable(AlexaPayload.class.getClassLoader());
    }

    public AlexaContext(AlexaHeader alexaHeader, AlexaPayload alexaPayload) {
        this.alexaHeader = alexaHeader;
        this.alexaPayload = alexaPayload;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AlexaHeader getAlexaHeader() {
        return this.alexaHeader;
    }

    public AlexaPayload getAlexaPayload() {
        return this.alexaPayload;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.alexaHeader, i);
        parcel.writeParcelable(this.alexaPayload, i);
    }
}
