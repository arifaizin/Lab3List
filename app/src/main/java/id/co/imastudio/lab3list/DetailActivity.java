package id.co.imastudio.lab3list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class DetailActivity extends AppCompatActivity {

    //logt
    private static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent nilaibalikan = new Intent();
                nilaibalikan.putExtra("DATA_BALIKAN", "Selamat Anda berhasil belajar ActivityForResult");
                setResult(8, nilaibalikan);
                finish();
            }
        });

//        String datanama = getIntent().getExtras().getString("DATA_NAMA");
//        int datagambar = getIntent().getExtras().getInt("DATA_GAMBAR");

        String dataNama = getIntent().getStringExtra("DATA_NAMA");
        int dataGambar = getIntent().getIntExtra("DATA_GAMBAR", 0);

        //logd
        Log.d(TAG, "onCreate: "+ dataNama+dataGambar);

        getSupportActionBar().setTitle(dataNama);
    }
}
