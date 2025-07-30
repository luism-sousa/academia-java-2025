package pt.upacademy.jseproject.maven.model;

import java.util.List;
import java.util.ArrayList;

/*
  	Uma prateleira pode ter 0 ou 1 produto
  	Um produto pode estar presente em 0 ou “n” prateleiras
 */
/* Enunciado da classe:
   Deve possuir:
	- Um código (ID)
	- Uma lista de prateleiras onde está exposto
	- Valor unitário de desconto
	- IVA (Imposto de Valor Acrescentado em percentagem)
	- PVP (Preço de Venda ao Público)
 */

public class Product extends Entity{
	private String name;
	private double discount;
	private int vat;
	private double pvp;
	private List<Long> shelvesId; // Associação

	// Construtor
	public Product(String name, double discount, int vat, double pvp) {
		this.name = name;
		this.discount = discount;
		this.vat = vat;
		this.pvp = pvp;
		this.shelvesId = new ArrayList<>();
	}

	// region Getters	
	public String getName() {
		return name;
	}

	public double getDiscount() {
		return discount;
	}

	public int getVat() {
		return vat;
	}

	public double getPvp() {
		return pvp;
	}
	
	public List<Long> getShelvesId() {
		return shelvesId;
	}
	// endregion

	// region Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}
	
	public void setShelvesId(List<Long> shelvesId) {
		this.shelvesId = shelvesId;
	}
	// endregion
	
	@Override
	public String toString() {
		return "Produto ID: " + id + " | " +
				"Nome Produto: " + name + 
				(!shelvesId.isEmpty() ? " | Colocado na(s) prateleira(s): " + shelvesId.toString() + " " : " | Não colocado em prateleira ") +
				"| Desconto: " + discount + "% " +
				"| IVA: " + vat + "% " +
				"| Preço: " + pvp + "€";
	}
}
