package cn.scut.dongxia.opencvdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    private ImageView image;
    private Button button;
    private Bitmap bitmap;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenCVLoader.initDebug();

        initView();
    }

    private void initView(){
        image = (ImageView)findViewById(R.id.image);
        button = (Button)findViewById(R.id.button);

        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lena);
        image.setImageBitmap(bitmap);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pixels = new int[w*h];
        bitmap.getPixels(pixels,0,w,0,0,w,h);

        int[] resultInt = color2gray(pixels,w,h);

        Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        //(@ColorInt int[] pixels, int offset, int stride,int x, int y, int width, int height)
        resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
        image.setImageBitmap(resultImg);
    }

    public native int[] color2gray(int[] pixels, int w, int h);
}
