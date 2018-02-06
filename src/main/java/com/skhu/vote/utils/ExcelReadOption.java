package com.skhu.vote.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ExcelReadOption {
	private String filePath;			// 엑셀 파일의 경로
	private List<String> outputCols;	// 추출할 컬럼명
	private int startRow;				// 추출을 시작할 행 번호

	public List<String> getOutputCols() {
		List<String> temp = new ArrayList<>();
		temp.addAll(outputCols);
		return temp;
	}

	public void setOutputCols(List<String> outputCols) {
		List<String> temp = new ArrayList<>();
		temp.addAll(outputCols);
		this.outputCols = temp;
	}

	public void setOutputCols(String ... outputCols) {
		if(this.outputCols == null) {
			this.outputCols = new ArrayList<String>();
		}
		for(String outputCol : outputCols) {
			this.outputCols.add(outputCol);
		}
	}
}
