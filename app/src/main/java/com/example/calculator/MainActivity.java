package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity{

    private EditText result;
    private String str_result;
    private String [] str_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout background = findViewById(R.id.background);
        AnimationDrawable animationDrawable = (AnimationDrawable)  background.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        str_result = "";
        str_array = new String[3];
        str_array[0] = "none";
        str_array[1] = "none";
        str_array[2] = "none";

        result = findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(getString(R.string.result).equals(result.getText().toString())){
                    result.setText("");
                }
            }
        });
    }

    // sets the result editText
    public void setResultText(String added_string)
    {
        String current_result = this.result.getText().toString();

        if(current_result.equals(this.str_result))
        {
            this.result.setText("");
            current_result = "";
        }


        String new_result = current_result + added_string;
        this.result.setText(new_result);
    }

    // clears the result and resets all variables
    public void clear(View view)
    {
        result.setText("");
        str_result = "";
        str_array[0] = "none";
        str_array[1] = "none";
        str_array[2] = "none";
    }

    // onClick functions for each button
    public void zero(View view)
    {
        String zero = getString(R.string.zero);
        this.setResultText(zero);

    }

    public void one(View view)
    {
        String one = getString(R.string.one);
        this.setResultText(one);
    }

    public void two(View view)
    {
        String two = getString(R.string.two);
        this.setResultText(two);
    }

    public void three(View view)
    {
        String three = getString(R.string.three);
        this.setResultText(three);
    }

    public void four(View view)
    {
        String four = getString(R.string.four);
        this.setResultText(four);
    }

    public void five(View view)
    {
        String five = getString(R.string.five);
        this.setResultText(five);
    }

    public void six(View view)
    {
        String six = getString(R.string.six);
        this.setResultText(six);
    }

    public void seven(View view)
    {
        String seven = getString(R.string.seven);
        this.setResultText(seven);
    }

    public void eight(View view)
    {
        String eight = getString(R.string.eight);
        this.setResultText(eight);
    }

    public void nine(View view)
    {
        String nine = getString(R.string.nine);
        this.setResultText(nine);
    }

    public void decimal(View view)
    {
        String decimal = getString(R.string.decimal);
        String str = result.getText().toString();
        if(!str.contains(decimal)){
            this.setResultText(decimal);
        }
    }

    // the onClick for the addition button
    // the input within the EditText is placed in an array
    // the first spot in the array represents the first number in the equation
    // the second spot represents the operator
    // the third spot represents the second number in the equation
    public void add(View view)
    {
        String add = getString(R.string.plus);

        switch (this.str_array[1]) {
            case "-":
            case "/":
            case "*":
                this.equal(view);
                break;
        }

        String number = this.result.getText().toString();
        if(this.checkValue(number)) {
            return;
        }


        if(this.str_array[0].equals("none")){
            this.str_array[0] = number;
            this.str_array[1] = add;
            this.result.setText("");
        }

        else{
            if(this.str_array[2].equals("none")){
                this.str_array[2] = number;
                this.str_array[0] = this.performAdd(this.str_array);
                this.str_array[1] = add;
                this.str_array[2] = "none";

            }
        }

    }

    // performs the addition within the array
    public String performAdd(String[] arr1){
        BigDecimal big1 = new BigDecimal(arr1[0]);
        BigDecimal big2 = new BigDecimal(arr1[2]);

        BigDecimal answer = big1.add(big2);


        this.str_array[0] = "none";
        this.str_array[1] = "none";
        this.str_array[2] = "none";

        this.result.setText("");
        String check = answer.toString();
        double check_whole = Double.parseDouble(check);

        if(check_whole % 1 == 0){
            long whole_num = (long) check_whole;
            this.setResultText("" + whole_num);
            this.str_result = "" + whole_num;
            return "" + whole_num;
        }

        this.setResultText(answer.toString());
        this.str_result = answer.toString();
        return answer.toString();

    }

    // onClick function for the subtract button
    public void subtract(View view)
    {
        String subtract = getString(R.string.minus);
        switch (this.str_array[1]) {
            case "+":
            case "/":
            case "*":
                this.equal(view);
                break;
        }

        String number = result.getText().toString();
        if(this.checkValue(number)){
            return;
        }

        if(this.str_array[0].equals("none")){
            this.str_array[0] = number;
            this.str_array[1] = subtract;
            this.result.setText("");
        }

        else{
            if(this.str_array[2].equals("none")){
                this.str_array[2] = number;
                this.str_array[0] = this.performSubtract(this.str_array);
                this.str_array[1] = subtract;
                this.str_array[2] = "none";

            }
        }
    }

    // performs the subtraction in the array
    public String performSubtract(String[] arr1){
        BigDecimal big1 = new BigDecimal(arr1[0]);
        BigDecimal big2 = new BigDecimal(arr1[2]);

        BigDecimal answer = big1.subtract(big2);


        this.str_array[0] = "none";
        this.str_array[1] = "none";
        this.str_array[2] = "none";

        this.result.setText("");
        String check = answer.toString();
        double check_whole = Double.parseDouble(check);

        if(check_whole % 1 == 0){
            long whole_num = (long) check_whole;
            this.setResultText("" + whole_num);
            this.str_result = "" + whole_num;
            return "" + whole_num;
        }

        this.setResultText(answer.toString());
        this.str_result = answer.toString();
        return answer.toString();
    }

    // onClick function for the multiply button
    public void multiply(View view)
    {
        String multiply = "*";
        switch (this.str_array[1]) {
            case "-":
            case "/":
            case "+":
                this.equal(view);
                break;
        }

        String number = result.getText().toString();
        if(this.checkValue(number)){
            return;
        }

        if(this.str_array[0].equals("none")){
            this.str_array[0] = number;
            this.str_array[1] = multiply;
            this.result.setText("");
        }

        else{
            if(this.str_array[2].equals("none")){
                this.str_array[2] = number;
                this.str_array[0] = this.performMultiply(this.str_array);
                this.str_array[1] = multiply;
                this.str_array[2] = "none";

            }
        }
    }

    // performs the multiplication in the array
    public String performMultiply(String[] arr1){
        BigDecimal big1 = new BigDecimal(arr1[0]);
        BigDecimal big2 = new BigDecimal(arr1[2]);

        BigDecimal answer = big1.multiply(big2);


        this.str_array[0] = "none";
        this.str_array[1] = "none";
        this.str_array[2] = "none";

        this.result.setText("");
        String check = answer.toString();
        double check_whole = Double.parseDouble(check);

        if(check_whole % 1 == 0){
            String check_for_e = "" + check_whole;
            if(!check_for_e.contains("E")){
                this.setResultText("" + (long) check_whole);
                this.str_result = "" + (long) check_whole;
                return "" + (long) check_whole;
            }

            this.setResultText("" + check_whole);
            this.str_result = "" + check_whole;
            return "" + check_whole;
        }

        this.setResultText(answer.toString());
        this.str_result = answer.toString();
        return answer.toString();
    }

    // onClick function for the divide button
    public void divide(View view)
    {
        String divide = "/";
        switch (this.str_array[1]) {
            case "+":
            case "*":
            case "-":
                this.equal(view);
                break;
        }

        String number = result.getText().toString();
        if(this.checkValue(number)){
            return;
        }

        if(this.str_array[0].equals("none")){
            this.str_array[0] = number;
            this.str_array[1] = divide;
            this.result.setText("");
        }

        else{
            if(this.str_array[2].equals("none")){
                this.str_array[2] = number;
                this.str_array[0] = this.performDivide(this.str_array);
                this.str_array[1] = divide;
                this.str_array[2] = "none";

            }
        }

    }

    // performs the division in the array
    public String performDivide(String[] arr1){
        BigDecimal big1 = new BigDecimal(arr1[0]);
        BigDecimal big2 = new BigDecimal(arr1[2]);

        if((big1.compareTo(BigDecimal.ZERO) == 0) || (big2.compareTo(BigDecimal.ZERO) == 0)){
            this.str_result = "0";
            this.str_array[0] = "none";
            this.str_array[1] = "none";
            this.str_array[2] = "none";
            this.result.setText("");
            this.setResultText("0");
            return "0";
        }

        BigDecimal answer = big1.divide(big2, 11, RoundingMode.CEILING);

        this.str_array[0] = "none";
        this.str_array[1] = "none";
        this.str_array[2] = "none";

        this.result.setText("");
        String check = answer.toString();
        double check_whole = Double.parseDouble(check);

        if(check_whole % 1 == 0){
            String check_for_e = "" + check_whole;
            if(!check_for_e.contains("E")){
                this.setResultText("" + (long) check_whole);
                this.str_result = "" + (long) check_whole;
                return "" + (long) check_whole;
            }

            this.setResultText("" + check_whole);
            this.str_result = "" + check_whole;
            return "" + check_whole;
        }

        this.setResultText(answer.toString());
        this.str_result = answer.toString();
        return answer.toString();
    }

    // onClick for the equal button
    // checks if there is an operation in the second array spot
    // if there is, it checks if the last array spot is occupied.
    // if there is no number, the number on the screen is used
    public void equal(View view)
    {
        if(this.str_array[1].charAt(0) == '+'){

            if(this.str_array[2].equals("none")){
                String current_text = result.getText().toString();

                if(current_text.isEmpty() || this.checkValue(current_text)){
                    return;
                }
                else{
                    this.str_array[2] = current_text;
                }
            }
            this.performAdd(this.str_array);
        }

        else if(this.str_array[1].charAt(0) == '-'){

            if(this.str_array[2].equals("none")){
                String current_text = result.getText().toString();

                if(current_text.isEmpty() || this.checkValue(current_text)){
                    return;
                }
                else{
                    this.str_array[2] = current_text;
                }
            }
            this.performSubtract(this.str_array);
        }

        else if(this.str_array[1].charAt(0) == '*'){

            if(this.str_array[2].equals("none")){
                String current_text = result.getText().toString();

                if(current_text.isEmpty() || this.checkValue(current_text)){
                    return;
                }
                else{
                    this.str_array[2] = current_text;
                }
            }
            this.performMultiply(this.str_array);
        }

        else if(this.str_array[1].charAt(0) == '/'){

            if(this.str_array[2].equals("none")){
                String current_text = result.getText().toString();

                if(current_text.isEmpty() || this.checkValue(current_text)){
                    return;
                }
                else{
                    this.str_array[2] = current_text;
                }
            }
            this.performDivide(this.str_array);
        }
    }

    // function to switch a number between being negative and positive using the +/- button
    public void negative(View view)
    {
        String negative = "-";
        String str = result.getText().toString();
        if(str.contains(negative)){
            String new_str = str.replace("-","");
            result.setText("");
            this.setResultText(new_str);
        }
        else {
            String new_str = "-" + str;
            result.setText("");
            this.setResultText(new_str);
        }
    }

    // back button onClick. only changes the EditText string if it is not empty
    // if a result is on screen from a previous calculation, the calculator will clear
    public void back(View view)
    {
        String current_str = result.getText().toString();
        if(current_str.length() != 0)
        {
            if(getString(R.string.result).equals(result.getText().toString())){
                result.setText("");
            }
            else{
                if(current_str.equals(this.str_result))
                {
                    this.clear(view);
                }
                else {
                    String str = this.removeLastCharacter(current_str);
                    result.setText("");
                    this.setResultText(str);
                }
            }
        }
    }

    // function to remove the last character of a string. used in the back button onClick
    public String removeLastCharacter(String str)
    {
        return str.substring(0, str.length() - 1);
    }

    // checks if an EditText entry is a valid number and not any other character used
    public boolean checkValue(String str)
    {
        if(str.isEmpty()){
                return true;
        }
        else if(str.length() == 1){
            return (str.charAt(0) == '-') || (str.charAt(0) == '.');
        }

        else if(str.length() == 2){
            return str.equals("-.");
        }

        return false;
    }
}