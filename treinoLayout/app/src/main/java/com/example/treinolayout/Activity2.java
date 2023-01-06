package com.example.treinolayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    //--------------------------------------------------------------
    public static int RESULT_ACTIVITY2 = 2;
    TextView tvRacaEscolhida, tvNome, tvSeMortoVivo;
    TextView tvPontosDisponiveis, tvNForca, tvNOcult, tvNDext, tvNInteligi;
    //--------------------------------------------------------------
    ImageButton imgMaisForca, imgMaisInteligi, imgMaisOcult, imgMaisDext;
    ImageButton imgMenosForca, imgMenosInteligi, imgMenosOcult, imgMenosDext;
    //--------------------------------------------------------------
    ImageButton imgVoltar;
    //--------------------------------------------------------------
    int totalDePontos, pontosForca, pontosInteligencia, pontosOcult, pontosDext;
    //--------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        //--------------------------------------------------------------

        //--------------------------------------------------------------
        tvRacaEscolhida = findViewById(R.id.tvRacaEscolhida);
        tvSeMortoVivo = findViewById(R.id.tvSeMortoVivoDistribuido);
        tvPontosDisponiveis = findViewById(R.id.tvPontosDisponiveis);
        tvNForca = findViewById(R.id.tvPontosForca);
        tvNInteligi = findViewById(R.id.tvPontosInteligencia);
        tvNOcult = findViewById(R.id.tvPontosOcultismo);
        tvNDext = findViewById(R.id.tvPontosDestrresa);
        imgVoltar = findViewById(R.id.imgVoltarTela3);
        //--------------------------------------------------------------
        imgMaisForca = findViewById(R.id.imgMaisForca);
        imgMaisInteligi = findViewById(R.id.imgMaisInt);
        imgMaisOcult = findViewById(R.id.imgMaisOcult);
        imgMaisDext = findViewById(R.id.imgMaisDext);
        //--------------------------------------------------------------
        imgMenosForca = findViewById(R.id.imgMenosForca);
        imgMenosInteligi = findViewById(R.id.imgMenossInt);
        imgMenosOcult = findViewById(R.id.imgMenosOcult);
        imgMenosDext = findViewById(R.id.imgMenosDext);
        //--------------------------------------------------------------
        totalDePontos = 50;
        pontosForca = 50;
        tvNForca.setText(String.valueOf(pontosForca));
        pontosInteligencia = 50;
        tvNInteligi.setText(String.valueOf(pontosInteligencia));
        pontosOcult = 50;
        tvNOcult.setText(String.valueOf(pontosOcult));
        pontosDext = 50;
        tvNDext.setText(String.valueOf(pontosDext));
        //--------------------------------------------------------------
        imgMaisForca.setOnClickListener(view -> {
            if (totalDePontos > 0){
                totalDePontos--;
                pontosForca++;
                tvPontosDisponiveis.setText(String.valueOf(totalDePontos));
                tvNForca.setText(String.valueOf(pontosForca));
            }
        });
        imgMaisInteligi.setOnClickListener(view -> {
            if (totalDePontos > 0){
                totalDePontos--;
                pontosInteligencia++;
                tvPontosDisponiveis.setText(String.valueOf(totalDePontos));
                tvNInteligi.setText(String.valueOf(pontosInteligencia));
            }
        });
        imgMaisOcult.setOnClickListener(view -> {
            if (totalDePontos > 0){
                totalDePontos--;
                pontosOcult++;
                tvPontosDisponiveis.setText(String.valueOf(totalDePontos));
                tvNOcult.setText(String.valueOf(pontosOcult));
            }
        });
        imgMaisDext.setOnClickListener(view -> {
            if (totalDePontos > 0){
                totalDePontos--;
                pontosDext++;
                tvPontosDisponiveis.setText(String.valueOf(totalDePontos));
                tvNDext.setText(String.valueOf(pontosDext));
            }
        });
        //--------------------------------------------------------------
        imgMenosForca.setOnClickListener(view -> {
            if (pontosForca > 1) {
                totalDePontos++;
                pontosForca--;
                tvPontosDisponiveis.setText(String.valueOf(totalDePontos));
                tvNForca.setText(String.valueOf(pontosForca));
            }

        });
        imgMenosInteligi.setOnClickListener(view -> {
            if (pontosInteligencia > 1) {
                totalDePontos++;
                pontosInteligencia--;
                tvPontosDisponiveis.setText(String.valueOf(totalDePontos));
                tvNInteligi.setText(String.valueOf(pontosInteligencia));
            }

        });
        imgMenosOcult.setOnClickListener(view -> {
            if (pontosOcult > 1) {
                totalDePontos++;
                pontosOcult--;
                tvPontosDisponiveis.setText(String.valueOf(totalDePontos));
                tvNOcult.setText(String.valueOf(pontosOcult));
            }

        });
        imgMenosDext.setOnClickListener(view -> {
            if (pontosDext > 1) {
                totalDePontos++;
                pontosDext--;
                tvPontosDisponiveis.setText(String.valueOf(totalDePontos));
                tvNDext.setText(String.valueOf(pontosDext));
            }
        });
        //--------------------------------------------------------------
        imgVoltar.setOnClickListener(view -> {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("pacote");
            bundle.putInt("forca", pontosForca);
            bundle.putInt("inteligencia", pontosInteligencia);
            bundle.putInt("ocultismo", pontosOcult);
            bundle.putInt("dext", pontosDext);
            intent.putExtra("pacoteParaTela2", bundle);
            setResult(RESULT_OK, intent);
            onBackPressed();
        });
        //--------------------------------------------------------------
        receberBundle();
    }

    private void receberBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("pacote");
        tvRacaEscolhida.setText(bundle.getString("descricao"));
        //bundle.getString("descricao")
        tvSeMortoVivo.setText(bundle.getString("mortoVivo"));
        //bundle.getString("mortoVivo")
    }
}