package com.example.myapplication.adapters;

import android.content.res.Resources;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.myapplication.R;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT;

public class MyBindingAdapter {
    @BindingAdapter("isGone")
    public static void bindIsGone(View view, boolean isGone){
        if (isGone){
            view.setVisibility(View.GONE);
        }else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }

    @BindingAdapter("renderHtml")
    public static void bindRenderHtml(TextView view, String description) {
        if (description != null) {
            view.setText(HtmlCompat.fromHtml(description, FROM_HTML_MODE_COMPACT));
            view.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            view.setText("");
        }
    }

    @BindingAdapter("wateringText")
    public static void bindWateringText(TextView textView, int wateringInterval) {
        Resources resources = textView.getContext().getResources();
        String quantityString = resources.getQuantityString(R.plurals.watering_needs_suffix,
                wateringInterval, wateringInterval);

        textView.setText(quantityString);
    }
}
