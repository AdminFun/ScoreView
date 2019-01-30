package com.fun.widget.scoreview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.fun.widget.scoreview.util.Utils;

/**
 * 作者: Created by AdminFun
 * 邮箱: 614484070@qq.com
 * 创建: 2019/1/18
 * 修改: 2019/1/18
 * 版本: v1.0.0
 * 描述: 计分器
 */
@SuppressWarnings("unused")
public class ScoreView extends LinearLayout {

    private String mCurrentText;                // 当前文本

    // GroupView的属性
    private int mHorizontalInterval;            // 容器横向间距

    // ItemView的属性
    private int mItemBorderColor = Color.parseColor("#88F44336");   // 子文本边框颜色
    private float mItemBorderWidth = 0.5f;      // 子文本边框宽度
    private float mItemBorderRadius = 15.0f;    // 子文本边框圆角
    private float mItemTextSize = 14;           // 子文本字体大小
    private int mItemTextColor = Color.parseColor("#FF666666");     // 子文本字体颜色
    private int mItemHorizontalPadding = 10;    // 子文本横向间距
    private int mItemVerticalPadding = 8;       // 子文本纵向间距
    private int mItemBackgroundResource;
    private int mItemBackgroundColor = Color.parseColor("#33F44336");// 子文本背景色
    private Typeface mItemTypeface = Typeface.DEFAULT;// 子文本样式

    // 绘制ItemView需要用到的工具和计算长宽
    private RectF mRectF;
    private final int[] groupPadding = {0, 0, 0, 0};
    private int widthSpecSize, widthSpecMode, heightSpecSize, heightSpecMode;

    public ScoreView(Context context) {
        this(context, null);
    }

    public ScoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ScoreView, defStyleAttr, 0);
        mHorizontalInterval = (int) attributes.getDimension(R.styleable.ScoreView_score_horizontal_space, Utils.dp2px(context, 1));
        mItemBorderWidth = attributes.getDimension(R.styleable.ScoreView_item_border_width, Utils.dp2px(context, mItemBorderWidth));
        mItemBorderRadius = attributes.getDimension(R.styleable.ScoreView_item_corner_radius, Utils.dp2px(context, mItemBorderRadius));
        mItemHorizontalPadding = (int) attributes.getDimension(R.styleable.ScoreView_item_horizontal_padding, Utils.dp2px(context, mItemHorizontalPadding));
        mItemVerticalPadding = (int) attributes.getDimension(R.styleable.ScoreView_item_vertical_padding, Utils.dp2px(context, mItemVerticalPadding));
        mItemTextSize = attributes.getDimension(R.styleable.ScoreView_item_text_size, Utils.sp2px(context, mItemTextSize));
        mItemBorderColor = attributes.getColor(R.styleable.ScoreView_item_border_color, mItemBorderColor);
        mItemBackgroundColor = attributes.getColor(R.styleable.ScoreView_item_background_color, mItemBackgroundColor);
        mItemTextColor = attributes.getColor(R.styleable.ScoreView_item_text_color, mItemTextColor);
        mItemBackgroundResource = attributes.getResourceId(R.styleable.ScoreView_item_background, mItemBackgroundResource);
        mCurrentText = attributes.getString(R.styleable.ScoreView_score_text);
        attributes.recycle();

        this.mRectF = new RectF();
        this.setWillNotDraw(false);
        this.setItemHorizontalPadding(mItemHorizontalPadding);
        this.setItemVerticalPadding(mItemVerticalPadding);

        if (!TextUtils.isEmpty(mCurrentText)) {
            this.setText(mCurrentText);
        }
    }

    /**
     * 设置文本
     */
    public void setText(String text) {
        if (!TextUtils.isEmpty(text)) {
            String[] mChildChar = Utils.toStringArray(text);
            if (mChildChar != null && mChildChar.length > 0) {
                this.removeAllItems();
                for (String char_ : mChildChar) {
                    addItemViewByPosition(char_);
                }
                this.postInvalidate();
            }
        }
    }

    public String getText() {
        return mCurrentText;
    }

    private void addItemViewByPosition(String text) {
        this.addView(initItemView(new ItemView(getContext()).setText(text)));
        if (mHorizontalInterval > 0) {
            this.addView(new View(getContext()), new LayoutParams(mHorizontalInterval, 1));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        this.widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        this.widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        this.heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        this.heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.groupPadding[0] = getPaddingLeft();
        this.groupPadding[1] = getPaddingTop();
        this.groupPadding[2] = getPaddingRight();
        this.groupPadding[3] = getPaddingBottom();

        final int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            int itemSpecWidth = getChildAt(0).getMeasuredWidth();
            int itemSpecHeight = getChildAt(0).getMeasuredHeight();
            int tempWidth, tempHeight;

            if (widthSpecMode == MeasureSpec.EXACTLY) {
                tempWidth = widthSpecSize;
            } else {
                tempWidth = itemSpecWidth * childCount + mHorizontalInterval * (childCount - 1)
                        + groupPadding[0] + groupPadding[2];
            }

            if (heightSpecMode == MeasureSpec.EXACTLY) {
                tempHeight = heightSpecSize;
            } else {
                tempHeight = itemSpecHeight + groupPadding[1] + groupPadding[3];
            }

            setMeasuredDimension(tempWidth, tempHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(0, 0, w, h);
    }

    // 初始化ItemView
    private ItemView initItemView(final ItemView itemView) {
        itemView.setItemBackgroundColor(mItemBackgroundColor);
        itemView.setItemBorderColor(mItemBorderColor);
        itemView.setItemTextColor(mItemTextColor);
        itemView.setTypeface(mItemTypeface);
        itemView.setBorderWidth(mItemBorderWidth);
        itemView.setBorderRadius(mItemBorderRadius);
        itemView.setTextSize(mItemTextSize);
        itemView.setHorizontalPadding(mItemHorizontalPadding);
        itemView.setVerticalPadding(mItemVerticalPadding);
        itemView.setBackgroundResource(mItemBackgroundResource);
        return itemView;
    }

    private int ceilItemBorderWidth() {
        return (int) Math.ceil(mItemBorderWidth);
    }

    public void removeAllItems() {
        this.removeAllViews();
        this.postInvalidate();
    }

    public void setHorizontalInterval(float interval) {
        mHorizontalInterval = (int) Utils.dp2px(getContext(), interval);
        postInvalidate();
    }

    public void setItemBorderWidth(float width) {
        this.mItemBorderWidth = width;
    }

    public void setItemBorderRadius(float radius) {
        this.mItemBorderRadius = radius;
    }

    public void setItemTextSize(float size) {
        this.mItemTextSize = size;
    }

    public void setItemHorizontalPadding(int padding) {
        int ceilWidth = ceilItemBorderWidth();
        this.mItemHorizontalPadding = padding < ceilWidth ? ceilWidth : padding;
    }

    public void setItemVerticalPadding(int padding) {
        int ceilWidth = ceilItemBorderWidth();
        this.mItemVerticalPadding = padding < ceilWidth ? ceilWidth : padding;
    }

    public void setItemBorderColor(int color) {
        this.mItemBorderColor = color;
    }

    public void setItemBackgroundColor(int color) {
        this.mItemBackgroundColor = color;
    }

    public void setItemTypeface(Typeface typeface) {
        this.mItemTypeface = typeface;
    }

    public void setItemTextColor(int color) {
        this.mItemTextColor = color;
    }

    public void setItemBackgroundResource(@DrawableRes int BackgroundResource) {
        this.mItemBackgroundResource = BackgroundResource;
    }
}