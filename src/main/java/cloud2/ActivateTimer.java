package cloud2;

import org.apache.log4j.Logger;

public class ActivateTimer {
	private static Logger logger = Logger.getLogger(ActivateTimer.class);

	public void doWork() {
		// 调用线程
		ActivateThread activateThread = new ActivateThread();
		// 线程数量
		int threadNum = 5;
		Thread[] threads = new Thread[threadNum];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(activateThread);
			threads[i].start();
			logger.info("线程" + i + "开启");
		}

		// 扫码完成状态，线程处理完成后返回方法
		while (true) {
			if (activateThread.successFlag == true) {// 激活完成
				break;
			}
			try {// 线程休眠2秒
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				logger.error("线程休眠异常", e);
			}
		}
	}

	public static void main(String[] args) {
		ActivateTimer a = new ActivateTimer();
		a.doWork();
	}
}
