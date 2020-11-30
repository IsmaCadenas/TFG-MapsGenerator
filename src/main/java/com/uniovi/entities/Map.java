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
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6674062842624776151L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long id;
	
	@Column private String name;
	@Column private String background;
	@Column private String borderColor;
	@Transient private String area1fill;
	@Column private String namecode;
	@Transient private String legendInput;
	@Column private String legendTitle;
	@Column private String legendTitleSize;
	@Column private String legendTitleFont;
	@Column private String legendTitleColor;
	@Column private String legendLabelSize;
	@Column private String legendLabelFont;
	@Column private String legendLabelColor;
	@Transient private String edited;
	@Transient private String editedLegend;
	@Column private String predefinedColor;
	@Column private String borderWidth;
	@Column private String backgroundLegendColor;
	@Column private String borderLegendColor;
	@Column private String borderLegendWidth;
	@Column private String borderLegendRadio;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name="map_user"
		,joinColumns=@JoinColumn(name="map_id")
		,inverseJoinColumns=@JoinColumn(name="user_id"))
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="map_areas"
		,joinColumns=@JoinColumn(name="map_id")
		,inverseJoinColumns=@JoinColumn(name="area_id"))
	private Set<Area> areas;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="map_legend"
		,joinColumns=@JoinColumn(name="map_id")
		,inverseJoinColumns=@JoinColumn(name="legend_id"))
	private Set<Legend> legend;

	
	public Map() {
		setBackground("#FFFFFF");
		setBorderColor("#000000");
		setPredefinedColor("#c2c1c1");
		setBorderWidth("0.4");
		setBorderLegendRadio("5px");
		setBorderLegendWidth("5px");
		setBackgroundLegendColor("#FFFFFF");
		setBorderLegendColor("#FFFFFF");
		setLegendInput("");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Legend> getLegend() {
		return legend;
	}

	public void setLegend(Set<Legend> legend) {
		this.legend = legend;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getArea1fill() {
		return area1fill;
	}

	public void setArea1fill(String area1fill) {
		this.area1fill = area1fill;
	}

	public User getUser() {
		return user;
	}

	public String getLegendTitle() {
		return legendTitle;
	}

	public void setLegendTitle(String legendTitle) {
		this.legendTitle = legendTitle;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public String getNamecode() {
		return namecode;
	}

	public void setNamecode(String namecode) {
		this.namecode = namecode;
	}
	public String getLegendInput() {
		return legendInput;
	}

	public void setLegendInput(String legendInput) {
		this.legendInput = legendInput;
	}

	public String getLegendTitleSize() {
		return legendTitleSize;
	}

	public void setLegendTitleSize(String legendTitleSize) {
		this.legendTitleSize = legendTitleSize;
	}

	public String getLegendTitleFont() {
		return legendTitleFont;
	}

	public void setLegendTitleFont(String legendTitleFont) {
		this.legendTitleFont = legendTitleFont;
	}

	public String getLegendTitleColor() {
		return legendTitleColor;
	}

	public void setLegendTitleColor(String legendTitleColor) {
		this.legendTitleColor = legendTitleColor;
	}

	public String getLegendLabelSize() {
		return legendLabelSize;
	}

	public void setLegendLabelSize(String legendLabelSize) {
		this.legendLabelSize = legendLabelSize;
	}

	public String getLegendLabelFont() {
		return legendLabelFont;
	}

	public void setLegendLabelFont(String legendLabelFont) {
		this.legendLabelFont = legendLabelFont;
	}

	public String getLegendLabelColor() {
		return legendLabelColor;
	}

	public void setLegendLabelColor(String legendLabelColor) {
		this.legendLabelColor = legendLabelColor;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getEditedLegend() {
		return editedLegend;
	}

	public void setEditedLegend(String editedLegend) {
		this.editedLegend = editedLegend;
	}

	public String getPredefinedColor() {
		return predefinedColor;
	}

	public void setPredefinedColor(String predefinedColor) {
		this.predefinedColor = predefinedColor;
	}

	public String getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(String borderWidth) {
		this.borderWidth = borderWidth;
	}

	public String getBackgroundLegendColor() {
		return backgroundLegendColor;
	}

	public void setBackgroundLegendColor(String backgroundLegendColor) {
		this.backgroundLegendColor = backgroundLegendColor;
	}

	public String getBorderLegendColor() {
		return borderLegendColor;
	}

	public void setBorderLegendColor(String borderLegendColor) {
		this.borderLegendColor = borderLegendColor;
	}

	public String getBorderLegendWidth() {
		return borderLegendWidth;
	}

	public void setBorderLegendWidth(String borderLegendWidth) {
		this.borderLegendWidth = borderLegendWidth;
	}

	public String getBorderLegendRadio() {
		return borderLegendRadio;
	}

	public void setBorderLegendRadio(String borderLegendRadio) {
		this.borderLegendRadio = borderLegendRadio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Map [id=" + id + ", name=" + name + ", background=" + background + ", borderColor=" + borderColor
				+ ", area1fill=" + area1fill + ", namecode=" + namecode + ", legendInput=" + legendInput
				+ ", legendTitle=" + legendTitle + ", legendTitleSize=" + legendTitleSize + ", legendTitleFont="
				+ legendTitleFont + ", legendTitleColor=" + legendTitleColor + ", legendLabelSize=" + legendLabelSize
				+ ", legendLabelFont=" + legendLabelFont + ", legendLabelColor=" + legendLabelColor + ", edited="
				+ edited + ", editedLegend=" + editedLegend + ", predefinedColor=" + predefinedColor + ", borderWidth="
				+ borderWidth + ", backgroundLegendColor=" + backgroundLegendColor + ", borderLegendColor="
				+ borderLegendColor + ", borderLegendWidth=" + borderLegendWidth + ", borderLegendRadio="
				+ borderLegendRadio + ", user=" + user + ", areas=" + areas + ", legend=" + legend + "]";
	}

	

}
