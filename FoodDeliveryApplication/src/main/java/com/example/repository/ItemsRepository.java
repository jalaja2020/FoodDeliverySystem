package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Items;
import java.lang.Integer;
import java.util.List;
import java.util.Optional;
@Repository
public interface  ItemsRepository extends JpaRepository<Items, Integer>{

	Optional<List<Items>> findByVid(Integer vid);
	@Query( "select o from Items o where id in :ids" )
	List<Items> findByItemIds(@Param("ids") List<Integer> itemIds);
}