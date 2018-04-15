package com.domain;
import com.common.StringUtil;
/**
 * 基因组数据的实例对象 用于存储对象的数据
 * 
 * @author 李君易
 *
 */
public class DataInstance {
	private String LOCUS;
	private String DEFINITION;
	private String ACCESSION;
	private String VERSION;
	private String DBLINK;
	private String KEYWORDS;
	private String SOURCE;
	private String ORGANISM;
	private String COMMENT;
	private String PRIMARY;
	private String FEATURES;
	private String ORIGIN;
	final static String[] INTERCEPTION_ARR = { "LOCUS", "DEFINITION", "ACCESSION", "VERSION", "DBLINK", "KEYWORDS",
			"SOURCE", "ORGANISM", "COMMENT", "PRIMARY", "FEATURES", "ORIGIN" };
	public String getLOCUS() {
		return LOCUS;
	}

	public void setLOCUS(String lOCUS) {
		LOCUS = lOCUS;
	}

	public String getDEFINITION() {
		return DEFINITION;
	}

	public void setDEFINITION(String dEFINITION) {
		DEFINITION = dEFINITION;
	}

	public String getACCESSION() {
		return ACCESSION;
	}

	public void setACCESSION(String aCCESSION) {
		ACCESSION = aCCESSION;
	}

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}

	public String getDBLINK() {
		return DBLINK;
	}

	public void setDBLINK(String dBLINK) {
		DBLINK = dBLINK;
	}

	public String getKEYWORDS() {
		return KEYWORDS;
	}

	public void setKEYWORDS(String kEYWORDS) {
		KEYWORDS = kEYWORDS;
	}

	public String getSOURCE() {
		return SOURCE;
	}

	public void setSOURCE(String sOURCE) {
		SOURCE = sOURCE;
	}

	public String getORGANISM() {
		return ORGANISM;
	}

	public void setORGANISM(String oRGANISM) {
		ORGANISM = oRGANISM;
	}

	public String getCOMMENT() {
		return COMMENT;
	}

	public void setCOMMENT(String cOMMENT) {
		COMMENT = cOMMENT;
	}

	public String getPRIMARY() {
		return PRIMARY;
	}

	public void setPRIMARY(String pRIMARY) {
		PRIMARY = pRIMARY;
	}

	public String getFEATURES() {
		return FEATURES;
	}

	public void setFEATURES(String fEATURES) {
		FEATURES = fEATURES;
	}

	public String getORIGIN() {
		return ORIGIN;
	}

	public void setORIGIN(String oRIGIN) {
		ORIGIN = oRIGIN;
	}

	public DataInstance(String str) {
		this.LOCUS = str.indexOf("LOCUS") != -1
				? str.substring(str.indexOf("LOCUS"), str.indexOf(InterceptionArr(str, "DEFINITION"))) : "";
		this.DEFINITION = str.indexOf("DEFINITION") != -1
				? str.substring(str.indexOf("DEFINITION"), str.indexOf(InterceptionArr(str, "ACCESSION"))) : "";
		this.ACCESSION = str.indexOf("ACCESSION") != -1
				? str.substring(str.indexOf("ACCESSION"), str.indexOf(InterceptionArr(str, "VERSION"))) : "";
		this.VERSION = str.indexOf("VERSION") != -1
				? str.substring(str.indexOf("VERSION"), str.indexOf(InterceptionArr(str, "DBLINK"))) : "";
		this.DBLINK = str.indexOf("DBLINK") != -1
				? str.substring(str.indexOf("DBLINK"), str.indexOf(InterceptionArr(str, "KEYWORDS"))) : "";
		this.KEYWORDS = str.indexOf("KEYWORDS") != -1
				? str.substring(str.indexOf("KEYWORDS"), str.indexOf(InterceptionArr(str, "SOURCE"))) : "";
		this.SOURCE = str.indexOf("SOURCE") != -1
				? str.substring(str.indexOf("SOURCE"), str.indexOf(InterceptionArr(str, "ORGANISM"))) : "";
		this.ORGANISM = str.indexOf("ORGANISM") != -1
				? str.substring(str.indexOf("ORGANISM"), str.indexOf(InterceptionArr(str, "COMMENT"))) : "";
		this.COMMENT = str.indexOf("COMMENT") != -1
				? str.substring(str.indexOf("COMMENT"), str.indexOf(InterceptionArr(str, "PRIMARY"))) : "";
		this.PRIMARY = str.indexOf("PRIMARY") != -1
				? str.substring(str.indexOf("PRIMARY"), str.indexOf(InterceptionArr(str, "FEATURES"))) : "";
		this.FEATURES = str.indexOf("FEATURES") != -1
				? str.substring(str.indexOf("FEATURES"), str.indexOf(InterceptionArr(str, "ORIGIN"))) : "";
		this.ORIGIN = str.indexOf("ORIGIN") != -1 ? dealOrign(str.substring(str.indexOf("ORIGIN"))) : "";
		
		StringUtil stringUtil = new StringUtil();
		this.LOCUS = stringUtil.translation(this.LOCUS);
		this.DEFINITION =stringUtil.translation(this.DEFINITION);
		this.ACCESSION = stringUtil.translation(this.ACCESSION);
		this.VERSION = stringUtil.translation(this.VERSION);
		this.KEYWORDS = stringUtil.translation(this.KEYWORDS);
		this.SOURCE = stringUtil.translation(this.SOURCE);
		this.ORGANISM = stringUtil.translation(this.ORGANISM);
		this.COMMENT = stringUtil.translation(this.COMMENT);
		this.PRIMARY = stringUtil.translation(this.PRIMARY);
		this.FEATURES = stringUtil.translation(this.FEATURES);
		this.DBLINK = stringUtil.translation(this.DBLINK);
		this.ORIGIN = stringUtil.translation(this.ORIGIN);
	}

	public String InterceptionArr(String str, String index) {
		if (str == null || index == null) {
			return "";
		}

		int a = 0;
		for (int i = 0; i < INTERCEPTION_ARR.length; i++) {
			if (index.equals(INTERCEPTION_ARR[i])) {
				a = i;
				break;
			}
		}
		for (int i = a; i <INTERCEPTION_ARR.length; i++) {
			if (str.indexOf(INTERCEPTION_ARR[i]) != -1) {
				return INTERCEPTION_ARR[i];
			}
		}
		if(str.equals("ORIGIN")){
			System.out.println("没有R");
		}
			
		return "";

	}

	public String dealOrign(String str) {
		String res = null;
		res = str.replaceAll("\\d+", "");
		res = res.replaceAll("ORIGIN", "");
		return res;
	}

}
