package com.skhu.vote.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {
	
	public static List<Map<String, String>> read(ExcelReadOption excelReadOption) {
		/*
		 * 엑셀 파일 자체, 엑셀 파일을 읽어들인다.
		 * FileType.getWorkbook --> 파일의 확장자에 따라서 적절하게 가져온다.
		 */
		Workbook wb = ExcelFileType.getWorkbook(excelReadOption.getFilePath());
		
		Sheet sheet = wb.getSheetAt(0);		// 엑셀 파일에서 첫번째 시트를 가져온다.
		
		int numOfRows = sheet.getPhysicalNumberOfRows();
		int numOfCells = 0;
		
		Row row = null;
		Cell cell = null;
		
		String cellName = "";
		
		/*
		 * 각 row마다의 값을 저장할 map 객체
		 * 저장되는 형식은 put("A", "이름");
		 */
		Map<String, String> map = null;
		
		/*
		 * 각 row를 리스트에 담는다.
		 * 하나의 row를 하나의 map으로 표현
		 * List에는 모든 row가 포함된다.
		 */
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		// Row만큼 반복
		for(int rowIndex = excelReadOption.getStartRow() - 1; rowIndex < numOfRows; ++rowIndex) {
			/*
			 * Workbook에서 가져온 시트 중 rowIndex에 해당하는 row를 가져온다.
			 * 하나의 row는 여러 개의 cell을 가진다.
			 */
			row = sheet.getRow(rowIndex);
			
			if(row != null) {
				numOfCells = row.getLastCellNum();					// 가져온 row의 cell 개수를 구한다.
				map = new HashMap<String, String>();				// 데이터를 담을 map 객체 초기화
				
				// cell의 수만큼 반복
				for(int cellIndex = 0; cellIndex < numOfCells; ++cellIndex) {
					cell = row.getCell(cellIndex);						// row에서 cellIndex에 해당하는 cell을 가져온다.
					cellName = ExcelCellRef.getName(cell, cellIndex);	// 현재 cell의 이름을 가져온다. (이름의 예: A, B, C ...)
					
					// 추출 대상 컬럼인지 확인 후 대상이 아니라면 for로 다시 올라간다. 
					if(!excelReadOption.getOutputCols().contains(cellName)) continue;
					// map객체의 cell의 이름을 Key로 데이터를 담는다.
					map.put(cellName, ExcelCellRef.getValue(cell));
				}
				result.add(map);
			}
		}
		return result;
	}

}
