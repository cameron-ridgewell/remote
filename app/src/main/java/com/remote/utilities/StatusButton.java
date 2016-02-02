package com.remote.utilities;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.remote.R;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by cameronridgewell on 1/28/16.
 */
public class StatusButton extends View {
    private String status;
    private FancyButton button;

    public StatusButton(Context context) {
        super(context);
    }

    public StatusButton(FancyButton button) {
        super(button.getContext());
        this.button = button;
        this.status = "";
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FancyButton get() {
        return button;
    }

    public void setText(String text) {
        this.button.setText(text);
    }

    public void setTextColor(int color) {
        this.button.setTextColor(color);
    }

    public void setIconColor(int color) {
        this.button.setIconColor(color);
    }

    public void setBackgroundColor(int color) {
        this.button.setBackgroundColor(color);
    }

    public void setFocusBackgroundColor(int color) {
        this.button.setFocusBackgroundColor(color);
    }

    public void setTextSize(int textSize) {
        this.button.setTextSize(textSize);
    }

    public void setTextGravity(int gravity) {
        this.button.setTextGravity(gravity);
    }

    public void setIconPadding(int paddingLeft, int paddingTop, int paddingRight,
                               int paddingBottom) {
        this.button.setIconPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public void setIconResource(int drawable) {
        this.button.setIconResource(drawable);
    }

    public void setIconResource(String icon) {
        this.button.setIconResource(icon);
    }

    public void setFontIconSize(int iconSize) {
        this.button.setFontIconSize(iconSize);
    }

    public void setIconPosition(int position) {
        this.button.setIconPosition(position);
    }

    public void setBorderColor(int color) {
        this.button.setBorderColor(color);
    }

    public void setBorderWidth(int width) {
        this.button.setBorderWidth(width);
    }

    public void setRadius(int radius) {
        this.button.setRadius(radius);
    }

    public void setCustomTextFont(String fontName) {
        this.button.setCustomTextFont(fontName);
    }

    public void setCustomIconFont(String fontName) {
        this.button.setCustomIconFont(fontName);
    }

    public void setGhost(boolean ghost) {
        this.button.setGhost(ghost);
    }

    public CharSequence getText() {
        return this.button.getText();
    }

    public TextView getTextViewObject() {
        return this.button.getTextViewObject();
    }

    public TextView getIconFontObject() {
        return this.button.getIconFontObject();
    }

    public ImageView getIconImageObject() {
        return this.button.getIconImageObject();
    }

    public void setOnClickListener(View.OnClickListener clickListener) {
        this.button.setOnClickListener(clickListener);
    }

    public void selectButton(int color) {
        button.setTextColor(color);
        button.setBackgroundColor(getResources()
                .getColor(R.color.background_floating_material_light));
    }

    public void deselectButton(int color) {
        button.setBackgroundColor(color);
        button.setTextColor(getResources()
                .getColor(R.color.colorbuttontext));
    }
}
