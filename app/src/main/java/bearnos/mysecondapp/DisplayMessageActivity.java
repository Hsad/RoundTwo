package bearnos.mysecondapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            //What I think these are doing
        //passing up the given parameters to the superclass, just incase I guess
        super.onCreate(savedInstanceState);
        //set activity_display_message.xml as the source/root of the contentview
        setContentView(R.layout.activity_display_message);
        //get the tool bar to be this predefined thing
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //set what ever the action bar is to be this tool bar
        setSupportActionBar(toolbar);


        //create the little mail icon button, and have it give a prompt when pressed
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //At minimum this must be after the set content view line, otherwise findViewbyId will fail
        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.content);
        layout.addView(textView);
    }

}
