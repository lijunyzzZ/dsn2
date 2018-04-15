package com.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import com.dao.*;
import com.domain.*;

public class GBK {
	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 */
	public static void readFileByBytes(String fileName) {
		File file = new File(fileName);
		InputStream in = null;
		try {
			System.out.println("以字节为单位读取文件内容，一次读一个字节：");
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				System.out.write(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			System.out.println("以字节为单位读取文件内容，一次读多个字节：");
			// 一次读多个字节
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			in = new FileInputStream(fileName);
			GBK.showAvailableBytes(in);
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = in.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public static void readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，/r/n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉/r，或者屏蔽/n。否则，将会多出很多空行。
				if (((char) tempchar) != '\r') {
					System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("以字符为单位读取文件内容，一次读多个字节：");
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉/r不显示
				if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {
					System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							System.out.print(tempchars[i]);
						}
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 随机读取文件内容
	 */
	public static void readFileByRandomAccess(String fileName) {
		RandomAccessFile randomFile = null;
		try {
			System.out.println("随机读取一段文件内容：");
			// 打开一个随机访问文件流，按只读方式
			randomFile = new RandomAccessFile(fileName, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 读文件的起始位置
			int beginIndex = (fileLength > 4) ? 4 : 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			while ((byteread = randomFile.read(bytes)) != -1) {
				System.out.write(bytes, 0, byteread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 显示输入流中还剩的字节数
	 */
	private static void showAvailableBytes(InputStream in) {
		try {
			System.out.println("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据byte数组来获取数据实例
	 * 
	 * @param buf
	 * @param surplus
	 * @return
	 */
	private static IndirectData GetInstanceByBuf(byte[] buf, byte[] surplus) {
		byte[] mergeByte = getMergeByte(buf, surplus);
		String longa = new String(mergeByte, 0, mergeByte.length);
		List<String> resString = new ArrayList<String>();
		// while (longa.indexOf("//") != -1) {
		// String a = longa;
		// String instanceString = a.substring(0, longa.indexOf("//")+2);
		// resString.add(instanceString);
		// longa = longa.substring(longa.indexOf("//")+2);
		// }
		String str[] = longa.split("//");
		byte[] sur = null;
		if (str[str.length - 1].indexOf("//") == -1) {
			list2String(resString, str, str.length - 1);
			sur = str[str.length - 1].getBytes();
		}else{
			list2String(resString, str, str.length);
		}
		IndirectData indirectData = new IndirectData(resString, sur);
		return indirectData;
	}

	/**
	 * str[] 转化list<String> 转化到 len
	 * 
	 * @param s
	 * @param str
	 */
	private static void list2String(List<String> s, String[] str, int len) {
		if (str.length == 0) {
			return;
		}
		for (int i = 0; i < len; i++) {
			s.add(str[i]);
		}
	}

	/**
	 * 获取剩余数组和buf数组的 合并数组
	 * 
	 * @param buf
	 * @param surplus
	 * @return
	 */
	private static byte[] getMergeByte(byte[] buf, byte[] surplus) {
		if (surplus == null) {
			return buf;
		}
		byte[] data3 = new byte[buf.length + surplus.length];
		System.arraycopy(surplus, 0, data3, 0, surplus.length);
		System.arraycopy(buf, 0, data3, surplus.length, buf.length);
		return data3;
	}

	/**
	 * 将数据实例化成对象然后存储到数据库
	 * 
	 * @param res
	 */
	private static List<DataInstance> String2Instance(List<String> res) {
		List<DataInstance> datalist = new ArrayList<DataInstance>();
		for (int i = 0; i < res.size(); i++) {
			DataInstance dataInstance = new DataInstance(res.get(i));
			datalist.add(dataInstance);
		}
		return datalist;
	}

	private static  void SaveData2DataBase(List<String> res) {
		List<DataInstance> datalist = String2Instance(res);
		DataBase_Dao a = new DataBase_Dao();
		a.saveDaos1(datalist);
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("1111111111111111111");
		String fileName = "D:/rna.gbk";
		// GBK.readFileByBytes(fileName);
		// GBK.readFileByChars(fileName);
		// GBK.readFileByLines(fileName);
		// GBK.readFileByRandomAccess(fileName);
		long startTime = System.currentTimeMillis(); // 读取开始时的时间
		File file = new File(fileName); // 找到目标文件
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 建立数据通道
		int length = 0;
		int i = 0;
		byte[] buf = new byte[204800];// 建立缓存数组，缓存数组的大小一般都是1024的整数倍，理论上越大效率越好
		byte[] surplusBuf = null;
		List<String> res = new ArrayList<String>();
		while ((length = fileInputStream.read(buf)) != -1) {
//			System.out.print(new String(buf, 0, length));
			// 这里写个方法 将余数组和当前数组集合在一起分隔成对象数组
			IndirectData indirectData = GetInstanceByBuf(buf, surplusBuf);
			res.addAll(indirectData.getInstanceString());
			surplusBuf = indirectData.getSurplus();
			
		}
		// length = fileInputStream.read(buf);
		// System.out.println(new String(buf,0,length));
		fileInputStream.close(); // 关闭资源
		long endTime = System.currentTimeMillis();
		System.out.println("读取的时	间是：" + (endTime - startTime));
		SaveData2DataBase(res);
	}

}
