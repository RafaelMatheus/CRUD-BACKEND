package crud.crud.services;

import crud.crud.models.Usuario;
import crud.crud.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder cripto;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario insert(Usuario obj){
        obj.setMatricula(null);
        obj.setSenha(cripto.encode(obj.getSenha()));
        return usuarioRepository.save(obj);
    }

}
