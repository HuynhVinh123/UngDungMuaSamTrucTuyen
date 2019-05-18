package com.example.huynhvinh.applazada_java.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.huynhvinh.applazada_java.R;

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText {

    Drawable crossX, noneCrossX;
    Boolean visiable = false;
    Drawable drawable ;

    public ClearEditText(Context context) {
        super(context);
        KhoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        KhoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        KhoiTao();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        KhoiTao();
    }

    private void KhoiTao(){
        crossX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        noneCrossX = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();
        CauHinh();
    }

    private void CauHinh(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visiable ? crossX : noneCrossX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    // Bắt sự kiện thay đổi text bằng việc thêm và xóa
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter ==0 && start ==0)
        {
            visiable = false;
            CauHinh();
        }
        else
        {
            visiable = true;
            CauHinh();
        }

    }

    // Bắt sự kiện click như Password

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width()))
        {
           setText("");
        }
        return super.onTouchEvent(event);
    }
}
