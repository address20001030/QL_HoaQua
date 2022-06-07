package com.example.ql_hoaqua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class EditData extends AppCompatActivity {
    private Button btnUp, btnExitUp;
    private EditText txtname, txtloai, txtdvt, txtdongia,txtxx;

    int id = 0;

    private Database database;
    private List<HoaQua> thucPhams;
    private HQAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        database = new Database(this);
        anhxa();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        id = bundle.getInt("id",id);

        HoaQua hoaQua = database.findById(id);
        txtname.setText(hoaQua.getName());
        txtloai.setText(hoaQua.getLoai());
        txtdvt.setText(hoaQua.getDvt());
        txtdongia.setText(String.valueOf(hoaQua.getDongia()));
        txtxx.setText(hoaQua.getNsx());

        btnExitUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditData.this,MainActivity.class);
                startActivity(intent1);
            }
        });


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               HoaQua hoaQua1 = new HoaQua();
                hoaQua1.setId(id);
                hoaQua1.setName(txtname.getText().toString());
                hoaQua1.setLoai(txtloai.getText().toString());
                hoaQua1.setDvt(txtdvt.getText().toString());
                hoaQua1.setDongia(Integer.parseInt(txtdongia.getText().toString()));
                hoaQua1.setNsx(txtxx.getText().toString());

                if (hoaQua1 != null){
                    database.updateHQ(hoaQua1);
                    Intent intents = new Intent(EditData.this,MainActivity.class);
                    startActivity(intents);
                }
            }
        });
    }

    private void anhxa() {
        txtname = findViewById(R.id.ed_ten);
        txtloai = findViewById(R.id.ed_loai);
        txtdvt = findViewById(R.id.ed_dvt);
        txtdongia = findViewById(R.id.ed_dg);
        txtxx = findViewById(R.id.ed_nsx);
        btnExitUp = findViewById(R.id.btnExitED);
        btnUp = findViewById(R.id.btnEdit);
    }
}