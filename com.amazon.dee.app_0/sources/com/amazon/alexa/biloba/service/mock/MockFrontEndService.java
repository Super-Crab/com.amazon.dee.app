package com.amazon.alexa.biloba.service.mock;

import com.amazon.alexa.biloba.utils.IOUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
/* loaded from: classes6.dex */
public class MockFrontEndService {
    public static final String TAG = "MockFrontEndService";
    private Map<String, String> resourceMap = new HashMap();
    final Dispatcher dispatcher = new Dispatcher() { // from class: com.amazon.alexa.biloba.service.mock.MockFrontEndService.1
        @Override // okhttp3.mockwebserver.Dispatcher
        public MockResponse dispatch(RecordedRequest recordedRequest) {
            String dataFromFile = MockFrontEndService.this.getDataFromFile(MockFrontEndService.this.getFileNameFromPath(MockFrontEndService.this.getPathFromUrl(recordedRequest.getPath())));
            if (dataFromFile != null) {
                return new MockResponse().setResponseCode(200).setBody(dataFromFile);
            }
            return new MockResponse().setResponseCode(404);
        }
    };
    private MockWebServer mockWebServer = new MockWebServer();

    public MockFrontEndService() {
        this.resourceMap.put("/v1/activities", "res/raw/_v1_activities.json");
        this.resourceMap.put("/v1/cards", "res/raw/_v1_cards.json");
        this.mockWebServer.setDispatcher(this.dispatcher);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDataFromFile(String str) {
        String str2 = TAG;
        LogUtils.i(str2, "Local path for mock data: " + str);
        if (str == null) {
            return null;
        }
        InputStream resourceAsStream = MockFrontEndService.class.getClassLoader().getResourceAsStream(str);
        if (resourceAsStream != null) {
            byte[] bArr = new byte[0];
            try {
                bArr = IOUtils.streamToByteArray(resourceAsStream);
            } catch (IOException e) {
                String str3 = TAG;
                LogUtils.e(str3, "Mock data parsing error " + str);
                e.printStackTrace();
            }
            return new String(bArr);
        }
        String str4 = TAG;
        LogUtils.e(str4, "Mock data unavailable for " + str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getFileNameFromPath(String str) {
        String str2 = this.resourceMap.get(str);
        return (str2 != null || str == null) ? str2 : GeneratedOutlineSupport1.outline75("res/raw/", str.replaceAll("([A-Z])", "_$0").toLowerCase().replaceAll("\\/", "\\_"), ".json");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPathFromUrl(String str) {
        if (str != null) {
            return str.split("\\?")[0];
        }
        return null;
    }

    public String getHostName() {
        return this.mockWebServer.getHostName();
    }

    public int getPort() {
        return this.mockWebServer.getPort();
    }

    public HttpUrl getUrl(String str) {
        return this.mockWebServer.url(str);
    }

    public void start() {
        try {
            this.mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            this.mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
