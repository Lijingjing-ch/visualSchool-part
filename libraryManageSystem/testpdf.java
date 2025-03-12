package libraryManageSystem;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class testpdf {
    public static void main(String[] args) {
        String filePath = "D:/����ѧУ-2/רҵʵѵ/����ǿ��Į�ӵ�����õ��.txt";
        try {
            String fileContent = readFile(filePath, "UTF-8");
            System.out.println(fileContent); // ����ļ�����
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filePath, String encoding) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }
}

