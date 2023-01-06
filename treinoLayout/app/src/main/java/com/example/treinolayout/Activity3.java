package com.example.treinolayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {
    /*
    FEITO 2) O App deverá passar informações da tela inicial para a segunda tela, e da segunda tela para a terceira tela, podendo ser dados diferentes ou não.

    3) A terceira tela deverá passar informações para a tela inicial, sendo ignoradas na segunda tela e captadas na tela inicial.

    4) A tela inicial deverá ter elementos de interface diferentes, como editText, checkBox, seekBar, etc. e os valores que o usuário inserir nesses
       campos de interface deverão ser passados e exibidos na tela 2.

    5) A tela 3 deverá também ter alguns elementos de interface que o usuário ao alterá-los, as informações deverão ser passadas e exibidas na tela 1.
     */

    TextView idForca, idInt, idOcult, idDext;
    EditText edNome;
    SeekBar barraIdade;
    SwitchCompat mortoVivo;
    TextView tvIdade, tvSePersonagem, tvRaca, tvNome;
    ConstraintLayout constraintLayout;
    ImageView imageView;
    //---------------------------------------------------------------------------------------------
    ActivityResultLauncher<Intent> inciarAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        idForca = findViewById(R.id.tvValorForça);
        idInt = findViewById(R.id.tvValorInt);
        idOcult = findViewById(R.id.tvValorOcult);
        idDext = findViewById(R.id.tvvalorDext);
        edNome = findViewById(R.id.edNome);
        tvIdade = findViewById(R.id.tvIdade);
        mortoVivo = findViewById(R.id.switch1);
        barraIdade = findViewById(R.id.seekBar2);
        tvSePersonagem = findViewById(R.id.tvSePersonagem);
        constraintLayout = findViewById(R.id.cl);
        imageView = findViewById(R.id.setaCriarPersonagem);
        tvRaca = findViewById(R.id.tvRaca);
        tvNome = findViewById(R.id.tvTela1Nome);
        //---------------------------------------------------------------------------------------------
        inicializarSeekBar();
        verificarSePersonagemCriado();
        //---------------------------------------------------------------------------------------------
        constraintLayout.setVisibility(View.GONE);
        //---------------------------------------------------------------------------------------------
        imageView.setOnClickListener(view -> {
            //armazenar dados em variaveis
            int idade = barraIdade.getProgress() + 1;
            String nome = edNome.getText().toString();
            String mV;
            if (mortoVivo.isChecked()) {
                mV = "morto-vivo";
            } else {
                mV = "vivo";
            }
            //------------------------------
            Bundle bundle = new Bundle();
            bundle.putString("nome", nome);
            bundle.putInt("idade", idade);
            bundle.putString("mortoVivo", mV);
            //--------------------------
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("pacote", bundle);
            inciarAtividade.launch(intent);

        });
    }

    private void verificarSePersonagemCriado() {
        inciarAtividade = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null && result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null && result.getData().getBundleExtra("pacote") != null) {
                        Bundle bundle = result.getData().getBundleExtra("pacote");
                        if (String.valueOf(bundle.getInt("forca")).equals("0")){
                            Toast.makeText(getApplicationContext(), "Complete a criação do personagem", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            idForca.setText(String.valueOf(bundle.getInt("forca")));
                            idInt.setText(String.valueOf(bundle.getInt("inteligencia")));
                            idOcult.setText(String.valueOf(bundle.getInt("ocultismo")));
                            idDext.setText(String.valueOf(bundle.getInt("dext")));
                            tvRaca.setText( "Raça " + bundle.getString("raca"));
                            tvNome.setText( "Nome: " + bundle.getString("nome"));
                            constraintLayout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "por favor preecha todos os dados do seu personagem", Toast.LENGTH_SHORT).show();
                    }
                } 
            }
        });
    }

    private void inicializarSeekBar() {
        barraIdade.setMax(299);
        barraIdade.setProgress(150);
        barraIdade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvIdade.setText(String.valueOf(i + 1).concat(" anos"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tvIdade.setText(String.valueOf(barraIdade.getProgress()).concat(" anos"));
    }
}
