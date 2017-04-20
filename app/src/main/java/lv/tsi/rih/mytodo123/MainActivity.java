package lv.tsi.rih.mytodo123;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.preference.PreferenceActivity;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import static lv.tsi.rih.mytodo123.R.xml.settings;

public class MainActivity extends Activity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    Button btnActTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ADD HERE
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();


        Button prefBtn = (Button) findViewById(R.id.btnSettings);

            View.OnClickListener myhandler1 = new View.OnClickListener() {


                    public void onClick(View v) {
                        setContentView(R.layout.activity_settings2);


//                Intent settingsActivity = new Intent(getBaseContext(),
//                        SettingsActivity.class);
//                startActivity(settingsActivity);
            }
        };
        prefBtn.setOnClickListener(myhandler1);


    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        writeItems();
                        return true;
                    }

                });
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//    public void  onClickSettings (View v) {
//        Intent settingsActivity = new Intent(getBaseContext(),
//                SettingsActivity.class);
//        startActivity(settingsActivity);
//
//    }











}

