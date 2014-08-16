package com.NS.mathforkids;

import java.util.ArrayList;
import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private static final int MAX_NUM=30;
	private static final int MIN_NUM=2;
	private Random rand=new Random();
	private int currentQuestion;
	private ArrayList<String> Questions;
	private ArrayList<String> Answers;
	private TextView questionView;
	private RadioButton a;
	private RadioButton b;
	private RadioButton c;
	private RadioButton d;
	private RadioGroup answerGroup;
	private ArrayList<Integer> correctAnswers;
	private int score;
	private int wrongAnswers;
	private TextView answerView;
	private Button back;
	private Button next;
	private Button submit;
	private int correctAns;
	private RadioButton r;
	int t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Questions=new ArrayList<String>();
		Answers=new ArrayList<String>();
		correctAnswers=new ArrayList<Integer>();
		currentQuestion=0;
		wrongAnswers=0;
		score=0;
		t=1;
		questionView=(TextView)findViewById(R.id.textView_questionView);
		a=(RadioButton)findViewById(R.id.RadioButton_A);
		b=(RadioButton)findViewById(R.id.RadioButton_B);
		c=(RadioButton)findViewById(R.id.RadioButton_C);
		d=(RadioButton)findViewById(R.id.RadioButton_D);	
		answerGroup=(RadioGroup)findViewById(R.id.radioGroup_choices);
		answerView=(TextView)findViewById(R.id.textView_answerView);
		back=(Button)findViewById(R.id.button_back);
		next=(Button)findViewById(R.id.button_next);
	    submit=(Button)findViewById(R.id.button_submit);
	    showQuestion();
	    
	}
	public void onClick(View view){
		if(a.isChecked()||b.isChecked()||c.isChecked()||d.isChecked()){
		r=(RadioButton)findViewById(answerGroup.getCheckedRadioButtonId());
		checkAnswer();
		}
		
		if(view.getId()==next.getId()){					
						
			showQuestion();			
		}else if(view.getId()==back.getId()){
			t++;
			questionView.setText(Questions.get(currentQuestion-t));
			a.setText(String.valueOf(correctAnswers.get(currentQuestion-t)));
			b.setText(String.valueOf(correctAnswers.get(currentQuestion-t)-1));
			c.setText(String.valueOf(correctAnswers.get(currentQuestion-t)+2));
			d.setText(String.valueOf(correctAnswers.get(currentQuestion-t)-3));
		}else if(view.getId()==submit.getId()){
			answerView.setText("You give "+wrongAnswers+" wrong answers.\n" +"Your score is : "+score);
		}
		answerGroup.clearCheck();
	}
	private void showQuestion() {
		String q="";
		int n=rand.nextInt(MAX_NUM-MIN_NUM+1)+MIN_NUM;
		int m=rand.nextInt(MAX_NUM-MIN_NUM+1)+MIN_NUM;
		if( generateOp()==1) {correctAns=n+m; q=m+" + "+n;}
		else if( generateOp()==2) {correctAns=n-m; q=n+" - "+m;}
		else if(generateOp()==3){ correctAns=n*m; q=m+" * "+n;}
		else{
			if(n%m!=0){
				n=findSmallestMultiple(n,m);
			}
			correctAns=n/m; q=n+" / "+m;
		}
		correctAnswers.add(correctAns);
		Questions.add(q);
		Answers.add(""+correctAns);
		questionView.setText(Questions.get(currentQuestion));
		a.setText(String.valueOf(correctAns));
		b.setText(String.valueOf(correctAns-1));
		c.setText(String.valueOf(correctAns+2));
		d.setText(String.valueOf(correctAns-3));

		currentQuestion++;
		
	}


	private int findSmallestMultiple(int n, int m) {
		while(n%m!=0) n+=1;
		return n;
	}

	private  int generateOp(){
		return (int)rand.nextInt(4)+1;
	}
	private boolean isCorrect(String answer) {

		return answer.equalsIgnoreCase(Answers.get(currentQuestion-1));
	}
	private void checkAnswer(){
		
		String answer=(String)r.getText().toString();
		if(isCorrect(answer)){
			answerView.setText("Yeah!Keep Going!");
			score+=10;
		}else{
			answerView.setText("You gotta work harder! The answer is: "+Answers.get(currentQuestion-1));
			wrongAnswers++;
			
		}

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
