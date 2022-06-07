package com.example.ql_hoaqua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText edSearch;
    private Button btnSearch;

    private Database database;
    private List<HoaQua> listHQ;
    private HQAdapter adapter;
    Bundle bundle = new Bundle();

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        anhxa();

        database = new Database(this);
        listHQ = database.getAllHQ();
        setAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HoaQua hoaQua = listHQ.get(i);

                    id = hoaQua.getId();

                    bundle.putInt("id",id);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    HoaQua hoaQua = database.findById(Integer.parseInt(edSearch.getText().toString()));
                    if (hoaQua!= null){
                        hoaQua.setId(Integer.parseInt(edSearch.getText().toString()));
                        listHQ.clear();
                        listHQ.add(hoaQua);
                        if (adapter != null){
                            setAdapter();
                        }
                    }
                }catch (Throwable e){
                    Toast.makeText(MainActivity.this,"Không có kết quả!",Toast.LENGTH_LONG).show();
                }
            }
        });

        registerForContextMenu(listView);
    }

    private void anhxa() {
        listView = findViewById(R.id.listview);
        edSearch = findViewById(R.id.edSearch);
        btnSearch = findViewById(R.id.btnSearch);
    }

    private void setAdapter() {
        if (adapter == null){
            adapter = new HQAdapter(this,R.layout.list_view,listHQ);
            listView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuAdd:{
                Intent intent = new Intent(MainActivity.this,InsertData.class);
                startActivity(intent);
                break;
            }
            case R.id.mnuExit:{
                finish();

                break;
            }
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v == listView){
            getMenuInflater().inflate(R.menu.menu_context,menu);
        }
    }

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_Del:{
                HoaQua hoaQua = new HoaQua();
                hoaQua.setId(id);
                int kq = database.delHQ(hoaQua.getId());
                if (kq>0){
                    Toast.makeText(MainActivity.this,"Xóa hoa quả thành công",Toast.LENGTH_LONG).show();
                    listHQ.clear();
                    listHQ.addAll(database.getAllHQ());
                    if (adapter != null){
                        setAdapter();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Xóa hoa quả không thành công",Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.mnu_Edit:{
                Intent intent = new Intent(MainActivity.this,EditData.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            default:break;
        }
        return super.onContextItemSelected(item);
    }

}