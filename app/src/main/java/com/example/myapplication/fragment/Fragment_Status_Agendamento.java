package com.example.myapplication.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapter_agenda;
import com.example.myapplication.dao.AgendamentoDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Status_Agendamento extends Fragment {
    private RecyclerView recyclerView;
    private AgendamentoDAO db;


    public Fragment_Status_Agendamento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__historico, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        db = new AgendamentoDAO(getContext());

        if (db.ListarBancoConsulta().isEmpty()) {
            Toast.makeText(getContext(), "vazio", Toast.LENGTH_SHORT).show();

        } else {
            db.ListarBancoConsulta();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new Adapter_agenda(getContext(), db.ListarBancoConsulta(), clickListner()));
        }


        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            if (getContext() != null) {

                db.ListarBancoConsulta();
                //setLayoutManager para exibir o recyclerView
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new Adapter_agenda(getContext(), db.ListarBancoConsulta(), clickListner()));
            }

        }
    }


    @Override
    public void onResume() {
        db.ListarBancoConsulta();
        //setLayoutManager para exibir o recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new Adapter_agenda(getContext(), db.ListarBancoConsulta(), clickListner()));


        super.onResume();
    }


    private Adapter_agenda.cliqueCard clickListner() {
        return new Adapter_agenda.cliqueCard() {
            @Override
            public void onLongClick(View view, final int indxx) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());

                alerta.setTitle("Atenção");
                alerta.setMessage("Deseja excluir essa consulta ");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.excluir(db.ListarBancoConsulta().get(indxx).getIdAgenda());

                        db.ListarBancoConsulta();
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(new Adapter_agenda(getContext(), db.ListarBancoConsulta(), clickListner()));
                    }
                });
                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alerta.show();
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void clickView(View view, final int index) {
//                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
//                View mapeaView = getLayoutInflater().inflate(R.layout.dialog_historico, null);
//                alerta.setView(mapeaView);
//                final AlertDialog fechar = alerta.create();
//                fechar.setCanceledOnTouchOutside(false);
//
//                TextView nome = mapeaView.findViewById(R.id.alertNome);
//                TextView telefone = mapeaView.findViewById(R.id.alertTelefone);
//                TextView endereco = mapeaView.findViewById(R.id.alertEndereco);
//                TextView tconsulta = mapeaView.findViewById(R.id.alertTipoConsulta);
//
//                Button btn_edt = mapeaView.findViewById(R.id.btn_edt);
//                btn_edt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        Intent intent = new Intent(getContext(), EditarActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                        intent.putExtra("objeto", agendaList.get(position));
//                        intent.putExtra("objeto", db.ListarBancoConsulta().get(index));
//                        startActivity(intent);
//                        fechar.cancel();
//
//                    }
//                });
//
//                Button btn_fechar = mapeaView.findViewById(R.id.btn_ok);
//
//                btn_fechar.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        fechar.cancel();
//                    }
//                });
//
//
////                nome.setText(db.ListarBancoConsulta().get(index).getNome().toUpperCase());
////                telefone.setText(db.ListarBancoConsulta().get(index).getTelefone());
////                endereco.setText(db.ListarBancoConsulta().get(index).getEndereco());
////                tconsulta.setText(db.ListarBancoConsulta().get(index).getTipoConsulta());
//
//
//                fechar.show();
//

            }
        };
    }
}
