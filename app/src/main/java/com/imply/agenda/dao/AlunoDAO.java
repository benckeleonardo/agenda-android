package com.imply.agenda.dao;

import android.widget.Toast;

import androidx.annotation.Nullable;

import com.imply.agenda.model.Aluno;
import java.util.ArrayList;

public class AlunoDAO {
    private final static ArrayList<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno alunoCriado) {
        alunoCriado.setId(contadorDeIds);
        alunos.add(alunoCriado);
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = getAlunoPeloId(aluno);
        if(alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno getAlunoPeloId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) return a;
        }
        return null;
    }

    public ArrayList<Aluno> todos() {
        ArrayList<Aluno> lista = new ArrayList<>(alunos);
        return lista;
    }
}
