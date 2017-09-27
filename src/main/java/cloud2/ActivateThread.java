package cloud2;

import org.apache.log4j.Logger;

public class ActivateThread implements Runnable {
	private static Logger logger = Logger.getLogger(ActivateThread.class);

	/**
	 * 状态-执行中
	 */
	private final String RUN_START = "run";

	/**
	 * 状态-执行完成
	 */
	private final String RUN_STOP = "stop";

	/**
	 * 运行状态
	 */
	private String RUN_STATUS = RUN_STOP;

	public volatile boolean successFlag = false;
	private int threadCount = 5;

	public void doWork() {
		try {

		} catch (Exception e) {
			logger.error("公有云执行二维码定时激活任务异常", e);
			e.printStackTrace();

		} finally {
			RUN_STATUS = RUN_STOP;
			successFlag = true; //码包激活后结束线程
		}
	}

	public void run() {
		while (true) {
			if (successFlag) {
				logger.info("线程===" + Thread.currentThread().getName().toString() + "处理完成");
				break;
			} else {
				doWork();
			}
		}
		// 判断该类中线程是否执行完毕
		threadCount--;

		if (threadCount == 0) {
			logger.info("所有线程执行完毕");
		}

	}

}
