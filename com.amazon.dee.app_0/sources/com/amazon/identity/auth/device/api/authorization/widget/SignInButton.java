package com.amazon.identity.auth.device.api.authorization.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class SignInButton extends ImageButton {
    private static final String BUTTON_IMAGE_PREFIX = "btnlwa";
    private static final String BUTTON_PRESSED_SUFFIX = "pressed";
    private static final String LOG_TAG = SignInButton.class.getName();
    private static final Map<String, Integer> resourceIdCache = new HashMap();
    private Color color;
    private Style style;

    /* loaded from: classes12.dex */
    public enum Color {
        GOLD("gold"),
        GRAY("gry"),
        DARK_GRAY("dark_gray");
        
        private final String name;

        Color(String str) {
            this.name = str;
        }
    }

    /* loaded from: classes12.dex */
    public enum Style {
        A_WITH_SMILE("a"),
        LOGIN("login"),
        LOGIN_WITH_AMAZON("loginwithamazon");
        
        private final String name;

        Style(String str) {
            this.name = str;
        }
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.style = Style.LOGIN_WITH_AMAZON;
        this.color = Color.GOLD;
        updateImage();
    }

    private String getButtonDescription() {
        return String.format("Button configuration = { style:%s color:%s pressed:%b }", this.style.toString(), this.color.toString(), Boolean.valueOf(isPressed()));
    }

    private String getButtonNameForCurrentState() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(BUTTON_IMAGE_PREFIX, "_");
        outline113.append(this.color.name);
        outline113.append("_");
        outline113.append(this.style.name);
        if (isPressed()) {
            outline113.append("_");
            outline113.append(BUTTON_PRESSED_SUFFIX);
        }
        return outline113.toString();
    }

    private int getResourceIdForCurrentState() {
        String buttonNameForCurrentState = getButtonNameForCurrentState();
        Integer num = resourceIdCache.get(buttonNameForCurrentState);
        if (num == null) {
            num = Integer.valueOf(getResources().getIdentifier(String.format("%s:drawable/%s", getContext().getPackageName(), buttonNameForCurrentState), null, null));
            if (num.intValue() != 0) {
                resourceIdCache.put(buttonNameForCurrentState, num);
            } else {
                String str = LOG_TAG;
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Could not find the resource ID for the image named \"", buttonNameForCurrentState, "\". It must be added to the drawables resources  (");
                outline115.append(getButtonDescription());
                outline115.append(")");
                MAPLog.e(str, outline115.toString());
            }
        }
        return num.intValue();
    }

    private void updateImage() {
        setImageResource(getResourceIdForCurrentState());
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        super.setPressed(z);
        updateImage();
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
