package com.menjin.afr.model;

import com.sinovoice.hcicloudsdk.common.HciErrorCode;

/**OOO
 * Afr能力native方法的调用演示 流程: 系统初始化 , 获取授权/更新授权文件, Afr引擎初始化, Afr探测, Afr注册, Afr验证,
 * Afr辨识, 系统反初始化
 */
public class HciCloudAfrMain {

	private static String capkey = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * 加载用户信息工具类
		 */
		AccountInfo mAccountInfo;

		/**
		 * HciCloud帮助类，可完成灵云系统初始化，释放操作。
		 */
		HciCloudSysHelper mHciCloudSysHelper;

		/**
		 * AFR帮助类， 可完成AFR能力的初始化，开始合成，释放操作。
		 */
		HciCloudAfrHelper mHciCloudAfrHelper;

		mAccountInfo = AccountInfo.getInstance();
		boolean loadResult = mAccountInfo.loadAccountInfo();
		if (loadResult) {
			// 加载信息成功进入主界面
			System.out.println("加载灵云账号成功");
		} else {
			// 加载信息失败，显示失败界面
			System.out
					.println("加载灵云账号失败！请在assets/AccountInfo.txt文件中填写正确的灵云账户信息，账户需要从www.hcicloud.com开发者社区上注册申请。");
			return;
		}

		capkey = mAccountInfo.getCapKey();

		mHciCloudSysHelper = HciCloudSysHelper.getInstance();
		mHciCloudAfrHelper = HciCloudAfrHelper.getInstance();

		// 必须首先调用HciCloudSys的初始化方法
		int sysInitResult = mHciCloudSysHelper.init();
		if (sysInitResult != HciErrorCode.HCI_ERR_NONE) {
			System.out.println("hci init error, error code = " + sysInitResult);
			return;
		} else {
			System.out.println("hci init success");
		}

		//mHciCloudAfrHelper.Func(capkey);

		// 终止 灵云 系统
		int sysReleaseRet = mHciCloudSysHelper.release();
		if (HciErrorCode.HCI_ERR_NONE != sysReleaseRet) {
			System.out.println("hciRelease failed:" + sysReleaseRet);
		}
		System.out.println("hciRelease Success");

	}
	
}
