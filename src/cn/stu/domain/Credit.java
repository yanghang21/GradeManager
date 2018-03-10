package cn.stu.domain;

public class Credit {
	private long id;
	private long sno;
	private String name;
	private String bj;
	public Credit() {
		// TODO Auto-generated constructor stub
	}
	public Credit(int yw,int sx,int yy,int wl,int hx,int sw) {
		this.yw=yw;
		this.hx=hx;
		this.sx=sx;
		this.wl=wl;
		this.sw=sw;
		this.yy=yy;
		// TODO Auto-generated constructor stub
	}
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
private int yw;
private int sx;
private int yy;
private int wl;
private int hx;
private int sw;
private int _sum;
private int _avg;
private float credit;
private int ywR;
private int sxR;
private int yyR;
private int wlR;
private int hxR;
private int swR;
private int _sumR;
private int _avgR;
private int creditR;
public int getYwR() {
	return ywR;
}
public void setYwR(int ywR) {
	this.ywR = ywR;
}
public int getSxR() {
	return sxR;
}
public void setSxR(int sxR) {
	this.sxR = sxR;
}
public int getYyR() {
	return yyR;
}
public void setYyR(int yyR) {
	this.yyR = yyR;
}
public int getWlR() {
	return wlR;
}
public void setWlR(int wlR) {
	this.wlR = wlR;
}
public int getHxR() {
	return hxR;
}
public void setHxR(int hxR) {
	this.hxR = hxR;
}
public int getSwR() {
	return swR;
}
public void setSwR(int swR) {
	this.swR = swR;
}
public int get_sumR() {
	return _sumR;
}
public void set_sumR(int _sumR) {
	this._sumR = _sumR;
}
public int get_avgR() {
	return _avgR;
}
public void set_avgR(int _avgR) {
	this._avgR = _avgR;
}

public int getCreditR() {
	return creditR;
}
public void setCreditR(int creditR) {
	this.creditR = creditR;
}
private String condition;
public String getCondition() {
	return condition;
}
public void setCondition(String condition) {
	this.condition = condition;
}
public int getYw() {
	return yw;
}
public void setYw(int yw) {
	this.yw = yw;
}
public int getSx() {
	return sx;
}
public void setSx(int sx) {
	this.sx = sx;
}
public int getYy() {
	return yy;
}
public void setYy(int yy) {
	this.yy = yy;
}
public int getWl() {
	return wl;
}
public void setWl(int wl) {
	this.wl = wl;
}
public int getHx() {
	return hx;
}
public void setHx(int hx) {
	this.hx = hx;
}
public int getSw() {
	return sw;
}
public void setSw(int sw) {
	this.sw = sw;
}

public float getCredit() {
	return credit;
}
public void setCredit(float credit) {
	this.credit = credit;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getSno() {
	return sno;
}
public void setSno(long sno) {
	this.sno = sno;
}
public int get_sum() {
	return _sum;
}
public void set_sum(int _sum) {
	this._sum = _sum;
}
public int get_avg() {
	return _avg;
}
public void set_avg(int _avg) {
	this._avg = _avg;
}



}
