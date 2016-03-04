package test;

import sketch.CashRegister;

public class TestCashRegister {

	public static void main(String[] args) {
		String testJSON1 = "['ITEM000001'" + "\n" + " , '  ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000003-3','ITEM000005','ITEM000005','ITEM000005']";
		String testJSON2 = "['ITEM000001','ITEM000001','ITEM000003-3','ITEM000005','ITEM000006','ITEM000005']";
		String testJSON3 = "['ITEM000007']";

		CashRegister in = new CashRegister();
		// 1.去掉无效字符
		String input = in.replace(testJSON2);
		// 2.输入内容分析后使用HashMap存储，等待检测
		in.analyse(input);
		// 3.计算并打印小票
		in.printTicket();
	}

}
