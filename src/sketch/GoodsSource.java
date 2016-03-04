package sketch;

import java.util.TreeMap;

/*
 * GoodsSource.java
 * Description: 商品库，初始化时构造所有存在的商品条码及信息
 */
public class GoodsSource {
	TreeMap<String, Goods> map = new TreeMap<String, Goods>();

	public GoodsSource() {
		Goods g1 = new Goods("可口可乐", "瓶", 3.00, false, false);
		Goods g2 = new Goods("羽毛球", "个", 5.00, false, true);
		Goods g3 = new Goods("苹果", "斤", 4.00, true, true);
		Goods g4 = new Goods("矿泉水", "瓶", 2.50, false, false);
		Goods g5 = new Goods("牛奶", "盒", 5.50, false, true);
		Goods g6 = new Goods("香蕉", "斤", 4.00, false, false);
		Goods g7 = new Goods("菠菜", "斤", 0.80, false, false);

		map.put("ITEM000001", g1);
		map.put("ITEM000002", g2);
		map.put("ITEM000003", g3);
		map.put("ITEM000004", g4);
		map.put("ITEM000005", g5);
		map.put("ITEM000006", g6);
		map.put("ITEM000007", g7);
	}

	public Goods query(String item) {
		Goods g = map.get(item);
		return g;
	}

}
