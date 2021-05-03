package com.bsuir.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix,
            buttonSeven, buttonEight, buttonNine, buttonZero, buttonDecimal, buttonDoubleZero;

    private Button buttonClear, buttonEqual, buttonPercentage, buttonDivide, buttonMultiply,
            buttonMinus, buttonAdd, buttonBackspace;

    private Button buttonLeftParenthesis, buttonRightParenthesis, buttonOneDivided, buttonSquare,
            buttonCube, buttonDegree, buttonFactorial, buttonRoot, buttonPI, buttonExp,
            buttonLn, buttonLog, buttonSin, buttonCos, buttonTan;

    private Button buttonHistory;

    private int counter;

    public static List<String> history = new ArrayList();

    private TextView tvExpression, tvResult;

    private boolean isOperation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonZero = findViewById(R.id.btnZero);
        buttonOne = findViewById(R.id.btnOne);
        buttonTwo = findViewById(R.id.btnTwo);
        buttonThree = findViewById(R.id.btnThree);
        buttonFour = findViewById(R.id.btnFour);
        buttonFive = findViewById(R.id.btnFive);
        buttonSix = findViewById(R.id.btnSix);
        buttonSeven = findViewById(R.id.btnSeven);
        buttonEight = findViewById(R.id.btnEight);
        buttonNine = findViewById(R.id.btnNine);

        buttonDoubleZero = findViewById(R.id.btnDoubleZero);
        buttonDecimal = findViewById(R.id.btnDecimal);

        buttonClear = findViewById(R.id.btnClear);
        buttonEqual = findViewById(R.id.btnEqual);
        buttonPercentage = findViewById(R.id.btnPercentage);
        buttonDivide = findViewById(R.id.btnDivide);
        buttonMultiply = findViewById(R.id.btnMultiply);
        buttonMinus = findViewById(R.id.btnMinus);
        buttonAdd = findViewById(R.id.btnAdd);
        tvExpression = findViewById(R.id.tvExpression);
        tvResult = findViewById(R.id.tvResult);
        buttonBackspace = findViewById(R.id.btnBackspace);

        buttonHistory = findViewById(R.id.historyButton);

        buttonHistory.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(i);
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            buttonLeftParenthesis = findViewById(R.id.btnLeftParenthesis);
            buttonRightParenthesis = findViewById(R.id.btnRightParenthesis);
            buttonOneDivided = findViewById(R.id.btnOneDivided);
            buttonSquare = findViewById(R.id.btnSquare);
            buttonCube = findViewById(R.id.btnCube);
            buttonDegree = findViewById(R.id.btnDegree);
            buttonFactorial = findViewById(R.id.btnFactorial);
            buttonRoot = findViewById(R.id.btnRoot);
            buttonPI = findViewById(R.id.btnPi);
            buttonExp = findViewById(R.id.btnExp);
            buttonLn = findViewById(R.id.btnLn);
            buttonLog = findViewById(R.id.btnCtg);
            buttonSin = findViewById(R.id.btnSin);
            buttonCos = findViewById(R.id.btnCos);
            buttonTan = findViewById(R.id.btnTan);

            buttonLeftParenthesis.setOnClickListener(v -> {
                        if (tvExpression.getText().toString().endsWith("1")
                                || tvExpression.getText().toString().endsWith("2")
                                || tvExpression.getText().toString().endsWith("3")
                                || tvExpression.getText().toString().endsWith("4")
                                || tvExpression.getText().toString().endsWith("5")
                                || tvExpression.getText().toString().endsWith("6")
                                || tvExpression.getText().toString().endsWith("7")
                                || tvExpression.getText().toString().endsWith("8")
                                || tvExpression.getText().toString().endsWith("9")) {
                            writeExpression("*(");
                        } else {
                            writeExpression("(");
                        }
                    }
            );

            buttonRightParenthesis.setOnClickListener(v -> {
                String expression = tvExpression.getText().toString();
                if (!isParenthesis()) {
                    if (!expression.isEmpty()) {
                        if (tvExpression.getText().toString().endsWith("+")
                                || tvExpression.getText().toString().endsWith("-")
                                || tvExpression.getText().toString().endsWith("*")
                                || tvExpression.getText().toString().endsWith("/")) {
                            if (tvExpression.getText().toString().length() > 0) {
                                String str = tvExpression.getText().toString();
                                tvExpression.setText(str.substring(0, str.length() - 1));
                                writeExpression(")");
                            }
                        }
                    }
                } else if (isParenthesis()) {
                    Toast.makeText(MainActivity.this, ")))", Toast.LENGTH_SHORT).show();
                } else {
                    writeExpression(")");
                }
            });
            buttonOneDivided.setOnClickListener(v -> writeExpression("1/"));
            buttonSquare.setOnClickListener(v -> writeExpression("^(2)"));
            buttonCube.setOnClickListener(v -> writeExpression("^(3)"));
            buttonDegree.setOnClickListener(v -> writeExpression("^("));
            buttonFactorial.setOnClickListener(v -> writeExpression("!"));
            buttonRoot.setOnClickListener(v -> {
                        if (tvExpression.getText().toString().endsWith("1")
                                || tvExpression.getText().toString().endsWith("2")
                                || tvExpression.getText().toString().endsWith("3")
                                || tvExpression.getText().toString().endsWith("4")
                                || tvExpression.getText().toString().endsWith("5")
                                || tvExpression.getText().toString().endsWith("6")
                                || tvExpression.getText().toString().endsWith("7")
                                || tvExpression.getText().toString().endsWith("8")
                                || tvExpression.getText().toString().endsWith("9")) {
                            writeExpression("*sqrt(");
                        } else {
                            writeExpression("sqrt(");
                        }
                    }
            );
            buttonPI.setOnClickListener(v -> {
                if (tvExpression.getText().toString().endsWith("1")
                        || tvExpression.getText().toString().endsWith("2")
                        || tvExpression.getText().toString().endsWith("3")
                        || tvExpression.getText().toString().endsWith("4")
                        || tvExpression.getText().toString().endsWith("5")
                        || tvExpression.getText().toString().endsWith("6")
                        || tvExpression.getText().toString().endsWith("7")
                        || tvExpression.getText().toString().endsWith("8")
                        || tvExpression.getText().toString().endsWith("9")) {
                    writeExpression("*pi");
                } else {
                    writeExpression("pi");
                }
            });
            buttonExp.setOnClickListener(v -> {
                if (tvExpression.getText().toString().endsWith("1")
                        || tvExpression.getText().toString().endsWith("2")
                        || tvExpression.getText().toString().endsWith("3")
                        || tvExpression.getText().toString().endsWith("4")
                        || tvExpression.getText().toString().endsWith("5")
                        || tvExpression.getText().toString().endsWith("6")
                        || tvExpression.getText().toString().endsWith("7")
                        || tvExpression.getText().toString().endsWith("8")
                        || tvExpression.getText().toString().endsWith("9")) {
                    writeExpression("*e");
                } else {
                    writeExpression("e");
                }
            });
            buttonLn.setOnClickListener(v -> {
                if (tvExpression.getText().toString().endsWith("1")
                        || tvExpression.getText().toString().endsWith("2")
                        || tvExpression.getText().toString().endsWith("3")
                        || tvExpression.getText().toString().endsWith("4")
                        || tvExpression.getText().toString().endsWith("5")
                        || tvExpression.getText().toString().endsWith("6")
                        || tvExpression.getText().toString().endsWith("7")
                        || tvExpression.getText().toString().endsWith("8")
                        || tvExpression.getText().toString().endsWith("9")) {
                    writeExpression("*ln(");
                } else {
                    writeExpression("ln(");
                }
            });
            buttonLog.setOnClickListener(v -> {
                if (tvExpression.getText().toString().endsWith("1")
                        || tvExpression.getText().toString().endsWith("2")
                        || tvExpression.getText().toString().endsWith("3")
                        || tvExpression.getText().toString().endsWith("4")
                        || tvExpression.getText().toString().endsWith("5")
                        || tvExpression.getText().toString().endsWith("6")
                        || tvExpression.getText().toString().endsWith("7")
                        || tvExpression.getText().toString().endsWith("8")
                        || tvExpression.getText().toString().endsWith("9")) {
                    writeExpression("*log(");
                } else {
                    writeExpression("log(");
                }
            });
            buttonSin.setOnClickListener(v -> {
                if (tvExpression.getText().toString().endsWith("1")
                        || tvExpression.getText().toString().endsWith("2")
                        || tvExpression.getText().toString().endsWith("3")
                        || tvExpression.getText().toString().endsWith("4")
                        || tvExpression.getText().toString().endsWith("5")
                        || tvExpression.getText().toString().endsWith("6")
                        || tvExpression.getText().toString().endsWith("7")
                        || tvExpression.getText().toString().endsWith("8")
                        || tvExpression.getText().toString().endsWith("9")) {
                    writeExpression("*sin(");
                } else {
                    writeExpression("sin(");
                }
            });
            buttonCos.setOnClickListener(v -> {
                if (tvExpression.getText().toString().endsWith("1")
                        || tvExpression.getText().toString().endsWith("2")
                        || tvExpression.getText().toString().endsWith("3")
                        || tvExpression.getText().toString().endsWith("4")
                        || tvExpression.getText().toString().endsWith("5")
                        || tvExpression.getText().toString().endsWith("6")
                        || tvExpression.getText().toString().endsWith("7")
                        || tvExpression.getText().toString().endsWith("8")
                        || tvExpression.getText().toString().endsWith("9")) {
                    writeExpression("*cos(");
                } else {
                    writeExpression("cos(");
                }
            });
            buttonTan.setOnClickListener(v -> {
                if (tvExpression.getText().toString().endsWith("1")
                        || tvExpression.getText().toString().endsWith("2")
                        || tvExpression.getText().toString().endsWith("3")
                        || tvExpression.getText().toString().endsWith("4")
                        || tvExpression.getText().toString().endsWith("5")
                        || tvExpression.getText().toString().endsWith("6")
                        || tvExpression.getText().toString().endsWith("7")
                        || tvExpression.getText().toString().endsWith("8")
                        || tvExpression.getText().toString().endsWith("9")) {
                    writeExpression("*tan(");
                } else {
                    writeExpression("tan(");
                }
            });
        }


        clearScreen();
        buttonClear.setOnClickListener(v -> clearScreen());

        buttonBackspace.setOnClickListener(v -> deleteSymbol());

        buttonEqual.setOnClickListener(v -> {
            equal();
        });

        buttonOne.setOnClickListener(v -> writeExpression("1"));
        buttonTwo.setOnClickListener(v -> writeExpression("2"));
        buttonThree.setOnClickListener(v -> writeExpression("3"));
        buttonFour.setOnClickListener(v -> writeExpression("4"));
        buttonFive.setOnClickListener(v -> writeExpression("5"));
        buttonSix.setOnClickListener(v -> writeExpression("6"));
        buttonSeven.setOnClickListener(v -> writeExpression("7"));
        buttonEight.setOnClickListener(v -> writeExpression("8"));
        buttonNine.setOnClickListener(v -> writeExpression("9"));
        buttonZero.setOnClickListener(v -> writeExpression("0"));

        buttonDoubleZero.setOnClickListener(v -> {
            if (isOperation) {
                writeExpression("0");
            } else {
                writeExpression("00");
            }
        });
        buttonDecimal.setOnClickListener(v -> {
            if (tvExpression.getText().toString().equals("")) {
                writeExpression("0.");
            } else {
                writeExpression(".");
            }
        });
        buttonPercentage.setOnClickListener(v -> writeExpression("%"));
        buttonDivide.setOnClickListener(v -> writeExpression("/"));
        buttonMultiply.setOnClickListener(v -> writeExpression("*"));
        buttonMinus.setOnClickListener(v -> writeExpression("-"));
        buttonAdd.setOnClickListener(v -> writeExpression("+"));


    }

    private void deleteSymbol() {
        if (tvExpression.getText().toString().length() > 0) {
            String str = tvExpression.getText().toString();
            String expression = tvExpression.getText().toString();
            if (expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*") ||
                    expression.endsWith("/") || expression.endsWith(".")) {
                isOperation = false;
                expression = expression.substring(0, expression.length() - 1);
            }
            tvExpression.setText(str.substring(0, str.length() - 1));
            expression = tvExpression.getText().toString();

            Expression expr = new Expression(expression);
            String result = String.valueOf(expr.calculate());
            if (expression.length() < 1) {
                result = "0";
                tvResult.setText(result);
            }
            if (result.equals("NaN")) {
                tvResult.setText("Error");
            } else {
                tvResult.setText(result);
            }
        }
    }

    private void writeExpression(String value) {
        String expression = tvExpression.getText().toString();
        boolean notDigit = value.equals(".") || value.equals("/") ||
                value.equals("*") || value.equals("-") || value.equals("+") ||
                value.equals("sqrt") || value.equals("%");
        String result;
        if (!notDigit && !value.equals(")")) {
            expression = expression + value;
            tvExpression.setText(expression);
            isOperation = false;
        }
        if (expression.length() > 0 && isParenthesis() && value.equals(")") && isOperation == true) {
            tvExpression.setText(expression);
            isOperation = true;
        }
        if (expression.length() > 0 && !isParenthesis() && value.equals(")")) {
            expression = expression + value;
            tvExpression.setText(expression);
        }
        if (isOperation == true && notDigit) {
            if (tvExpression.getText().toString().length() > 0) {
                String str = tvExpression.getText().toString();
                tvExpression.setText(str.substring(0, str.length() - 1));
                expression = (String) tvExpression.getText();
                expression = expression + value;
                tvExpression.setText(expression);
                isOperation = true;
            }
        }
        if (isOperation == false && notDigit) {

            expression = expression + value;
            tvExpression.setText(expression);
            isOperation = true;
        }

        if (isOperation == false) {
            expression = tvExpression.getText().toString();
            Expression expr = new Expression(expression);
            result = String.valueOf(expr.calculate());
            if (!result.equals("NaN")) {
                tvResult.setText("=" + result);
            } else {
                tvResult.setText("");
            }
        }
        if (value.equals("1/") || value.equals("^(") || value.equals("sqrt")) {
            isOperation = true;
        }
    }


    private void clearScreen() {
        isOperation = true;
        tvExpression.setText("");
        tvResult.setText("0");
    }

    private boolean isParenthesis() {
        String str = tvExpression.getText().toString();
        if(!str.isEmpty()) {
            if (str.charAt(0) == '{') {
                return false;
            }
        }
        Stack<Character> stack = new Stack<Character>();

        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else if (c == ')')
                if (stack.empty())
                    return false;
                else if (stack.peek() == '(')
                    stack.pop();
                else
                    return false;
        }
        counter = stack.size();
        return stack.empty();
    }

    public void equal() {
        String expression = tvExpression.getText().toString();
        if (expression.length() > 0) {
            if (!isParenthesis()) {
                if (counter > 3) {
                    Toast.makeText(MainActivity.this, "Check parenthesis", Toast.LENGTH_SHORT).show();
                }
                if (counter == 1) {
                    if (expression.length() > 0) {
                        if (expression.charAt(expression.length() - 1) == '(' || expression.charAt(expression.length() - 1) == ')') {
                            tvExpression.setText(expression.substring(0, expression.length() - 1));
                        } else if (isOperation) {
                            tvExpression.setText(expression.substring(0, expression.length() - 1));
                            writeExpression(")");
                        } else {
                            writeExpression(")");
                        }
                    }
                }
                if (counter == 2) {
                    if (expression.length() > 0) {
                        if ((expression.charAt(expression.length() - 1) == '(' &&
                                expression.charAt(expression.length() - 2) == '(') || isOperation || expression.charAt(expression.length() - 1) == ')') {
                            tvExpression.setText(expression.substring(0, expression.length() - 2));
                        } else {
                            writeExpression("))");
                        }
                    }
                }
                if (counter == 3) {
                    if (expression.length() > 0) {
                        if ((expression.charAt(expression.length() - 1) == '(' &&
                                expression.charAt(expression.length() - 2) == '(' &&
                                expression.charAt(expression.length() - 3) == '(') || isOperation || expression.charAt(expression.length() - 1) == ')') {
                            tvExpression.setText(expression.substring(0, expression.length() - 3));
                        } else {
                            writeExpression(")))");
                        }
                    }
                }
            }
            expression = tvExpression.getText().toString();
//            if (expression.endsWith(")") && (expression.charAt(expression.length()-2) == ('+') ||
//                    expression.charAt(expression.length()-2) == ('-') ||
//                    expression.charAt(expression.length()-2) == ('*') ||
//                    expression.charAt(expression.length()-2) == ('/'))) {
//                expression = expression.substring(0, expression.length() - 2);
//            } else
            if ((expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*") ||
                    expression.endsWith("/"))) {
                expression = expression.substring(0, expression.length() - 1);
            }
            Expression expr = new Expression(expression);
            String result = String.valueOf(expr.calculate());
            if (result.equals("NaN")) {
                tvResult.setText("Error");
            } else {
                isOperation = false;
                tvExpression.setText(result);
                tvResult.setText(result);
            }
            if (history.size() >= 13) {
                history.remove(0);
            }
            history.add(expression + "=" + result);
        } else {
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("expression", (String) tvExpression.getText());
        outState.putString("result", (String) tvResult.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvResult.setText(savedInstanceState.getString("result"));
        tvExpression.setText(savedInstanceState.getString("expression"));
    }

}