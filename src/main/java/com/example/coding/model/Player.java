package com.example.coding.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class Player implements Serializable {

	private static final long serialVersionUID = -5755629173293747602L;

	@JsonProperty("pName")
	private String pName;

	@JsonProperty("pHealth")
	private Integer pHealth;

	@JsonProperty("pGold")
	private Long pGold;

	@JsonProperty("pEnergyDrink")
	private Integer pEnergyDrink;

	@JsonProperty("pShortGun")
	private byte pShortGun;

	@JsonProperty("pBigGun")
	private byte pBigGun;

	@JsonProperty("eName")
	private String eName;

	@JsonProperty("eHealth")
	private Integer eHealth;

	@JsonProperty("eGold")
	private Long eGold;

	@JsonProperty("eEnergyDrink")
	private Integer eEnergyDrink;

	@JsonProperty("eShortGun")
	private byte eShortGun;

	@JsonProperty("eBigGun")
	private byte eBigGun;

	@JsonProperty("gameName")
	private String gameName;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Player() {

	}

	public Player(String pName, Integer pHealth, Long pGold,
			Integer pEnergyDrink, byte pShortGun, byte pBigGun, String eName,
			Integer eHealth, Long eGold, Integer eEnergyDrink, byte eShortGun,
			byte eBigGun) {
		this.pName = pName;
		this.pHealth = pHealth;
		this.pGold = pGold;
		this.pEnergyDrink = pEnergyDrink;
		this.pShortGun = pShortGun;
		this.pBigGun = pBigGun;
		this.eName = eName;
		this.eHealth = eHealth;
		this.eGold = eGold;
		this.eEnergyDrink = eEnergyDrink;
		this.eShortGun = eShortGun;
		this.eBigGun = eBigGun;
	}

	public Integer getpEnergyDrink() {
		return pEnergyDrink;
	}

	public void setpEnergyDrink(Integer pEnergyDrink) {
		this.pEnergyDrink = pEnergyDrink;
	}

	public byte getpShortGun() {
		return pShortGun;
	}

	public void setpShortGun(byte pShortGun) {
		this.pShortGun = pShortGun;
	}

	public byte getpBigGun() {
		return pBigGun;
	}

	public void setpBigGun(byte pBigGun) {
		this.pBigGun = pBigGun;
	}

	public Integer geteEnergyDrink() {
		return eEnergyDrink;
	}

	public void seteEnergyDrink(Integer eEnergyDrink) {
		this.eEnergyDrink = eEnergyDrink;
	}

	public byte geteShortGun() {
		return eShortGun;
	}

	public void seteShortGun(byte eShortGun) {
		this.eShortGun = eShortGun;
	}

	public byte geteBigGun() {
		return eBigGun;
	}

	public void seteBigGun(byte eBigGun) {
		this.eBigGun = eBigGun;
	}

	public Long getpGold() {
		return pGold;
	}

	public void setpGold(Long pGold) {
		this.pGold = pGold;
	}

	public Long geteGold() {
		return eGold;
	}

	public void seteGold(Long eGold) {
		this.eGold = eGold;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getpHealth() {
		return pHealth;
	}

	public void setpHealth(Integer pHealth) {
		this.pHealth = pHealth;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public Integer geteHealth() {
		return eHealth;
	}

	public void seteHealth(Integer eHealth) {
		this.eHealth = eHealth;
	}

	@Override
	public String toString() {
		return "Players Detail: \n 1.Name P1 : " + pName + ", Health P1 : "
				+ pHealth + ", Gold P1 : " + pGold + ", EnergyDrink P1 : "
				+ pEnergyDrink + ", ShortGun P1 : " + pShortGun
				+ ", BigGun P2 : " + pBigGun + " \n 2.Name P2 : " + eName
				+ ", Health P2 : " + eHealth + ", Gold P2 : " + eGold
				+ ", EnergyDrink P2 : " + eEnergyDrink + ", ShortGun P2 : "
				+ eShortGun + ", BigGun P2 : " + eBigGun;
	}
}
