import es.geeko.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Listar {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("geeko");

        EntityManager em = emf.createEntityManager();
        List<Transaccion> listaUsuarios = em.createQuery("select usu from Transaccion usu", Transaccion.class).getResultList();

        listaUsuarios.forEach(System.out::println);
        System.out.println();

        em.close();

    }
}
