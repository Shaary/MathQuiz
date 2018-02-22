package com.example.mathquiz;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.HashSet;
        import java.util.Random;

public class MathQuizActivity extends Activity implements View.OnClickListener {
    private Questions questions = new Questions();
    private ColorWheel colorWheel = new ColorWheel();
    private TextView questionTextView;
    private LinearLayout linearLayout;
    private Button submitButton;
    private RadioButton Button1;
    private RadioButton Button2;
    private RadioButton Button3;
    private RadioGroup radioGroup;
    private boolean correctAnswer;
    private boolean haveAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        submitButton = findViewById(R.id.submitButton);
        Button1 = findViewById(R.id.radioButton1);
        Button2 = findViewById(R.id.radioButton2);
        Button3 = findViewById(R.id.radioButton3);
        radioGroup = findViewById(R.id.radioGroup);
        linearLayout = findViewById(R.id.linearLayout);

        setLayout();

        submitButton.setOnClickListener(this);
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);

    }

    public void setLayout() {
        radioGroup.clearCheck();
        String question = questions.getQuestion();
        questionTextView.setText(question);

        HashSet<Integer> answers = questions.getAnswers();
        int i = 0;
        for (int answer : answers) {
            int value = i;
            switch (value) {
                case 0:
                    Button1.setText(Integer.toString(answer));
                    break;
                case 1:
                    Button2.setText(Integer.toString(answer));
                    break;
                case 2:
                    Button3.setText(Integer.toString(answer));
                    break;
            }
            i++;
        }
    }

    private boolean isCorrectAnswer(int buttonText) {
        correctAnswer = false;
        int rightAnswer = questions.getCorrectAnswer();
        if (rightAnswer == buttonText) {
            correctAnswer = true;
        }
        return correctAnswer;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitButton:
                if (haveAnswer == true) {
                    if (correctAnswer == true) {
                        Toast.makeText(this, "Correct answer! :)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Incorrect answer! :(", Toast.LENGTH_SHORT).show();
                    }
                    setLayout();
                    int color = colorWheel.getColor();
                    submitButton.setTextColor(color);
                    linearLayout.setBackgroundColor(color);
                    haveAnswer = false;
                    break;
                }
                Toast.makeText(this, "Choose one answer!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButton1:
                int buttonText = Integer.parseInt((String) Button1.getText());
                isCorrectAnswer(buttonText);
                haveAnswer = true;
                break;
            case  R.id.radioButton2:
                buttonText = Integer.parseInt((String) Button2.getText());
                isCorrectAnswer(buttonText);
                haveAnswer = true;
                break;
            case R.id.radioButton3:
                buttonText = Integer.parseInt((String) Button3.getText());
                isCorrectAnswer(buttonText);
                haveAnswer = true;
                break;
        }



    }
}

