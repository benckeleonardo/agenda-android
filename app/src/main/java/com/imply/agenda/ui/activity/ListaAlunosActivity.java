package com.imply.agenda.ui.activity;

import static com.imply.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imply.agenda.R;
import com.imply.agenda.dao.AlunoDAO;
import com.imply.agenda.model.Aluno;

import java.util.ArrayList;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private FloatingActionButton btnAdd;
    private ListView lstViewAlunos;
    private ArrayList<Aluno> lstAlunos;

    public ListaAlunosActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(APPBAR_TITLE);
        setContentView(R.layout.activity_lista_alunos);
        initializeComponents();
        dao.salva(new Aluno("Leo", "555", "leo@gmail.com"));
        dao.salva(new Aluno("teste", "555", "leo@gmail.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLstAlunos();
    }

    private void initializeComponents() {
        lstViewAlunos = findViewById(R.id.lstAlunos);
        lstViewAlunos.setOnItemClickListener((adapterView, view, i, l) -> { //indice e ID interno
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(i);
            abreFormularioModoEditarAluno(alunoEscolhido);
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            abreFormularioModoAddAluno();
        });
    }

    private void abreFormularioModoAddAluno() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    private void abreFormularioModoEditarAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void updateLstAlunos() {
        lstAlunos = dao.todos();
        lstViewAlunos.setAdapter(new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, lstAlunos));
    }
}