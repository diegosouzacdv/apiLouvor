package com.pv.louvor.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.pv.louvor.model.Musica;
import com.pv.louvor.security.UserSS;
import com.pv.louvor.services.exceptions.NoSuchFileException;
import com.pv.louvor.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage{
	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getProperty("user.home"), ".louvorfotos"));
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}

	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			
			System.err.println(this.local.toAbsolutePath());
			
			if(logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar fotos.");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
				logger.debug("Pasta temporária: " + this.local.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro criado pasta para salvar foto", e);
		}
		
	}

	@Override
	public String salvarTemporariamente(MultipartFile file,  UserSS user) {
		criarPastas();
		String novoNome = null;
		if(file != null) {
			MultipartFile arquivo = file;
			novoNome = user.getUsername() +"_"+ user.getId()+".jpg";
			try {
				arquivo.transferTo(new File(this.local.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
			}			
		}
		return novoNome;
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		System.err.println("entrando");
		
		try {
			if(Files.readAllBytes(this.local.resolve(nome)) != null) {
				return Files.readAllBytes(this.local.resolve(nome));
			} else {
				return null;
			}
		} catch (IOException e) {
			
			throw new NoSuchFileException("Foto na encontrada! Id: ");
		}
	}
	
	/*private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		System.err.println(novoNome);
		return novoNome;
	}*/
}
