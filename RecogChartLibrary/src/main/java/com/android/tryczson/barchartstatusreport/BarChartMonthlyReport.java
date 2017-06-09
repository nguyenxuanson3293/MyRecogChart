package com.android.tryczson.barchartstatusreport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.tryczson.barchartstatusreport.Object.BarChartMonthlyData;
import com.android.tryczson.barchartstatusreport.Object.SplitDate;

import java.util.ArrayList;

/**
 * Created by tryczson on 24/04/2017.
 */

public class BarChartMonthlyReport extends RelativeLayout {
    private int mWidth, mHeight;
    private float mRowHeight, mDateHeight, mPointHeight;
    private float mTopChart, mLeftChart;
    private Paint mPaint;
    private BarChartBackground mLinearBackground;
    private ArrayList<BarChartMonthlyData> mListData = new ArrayList<>();
    private RecyclerView mSnappingRecyclerView;
    private String mDate = "", mPoint = "", mColor = "";
    private boolean isFirst = true, isFirst2 = true, isFirst3 = true;
    private float mMax;
    private float mPerPoint;
    private int selectedPosition = 0;
    private SplitDate date;
    private int positionLast;

    public BarChartMonthlyReport(Context context) {
        super(context);
    }

    public BarChartMonthlyReport(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public BarChartMonthlyReport(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mLinearBackground = new BarChartBackground(getContext());
        addView(mLinearBackground);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        mRowHeight = h / 4;
    }

    public ArrayList<BarChartMonthlyData> getListMonthly() {
        return mListData;
    }

    public void setListMonthly(ArrayList<BarChartMonthlyData> pData) {
        this.mListData = pData;
        this.invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mListData.size() > 0) {
            if (isFirst) {
                mDate = mListData.get(0).getDate();
                mPoint = String.valueOf(mListData.get(0).getPt());
            }

            for (int i = 0; i < mListData.size(); i++)
                mMax = Math.max(mMax, mListData.get(i).getPt());
            if (mMax <= 20) {
                while (mMax % 2 != 0) mMax = mMax + 1;
            } else if (mMax > 20 && mMax < 200) {
                mMax = (float) (Math.ceil(mMax / 10) * 10);
                while (mMax % 2 != 0) mMax = mMax + 10;
            } else if (mMax > 200) {
                mMax = (float) (Math.ceil(mMax / 100) * 100);
                while (mMax % 2 != 0) mMax = mMax + 100;
            }

            Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            Canvas tempCanvas = new Canvas(bitmap);
            Rect bounds = new Rect();

            mPaint.setColor(Color.parseColor("#000000"));
            mPaint.setTextSize(mWidth / 23f);
            date = CommonClass.splitDate(mDate);

            mPaint.getTextBounds(date.getYear() + "年" + date.getMonth() + "月", 0, String.valueOf(date.getYear() + "年" + date.getMonth() + "月").length(), bounds);
            mDateHeight = bounds.height() + 50;
            tempCanvas.drawText(date.getYear() + "年" + date.getMonth() + "月", mWidth / 2 - bounds.width() / 2, mDateHeight - 20, mPaint);

            mPaint.setTextSize(mWidth / 10f);
            mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
         //   mPaint.setStrokeWidth(5);
            mPaint.getTextBounds(mPoint, 0, mPoint.length(), bounds);
            mPointHeight = bounds.height();
            tempCanvas.drawText(mPoint, mWidth / 2 - bounds.width() / 2, mDateHeight + mPointHeight + 10, mPaint);

            mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            mPaint.setTextSize(mWidth / 25f);
            mPaint.setColor(Color.parseColor("#000000"));
            tempCanvas.drawText("pt", mWidth / 2 + bounds.width() / 2 + 10, mDateHeight + mPointHeight + 10, mPaint);

            mPaint.setTextSize(mWidth / 25f);
            mPaint.setColor(Color.parseColor("#cdcdcd"));
            tempCanvas.drawText("ポイント", 20 , 70 , mPaint);

            canvas.save();
            canvas.drawBitmap(bitmap, 0, 0, null);
            canvas.restore();
        }
    }

    private class BarChartBackground extends View {

        public BarChartBackground(Context context) {
            super(context);
        }

        public BarChartBackground(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public BarChartBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (mListData.size() > 0) {
                if (isFirst) {
                    selectedPosition = mListData.size() -1 ;
                    mDate = mListData.get(mListData.size() -1 ).getDate();
                    mPoint = String.valueOf(mListData.get(mListData.size() -1 ).getPt());
                }

                for (int i = 0; i < mListData.size(); i++)
                    mMax = Math.max(mMax, mListData.get(i).getPt());
                if (mMax <= 20) {
                    while (mMax % 2 != 0) mMax = mMax + 1;
                } else if (mMax > 20 && mMax < 200) {
                    mMax = (float) (Math.ceil(mMax / 10) * 10);
                    while (mMax % 2 != 0) mMax = mMax + 10;
                } else if (mMax > 200) {
                    mMax = (float) (Math.ceil(mMax / 100) * 100);
                    while (mMax % 2 != 0) mMax = mMax + 100;
                }
                float mPointPerCol = mMax / 2;
                mPerPoint = mRowHeight / mPointPerCol;

                Rect bounds = new Rect();
                Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
                Canvas tempCanvas = new Canvas(bitmap);

                mPaint.setColor(Color.parseColor("#828282"));
                mPaint.setStrokeWidth(1);
                mPaint.setTextSize(mWidth / 23f);
                mPaint.getTextBounds("00000", 0, "00000".length(), bounds);
                mTopChart = mRowHeight;
                mLeftChart = bounds.width() + 20;
                int x = 2;
                for (int i = 1; i < 4 ; i++) {
                    mPaint.getTextBounds(String.valueOf((int) (mPointPerCol * x)), 0, String.valueOf((int) (mPointPerCol * x)).length(), bounds);
                    tempCanvas.drawText(String.valueOf((int) (mPointPerCol * x)), mLeftChart - bounds.width() - 20  , mRowHeight * i + 10 , mPaint);
                    x--;
                }

                mPaint.setColor(Color.parseColor("#d7d7d7"));
                mPaint.setStrokeWidth(2);
                for (int i = 1 ; i < 4; i++) {
                    tempCanvas.drawLine(mLeftChart , mRowHeight * i, mWidth, mRowHeight * i, mPaint);
                }
                tempCanvas.drawLine( mLeftChart , mRowHeight * 3 + 30, mWidth, mRowHeight * 3 + 30, mPaint);

                canvas.save();
                canvas.drawBitmap(bitmap, 0, 0, null);
                canvas.restore();

                if (isFirst) {
                    mSnappingRecyclerView = new RecyclerView(getContext());
                    RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams((int) (mWidth - mLeftChart), (int) (mHeight - mTopChart));
                    param.addRule(ALIGN_PARENT_RIGHT);
                    param.addRule(ALIGN_PARENT_BOTTOM);
                    mSnappingRecyclerView.setLayoutParams(param);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mSnappingRecyclerView.setLayoutManager(manager);
                    SnapHelper snapHelper = new LinearSnapHelper();
                    snapHelper.attachToRecyclerView(mSnappingRecyclerView);
                    addView(mSnappingRecyclerView);
                    mSnappingRecyclerView.setAdapter(new barChartAdapter());
                    isFirst = false;
                    mSnappingRecyclerView.scrollToPosition(selectedPosition);
                }
            } else {
                Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
                Canvas tempCanvas = new Canvas(bitmap);

                mPaint.setTextSize(mWidth / 18f);
                float xPos = (mWidth / 2 - (int) mPaint.measureText(String.valueOf("No data")) / 2);
                float yPos = (int) (mHeight / 2 - ((mPaint.descent() + mPaint.ascent()) / 2));
                tempCanvas.drawText(String.valueOf("No data"), xPos, yPos, mPaint);

                canvas.save();
                canvas.drawBitmap(bitmap, 0, 0, null);
                canvas.restore();
            }
        }
    }

    public class barChartAdapter extends RecyclerView.Adapter {

        public barChartAdapter() {
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barchart, parent, false);
            return new VHItem(v);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof VHItem) {
                final VHItem item = (VHItem) holder;
                    if (position == selectedPosition) {
                        item.lineChartItemView.setShowCursor(true);
                    } else {
                        item.lineChartItemView.setShowCursor(false);
                    }

                item.lineChartItemView.setmPoint(mListData.get(position).getPt());
                item.lineChartItemView.setmDate(mListData.get(position).getDate());
                item.lineChartItemView.setmPxPerPoint(mPerPoint);
                item.lineChartItemView.setmRowHeight(mRowHeight);
                item.lineChartItemView.setmColor(mColor);

                item.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setmDate(mListData.get(position).getDate());
                        setmPoint(String.valueOf(mListData.get(position).getPt()));
                        selectedPosition = position;
                        if (position == selectedPosition) {
                            item.lineChartItemView.setShowCursor(true);
                            notifyDataSetChanged();
                        } else {
                            item.lineChartItemView.setShowCursor(false);
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mListData.size();
        }

        class VHItem extends RecyclerView.ViewHolder {
            public BarChartMonthlyItem lineChartItemView;

            public VHItem(View itemView) {
                super(itemView);
                lineChartItemView = (BarChartMonthlyItem) itemView.findViewById(R.id.barChart);
               // lineChartItemView.setmColor(color);
            }
        }
    }


    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
        invalidate();
    }

    public String getmPoint() {
        return mPoint;
    }

    public void setmPoint(String mPoint) {
        this.mPoint = mPoint;
        invalidate();
    }

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
    }
}
