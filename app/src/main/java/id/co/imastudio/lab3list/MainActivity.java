package id.co.imastudio.lab3list;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String[] namaNotification = {
            "Default Notification",
            "Action Notification",
            "Big Picture Notification"
    };

    int[] gambarNotification = {
            R.drawable.movie,
            R.drawable.profile2,
            R.drawable.unnamed,
    };

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.listView);

//        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, namaNotification);
        CustomAdapter adapter = new CustomAdapter(namaNotification, gambarNotification, MainActivity.this);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Kamu memilih "+ namaNotification[i], Toast.LENGTH_SHORT).show();
                Intent pindah = new Intent(MainActivity.this, DetailActivity.class);
                //kirim data
//                Bundle bungkus = new Bundle();
//                bungkus.putString("DATA_NAMA", namaNotification[i]);
//                bungkus.putInt("DATA_GAMBAR", gambarNotification[i]);
//                pindah.putExtras(bungkus);

                pindah.putExtra("DATA_NAMA", namaNotification[i]);
                pindah.putExtra("DATA_GAMBAR", gambarNotification[i]);
                startActivityForResult(pindah, 006);
            }
        });

        registerForContextMenu(list);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        AdapterView.AdapterContextMenuInfo cmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(1, cmi.position, 0, "Munculkan Notif");
        menu.add(2, cmi.position, 0, "Hapus Notif");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getGroupId()){
            case 1 :
                //munculin notif
                if (item.getItemId() == 0){
                    Notification.Builder notif = new Notification.Builder(this)
                            .setContentTitle("Default Notification")
                            .setContentText("Ini contoh notif")
                            .setPriority(Notification.PRIORITY_MIN)
                            .setSmallIcon(android.R.drawable.ic_dialog_alert);
                    manager.notify(1, notif.build());
                } else if (item.getItemId() == 1){
                    Intent bukakweb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.androidatc.com"));
                    PendingIntent webpendingintent = PendingIntent.getActivity(this, 1, bukakweb, PendingIntent.FLAG_ONE_SHOT);

                    Notification.Builder notif = new Notification.Builder(this)
                            .setContentTitle("Default Notification")
                            .setContentText("Ini contoh notif")
                            .setPriority(Notification.PRIORITY_DEFAULT)
                            .addAction(android.R.drawable.ic_dialog_info, "Lihat Web", webpendingintent)
                            .setSmallIcon(android.R.drawable.ic_dialog_alert);
                    manager.notify(2, notif.build());
                } else if (item.getItemId() == 2){

                    Bitmap gambar = BitmapFactory.decodeResource(getResources(), R.drawable.profile2);

                Notification.Builder notif = new Notification.Builder(this)
                        .setContentTitle("Default Notification")
                        .setContentText("Ini contoh notif")
                        .setPriority(Notification.PRIORITY_MAX)
                        .setStyle(new Notification.BigPictureStyle()
                                .bigPicture(gambar)
                                .setBigContentTitle("Gambear gede"))
                        .setSmallIcon(android.R.drawable.ic_dialog_alert);
                manager.notify(3, notif.build());
            }


//                Toast.makeText(this, "Tes", Toast.LENGTH_SHORT).show();
                break;
            case 2 :
                //hapus notif
                if (item.getItemId() == 0){
                    manager.cancel(1);
                } else if (item.getItemId() == 1){
                    manager.cancel(2);
                } else if (item.getItemId() == 2){
                    manager.cancel(3);
                }

                Toast.makeText(this, "Hapus", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 006 && resultCode == 8){
            String dataBalikan = data.getStringExtra("DATA_BALIKAN");
            Toast.makeText(this, ""+dataBalikan, Toast.LENGTH_SHORT).show();
        }
    }
}
