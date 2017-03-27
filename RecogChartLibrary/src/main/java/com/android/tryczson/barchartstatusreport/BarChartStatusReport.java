package com.android.tryczson.barchartstatusreport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.tryczson.barchartstatusreport.Object.BarChartStatusData;

import java.util.ArrayList;

/**
 * Created by tryczson on 23/03/2017.
 */

public class BarChartStatusReport extends View {

    private Paint mPaint = new Paint();
    private ArrayList<BarChartStatusData> mData = new ArrayList<>();
    private Canvas tempCanvas;
    private int mWidth, mHeight, mPadding;
    private float mWidthCol, mHeightCol;
    private float mMax = 0;
    int mMax2;
    private int flag = 1;

    public BarChartStatusReport(Context context) {
        super(context);
    }

    public BarChartStatusReport(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mWidthCol = (float) (w / 5.7);
        mHeightCol = (float) (h / (mData.size() + 1));
        mPadding = 50;
    }

    public ArrayList<BarChartStatusData> getListStatus() {
        return mData;
    }

    public void setListStatus(ArrayList<BarChartStatusData> pData) {
        this.mData = pData;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        if (mData.size() > 0) {
            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            tempCanvas = new Canvas(bitmap);
            tempCanvas.drawColor(Color.WHITE);

            for (int i = 0; i < mData.size(); i++)
                mMax = Math.max(mMax, mData.get(i).getPoint());
            if (flag == 1) {
                mMax2 = (int) mMax;
                flag = 0;
            }
            if (mMax <= 30) {
                while (mMax % 3 != 0) mMax = mMax + 1;
            } else if (mMax > 30 && mMax < 300) {
                mMax = (float) (Math.ceil(mMax / 10) * 10);
                while (mMax % 3 != 0) mMax = mMax + 10;
            } else if (mMax > 300) {
                mMax = (float) (Math.ceil(mMax / 100) * 100);
                while (mMax % 3 != 0) mMax = mMax + 100;
            }

            float mPointPerCol = mMax / 3;
            double mPerPoint = mWidthCol / mPointPerCol;
            Rect bounds = new Rect();

            // draw line collum
            mPaint.setColor(Color.parseColor("#cdcdcd"));
            for (int i = 2; i < 6; i++) {
                mPaint.setStrokeWidth(2);
                if (i == 2)
                    mPaint.setStrokeWidth(4);
                tempCanvas.drawLine(mWidthCol * i -2 , 0, mWidthCol * i -2 , mHeightCol * mData.size(), mPaint);
            }

            // draw point collum
            mPaint.setColor(Color.parseColor("#a0a0a0"));
            mPaint.setStrokeWidth(1);
            mPaint.setTextSize(mWidth / 25f);
            for (int i = 0; i < 4; i++) {
                mPaint.getTextBounds(String.valueOf((int) (mPointPerCol * i)), 0, String.valueOf((int) (mPointPerCol * i)).length(), bounds);
                tempCanvas.drawText(String.valueOf((int) (mPointPerCol * i)), mWidthCol * (i + 2) - bounds.width() / 2, mHeightCol * mData.size() + 50, mPaint);
            }

            // draw name status
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).getPoint() == mMax2)
                    mPaint.setTextSize(mWidth / 22f);
                else mPaint.setTextSize(mWidth / 25f);
                mPaint.setColor(Color.parseColor(mData.get(i).getColor()));
                tempCanvas.drawText(mData.get(i).getName(), 20, mHeightCol * i + mPadding + 15, mPaint);
            }

            //draw point bar chart
            for (int i = 0; i < mData.size(); i++) {
                mPaint.setTextSize(mWidth / 20f);
                mPaint.setColor(Color.parseColor("#000000"));
                if (mData.get(i).getPoint() != mMax2) {
                    mPaint.setTextSize(mWidth / 25f);
                    mPaint.setColor(Color.parseColor("#a0a0a0"));
                }
                tempCanvas.drawText(String.valueOf(mData.get(i).getPoint() + "pt"), (float) (mWidthCol * 2 + mPerPoint * mData.get(i).getPoint() + 10), mHeightCol * i + mPadding + 15, mPaint);
            }

            // draw bar chart
            for (int i = 0; i < mData.size(); i++) {
                mPaint.setColor(Color.parseColor(mData.get(i).getColor()));
                if (mData.get(i).getPoint() == mMax2)
                    mPaint.setStrokeWidth(48);
                else mPaint.setStrokeWidth(35);
                tempCanvas.drawLine(mWidthCol * 2, mHeightCol * i + mPadding, (float) (mWidthCol * 2 + mPerPoint * mData.get(i).getPoint()), mHeightCol * i + mPadding, mPaint);
            }

            canvas.save();
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.restore();
        } else {
            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            Canvas tempCanvas = new Canvas(bitmap);

            mPaint.setTextSize(mWidth / 15f);
            float xPos = (mWidth / 2 - (int) mPaint.measureText(String.valueOf("No data")) / 2);
            float yPos = (int) (mHeight / 2 - ((mPaint.descent() + mPaint.ascent()) / 2));
            tempCanvas.drawText(String.valueOf("No data"), xPos, yPos, mPaint);

            canvas.save();
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.restore();
        }
    }
}
