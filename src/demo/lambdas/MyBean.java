package demo.lambdas;

public class MyBean {
	
	private int num;
	private String str;
	private boolean boolVal;
	
	public MyBean(int num, String str, boolean boolVal) {
		super();
		this.num = num;
		this.str = str;
		this.boolVal = boolVal;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public boolean isBoolVal() {
		return boolVal;
	}
	public void setBoolVal(boolean boolVal) {
		this.boolVal = boolVal;
	}

	@Override
	public String toString() {
		return "MyBean [num=" + num + ", str=" + str + ", boolVal=" + boolVal + "]";
	}
	
}
