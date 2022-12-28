import es.geeko.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Listar {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("geeko");

        EntityManager em = emf.createEntityManager();
        List<Usuario> listaUsuarios = em.createQuery("select usu from Usuario usu", Usuario.class).getResultList();

        listaUsuarios.forEach(System.out::println);
        System.out.println();

        em.close();

    }
}
