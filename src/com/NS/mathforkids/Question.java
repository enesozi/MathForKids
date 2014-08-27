
package com.NS.mathforkids;

public class Question {
	private String QUESTION;
	private String OPTA;
	private String OPTB;
	private String OPTC;
	private String OPTD;
	private String ANSWER;
	public Question()
	{		
		QUESTION="";
		OPTA="";
		OPTB="";
		OPTC="";
		OPTD="";
		ANSWER="";
	}
	public Question(String qUESTION, String oPTA, String oPTB, String oPTC,String oPTD,
			String aNSWER) {
		
		QUESTION = qUESTION;
		OPTA = oPTA;
		OPTB = oPTB;
		OPTC = oPTC;
		OPTD=oPTD;
		ANSWER = aNSWER;
	}
	
	public String getQUESTION() {
		return QUESTION;
	}
	public String getOPTA() {
		return OPTA;
	}
	public String getOPTB() {
		return OPTB;
	}
	public String getOPTC() {
		return OPTC;
	}
	public String getOPTD() {
		return OPTD;
	}
	public String getANSWER() {
		return ANSWER;
	}

	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}
	public void setOPTA(String oPTA) {
		OPTA = oPTA;
	}
	public void setOPTB(String oPTB) {
		OPTB = oPTB;
	}
	public void setOPTC(String oPTC) {
		OPTC = oPTC;
	}
	public void setOPTD(String oPTD) {
		OPTD = oPTD;
	}
	public void setANSWER(String aNSWER) {
		ANSWER = aNSWER;
	}
	
	
}