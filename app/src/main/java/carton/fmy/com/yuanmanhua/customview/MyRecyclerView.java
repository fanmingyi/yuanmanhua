package carton.fmy.com.yuanmanhua.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import carton.fmy.com.yuanmanhua.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyRecyclerView extends RecyclerView {

    private Bitmap background;

    public MyRecyclerView(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(getResources(),
                R.mipmap.bookshelf_layer_center);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        background = BitmapFactory.decodeResource(getResources(),
                R.mipmap.bookshelf_layer_center);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {


        View childAt = getChildAt(0);

        if (childAt!=null){
            for (int y=0;y<getChildCount();y+=2){
                View childAt1 = getChildAt(y);
                if (childAt1!=null){
                    Rect rect = new Rect(0,childAt1.getTop(),getWidth(),y+childAt.getHeight()+20+childAt1.getTop());
                    canvas.drawBitmap(background,null,rect,null);
                }
            }

        }


        super.dispatchDraw(canvas);
    }
}
