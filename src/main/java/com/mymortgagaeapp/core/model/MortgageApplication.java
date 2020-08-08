/**
 * 
 */
package com.mymortgagaeapp.core.model;

import java.util.Date;

/**
 * @author Swapnil Dangore
 *
 */
public class MortgageApplication {

	String mortgageId;
	String version;
	String offerId;
	String productId;
	Date offerDate;
	Date createdDate;
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
	public Date getOfferDate() {
		return offerDate;
	}
	/**
	 * @param offerDate the offerDate to set
	 */
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
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
}
