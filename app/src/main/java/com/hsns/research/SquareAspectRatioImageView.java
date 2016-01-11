package com.hsns.research;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by otdom on 1/11/16.
 */
public class SquareAspectRatioImageView extends ImageView {
    public SquareAspectRatioImageView(Context context) {
        super(context);
    }

    public SquareAspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareAspectRatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SquareAspectRatioImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int squareHeight = MeasureSpec.getSize(widthMeasureSpec);
        int squareHeightSpect = MeasureSpec.makeMeasureSpec(squareHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, squareHeightSpect);
    }
}
