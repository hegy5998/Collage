package com.fcu.photocollage.collage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;

import com.fcu.photocollage.R;

/**
 * Created by Lighting on 2015/8/31.
 */
public class AddText extends Activity {
    private ImageView textShow;
    private Context Context=AddText.this;
    private int currentColor;
    private int textShowW,textShowH;
    private Canvas textCanvas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_add);

        //全螢幕模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public void onWindowFocusChanged(boolean focus) {
        super.onWindowFocusChanged(focus);
        // 在這裡getWidth()或getHeight()
        textShow = (ImageView) findViewById(R.id.textShow);
        textShowW = textShow.getMeasuredWidth();
        textShowH = textShow.getMeasuredHeight();
        Bitmap aa = Bitmap.createBitmap(textShowW, textShowH, Bitmap.Config.ARGB_8888);
        textCanvas = new Canvas(aa);
        Paint aaa = new Paint();
        Typeface aaaa = Typeface.createFromAsset(getAssets(), "fonts/AdobeArabic-BoldItalic.otf");
        aaa.setTypeface(aaaa);
        aaa.setColor(getResources().getColor(R.color.md_blue_500));
        aaa.setStrokeWidth(30);
        aaa.setTextSize(200);
        aaa.setFakeBoldText(true); //true为粗体，false为非粗体
        aaa.setTextSkewX(-0.5f); //float类型参数，负数表示右斜，整数左斜
        //消除锯齿
        aaa.setFlags(Paint.ANTI_ALIAS_FLAG);
        textCanvas.drawText("豆漿加紅茶",0, 400, aaa);//（顯示文字,顯示位置-X,顯示位置-Y, paint）
        Log.d("w", String.valueOf(textShowW));
        Log.d("h", String.valueOf(textShowH));
        textShow.setImageBitmap(aa);
    }


    /**
     * 設置畫筆的颜色
     */
    public void setColor(int color) {
//		currentColor = Color.parseColor(color);
        currentColor = color;
    }


    //region 從linearlayout中偵測我按的是哪一個顏色
    public void colorchoose(View view) {
        // TODO 自動產生的方法 Stub
        switch (view.getId()) {
            case R.id.colorRed:
                setColor(getResources().getColor(R.color.md_red_500));
                break;
            case R.id.colorGreen:
                setColor(getResources().getColor(R.color.md_green_500));
                break;
            case R.id.colorBlue:
                setColor(getResources().getColor(R.color.md_blue_500));
                break;
            case R.id.colorPurple:
                setColor(getResources().getColor(R.color.md_purple_500));
                break;
            case R.id.colorYellow:
                setColor(getResources().getColor(R.color.md_yellow_500));
                break;
            case R.id.colorOrange:
                setColor(getResources().getColor(R.color.md_orange_500));
                break;
            case R.id.colorBrown:
                setColor(getResources().getColor(R.color.md_brown_500));
                break;
            case R.id.colorGray:
                setColor(getResources().getColor(R.color.md_grey_500));
                break;
            case R.id.colorBlack:
                setColor(getResources().getColor(R.color.md_black_1000));
                break;
            case R.id.palette:
                //調色盤
                ColorPickerDialog dialog = new ColorPickerDialog(Context, currentColor, "Choose Color",
                        new ColorPickerDialog.OnColorChangedListener() {
                            public void colorChanged(int color2) {
                                //set your color variable
                                setColor(color2);

                                    //選完顏色同時也要把畫筆大小的顏色換成所選的
                                    Bitmap textBitmap = Bitmap.createBitmap(textShowW,textShowH, Bitmap.Config.ARGB_8888);
                                    Canvas textCanvas = new Canvas(textBitmap);

                                    Paint cleancanvas = new Paint();
                                    cleancanvas.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                                    textCanvas.drawPaint(cleancanvas);
                                    cleancanvas.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

                                    Paint textPaint = new Paint();
                                    textPaint.setAntiAlias(true);
                                    textPaint.setColor(currentColor);
                                    textPaint.setTextSize(200);
                                    textPaint.setFakeBoldText(true); //true为粗体，false为非粗体
                                    textPaint.setTextSkewX(-0.5f); //float类型参数，负数表示右斜，整数左斜
                                    Typeface aaaa = Typeface.createFromAsset(getAssets(), "fonts/AdobeArabic-BoldItalic.otf");
                                    textPaint.setTypeface(aaaa);
                                    //textPaint.setStyle(Paint.Style.FILL);
                                    textPaint.setStrokeWidth(30);

                                    textCanvas.drawText("豆漿加紅茶", 0, 400, textPaint);
                                    textShow.setImageBitmap(textBitmap);


                            }
                        });
                dialog.show();
                break;
            default:
                break;
        }
        //選完顏色同時也要把畫筆大小的顏色換成所選的
        Bitmap textBitmap = Bitmap.createBitmap(textShowW,textShowH, Bitmap.Config.ARGB_8888);
        Canvas textCanvas = new Canvas(textBitmap);

        Paint cleancanvas = new Paint();
        cleancanvas.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        textCanvas.drawPaint(cleancanvas);
        cleancanvas.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(currentColor);
        textPaint.setTextSize(200);
        textPaint.setFakeBoldText(true); //true为粗体，false为非粗体
        textPaint.setTextSkewX(-0.5f); //float类型参数，负数表示右斜，整数左斜
        Typeface aaaa = Typeface.createFromAsset(getAssets(), "fonts/AdobeArabic-BoldItalic.otf");
        textPaint.setTypeface(aaaa);
        //textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(30);

        textCanvas.drawText("豆漿加紅茶", 0, 400, textPaint);
        textShow.setImageBitmap(textBitmap);

    }
}
