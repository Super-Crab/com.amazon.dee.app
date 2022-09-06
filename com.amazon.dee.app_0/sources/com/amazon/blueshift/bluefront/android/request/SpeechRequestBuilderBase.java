package com.amazon.blueshift.bluefront.android.request;

import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.amazon.bluefront.api.common.Customer;
import com.amazon.bluefront.api.common.Device;
import com.amazon.bluefront.api.common.TranslateToObfuscatedCustomerIdFrom;
import com.amazon.bluefront.api.v2.ServiceParameters;
import com.amazon.blueshift.bluefront.android.common.AndroidDevice;
import com.amazon.blueshift.bluefront.android.common.BluefrontCustomer;
import com.amazon.blueshift.bluefront.android.common.RequestTimeouts;
import com.amazon.blueshift.bluefront.android.request.SpeechRequestBuilderBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes11.dex */
public abstract class SpeechRequestBuilderBase<E extends SpeechRequestBuilderBase<E, OUTPUT, CONTEXT>, OUTPUT, CONTEXT> {
    private static final String ANDROID = "Android";
    static final String APP_VERSION = "appVersion";
    static final String AUDIO_ROUTE = "audioRoute";
    static final String CARRIER = "carrier";
    static final String DEVICE_BRAND = "deviceBrand";
    static final String DEVICE_MANUFACTURER = "deviceManufacturer";
    static final String DEVICE_MODEL = "deviceModel";
    static final String DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
    static final String LANGUAGE = "language";
    static final String LATITUDE = "latitude";
    static final String LOCALE = "locale";
    private static final String LOCALE_KEY = "locale";
    static final String LONGTITUDE = "longitude";
    private static final String MAP_TOKEN_HEADER = "MAP-Credential";
    static final String NETWORK_TYPE = "networkType";
    static final String OS = "os";
    static final String OS_VERSION = "osVersion";
    static final String PLATFORM = "platform";
    private static final String PROFILE_KEY = "profile";
    static final String REQUEST_ID = "requestId";
    private final Optional<CONTEXT> mContext;
    private final Class<OUTPUT> mOutputForm;
    protected static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String TAG = SpeechRequestBuilderBase.class.getCanonicalName();
    private final Uri.Builder mUriBuilder = new Uri.Builder().scheme("https").encodedAuthority("blue-ash.amazon.com");
    private RequestTimeouts mTimeouts = new RequestTimeouts();
    private String mProfile = null;
    private Locale mLocale = null;
    private String mRequestId = null;
    private AndroidDevice mDevice = null;
    private BluefrontCustomer mCustomer = null;
    private final Map<String, Object> mMetadata = new HashMap();
    private final Map<String, String> mHeaders = new HashMap();
    private String mPath = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public SpeechRequestBuilderBase(Class<OUTPUT> cls, @Nullable CONTEXT context) {
        Preconditions.checkNotNull(cls, "outputForm cannot be null");
        this.mOutputForm = cls;
        if (context != null) {
            this.mContext = Optional.of(context);
        } else {
            this.mContext = Optional.absent();
        }
    }

    private void addClientMetadata() {
        if (this.mDevice.getId() != null) {
            this.mMetadata.put(APP_VERSION, this.mDevice.getAppVersion());
            this.mMetadata.put(DEVICE_BRAND, this.mDevice.getBrand());
            this.mMetadata.put(DEVICE_MANUFACTURER, this.mDevice.getManufacturer());
            this.mMetadata.put("deviceSerialNumber", this.mDevice.getSerialNumber());
            this.mMetadata.put("deviceModel", this.mDevice.getModel());
            this.mMetadata.put("carrier", this.mDevice.getCarrier());
            this.mMetadata.put(NETWORK_TYPE, this.mDevice.getConnectedNetworkType().toString());
            if (this.mDevice.getLocation() != null) {
                this.mMetadata.put("latitude", String.format("%.1f", Double.valueOf(this.mDevice.getLocation().getLatitude())));
                this.mMetadata.put("longitude", String.format("%.1f", Double.valueOf(this.mDevice.getLocation().getLongitude())));
            }
        }
        Locale locale = this.mLocale;
        if (locale != null) {
            this.mMetadata.put(LANGUAGE, locale.getLanguage());
            this.mMetadata.put("locale", getLocaleString());
        }
        this.mMetadata.put(AUDIO_ROUTE, "Android Recognition Default");
        this.mMetadata.put("os", "Android");
        this.mMetadata.put(OS_VERSION, Build.VERSION.RELEASE);
        this.mMetadata.put("platform", "Android");
        String str = this.mRequestId;
        if (str != null) {
            this.mMetadata.put("requestId", str);
        }
    }

    public abstract SpeechRequest<OUTPUT> build();

    /* JADX INFO: Access modifiers changed from: protected */
    public ServiceParameters buildBaseParameters() {
        String writeValueAsString;
        Preconditions.checkNotNull(this.mDevice, "device cannot be null");
        addClientMetadata();
        ServiceParameters serviceParameters = new ServiceParameters();
        String str = this.mRequestId;
        if (str != null) {
            serviceParameters.setRequestId(str);
        }
        if (this.mDevice.getId() != null) {
            Device device = new Device();
            device.setDeviceSerialNumber(this.mDevice.getId());
            serviceParameters.setDevice(device);
        }
        if (this.mCustomer != null) {
            Customer customer = new Customer();
            customer.setIdentifier(this.mCustomer.getCustomerId());
            customer.setTranslateToObfuscatedCustomerIdFrom(this.mCustomer.getIdentifierType().toSerializedForm());
            serviceParameters.setCustomer(customer);
        }
        try {
            if (!this.mMetadata.isEmpty()) {
                HashMap hashMap = new HashMap();
                for (Map.Entry<String, Object> entry : this.mMetadata.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        writeValueAsString = (String) value;
                    } else {
                        writeValueAsString = MAPPER.writeValueAsString(entry.getValue());
                    }
                    hashMap.put(entry.getKey(), writeValueAsString);
                }
                serviceParameters.setMetadata(hashMap);
            }
            return serviceParameters;
        } catch (JsonProcessingException e) {
            Log.e(TAG, "Failed to serialize request parameters!");
            throw new RuntimeException(e);
        }
    }

    public E customer(String str, TranslateToObfuscatedCustomerIdFrom translateToObfuscatedCustomerIdFrom) {
        this.mCustomer = new BluefrontCustomer(str, translateToObfuscatedCustomerIdFrom);
        return this;
    }

    public E device(AndroidDevice androidDevice) {
        this.mDevice = androidDevice;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Uri getCompleteUri() {
        String defaultPath = getDefaultPath();
        String str = this.mPath;
        if (str != null) {
            defaultPath = str;
        }
        this.mUriBuilder.appendEncodedPath(defaultPath);
        return this.mUriBuilder.build();
    }

    public Optional<CONTEXT> getContext() {
        return this.mContext;
    }

    public final BluefrontCustomer getCustomer() {
        return this.mCustomer;
    }

    protected abstract String getDefaultPath();

    public final AndroidDevice getDevice() {
        return this.mDevice;
    }

    public final Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public final Uri getHostname() {
        return this.mUriBuilder.build();
    }

    public final Locale getLocale() {
        return this.mLocale;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getLocaleString() {
        Locale locale = this.mLocale;
        if (locale != null) {
            return locale.getLanguage() + '_' + this.mLocale.getCountry();
        }
        return null;
    }

    public final Map<String, Object> getMetadata() {
        return this.mMetadata;
    }

    public Class<OUTPUT> getOutputForm() {
        return this.mOutputForm;
    }

    public final String getProfile() {
        return this.mProfile;
    }

    public final String getRequestId() {
        return this.mRequestId;
    }

    public final RequestTimeouts getTimeouts() {
        return this.mTimeouts;
    }

    public E header(String str, String str2) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "headerName cannot be empty or null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str2), "headerValue cannot be empty or null");
        Preconditions.checkArgument(!"Content-Type".equals(str), "headerName cannot be \"Content-Type\"");
        this.mHeaders.put(str, str2);
        return this;
    }

    public E hostname(String str) {
        this.mUriBuilder.encodedAuthority(str);
        return this;
    }

    public E locale(Locale locale) {
        Preconditions.checkNotNull(locale, "Locale cannot be null");
        this.mLocale = locale;
        this.mUriBuilder.appendQueryParameter("locale", getLocaleString());
        return this;
    }

    public E mapAccessToken(String str) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "mapAccessToken cannot be empty or null");
        return header(MAP_TOKEN_HEADER, str);
    }

    public E metadata(String str, Object obj) {
        this.mMetadata.put(str, obj);
        return this;
    }

    public E metadataMap(Map<String, Object> map) {
        Preconditions.checkNotNull(map, "Metadata cannot be null");
        this.mMetadata.putAll(map);
        return this;
    }

    public E path(String str) {
        this.mPath = str;
        return this;
    }

    public E profile(String str) {
        Preconditions.checkNotNull(str, "Profile cannot be null");
        this.mProfile = str;
        this.mUriBuilder.appendQueryParameter("profile", this.mProfile);
        return this;
    }

    public E requestId(String str) {
        this.mRequestId = str;
        return this;
    }

    public E timeouts(RequestTimeouts requestTimeouts) {
        this.mTimeouts = requestTimeouts;
        return this;
    }
}
