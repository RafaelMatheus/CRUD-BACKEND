package crud.crud.services;

import crud.crud.models.Usuario;
import crud.crud.repositories.UsuarioRepository;
import crud.crud.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = repo.findByEmail(email);
        if(u==null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(u.getMatricula(),u.getEmail(),u.getSenha());
    }
}