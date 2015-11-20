package com.yzt.app.utils.compare;

import java.util.Comparator;

import com.yzt.app.model.YztLearningcenter;

public class LearningcenterComparatorComment implements Comparator<YztLearningcenter>{
	public int compare(YztLearningcenter o1, YztLearningcenter o2) {
		return (int)(o1.getComments() - o2.getComments());
	}
}
