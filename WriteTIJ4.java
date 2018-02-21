import java.io.*;
import java.util.ArrayList;  
import java.util.List;
import java.io.RandomAccessFile; 

public class WriteTIJ4 {
	private static void test(String fileDir) {  
        List<File> fileList = new ArrayList<File>();  
        File file = new File(fileDir);  
        File[] files = file.listFiles();// ��ȡĿ¼�µ������ļ����ļ���  
        if (files == null) {// ���Ŀ¼Ϊ�գ�ֱ���˳�  
            return;  
        }  
        // ������Ŀ¼�µ������ļ�  
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
        		raf.seek(0);//���ȵĻ���0
        		byte[] buf = new byte[64];
        		int hasRead = 0;
        		while((hasRead = raf.read(buf))>0){
        			//��ԭ�����ݶ�����ʱ�ļ�
        			tmpOut.write(buf,0,hasRead);

        		}
        		raf.seek(0);
        		raf.write("package strings;\r\n".getBytes());
        		//׷����ʱ�ļ�������
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
