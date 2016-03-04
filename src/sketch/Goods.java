package sketch;

import java.text.DecimalFormat;

public class Goods {
	private String name;
	private String unit;
	private double price;
	private String typeName;

	private boolean promotion3for2 = false;
	private boolean promotionDiscount = false;

	private final double DISCOUNT = 0.95;


	public Goods(String name, String unit, double price, boolean pro3for2, boolean proDiscount) {
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.promotion3for2 = pro3for2;
		this.promotionDiscount = proDiscount;

		if (pro3for2) {
			this.typeName = "买二赠一";
		} else if (proDiscount) {
			this.typeName = "95折";
		} else {
			this.typeName = "暂无优惠";
		}

	}

	public Pay pay(int num) {
		double payPrice = 0;
		double savePrice = 0;

		Pay pay = new Pay();

		if (this.promotion3for2) {
			// 买二赠一优惠计算
			payPrice = price * (num - (num / 3));
			savePrice = price * (num / 3);
			pay.setDiscountRecord(this.name, (num / 3), this.unit);
		} else if (this.promotionDiscount) {
			// 95折优惠计算
			payPrice = price * num * DISCOUNT;
			savePrice = price * num * (1 - DISCOUNT);
		} else {
			payPrice = price * num;
		}
		pay.setPayPrice(payPrice);
		pay.setSavePrice(savePrice);
		this.printGoodsList(payPrice, savePrice, num);
		return pay;
	}
	
	/*
	 * Description: 打印购买详细小单
	 * 
	 */
	private void printGoodsList(double payPrice, double savePrice, int num) {
		DecimalFormat df = new DecimalFormat("0.00");
		String strPrice = df.format(payPrice);

		if (this.promotion3for2 || this.promotionDiscount) {
			System.out.print("名称:" + this.name + "，单价：" + this.price + "(元)，优惠：" + this.typeName);
		} else {
			System.out.print("名称:" + this.name + "，单价：" + this.price + "(元)");
		}
		if (savePrice == 0) {
			System.out.println("，数量：" + num + this.unit + ",小计：" + strPrice + "(元)");
		} else {
			String strSavePrice = df.format(savePrice);
			System.out.println("，数量：" + num + this.unit + ",小计：" + strPrice + "(元)，小计优惠：" + strSavePrice + "(元)");
		}
	}

}
