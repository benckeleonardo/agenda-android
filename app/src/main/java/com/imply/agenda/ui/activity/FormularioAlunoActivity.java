package com.imply.agenda.ui.activity;

import static com.imply.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.imply.agenda.R;
import com.imply.agenda.dao.AlunoDAO;
import com.imply.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE_NOVO_ALUNO = "Novo aluno";
    private static final String APPBAR_TITLE_EDITA_ALUNO = "Edita aluno";
    private final AlunoDAO dao = new AlunoDAO();
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(APPBAR_TITLE_NOVO_ALUNO);
        initializeComponents();

        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(APPBAR_TITLE_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        }
    }

    private void initializeComponents() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);

        botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(view -> {
            String nome = campoNome.getText().toString();
            String telefone = campoTelefone.getText().toString();
            String email = campoEmail.getText().toString();

            if(aluno == null) {
                dao.salva(new Aluno(nome, telefone, email));
            }
            else {
                aluno.setNome(nome);
                aluno.setTelefone(telefone);
                aluno.setEmail(email);
                dao.edita(aluno);
            }
            finish();
        });
    }
}