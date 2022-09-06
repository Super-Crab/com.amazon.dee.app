package com.amazon.alexa.handsfree.devices.utils;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/* loaded from: classes8.dex */
public class BuildPropertyGetter {
    private static final String GETPROP_EXECUTABLE_PATH = "/system/bin/getprop";
    private static final String TAG = "BuildPropertyGetter";
    private final ProcessBuilder mProcessBuilder;

    public BuildPropertyGetter() {
        this(new ProcessBuilder(new String[0]));
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0078: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:32:0x0078 */
    @Nullable
    public String readProperty(@NonNull String str) {
        Process process;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                process = this.mProcessBuilder.command(GETPROP_EXECUTABLE_PATH, str).redirectErrorStream(true).start();
            } catch (Throwable th) {
                th = th;
                bufferedReader3 = bufferedReader2;
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName(StandardCharsets.UTF_8.name())));
                try {
                    String readLine = bufferedReader.readLine();
                    String.format("readProperty System Property: %s = %s", str, readLine);
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "Error  closing buffer stream", e);
                    }
                    process.destroy();
                    return readLine;
                } catch (Exception e2) {
                    e = e2;
                    Log.e(TAG, "Failed to readProperty System Property ", e);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            Log.e(TAG, "Error  closing buffer stream", e3);
                        }
                    }
                    if (process != null) {
                        process.destroy();
                    }
                    return null;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader3 != null) {
                    try {
                        bufferedReader3.close();
                    } catch (IOException e5) {
                        Log.e(TAG, "Error  closing buffer stream", e5);
                    }
                }
                if (process != null) {
                    process.destroy();
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            process = null;
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            process = null;
        }
    }

    public BuildPropertyGetter(@NonNull ProcessBuilder processBuilder) {
        this.mProcessBuilder = processBuilder;
    }
}
