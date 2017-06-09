package com.android.tryczson.barchartstatusreport

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by tryczson on 23/03/2017.
 */

class PieChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
     val rect = RectF()
     var mPaint = Paint()
     var mTextPieChart = ""
     var mColorPieChart = "#f86d94"
    var percentage: Int = 0
     var cx: Int = 0
     var cy: Int = 0
     var mWidth: Int = 0
     var mHeight: Int = 0

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.pieChartElement)
        try {
            percentage = Integer.parseInt(a.getString(R.styleable.pieChartElement_percentage))
            mTextPieChart = a.getString(R.styleable.pieChartElement_text)
            mColorPieChart = a.getString(R.styleable.pieChartElement_colorChart)
        } catch (e: Exception) {
            percentage = 0
        }

    }



    var textPieChart: String
        get() = mTextPieChart
        set(mTextPieChart) {
            this.mTextPieChart = mTextPieChart
            invalidate()
        }

    fun setColorPieChart(mColorPieChart: String) {
        this.mColorPieChart = mColorPieChart
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        cx = w / 2
        cy = h / 2
        if (w > h) {
            rect.set((w / 2 - h / 2).toFloat(), 0f, (w / 2 + h / 2).toFloat(), h.toFloat())
        } else {
            rect.set(0f, (h / 2 - w / 2).toFloat(), w.toFloat(), (h / 2 + w / 2).toFloat())
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (percentage != 0) {
            val bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
            val tempCanvas = Canvas(bitmap)

            tempCanvas.drawColor(Color.TRANSPARENT)
            mPaint.color = Color.parseColor(mColorPieChart)
            tempCanvas.drawArc(rect, -90f, (360 * percentage / 100).toFloat(), true, mPaint)

            mPaint.color = Color.parseColor("#f0f0f0")
            tempCanvas.drawArc(rect, (360 * percentage / 100 - 90).toFloat(), (360 - 360 * percentage / 100).toFloat(), true, mPaint)

            mPaint.color = Color.WHITE
            val scaledSize = resources.getDimensionPixelSize(R.dimen.report_size_text_piechart)
            mPaint.textSize = scaledSize.toFloat()
            if (percentage >= 30)
                tempCanvas.drawText(mTextPieChart, (cx + cx / 10).toFloat(), (cy - cy / 10).toFloat(), mPaint)

            canvas.save()
            canvas.drawBitmap(bitmap, 0f, 0f, null)
            canvas.restore()
        } else {
            val bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
            val tempCanvas = Canvas(bitmap)

            mPaint.textSize = mWidth / 15f
            val xPos = (cx - mPaint.measureText("No data".toString()).toInt() / 2).toFloat()
            val yPos = (cy - (mPaint.descent() + mPaint.ascent()) / 2).toInt().toFloat()
            tempCanvas.drawText("No data".toString(), xPos, yPos, mPaint)

            canvas.save()
            canvas.drawBitmap(bitmap, 0f, 0f, null)
            canvas.restore()
        }
    }
}
