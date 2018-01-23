package com.example.arturbaboskin.painttest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView original;
    private ImageView grayscale;
    private ImageView invert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        original = findViewById(R.id.original);
        grayscale = findViewById(R.id.grayscale);
        invert = findViewById(R.id.invert);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sirius);

        original.setImageBitmap(bitmap);
        grayscale.setImageBitmap(toGrayscale(bitmap));
        invert.setImageBitmap(toInvert(bitmap));

    }

    private Bitmap toGrayscale(Bitmap original) {
        Bitmap result = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(),
                Bitmap.Config.ARGB_8888);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(original,0,0,paint);

        return result;
    }

    private Bitmap toInvert(Bitmap original){
        Bitmap result = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(),
                Bitmap.Config.ARGB_8888);

        ColorMatrix matrix = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });

        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(original,0,0,paint);

        return result;
    }
}
