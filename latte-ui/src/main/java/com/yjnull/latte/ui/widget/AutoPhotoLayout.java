package com.yjnull.latte.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.yjnull.latte.ui.R;
import com.yjnull.latte_core.delegates.LatteDelegate;

import java.util.ArrayList;

/**
 * Created by Yangya on 2018/7/23
 * 微信多图上传自定义组件
 */
public final class AutoPhotoLayout extends LinearLayoutCompat{

    private int mCurrentNum = 0;
    private final int mMaxNum;
    private final int mMaxLineNum;
    private IconTextView mIconAdd = null;
    private LayoutParams mParams = null;
    //要删除的图片ID
    private int mDeleteId = 0;
    private AppCompatImageView mTargetImageVew = null;
    private final int mImageMargin;
    private LatteDelegate mDelegate = null;
    private ArrayList<View> mLineViews = null;
    private AlertDialog mTargetDialog = null;
    private static final String ICON_TEXT = "{fa-plus}";
    private final float mIconSize;

    private final ArrayList<ArrayList<View>> ALL_VIEWS = new ArrayList<>();
    private final ArrayList<Integer> LINE_HEIGHTS = new ArrayList<>();

    //防止多次的测量和布局过程
    private boolean mIsOnceInitOnMeasure = false;
    private boolean mHasInitOnLayout = false;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoPhotoLayout);
        mMaxNum = a.getInteger(R.styleable.AutoPhotoLayout_max_count, 1);
        mMaxLineNum = a.getInt(R.styleable.AutoPhotoLayout_line_count, 3);
        mImageMargin = a.getInt(R.styleable.AutoPhotoLayout_item_margin, 0);
        mIconSize = a.getDimension(R.styleable.AutoPhotoLayout_icon_size, 20);
        a.recycle();
    }

    public final void setDelegate(LatteDelegate delegate) {
        this.mDelegate = delegate;
    }

    public final void onCropTarget(Uri uri) {
        createNewImageView();
        Glide.with(mDelegate)
                .load(uri)
                .apply(OPTIONS)
                .into(mTargetImageVew);
    }

    private void createNewImageView() {
        mTargetImageVew = new AppCompatImageView(getContext());
        mTargetImageVew.setId(mCurrentNum);
        mTargetImageVew.setLayoutParams(mParams);
        mTargetImageVew.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //添加子View的时候传入位置
        this.addView(mTargetImageVew, mCurrentNum);
        mCurrentNum++;
        //当天家数目大于mMaxNum时，自动隐藏添加按钮
        if (mCurrentNum >= mMaxNum) {
            mIconAdd.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        //wrap_content
        int width = 0;
        int height = 0;
        //记录每一行的宽度与高度
        int lineWidth = 0;
        int lineHeight = 0;
        //得到内部元素个数
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            //测量子View的宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //得到LayoutParams
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //子View占据的宽度
            final int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            //子View占据的高度
            final int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //View是否超出一行，换行
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() + getPaddingRight()) {
                //得到最大宽度
                width = Math.max(width, lineWidth);
                height += lineHeight;
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //未换行
                //叠加行宽
                lineWidth += childWidth;
                //得到当前最大的高度
                lineHeight = Math.max(lineHeight, childHeight);
            }

            //最后一个子控件
            if (i == childCount - 1) {
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }

        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingRight() + getPaddingLeft(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );

        //设置一行所有图片的宽高
        final int imgSideLen = sizeWidth / mMaxLineNum;
        if (!mIsOnceInitOnMeasure) {
            mParams = new LayoutParams(imgSideLen, imgSideLen);
            mIsOnceInitOnMeasure = true;
        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        ALL_VIEWS.clear();
        LINE_HEIGHTS.clear();
        // 当前ViewGroup的宽度
        final int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        if (!mHasInitOnLayout) {
            mLineViews = new ArrayList<>();
            mHasInitOnLayout = true;
        }

        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            //如果需要换行
            if (lineWidth + childWidth + lp.leftMargin + lp.rightMargin >
                    width - getPaddingLeft() - getPaddingRight()) {
                LINE_HEIGHTS.add(lineHeight);
                ALL_VIEWS.add(mLineViews);
                //重置宽高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                mLineViews.clear();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, lineHeight + lp.topMargin + lp.bottomMargin);
            mLineViews.add(child);
        }
        //处理最后一行
        LINE_HEIGHTS.add(lineHeight);
        ALL_VIEWS.add(mLineViews);
        //设置子View位置
        int left = getPaddingLeft();
        int top = getPaddingTop();
        //行数
        final int lineNum = ALL_VIEWS.size();
        for (int i = 0; i < lineNum; i++) {
            mLineViews = ALL_VIEWS.get(i);
            lineHeight = LINE_HEIGHTS.get(i);
            final int size = mLineViews.size();
            for (int j = 0; j < size; j++) {
                final View child = mLineViews.get(j);
                if (child.getVisibility() == GONE) continue;
                final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                final int lc = left + lp.leftMargin;
                final int tc = top + lp.topMargin;
                final int rc = lc + child.getMeasuredWidth() - mImageMargin;
                final int bc = tc + child.getMeasuredHeight();
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }

        mIconAdd.setLayoutParams(mParams);
        mHasInitOnLayout = false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initAddIcon();
        mTargetDialog = new AlertDialog.Builder(getContext()).create();
    }

    private void initAddIcon() {
        mIconAdd = new IconTextView(getContext());
        mIconAdd.setText(ICON_TEXT);
        mIconAdd.setGravity(Gravity.CENTER);
        mIconAdd.setTextSize(mIconSize);
        mIconAdd.setBackgroundResource(R.drawable.border_text);
        mIconAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.startCameraWithCheck();
            }
        });
        this.addView(mIconAdd);
    }
}
