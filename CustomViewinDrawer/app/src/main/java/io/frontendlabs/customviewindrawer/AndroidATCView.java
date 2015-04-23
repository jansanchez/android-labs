package io.frontendlabs.customviewindrawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jansanchez on 22/04/2015.
 */
public class AndroidATCView extends View {

    // circle and text colors
    private int squareColor, labelColor;
    // label text
    private String squareText;
    // paint for drawing custom view
    private Paint squarePaint;



    public AndroidATCView(Context context, AttributeSet attrs) {
        super(context, attrs);

        squarePaint = new Paint();
        TypedArray theme = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AndroidATCView, 0, 0);

        try {
            squareText = theme.getString(R.styleable.AndroidATCView_squareText);
            squareColor = theme.getInteger(R.styleable.AndroidATCView_squareColor, 0);
            labelColor = theme.getInteger(R.styleable.AndroidATCView_labelColor, 0);


        }finally {
            theme.recycle();
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        squarePaint.setStyle(Paint.Style.FILL);
        squarePaint.setAntiAlias(true);
        squarePaint.setColor(squareColor);

        canvas.drawRect(0, 0, this.getMeasuredWidth(),
                        this.getMeasuredHeight(),
                        squarePaint);

        squarePaint.setColor(labelColor);
        squarePaint.setTextAlign(Paint.Align.CENTER);
        squarePaint.setTextSize(50);
        canvas.drawText(squareText,
                        this.getMeasuredWidth()/2,
                        this.getMeasuredHeight()/2,
                        squarePaint);


    }


    public int getSquareColor() {
        return squareColor;
    }

    public void setSquareColor(int squareColor) {
        this.squareColor = squareColor;
        invalidate();
        requestLayout();
    }

    public int getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
        invalidate();
        requestLayout();
    }

    public String getSquareText() {
        return squareText;
    }

    public void setSquareText(String squareText) {
        this.squareText = squareText;
        invalidate();
        requestLayout();
    }

}
