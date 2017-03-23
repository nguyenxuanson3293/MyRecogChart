package com.android.tryczson.barchartstatusreport;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tryczson on 23/03/2017.
 */

public class PieChart extends View {
    private RectF rect = new RectF();
    private Paint mPaint = new Paint();
    private String mTextPieChart = "", mColorPieChart= "#f86d94";
    private int percentage;
    private int cx, cy, mWidth, mHeight;

    public PieChart(Context context) {
        this(context, null);
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.pieChartElement);
        try {
            percentage = Integer.parseInt(a.getString(R.styleable.pieChartElement_percentage));
            mTextPieChart = a.getString(R.styleable.pieChartElement_text);
            mColorPieChart = a.getString(R.styleable.pieChartElement_colorChart);
        } catch (Exception e) {
            percentage = 0;
        }
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
        invalidate();
    }

    public String getTextPieChart() {
        return mTextPieChart;
    }

    public void setTextPieChart(String mTextPieChart) {
        this.mTextPieChart = mTextPieChart;
        invalidate();
    }

    public void setColorPieChart(String mColorPieChart) {
        this.mColorPieChart = mColorPieChart;
        invalidate();
    }

    public int getPercentage() {
        return percentage;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        cx = w/2;
        cy = h/2  ;
        if (w > h) {
            rect.set(w / 2 - h / 2, 0, w / 2 + h / 2, h);
        } else {
            rect.set(0 , h / 2 - w / 2 , w, h / 2 + w / 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (percentage != 0) {
            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            Canvas tempCanvas = new Canvas(bitmap);

            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

            tempCanvas.drawColor(Color.TRANSPARENT);
            mPaint.setColor(Color.parseColor(mColorPieChart));
            tempCanvas.drawArc(rect, -90, 360 * percentage / 100, true, mPaint);

            mPaint.setColor(Color.parseColor("#f0f0f0"));
            tempCanvas.drawArc(rect, 360 * percentage / 100 - 90, 360 - 360 * percentage / 100, true, mPaint);

            mPaint.setColor(Color.WHITE);
            int scaledSize = getResources().getDimensionPixelSize(R.dimen.report_size_text_piechart);
            mPaint.setTextSize(scaledSize);
            if (percentage >= 30)
                tempCanvas.drawText(mTextPieChart, cx + cx / 10, cy - cy / 10, mPaint);

            canvas.save();
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.restore();
        }
        else {
            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            Canvas tempCanvas = new Canvas(bitmap);

            mPaint.setTextSize(mWidth / 15f);
            float xPos = (cx - (int) mPaint.measureText(String.valueOf("No data")) / 2);
            float yPos = (int) (cy - ((mPaint.descent() + mPaint.ascent()) / 2));
            tempCanvas.drawText(String.valueOf("No data"), xPos, yPos, mPaint);

            canvas.save();
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.restore();
        }
    }
}
