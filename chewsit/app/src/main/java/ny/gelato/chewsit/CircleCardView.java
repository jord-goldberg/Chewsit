package ny.gelato.chewsit;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by joshuagoldberg on 9/5/16.
 */
public class CircleCardView extends CardView {
    public CircleCardView(Context context) {
        super(context);
    }

    public CircleCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpec = widthMeasureSpec > heightMeasureSpec ? heightMeasureSpec : widthMeasureSpec;
        super.onMeasure(measureSpec, measureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
        setRadius(((float) size) / 2);
    }
}
