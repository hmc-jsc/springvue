package com.dataspring.dataspring.response;

import com.dataspring.dataspring.entity.data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface dataResponse extends JpaRepository<data,String> {
}
