package com.models.Documents;


import java.util.List;
import com.dataObjects.PurchaseHistoryVO;
public class PurchaseHistoryKeyBasedDocument  extends KeyBasedDocument{

	private List<PurchaseHistoryVO>PurchaseHistoryVO;

	public List<PurchaseHistoryVO> getPurchaseHistoryVO() {
		return PurchaseHistoryVO;
	}
	public void setPurchaseHistoryVO(List<PurchaseHistoryVO> purchaseHistoryVO) {
		PurchaseHistoryVO = purchaseHistoryVO;
	}



	

	
}
