package cl.rodrigoayala.ejemplo.ejemplos;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import cl.rodrigoayala.ejemplo.R;

public class ListViewActivity extends AppCompatActivity {

    private static final int MENU_ITEM_ITEM1 = 0;
    ListView listViewDemo;
    LocalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listViewDemo = (ListView) findViewById(R.id.list_view_demo);

        adapter = new LocalAdapter(ListViewActivity.this, android.R.layout.simple_list_item_1);
        adapter.add("Hola1");
        adapter.add("Hola2");
        adapter.add("Hola3");
        listViewDemo.setAdapter(adapter);

        listViewDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(ListViewActivity.this)
                        .setTitle("Item seleccionado")
                        .setMessage("Se seleccionó: '"+adapter.getItem(position)+"' en posición "+ position)
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, "Agregar");
        item.setIcon(android.R.drawable.ic_input_add);
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_ITEM1:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Ingresa un texto para que sea agregado a la lista");


                final EditText input = new EditText(this);

                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        adapter.add(text);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                return true;

            default:
                return false;
        }
    }

        class LocalAdapter extends ArrayAdapter<String> {

        public LocalAdapter(Context context, int resource) {
            super(context, resource);
        }
    }
}
