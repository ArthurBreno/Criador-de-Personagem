package com.example.treinolayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rd1, rd2, rd3, rd4;
    SeekBar seekBar;
    EditText edPeso;
    CheckBox cbCicatriz, cbTatuagem, cbOlhoDeVidro, cbPernaDePau;
    TextView tvAlturamomentanea;
    ImageButton btnIr, btnVolta;
    TextView tvNomeidade;
    //--------------------------------------------------------------------
    float valorReal = 0;
    String texto = "";
    String nome;
    String mortoVivo;
    String raca = "";
    ActivityResultLauncher<Intent> inciarAtividade;
    Bundle bundlePrincipal = new Bundle();
    //--------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);
        seekBar = findViewById(R.id.seekBar);
        edPeso = findViewById(R.id.edPeso);
        tvNomeidade = findViewById(R.id.tvNomeIdade);
        cbCicatriz = findViewById(R.id.cbCicatriz);
        cbPernaDePau = findViewById(R.id.cbPernaDePau);
        cbTatuagem = findViewById(R.id.cbTatoo);
        cbOlhoDeVidro = findViewById(R.id.cbOlhoDeVidro);
        tvAlturamomentanea = findViewById(R.id.tvAlturaMomentanea);
        rd1 = findViewById(R.id.radioButton);
        rd2 = findViewById(R.id.radioButton2);
        rd3 = findViewById(R.id.radioButton3);
        rd4 = findViewById(R.id.radioButton4);
        btnIr = findViewById(R.id.imgIr);
        btnVolta = findViewById(R.id.imgVoltar);
        //--------------------------------------------------------------
        inicializarSeekbar();
        receberBundle();
        verificarSePersonagemCriado();
        //--------------------------------------------------------------
        btnIr.setOnClickListener(view -> {
           if (checarSeCamposObrigatoriosPreenchidos()) {
                gerarDescricao();
                // receber bundle
                Bundle bundle = getIntent().getBundleExtra("pacote");
                bundle.putString("descricao", texto);
                bundle.putString("mortoVivo", mortoVivo);
                bundle.putString("raca", raca);
                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                intent.putExtra("pacote", bundle);
                inciarAtividade.launch(intent);
            }
        });
        btnVolta.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("pacote", bundlePrincipal);
            setResult(RESULT_OK, intent);
            onBackPressed();
        });
    }

    private void receberBundle() {
        Bundle bundle = getIntent().getBundleExtra("pacote");
        nome = bundle.getString("nome");
        mortoVivo = bundle.getString("mortoVivo");
        int idade = bundle.getInt("idade");
        tvNomeidade.setText("nome: " + nome + "\nIdade:  " + idade + "\nEstado de saude: " + mortoVivo);

    }

    private boolean checarSeCamposObrigatoriosPreenchidos() {
        boolean check = false;
        int i = 0;
        if (edPeso.getText().toString().isEmpty()){
            i = 2;
        }
        if (radioGroup.getCheckedRadioButtonId() == 0){
            i = 3;
        }

        switch (i){
            case 2:
                Toast.makeText(getApplicationContext(), "Defina o peso inicial do seu pesonagem", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "Defina a raça do seu pesonagem", Toast.LENGTH_SHORT).show();
                break;
            case 0:
                check = true;
                break;
        }
        return check;
    }

    private void gerarDescricao() {
        //atribuir as descições ao texto total
        String a = "";
        String b = "";
        String c = "";
        String d = "";
        // atribuir raça ao personage
        int racaEscolhida = radioGroup.getCheckedRadioButtonId();
        if (racaEscolhida == 2131231068){
            raca = " dos elfos";
        }
        if (racaEscolhida == 2131231069){
            raca = " dos Orcs";
        }
        if (racaEscolhida == 2131231070){
            raca = "Humana";
        }
        if (racaEscolhida == 2131231071){
            raca = "dos Anões";
        }

        //definiar parte inicial do texto:
        String inicio = "Grande " + nome + " ,pertence a raça " + raca;
                // definiar parte das caracteristicas
        String caracteristicas = "";
        if ((!cbOlhoDeVidro.isChecked()) && (!cbTatuagem.isChecked()) && (!cbCicatriz.isChecked()) && (!cbPernaDePau.isChecked())){
            a = " ,é um novato de guerra que";
        }
        else {
            if (cbOlhoDeVidro.isChecked()){
                a = " perdeu um olho cozinhando o cranio de seus enimigos,";
            }
            if (cbTatuagem.isChecked()){
                b = " tem uma tatuagem do antigo clã que pertência,";
            }
            if (cbCicatriz.isChecked()){
                c = " tem varias cicatrizes pelo rosto,";
            }
            if (cbPernaDePau.isChecked()){
                d = " perdeu uma perna, pisando em uma armadilha para ursos,";
            }
        }
        caracteristicas = a + b + c + d;
        // acrescentar altura na descrição
        caracteristicas = caracteristicas.concat(" com incriveis " + valorReal + " metros de altura e pesando " + edPeso.getText().toString() + " quilos, " + nome + " está prontamente disponivel para qualquer combate.");

        texto = inicio + caracteristicas;

    }

    private void inicializarSeekbar() {
        seekBar.setMax(300);
        seekBar.setProgress(150);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valorReal = ((float) i) / 100;
                tvAlturamomentanea.setText(String.valueOf(valorReal).concat(" m"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        valorReal = 1.5F;
        tvAlturamomentanea.setText(String.valueOf( (float) seekBar.getProgress() / 100).concat(" m"));
    }

    private void verificarSePersonagemCriado() {
        inciarAtividade = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null && result.getResultCode() == RESULT_OK){
                    if (result.getData() != null && result.getData().getBundleExtra("pacoteParaTela2") != null){
                        bundlePrincipal = result.getData().getBundleExtra("pacoteParaTela2");
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "por favor preecha todos os dados do seu personagem", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "PANE NO SISTEMA ALGUEM ME DESCNFIGURO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}