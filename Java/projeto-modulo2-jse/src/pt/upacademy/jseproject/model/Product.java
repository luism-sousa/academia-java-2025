package pt.upacademy.jseproject.model;

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
	private static long count = 0;

	private Long id;
	private String name;
	private float discount;
	private int vat;
	private float pvp;
	private List<Long> shelvesId; // Associação

	// Construtor
	public Product(String name, float discount, int vat, float pvp) {
		this.id = count++;
		this.name = name;
		this.discount = discount;
		this.vat = vat;
		this.pvp = pvp;
		this.shelvesId = new ArrayList<Long>();
	}

	// region Getters
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public float getDiscount() {
		return discount;
	}

	public int getVat() {
		return vat;
	}

	public float getPvp() {
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
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public void setPvp(float pvp) {
		this.pvp = pvp;
	}
	
	public void setShelvesId(List<Long> shelvesId) {
		this.shelvesId = shelvesId;
	}
	// endregion
}
