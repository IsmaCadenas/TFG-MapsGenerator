package com.uniovi.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MasterMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4411037662925268580L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long id;
	
	@Column private String namecode;
	@Column private String nameMap;
	@Column private String width;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="mmap_mareas"
		,joinColumns=@JoinColumn(name="mmap_id")
		,inverseJoinColumns=@JoinColumn(name="marea_id"))
	private Set<MasterArea> areas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamecode() {
		return namecode;
	}

	public void setNamecode(String namecode) {
		this.namecode = namecode;
	}

	public Set<MasterArea> getAreas() {
		return areas;
	}

	public void setAreas(Set<MasterArea> areas) {
		this.areas = areas;
	}

	public String getNameMap() {
		return nameMap;
	}

	public void setNameMap(String nameMap) {
		this.nameMap = nameMap;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "MasterMap [id=" + id + ", namecode=" + namecode + ", nameMap=" + nameMap + ", width=" + width
				+ ", areas=" + areas + "]";
	}
	
	

}
