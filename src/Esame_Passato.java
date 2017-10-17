/**
 * This class is used to explain the mark get by student.
 * 
 * @author Lorenzo Stacchio
 *
 */
public class Esame_Passato {
	// private variables
	private String nomeMateria;
	private int voto;
	private int cfu;
	private String date;

	/**
	 * Empty constructor
	 */
	public Esame_Passato() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Classi constructor
	 * 
	 * @param nomeMateria
	 *            name of subject
	 * @param voto
	 *            mark as integer
	 * @param cfu
	 *            weight of mark
	 */
	public Esame_Passato(String nomeMateria, int cfu, String date,int voto) {
		this.nomeMateria = nomeMateria;
		this.voto = voto;
		this.cfu = cfu;
		this.date=date;
	}

	// GETTERS AND SETTER
	public String getNomeMateria() {
		return nomeMateria;
	}

	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	/**
	 * Classic method to String
	 */
	public String toString() {
		return "Materia: "+ this.nomeMateria+ "\nVoto: "+ this.voto 
				+"\nCfu "+ this.cfu + "\nData firma: "+this.date;
	}

}
