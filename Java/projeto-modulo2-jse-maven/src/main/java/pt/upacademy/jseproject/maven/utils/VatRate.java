package pt.upacademy.jseproject.maven.utils;

/* 
 * IVA Açores - 4%, 9%, 18%
 * IVA Madeira -  5%, 12%, 22%
 * IVA Continente - 6%, 13%, 23%
 */
public enum VatRate {
	VAT_4(4),
	VAT_5(5),
	VAT_6(6),
	VAT_9(9),
	VAT_12(12),
	VAT_13(13),
	VAT_18(18),
	VAT_22(22),
	VAT_23(23);
	
	private final int valorIVA;
	
	// Construtor
	VatRate(int valorIVA) {
		this.valorIVA = valorIVA;
	}
	
	// Getter
	public int getVatRate() {
		return this.valorIVA;
	}
	
	@Override
	public String toString() {
		return valorIVA + "%";
	}
	
	// Transformar input int em IVA
	public static VatRate fromInt(int valorIVA) {
		for (VatRate vat : VatRate.values()) {
			if (vat.getVatRate() == valorIVA) {
				return vat;
			}
		}
		return null;
	}
}
