package br.com.pocs.awsservices.pocawsservices.controller;

import br.com.pocs.awsservices.pocawsservices.dto.FilmeDto;
import br.com.pocs.awsservices.pocawsservices.model.Diretor;
import br.com.pocs.awsservices.pocawsservices.model.Filme;
import br.com.pocs.awsservices.pocawsservices.repository.FilmesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/filme")
public class FilmeController {

    private final FilmesRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Filme>> buscarFilmePorID(@PathVariable("id") String id){
        return Optional
                .ofNullable(repository.getFilmeById(id))
                .map(filme -> ResponseEntity.ok().body(filme))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Filme> salvarNovoFilme(@RequestBody FilmeDto dto){
        final var diretor = new Diretor();
        diretor.setNome(dto.getNomeDiretor());

        final var novoFilme = new Filme();
        novoFilme.setTitulo(dto.getTitulo());
        novoFilme.setDiretor(diretor);

        final var saveFilme = repository.save(novoFilme);

        return ResponseEntity.ok().body(saveFilme);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarFilme(@PathVariable("id") String id) {
        repository.delete(id);
        return ResponseEntity.ok().body("Filme: " + id + " excluído");
    }

    @PutMapping
    public ResponseEntity<Filme> atualizarFilme(@RequestBody FilmeDto dto){
        final var diretor = new Diretor();
        diretor.setNome(dto.getNomeDiretor());

        final var filme = new Filme();
        filme.setTitulo(dto.getTitulo());
        filme.setDiretor(diretor);

        return ResponseEntity.ok().body(repository.update(filme));
    }

}
