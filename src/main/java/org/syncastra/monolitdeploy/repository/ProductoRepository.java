package org.syncastra.monolitdeploy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.syncastra.monolitdeploy.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
