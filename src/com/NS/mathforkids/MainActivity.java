package com.NS.mathforkids;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	List<Question> quesList;
	int qid;
	Question currentQ;
	private TextView questionView;
	private RadioButton a;
	private RadioButton b;
	private RadioButton c;
	private RadioButton d;
	private RadioGroup answerGroup;
	private int score;
	private int wrongAnswers;
	private Button next;
	private Button submit;
	private RadioButton r;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		quesList = readTextFile(this, R.raw.quans);
		
		qid=0;
		wrongAnswers=0;
		score=0;						
		questionView=(TextView)findViewById(R.id.textView_questionView);
		a=(RadioButton)findViewById(R.id.RadioButton_A);
		b=(RadioButton)findViewById(R.id.RadioButton_B);
		c=(RadioButton)findViewById(R.id.RadioButton_C);
		d=(RadioButton)findViewById(R.id.RadioButton_D);	
		answerGroup=(RadioGroup)findViewById(R.id.radioGroup_choices);
		next=(Button)findViewById(R.id.button_next);
		submit=(Button)findViewById(R.id.button_submit);
		showQuestion();
	}
	public void onClick(View view){
		if((a.isChecked()||b.isChecked()||c.isChecked()||d.isChecked())&&qid<quesList.size()){
			r=(RadioButton)findViewById(answerGroup.getCheckedRadioButtonId());
			checkAnswer();
		}

		if(view.getId()==next.getId()&&(a.isChecked()||b.isChecked()||c.isChecked()||d.isChecked())&&qid<quesList.size()){					

			showQuestion();			
		}
		answerGroup.clearCheck();
	}
	private void showQuestion() {
		currentQ=quesList.get(qid);
		questionView.setText(currentQ.getQUESTION());
		a.setText(currentQ.getOPTA());
		b.setText(currentQ.getOPTB());
		c.setText(currentQ.getOPTC());
		d.setText(currentQ.getOPTD());

		

	}
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	           .setMessage("Are you sure you want to exit?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                    MainActivity.this.finish();
	               }
	           })
	           .setNegativeButton("No", null)
	           .show();
	}


	private void checkAnswer(){
		final Toast toast = new Toast(this);
		ImageView view = new ImageView(this); 
		String answer=(String)r.getText().toString();		
		if(currentQ.getANSWER().equals(answer)){

			Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.tick);
			Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 200, 200, true);			
			MediaPlayer mp = MediaPlayer.create(getBaseContext(),
					R.raw.correct);
					mp.start();
					view.setImageBitmap(bMapScaled);
			

			score+=10;
		}else{			
			Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.wrong);
			Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 200, 200, true);			
			MediaPlayer mp = MediaPlayer.create(getBaseContext(),
					R.raw.wrong);
					mp.start();
					view.setImageBitmap(bMapScaled);
			wrongAnswers++;

		}
		qid++;
		toast.setView(view);
		Handler handler = new Handler();
		toast.show();
        handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               toast.cancel(); 
           }
    }, 500);
		
	}
	public void sendMessage(View view) 
	{	
		if(view.getId()==submit.getId()){
			String s="You've given "+wrongAnswers+" wrong answers.\n" +"Your score is : "+score;
			Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
			intent.putExtra("new_activity_info", s);
			startActivity(intent);
			//onResume();

		}
	}
	 private  List<Question> readTextFile(Context ctx, int resId)
	    {
	        InputStream inputStream = ctx.getResources().openRawResource(resId);
ArrayList<String> buf=new ArrayList<String>();
ArrayList<Question> q=new ArrayList<Question>();
	        InputStreamReader inputreader = new InputStreamReader(inputStream);
	        BufferedReader bufferedreader = new BufferedReader(inputreader);
	        String line;
	        
	        try 
	        {
	            while (( line = bufferedreader.readLine()) != null) 
	            {
	                
					buf.add(String.valueOf(line));
	            }
	        } 
	        catch (IOException e) 
	        {
	            return null;
	        }
	        
			for(int i=0;i<=54;i+=6){
	    		Question question=new Question(buf.get(i), buf.get(i+1), buf.get(i+2), 
	    					buf.get(i+3), buf.get(i+4), buf.get(i+5));
	    			q.add(question);
	    		}
	        return q;
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
