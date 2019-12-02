package com.example.myapplication.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.conexao.AgendamentoWs;
import com.example.myapplication.dao.AgendamentoDAO;
import com.example.myapplication.dao.ClienteDAO;
import com.example.myapplication.model.Agenda;
import com.example.myapplication.model.Cliente;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Fragment_Agendamento extends Fragment {
    private Spinner spiner_especialidade, spiner_convenio, spiner_medico,spiner_horario;
    private TextView restornoData;
    private ImageView imgViewData;
    private DatePickerDialog datePickerDialog;
    private CardView btn_enviar;


    private String exame,convenio,medico,horario,dataSelecionada, cliente;
    private ClienteDAO clienteNome;


    AgendamentoDAO db = new AgendamentoDAO(getContext());


    public Fragment_Agendamento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__agendamento, container, false);
        findView(view);
        db = new AgendamentoDAO(getContext());
        clienteNome = new ClienteDAO(getContext());


        final String posicaoExame[] = getResources().getStringArray(R.array.agenda);
        final String posicaoConvenio[] = getResources().getStringArray(R.array.convenio);
        final String posicaoMedico[] = getResources().getStringArray(R.array.medico);
        final String posicaohorario[] = getResources().getStringArray(R.array.horario);

        spiner_especialidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                exame = posicaoExame[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spiner_convenio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                convenio = posicaoConvenio[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spiner_medico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                medico = posicaoMedico[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spiner_horario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                horario = posicaohorario[i];


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imgViewData.setOnClickListener(new View.OnClickListener() {
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
                        restornoData.setText(dataSelecionada);




                    }
                };
                datePickerDialog = new DatePickerDialog(getContext(), listener, year, month, day);
                datePickerDialog.show();
            }
        });



        ArrayAdapter adaptadorExame = ArrayAdapter.createFromResource
                (getContext(), R.array.agenda, android.R.layout.simple_dropdown_item_1line);
        spiner_especialidade.setAdapter(adaptadorExame);

        ArrayAdapter adaptadorMedico = ArrayAdapter.createFromResource
                (getContext(), R.array.medico, android.R.layout.simple_dropdown_item_1line);
        spiner_medico.setAdapter(adaptadorMedico);

        ArrayAdapter adaptadorConvenio = ArrayAdapter.createFromResource
                (getContext(), R.array.convenio, android.R.layout.simple_dropdown_item_1line);
        spiner_convenio.setAdapter(adaptadorConvenio);

        ArrayAdapter adaptadorData = ArrayAdapter.createFromResource
                (getContext(), R.array.horario, android.R.layout.simple_dropdown_item_1line);
        spiner_horario.setAdapter(adaptadorData);


        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean achou = false;
                Boolean validar = exame == null || convenio == null || medico == null || dataSelecionada == null || horario == null;
                String dataFinal = (dataSelecionada + " : " + horario);

                if (validar) {
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }else{

                    for (int i = 0; i < db.ListarBancoConsulta().size(); i++) {
                        if (medico.equals(db.ListarBancoConsulta().get(i).getMedico()) && dataFinal.equals(db.ListarBancoConsulta().get(i).getData())) {
                            achou = true;
                        }
                    }
                    if (achou) {
                        Toast.makeText(getContext(),"Esse dia ja possui uma consulta, Indisponivel agendar consulta nesse dia ",Toast.LENGTH_LONG).show();
                    } else {
                        for (Cliente clientes : clienteNome.listarBancoCliente()) {
                           cliente = clientes.getNome_cliente();
                        }
                        db.inserir(new Agenda(exame, convenio, medico,dataFinal,cliente));
                        CadConsulta(exame, convenio, medico,dataFinal, cliente);
                        Toast.makeText(getContext(),"Consulta Agendada com Sucesso",Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
        return view;
    }


    public void findView (View view){
        spiner_especialidade = view.findViewById(R.id.spiner_especialidade);
        spiner_convenio = view.findViewById(R.id.spiner_convenio);
        spiner_medico = view.findViewById(R.id.spiner_medico);
        imgViewData = view.findViewById(R.id.imgViewData);
        restornoData = view.findViewById(R.id.restornoData);
        spiner_horario = view.findViewById(R.id.spiner_horario);
        btn_enviar = view.findViewById(R.id.btn_enviar);
    }

    private void CadConsulta(String exame, String convenio, String medico, String dataFinal, String cliente) {

        Map<String, String> map = new HashMap<>();

        map.put("exame_agenda", String.valueOf(exame));
        map.put("convenio_agenda", String.valueOf(convenio));
        map.put("medico_agenda", medico);
        map.put("data_agenda", String.valueOf(dataFinal));
        map.put("nome_cliente", String.valueOf(cliente));



        AgendamentoWs.agendamento(getContext(), "consulta/agenda", map);

    }





}
