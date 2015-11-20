package com.yzt.app.utils.compare;

import java.util.Comparator;

import com.yzt.app.model.YztLearningcenter;

public class LearningcenterComparatorCommentCount implements Comparator<YztLearningcenter>{
	public int compare(YztLearningcenter o1, YztLearningcenter o2) {
		return o1.getCommentsCount() - o2.getCommentsCount();
	}
	
}
