package com.example.ql_hoaqua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertData extends AppCompatActivity {

    private EditText ma,ten,loai,dovvi,don_gia,noisx;
    private Button btnAdd,btnExitAD;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        database = new Database(this);
        anhxa();

        btnExitAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertData.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaQua hoaQua = createHQ();
                if (hoaQua != null){
                    database.addHQ(hoaQua);
                    Intent intent = new Intent(InsertData.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private HoaQua createHQ() {
        String tenhh = ten.getText().toString();
        String loaihh = loai.getText().toString();
        String donvitinh = dovvi.getText().toString();
        int dongia = Integer.parseInt(don_gia.getText().toString());
        String xxhh = noisx.getText().toString();
        HoaQua hoaQua = new HoaQua(tenhh,loaihh,donvitinh,dongia,xxhh);

        return hoaQua;
    }

    private void anhxa() {
        ten = findViewById(R.id.ed_ten);
        loai = findViewById(R.id.ed_loai);
        dovvi = findViewById(R.id.ed_dvt);
        don_gia = findViewById(R.id.ed_dg);
        noisx = findViewById(R.id.ed_nsx);
        btnAdd = findViewById(R.id.btnAdd);
        btnExitAD = findViewById(R.id.btnExitIS);
    }
}