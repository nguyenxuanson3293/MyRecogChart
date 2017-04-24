package com.android.tryczson.barchartstatusreport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tryczson on 24/04/2017.
 */

public class BarChartMonthlyItem extends View {
    private String mLabel;
    private Paint mPaint;
    private int mWidth, mHeight;
    private String mColor = "#F2354B";
    private int mPoint;
    private float mPxPerPoint, mRowHeight;
    private boolean isShowCursor = false;
    private String mDate;

    public BarChartMonthlyItem(Context context) {
        super(context);
    }

    public BarChartMonthlyItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public BarChartMonthlyItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(bitmap);
        Rect bounds = new Rect();

        mPaint.setColor(Color.parseColor(mColor));
        mPaint.setStrokeWidth(32);

        tempCanvas.drawLine(mWidth/2 - 16 , mRowHeight*2, mWidth/2 - 16 , (mRowHeight * 2 ) - (mPxPerPoint*mPoint),  mPaint);

        mPaint.setColor(Color.parseColor("#828282"));
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(mWidth/4f);
        mPaint.getTextBounds( String.valueOf(CommonClass.splitDate(mDate).getMonth() + "日"), 0, String.valueOf(CommonClass.splitDate(mDate).getMonth() + "日").length(), bounds);
        tempCanvas.drawText(String.valueOf(CommonClass.splitDate(mDate).getMonth() + "日"), mWidth/2 - bounds.width() , mHeight - mRowHeight/2, mPaint);

        mPaint.setColor(Color.parseColor("#d7d7d7"));
        mPaint.setStrokeWidth(2);
        if (isShowCursor)
            tempCanvas.drawLine(mWidth/2 - 16 , mRowHeight*2 + 30, mWidth / 2 - 16 , 0, mPaint);

        canvas.save();
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.restore();
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
        invalidate();
    }

    public float getmRowHeight() {
        return mRowHeight;
    }

    public void setmRowHeight(float mRowHeight) {
        this.mRowHeight = mRowHeight;
        invalidate();
    }

    public float getmPxPerPoint() {
        return mPxPerPoint;
    }

    public void setmPxPerPoint(float mPxPerPoint) {
        this.mPxPerPoint = mPxPerPoint;
        invalidate();
    }

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
        invalidate();
    }

    public int getmPoint() {
        return mPoint;
    }

    public void setmPoint(int mPoint) {
        this.mPoint = mPoint;
        invalidate();
    }

    public boolean isShowCursor() {
        return isShowCursor;
    }

    public void setShowCursor(boolean showCursor) {
        isShowCursor = showCursor;
        invalidate();
    }
}
