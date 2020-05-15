package com.example.sunflower.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.sunflower.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

public class MaskedCardView extends MaterialCardView {

    private ShapeAppearancePathProvider pathProvider;
    private ShapeAppearanceModel shapeAppearanceModel;

    private Path path;
    private RectF rectF;

    public MaskedCardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        pathProvider = new ShapeAppearancePathProvider();
        shapeAppearanceModel = ShapeAppearanceModel.builder(context, attrs, 0, R.style.Widget_MaterialComponents_CardView).build();
        path = new Path();
        rectF = new RectF(0f, 0f, 0f, 0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF.right = w;
        rectF.bottom = h;
        pathProvider.calculatePath(shapeAppearanceModel, 1f, rectF, path);
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
