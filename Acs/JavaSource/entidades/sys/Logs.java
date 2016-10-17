package entidades.sys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="acs_logs")
public class Logs {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private UsuarioEfika usuarioEfika;
	
	private Date dataHora;	
	
	/**
	 * Nome do comando
	 * Ex: Reboot, Factory Reset, SetWifi...
	 **/
	private String comando;	
	
	/**
	 * Designador, Numero de serie, Mac...
	 **/
	private String parametro;
	
	private String valor;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioEfika getUsuarioEfika() {
		return usuarioEfika;
	}

	public void setUsuarioEfika(UsuarioEfika usuarioEfika) {
		this.usuarioEfika = usuarioEfika;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getComando() {
		return comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
}
