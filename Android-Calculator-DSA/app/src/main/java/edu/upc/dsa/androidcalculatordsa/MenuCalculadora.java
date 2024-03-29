package edu.upc.dsa.androidcalculatordsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MenuCalculadora extends AppCompatActivity {
    private double numero1,numero2,resultado;
    private int operador=1;
    private TextView textView;
    private boolean isDegrees=true;
    private  boolean numeroIngresado=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_calculadora);

        Button btnToggleMode=findViewById(R.id.btToggle);

        btnToggleMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDegrees=!isDegrees;
            }
        });
    }

    public void ClickBtAC(View view){
        numero1=0.0;
        numero2=0.0;
        operador=0;
        textView=findViewById(R.id.txVerOperacion);
        textView.setText("");
    }
    public void ClickBtSum(View view){
        operador=1;
        textView=findViewById(R.id.txVerOperacion);
        numero1=Double.parseDouble(textView.getText().toString());
        textView.setText("");
    }

    public void ClickBtSubs(View view){
        operador=2;
        textView=findViewById(R.id.txVerOperacion);
        numero1=Double.parseDouble(textView.getText().toString());
        textView.setText("");
    }
    public void ClickBtMultip(View view){
        operador=3;
        textView=findViewById(R.id.txVerOperacion);
        numero1=Double.parseDouble(textView.getText().toString());
        textView.setText("");
    }
    public void ClickBtDiv(View view){
        operador=4;
        textView=findViewById(R.id.txVerOperacion);
        numero1=Double.parseDouble(textView.getText().toString());
        textView.setText("");
    }

    public void ClickBtSin(View view){
        operador=5;
        textView=findViewById(R.id.txVerOperacion);
        String numeroTexto = textView.getText().toString().replace("TextView", "");
        if (!numeroTexto.isEmpty()) {
            try {
                numero1 = Double.parseDouble(numeroTexto);
                numeroIngresado=true;
                textView.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Número no válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }
    public void ClickBtCos(View view){
        operador=6;
        textView=findViewById(R.id.txVerOperacion);
        String numeroTexto = textView.getText().toString().replace("TextView", "");
        if (!numeroTexto.isEmpty()) {
            try {
                numero1 = Double.parseDouble(numeroTexto);
                numeroIngresado=true;
                textView.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Número no válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }
    public void ClickBtTan(View view){
        textView=findViewById(R.id.txVerOperacion);
        String numeroTexto = textView.getText().toString();
        if (!numeroTexto.isEmpty()) {
            try {
                numero1 = Double.parseDouble(numeroTexto);
                operador=7;
                numeroIngresado=true;
                textView.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Número no válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }

    public void ClickBtNum(View view) {
        textView = findViewById(R.id.txVerOperacion);
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        textView.append(buttonText);
    }

    public void ClickBtIgual(View view){
        textView=findViewById(R.id.txVerOperacion);
        String texto=textView.getText().toString();

        if (TextUtils.isEmpty(texto)) {
            Toast.makeText(this, "Ingrese un número antes de calcular", Toast.LENGTH_SHORT).show();
            return;
        }
        numero2=Double.parseDouble(texto);
        if(operador==1){
            resultado=numero1+numero2;
        } else if (operador==2) {
            resultado=numero1-numero2;
        } else if (operador==3) {
            resultado=numero1*numero2;
        } else if (operador==4) {
            if(numero2!=0){
                resultado=numero1/numero2;
            }else{
                Toast.makeText(this, "Error: División por cero", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (operador==5) {
            resultado=isDegrees?Math.sin(Math.toRadians(numero1)):Math.sin(numero1);
        } else if (operador==6) {
            resultado=isDegrees?Math.cos(Math.toRadians(numero1)):Math.cos(numero1);
        } else if (operador==7) {
            resultado=isDegrees?Math.tan(Math.toRadians(numero1)):Math.tan(numero1);
        }
        textView.setText(String.format(Locale.getDefault(),"%.2f",resultado));
    }
}