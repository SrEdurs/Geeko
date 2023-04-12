package es.geeko.repository;

import es.geeko.model.Chat;
import es.geeko.model.Usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {

Optional<Chat> findChatByIdDestinatarioAndIdRemitente(Long idDestinatario,Long idRemitente);
Optional<Chat> findChatByIdRemitenteAndIdDestinatario(Long idRemitente,Long idDestinatario);

}
