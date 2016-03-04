package sketch;

/*
 * DisCountRecord.java
 * Description: 用来保存买二赠一活动的数据结构
 */
public class DiscountRecord {
	//商品名称
	private String name;
	//商品数量
	private int number;
	//商品单位
	private String unit;

	public void setDiscountRecord(String str, int num, String unit) {
		this.name = str;
		this.number = num;
		this.unit = unit;
	}

	public void showDiscountRecord() {
		System.out.println("名称：" + this.name + "，数量：" + this.number + this.unit);
	}

}
