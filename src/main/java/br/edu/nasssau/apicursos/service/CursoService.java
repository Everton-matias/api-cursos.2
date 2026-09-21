package br.edu.nasssau.apicursos.service;

import br.edu.nasssau.apicursos.model.Curso;
import br.edu.nasssau.apicursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    // Injeção de dependência via construtor
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso salvar(Curso curso) {
        validarCurso(curso);
        return cursoRepository.save(curso);
    }

    public Curso atualizar(Long id, Curso cursoAtualizado) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);

        if (cursoExistente.isPresent()) {
            validarCurso(cursoAtualizado);
            Curso curso = cursoExistente.get();
            curso.setNome(cursoAtualizado.getNome());
            curso.setCargaHoraria(cursoAtualizado.getCargaHoraria());
            return cursoRepository.save(curso);
        }
        return null;
    }

    public boolean remover(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método privado para aplicar as regras de negócio
    private void validarCurso(Curso curso) {
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do curso não pode ser nulo nem ficar em branco.");
        }
        if (curso.getCargaHoraria() == null || curso.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("A carga horária deve ser maior que zero.");
        }
    }
}