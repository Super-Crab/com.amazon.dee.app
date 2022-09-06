package com.amazon.alexa.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: QuickActionsWidget.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 !2\u00020\u0001:\u0002!\"B\u0005¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\b\nJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J \u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J-\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bH\u0000¢\u0006\u0002\b\u001dJ%\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001bH\u0000¢\u0006\u0002\b ¨\u0006#"}, d2 = {"Lcom/amazon/alexa/widget/QuickActionsWidget;", "Landroid/appwidget/AppWidgetProvider;", "()V", "getIntentToLaunchUrl", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "uri", "", "routeName", "getIntentToLaunchUrl$AlexaMobileAndroidWidget_release", "getVoiceActivityLaunchIntent", "getVoiceActivityLaunchIntent$AlexaMobileAndroidWidget_release", "onDisabled", "", "onEnabled", "onReceive", MAPAccountManager.KEY_INTENT, "onUpdate", "appWidgetManager", "Landroid/appwidget/AppWidgetManager;", "appWidgetIds", "", "setQuickActionClickListener", "views", "Landroid/widget/RemoteViews;", "viewId", "", "action", "setQuickActionClickListener$AlexaMobileAndroidWidget_release", "updateAppWidget", "appWidgetId", "updateAppWidget$AlexaMobileAndroidWidget_release", "Companion", "MetricsConstants", "AlexaMobileAndroidWidget_release"}, k = 1, mv = {1, 4, 1})
/* loaded from: classes11.dex */
public final class QuickActionsWidget extends AppWidgetProvider {
    private static final HashMap<String, String> ACTION_TO_ROUTE_NAME;
    private static final HashMap<String, String> ACTION_TO_URI;
    private static final String ASK_ALEXA_ACTION = "com.amazon.alexa.widget.action.ASK_ALEXA";
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final String QUICK_ACTIONS_WIDGET_CLICK_TIME_KEY = "QUICK_ACTIONS_WIDGET_CLICK_TIME";
    private static final String QUICK_ACTIONS_WIDGET_INTENT_ACTION = "com.amazon.alexa.action.QUICK_ACTIONS_WIDGET";
    @NotNull
    public static final String ROUTE_ALARMS_CREATE = "https://alexa.amazon.com/spa/index.html#v2/reminders-alarms-timers/alarm-create-landing";
    private static final String ROUTE_ALARMS_CREATE_ACTION = "com.amazon.alexa.widget.action.ROUTE_ALARMS_CREATE";
    @NotNull
    public static final String ROUTE_COMMS_ANNOUNCEMENT = "https://alexa.amazon.com/spa/index.html#v2/comms/announcement";
    private static final String ROUTE_COMMS_ANNOUNCEMENT_ACTION = "com.amazon.alexa.widget.action.ROUTE_COMMS_ANNOUNCEMENT";
    @NotNull
    public static final String ROUTE_LISTS = "https://alexa.amazon.com/spa/index.html#v2/lists";
    private static final String ROUTE_LISTS_ACTION = "com.amazon.alexa.widget.action.ROUTE_LISTS";
    private static final String ROUTE_NAME_KEY = "routeName";
    @NotNull
    public static final String ROUTE_REMINDERS_CREATE = "https://alexa.amazon.com/spa/index.html#v2/reminders-alarms-timers/reminders-create-landing";
    private static final String ROUTE_REMINDERS_CREATE_ACTION = "com.amazon.alexa.widget.action.ROUTE_REMINDERS_CREATE";
    private static final String ROUTE_URL_PREFIX = "https://alexa.amazon.com/spa/index.html#";

    /* compiled from: QuickActionsWidget.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/widget/QuickActionsWidget$Companion;", "", "()V", "ACTION_TO_ROUTE_NAME", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "ACTION_TO_URI", "ASK_ALEXA_ACTION", "QUICK_ACTIONS_WIDGET_CLICK_TIME_KEY", "QUICK_ACTIONS_WIDGET_INTENT_ACTION", "ROUTE_ALARMS_CREATE", "ROUTE_ALARMS_CREATE_ACTION", "ROUTE_COMMS_ANNOUNCEMENT", "ROUTE_COMMS_ANNOUNCEMENT_ACTION", "ROUTE_LISTS", "ROUTE_LISTS_ACTION", "ROUTE_NAME_KEY", "ROUTE_REMINDERS_CREATE", "ROUTE_REMINDERS_CREATE_ACTION", "ROUTE_URL_PREFIX", "AlexaMobileAndroidWidget_release"}, k = 1, mv = {1, 4, 1})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: QuickActionsWidget.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R-\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/widget/QuickActionsWidget$MetricsConstants;", "", "()V", "ACTION_TO_CLICK_EVENT_NAME", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getACTION_TO_CLICK_EVENT_NAME", "()Ljava/util/HashMap;", "CLICK", "CORE_APP", "LAUNCHER_WIDGET", "QUICK_ACTIONS_WIDGET", MetricsConstants.QUICK_ACTIONS_WIDGET_ALARM_CLICK, MetricsConstants.QUICK_ACTIONS_WIDGET_ANNOUNCE_CLICK, MetricsConstants.QUICK_ACTIONS_WIDGET_ASK_ALEXA_CLICK, MetricsConstants.QUICK_ACTIONS_WIDGET_LISTS_CLICK, MetricsConstants.QUICK_ACTIONS_WIDGET_REMINDER_CLICK, "AlexaMobileAndroidWidget_release"}, k = 1, mv = {1, 4, 1})
    /* loaded from: classes11.dex */
    public static final class MetricsConstants {
        @NotNull
        private static final HashMap<String, String> ACTION_TO_CLICK_EVENT_NAME;
        @NotNull
        public static final String CLICK = "click";
        @NotNull
        public static final String CORE_APP = "coreapp";
        @NotNull
        public static final MetricsConstants INSTANCE = new MetricsConstants();
        @NotNull
        public static final String LAUNCHER_WIDGET = "LAUNCHER_WIDGET";
        @NotNull
        public static final String QUICK_ACTIONS_WIDGET = "QUICK_ACTIONS_WIDGET";
        @NotNull
        public static final String QUICK_ACTIONS_WIDGET_ALARM_CLICK = "QUICK_ACTIONS_WIDGET_ALARM_CLICK";
        @NotNull
        public static final String QUICK_ACTIONS_WIDGET_ANNOUNCE_CLICK = "QUICK_ACTIONS_WIDGET_ANNOUNCE_CLICK";
        @NotNull
        public static final String QUICK_ACTIONS_WIDGET_ASK_ALEXA_CLICK = "QUICK_ACTIONS_WIDGET_ASK_ALEXA_CLICK";
        @NotNull
        public static final String QUICK_ACTIONS_WIDGET_LISTS_CLICK = "QUICK_ACTIONS_WIDGET_LISTS_CLICK";
        @NotNull
        public static final String QUICK_ACTIONS_WIDGET_REMINDER_CLICK = "QUICK_ACTIONS_WIDGET_REMINDER_CLICK";

        static {
            HashMap<String, String> hashMapOf;
            hashMapOf = MapsKt__MapsKt.hashMapOf(TuplesKt.to(QuickActionsWidget.ROUTE_LISTS_ACTION, QUICK_ACTIONS_WIDGET_LISTS_CLICK), TuplesKt.to(QuickActionsWidget.ROUTE_COMMS_ANNOUNCEMENT_ACTION, QUICK_ACTIONS_WIDGET_ANNOUNCE_CLICK), TuplesKt.to(QuickActionsWidget.ROUTE_REMINDERS_CREATE_ACTION, QUICK_ACTIONS_WIDGET_REMINDER_CLICK), TuplesKt.to(QuickActionsWidget.ROUTE_ALARMS_CREATE_ACTION, QUICK_ACTIONS_WIDGET_ALARM_CLICK), TuplesKt.to(QuickActionsWidget.ASK_ALEXA_ACTION, QUICK_ACTIONS_WIDGET_ASK_ALEXA_CLICK));
            ACTION_TO_CLICK_EVENT_NAME = hashMapOf;
        }

        private MetricsConstants() {
        }

        @NotNull
        public final HashMap<String, String> getACTION_TO_CLICK_EVENT_NAME() {
            return ACTION_TO_CLICK_EVENT_NAME;
        }
    }

    static {
        HashMap<String, String> hashMapOf;
        HashMap<String, String> hashMapOf2;
        hashMapOf = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ROUTE_LISTS_ACTION, ROUTE_LISTS), TuplesKt.to(ROUTE_COMMS_ANNOUNCEMENT_ACTION, ROUTE_COMMS_ANNOUNCEMENT), TuplesKt.to(ROUTE_REMINDERS_CREATE_ACTION, ROUTE_REMINDERS_CREATE), TuplesKt.to(ROUTE_ALARMS_CREATE_ACTION, ROUTE_ALARMS_CREATE));
        ACTION_TO_URI = hashMapOf;
        hashMapOf2 = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ROUTE_LISTS_ACTION, "v2/lists"), TuplesKt.to(ROUTE_COMMS_ANNOUNCEMENT_ACTION, RouteName.COMMS_ANNOUNCEMENT), TuplesKt.to(ROUTE_REMINDERS_CREATE_ACTION, RouteName.ELEMENTS_REMINDERS_CREATE_LANDING), TuplesKt.to(ROUTE_ALARMS_CREATE_ACTION, RouteName.ELEMENTS_ALARM_CREATE_LANDING));
        ACTION_TO_ROUTE_NAME = hashMapOf2;
    }

    @NotNull
    public final Intent getIntentToLaunchUrl$AlexaMobileAndroidWidget_release(@NotNull Context context, @NotNull String uri, @NotNull String routeName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(routeName, "routeName");
        Intent intent = new Intent("com.amazon.alexa.action.QUICK_ACTIONS_WIDGET");
        intent.setData(Uri.parse(uri));
        intent.setClassName(context.getPackageName(), "com.amazon.dee.app.ui.main.MainActivity");
        intent.addFlags(131072);
        intent.addFlags(268435456);
        intent.putExtra("routeName", routeName);
        return intent;
    }

    @NotNull
    public final Intent getVoiceActivityLaunchIntent$AlexaMobileAndroidWidget_release(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("com.amazon.alexa.action.QUICK_ACTIONS_WIDGET");
        intent.setComponent(new ComponentName(context.getPackageName(), "com.amazon.alexa.voice.ui.VoiceActivity"));
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:
        if (r14.equals(com.amazon.alexa.widget.QuickActionsWidget.ROUTE_REMINDERS_CREATE_ACTION) != false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (r14.equals(com.amazon.alexa.widget.QuickActionsWidget.ROUTE_COMMS_ANNOUNCEMENT_ACTION) != false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
        if (r14.equals(com.amazon.alexa.widget.QuickActionsWidget.ROUTE_ALARMS_CREATE_ACTION) != false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004a, code lost:
        if (r14.equals(com.amazon.alexa.widget.QuickActionsWidget.ASK_ALEXA_ACTION) != false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
        r7 = com.amazon.alexa.widget.QuickActionsWidget.MetricsConstants.INSTANCE.getACTION_TO_CLICK_EVENT_NAME().get(r14);
        kotlin.jvm.internal.Intrinsics.checkNotNull(r7);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, "MetricsConstants.ACTION_…_EVENT_NAME.get(action)!!");
        r8 = (com.amazon.alexa.mobilytics.Mobilytics) com.android.tools.r8.GeneratedOutlineSupport1.outline20(com.amazon.alexa.mobilytics.Mobilytics.class);
        r7 = r8.createUserInteractionEvent(r7, "click", "LAUNCHER_WIDGET", "QUICK_ACTIONS_WIDGET");
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, "mobilytics.createUserInt…_WIDGET\n                )");
        r7.setChannelName("coreapp");
        r8.recordUserInteractionEvent(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002e, code lost:
        if (r14.equals(com.amazon.alexa.widget.QuickActionsWidget.ROUTE_LISTS_ACTION) != false) goto L7;
     */
    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceive(@org.jetbrains.annotations.NotNull android.content.Context r13, @org.jetbrains.annotations.NotNull android.content.Intent r14) {
        /*
            r12 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "intent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            super.onReceive(r13, r14)
            long r0 = java.lang.System.nanoTime()
            java.lang.String r14 = r14.getAction()
            java.lang.String r2 = "com.amazon.alexa.widget.action.ROUTE_LISTS"
            java.lang.String r3 = "com.amazon.alexa.widget.action.ROUTE_REMINDERS_CREATE"
            java.lang.String r4 = "com.amazon.alexa.widget.action.ROUTE_COMMS_ANNOUNCEMENT"
            java.lang.String r5 = "com.amazon.alexa.widget.action.ROUTE_ALARMS_CREATE"
            java.lang.String r6 = "com.amazon.alexa.widget.action.ASK_ALEXA"
            if (r14 != 0) goto L22
            goto L7f
        L22:
            int r7 = r14.hashCode()
            switch(r7) {
                case -1737976793: goto L46;
                case -620270803: goto L3f;
                case -202051413: goto L38;
                case 1463097542: goto L31;
                case 2063518473: goto L2a;
                default: goto L29;
            }
        L29:
            goto L7f
        L2a:
            boolean r7 = r14.equals(r2)
            if (r7 == 0) goto L7f
            goto L4c
        L31:
            boolean r7 = r14.equals(r3)
            if (r7 == 0) goto L7f
            goto L4c
        L38:
            boolean r7 = r14.equals(r4)
            if (r7 == 0) goto L7f
            goto L4c
        L3f:
            boolean r7 = r14.equals(r5)
            if (r7 == 0) goto L7f
            goto L4c
        L46:
            boolean r7 = r14.equals(r6)
            if (r7 == 0) goto L7f
        L4c:
            com.amazon.alexa.widget.QuickActionsWidget$MetricsConstants r7 = com.amazon.alexa.widget.QuickActionsWidget.MetricsConstants.INSTANCE
            java.util.HashMap r7 = r7.getACTION_TO_CLICK_EVENT_NAME()
            java.lang.Object r7 = r7.get(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.lang.String r8 = "MetricsConstants.ACTION_…_EVENT_NAME.get(action)!!"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Class<com.amazon.alexa.mobilytics.Mobilytics> r8 = com.amazon.alexa.mobilytics.Mobilytics.class
            java.lang.Object r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline20(r8)
            com.amazon.alexa.mobilytics.Mobilytics r8 = (com.amazon.alexa.mobilytics.Mobilytics) r8
            java.lang.String r9 = "click"
            java.lang.String r10 = "LAUNCHER_WIDGET"
            java.lang.String r11 = "QUICK_ACTIONS_WIDGET"
            com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent r7 = r8.createUserInteractionEvent(r7, r9, r10, r11)
            java.lang.String r9 = "mobilytics.createUserInt…_WIDGET\n                )"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r9)
            java.lang.String r9 = "coreapp"
            r7.setChannelName(r9)
            r8.recordUserInteractionEvent(r7)
        L7f:
            if (r14 != 0) goto L82
            goto Le2
        L82:
            int r7 = r14.hashCode()
            java.lang.String r8 = "QUICK_ACTIONS_WIDGET_CLICK_TIME"
            switch(r7) {
                case -1737976793: goto Ld2;
                case -620270803: goto La1;
                case -202051413: goto L9a;
                case 1463097542: goto L93;
                case 2063518473: goto L8c;
                default: goto L8b;
            }
        L8b:
            goto Le2
        L8c:
            boolean r2 = r14.equals(r2)
            if (r2 == 0) goto Le2
            goto La7
        L93:
            boolean r2 = r14.equals(r3)
            if (r2 == 0) goto Le2
            goto La7
        L9a:
            boolean r2 = r14.equals(r4)
            if (r2 == 0) goto Le2
            goto La7
        La1:
            boolean r2 = r14.equals(r5)
            if (r2 == 0) goto Le2
        La7:
            java.util.HashMap<java.lang.String, java.lang.String> r2 = com.amazon.alexa.widget.QuickActionsWidget.ACTION_TO_URI
            java.lang.Object r2 = r2.get(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.String r3 = "ACTION_TO_URI.get(action)!!"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.String r2 = (java.lang.String) r2
            java.util.HashMap<java.lang.String, java.lang.String> r3 = com.amazon.alexa.widget.QuickActionsWidget.ACTION_TO_ROUTE_NAME
            java.lang.Object r14 = r3.get(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            java.lang.String r3 = "ACTION_TO_ROUTE_NAME.get(action)!!"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r3)
            java.lang.String r14 = (java.lang.String) r14
            android.content.Intent r14 = r12.getIntentToLaunchUrl$AlexaMobileAndroidWidget_release(r13, r2, r14)
            r14.putExtra(r8, r0)
            r13.startActivity(r14)
            goto Le2
        Ld2:
            boolean r14 = r14.equals(r6)
            if (r14 == 0) goto Le2
            android.content.Intent r14 = r12.getVoiceActivityLaunchIntent$AlexaMobileAndroidWidget_release(r13)
            r14.putExtra(r8, r0)
            r13.startActivity(r14)
        Le2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.widget.QuickActionsWidget.onReceive(android.content.Context, android.content.Intent):void");
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(@NotNull Context context, @NotNull AppWidgetManager appWidgetManager, @NotNull int[] appWidgetIds) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appWidgetManager, "appWidgetManager");
        Intrinsics.checkNotNullParameter(appWidgetIds, "appWidgetIds");
        for (int i : appWidgetIds) {
            updateAppWidget$AlexaMobileAndroidWidget_release(context, appWidgetManager, i);
        }
    }

    public final void setQuickActionClickListener$AlexaMobileAndroidWidget_release(@NotNull Context context, @NotNull RemoteViews views, int i, @NotNull String action) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(views, "views");
        Intrinsics.checkNotNullParameter(action, "action");
        Intent intent = new Intent(context, QuickActionsWidget.class);
        intent.setAction(action);
        views.setOnClickPendingIntent(i, PendingIntent.getBroadcast(context, 0, intent, 0));
    }

    public final void updateAppWidget$AlexaMobileAndroidWidget_release(@NotNull Context context, @NotNull AppWidgetManager appWidgetManager, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appWidgetManager, "appWidgetManager");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.quick_actions_widget);
        setQuickActionClickListener$AlexaMobileAndroidWidget_release(context, remoteViews, R.id.quick_action_id_list, ROUTE_LISTS_ACTION);
        setQuickActionClickListener$AlexaMobileAndroidWidget_release(context, remoteViews, R.id.quick_action_id_announce, ROUTE_COMMS_ANNOUNCEMENT_ACTION);
        setQuickActionClickListener$AlexaMobileAndroidWidget_release(context, remoteViews, R.id.quick_action_id_reminder, ROUTE_REMINDERS_CREATE_ACTION);
        setQuickActionClickListener$AlexaMobileAndroidWidget_release(context, remoteViews, R.id.quick_action_id_alarm, ROUTE_ALARMS_CREATE_ACTION);
        setQuickActionClickListener$AlexaMobileAndroidWidget_release(context, remoteViews, R.id.quick_action_id_ask_alexa, ASK_ALEXA_ACTION);
        appWidgetManager.updateAppWidget(i, remoteViews);
    }
}
