package pt.upacademy.jseproject.maven.DTO;

import java.util.List;

import pt.upacademy.jseproject.maven.utils.VatRate;

public class ProductDTO {
	private Long id;
	private String name;
	private double discount;
	private VatRate vat;
	private double pvp;
	private List<Long> shelvesId;
	
	//region Getters
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getDiscount() {
		return discount;
	}
	public VatRate getVat() {
		return vat;
	}
	public double getPvp() {
		return pvp;
	}
	public List<Long> getShelvesId() {
		return shelvesId;
	}
	//endregion
	
	//region Setters
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public void setVat(VatRate vat) {
		this.vat = vat;
	}
	public void setPvp(double pvp) {
		this.pvp = pvp;
	}
	public void setShelvesId(List<Long> shelvesId) {
		this.shelvesId = shelvesId;
	}	
	//endregion
}
