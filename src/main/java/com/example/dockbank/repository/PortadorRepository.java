package com.example.dockbank.repository;

import com.example.dockbank.model.Portador;
import com.example.dockbank.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PortadorRepository extends JpaRepository<Portador, Long> {

    boolean existsByCpf(String cpf);

    Optional<Portador> findByCpf(String cpf);

    void deleteByCpf(String cpf);

    @Query(nativeQuery = true, value = """
				SELECT portadores.cpf AS username, portadores.password, tb_role.id AS roleId, tb_role.authority
				FROM portadores
				INNER JOIN tb_user_role ON portadores.id = tb_user_role.user_id
				INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
				WHERE portadores.cpf = :cpf
			""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String cpf);
}
