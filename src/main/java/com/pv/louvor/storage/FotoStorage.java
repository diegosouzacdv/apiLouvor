package com.pv.louvor.storage;

import org.springframework.web.multipart.MultipartFile;

import com.pv.louvor.model.Usuario;
import com.pv.louvor.security.UserSS;

public interface FotoStorage {
	public String salvarTemporariamente(MultipartFile file, UserSS user, Usuario usuario);

	public byte[] recuperarFotoTemporaria(String nome);

}
