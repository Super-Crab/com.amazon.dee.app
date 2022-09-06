package org.json.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/* loaded from: classes5.dex */
public class ItemList {
    List items;
    private String sp;

    public ItemList() {
        this.sp = ",";
        this.items = new ArrayList();
    }

    public ItemList(String str) {
        this.sp = ",";
        this.items = new ArrayList();
        split(str, this.sp, this.items);
    }

    public ItemList(String str, String str2) {
        this.sp = ",";
        this.items = new ArrayList();
        this.sp = str;
        split(str, str2, this.items);
    }

    public ItemList(String str, String str2, boolean z) {
        this.sp = ",";
        this.items = new ArrayList();
        split(str, str2, this.items, z);
    }

    public void add(int i, String str) {
        if (str == null) {
            return;
        }
        this.items.add(i, str.trim());
    }

    public void add(String str) {
        if (str == null) {
            return;
        }
        this.items.add(str.trim());
    }

    public void addAll(String str) {
        split(str, this.sp, this.items);
    }

    public void addAll(String str, String str2) {
        split(str, str2, this.items);
    }

    public void addAll(String str, String str2, boolean z) {
        split(str, str2, this.items, z);
    }

    public void addAll(ItemList itemList) {
        this.items.addAll(itemList.items);
    }

    public void clear() {
        this.items.clear();
    }

    public String get(int i) {
        return (String) this.items.get(i);
    }

    public String[] getArray() {
        return (String[]) this.items.toArray();
    }

    public List getItems() {
        return this.items;
    }

    public void reset() {
        this.sp = ",";
        this.items.clear();
    }

    public void setSP(String str) {
        this.sp = str;
    }

    public int size() {
        return this.items.size();
    }

    public void split(String str, String str2, List list) {
        if (str == null || str2 == null) {
            return;
        }
        int i = 0;
        while (true) {
            int indexOf = str.indexOf(str2, i);
            if (indexOf == -1) {
                break;
            }
            list.add(str.substring(i, indexOf).trim());
            int length = indexOf + str2.length();
            if (length == -1) {
                break;
            }
            i = length;
        }
        list.add(str.substring(i).trim());
    }

    public void split(String str, String str2, List list, boolean z) {
        if (str == null || str2 == null) {
            return;
        }
        if (!z) {
            split(str, str2, list);
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        while (stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken().trim());
        }
    }

    public String toString() {
        return toString(this.sp);
    }

    public String toString(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.items.size(); i++) {
            if (i != 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(this.items.get(i));
        }
        return stringBuffer.toString();
    }
}
