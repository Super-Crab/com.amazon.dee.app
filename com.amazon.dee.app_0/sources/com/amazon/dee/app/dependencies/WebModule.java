package com.amazon.dee.app.dependencies;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.dependencies.WebModule;
import com.amazon.dee.app.ui.web.AlexaWebView;
import com.amazon.dee.app.ui.web.DeviceInfo;
import com.amazon.dee.app.ui.web.EnvironmentWebNavigator;
import com.amazon.dee.app.ui.web.HouseholdLibraryInfo;
import com.amazon.dee.app.ui.web.JavaScriptBridgeOrchestrator;
import com.amazon.dee.app.ui.web.JavaScriptDelegate;
import com.amazon.dee.app.ui.web.JavaScriptInjector;
import com.amazon.dee.app.ui.web.JavaScriptPlayer;
import com.amazon.dee.app.ui.web.JavaScriptResponse;
import com.amazon.dee.app.ui.web.JavaScriptResponseQueue;
import com.amazon.dee.app.ui.web.WebAppMessagingReceiver;
import com.amazon.dee.app.ui.web.WebNavigator;
import com.amazon.dee.app.ui.web.WebViewDelegate;
import com.amazon.dee.app.ui.web.WebViewJavaScriptLoader;
import com.amazon.dee.app.util.ResourceUtils;
import com.amazon.dee.app.util.WebUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
@Module
/* loaded from: classes12.dex */
public class WebModule {
    AlexaWebView webView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.dee.app.dependencies.WebModule$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass1 implements JavaScriptDelegate {
        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$notifyLoadingDialogDismissed$9$WebModule$1() {
            WebModule.this.webView.notifyLoadingDialogDismissed();
        }

        public /* synthetic */ void lambda$onWebAppReady$8$WebModule$1() {
            WebModule.this.webView.onWebAppReady();
        }

        public /* synthetic */ void lambda$setDevices$3$WebModule$1(List list) {
            WebModule.this.webView.setDevices(list);
        }

        public /* synthetic */ void lambda$setFullScreen$0$WebModule$1(boolean z) {
            WebModule.this.webView.setFullScreen(z);
        }

        public /* synthetic */ void lambda$setHeaderTitle$2$WebModule$1(CharSequence charSequence) {
            WebModule.this.webView.setHeaderTitle(charSequence);
        }

        public /* synthetic */ void lambda$setHeaderVisible$1$WebModule$1(boolean z) {
            WebModule.this.webView.setHeaderVisible(z);
        }

        public /* synthetic */ void lambda$setHouseholdLibraries$12$WebModule$1(List list) {
            WebModule.this.webView.setHouseholdLibraries(list);
        }

        public /* synthetic */ void lambda$setHouseholdVisible$10$WebModule$1(boolean z) {
            WebModule.this.webView.setHouseholdVisible(z);
        }

        public /* synthetic */ void lambda$setNowPlaying$5$WebModule$1(boolean z) {
            WebModule.this.webView.setNowPlaying(z);
        }

        public /* synthetic */ void lambda$setSelectedDevice$4$WebModule$1(DeviceInfo deviceInfo) {
            WebModule.this.webView.setSelectedDevice(deviceInfo);
        }

        public /* synthetic */ void lambda$setSelectedLibrary$11$WebModule$1(HouseholdLibraryInfo householdLibraryInfo) {
            WebModule.this.webView.setSelectedLibrary(householdLibraryInfo);
        }

        public /* synthetic */ void lambda$setYourSkillsTitleAndDisplay$7$WebModule$1(CharSequence charSequence, boolean z) {
            WebModule.this.webView.setYourSkillsTitleAndDisplay(charSequence, z);
        }

        public /* synthetic */ void lambda$setYourSkillsVisible$6$WebModule$1(boolean z) {
            WebModule.this.webView.setYourSkillsVisible(z);
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void notifyLoadingDialogDismissed() {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$gm2w7JHlmhlKk8Tp7ssDl_l5zoo
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$notifyLoadingDialogDismissed$9$WebModule$1();
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void onWebAppReady() {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$YhubIedvzEDqvrm7ud4D9Qgblwc
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$onWebAppReady$8$WebModule$1();
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setDevices(@Nullable final List<DeviceInfo> list) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$b_h5RGIpuPanPXi6RptvE8CWPtQ
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setDevices$3$WebModule$1(list);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setFullScreen(final boolean z) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$G2NJrdQfUvHRGSCzkZLIdv7ilWU
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setFullScreen$0$WebModule$1(z);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setHeaderTitle(@Nullable final CharSequence charSequence) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$7JnkGVBlsxBm2tEMkF9CqGjfPng
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setHeaderTitle$2$WebModule$1(charSequence);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setHeaderVisible(final boolean z) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$TdMv4cc94aURQZMWNBgxIzrihpg
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setHeaderVisible$1$WebModule$1(z);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setHouseholdLibraries(@Nullable final List<HouseholdLibraryInfo> list) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$cCKn-kaN3xYjzXMaXoS4Wtikd-E
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setHouseholdLibraries$12$WebModule$1(list);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setHouseholdVisible(final boolean z) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$qiRzgP2ZJKHhYeTBMEQJczRGpBE
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setHouseholdVisible$10$WebModule$1(z);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setNowPlaying(final boolean z) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$IH4Ona6vY0z3nWtUTvjEry_1f9w
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setNowPlaying$5$WebModule$1(z);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setSelectedDevice(@Nullable final DeviceInfo deviceInfo) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$7-QhHjXjy4aQWyr-iM4tQ2W0PwM
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setSelectedDevice$4$WebModule$1(deviceInfo);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setSelectedLibrary(@Nullable final HouseholdLibraryInfo householdLibraryInfo) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$iMp-gGbbQSyIupi59LtxXyXO24Y
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setSelectedLibrary$11$WebModule$1(householdLibraryInfo);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setYourSkillsTitleAndDisplay(@Nullable final CharSequence charSequence, final boolean z) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$OhEYdqypouhznyo3LwMkc-eH4Tk
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setYourSkillsTitleAndDisplay$7$WebModule$1(charSequence, z);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptDelegate
        public void setYourSkillsVisible(final boolean z) {
            WebModule.this.webView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$1$lirtPCvVK4yr5XwhkhT0cf8-4JM
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass1.this.lambda$setYourSkillsVisible$6$WebModule$1(z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.dee.app.dependencies.WebModule$4  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass4 implements WebViewDelegate {
        AnonymousClass4() {
        }

        @Override // com.amazon.dee.app.ui.web.WebViewDelegate
        public void clearCache() {
            runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$4$tVXChGaFWuYhxNzGufqUMoSsZZw
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass4.this.lambda$clearCache$1$WebModule$4();
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.WebViewDelegate
        public void clearHistory() {
            runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$4$SkNJAT9Hz7pOrGOZQJlHgX7y1Dk
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass4.this.lambda$clearHistory$2$WebModule$4();
                }
            });
        }

        public /* synthetic */ void lambda$clearCache$1$WebModule$4() {
            WebModule.this.webView.clearCache(true);
        }

        public /* synthetic */ void lambda$clearHistory$2$WebModule$4() {
            WebModule.this.webView.clearHistory();
        }

        public /* synthetic */ void lambda$loadUrl$0$WebModule$4(String str) {
            WebModule.this.webView.loadUrl(str);
        }

        public /* synthetic */ void lambda$reload$3$WebModule$4() {
            WebModule.this.webView.reload();
        }

        @Override // com.amazon.dee.app.ui.web.WebViewDelegate
        public void loadUrl(final String str) {
            runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$4$kY5QYaxvaebp17GQOeUmakjM11A
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass4.this.lambda$loadUrl$0$WebModule$4(str);
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.WebViewDelegate
        public void reload() {
            runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$4$vfqvPM5pupvLR0YRGtiGdzHRsjs
                @Override // java.lang.Runnable
                public final void run() {
                    WebModule.AnonymousClass4.this.lambda$reload$3$WebModule$4();
                }
            });
        }

        @Override // com.amazon.dee.app.ui.web.WebViewDelegate
        public void runOnUiThread(@NonNull Runnable runnable) {
            WebModule.this.webView.post(runnable);
        }
    }

    public WebModule(AlexaWebView alexaWebView) {
        this.webView = alexaWebView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$provideWebNavigator$0(EnvironmentWebNavigator environmentWebNavigator, String str) {
        String route = WebUtils.getRoute(str);
        if (!TextUtils.isEmpty(route)) {
            environmentWebNavigator.notifyUriChanged(route);
        }
    }

    @Provides
    @WebScope
    public JavaScriptBridgeOrchestrator provideJavaScriptBridgeOrchestrator(JavaScriptResponseQueue javaScriptResponseQueue) {
        return new JavaScriptBridgeOrchestrator(javaScriptResponseQueue);
    }

    @Provides
    @WebScope
    public JavaScriptDelegate provideJavaScriptDelegate() {
        return new AnonymousClass1();
    }

    @Provides
    @WebScope
    public JavaScriptInjector provideJavaScriptInjector(final Context context, final WebViewJavaScriptLoader webViewJavaScriptLoader) {
        return new JavaScriptInjector() { // from class: com.amazon.dee.app.dependencies.WebModule.2
            @Override // com.amazon.dee.app.ui.web.JavaScriptInjector
            public void inject(@NonNull String str) {
                final String outline72 = GeneratedOutlineSupport1.outline72(str, ";''");
                AlexaWebView alexaWebView = WebModule.this.webView;
                final WebViewJavaScriptLoader webViewJavaScriptLoader2 = webViewJavaScriptLoader;
                alexaWebView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$2$IZbKozTsmbJdLSpAmWXpHqrhFi0
                    @Override // java.lang.Runnable
                    public final void run() {
                        WebViewJavaScriptLoader.this.loadJavascript(outline72);
                    }
                });
            }

            @Override // com.amazon.dee.app.ui.web.JavaScriptInjector
            public void inject(@RawRes int i) {
                String readRawAsString = ResourceUtils.readRawAsString(context, i, null);
                final String outline72 = GeneratedOutlineSupport1.outline72(readRawAsString, ";''");
                if (!TextUtils.isEmpty(readRawAsString)) {
                    AlexaWebView alexaWebView = WebModule.this.webView;
                    final WebViewJavaScriptLoader webViewJavaScriptLoader2 = webViewJavaScriptLoader;
                    alexaWebView.post(new Runnable() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$2$k3Iyx7GUGD-Je0b57W450Y8zhco
                        @Override // java.lang.Runnable
                        public final void run() {
                            WebViewJavaScriptLoader.this.loadJavascript(outline72);
                        }
                    });
                }
            }
        };
    }

    @Provides
    @WebScope
    public WebViewJavaScriptLoader provideJavaScriptLoader() {
        return new WebViewJavaScriptLoader(this.webView);
    }

    @Provides
    @WebScope
    public JavaScriptPlayer provideJavaScriptPlayer(Context context) {
        return new JavaScriptPlayer(context);
    }

    @Provides
    @WebScope
    public JavaScriptResponseQueue provideJavaScriptResponseQueue() {
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(50);
        return new JavaScriptResponseQueue() { // from class: com.amazon.dee.app.dependencies.WebModule.3
            @Override // com.amazon.dee.app.ui.web.JavaScriptResponseQueue
            public boolean enqueue(@NonNull JavaScriptResponse javaScriptResponse) {
                boolean offer;
                synchronized (linkedBlockingQueue) {
                    offer = linkedBlockingQueue.offer(javaScriptResponse);
                }
                return offer;
            }

            @Override // com.amazon.dee.app.ui.web.JavaScriptResponseQueue
            @Nullable
            public JavaScriptResponse poll() {
                JavaScriptResponse javaScriptResponse;
                synchronized (linkedBlockingQueue) {
                    javaScriptResponse = (JavaScriptResponse) linkedBlockingQueue.poll();
                }
                return javaScriptResponse;
            }
        };
    }

    @Provides
    @WebScope
    public WebAppMessagingReceiver provideWebAppMessagingReceiver(WebNavigator webNavigator, JavaScriptInjector javaScriptInjector, Gson gson) {
        return new WebAppMessagingReceiver(webNavigator, javaScriptInjector, gson);
    }

    @Provides
    @WebScope
    public WebNavigator provideWebNavigator(WebViewDelegate webViewDelegate, JavaScriptInjector javaScriptInjector, EnvironmentService environmentService, IdentityService identityService, EventBus eventBus) {
        final EnvironmentWebNavigator environmentWebNavigator = new EnvironmentWebNavigator(webViewDelegate, javaScriptInjector, environmentService, identityService, eventBus);
        this.webView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.amazon.dee.app.dependencies.WebModule.5
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                environmentWebNavigator.initialize();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                environmentWebNavigator.release();
            }
        });
        this.webView.setOnPageFinishedListener(new AlexaWebView.OnPageFinishedListener() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$WebModule$Ue35G7MVGzzGNMap6x7vBnx1z3c
            @Override // com.amazon.dee.app.ui.web.AlexaWebView.OnPageFinishedListener
            public final void onPageFinished(String str) {
                WebModule.lambda$provideWebNavigator$0(EnvironmentWebNavigator.this, str);
            }
        });
        return environmentWebNavigator;
    }

    @Provides
    @WebScope
    public WebViewDelegate provideWebViewDelegate() {
        return new AnonymousClass4();
    }
}
