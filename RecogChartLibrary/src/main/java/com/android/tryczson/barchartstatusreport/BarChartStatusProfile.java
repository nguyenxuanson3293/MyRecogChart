package com.android.tryczson.barchartstatusreport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.tryczson.barchartstatusreport.Object.BarChartStatusData;

import java.util.ArrayList;

/**
 * Created by tryczson on 24/03/2017.
 */

public class BarChartStatusProfile extends View {
    private Paint mPaint = new Paint();
    private ArrayList<BarChartStatusData> mData = new ArrayList<>();
    private Canvas tempCanvas;
    private int mWidth, mHeight, mPadding;
    private float mWidthCol, mHeightCol;
    private float mMax = 0, mMax2;

    public BarChartStatusProfile(Context context) {
        super(context);
    }

    public BarChartStatusProfile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mWidthCol = (float) (w / 5.8);
        mHeightCol = (float) (h / (mData.size() + 1.5));
        mPadding = 75;
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

        if (mData.size() > 0) {
            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            tempCanvas = new Canvas(bitmap);
            tempCanvas.drawColor(Color.WHITE);

            int sum = 0;
            for (int i = 0; i < mData.size(); i++) {
                mMax = Math.max(mMax, mData.get(i).getPoint());
                sum = sum + mData.get(i).getPoint();
            }
            mMax2 = mMax;
            if (mMax <= 20) {
                while (mMax % 4 != 0) mMax = mMax + 1;
            } else if (mMax > 20 && mMax < 200) {
                mMax = (float) (Math.ceil(mMax / 10) * 10);
                while (mMax % 4 != 0) mMax = mMax + 10;
            } else if (mMax > 200) {
                mMax = (float) (Math.ceil(mMax / 100) * 100);
                while (mMax % 4 != 0) mMax = mMax + 100;
            }

            float mPointPerCol = mMax / 4;
            double mPerPoint = mWidthCol / mPointPerCol;
            Rect bounds = new Rect();

            // draw line collum
            mPaint.setColor(Color.parseColor("#A4A4A4"));
            for (int i = 1; i < 6; i++) {
                mPaint.setStrokeWidth(2);
                if (i == 1)
                    mPaint.setStrokeWidth(4);
                tempCanvas.drawLine(mWidthCol * i - 2 , 0, mWidthCol*i - 2, mHeightCol * mData.size(), mPaint);
            }

            // draw point collum
            mPaint.setColor(Color.parseColor("#A4A4A4"));
            mPaint.setTextSize(mWidth / 26f);
            for (int i = 0; i < 5; i++) {
                mPaint.getTextBounds(String.valueOf((int) (mPointPerCol * i)), 0, String.valueOf((int) (mPointPerCol * i)).length(), bounds);
                tempCanvas.drawText(String.valueOf((int) (mPointPerCol * i)), mWidthCol * (i + 1) - bounds.width() / 2, mHeightCol * mData.size() + 40, mPaint);
            }

            // draw name status
            mPaint.setTextSize(mWidth / 24f);
            for (int i = 0; i < mData.size(); i++) {
                mPaint.setColor(Color.parseColor("#000000"));
                if (mData.get(i).getPoint() != mMax2) {
                    mPaint.setColor(Color.parseColor("#A4A4A4"));
                }
                tempCanvas.drawText(mData.get(i).getName(), 40, mHeightCol * i + mPadding + 20, mPaint);
            }

            //draw point bar chart
            mPaint.setTextSize(mWidth / 26f);
            for (int i = 0; i < mData.size(); i++) {
                mPaint.setColor(Color.parseColor("#000000"));
                if (mData.get(i).getPoint() != mMax2) {
                    mPaint.setColor(Color.parseColor("#A4A4A4"));
                }
                tempCanvas.drawText(String.valueOf(mData.get(i).getPoint() + "通"), (float) (mWidthCol * 1 + mPerPoint * mData.get(i).getPoint() + 12), mHeightCol * i + mPadding + 15, mPaint);
            }

            // draw max point
            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_point_barchart);
            bitmap1 = Bitmap.createScaledBitmap(bitmap1,  mWidth/8, mWidth/7 , true);
            tempCanvas.drawBitmap(bitmap1,(float) (mWidthCol * 1.9) ,(float) (mHeightCol * (mData.size() + 0.5)), null);

            mPaint.setTextSize(mWidth / 10f);
            mPaint.setColor(Color.parseColor("#000000"));
            mPaint.getTextBounds(String.valueOf(sum), 0, String.valueOf(sum).length(), bounds);
            tempCanvas.drawText(String.valueOf(sum),(float) (mWidthCol * 1.9 + bitmap1.getWidth()),(float) (mHeightCol * (mData.size() + 0.5)) + mWidth/10 , mPaint);
            mPaint.setTextSize(mWidth / 26f);
            tempCanvas.drawText("通", (float) (mWidthCol * 1.9 + bitmap1.getWidth() + bounds.width() + 20), (float) (mHeightCol * (mData.size() + 0.5)) + mWidth/10, mPaint);

            // draw bar chart
            int strokeBar = 55;
            mPaint.setStrokeWidth(strokeBar);
            for (int i = 0; i < mData.size(); i++) {
                mPaint.setColor(Color.parseColor(mData.get(i).getColor()));
                if (mData.get(i).getPoint() == mMax2)
                    mPaint.setAlpha(210);
                else mPaint.setAlpha(150);
                tempCanvas.drawLine(mWidthCol * 1, mHeightCol * i + mPadding, (float) (mWidthCol * 1 + mPerPoint * mData.get(i).getPoint()), mHeightCol * i + mPadding, mPaint);
            }

            // draw bar chart child
            mPaint.setStrokeWidth(10);
            for (int i = 0; i < mData.size(); i++) {
                float x = mWidthCol ;
                for (int j = 0; j< mData.get(i).getAll_stamps().size(); j++) {
                    mPaint.setColor(Color.parseColor(mData.get(i).getAll_stamps().get(j).getColor()));
                    tempCanvas.drawLine(x , mHeightCol * i + mPadding + strokeBar/2 + 5, (float) (x + mPerPoint * mData.get(i).getAll_stamps().get(j).getPoint()), mHeightCol * i + mPadding + strokeBar/2 +5 , mPaint);
                    x = x + (float) (mPerPoint * mData.get(i).getAll_stamps().get(j).getPoint());
                }
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
