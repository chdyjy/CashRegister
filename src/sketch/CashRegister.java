package sketch;

import java.util.HashMap;
import java.util.Iterator;

public class CashRegister {

	public final String SEPARATOR = "-";

	GoodsSource gs = new GoodsSource();// 实例化商品库

	HashMap<String, Integer> count = new HashMap<String, Integer>();
	Pay countPay = new Pay();

	public static void main(String[] args) {

		String inputLine = "['ITEM000001'" + "\n" + " , '  ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000003-3','ITEM000005','ITEM000005','ITEM000005']";

		CashRegister in = new CashRegister();
		// 1.去掉无效字符
		String input = in.replace(inputLine);
		// 2.输入内容分析后使用HashMap存储，等待检测
		in.analyse(input);
		// 3.计算并打印小票
		in.printTicket();
	}

	/*
	 * Description: 替换掉字符串中的空格、换行、制表符等无效字符 Input: String类型的JSON字符串 Output: true
	 * or false
	 */
	public String replace(String str) {
		return str.replaceAll("\\s*|\t|\r|\n", "");
	}

	/*
	 * Description: 分析JSON数据，解析为Item数组，分别进行计算 Input: String类型的JSON字符串 Output:
	 * true or false
	 */
	public boolean analyse(String str) {

		if (!this.checkFormat(str, '[', ']')) {
			return false;
		}
		// 剔除收尾字符
		str = str.substring(1, str.length() - 1);
		// 以“,”分割成数组
		String strArray[] = str.split(",");

		String itemArray[] = null;
		int payNum = 0;
		// 遍历分割分割后数组
		for (String item : strArray) {
			if (!this.checkFormat(item, '\'', '\'')) {
				return false;
			}
			// 去掉首尾包裹的单引号
			item = item.substring(1, item.length() - 1);

			if (item.contains(SEPARATOR)) {
				// 单个Item中“-”表示购买数量，取出并进行计算
				itemArray = item.split(SEPARATOR);
				item = itemArray[0];
				payNum = Integer.parseInt(itemArray[1]);
				this.compute(item, payNum);

			} else {
				this.compute(item, 1);
			}
		}
		return true;
	}

	/*
	 * Description: 字符串包裹字符检测 Input: (String)待检测字符串，(char)首字符，(char)末尾字符 Output:
	 * true or false
	 */
	private boolean checkFormat(String str, char cPrev, char cNext) {
		if (str.charAt(0) != cPrev || str.charAt(str.length() - 1) != cNext) {
			System.out.println("输入格式有误");
			return false;
		}
		return true;
	}

	/*
	 * Description: 将单条Item中包含的信息使用HashMap存储 Input: (String)item，(int)payNum
	 * 
	 */
	private void compute(String item, int payNum) {
		int mCount = 0;

		if (count.get(item) != null) {
			mCount = count.get(item);
			mCount += payNum;
			count.put(item, mCount);
		} else {
			count.put(item, payNum);
		}

	}

	/*
	 * Description: 计算并打印购物小票 Input: (String)item，(int)payNum
	 * 
	 */
	public void printTicket() {

		System.out.println("***<怎么可能不赚钱商店>购物清单***");
		// 使用迭代器遍历HashMap对象
		@SuppressWarnings("rawtypes")
		Iterator iter = count.entrySet().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			HashMap.Entry entry = (HashMap.Entry) iter.next();
			Object key = entry.getKey();
			// 查询商品库GoodsSource,由于商品库一定是最全的，不必考虑条码不存在的情况
			Goods g = gs.query(key.toString());
			// 购买此商品
			countPay = g.pay(this.getPayNum(key.toString()));
		}
		System.out.println("----------------------");
		countPay.showDiscountRecordSet();
		System.out.println("总计：" + Pay.totalPrice + "(元)");
		if (Pay.totalSavePrice > 0) {
			System.out.println("节省：" + Pay.totalSavePrice + "(元)");
		}
		System.out.println("**********************");
	}

	/*
	 * Description: 从HashMap中获取购买数量 Input: (String)item Output: (int)payNum
	 */
	private int getPayNum(String str) {
		return count.get(str);
	}

}
