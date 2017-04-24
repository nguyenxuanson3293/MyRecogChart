package com.android.tryczson.barchartstatusreport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 * Created by tryczson on 24/03/2017.
 */

public class BarChartStatusProfile extends View {
    private Paint mPaint = new Paint();
    private ArrayList<BarChartStatusData> mData = new ArrayList<>();
    private Canvas tempCanvas;
    private int mWidth, mHeight, mPadding;
    private float mWidthCol, mHeightCol;
    private float mMax = 0;
    private int mMax2;
    private int flag = 1;

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
        mHeightCol = (float) (h / (mData.size() + 2));
        mPadding = (int) (mHeightCol/2);
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
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        if (mData.size() > 0) {
            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            tempCanvas = new Canvas(bitmap);
            tempCanvas.drawColor(Color.WHITE);

            int sum = 0;
            for (int i = 0; i < mData.size(); i++) {
                mMax = Math.max(mMax, mData.get(i).getPoint());
                sum = sum + mData.get(i).getPoint();
            }
            if (flag == 1) {
                mMax2 = (int) mMax;
                flag = 0;
            }
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
                mPaint.setStrokeWidth(1);
                if (i == 1)
                    mPaint.setStrokeWidth(2);
                tempCanvas.drawLine(mWidthCol * i , 0, mWidthCol*i , mHeightCol * mData.size(), mPaint);
            }

            // draw point collum
            mPaint.setColor(Color.parseColor("#A4A4A4"));
            mPaint.setStrokeWidth(1);
            mPaint.setTextSize(mWidth / 26f);
            for (int i = 0; i < 5; i++) {
                mPaint.getTextBounds(String.valueOf((int) (mPointPerCol * i)), 0, String.valueOf((int) (mPointPerCol * i)).length(), bounds);
                tempCanvas.drawText(String.valueOf((int) (mPointPerCol * i)), mWidthCol * (i + 1) - bounds.width() / 2, mHeightCol * mData.size() + 50, mPaint);
            }

            // draw name status
            mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            mPaint.setTextSize(mWidth / 22f);
            for (int i = 0; i < mData.size(); i++) {
                mPaint.setColor(Color.parseColor("#000000"));
                if (mData.get(i).getPoint() != mMax2) {
                    mPaint.setColor(Color.parseColor("#A4A4A4"));
                }
                mPaint.getTextBounds(mData.get(i).getName() , 0, mData.get(i).getName().length(), bounds);
                tempCanvas.drawText(mData.get(i).getName(), mWidthCol - bounds.width() - 30, mHeightCol * i + mPadding + 15, mPaint);
            }

            //draw point bar chart
            mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            mPaint.setTextSize(mWidth / 24f);
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
            tempCanvas.drawBitmap(bitmap1,(float) (mWidthCol * 1.9) ,(float) (mHeightCol * (mData.size() + 0.8)), null);

            mPaint.setTextSize(mWidth / 10f);
            mPaint.setStrokeWidth(3);
            mPaint.setColor(Color.parseColor("#000000"));
            mPaint.getTextBounds(String.valueOf(sum), 0, String.valueOf(sum).length(), bounds);
            tempCanvas.drawText(String.valueOf(sum),(float) (mWidthCol * 1.9 + bitmap1.getWidth()),(float) (mHeightCol * (mData.size() + 0.8)) + mWidth/10 , mPaint);
            mPaint.setTextSize(mWidth / 26f);
            mPaint.setStrokeWidth(1);
            tempCanvas.drawText("通", (float) (mWidthCol * 1.9 + bitmap1.getWidth() + bounds.width() + 10), (float) (mHeightCol * (mData.size() + 0.8)) + mWidth/10 - 2, mPaint);

            // draw bar chart
            int strokeBar = (int) (mHeightCol/3);
            mPaint.setStrokeWidth(strokeBar);
            for (int i = 0; i < mData.size(); i++) {
                mPaint.setColor(Color.parseColor(mData.get(i).getColor()));
                tempCanvas.drawLine(mWidthCol * 1, mHeightCol * i + mPadding, (float) (mWidthCol * 1 + mPerPoint * mData.get(i).getPoint()), mHeightCol * i + mPadding, mPaint);
            }

            mPaint.setStrokeWidth(strokeBar);
            for (int i = 0; i < mData.size(); i++) {
                mPaint.setColor(Color.parseColor("#FFFFFF"));
                if (mData.get(i).getPoint() == mMax2)
                    mPaint.setAlpha(50);
                else mPaint.setAlpha(120);
                tempCanvas.drawLine(mWidthCol * 1, mHeightCol * i + mPadding, (float) (mWidthCol * 1 + mPerPoint * mData.get(i).getPoint()), mHeightCol * i + mPadding, mPaint);
            }

            // draw bar chart child
            mPaint.setStrokeWidth(mHeightCol/23);
            for (int i = 0; i < mData.size(); i++) {
                float x = mWidthCol ;
                for (int j = 0; j< mData.get(i).getAll_stamps().size(); j++) {
                    mPaint.setColor(Color.parseColor(mData.get(i).getAll_stamps().get(j).getColor()));
                    tempCanvas.drawLine(x , mHeightCol * i + mPadding + strokeBar/2 + 2, (float) (x + mPerPoint * mData.get(i).getAll_stamps().get(j).getPoint()), mHeightCol * i + mPadding + strokeBar/2 + 2 , mPaint);
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
