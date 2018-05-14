package br.senai.sp.info.pweb.ianes.ws.services;

import br.senai.sp.info.pweb.ianes.ws.dao.PatrimonioDAO;
import br.senai.sp.info.pweb.ianes.ws.exceptions.EntityNotFoundException;
import br.senai.sp.info.pweb.ianes.ws.exceptions.UnauthorizedException;
import br.senai.sp.info.pweb.ianes.ws.exceptions.ValidationException;
import br.senai.sp.info.pweb.ianes.ws.models.Patrimonio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class PatrimonioService {

    @Autowired
    private PatrimonioDAO patrimonioDAO;

    /**
     * Persists a categoriaPatrimonio in dabatase
     * @param patrimonio
     * @param brPatrimonio
     * @return
     * @throws ValidationException
     * @throws UnauthorizedException
     */
    public Patrimonio cadastrar(Patrimonio patrimonio, BindingResult brPatrimonio) throws ValidationException, UnauthorizedException {

        // Trata validacoes
        if (brPatrimonio.hasErrors()) {
            throw new ValidationException();
        }

        // Verifica se a categoria já existe
        Patrimonio patrimonioBuscado = patrimonioDAO.buscarId(patrimonio.getId());
        if (patrimonioBuscado != null) {
            throw new ValidationException("A categoria já existe");
        }

        patrimonioDAO.persistir(patrimonio);
        return patrimonio;

    }

    /**
     * Search by ID a categoriaPatrimonio in database
     * @param id
     * @return
     * @throws EntityNotFoundException
     * @throws UnauthorizedException
     */
    public Patrimonio buscarPorId(Long id) throws EntityNotFoundException, UnauthorizedException {

        Patrimonio categoriaBuscada = patrimonioDAO.buscarId(id);
        if (categoriaBuscada == null) {
            throw new EntityNotFoundException();
        }

        return categoriaBuscada;
    }

    /**
     * Search all categoriaPatrimonio in database
     * @return
     * @throws UnauthorizedException
     */
    public List<Patrimonio> buscarTodos() throws UnauthorizedException {
        return patrimonioDAO.buscarTodos();
    }

    /**
     * Delete a categoriaPatrimonio in database
     * @param id
     * @throws EntityNotFoundException
     * @throws UnauthorizedException
     */
    public void deletar(Long id) throws EntityNotFoundException, UnauthorizedException {

        Patrimonio categoriaBuscada = patrimonioDAO.buscarId(id);
        if (categoriaBuscada == null) {
            throw new EntityNotFoundException();
        }

        patrimonioDAO.deletar(categoriaBuscada);
    }

    /**
     * Update categoriaPatrimonio in database
     * @param patrimonio
     * @throws UnauthorizedException
     */
    public void alterar(Patrimonio patrimonio) throws UnauthorizedException {
        patrimonioDAO.alterar(patrimonio);
    }

}