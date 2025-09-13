package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    public List<ListItem> findByIdTransaction(Long idTransaction);
}
