package cn.scut.dongxia.opencvdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    private ImageView image;
    private Button button;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, new LoaderCallbackInterface() {
            @Override
            public void onManagerConnected(int status) {
                Log.d(TAG, "onManagerConnected: ");

            }

            @Override
            public void onPackageInstall(int operation, InstallCallbackInterface callback) {
                Log.d(TAG, "onPackageInstall: ");
            }
        });

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
        Mat mat = new Mat();

        Utils.bitmapToMat(bitmap,mat);
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2GRAY);
        final Bitmap result = Bitmap.createBitmap(bitmap);
        Utils.matToBitmap(mat,result);
        image.setImageBitmap(bitmap);
    }
}
