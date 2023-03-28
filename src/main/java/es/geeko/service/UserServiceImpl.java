package es.geeko.service;

import es.geeko.model.Usuario;
import es.geeko.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService, UserDetailsService{

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Integer saveUser(Usuario user) {
        String passwd= user.getClave();
        String encodedPasswod = passwordEncoder.encode(passwd);
        user.setClave(encodedPasswod);
        user = userRepo.save(user);
        return (int) user.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String emilio)
            throws UsernameNotFoundException {
        Optional<Usuario> opt = userRepo.findUsuarioByEmilio(emilio);

        if(opt.isEmpty())
            throw new UsernameNotFoundException("User with email: " +emilio +" not found !");
        else {
            Usuario user = opt.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmilio(),
                    user.getClave(),
                    user.getRoles()
                            .stream()
                            .map(role-> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toSet())
            );
        }

    }

    //Implement checkUserExists
    @Override
    public boolean checkUserExists(String email) {
        Optional<Usuario> opt = userRepo.findUsuarioByEmilio(email);
        return opt.isPresent();
    }
}