package com.pv.louvor.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.FotoDTO;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.storage.FotoStorage;
import com.pv.louvor.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotoResource {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
			
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("file") MultipartFile foto) {	
		Usuario usuario = new Usuario();
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(foto, resultado, fotoStorage, usuario));
		thread.start();
		return resultado;
	}
	
	@PostMapping("/{id}")
	public DeferredResult<FotoDTO> uploadUseNew(@RequestParam("file") MultipartFile foto, @PathVariable Integer id) {	
		Usuario usuario = usuarioRepository.findById(id);
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(foto, resultado, fotoStorage, usuario));
		thread.start();
		return resultado;
	}
	
	@GetMapping("/{nome:.*}")
	public byte[] recuperarFotoTemporaria(@PathVariable String nome) {
		return fotoStorage.recuperarFotoTemporaria(nome);
	}
}
