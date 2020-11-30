package com.uniovi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Legend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158396882291048862L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long id;
	
	@Column private String label;
	@Column private String fill;
	
	public Legend() {}
	
	public Legend(String colorLegend, String labelLegend) {
		this.fill =colorLegend;
		this.label=labelLegend;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getFill() {
		return fill;
	}
	public void setFill(String fill) {
		this.fill = fill;
	}
	@Override
	public String toString() {
		return "Leyenda [id=" + id + ", label=" + label + ", fill=" + fill + "]";
	}
	
	
}
