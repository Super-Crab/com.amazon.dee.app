package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;
/* loaded from: classes2.dex */
final class Item {
    int a;
    int b;
    int c;
    long d;
    String g;
    String h;
    String i;
    int j;
    Item k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item(int i) {
        this.a = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item(int i, Item item) {
        this.a = i;
        this.b = item.b;
        this.c = item.c;
        this.d = item.d;
        this.g = item.g;
        this.h = item.h;
        this.i = item.i;
        this.j = item.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(double d) {
        this.b = 6;
        this.d = Double.doubleToRawLongBits(d);
        this.j = Integer.MAX_VALUE & (this.b + ((int) d));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f) {
        this.b = 4;
        this.c = Float.floatToRawIntBits(f);
        this.j = Integer.MAX_VALUE & (this.b + ((int) f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i) {
        this.b = 3;
        this.c = i;
        this.j = Integer.MAX_VALUE & (this.b + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2) {
        this.b = 33;
        this.c = i;
        this.j = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, String str, String str2, String str3) {
        int hashCode;
        int i2;
        this.b = i;
        this.g = str;
        this.h = str2;
        this.i = str3;
        if (i != 1) {
            if (i == 12) {
                i2 = ((str2.hashCode() * str.hashCode()) + i) & Integer.MAX_VALUE;
                this.j = i2;
            } else if (i != 16 && i != 30 && i != 7 && i != 8) {
                hashCode = str3.hashCode() * str2.hashCode() * str.hashCode();
                i2 = (hashCode + i) & Integer.MAX_VALUE;
                this.j = i2;
            }
        }
        hashCode = str.hashCode();
        i2 = (hashCode + i) & Integer.MAX_VALUE;
        this.j = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j) {
        this.b = 5;
        this.d = j;
        this.j = Integer.MAX_VALUE & (this.b + ((int) j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2, int i) {
        this.b = 18;
        this.d = i;
        this.g = str;
        this.h = str2;
        this.j = Integer.MAX_VALUE & ((this.h.hashCode() * this.g.hashCode() * i) + 18);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Item r9) {
        /*
            r8 = this;
            int r0 = r8.b
            r1 = 1
            if (r0 == r1) goto L96
            r2 = 12
            r3 = 0
            if (r0 == r2) goto L7f
            r2 = 16
            if (r0 == r2) goto L96
            r2 = 18
            if (r0 == r2) goto L60
            switch(r0) {
                case 3: goto L57;
                case 4: goto L57;
                case 5: goto L4c;
                case 6: goto L4c;
                case 7: goto L96;
                case 8: goto L96;
                default: goto L15;
            }
        L15:
            switch(r0) {
                case 30: goto L96;
                case 31: goto L39;
                case 32: goto L4c;
                default: goto L18;
            }
        L18:
            java.lang.String r0 = r9.g
            java.lang.String r2 = r8.g
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L37
            java.lang.String r0 = r9.h
            java.lang.String r2 = r8.h
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L37
            java.lang.String r9 = r9.i
            java.lang.String r0 = r8.i
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L37
            goto L38
        L37:
            r1 = r3
        L38:
            return r1
        L39:
            int r0 = r9.c
            int r2 = r8.c
            if (r0 != r2) goto L4a
            java.lang.String r9 = r9.g
            java.lang.String r0 = r8.g
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L4a
            goto L4b
        L4a:
            r1 = r3
        L4b:
            return r1
        L4c:
            long r4 = r9.d
            long r6 = r8.d
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L55
            goto L56
        L55:
            r1 = r3
        L56:
            return r1
        L57:
            int r9 = r9.c
            int r0 = r8.c
            if (r9 != r0) goto L5e
            goto L5f
        L5e:
            r1 = r3
        L5f:
            return r1
        L60:
            long r4 = r9.d
            long r6 = r8.d
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L7d
            java.lang.String r0 = r9.g
            java.lang.String r2 = r8.g
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L7d
            java.lang.String r9 = r9.h
            java.lang.String r0 = r8.h
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L7d
            goto L7e
        L7d:
            r1 = r3
        L7e:
            return r1
        L7f:
            java.lang.String r0 = r9.g
            java.lang.String r2 = r8.g
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L94
            java.lang.String r9 = r9.h
            java.lang.String r0 = r8.h
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L94
            goto L95
        L94:
            r1 = r3
        L95:
            return r1
        L96:
            java.lang.String r9 = r9.g
            java.lang.String r0 = r8.g
            boolean r9 = r9.equals(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Item.a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Item):boolean");
    }
}
