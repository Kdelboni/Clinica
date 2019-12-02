package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.ExameDAO;
import com.example.myapplication.interfaceHelp.InterfaceHelp;
import com.example.myapplication.model.Exame;

import java.util.Calendar;

public class ExameActivity extends AppCompatActivity implements InterfaceHelp {

    private Spinner spiner_especialidade_exame, spiner_convenio_exame, spiner_medico_exame, spiner_horario_exame;
    private CardView btn_enviarExame;
    private TextView restornoDataExame;
    private Context contexto = this;
    private ImageView ivData;
    private String dataSelecionada;
    private DatePickerDialog datePickerDialog;

    private String exame,convenio,medico,horario;



    ExameDAO db = new ExameDAO(contexto);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame);

        FindView();
        valorSpinner();
        Onclick();

        db = new ExameDAO(contexto);

        ArrayAdapter adaptadorExame = ArrayAdapter.createFromResource
                (contexto, R.array.exame, android.R.layout.simple_dropdown_item_1line);
        spiner_especialidade_exame.setAdapter(adaptadorExame);

        ArrayAdapter adaptadorMedico = ArrayAdapter.createFromResource
                (contexto, R.array.medico, android.R.layout.simple_dropdown_item_1line);
        spiner_medico_exame.setAdapter(adaptadorMedico);

        ArrayAdapter adaptadorConvenio = ArrayAdapter.createFromResource
                (contexto, R.array.convenio, android.R.layout.simple_dropdown_item_1line);
        spiner_convenio_exame.setAdapter(adaptadorConvenio);

        ArrayAdapter adaptadorData = ArrayAdapter.createFromResource
                (contexto, R.array.horario, android.R.layout.simple_dropdown_item_1line);
        spiner_horario_exame.setAdapter(adaptadorData);




    }

    @Override
    public void FindView() {
        spiner_especialidade_exame = findViewById(R.id.spiner_especialidade_exame);
        spiner_convenio_exame = findViewById(R.id.spiner_convenio_exame);
        spiner_medico_exame = findViewById(R.id.spiner_medico_exame);
        btn_enviarExame = findViewById(R.id.btn_enviarExame);
        ivData = findViewById(R.id.imgViewDataExame);
        restornoDataExame = findViewById(R.id.restornoDataExame);
        spiner_horario_exame = findViewById(R.id.spiner_horario_exame);
    }

    @Override
    public void Onclick() {

        ivData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day, month, year;
                Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);


                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        String dia = String.valueOf(day);
                        month = month + 1;
                        String mes = String.valueOf(month);
                        if (dia.length() == 1) {
                            dia = "0" + dia;
                        }
                        if (mes.length() == 1) {
                            mes = "0" + mes;
                        }

                        dataSelecionada = dia + "/" + mes + "/" + String.valueOf(year);
                        restornoDataExame.setText(dataSelecionada);


                    }
                };
                datePickerDialog = new DatePickerDialog(contexto, listener, year, month, day);
                datePickerDialog.show();
            }
        });





        btn_enviarExame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean achou = false;
                boolean validar = exame == null || convenio == null || medico == null || dataSelecionada == null || horario == null;
                String dataFinal = (dataSelecionada + " : " + horario);

                if (validar) {
                    Toast.makeText(contexto, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }else{

                    for (int i = 0; i < db.ListarBancoExame().size(); i++) {
                        if (medico.equals(db.ListarBancoExame().get(i).getMedicoExame()) && dataFinal.equals(db.ListarBancoExame().get(i).getData())) {
                            achou = true;
                        }
                    }
                    if (achou) {
                        Toast.makeText(contexto,"Esse dia ja possui um Exame Agendado",Toast.LENGTH_LONG).show();
                    } else {
                        db.inserir(new Exame(exame, convenio, medico,dataFinal));

                        Toast.makeText(contexto,"Exame Agendado com Sucesso",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    public void valorSpinner() {
        final String posicaoExame[] = getResources().getStringArray(R.array.exame);
        final String posicaoConvenio[] = getResources().getStringArray(R.array.convenio);
        final String posicaoMedico[] = getResources().getStringArray(R.array.medico);
        final String posicaoHorario[] = getResources().getStringArray(R.array.horario);


        spiner_especialidade_exame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                exame = posicaoExame[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spiner_horario_exame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                horario = posicaoHorario[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spiner_convenio_exame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convenio = posicaoConvenio[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spiner_medico_exame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                medico = posicaoMedico[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
