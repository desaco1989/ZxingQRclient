package example.zxing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by desaco on 2018/6/29.
 */

public class GenerateQRActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

        initView();
        initData();
    }

    private EditText qrEt;
    private ImageView qrIv;

    private void initView() {
        qrEt = (EditText) findViewById(R.id.qr_content);

        Button generateQr = (Button) findViewById(R.id.generate_qr_bt);
        generateQr.setOnClickListener(this);
        //qr_img
        qrIv = (ImageView) findViewById(R.id.qr_img);


    }

    private void initData() {
        String content = qrEt.getText().toString().trim();
        if(content == null || "".equals(content)){
            Toast.makeText(GenerateQRActivity.this,"请输入需要生成二维码的内容！",Toast.LENGTH_LONG).show();
        }
        Bitmap qrBitmap = generateBitmap(content, 400, 400);
        qrIv.setImageBitmap(qrBitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generate_qr_bt:
                initData();
                break;
        }
    }

    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


}
