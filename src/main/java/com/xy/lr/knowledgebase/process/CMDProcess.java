package com.xy.lr.knowledgebase.process;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class CMDProcess {

	/**
	 * 运行shell脚本
	 * @param shell 需要运行的shell脚本
	 */
	public static void execShell(String shell){
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec(shell);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 运行shell
	 * 
	 * @param shStr
	 *            需要执行的shell
	 * @return
	 * @throws IOException
	 */
	public static List runShell(String shStr) throws Exception {
		List<String> strList = new ArrayList();

		Process process;
		process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",shStr},null,null);
		InputStreamReader ir = new InputStreamReader(process
				.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		process.waitFor();
		while ((line = input.readLine()) != null){
		    strList.add(line);
		}
		
		return strList;
	}

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		runShell("sh shell/ChineseProcessor.sh");
	}
}
