package carton.fmy.com.yuanmanhua.customview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import carton.fmy.com.yuanmanhua.R;

public class LHLoadingView extends View{
	private Paint paint = new Paint();
	private List<SquareWrapper> wrappers;
	private RectF workArea ;//工作区域	
	//属性
	private int squareWidth;
	private int squareSpacing;
	private int squareColor;
	//运行时参数
	private int increment;
	
	public LHLoadingView(Context context) {
		super(context);
		squareWidth = dip2px(this.getContext(), 60);
		squareSpacing = dip2px(this.getContext(), 25);		
		squareColor = 0xFFCED5E3;
		
		int number = (int)(250*1.0/1000 * 83);
		this.increment = (int)((this.squareSpacing + this.squareWidth) / number);
	}
	public LHLoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.lhloadingview);
		squareWidth = (int)a.getDimension(R.styleable.lhloadingview_squareWidth, dip2px(this.getContext(), 60));
		squareSpacing = (int)a.getDimension(R.styleable.lhloadingview_squareSpacing, dip2px(this.getContext(), 25));
		
		squareColor = (int)a.getColor(R.styleable.lhloadingview_squareColor, 0);
		if(squareColor == 0)
			squareColor = a.getResourceId(R.styleable.lhloadingview_squareColor, 0);
		if(squareColor == 0)
			squareColor = 0xFFCED5E3;
		
		int cycle = a.getInt(R.styleable.lhloadingview_time, 250);//
		int number = (int)(cycle*1.0/1000 * 83);
		this.increment = (int)((this.squareSpacing + this.squareWidth) / number);
		this.increment = this.increment<=0?1:this.increment;
	}
	/**
	 * 测绘
	 */
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }	
	/**
     * 计算组件宽度
     */
    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
        	result = getDefaultWidth();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
    /**
     * 计算组件高度
     */
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = getDefaultHeight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
    /**
     * 计算默认宽度
     */
    private int getDefaultWidth(){
    	return this.squareWidth * 2 + this.squareSpacing;
    }
    /**
     * 计算默认宽度
     */
    private int getDefaultHeight(){
    	return this.squareWidth * 2 + this.squareSpacing;
    }
    /** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(this.workArea == null)
			createWorkArea();
		if(this.wrappers == null)
			createWrappers();
		paint.setColor(this.squareColor);
		for(int i = 0; i<this.wrappers.size(); i++){
			SquareWrapper wrapper = this.wrappers.get(i);
			RectF rectF = new RectF(wrapper.left, wrapper.top, wrapper.left+this.squareWidth , wrapper.top+this.squareWidth);
			canvas.drawRect(rectF, paint);	
			if(wrapper.enable){//活动的
				changePosition(wrapper, i);					
			}
		}
		this.invalidate();
		
	}
	
	/**
	 * 创建工作区域
	 */
	private void createWorkArea(){
		this.workArea = new RectF();
		int workAreaWidth = this.squareWidth * 2 + this.squareSpacing;
		workArea.left = this.getWidth()/2 - workAreaWidth/2;
		workArea.top = this.getHeight()/2 - workAreaWidth/2;
		workArea.bottom = workArea.top + workAreaWidth;
		workArea.right = workArea.left + workAreaWidth;
		
	}
	/**
	 * 创建wrappers
	 */
	private void createWrappers(){
		this.wrappers = new ArrayList<SquareWrapper>();
		//左上角
		SquareWrapper wrapper = new SquareWrapper();
		wrapper.point = leftTop;
		wrapper.enable = false;
		wrapper.left = (int)this.workArea.left;
		wrapper.top = (int)this.workArea.top;
		wrappers.add(wrapper);
		//左下角
		wrapper = new SquareWrapper();
		wrapper.point = leftBottom;
		wrapper.enable = false;
		wrapper.left = (int)this.workArea.left;
		wrapper.top = (int)this.workArea.bottom - this.squareWidth;
		wrappers.add(wrapper);
		//右下角
		wrapper = new SquareWrapper();
		wrapper.point = rightBottom;
		wrapper.enable = true;
		wrapper.left = (int)this.workArea.right - this.squareWidth;
		wrapper.top = (int)this.workArea.bottom - this.squareWidth;
		wrappers.add(wrapper);		
	}	
	/**
	 * 改变位置
	 */
	private void changePosition(SquareWrapper wrapper, int i){
		boolean isEnd = false;
		if(wrapper.point == leftTop){
			wrapper.top += this.increment;
			if(wrapper.top >= this.workArea.bottom-this.squareWidth){
				wrapper.top = (int)this.workArea.bottom-this.squareWidth;
				wrapper.point = leftBottom;
				isEnd = true;
			}
		}else  if(wrapper.point == leftBottom){
			wrapper.left += this.increment;
			if(wrapper.left >= this.workArea.right - this.squareWidth){
				wrapper.left = (int)this.workArea.right - this.squareWidth;
				wrapper.point = rightBottom;
				isEnd = true;
			}
		}else if(wrapper.point == rightBottom){
			wrapper.top -= this.increment;
			if(wrapper.top <= this.workArea.top){
				wrapper.top = (int)this.workArea.top;
				wrapper.point = rightTop;
				isEnd = true;
			}
		}else if(wrapper.point == rightTop){
			wrapper.left -= this.increment;
			if(wrapper.left <= this.workArea.left){
				wrapper.left = (int)this.workArea.left;
				wrapper.point = leftTop;
				isEnd = true;
			}
		}
		if(isEnd){
			wrapper.enable = false;
			int index = i - 1;
			index = index < 0 ? this.wrappers.size()-1 : index;
			this.wrappers.get(index).enable = true;
		}
	}
	/**
	 * 内部封装类
	 */
	private final int leftTop = 0;//左上角
	private final int leftBottom = 1;//左下角
	private final int rightBottom = 2;//右下角
	private final int rightTop = 3;//右下角
	private class SquareWrapper{
		private int point ;//当前方位
		private boolean enable;//是否正在移动中
		//当前左上角的坐标
		private int left;
		private int top;
		
	}
}
