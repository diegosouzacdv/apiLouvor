package com.pv.louvor.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.pv.louvor.model.dto.FotoDTO;
import com.pv.louvor.security.UserSS;
import com.pv.louvor.services.UserService;

public class FotoStorageRunnable implements Runnable{
	
	private MultipartFile file;
	private DeferredResult<FotoDTO> resultado;
	private FotoStorage fotoStorage;
	private UserSS user = UserService.authenticated();

	@Value("${img.profile.size}")
	private Integer size;
	

	public FotoStorageRunnable(MultipartFile file, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
		this.file = file;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
	}
	
	@Override
	public void run() {
		String nomeFoto = this.fotoStorage.salvarTemporariamente(file, user);
		String contentType = file.getContentType();
		resultado.setResult(new FotoDTO(nomeFoto, contentType));
	}

}
