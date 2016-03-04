package sketch;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/*
 * Pay.java
 * Description: 用来保存价格和“买二赠一”优惠记录的数据结构
 */
public class Pay {

	private double payPrice = 0;
	private double savePrice = 0;
	public static double totalSavePrice = 0;
	public static double totalPrice = 0;
	private DiscountRecord disRec = new DiscountRecord();

	static Set<DiscountRecord> discountSet = new HashSet<DiscountRecord>();

	public void setDiscountRecord(String str, int num, String unit) {
		disRec.setDiscountRecord(str, num, unit);
		discountSet.add(disRec);
	}

	public double getPayPrice() {
		return this.payPrice;
	}

	public void setPayPrice(double price) {
		price = this.priceFormat(price);
		this.payPrice = price;
		totalPrice += price;
	}

	public double getSavePrice() {
		return this.savePrice;
	}

	public void setSavePrice(double price) {
		price = this.priceFormat(price);
		this.savePrice = price;
		totalSavePrice += price;
	}

	public void showDiscountRecordSet() {
		if (discountSet.size() >= 1) {
			System.out.println("买二赠一商品：");
			for (DiscountRecord dr : discountSet) {
				dr.showDiscountRecord();
			}
			System.out.println("----------------------");
		}
	}

	/* double型价格保留两位小数 */
	private double priceFormat(double p) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(p)).doubleValue();
	}
}
