package es.geeko.service;

import es.geeko.dto.UsuarioDto;
import es.geeko.model.Usuario;
import es.geeko.repository.UsuarioRepository;
import es.geeko.service.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends AbstractBusinessService<Usuario, Integer, UsuarioDto, UsuarioRepository, UsuarioMapper> {

    public UsuarioService(UsuarioRepository repo, UsuarioMapper mapper) {
        super(repo, mapper);
    }
}