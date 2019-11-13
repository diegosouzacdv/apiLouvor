package com.pv.louvor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.pv.louvor.storage.FotoStorage;
import com.pv.louvor.storage.local.FotoStorageLocal;

@Configuration
@EnableScheduling
public class WebConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}

}
