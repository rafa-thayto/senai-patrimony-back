package br.senai.sp.info.pweb.ianes.ws.rest.v1.controllers;

import br.senai.sp.info.pweb.ianes.ws.exceptions.EntityNotFoundException;
import br.senai.sp.info.pweb.ianes.ws.exceptions.ValidationException;
import br.senai.sp.info.pweb.ianes.ws.models.Patrimonio;
import br.senai.sp.info.pweb.ianes.ws.services.PatrimonioService;
import br.senai.sp.info.pweb.ianes.ws.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1/patrimonios")
public class PatrimonioController {

    @Autowired
    private PatrimonioService patrimonioService;

    /**
     * Search user by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {

        try {

            return ResponseEntity
                    .ok(patrimonioService.buscarPorId(id));

        } catch (EntityNotFoundException e) {

            return ResponseEntity
                    .notFound()
                    .header("X-Reason", "Entidade não encontrada")
                    .build();

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();

        }

    }

    /**
     * Search all users
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> buscarTodos() {

        try {

            return ResponseEntity
                    .ok(patrimonioService.buscarTodos());

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();

        }
    }

    /**
     * Persists the user
     * @param patrimonio
     * @param brPatrimonio
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Patrimonio patrimonio, BindingResult brPatrimonio) {

        try {

            return ResponseEntity
                    .ok(patrimonioService.cadastrar(patrimonio, brPatrimonio));

        } catch (ValidationException e) {

            return ResponseEntity
                    .unprocessableEntity()
                    .body(MapUtils.mapaDe(brPatrimonio));

        } catch (EntityNotFoundException e) {

            return ResponseEntity
                    .notFound()
                    .header("X-Reason", "Entidade categoria não foi encontrada!")
                    .build();

        }  catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }


    }

    /**
     * Delete the user
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {

        try {

            patrimonioService.deletar(id);

            return ResponseEntity
                    .noContent()
                    .build();

        } catch (EntityNotFoundException e) {

            return ResponseEntity
                    .notFound()
                    .header("X-Reason", "Entidade não foi encontrada!")
                    .build();

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody @Valid Patrimonio patrimonio,  BindingResult brPatrimonio) {
        try {

            return ResponseEntity
                    .ok(patrimonioService.alterar(id, patrimonio, brPatrimonio));

        } catch (EntityNotFoundException e) {

            return ResponseEntity
                    .notFound()
                    .header("X-Reason", "Entidade não foi encontrada")
                    .build();

        } catch (ValidationException e) {

            return ResponseEntity
                    .unprocessableEntity()
                    .body(MapUtils.mapaDe(brPatrimonio));

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<Object> buscarItensDoPatrimonio(@PathVariable Long id) {
        try {

            return ResponseEntity
                    .ok(patrimonioService.buscarItens(id));

        } catch (EntityNotFoundException e) {

            return ResponseEntity
                    .notFound()
                    .header("X-Reason", "Entidade não foi encontrada")
                    .build();

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
