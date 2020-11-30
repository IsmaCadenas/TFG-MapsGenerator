package com.uniovi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UploadMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8277986502316132622L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long id;
	@Transient
	private String inputAreas;
	@Transient
	private String inputJS;
	@Column
	private String name;
	@Column
	private String nameUser;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInputJS() {
		return inputJS;
	}

	public void setInputJS(String inputJS) {
		this.inputJS = inputJS;
	}

	public String getInputAreas() {
		return inputAreas;
	}

	public void setInputAreas(String inputAreas) {
		this.inputAreas = inputAreas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UploadMapDTO [inputAreas=" + inputAreas + ", inputJS=" + inputJS + ", name=" + name + ", nameUser="
				+ nameUser + "]";
	}
	
}
