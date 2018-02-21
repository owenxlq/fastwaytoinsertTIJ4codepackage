import java.io.*;
import java.util.ArrayList;  
import java.util.List;
import java.io.RandomAccessFile; 

public class WriteTIJ4 {
	private static void test(String fileDir) {  
        List<File> fileList = new ArrayList<File>();  
        File file = new File(fileDir);  
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
        if (files == null) {// 如果目录为空，直接退出  
            return;  
        }  
        // 遍历，目录下的所有文件  
        for (File f : files) {  
            if (f.isFile()) {  
                fileList.add(f);  
            } else if (f.isDirectory()) {  
                System.out.println(f.getAbsolutePath());  
                test(f.getAbsolutePath());  
            }  
        }  
        for (File f1 : fileList) { 
        	try{
        		File tmp = File.createTempFile("tmp",null);
                tmp.deleteOnExit();
        		RandomAccessFile raf = new RandomAccessFile(f1,"rw");
        		FileOutputStream tmpOut = new FileOutputStream(tmp);
        		FileInputStream tmpIn = new FileInputStream(tmp);
        		raf.seek(0);//首先的话是0
        		byte[] buf = new byte[64];
        		int hasRead = 0;
        		while((hasRead = raf.read(buf))>0){
        			//把原有内容读入临时文件
        			tmpOut.write(buf,0,hasRead);

        		}
        		raf.seek(0);
        		raf.write("package strings;\r\n".getBytes());
        		//追加临时文件的内容
        		while((hasRead = tmpIn.read(buf))>0){
        			raf.write(buf,0,hasRead);
        			System.out.println(f1.getName());  
        		}
        	}
        	catch(Exception e){  
                System.out.println(e);  
            }  
        }
    }  
	public static void main(String[] args) {
		test("D:\\eclipse\\workspace\\thinkinjava\\src\\strings");
	}
}
