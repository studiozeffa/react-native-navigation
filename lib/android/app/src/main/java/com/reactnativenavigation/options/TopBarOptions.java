package com.reactnativenavigation.options;


import android.content.Context;
import android.util.Log;

import com.reactnativenavigation.BuildConfig;
import com.reactnativenavigation.options.params.Bool;
import com.reactnativenavigation.options.params.Fraction;
import com.reactnativenavigation.options.params.NullBool;
import com.reactnativenavigation.options.params.NullFraction;
import com.reactnativenavigation.options.params.NullNumber;
import com.reactnativenavigation.options.params.NullText;
import com.reactnativenavigation.options.params.Number;
import com.reactnativenavigation.options.params.RNNColour;
import com.reactnativenavigation.options.params.RNNColourKt;
import com.reactnativenavigation.options.params.RNNNullColor;
import com.reactnativenavigation.options.params.Text;
import com.reactnativenavigation.options.parsers.BoolParser;
import com.reactnativenavigation.options.parsers.FractionParser;
import com.reactnativenavigation.options.parsers.NumberParser;
import com.reactnativenavigation.options.parsers.TextParser;
import com.reactnativenavigation.options.parsers.TypefaceLoader;

import org.json.JSONObject;

public class TopBarOptions {

    public static TopBarOptions parse(Context context, TypefaceLoader typefaceLoader, JSONObject json) {
        TopBarOptions options = new TopBarOptions();
        if (json == null) return options;

        options.title = TitleOptions.parse(context, typefaceLoader, json.optJSONObject("title"));
        options.subtitle = SubtitleOptions.parse(context, typefaceLoader, json.optJSONObject("subtitle"));
        options.background = TopBarBackgroundOptions.parse(context, json.optJSONObject("background"));
        options.visible = BoolParser.parse(json, "visible");
        options.animate = BoolParser.parse(json,"animate");
        options.hideOnScroll = BoolParser.parse(json,"hideOnScroll");
        options.drawBehind = BoolParser.parse(json,"drawBehind");
        options.testId = TextParser.parse(json, "testID");
        options.height = NumberParser.parse(json, "height");
        options.borderColor = RNNColourKt.parse(context, json.optJSONObject( "borderColor"));
        options.borderHeight = FractionParser.parse(json, "borderHeight");
        options.elevation = FractionParser.parse(json, "elevation");
        options.topMargin = NumberParser.parse(json, "topMargin");
        options.animateLeftButtons = BoolParser.parse(json, "animateLeftButtons");
        options.animateRightButtons = BoolParser.parse(json, "animateRightButtons");
        options.buttons = TopBarButtons.parse(context, json);

        options.rightButtonColor = RNNColourKt.parse(context, json.optJSONObject("rightButtonColor"));
        options.leftButtonColor = RNNColourKt.parse(context, json.optJSONObject("leftButtonColor"));
        options.leftButtonDisabledColor = RNNColourKt.parse(context, json.optJSONObject("leftButtonDisabledColor"));
        options.rightButtonDisabledColor = RNNColourKt.parse(context, json.optJSONObject("rightButtonDisabledColor"));

        options.validate();
        return options;
    }

    public TitleOptions title = new TitleOptions();
    public SubtitleOptions subtitle = new SubtitleOptions();
    public TopBarButtons buttons = new TopBarButtons();
    public Text testId = new NullText();
    public TopBarBackgroundOptions background = new TopBarBackgroundOptions();
    public Bool visible = new NullBool();
    public Bool animate = new NullBool();
    public Bool hideOnScroll = new NullBool();
    public Bool drawBehind = new NullBool();
    public Number height = new NullNumber();
    public Fraction elevation = new NullFraction();
    public Number topMargin = new NullNumber();
    public Fraction borderHeight = new NullFraction();
    public RNNColour borderColor = new RNNNullColor();
    public Bool animateLeftButtons = new NullBool();
    public Bool animateRightButtons = new NullBool();
    // Deprecated
    public RNNColour rightButtonColor = new RNNNullColor();
    public RNNColour leftButtonColor = new RNNNullColor();
    public RNNColour rightButtonDisabledColor = new RNNNullColor();
    public RNNColour leftButtonDisabledColor = new RNNNullColor();

    public TopBarOptions copy() {
        TopBarOptions result = new TopBarOptions();
        result.mergeWith(this);
        return result;
    }

    void mergeWith(final TopBarOptions other) {
        title.mergeWith(other.title);
        subtitle.mergeWith(other.subtitle);
        background.mergeWith(other.background);
        buttons.mergeWith(other.buttons);
        rightButtonColor.mergeWith(other.rightButtonColor);
        leftButtonColor.mergeWith(other.leftButtonColor);
        rightButtonDisabledColor.mergeWith(other.rightButtonDisabledColor);
        leftButtonDisabledColor.mergeWith(other.leftButtonDisabledColor);

        if (other.testId.hasValue()) testId = other.testId;
        if (other.visible.hasValue()) visible = other.visible;
        if (other.animate.hasValue()) animate = other.animate;
        if (other.hideOnScroll.hasValue()) hideOnScroll = other.hideOnScroll;
        if (other.drawBehind.hasValue()) drawBehind = other.drawBehind;
        if (other.height.hasValue()) height = other.height;
        if (other.borderHeight.hasValue()) borderHeight = other.borderHeight;
        if (other.borderColor.hasValue()) borderColor = other.borderColor;
        if (other.elevation.hasValue()) elevation = other.elevation;
        if (other.topMargin.hasValue()) topMargin = other.topMargin;
        if (other.animateLeftButtons.hasValue()) animateLeftButtons = other.animateLeftButtons;
        if (other.animateRightButtons.hasValue()) animateRightButtons = other.animateRightButtons;

        validate();
    }

    public TopBarOptions mergeWithDefault(TopBarOptions defaultOptions) {
        title.mergeWithDefault(defaultOptions.title);
        subtitle.mergeWithDefault(defaultOptions.subtitle);
        background.mergeWithDefault(defaultOptions.background);
        buttons.mergeWithDefault(defaultOptions.buttons);

        rightButtonColor.mergeWithDefault(defaultOptions.rightButtonColor);
        leftButtonColor.mergeWithDefault(defaultOptions.leftButtonColor);
        rightButtonDisabledColor.mergeWithDefault(defaultOptions.rightButtonDisabledColor);
        leftButtonDisabledColor.mergeWithDefault(defaultOptions.leftButtonDisabledColor);

        if (!visible.hasValue()) visible = defaultOptions.visible;
        if (!animate.hasValue()) animate = defaultOptions.animate;
        if (!hideOnScroll.hasValue()) hideOnScroll = defaultOptions.hideOnScroll;
        if (!drawBehind.hasValue()) drawBehind = defaultOptions.drawBehind;
        if (!testId.hasValue()) testId = defaultOptions.testId;
        if (!height.hasValue()) height = defaultOptions.height;
        if (!borderHeight.hasValue()) borderHeight = defaultOptions.borderHeight;
        if (!borderColor.hasValue()) borderColor = defaultOptions.borderColor;
        if (!elevation.hasValue()) elevation = defaultOptions.elevation;
        if (!topMargin.hasValue()) topMargin = defaultOptions.topMargin;
        if (!animateLeftButtons.hasValue()) animateLeftButtons = defaultOptions.animateLeftButtons;
        if (!animateRightButtons.hasValue()) animateRightButtons = defaultOptions.animateRightButtons;

        validate();
        return this;
    }

    public void validate() {
        if (title.component.hasValue() && (title.text.hasValue() || subtitle.text.hasValue())) {
            if (BuildConfig.DEBUG) Log.w("RNN", "A screen can't use both text and component - clearing text.");
            title.text = new NullText();
            subtitle.text = new NullText();
        }
    }

    public boolean isHiddenOrDrawBehind() {
        return drawBehind.isTrue() || visible.isFalse();
    }


}
