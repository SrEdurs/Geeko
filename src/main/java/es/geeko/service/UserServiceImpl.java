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
        return user.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String emilio)
            throws UsernameNotFoundException {
        System.out.println("jkshdfkjsef");
        Optional<Usuario> opt = userRepo.findUsuarioByEmilio(emilio);
        System.out.println("jkshdfkjsef2");

        if(opt.isEmpty())
            throw new UsernameNotFoundException("User with email: " +emilio +" not found !");
        else {
            System.out.println("jkshdfkjsef3");
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
}