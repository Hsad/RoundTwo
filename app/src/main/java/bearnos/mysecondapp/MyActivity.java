package bearnos.mysecondapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.bearnos.mysecondapp.MESSAGE";
    private int textViewCount;
    private List<TextView> textList = new ArrayList<TextView>();
    private ArrayList<String> strList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        textViewCount=0;
    }

    @Override
    public void onSaveInstanceState(Bundle save){
        super.onSaveInstanceState(save);
        //need to save the stings that are held in the layoutview

        //need some sort of a list or something to store the results.
        //also need to decide if I want it to be a permanate list
        //or just have it stick around for the single running
        //I'm thinkning just for the running so I get used to the system

        //but I also want to receive the info from the other window, letting me get more data
        //from that second screen...

        //lets just do this window first.
        /*int cnt = 0;
        for (TextView tv : textList){
            save.putInt("TVID_"+cnt, tv.getId());
            save.putString("TVStr_"+cnt, tv.toString());
            cnt++;
        }
        save.putInt("textviewcount", textViewCount);*/
        save.putStringArrayList("strArr", strList);

    }

    @Override
    public void onRestoreInstanceState(Bundle save){
        super.onRestoreInstanceState(save);
        System.out.print("Printing size of textList during restore");
        System.out.print(textList.size());
        //need to restore the data here
        /*textViewCount = save.getInt("textviewcount");
        for (int x = 0; x < textViewCount;){
            //TextView tv = textList.get(x);
            TextView tv = new TextView(this);

            int tvID = save.getInt("TVID_"+x);
            tv.setId(tvID);
            tv.setText(save.getString("TVStr_"+x));
            x++;
            textList.add(tv);
        }*/
        strList = save.getStringArrayList("strArr");
        repopulateTextViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //called when user presses send
    public void sendMessage(View view){
        //do stuff
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);
        addEntry(message);
    }

    public void repopulateTextViews(){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.scrollMe);
        for (String str : strList){
            //TextView textView = new TextView(this);
            //tv.setTextSize(30);
            //textView.setText(str);
            //textView.setId(textViewCount);
            //textView.setFreezesText(true);
            //linearLayout.addView(tv);
            makeTextEntry(str);
        }
    }

    public void makeTextEntry(String str){
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.scrollMe);
        TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setText(str);
        //textView.setId(textViewCount);
        textView.setFreezesText(true);
        linearLayout.addView(textView);
    }

    public void addEntry(String str){
        //want to take in what the user wrote, then put it into a textview below the entry spot

        //need to get a refrence to the vertical layout
        /*LinearLayout linearLayout = (LinearLayout)findViewById(R.id.scrollMe);
        //need to instantiate a textview, ideally a predefined one
        TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setText(str);
        textView.setId(textViewCount);
        textView.setFreezesText(true);
        linearLayout.addView(textView);*/

        makeTextEntry(str);
        //textList.add(textView);
        //textViewCount++;

        strList.add(str);


        //need to store the text view grouping, or something else so that it is retained between
        //uses and between screen orientation flips
    }
}
