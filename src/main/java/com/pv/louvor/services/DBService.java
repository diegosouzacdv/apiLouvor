package com.pv.louvor.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Categoria;
import com.pv.louvor.model.Equipe;
import com.pv.louvor.model.Estudo;
import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Grupo;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Musica;
import com.pv.louvor.model.MusicaRepertorio;
import com.pv.louvor.model.NotasMusicais;
import com.pv.louvor.model.Perfil;
import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.Tutorial;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.repositories.CategoriaRepository;
import com.pv.louvor.repositories.FuncaoRepository;
import com.pv.louvor.repositories.GrupoRepository;
import com.pv.louvor.repositories.IgrejaRepository;
import com.pv.louvor.repositories.MusicaRepertorioRepository;
import com.pv.louvor.repositories.MusicaRepository;
import com.pv.louvor.repositories.RepertorioRepository;
import com.pv.louvor.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
//@Autowired
//private FuncaoRepository funcaoRepository;
	
	@Autowired
	private IgrejaRepository igrejaRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RepertorioRepository repertorioRepository;
	
	@Autowired
	private MusicaRepertorioRepository musicaRepertorioRepository;
	
	@Autowired
	private FuncaoRepository fucaoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	

	public void instantiateTestDatabase() {
		
		
				//Categorias
				Categoria c1 = new Categoria(null, "Rápida");
				Categoria c2 = new Categoria(null, "Média-Rápida");
				Categoria c3 = new Categoria(null, "Média");
				Categoria c4 = new Categoria(null, "Média-Lenta");
				Categoria c5 = new Categoria(null, "Lenta");
				categoriaRepository.save(Arrays.asList(c1, c2, c3, c4, c5));
				
				//Funções
				Funcao f1 = new Funcao(null, "Ministro");
				Funcao f2 = new Funcao(null, "Violonista");
				Funcao f3 = new Funcao(null, "Guitarrista");
				Funcao f4 = new Funcao(null, "Baterista");
				Funcao f5 = new Funcao(null, "Tecladista");
				Funcao f6 = new Funcao(null, "Baixista");
				fucaoRepository.save(Arrays.asList(f1, f2,f3,f4,f5,f6));
						
				//Igrejas
				Igreja i1 = new Igreja(null, "Águas Claras");
				i1.setAtivo(true);
				Igreja i2 = new Igreja(null, "Samambaia");
				i2.setAtivo(true);
				Igreja i3 = new Igreja(null, "Sede");
				i3.setAtivo(true);
				igrejaRepository.save(Arrays.asList(i1, i2, i3));
				
				//Grupo
				Grupo g1 = new Grupo(null, "Avivah", true, i3, true);
				g1.setAtivo(true);
				Grupo g2 = new Grupo(null, "HillSong", true, i3, true);
				g2.setAtivo(true);
				Grupo g3 = new Grupo(null, "Elevation Worship", true, i3, true);
				g3.setAtivo(true);
				grupoRepository.save(Arrays.asList(g1, g2, g3));
				
				//Tutorial
				Tutorial t1 = new Tutorial();
				t1.setBaixo(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
				t1.setBateria(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
				t1.setGuitarra(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
				t1.setTeclado(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
				t1.setViolao(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
				
				Tutorial t2 = new Tutorial();
				t2.setBaixo(Arrays.asList("https://www.youtube.com/watch?v=KhAnkqL7tAY"));
				t2.setBateria(Arrays.asList("https://www.youtube.com/watch?v=LGHb2XBSiKU"));
				t2.setGuitarra(Arrays.asList("https://www.youtube.com/watch?v=fGCZdgTD4fA","https://www.youtube.com/watch?v=P97IMlDUnXE"));
				t2.setTeclado(Arrays.asList("https://www.youtube.com/watch?v=k0WzVgbJo-Y"));
				t2.setViolao(Arrays.asList("https://www.youtube.com/watch?v=JGF90fRs6AU"));
				
				Tutorial t3 = new Tutorial();
				t3.setBaixo(Arrays.asList("https://www.youtube.com/watch?v=EtoC2eQzK2k"));
				t3.setBateria(Arrays.asList("https://www.youtube.com/watch?v=ceEdgSkA4K4"));
				t3.setGuitarra(Arrays.asList("https://www.youtube.com/watch?v=66nCNiTKHw0"));
				t3.setTeclado(Arrays.asList("https://www.youtube.com/watch?v=zfod4Ptz2YM"));
				t3.setViolao(Arrays.asList("https://www.youtube.com/watch?v=s7bHhQGsfRs"));
				
				//Estudo
				Estudo e1 = new Estudo("https://drive.google.com/open?id=0BzCIxMGAHmIkQU9idERUenN3QXM", "https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms", 
						120, "https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW", 
						"https://drive.google.com/open?id=1Ht2BfGhoO62aMOH_6Bz_GWmGbjdrVBsb");
				
				Estudo e2 = new Estudo("https://drive.google.com/open?id=1EsSBBZLVmo1-Fd1EDZBuQ0iajYkvriii", "https://drive.google.com/open?id=1EsSBBZLVmo1-Fd1EDZBuQ0iajYkvriii", 
						120, "https://drive.google.com/open?id=1chy87_IqHqv8nQ7832RLHkCVAhhR2NxM", 
						"https://drive.google.com/open?id=1ScG9VK3Qkjl1LcxEzc5CmUf3toWvETfp");
				
				Estudo e3 = new Estudo("https://drive.google.com/open?id=1djHEE1Ckj_Cr4euBdnIEjV2oVG4DLMZK", "https://drive.google.com/open?id=1wcoKs532Txm_WGHemwpOmjDWtO6PfCA_", 
						120, "https://drive.google.com/open?id=1qvcoslLe_duqf2i8EZRgkKKjsPofP4xZ", 
						"https://drive.google.com/open?id=1IqDwsBCRjOcwuK2lIXBXuow4LjI7uCcm");	
				
				//NotasMusicais
				
				//Musica
				Musica m1 = new Musica(null, "O Senhor é Bom", NotasMusicais.D, NotasMusicais.D, true);
				m1.setEstudo(e1);
				m1.setDataInserida(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
				m1.setGrupo(g1);
				m1.setCategorias(c1);
				m1.setTutorial(t1);	
				
				Musica m2 = new Musica(null, "Broken Vessels", NotasMusicais.G, NotasMusicais.E, true);
				m2.setEstudo(e2);
				m2.setDataInserida("2018");
				m2.setGrupo(g2);
				m2.setCategorias(c5);
				m2.setTutorial(t2);
				
				Musica m3 = new Musica(null, "Here Again", NotasMusicais.D, NotasMusicais.D, true);
				m3.setEstudo(e3);
				m3.setDataInserida(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
				m3.setGrupo(g3);
				m3.setCategorias(c4);
				m3.setTutorial(t3);	
				musicaRepository.save(Arrays.asList(m1,m2,m3));
				
				
				//Usuario
				Usuario u1 = new Usuario( null, "Diego", "(xx)xxxxx-xxxx", "diegoguitaibanez@gmail.com", pe.encode("godemais"));
				Usuario u2 = new Usuario( null, "Admin", "(61)98576-9860", "admin@gmail.com", pe.encode("admin"));
				Usuario u3 = new Usuario( null, "Sede", "(61)98576-9860", "sede@gmail.com", pe.encode("sede"));
				u3.addPerfil(Perfil.ADMIN);
				u2.addPerfil(Perfil.ADMIN);
				u1.setFuncao(Arrays.asList(f3,f1,f4,f5));
				u2.setFuncao(Arrays.asList(f1,f2,f3,f6));
				u1.setIgreja(i1);
				u2.setIgreja(i1);
				u3.setIgreja(i3);
				u1.setDisponivel(false);
				u2.setDisponivel(true);
				usuarioRepository.save(Arrays.asList(u1, u2, u3));
				
				//Equipe
				Equipe eq1 = new Equipe();
				eq1.setBaterista(Arrays.asList(u1.getPessoa().getNome()));
				eq1.setGuitarrista(Arrays.asList(u2.getPessoa().getNome()));
				eq1.setMinistro(Arrays.asList(u1.getPessoa().getNome(), u2.getPessoa().getNome()));
				eq1.setTecladista(Arrays.asList(u2.getPessoa().getNome()));
				eq1.setViolonista(Arrays.asList(u2.getPessoa().getNome()));
				eq1.setBaixista(Arrays.asList(u2.getPessoa().getNome()));
				

				//Repertorio
				Repertorio r1 = new Repertorio();
				r1.setData("25/08/2019");
				r1.setDataSemana("Domingo");
				r1.setCriador(u1.getPessoa().getNome());
				//r1.setEquipeDoDia(eq1);
				r1.setAtivo(true);
				r1.setIgreja(i1);
				
				Repertorio r2 = new Repertorio();
				r2.setData("21/08/2019");
				r2.setDataSemana("Quarta-Feira");
				r2.setCriador(u1.getPessoa().getNome());
				//r2.setEquipeDoDia(eq1);
				r2.setAtivo(true);
				r2.setIgreja(i2);
				
				
				repertorioRepository.save(Arrays.asList(r1,r2));

				//MusicaRepertorio
				MusicaRepertorio ms1 = new MusicaRepertorio(r1, m1,null);
				MusicaRepertorio ms2 = new MusicaRepertorio(r1, m2,null);
				MusicaRepertorio ms3 = new MusicaRepertorio(r1, m3,null);
				
				MusicaRepertorio ms4 = new MusicaRepertorio(r2, m1,null);
				MusicaRepertorio ms5 = new MusicaRepertorio(r2, m2,null);
				MusicaRepertorio ms6 = new MusicaRepertorio(r2, m3,null);
				
				r1.getMusicasRepertorio().addAll(Arrays.asList(ms1,ms2,ms3));
				r2.getMusicasRepertorio().addAll(Arrays.asList(ms4,ms5,ms6));
				m1.getMusicasRepertorio().addAll(Arrays.asList(ms1,ms2,ms3));
				m2.getMusicasRepertorio().addAll(Arrays.asList(ms4,ms5,ms6));
				

				musicaRepertorioRepository.save(Arrays.asList(ms1,ms2,ms3,ms4,ms5,ms6));
				}

		
	}

