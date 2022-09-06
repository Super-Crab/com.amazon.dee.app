package com.amazon.identity.auth.device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class lb {
    private static final String TAG = "com.amazon.identity.auth.device.lb";
    private String a;
    private String bP;
    private String dk;
    private String dl;
    private String mAccessToken;
    private String ne;
    private int sI;
    private String sK;
    private String sL;
    private String sO;
    private String sS;
    private String tH;
    private String tI;
    private String tJ;
    private String tK;
    private la tL;
    private kc tM;
    private String tN;
    private Map<String, String> tO;
    private List<ke> tP;
    private JSONArray tQ;
    private Map<String, Map<String, String>> tR;
    private String tS;

    public lb(la laVar) {
        this(laVar, (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        this.tK = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        this.tI = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i) {
        this.sI = i;
    }

    public String cb() {
        return this.sL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(String str) {
        this.sS = str;
    }

    public void et(String str) {
        this.sK = str;
    }

    public String fW() {
        return this.ne;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public Map<String, String> getDeviceInfo() {
        return this.tO;
    }

    public String getDeviceName() {
        return this.sS;
    }

    public String getDirectedId() {
        return this.bP;
    }

    public String getEmail() {
        return this.tK;
    }

    public String getUserName() {
        return this.tI;
    }

    public String hL() {
        return this.a;
    }

    public int hM() {
        return this.sI;
    }

    public List<ke> hN() {
        return Collections.unmodifiableList(this.tP);
    }

    public String hO() {
        return this.tJ;
    }

    public String hP() {
        return this.sK;
    }

    public la hQ() {
        return this.tL;
    }

    public String hR() {
        return this.dk;
    }

    public String hS() {
        return this.tN;
    }

    public String hT() {
        return this.dl;
    }

    public String hU() {
        return this.sO;
    }

    public Map<String, Map<String, String>> hV() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.tR);
        return hashMap;
    }

    public JSONArray hW() {
        return this.tQ;
    }

    public kc hX() {
        return this.tM;
    }

    public String hY() {
        return this.tS;
    }

    public void j(String str) {
        this.sL = str;
    }

    public void k(String str) {
        this.sO = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(String str) {
        this.dk = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(String str) {
        this.tN = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(String str) {
        this.dl = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(String str) {
        this.mAccessToken = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(String str) {
        this.ne = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDirectedId(String str) {
        this.bP = str;
    }

    public lb(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str, null, null, 0, str2, str3, str4, str5, str6, null, null, null);
    }

    public String a() {
        return this.tH;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(String str) {
        this.tJ = str;
    }

    public void m(List<ke> list) {
        this.tP.addAll(list);
    }

    public void n(Map<String, String> map) {
        this.tO = map;
    }

    public lb(String str) {
        this.tQ = new JSONArray();
        this.tS = str;
    }

    public void c(JSONArray jSONArray) {
        this.tQ = jSONArray;
    }

    public void m(Map<String, Map<String, String>> map) {
        this.tR.clear();
        this.tR.putAll(map);
    }

    public lb(String str, String str2, String str3, int i, String str4, String str5, la laVar) {
        this(str, str2, str3, i, null, str4, null, null, null, str5, null, laVar);
    }

    public lb(kc kcVar, la laVar) {
        this(null, null, null, 0, null, null, null, null, null, null, kcVar, laVar);
    }

    public lb(la laVar, byte b) {
        this(null, null, null, 0, null, null, null, null, null, null, null, laVar);
    }

    private lb(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, String str8, String str9, kc kcVar, la laVar) {
        this.tQ = new JSONArray();
        this.a = str;
        this.ne = str2;
        this.mAccessToken = str3;
        this.sI = i;
        this.sS = str4;
        this.tH = str5;
        this.tI = str6;
        this.tJ = str7;
        this.tP = new ArrayList();
        this.tR = new HashMap();
        this.tK = str8;
        this.bP = str9;
        this.tO = null;
        this.tM = kcVar;
        this.tL = laVar;
    }
}
