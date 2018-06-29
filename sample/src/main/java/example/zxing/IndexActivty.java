package example.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by desaco on 2018/6/29.
 */

public class IndexActivty extends Activity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_index);
        mContext = this;

        initView();
        initData();
    }

    private void initView() {
        Button generateQr = (Button) findViewById(R.id.generate_qr);
        generateQr.setOnClickListener(this);

        Button scanQr = (Button) findViewById(R.id.scan_qr);
        scanQr.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generate_qr: //生成
                jump(GenerateQRActivity.class);
                break;
            case R.id.scan_qr://扫描
                jump(MainActivity.class);
                break;
            default:
                break;
        }
    }

    private void jump(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(mContext, clazz);
        startActivity(intent);
    }
}
