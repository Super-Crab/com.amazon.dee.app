package com.android.tools.r8;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.room.util.StringUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.log.CommsLogger;
import com.amazon.dee.app.strictmode.StackTrace;
import com.amazon.identity.auth.device.io;
import com.amazon.photos.discovery.internal.util.KotlinExtKt$catchDb$1;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.amazonaws.DefaultRequest;
import com.esotericsoftware.kryo.KryoException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.tasks.zzi;
import com.google.gson.JsonElement;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sun.mail.util.PropUtil;
import com.typesafe.config.impl.Token;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.TypeInfo;
import io.ktor.client.call.UtilsKt;
import io.ktor.client.request.HttpRequestBuilder;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.mail.Session;
import javax.mail.internet.InternetHeaders;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.util.Arrays;
import org.codehaus.jackson.JsonToken;
import org.threeten.bp.temporal.TemporalField;
/* compiled from: outline */
/* loaded from: classes.dex */
public class GeneratedOutlineSupport1 {
    public static float outline0(float f, float f2, float f3, float f4) {
        return ((f - f2) * f3) + f4;
    }

    public static int outline1(int i, int i2, int i3, int i4) {
        return i + i2 + i3 + i4;
    }

    public static IntentFilter outline10(String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(str);
        return intentFilter;
    }

    public static String outline100(ASN1Sequence aSN1Sequence, StringBuilder sb) {
        sb.append(aSN1Sequence.size());
        return sb.toString();
    }

    public static String outline101(ASN1TaggedObject aSN1TaggedObject, StringBuilder sb) {
        sb.append(aSN1TaggedObject.getTagNo());
        return sb.toString();
    }

    public static String outline102(CipherParameters cipherParameters, StringBuilder sb) {
        sb.append(cipherParameters.getClass().getName());
        return sb.toString();
    }

    public static StringBuffer outline103(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        return stringBuffer;
    }

    public static StringBuilder outline104(char c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        return sb;
    }

    public static StringBuilder outline105(int i, String str) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        return sb;
    }

    public static StringBuilder outline106(int i, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb;
    }

    public static StringBuilder outline107(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder outline108(String str, char c) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(c);
        return sb;
    }

    public static StringBuilder outline109(String str, int i, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        return sb;
    }

    public static Bundle outline11(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        return bundle;
    }

    public static StringBuilder outline110(String str, int i, String str2, int i2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i);
        sb.append(str2);
        sb.append(i2);
        sb.append(str3);
        return sb;
    }

    public static StringBuilder outline111(String str, long j, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(j);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder outline112(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder outline113(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder outline114(String str, String str2, String str3) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append(str);
        newStringBuilder.append(str2);
        newStringBuilder.append(str3);
        return newStringBuilder;
    }

    public static StringBuilder outline115(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static StringBuilder outline116(String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        sb.append(str5);
        return sb;
    }

    public static StringBuilder outline117(StringBuilder sb, String str, String str2, ConfigurableProvider configurableProvider, String str3) {
        sb.append(str);
        sb.append(str2);
        configurableProvider.addAlgorithm(str3, sb.toString());
        return new StringBuilder();
    }

    public static StringBuilder outline118(StringBuilder sb, ASN1ObjectIdentifier aSN1ObjectIdentifier, ConfigurableProvider configurableProvider, String str) {
        sb.append(aSN1ObjectIdentifier);
        configurableProvider.addAlgorithm(sb.toString(), str);
        return new StringBuilder();
    }

    public static StringBuilder outline119(StringBuilder sb, ASN1ObjectIdentifier aSN1ObjectIdentifier, ConfigurableProvider configurableProvider, String str, String str2) {
        sb.append(aSN1ObjectIdentifier);
        configurableProvider.addAlgorithm(sb.toString(), str);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        return sb2;
    }

    public static Bundle outline12(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        bundle.putString(str3, str4);
        return bundle;
    }

    public static StringBuilder outline120(ConfigurableProvider configurableProvider, String str, String str2) {
        configurableProvider.addAlgorithm(str, str2);
        return new StringBuilder();
    }

    public static StringBuilder outline121(ConfigurableProvider configurableProvider, String str, String str2, String str3) {
        configurableProvider.addAlgorithm(str, str2);
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        return sb;
    }

    public static StringBuilder outline122(ConfigurableProvider configurableProvider, String str, String str2, String str3, String str4) {
        configurableProvider.addAlgorithm(str, str2);
        configurableProvider.addAlgorithm(str3, str4);
        return new StringBuilder();
    }

    public static StringBuilder outline123(ConfigurableProvider configurableProvider, String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str2) {
        configurableProvider.addAlgorithm(str, aSN1ObjectIdentifier, str2);
        return new StringBuilder();
    }

    public static ArrayList outline124(int i, HashMap hashMap, ArrayList arrayList, int i2, String str) {
        hashMap.put(Integer.valueOf(i), arrayList);
        ArrayList arrayList2 = new ArrayList(i2);
        arrayList2.add(str);
        return arrayList2;
    }

    public static ArrayList outline125(StackTrace stackTrace, Map map, ArrayList arrayList) {
        map.put(Integer.valueOf(stackTrace.hashCode()), arrayList);
        return new ArrayList();
    }

    public static ArrayList outline126(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return arrayList;
    }

    public static ArrayList outline127(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        return arrayList;
    }

    public static ArrayList outline128(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        return arrayList;
    }

    public static ArrayList outline129(String str, String str2, String str3, String str4, String str5) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(str4);
        arrayList.add(str5);
        return arrayList;
    }

    public static Bundle outline13(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(str, z);
        return bundle;
    }

    public static ArrayList outline130(ArrayList arrayList, String str, int i, HashMap hashMap, ArrayList arrayList2, int i2) {
        arrayList.add(str);
        hashMap.put(Integer.valueOf(i), arrayList2);
        return new ArrayList(i2);
    }

    public static ArrayList outline131(LinkedHashMap linkedHashMap, Object obj) {
        ArrayList arrayList = new ArrayList();
        linkedHashMap.put(obj, arrayList);
        return arrayList;
    }

    public static ArrayList outline132(Map map, Object obj) {
        ArrayList arrayList = new ArrayList();
        map.put(obj, arrayList);
        return arrayList;
    }

    public static HashMap outline133(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, str2);
        return hashMap;
    }

    public static HashMap outline134(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, str2);
        hashMap.put(str3, str4);
        return hashMap;
    }

    public static HashSet outline135(HashMap hashMap, String str, TableInfo.Column column, int i) {
        hashMap.put(str, column);
        return new HashSet(i);
    }

    public static Map outline136() {
        return Collections.synchronizedMap(new HashMap());
    }

    public static ASN1EncodableVector outline137(ASN1EncodableVector aSN1EncodableVector, ASN1EncodableVector aSN1EncodableVector2) {
        aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector));
        return new ASN1EncodableVector();
    }

    public static ASN1ObjectIdentifier outline138(String str) {
        return new ASN1ObjectIdentifier(str).intern();
    }

    public static ECFieldElement outline139(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return eCFieldElement.square().add(eCFieldElement2).add(eCFieldElement3);
    }

    public static zzi outline14(zzag zzagVar, String str, Object[] objArr) {
        zzagVar.zzd(str, objArr);
        return new zzi();
    }

    public static void outline140(int i, int i2, int i3, TreeMap treeMap, Integer num) {
        treeMap.put(num, new VideoConstraints(i, i2, i3));
    }

    public static void outline141(int i, HashMap hashMap, String str, int i2, String str2, int i3, String str3, int i4, String str4) {
        hashMap.put(Integer.valueOf(i), str);
        hashMap.put(Integer.valueOf(i2), str2);
        hashMap.put(Integer.valueOf(i3), str3);
        hashMap.put(Integer.valueOf(i4), str4);
    }

    public static void outline142(int i, ReceiveChannel receiveChannel, Throwable th, int i2) {
        InlineMarker.finallyStart(i);
        ChannelsKt.cancelConsumed(receiveChannel, th);
        InlineMarker.finallyEnd(i2);
    }

    public static void outline143(SharedPreferences sharedPreferences, String str, boolean z) {
        sharedPreferences.edit().putBoolean(str, z).apply();
    }

    public static void outline144(Route.Builder builder, String str, String str2, RoutingRegistry routingRegistry) {
        routingRegistry.register(builder.asRoot().withParent(str).withTemplate(str2).build());
    }

    public static void outline145(RoutingService routingService, String str) {
        routingService.route(str).addToBackStack().navigate();
    }

    public static void outline146(Class cls, StringBuilder sb, String str) {
        sb.append(cls.getName());
        sb.append(str);
    }

    public static void outline147(Class cls, StringBuilder sb, String str, KryoException kryoException) {
        sb.append(cls.getName());
        sb.append(str);
        kryoException.addTrace(sb.toString());
    }

    public static void outline148(Exception exc, StringBuilder sb, String str) {
        sb.append(exc.getMessage());
        Log.e(str, sb.toString());
    }

    public static void outline149(String str, int i) {
        String str2 = str + i;
    }

    public static Character outline15(char c, HashMap hashMap, Character ch, char c2) {
        hashMap.put(ch, Character.valueOf(c));
        return Character.valueOf(c2);
    }

    public static void outline150(String str, int i, int i2, StringBuilder sb, String str2) {
        sb.append(str.substring(i, i2));
        sb.append(str2);
    }

    public static void outline151(String str, int i, String str2) {
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(str2, str + i);
    }

    public static void outline152(String str, int i, String str2) {
        com.google.android.exoplayer2.util.Log.w(str2, str + i);
    }

    public static void outline153(String str, long j) {
        String str2 = str + j;
    }

    public static void outline154(String str, Fragment fragment) {
        String str2 = str + fragment;
    }

    public static void outline155(String str, Metrics metrics, String str2, Exception exc) {
        metrics.recordSimpleErrorEvent(str2, new KotlinExtKt$catchDb$1(str), exc);
    }

    public static void outline156(String str, Exception exc, String str2) {
        Log.e(str2, str + exc);
    }

    public static void outline157(String str, Exception exc, String str2) {
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(str2, str + exc);
    }

    public static void outline158(String str, String str2) {
        String str3 = str + str2;
    }

    public static void outline159(String str, String str2, CommsLogger commsLogger) {
        commsLogger.d(str + str2);
    }

    public static Object outline16(int i) {
        return DescriptorProtos.getDescriptor().getMessageTypes().get(i);
    }

    public static void outline160(String str, String str2, RtcscLogger rtcscLogger) {
        rtcscLogger.i(str + str2);
    }

    public static void outline161(String str, String str2, String str3) {
        str2.concat(String.valueOf(str));
        io.dm(str3);
    }

    public static void outline162(String str, String str2, String str3) {
        Log.e(str3, str + str2);
    }

    public static void outline163(String str, String str2, String str3) {
        Log.i(str3, str + str2);
    }

    public static void outline164(String str, String str2, String str3) {
        Log.w(str3, str + str2);
    }

    public static void outline165(String str, String str2, String str3) {
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.d(str3, str + str2);
    }

    public static void outline166(String str, String str2, String str3) {
        com.amazon.alexa.accessory.notificationpublisher.utils.Log.i(str3, str + str2);
    }

    public static void outline167(String str, String str2, String str3) {
        com.amazon.alexa.handsfree.protocols.utils.Log.d(str3, str + str2);
    }

    public static void outline168(String str, String str2, String str3) {
        com.google.android.exoplayer2.util.Log.w(str3, str + str2);
    }

    public static void outline169(String str, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkParameterIsNotNull(str, str2);
        Intrinsics.checkParameterIsNotNull(str3, str4);
        Intrinsics.checkParameterIsNotNull(str5, str6);
    }

    public static Object outline17(int i, HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, Continuation continuation, int i2) {
        InlineMarker.mark(i);
        Object call = UtilsKt.call(httpClient, httpRequestBuilder, continuation);
        InlineMarker.mark(i2);
        return call;
    }

    public static void outline170(String str, String str2, List list) {
        list.add(new InternetHeaders.InternetHeader(str, str2));
    }

    public static void outline171(String str, String str2, boolean z, Map map) {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(str, str2, z, map);
    }

    public static void outline172(String str, boolean z) {
        String str2 = str + z;
    }

    public static void outline173(String str, boolean z, String str2) {
        Log.i(str2, str + z);
    }

    public static void outline174(StringBuffer stringBuffer, String str, String str2, String str3, String str4) {
        stringBuffer.append(str);
        stringBuffer.append(str2);
        stringBuffer.append(str3);
        stringBuffer.append(str4);
    }

    public static void outline175(StringBuilder sb, int i, String str, int i2, String str2) {
        sb.append(i);
        sb.append(str);
        sb.append(i2);
        sb.append(str2);
    }

    public static void outline176(StringBuilder sb, String str, char c, String str2) {
        sb.append(str);
        sb.append(c);
        sb.append(str2);
    }

    public static void outline177(StringBuilder sb, String str, CommsLogger commsLogger) {
        sb.append(str);
        commsLogger.i(sb.toString());
    }

    public static void outline178(StringBuilder sb, String str, PrintStream printStream) {
        sb.append(str);
        printStream.println(sb.toString());
    }

    public static void outline179(StringBuilder sb, String str, String str2) {
        sb.append(str);
        Log.i(str2, sb.toString());
    }

    public static Object outline18(int i, HttpClientCall httpClientCall, TypeInfo typeInfo, Continuation continuation, int i2, int i3, String str) {
        InlineMarker.mark(i);
        Object receive = httpClientCall.receive(typeInfo, continuation);
        InlineMarker.mark(i2);
        Intrinsics.reifiedOperationMarker(i3, str);
        return receive;
    }

    public static void outline180(StringBuilder sb, String str, String str2, String str3) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
    }

    public static void outline181(StringBuilder sb, String str, String str2, String str3, String str4) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
    }

    public static void outline182(StringBuilder sb, String str, String str2, ConfigurableProvider configurableProvider, String str3) {
        sb.append(str);
        sb.append(str2);
        configurableProvider.addAlgorithm(str3, sb.toString());
    }

    public static void outline183(StringBuilder sb, ASN1ObjectIdentifier aSN1ObjectIdentifier, ConfigurableProvider configurableProvider, String str) {
        sb.append(aSN1ObjectIdentifier);
        configurableProvider.addAlgorithm(sb.toString(), str);
    }

    public static void outline184(StringBuilder sb, boolean z, CommsLogger commsLogger) {
        sb.append(z);
        commsLogger.i(sb.toString());
    }

    public static void outline185(StringBuilder sb, boolean z, RtcscLogger rtcscLogger) {
        sb.append(z);
        rtcscLogger.i(sb.toString());
    }

    public static void outline186(Throwable th, Continuation continuation) {
        continuation.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(th)));
    }

    public static void outline187(HashSet hashSet, String str, String str2, String str3, String str4) {
        hashSet.add(str);
        hashSet.add(str2);
        hashSet.add(str3);
        hashSet.add(str4);
    }

    public static boolean outline188(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        supportSQLiteDatabase.query(str).close();
        return supportSQLiteDatabase.inTransaction();
    }

    public static boolean outline189(DefaultRequest defaultRequest, String str, String str2) {
        defaultRequest.setResourcePath(str);
        return defaultRequest.getHeaders().containsKey(str2);
    }

    public static Object outline19(CharSequence charSequence, int i, Function1 function1) {
        return function1.mo12165invoke(Character.valueOf(charSequence.charAt(i)));
    }

    public static boolean outline190(String str, String str2, String str3, DefaultRequest defaultRequest, String str4) {
        defaultRequest.setResourcePath(str.replace(str2, str3));
        return defaultRequest.getHeaders().containsKey(str4);
    }

    public static boolean outline191(String str, String str2, String str3, Session session, boolean z) {
        return PropUtil.getBooleanSessionProperty(session, str + str2 + str3, z);
    }

    public static boolean outline192(Flags.BooleanFlagField booleanFlagField, int i, String str) {
        Boolean mo11937get = booleanFlagField.mo11937get(i);
        Intrinsics.checkExpressionValueIsNotNull(mo11937get, str);
        return mo11937get.booleanValue();
    }

    public static byte[] outline193(ASN1Sequence aSN1Sequence, int i) {
        return ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i)).getOctets();
    }

    public static byte[] outline194(ASN1Sequence aSN1Sequence, int i) {
        return Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i)).getOctets());
    }

    public static StackTraceElement[] outline195() {
        return new Throwable().getStackTrace();
    }

    public static int outline2(int i, int i2, int i3, int i4) {
        return ((i + i2) * i3) + i4;
    }

    public static Object outline20(Class cls) {
        return ComponentRegistry.getInstance().get(cls).get();
    }

    public static Object outline21(Class cls) {
        return ComponentRegistry.getInstance().get(cls).orNull();
    }

    public static Object outline22(Class cls, String str) {
        Object obj = ComponentRegistry.getInstance().get(cls).get();
        Intrinsics.checkExpressionValueIsNotNull(obj, str);
        return obj;
    }

    public static Object outline23(ParameterizedType parameterizedType, String str) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Intrinsics.checkExpressionValueIsNotNull(actualTypeArguments, str);
        return ArraysKt.first(actualTypeArguments);
    }

    public static Object outline24(List list, int i) {
        return list.get(list.size() + i);
    }

    public static Object outline25(List list, int i) {
        return list.get(list.size() - i);
    }

    public static Object outline26(Object[] objArr, int i) {
        return Array.newInstance(objArr.getClass().getComponentType(), i);
    }

    public static String outline27(int i, String str, int i2) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(i2);
        return sb.toString();
    }

    public static String outline28(int i, String str, int i2, String str2, int i3) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(i2);
        sb.append(str2);
        sb.append(i3);
        return sb.toString();
    }

    public static String outline29(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static int outline3(int i, int i2, int i3, int i4) {
        return (i * i2) + i3 + i4;
    }

    public static String outline30(int i, String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb.toString();
    }

    public static String outline31(int i, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb.toString();
    }

    public static String outline32(int i, StringBuilder sb) {
        sb.append(Integer.toHexString(i));
        return sb.toString();
    }

    public static String outline33(int i, StringBuilder sb, String str) {
        sb.append(Integer.toHexString(i));
        sb.append(str);
        return sb.toString();
    }

    public static String outline34(Accessories.Response response, StringBuilder sb) {
        sb.append(response.getErrorCode());
        return sb.toString();
    }

    public static String outline35(AlexaMetricsName alexaMetricsName, StringBuilder sb, String str, String str2) {
        sb.append(alexaMetricsName.getValue());
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String outline36(InvalidProtocolBufferException invalidProtocolBufferException, StringBuilder sb) {
        sb.append(invalidProtocolBufferException.getMessage());
        return sb.toString();
    }

    public static String outline37(IOException iOException, StringBuilder sb) {
        sb.append(iOException.getMessage());
        return sb.toString();
    }

    public static String outline38(Class cls, StringBuilder sb) {
        sb.append(cls.getName());
        return sb.toString();
    }

    public static String outline39(Class cls, StringBuilder sb) {
        sb.append(cls.getSimpleName());
        return sb.toString();
    }

    public static int outline4(int i, int i2, int i3, int i4) {
        return ((i * i2) + i3) * i4;
    }

    public static String outline40(Class cls, StringBuilder sb, String str) {
        sb.append(cls.getName());
        sb.append(str);
        return sb.toString();
    }

    public static String outline41(Exception exc, StringBuilder sb) {
        sb.append(exc.getMessage());
        return sb.toString();
    }

    public static String outline42(Exception exc, StringBuilder sb) {
        sb.append(exc.toString());
        return sb.toString();
    }

    public static String outline43(IllegalArgumentException illegalArgumentException, StringBuilder sb) {
        sb.append(illegalArgumentException.getMessage());
        return sb.toString();
    }

    public static String outline44(Object obj, StringBuilder sb) {
        sb.append(obj.getClass());
        return sb.toString();
    }

    public static String outline45(Object obj, StringBuilder sb) {
        sb.append(obj.getClass().getName());
        return sb.toString();
    }

    public static String outline46(Object obj, StringBuilder sb, String str) {
        sb.append(obj.getClass().getName());
        sb.append(str);
        return sb.toString();
    }

    public static String outline47(String str, char c) {
        return str + c;
    }

    public static String outline48(String str, char c, String str2) {
        return str + c + str2;
    }

    public static String outline49(String str, int i) {
        return str + i;
    }

    public static int outline5(CharSequence charSequence, int i, int i2) {
        return (charSequence.hashCode() + i) * i2;
    }

    public static String outline50(String str, int i, int i2) {
        return str.substring(i2, str.length() + i);
    }

    public static String outline51(String str, int i, int i2) {
        return str.substring(i2, str.length() - i);
    }

    public static String outline52(String str, int i, String str2) {
        return str + i + str2;
    }

    public static String outline53(String str, int i, String str2, int i2) {
        return str + i + str2 + i2;
    }

    public static String outline54(String str, int i, String str2, int i2, String str3) {
        return str + i + str2 + i2 + str3;
    }

    public static String outline55(String str, int i, StringBuilder sb) {
        sb.append(str.substring(i));
        return sb.toString();
    }

    public static String outline56(String str, long j) {
        return str + j;
    }

    public static String outline57(String str, long j, String str2) {
        return str + j + str2;
    }

    public static String outline58(String str, Uri uri) {
        return str + uri;
    }

    public static String outline59(String str, Fragment fragment, String str2) {
        return str + fragment + str2;
    }

    public static int outline6(String str, int i) {
        return String.valueOf(str).length() + i;
    }

    public static String outline60(String str, TableInfo tableInfo, String str2, TableInfo tableInfo2) {
        return str + tableInfo + str2 + tableInfo2;
    }

    public static String outline61(String str, JsonElement jsonElement) {
        return str + jsonElement;
    }

    public static String outline62(String str, Token token) {
        return str + token;
    }

    public static String outline63(String str, File file) {
        return str + file;
    }

    public static String outline64(String str, File file, String str2) {
        return str + file + str2;
    }

    public static String outline65(String str, IOException iOException) {
        return str + iOException;
    }

    public static String outline66(String str, Class cls) {
        return str + cls;
    }

    public static String outline67(String str, Class cls, String str2, int i) {
        return str + cls + str2 + i;
    }

    public static String outline68(String str, Exception exc) {
        return str + exc;
    }

    public static String outline69(String str, Integer num, String str2) {
        return str + num + str2;
    }

    public static int outline7(String str, int i, int i2) {
        return (str.hashCode() + i) * i2;
    }

    public static String outline70(String str, Object obj) {
        return str + obj;
    }

    public static String outline71(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static String outline72(String str, String str2) {
        return str + str2;
    }

    public static String outline73(String str, String str2, char c) {
        return str + str2 + c;
    }

    public static String outline74(String str, String str2, int i) {
        return str + str2 + i;
    }

    public static String outline75(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String outline76(String str, String str2, String str3, String str4) {
        return str + str2 + str3 + str4;
    }

    public static String outline77(String str, String str2, String str3, String str4, String str5) {
        return str + str2 + str3 + str4 + str5;
    }

    public static String outline78(String str, StringBuilder sb, String str2) {
        sb.append(str.toString());
        sb.append(str2);
        return sb.toString();
    }

    public static String outline79(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return str + aSN1ObjectIdentifier;
    }

    public static int outline8(IntRange intRange, int i) {
        return intRange.mo11372getEndInclusive().intValue() + i;
    }

    public static String outline80(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str2) {
        return str + aSN1ObjectIdentifier + str2;
    }

    public static String outline81(String str, JsonToken jsonToken) {
        return str + jsonToken;
    }

    public static String outline82(String str, TemporalField temporalField) {
        return str + temporalField;
    }

    public static String outline83(StringBuffer stringBuffer, String str, String str2) {
        stringBuffer.append(str);
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static String outline84(StringBuilder sb, double d, String str) {
        sb.append(d);
        sb.append(str);
        return sb.toString();
    }

    public static String outline85(StringBuilder sb, int i, char c) {
        sb.append(i);
        sb.append(c);
        return sb.toString();
    }

    public static String outline86(StringBuilder sb, int i, String str) {
        sb.append(i);
        sb.append(str);
        return sb.toString();
    }

    public static String outline87(StringBuilder sb, long j, String str) {
        sb.append(j);
        sb.append(str);
        return sb.toString();
    }

    public static String outline88(StringBuilder sb, Object obj, String str) {
        sb.append(obj);
        sb.append(str);
        return sb.toString();
    }

    public static String outline89(StringBuilder sb, String str, char c) {
        sb.append(str);
        sb.append(c);
        return sb.toString();
    }

    public static long outline9(long j, long j2, long j3, long j4) {
        return (j * j2) + j3 + j4;
    }

    public static String outline90(StringBuilder sb, String str, char c, char c2) {
        sb.append(str);
        sb.append(c);
        sb.append(c2);
        return sb.toString();
    }

    public static String outline91(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static String outline92(StringBuilder sb, String str, String str2, String str3) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        return sb.toString();
    }

    public static String outline93(StringBuilder sb, String str, String str2, String str3, String str4) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb.toString();
    }

    public static String outline94(StringBuilder sb, List list, char c) {
        sb.append(list);
        sb.append(c);
        return sb.toString();
    }

    public static String outline95(StringBuilder sb, List list, String str) {
        sb.append(list);
        sb.append(str);
        return sb.toString();
    }

    public static String outline96(StringBuilder sb, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        sb.append(aSN1ObjectIdentifier);
        sb.append(str);
        return sb.toString();
    }

    public static String outline97(StringBuilder sb, boolean z, String str) {
        sb.append(z);
        sb.append(str);
        return sb.toString();
    }

    public static String outline98(Throwable th, StringBuilder sb) {
        sb.append(th.getMessage());
        return sb.toString();
    }

    public static String outline99(GeneralSecurityException generalSecurityException, StringBuilder sb) {
        sb.append(generalSecurityException.getMessage());
        return sb.toString();
    }
}
