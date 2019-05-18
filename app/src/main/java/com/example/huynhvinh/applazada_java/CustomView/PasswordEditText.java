package com.example.huynhvinh.applazada_java.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.huynhvinh.applazada_java.R;

@SuppressLint("AppCompatCustomView")
public class PasswordEditText extends EditText {

    Drawable eye,eyeStrike;
    Boolean visibile = false;
    Boolean useTrike = false;
    Boolean useValidate = false;
    Drawable drawable;
    int ALPHA = (int) (255 * .70f); // set độ mờ sáng của icon đục 55%

    public PasswordEditText(Context context) {
        super(context);
        KhoiTao(null);
    }
    // AttributeSet là lúc người dụng gọi nó sẽ setAttributeSet
    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        KhoiTao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        KhoiTao(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        KhoiTao(attrs);
    }

    private  void KhoiTao(AttributeSet attrs)
    {
        if(attrs!=null) {

            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText, 0, 0);  // trỏ tới phương thức tự tạo ở bên stye
            this.useTrike = array.getBoolean(R.styleable.PasswordEditText_useStrike, false); // Get thuộc tính Boolean useStrike tự tạo ở bên style để bắt sự kiện click vào EditText(trạng thái ở file .XML)
            this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate,false);
        }


        eye = ContextCompat.getDrawable(getContext(),R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(),R.drawable.ic_visibility_off_black_24dp).mutate();

        if(this.useValidate){ // Nếu người dùng focus vào InputText
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){ // nếu ta không Forcus vào Editext
                        TextInputLayout textInputLayout = (TextInputLayout) v.getParentForAccessibility();
                        String chuoi = getText().toString();
                        if(chuoi.equals("")|| chuoi == null)
                        {
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Mục này không được để trống!");
                        }else {
                            textInputLayout.setErrorEnabled(false);
                        }
                    }
                }
            });
        }

        CaiDat();
    }
    // Set trạng thái kiêu dữ liệu show ra của EditText là InputStyle : Text or Password
    private void CaiDat(){
        setInputType(InputType.TYPE_CLASS_TEXT | (visibile?InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables(); // get các loại drawable của EditText như: DrawableLeft, DrawableRight
        drawable = useTrike && !visibile? eyeStrike : eye ; // dựa vào trạng thái của userTrike và visiable để Show ra con mắt
        setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]); // setDrawble cho Edit text nếu ko set thì get mảng mặc định
    }
    // Bắt event click
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //    Sự kiện click vào màn hình                   so sánh tọa đọ click bằng cách lấy tọa độ bên phải trừ độ dài icoc_eye
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width()))
        {
            visibile = !visibile;
            CaiDat();
            invalidate(); // phương thức kiểm tra lại màn hình
        }
        return super.onTouchEvent(event);
    }
}