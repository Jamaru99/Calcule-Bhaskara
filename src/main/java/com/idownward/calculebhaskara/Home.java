package com.idownward.calculebhaskara;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.*;

public class Home extends Activity {

    EditText txtA;
    EditText txtB;
    EditText txtC;
    TextView txtX1;
    TextView txtX2;
    AdView adView;
    private AlertDialog alerta;


    public double delta;
    public double a = 0;
    public double b = 0;
    public double c = 0;
    double raiz1;
    double raiz2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtA = (EditText) findViewById(R.id.editText2);
        txtB = (EditText) findViewById(R.id.editText);
        txtC = (EditText) findViewById(R.id.editText3);
        txtX1 = (TextView) findViewById(R.id.x1);
        txtX2 = (TextView) findViewById(R.id.x2);
        adView = (AdView) findViewById(R.id.adView);

        txtA.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                adView.setVisibility(View.INVISIBLE);

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

        txtB.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                adView.setVisibility(View.INVISIBLE);

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

        txtC.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                adView.setVisibility(View.INVISIBLE);

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        if(isTablet()){
            startActivity(new Intent(this, Tablet.class));
            finish();
            return;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {
            startActivity(new Intent(this, About.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Boolean isTablet(){
        int sizeMask = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        boolean large = (sizeMask == Configuration.SCREENLAYOUT_SIZE_LARGE);
        boolean xlarge = (sizeMask == 4);
        return large || xlarge;
    }

    public void onClick(View v){

        adView.setVisibility(View.VISIBLE);
        if(txtA.getText().toString().equals("") || txtB.getText().toString().equals("") || txtC.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Erro");
            alert.setMessage("Não sou advinho, insira os valores de a, b e c.\n Lembre-se que ax² + bx + c = 0");
            alert.setNeutralButton("Beleza", null);
            alerta = alert.create();
            alert.show();
        }
        else{
            a = Double.parseDouble(txtA.getText().toString());
            b = Double.parseDouble(txtB.getText().toString());
            c = Double.parseDouble(txtC.getText().toString());

            delta = (b * b) - (4 * a * c);
            raiz1 = ((-b + Math.sqrt(delta)) / (2 * a));
            raiz2 = ((-b - Math.sqrt(delta)) / (2 * a));

            if (delta < 0) {
                txtX1.setText("Não existe solução");
                txtX2.setText("");
            }
            else if(delta == 0){
                txtX1.setText("X = " + Double.toString(raiz1));
                txtX2.setText("");
            }
            else {
                txtX1.setText("X' = " + Double.toString(raiz1));
                txtX2.setText("X'' = " + Double.toString(raiz2));
            }

        }
    }

    public void editTextClick(View v){
        adView.setVisibility(View.INVISIBLE);
    }

    public void clickResolution(View v){

        adView.setVisibility(View.VISIBLE);
        if(a == 0 || b == 0 || c == 0){
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Calcule antes");
            ad.setMessage("Tu nem calculou ainda e já quer ver a resolução?");
            ad.setNeutralButton("Ah ta", null);
            ad.show();
        }
        else {
            if(delta > 0) {
                AlertDialog.Builder ad = new AlertDialog.Builder(this);
                ad.setTitle("Resolução");
                ad.setMessage("Δ = b² - 4.a.c\n" +
                        "Δ = " + b + "² - 4 x " + a + " x " + c + "\n" +
                        "Δ = " + (b * b) + " " + (-4 * a * c) + "\n" +
                        "Δ = " + delta + "\n\n" +
                        "x = (-b ± √Δ)/(2.a)\n" +
                        "x = (" + -b + " ± " + "√" + delta + ")" + "/(2 x " + a + ")\n" +
                        "x = (" + -b + " ± " + Math.sqrt(delta) + ")" + "/" + (2 * a) + "\n\n" +
                        "x' = (" + -b + " + " + Math.sqrt(delta) + ")" + "/" + (2 * a) + " = " + (-b + Math.sqrt(delta)) + "/" + (2 * a) + " = " + raiz1 + "\n" +
                        "x'' = (" + -b + " - " + Math.sqrt(delta) + ")" + "/" + (2 * a) + " = " + (-b - Math.sqrt(delta)) + "/" + (2 * a) + " = " + raiz2);
                ad.setNeutralButton("Entendi", null);
                ad.show();
            }
            else if(delta == 0){
                AlertDialog.Builder ad = new AlertDialog.Builder(this);
                ad.setTitle("Resolução");
                ad.setMessage("Δ = b² - 4.a.c\n" +
                        "Δ = " + b + "² - 4 x " + a + " x " + c + "\n" +
                        "Δ = " + (b * b) + " " + (-4 * a * c) + "\n" +
                        "Δ = " + delta + "\n\n" +
                        "x = (-b ± √Δ)/(2.a)\n" +
                        "x = (" + -b + " ± " + "√" + delta + ")" + "/(2 x " + a + ")\n" +
                        "x = (" + -b + " ± " + Math.sqrt(delta) + ")" + "/" + (2 * a) + "\n" +
                        "x = (" + -b + " + " + ")" + "/" + (2 * a) + " = " + (-b + Math.sqrt(delta)) + "/" + (2 * a) + " = " + raiz1);
                ad.setNeutralButton("Entendi", null);
                ad.show();
            }
            else{
                AlertDialog.Builder ad = new AlertDialog.Builder(this);
                ad.setTitle("Resolução");
                ad.setMessage("Δ = b² - 4.a.c\n" +
                        "Δ = " + b + "² - 4 x " + a + " x " + c + "\n" +
                        "Δ = " + (b * b) + " " + (-4 * a * c) + "\n" +
                        "Δ = " + delta + "\n\n" +
                        "Quando o delta é negativo, a raiz é inexistente");
                ad.setNeutralButton("Entendi", null);
                ad.show();
            }
        }
    }

    public void limpar(View v){
        txtA.setText("");
        txtB.setText("");
        txtC.setText("");
        adView.setVisibility(View.VISIBLE);
    }

}
