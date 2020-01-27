package com.pv.louvor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pv.louvor.model.VersaoApp;

@Repository
public interface VersaoRepository  extends JpaRepository<VersaoApp, Integer>{


}