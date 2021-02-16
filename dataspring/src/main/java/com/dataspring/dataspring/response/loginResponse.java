package com.dataspring.dataspring.response;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dataspring.dataspring.entity.login;

public interface loginResponse extends JpaRepository<login,String> {
}
