package id.co.imastudio.lab3list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by idn on 1/23/2018.
 */

public class CustomAdapter extends ArrayAdapter {
    private String[] namaNotif;
    private int[] gambarNotif;
    private Context context;

    public CustomAdapter(String[] namaNotif, int[] gambarNotif, Context context) {
        super(context, R.layout.list_item, namaNotif);
        this.namaNotif = namaNotif;
        this.gambarNotif = gambarNotif;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //sambungkan layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        //inisialisasi komponen yg di dalam list
        TextView textNama = itemView.findViewById(R.id.text_nama);
        ImageView imageGambar = itemView.findViewById(R.id.image_gambar);

        //set data
        textNama.setText(namaNotif[position]);
        imageGambar.setImageResource(gambarNotif[position]);

        return itemView;
    }
}
