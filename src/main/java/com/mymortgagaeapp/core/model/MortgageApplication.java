/**
 * 
 */
package com.mymortgagaeapp.core.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mymortgagaeapp.core.validation.OfferDate;

/**
 * @author Swapnil Dangore
 *
 */
public class MortgageApplication {

	@NotNull
	String mortgageId;
	@NotNull
	String version;
	@NotNull
	String offerId;
	@NotNull
	String productId;
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy")
	@OfferDate
	String offerDate;
	@NotNull
	@JsonFormat(pattern="dd/MM/yyyy")	
	String createdDate;
	boolean offerExpired;
	/**
	 * @return the mortgageId
	 */
	public String getMortgageId() {
		return mortgageId;
	}
	/**
	 * @param mortgageId the mortgageId to set
	 */
	public void setMortgageId(String mortgageId) {
		this.mortgageId = mortgageId;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the offerId
	 */
	public String getOfferId() {
		return offerId;
	}
	/**
	 * @param offerId the offerId to set
	 */
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the offerDate
	 */
	public String getOfferDate() {
		return offerDate;
	}
	/**
	 * @param offerDate the offerDate to set
	 */
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the offerExpired
	 */
	public boolean isOfferExpired() {
		return offerExpired;
	}
	/**
	 * @param offerExpired the offerExpired to set
	 */
	public void setOfferExpired(boolean offerExpired) {
		this.offerExpired = offerExpired;
	}
	@Override
	public String toString() {
		return "MortgageApplication [mortgageId=" + mortgageId + ", version=" + version + ", offerId=" + offerId
				+ ", productId=" + productId + ", offerDate=" + offerDate + ", createdDate=" + createdDate
				+ ", offerExpired=" + offerExpired + "]";
	}

}
