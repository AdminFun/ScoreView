package com.fun.widget.scoreview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者: Created by AdminFun
 * 邮箱: 614484070@qq.com
 * 创建: 2019/1/18
 * 修改: 2019/1/18
 * 版本: v1.0.0
 * 描述: ItemView
 */
@SuppressWarnings("unused")
public class ItemView extends View {

    private final String Item = "common";
    private String mCurrentText;    // 当前文本
    private float mBorderWidth;     // 边框大小
    private float mBorderRadius;    // 边框角度
    private int mBorderColor;       // 边框颜色
    private float mTextSize;        // 文本字体大小
    private int mTextColor;         // 文本字体颜色
    private Typeface mTypeface = Typeface.DEFAULT;
    private int mHorizontalPadding; // 横向边距
    private int mVerticalPadding;   // 纵向边距
    private int mBackgroundColor;   // 文本背景色
    private Paint mPaint;
    private RectF mRectF;
    private float fontH, fontW;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ItemView setText(String text){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectF = new RectF();
        mCurrentText = text == null ? "" : text;
        return this;
    }

    /**
     * 文字的高度 = 下基准线 - 上基准线，但部分手机上基准线是负数，导致越减越大，所以我用绝对值求差
     * 正确写法：fontH = fontMetrics.descent - fontMetrics.ascent;
     */
    private void onDealText() {
        mPaint.setTypeface(mTypeface);
        mPaint.setTextSize(mTextSize);
        final Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        fontH = Math.abs(fontMetrics.descent) - Math.abs(fontMetrics.ascent);
        fontH = Math.abs(fontH);
        fontW = mPaint.measureText(mCurrentText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = mVerticalPadding * 2 + (int) fontH;
        int width = mHorizontalPadding * 2 + (int) fontW;
        this.setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(mBorderWidth, mBorderWidth, w - mBorderWidth, h - mBorderWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // draw background
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mBackgroundColor);
        canvas.drawRoundRect(mRectF, mBorderRadius, mBorderRadius, mPaint);

        // draw border
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mBorderColor);
        canvas.drawRoundRect(mRectF, mBorderRadius, mBorderRadius, mPaint);

        // draw text
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        canvas.drawText(mCurrentText, getWidth() / 2 - fontW / 2, (getHeight() / 2) + (fontH / 2), mPaint);
    }

    public String getText() {
        return mCurrentText;
    }

    public void setItemBackgroundColor(int color) {
        this.mBackgroundColor = color;
    }

    public void setItemBorderColor(int color) {
        this.mBorderColor = color;
    }

    public void setItemTextColor(int color) {
        this.mTextColor = color;
    }

    public void setBorderWidth(float width) {
        this.mBorderWidth = width;
    }

    public void setBorderRadius(float radius) {
        this.mBorderRadius = radius;
    }

    public void setTextSize(float size) {
        this.mTextSize = size;
        this.onDealText();
    }

    public void setHorizontalPadding(int padding) {
        this.mHorizontalPadding = padding;
    }

    public void setVerticalPadding(int padding) {
        this.mVerticalPadding = padding;
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
        this.onDealText();
    }
}