package com.lapatronaspring.lapatronaspring.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lapatronaspring.lapatronaspring.models.Suministro;
import com.lapatronaspring.lapatronaspring.models.Usuario;
public interface  SuministroRepositorio extends JpaRepository<Suministro,Long>{
    
}
