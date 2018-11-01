package com.ry.szq.pojo;

public class Trade {
	private int id;
	private String time ;
	private String dn ;
	private String gskCount ;
	private String tradeCount ;
	private String clName ;
	private String csID ;
	private String contract ;
	private String qwsy ;
	private String pcyk;
	private String ccyk ;
	private String fdsy;
	private String zxj;
	private String dcc;
	private String kcc;
	private String sxf ;
	public Trade(){}
	public Trade( String time, String dn, String gskCount, String tradeCount, String clName, String csID, String contract,
			String qwsy, String pcyk, String ccyk, String fdsy, String zxj, String dcc, String kcc, String sxf) {
		super();
		this.time = time;
		this.dn = dn;
		this.gskCount = gskCount;
		this.tradeCount = tradeCount;
		this.clName = clName;
		this.csID = csID;
		this.contract = contract;
		this.qwsy = qwsy;
		this.pcyk = pcyk;
		this.ccyk = ccyk;
		this.fdsy = fdsy;
		this.zxj = zxj;
		this.dcc = dcc;
		this.kcc = kcc;
		this.sxf = sxf;
	}
	public Trade(int id, String time, String dn, String gskCount, String tradeCount, String clName, String csID, String contract,
			String qwsy, String pcyk, String ccyk, String fdsy, String zxj, String dcc, String kcc, String sxf) {
		super();
		this.id = id;
		this.time = time;
		this.dn = dn;
		this.gskCount = gskCount;
		this.tradeCount = tradeCount;
		this.clName = clName;
		this.csID = csID;
		this.contract = contract;
		this.qwsy = qwsy;
		this.pcyk = pcyk;
		this.ccyk = ccyk;
		this.fdsy = fdsy;
		this.zxj = zxj;
		this.dcc = dcc;
		this.kcc = kcc;
		this.sxf = sxf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getGskCount() {
		return gskCount;
	}
	public void setGskCount(String gskCount) {
		this.gskCount = gskCount;
	}
	public String getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(String tradeCount) {
		this.tradeCount = tradeCount;
	}
	public String getClName() {
		return clName;
	}
	public void setClName(String clName) {
		this.clName = clName;
	}
	public String getCsID() {
		return csID;
	}
	public void setCsID(String csID) {
		this.csID = csID;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getQwsy() {
		return qwsy;
	}
	public void setQwsy(String qwsy) {
		this.qwsy = qwsy;
	}
	public String getPcyk() {
		return pcyk;
	}
	public void setPcyk(String pcyk) {
		this.pcyk = pcyk;
	}
	public String getCcyk() {
		return ccyk;
	}
	public void setCcyk(String ccyk) {
		this.ccyk = ccyk;
	}
	public String getFdsy() {
		return fdsy;
	}
	public void setFdsy(String fdsy) {
		this.fdsy = fdsy;
	}
	public String getZxj() {
		return zxj;
	}
	public void setZxj(String zxj) {
		this.zxj = zxj;
	}
	public String getDcc() {
		return dcc;
	}
	public void setDcc(String dcc) {
		this.dcc = dcc;
	}
	public String getKcc() {
		return kcc;
	}
	public void setKcc(String kcc) {
		this.kcc = kcc;
	}
	public String getSxf() {
		return sxf;
	}
	public void setSxf(String sxf) {
		this.sxf = sxf;
	}
	@Override
	public String toString() {
		return "Trade [id=" + id + ", time=" + time + ", dn=" + dn + ", gskCount=" + gskCount + ", tradeCount=" + tradeCount + ", clName="
				+ clName + ", csID=" + csID + ", contract=" + contract + ", qwsy=" + qwsy + ", pcyk=" + pcyk + ", ccyk=" + ccyk + ", fdsy="
				+ fdsy + ", zxj=" + zxj + ", dcc=" + dcc + ", kcc=" + kcc + ", sxf=" + sxf + "]";
	}
	
	
	
}
