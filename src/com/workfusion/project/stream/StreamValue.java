package com.workfusion.project.stream;

import org.apache.log4j.Logger;

import com.workfusion.project.dao.impl.ValueDAOImpl;
import com.workfusion.project.domain.Value;
import com.workfusion.project.helper.HelperValue;

public class StreamValue implements Runnable {

	static Logger LOG = Logger.getLogger(StreamValue.class);

	private long value = 0;
	private long timestamp = 6000;

	private final int ID_VALUE = 1;
	private ValueDAOImpl dao = ValueDAOImpl.getInstance();

	@Override
	public void run() {
		System.out.println("Start");
		Value valueObj = dao.getValue(ID_VALUE);
		System.out.println(valueObj);
		value = HelperValue.getValue();
		System.out.println(value);
		if (value > valueObj.getMaxValue()) {
			System.out.println("max");
			valueObj.setMaxValue(value);
		}
		if (value < valueObj.getMinValue()) {
			System.out.println("min");
			valueObj.setMinValue(value);
		}
		valueObj.setMeanValue(HelperValue.createMeanValue(
				valueObj.getMaxValue(), valueObj.getMinValue()));
		System.out.println(valueObj);
		dao.updateValue(valueObj);
		System.out.println("update");
		try {
			System.out.println("Sleep");
			Thread.sleep(timestamp);
			System.out.println("Wake up");
			run();
		} catch (InterruptedException e) {
			LOG.error(e);
		}
	}

}
