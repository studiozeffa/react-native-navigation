package com.reactnativenavigation.options;


import android.content.Context;

import com.reactnativenavigation.options.params.Bool;
import com.reactnativenavigation.options.params.NullBool;
import com.reactnativenavigation.options.params.NullText;
import com.reactnativenavigation.options.params.RNNColour;
import com.reactnativenavigation.options.params.RNNColourKt;
import com.reactnativenavigation.options.params.RNNNullColor;
import com.reactnativenavigation.options.params.Text;
import com.reactnativenavigation.options.parsers.BoolParser;
import com.reactnativenavigation.options.parsers.TextParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FabOptions {

    public static FabOptions parse(Context context, JSONObject json) {
        FabOptions options = new FabOptions();
        if (json == null) return options;

        options.id = TextParser.parse(json, "id");
        options.backgroundColor = RNNColourKt.parse(context, json.optJSONObject("backgroundColor"));
        options.clickColor = RNNColourKt.parse(context, json.optJSONObject("clickColor"));
        options.rippleColor = RNNColourKt.parse(context, json.optJSONObject("rippleColor"));
        options.visible = BoolParser.parse(json, "visible");
        if (json.has("icon")) {
            options.icon = TextParser.parse(json.optJSONObject("icon"), "uri");
        }
        options.iconColor = RNNColourKt.parse(context, json.optJSONObject( "iconColor"));
        if (json.has("actions")) {
            JSONArray fabsArray = json.optJSONArray("actions");
            for (int i = 0; i < fabsArray.length(); i++) {
                options.actionsArray.add(FabOptions.parse(context, fabsArray.optJSONObject(i)));
            }
        }
        options.alignHorizontally = TextParser.parse(json, "alignHorizontally");
        options.alignVertically = TextParser.parse(json, "alignVertically");
        options.hideOnScroll = BoolParser.parse(json, "hideOnScroll");
        options.size = TextParser.parse(json, "size");

        return options;
    }

    public Text id = new NullText();
    public RNNColour backgroundColor = new RNNNullColor();
    public RNNColour clickColor = new RNNNullColor();
    public RNNColour rippleColor = new RNNNullColor();
    public Text icon = new NullText();
    public RNNColour iconColor = new RNNNullColor();
    public Bool visible = new NullBool();
    public ArrayList<FabOptions> actionsArray = new ArrayList<>();
    public Text alignHorizontally = new NullText();
    public Text alignVertically = new NullText();
    public Bool hideOnScroll = new NullBool();
    public Text size = new NullText();

    void mergeWith(final FabOptions other) {
        if (other.id.hasValue()) {
            id = other.id;
        }

        backgroundColor.mergeWith(other.backgroundColor);
        clickColor.mergeWith(other.clickColor);
        iconColor.mergeWith(other.iconColor);
        rippleColor.mergeWith(other.rippleColor);

        if (other.visible.hasValue()) {
            visible = other.visible;
        }
        if (other.icon.hasValue()) {
            icon = other.icon;
        }

        if (other.actionsArray.size() > 0) {
            actionsArray = other.actionsArray;
        }
        if (other.alignVertically.hasValue()) {
            alignVertically = other.alignVertically;
        }
        if (other.alignHorizontally.hasValue()) {
            alignHorizontally = other.alignHorizontally;
        }
        if (other.hideOnScroll.hasValue()) {
            hideOnScroll = other.hideOnScroll;
        }
        if (other.size.hasValue()) {
            size = other.size;
        }
    }

    void mergeWithDefault(FabOptions defaultOptions) {
        if (!id.hasValue()) {
            id = defaultOptions.id;
        }
        backgroundColor.mergeWithDefault(defaultOptions.backgroundColor);
        clickColor.mergeWithDefault(defaultOptions.clickColor);
        rippleColor.mergeWithDefault(defaultOptions.rippleColor);
        iconColor.mergeWithDefault(defaultOptions.iconColor);

        if (!visible.hasValue()) {
            visible = defaultOptions.visible;
        }
        if (!icon.hasValue()) {
            icon = defaultOptions.icon;
        }
        if (actionsArray.size() == 0) {
            actionsArray = defaultOptions.actionsArray;
        }
        if (!alignHorizontally.hasValue()) {
            alignHorizontally = defaultOptions.alignHorizontally;
        }
        if (!alignVertically.hasValue()) {
            alignVertically = defaultOptions.alignVertically;
        }
        if (!hideOnScroll.hasValue()) {
            hideOnScroll = defaultOptions.hideOnScroll;
        }
        if (!size.hasValue()) {
            size = defaultOptions.size;
        }
    }

    public boolean hasValue() {
        return id.hasValue() || icon.hasValue();
    }

}
