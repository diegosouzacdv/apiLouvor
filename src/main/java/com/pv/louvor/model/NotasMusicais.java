package com.pv.louvor.model;

public enum NotasMusicais {
	
	C(1, "C (Dó Maior)"),
	CbaixoG(11, "C/G (Dó Maior com Sol no baixo)"),
	Dbemol(111, "Db (Ré Bemol)"),
	D(2, "D (Ré Maior)"),
	DbaixoA(22, "D/A (Ré Maior com Lá no baixo)"),
	Ebemol(222, "Eb (Mi Bemol)"),
	E(3, "E (Mi Maior)"),
	EbaixoB(33, "E/B (Mi Maior com Si no baixo)"),
	Fbemol(333, "Fb (Fá Bemol)"),
	F(4, "F (Fá Maior)"),
	FbaixoC(44, "F/C (Fá Maior com Dó no baixo)"),
	Gbemol(444, "Gb (Sol Bemol)"),
	G(5, "G (Sol Maior)"),
	GbaixoD(55, "G/D (Sol Maior com Ré no baixo)"),
	Abemol(555, "Lá Bemol"),
	A(6, "A (Lá Maior)"),
	AbaixoE(66, "A/E (Lá Maior com Mi no baixo)"),
	Bbemol(666, "Bb (Si Bemol)"),
	B(7, "B (Si Maior"),
	Bbaixofsustenido(77, "B/E (Si Maior com Fá Sustenido no baixo)"),
	Cbemol(777, "Cb (Dó Bemol)");
	
	private Integer cod;
	private String descricao;
	
	private NotasMusicais(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static NotasMusicais ToEnum(String desc) {
		if(desc == null) {
			return null;
		}
		
		for (NotasMusicais x : NotasMusicais.values()) {
			if(desc.equals(x.getDescricao())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + desc);
	}
	
}
